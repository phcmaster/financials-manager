package br.com.fm.investment.controller;

import br.com.fm.investment.service.feign.CurrencyQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    private CurrencyQuoteService service;

    @GetMapping("/currencyList")
    public ResponseEntity<?> listCurrencyQuote() {
        return ResponseEntity.ok().body(service.currencyListAll().getBody());

    }

}
