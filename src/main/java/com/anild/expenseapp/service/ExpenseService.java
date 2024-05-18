package com.anild.expenseapp.service;

import com.anild.expenseapp.config.ModelMapperConfig;
import com.anild.expenseapp.dto.ExpenseDTO;
import com.anild.expenseapp.entity.Expense;
import com.anild.expenseapp.repository.ExpenseRepository;
import com.anild.expenseapp.util.DateTimeUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    ModelMapper mapper;
    public List<ExpenseDTO> getAllExpenses(){
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream().map(this::mapExpenseToExpenseDTO).collect(Collectors.toList());
    }
    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException {
        Expense expense = mapExpenseDTOtoExpense(expenseDTO);
        expense = expenseRepository.save(expense);
        return mapExpenseToExpenseDTO(expense);
    }

    public void deleteExpense(String expenseId){
        Optional<Expense> expense = expenseRepository.findByExpenseId(expenseId);
        if (expense.isPresent()){
            expenseRepository.delete(expense.get());
        }
    }
    private ExpenseDTO mapExpenseToExpenseDTO(Expense expense){
        expense.setDateString(DateTimeUtil.convertDateToString(expense.getDate()));
        return mapper.map(expense, ExpenseDTO.class);
    }
    private Expense mapExpenseDTOtoExpense(ExpenseDTO expenseDTO) throws ParseException {
        Expense expense = mapper.map(expenseDTO, Expense.class) ;
        expense.setExpenseId(UUID.randomUUID().toString());
        System.out.println("Expense Id : " + expense.getExpenseId());
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));
        return expense;
    }
}
