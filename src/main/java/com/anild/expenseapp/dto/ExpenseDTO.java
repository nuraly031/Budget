package com.anild.expenseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDTO {
    private Long id;
    private String expenseId;
    private String name;
    private String description;
    private BigDecimal amount;
    private Date date;
    private String dateString;
}
