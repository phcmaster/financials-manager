package br.com.fm.expensesmanager.controller;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.dto.MonthlySpendResponse;
import br.com.fm.expensesmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/expenses")
public class ExpenseManagerController {


    @Autowired
    private ExpenseService expenseService;


    @PostMapping("/register")
    public ResponseEntity<?> registerNewExpense(@RequestBody ExpenseRequest request) throws ParseException {
        expenseService.registerExpense(request);
        return ResponseEntity.ok().body("Despesa cadastrada com sucesso");

    }

    @PutMapping("/uptade/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest request){
        expenseService.updateExpense(id, request);
        return ResponseEntity.ok().body("Successfully updated.");

    }

    @GetMapping("/all")
    public ResponseEntity<?> gettAllExpenses(){
        return ResponseEntity.ok().body(expenseService.listAllExpenses());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().body("Successfully deleted.");

    }


    @GetMapping("/monthlyValueSpend")
    public ResponseEntity<?> monthlyValueSpend(){
        return ResponseEntity.ok().body(expenseService.monthlyValueSpend());
    }

    @GetMapping("/monthlyAverageSpend")
    public ResponseEntity<?> monthlyAverageSpend(){
        return ResponseEntity.ok().body(expenseService.monthlyAverageSpend());
    }


    @GetMapping("/monthlySpend")
    public ResponseEntity<MonthlySpendResponse> monthlySpend(){
        return ResponseEntity.ok().body(expenseService.monthlySpend());
    }




}
