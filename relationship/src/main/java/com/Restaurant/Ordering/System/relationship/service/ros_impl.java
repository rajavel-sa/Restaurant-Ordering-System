package com.Restaurant.Ordering.System.relationship.service;

import com.Restaurant.Ordering.System.relationship.model.*;
import com.Restaurant.Ordering.System.relationship.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ros_impl implements ros_sevice_itf {

    private final menu_repo menuRepo;
    private final customer_repo customerRepo;
    private final order_repo orderRepo;
    private final payment_repo paymentRepo;
    private final order_items_repo orderItemsRepo;

    public ros_impl(menu_repo menuRepo, customer_repo customerRepo,order_repo orderRepo, payment_repo paymentRepo,order_items_repo orderItemsRepo) {
        this.menuRepo = menuRepo;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
        this.orderItemsRepo = orderItemsRepo;
    }

    @Override
    public List<menu_m> getAllMenuItems() {
        return menuRepo.findAll();
    }

    @Override @Transactional
    public order_m placeOrder(String customerName, Map<Integer, Integer> items) {
        customer_m customer = customerRepo.findAll().stream()
                .filter(x-> x.getC_name().equalsIgnoreCase(customerName))
                .findFirst()
                .orElseGet(() -> {
                    customer_m newCustomer = new customer_m();
                    newCustomer.setC_name(customerName);
                    return customerRepo.save(newCustomer);
                });

        order_m order = new order_m();
        order.setCustomer(customer);
        order.setO_status("Pending");
        order.setO_paymentStatus("Unpaid");
        order.setO_timestamp(LocalDateTime.now());
        orderRepo.save(order);

        int total = 0;
        List<menu_m> allMenuItems = menuRepo.findAll();

        for (Map.Entry<Integer, Integer> y : items.entrySet()) {
            menu_m menuItem = allMenuItems.stream()
                    .filter(item -> item.getF_id() == y.getKey())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Menu item not found: " + y.getKey()));

            order_items_m orderItem = new order_items_m();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(y.getValue());
            orderItem.setItem_total(menuItem.getF_price() * y.getValue());
            orderItemsRepo.save(orderItem);

            total += orderItem.getItem_total();
        }

        order.setO_total(total);
        return orderRepo.save(order);
    }

    @Override
    public order_m getOrderStatus(String customerName) {
        return orderRepo.findAll().stream()
                .filter(o -> o.getCustomer().getC_name().equalsIgnoreCase(customerName))
                .max(Comparator.comparing(order_m::getO_timestamp))
                .orElseThrow(() -> new RuntimeException("No orders found for: " + customerName));
    }

    @Override @Transactional
    public payment_m processPayment(String customerName, int amount) {
        order_m order = orderRepo.findAll().stream()
                .filter(o -> o.getCustomer().getC_name().equalsIgnoreCase(customerName))
                .max(Comparator.comparing(order_m::getO_timestamp))
                .orElseThrow(() -> new RuntimeException("No pending order found"));

        if (amount < order.getO_total()) {
            throw new RuntimeException("Insufficient payment. Required: " + order.getO_total());
        }

        payment_m payment = new payment_m();
        payment.setOrder(order);
        payment.setP_total(amount);
        payment.setP_paymentStatus("Paid");
        payment.setP_timestamp(LocalDateTime.now());
        paymentRepo.save(payment);

        order.setO_paymentStatus("Paid");
        order.setO_status("In Progress");
        orderRepo.save(order);

        return payment;
    }





    @Override
    public menu_m addMenuItem(menu_m item) {
        return menuRepo.save(item);
    }

    @Override
    public menu_m updateMenuItem(int itemId, menu_m item) {
        menu_m existing = menuRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        existing.setF_name(item.getF_name());
        existing.setF_price(item.getF_price());
        return menuRepo.save(existing);
    }

    @Override
    public void deleteMenuItem(int itemId) {
        menuRepo.deleteById(itemId);
    }





    @Override
    public List<order_m> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public List<order_m> getPaidOrders() {
        return orderRepo.findAll().stream()
                .filter(o -> "Paid".equalsIgnoreCase(o.getO_paymentStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public order_m markOrderAsCompleted(int orderId) {
        order_m order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setO_status("Completed");
        return orderRepo.save(order);
    }
}