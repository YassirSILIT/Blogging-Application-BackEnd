package com.YS.BlokingServer.Repository;

import com.YS.BlokingServer.entities.Payment;
import com.YS.BlokingServer.enume.PaymentStatus;
import com.YS.BlokingServer.enume.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
