package com.shop.vtluan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.shop.vtluan.model.Order_detail;
import com.shop.vtluan.model.Orders;
import com.shop.vtluan.repository.Order_detailRepository;
import com.shop.vtluan.service.OrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin")
public class OrdersController {
    private final OrdersService ordersService;
    private final Order_detailRepository order_detailRepository;

    public OrdersController(OrdersService ordersService, Order_detailRepository order_detailRepository) {
        this.ordersService = ordersService;
        this.order_detailRepository = order_detailRepository;
    }

    @GetMapping("/orders/update/{id}")
    public String getUpdateOrder(@PathVariable("id") long id, Model model) {
        Optional<Orders> order = this.ordersService.getOrderById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
        }
        return "admin/manage_orders/update_orders";
    }

    @PostMapping("/orders/updated")
    public String postUpdateOrder(@ModelAttribute Orders order) {
        Orders currentOrder = this.ordersService.getOrderById(order.getId()).isPresent()
                ? this.ordersService.getOrderById(order.getId()).get()
                : null;
        if (currentOrder != null) {
            currentOrder.setStatus(order.getStatus());
            this.ordersService.updateOrder(currentOrder);
        }
        return "redirect:/admin/orders";
    }

    @GetMapping("/orders/remove/{id}")
    public String getRemoveOrder(@PathVariable("id") long id, Model model) {
        Optional<Orders> order = this.ordersService.getOrderById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
        }
        return "admin/manage_orders/remove_orders";
    }

    @PostMapping("/orders/removed")
    public String postRemoveOrder(@RequestParam("id") long id) {
        Optional<Orders> order = this.ordersService.getOrderById(id);
        if (order.isPresent()) {
            List<Order_detail> lDetails = order.get().getOrder_details();
            for (Order_detail order_detail : lDetails) {
                this.order_detailRepository.delete(order_detail);
            }
            this.ordersService.deleteOrder(order.get());
        }

        return "redirect:/admin/orders";
    }

}
