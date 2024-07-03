package com.YS.BlokingServer.service.Payment;

import com.YS.BlokingServer.Repository.PaymentRepository;
import com.YS.BlokingServer.Repository.StudentRepository;
import com.YS.BlokingServer.entities.Payment;
import com.YS.BlokingServer.entities.Student;
import com.YS.BlokingServer.enume.PaymentStatus;
import com.YS.BlokingServer.enume.PaymentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Payment savePayment(MultipartFile file, LocalDate date, double amount,
                               PaymentType type, String studentCode) throws IOException {
        //creation des folders
        Path folderPath = Paths.get(System.getProperty("user.home"),"ensa-data","payments");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        //creation du file
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"ensa-data","payments",fileName+".pdf");
        //copy du file
        Files.copy(file.getInputStream(),filePath);
        //creation du payment
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
                .status(PaymentStatus.CREATED)
                .amount(amount)
                .date(LocalDate.now())
                .type(type)
                .student(student)
                .file(filePath.toUri().toString())
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment updatePaymentByStatus(PaymentStatus status, Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> listOfPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> listOfPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @Override
    public List<Payment> listOfPaymentsByStudentCode(String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @Override
    public List<Payment> listOfPaymentsByType(PaymentType type) {
        return paymentRepository.findByType(type);
    }

    @Override
    public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}
