package com.example.shop.order;

import com.example.shop.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public Order findByOrderId(Long orderId){
        return em.find(Order.class, orderId);
    }

    public List<Order> findAll(){
        return em.createQuery("SELECT m FROM Order m", Order.class)
                .getResultList();
    }

    public void deleteById(Long orderId){
        Order order = em.find(Order.class, orderId);
        em.remove(order);
    }

    public void save(Order order){
        em.persist(order);
    }
}
