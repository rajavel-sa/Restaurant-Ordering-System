package com.Restaurant.Ordering.System.relationship.service;

import com.Restaurant.Ordering.System.relationship.model.*;

import java.util.*;

public interface ros_sevice_itf {

    List<menu_m> getAllMenuItems();

    order_m placeOrder(String customerName, Map<Integer, Integer> items);
    order_m getOrderStatus(String customerName);
    payment_m processPayment(String customerName, int amount);

    menu_m addMenuItem(menu_m item);
    menu_m updateMenuItem(int itemId, menu_m item);
    void deleteMenuItem(int itemId);

    List<order_m> getAllOrders();
    List<order_m> getPaidOrders();
    order_m markOrderAsCompleted(int orderId);
}