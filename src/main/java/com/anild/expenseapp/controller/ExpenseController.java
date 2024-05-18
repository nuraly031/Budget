package com.anild.expenseapp.controller;

import com.anild.expenseapp.dto.ExpenseDTO;
import com.anild.expenseapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public String getExpensesList(Model model){
        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "expenses-list";
    }

    @GetMapping("/create-expense")
    public String showExpenseForm(Model model){
        model.addAttribute("expense",new ExpenseDTO());
        return "expense-form";
    }

    @PostMapping("/saveOrUpdateExpense")
    public String saveOrUpdateExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ParseException {
        System.out.println("ExpenseDTO to save||update : " + expenseDTO);
        expenseService.saveExpenseDetails(expenseDTO);
        return "redirect:/expenses";
    }

    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam("expenseId") String expenseId){
        System.out.println("Expense Id : " + expenseId);
        expenseService.deleteExpense(expenseId);
        return "redirect:/expenses";
    }
}
