package com.paulotech.api_bank_tech.controller;

import com.paulotech.api_bank_tech.dto.*;
import com.paulotech.api_bank_tech.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
            description = "User account created successfully"
    )
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }

    @Operation(
            summary = "login",
            description = "This API allows a user to log in to their account. It requires the user's email and password."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/login")
    public BankResponse login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @Operation(
            summary = "Balance enquiry",
            description = "Given an account number, this API retrieves the current balance of the account."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 201 CREATED"
    )
    @GetMapping("balance-enquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request) {
        return userService.balanceEnquiry(request);
    }

    @Operation(
            summary = "Name enquiry",
            description = "Given an account number, this API retrieves the name associated with the account."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 201 CREATED"
    )
    @GetMapping("name-enquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request) {
        return userService.nameEnquiry(request);
    }

    @Operation(
            summary = "Credit",
            description = "This API credits a specified amount to the user's account. It requires the account number and the amount to be credited."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest request) {
        return userService.creditAccount(request);
    }

    @Operation(
            summary = "Debit",
            description = "This API debits a specified amount from the user's account. It requires the account number and the amount to be debited."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest request) {
        return userService.debitAccount(request);
    }

    @Operation(
            summary = "Transfer",
            description = "This API transfers a specified amount from one user's account to another. It requires the source account number, destination account number, and the amount to be transferred."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("transfer")
    public BankResponse transfer(@RequestBody TransferRequest request) {
        return userService.transfer(request);
    }
}
