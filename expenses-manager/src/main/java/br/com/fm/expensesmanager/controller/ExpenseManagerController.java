package br.com.fm.expensesmanager.controller;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;
import br.com.fm.expensesmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseManagerController {


    @Autowired
    private ExpenseService expenseService;


    @PostMapping("/register")
    public ResponseEntity<?> registerNewExpense(@RequestBody ExpenseRequest request) throws ParseException {
        expenseService.registerExpense(request);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/uptade/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest request){

        return ResponseEntity.ok().body("updated");

    }

    @GetMapping("/all")
    public ResponseEntity<?> gettAllExpenses(){
        return ResponseEntity.ok().body(expenseService.listAllExpenses());

    }

}
