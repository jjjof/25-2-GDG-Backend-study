package com.example.shop.order;

import org.springframework.stereotype.Service;

import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.dto.OrderUpdateRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Long createOrder(OrderCreateRequest request) {//findByOrderId 정의 필요
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

    @Transactional(readOnly = true) //findAll 정의 필요
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다.");
        }

        return order;
    }

    @Transactional
    public void deleteOrder(Long orderId) {//deleteById 정의 필요
        Order order = orderRepository.findByOrderId(orderId);

        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다.");
        }

        // Repository에서 주문 삭제
        orderRepository.deleteById(orderId);
    }
}
