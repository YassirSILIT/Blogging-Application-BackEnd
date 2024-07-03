package com.YS.BlokingServer.service.Payment;

import com.YS.BlokingServer.entities.Payment;
import com.YS.BlokingServer.enume.PaymentStatus;
import com.YS.BlokingServer.enume.PaymentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    Payment savePayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException;
    Payment getPaymentById(Long id);
    Payment updatePaymentByStatus(PaymentStatus status, Long id);
    List<Payment> listOfPayments();
    List<Payment> listOfPaymentsByStatus(PaymentStatus status);
    List<Payment> listOfPaymentsByStudentCode(String code);
    List<Payment> listOfPaymentsByType(PaymentType type);
    byte[] getPaymentFile(Long paymentId) throws IOException;
}
