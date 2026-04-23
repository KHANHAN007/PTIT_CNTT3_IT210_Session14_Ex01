package com.session13.ex01.service;

import com.session13.ex01.entity.Order;
import com.session13.ex01.entity.Wallet;
import com.session13.ex01.repository.OrderRepository;
import com.session13.ex01.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final OrderRepository orderRepo;
    private final WalletRepository walletRepo;

    @Transactional
    public void processPayment(Long orderId, Long walletId, double amount){
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        order.setStatus("PAID");

//        if(true){
//            throw new RuntimeException("Simulated payment failure");
//        }

        Wallet wallet = walletRepo.findById(walletId).orElseThrow(()-> new RuntimeException("Wallet not found"));
        if(wallet.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");
        }
        wallet.setBalance(wallet.getBalance()-amount);
    }
}
