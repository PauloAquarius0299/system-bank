package com.paulotech.api_bank_tech.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE="001";
    public static final String ACCOUNT_EXISTS_MESSAGE ="Esse usuário foi criado!";
    public static final String ACCOUNT_CREATED_CODE="002";
    public static final String ACCOUNT_CREATED_MESSAGE ="Conta criada com sucesso!";

    public static String generateAccountNumber(){
        //2025 + randomSixDigits

        Year currentYear = Year.now();

        int min = 100000;
        int max = 999999;

        //generate a random number between min and max

        int randNumber = (int) Math.floor(Math.random() * (min - max + 1) + min);

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        StringBuilder accountNumber = new StringBuilder();

        return accountNumber.append(year).append(randomNumber).toString();

    }

}
