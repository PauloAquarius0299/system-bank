package com.paulotech.api_bank_tech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String recipient;
    private String messageBody;
    private String subject;
    private String attachment;
}
