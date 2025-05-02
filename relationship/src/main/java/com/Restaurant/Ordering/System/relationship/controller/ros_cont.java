package com.Restaurant.Ordering.System.relationship.controller;

import com.Restaurant.Ordering.System.relationship.model.*;
import com.Restaurant.Ordering.System.relationship.service.ros_impl;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/ROST")
public class ros_cont {

    private final ros_impl ros_impl_ref;

    public ros_cont(ros_impl lol) {

        this.ros_impl_ref = lol;
    }





    @GetMapping("/menu")
    public List<menu_m> getAllMenuItems() {
        return ros_impl_ref.getAllMenuItems();
    }

    @PostMapping("/order")
    public order_m placeOrder(@RequestParam String c_name, @RequestBody Map<Integer, Integer> items) {return ros_impl_ref.placeOrder(c_name, items);}

    @PostMapping("/payment/{c_name}")
    public payment_m processPayment(@PathVariable String c_name, @RequestBody int amount) {return ros_impl_ref.processPayment(c_name, amount);}

    @GetMapping("/order_status/{c_name}")
    public order_m getOrderStatus(@PathVariable String c_name) {
        return ros_impl_ref.getOrderStatus(c_name);
    }





    @GetMapping("/admin/menu")
    public List<menu_m> adminGetAllMenu() {
        return ros_impl_ref.getAllMenuItems();
    }

    @PostMapping("/admin/menu/add_item")
    public menu_m addMenuItem(@RequestBody menu_m item) {
        return ros_impl_ref.addMenuItem(item);
    }

    @PatchMapping("/admin/menu/edit_item/{f_id}")
    public menu_m updateMenuItem(@PathVariable int f_id, @RequestBody menu_m item) {return ros_impl_ref.updateMenuItem(f_id, item);}

    @DeleteMapping("/admin/menu/remove_item/{f_id}")
    public void deleteMenuItem(@PathVariable int f_id) {
        ros_impl_ref.deleteMenuItem(f_id);
    }





    @GetMapping("/admin/orders")
    public List<order_m> getAllOrders() {
        return ros_impl_ref.getAllOrders();
    }

    @GetMapping("/admin/paid_orders")
    public List<order_m> getPaidOrders() {
        return ros_impl_ref.getPaidOrders();
    }

    @PostMapping("/admin/completed/{o_id}")
    public order_m markOrderCompleted(@PathVariable int o_id) {
        return ros_impl_ref.markOrderAsCompleted(o_id);
    }
}