package com.rajeeb.ps.api.repository;

import com.rajeeb.ps.api.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
    PaymentEntity findByOrderId(Integer orderId);
}
