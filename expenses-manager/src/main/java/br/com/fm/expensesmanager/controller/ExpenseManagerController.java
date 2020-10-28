package br.com.fm.expensesmanager.controller;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.dto.Session;
import br.com.fm.expensesmanager.service.ExpenseRegisterService;
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
    private ExpenseRegisterService expenseRegisterService;


    @PostMapping("/register")
    public ResponseEntity<String> registerNewExpepense(@RequestBody ExpenseRequest request){
        return ResponseEntity.ok().body(expenseRegisterService.registerExpense(request));

    }

    @GetMapping("/register")
    public ResponseEntity<String> teste(){
        Session session = jwtUtils.getSession();
        return ResponseEntity.ok().body("Hello World!");

    }
}
