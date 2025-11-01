package com.example.shop.order;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.dto.OrderUpdateRequest;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
//orderId, phoneNumber, address, product, date
public class OrderService {

    private final OrderRepository orderRepository;

    //@Transactional
    public Long createOrder(OrderCreateRequest request) {
        Order existingOrder = orderRepository.findByOrderId(request.getOrderId());

        if (existingOrder != null) {
            throw new RuntimeException("이미 존재하는 주문 아이디입니다: " + request.getOrderId());
        }
        Order order = new Order(
                request.getOrderId(),
                request.getProduct(),
                request.getDate(),
                request.getPhoneNumber(),
                request.getAddress()
        );

        orderRepository.save(order);

        return order.getOrderId();
    }

    //@Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //@Transactional(readOnly = true)
    public Order getOrderById(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다.");
        }

        return order;
    }

    //@Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다.");
        }

        // Repository에서 주문 삭제
        orderRepository.deleteByOrderId(orderId);
    }
}
