package com.session13.ex01.controller;

import com.session13.ex01.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public String showForm() {
        return "payment";
    }

    @PostMapping
    public String pay(@RequestParam Long orderId,
                      @RequestParam Long walletId,
                      @RequestParam double amount) {

        paymentService.processPayment(orderId, walletId, amount);
        return "Success";
    }
}