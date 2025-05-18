package com.paulotech.api_bank_tech.controller;

import com.paulotech.api_bank_tech.dto.*;
import com.paulotech.api_bank_tech.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User account management APIs")
public class UserController {

    @Autowired
    UserService userService;
    @Operation(
            summary = "Create a new user account",
            description = "This API creates a new user account in the system. It requires the user's details such " +
                      "as name, " +
            "email, and initial deposit amount.")
    @ApiResponse(
            responseCode = "200",
            description = "User account created successfully",
    )
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }

    @Operation(
            summary = "Balance enquiry",
            description = "Given an account number, this API retrieves the current balance of the account.")
    @ApiResponse(
            responseCode = "200",
            description = "User account created successfully",
    )
    @GetMapping("balance-enquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request) {
        return userService.balanceEnquiry(request);
    }

    @GetMapping("name-enquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request) {
        return userService.nameEnquiry(request);
    }

    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest request) {
        return userService.creditAccount(request);
    }

    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest request) {
        return userService.debitAccount(request);
    }

    @PostMapping("transfer")
    public BankResponse transfer(@RequestBody TransferRequest request) {
        return userService.transfer(request);
    }
}
