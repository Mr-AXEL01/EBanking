package net.axel.ebanking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankingController {

    @GetMapping("/myLoans")
    public String getLoans() {
        return "Your loans information";
    }

    @GetMapping("/myCards")
    public String getCards() {
        return "Your cards information";
    }

    @GetMapping("/myAccount")
    public String getAccount() {
        return "Your account information";
    }

    @GetMapping("/myBalance")
    public String getBalance() {
        return "Your balance information";
    }
}

