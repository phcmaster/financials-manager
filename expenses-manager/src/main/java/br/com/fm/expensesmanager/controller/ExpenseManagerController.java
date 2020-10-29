package br.com.fm.expensesmanager.controller;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.dto.Session;
import br.com.fm.expensesmanager.service.ExpenseService;
import br.com.fm.expensesmanager.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseManagerController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ExpenseService expenseService;


    @PostMapping("/register")
    public ResponseEntity<?> registerNewExpense(@RequestBody ExpenseRequest request){
        expenseService.registerExpense(request);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/uptade/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest request){

        return ResponseEntity.ok().body("updated");

    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste(){
        Session session = jwtUtils.getSession();
        return ResponseEntity.ok().body("Hello World!");

    }

}
