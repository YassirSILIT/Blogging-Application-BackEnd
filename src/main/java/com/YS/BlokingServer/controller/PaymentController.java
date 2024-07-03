package com.YS.BlokingServer.controller;

import com.YS.BlokingServer.entities.Payment;
import com.YS.BlokingServer.enume.PaymentStatus;
import com.YS.BlokingServer.enume.PaymentType;
import com.YS.BlokingServer.service.Payment.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "*")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping("/allPayments")
    public List<Payment> listOfPayments(){
        return paymentService.listOfPayments();
    }
    @GetMapping("/PaymentStatus/{status}")
    public List<Payment> listOfPaymentsByStatus(@RequestParam PaymentStatus status){
        return paymentService.listOfPaymentsByStatus(status);
    }
    @GetMapping("/studentCode/{code}")
    public List<Payment> listOfPaymentsByStudentCode(@PathVariable String code){
        return paymentService.listOfPaymentsByStudentCode(code);
    }
    @GetMapping("/paymentType/{type}")
    public List<Payment> listOfPaymentsType(@RequestParam PaymentType type){
        return paymentService.listOfPaymentsByType(type);
    }
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id);
    }
    @PutMapping("/updatePayment/{id}")
    public Payment updatePaymentByStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
        return paymentService.updatePaymentByStatus(status,id);
    }
    @PostMapping(path = "/savePayment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amount,
                               PaymentType type, String studentCode) throws IOException {
        return paymentService.savePayment(file,date,amount,type,studentCode);
    }
    @GetMapping(path = "/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException{
        return paymentService.getPaymentFile(paymentId);
    }
}
