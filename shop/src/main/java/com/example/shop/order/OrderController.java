package com.example.shop.order;

import com.example.shop.order.OrderService;
import com.example.shop.order.dto.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.OrderedSetType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")

public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderCreateRequest request){
        Long orderId = orderService.createOrder(request);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        // Service 계층에서 주문 목록을 가져온다
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build(); // 204 no content
    }
}
