package com.paulotech.api_bank_tech.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.paulotech.api_bank_tech.dto.EmailDetails;
import com.paulotech.api_bank_tech.entity.Transaction;
import com.paulotech.api_bank_tech.entity.User;
import com.paulotech.api_bank_tech.repository.TransactionRepository;
import com.paulotech.api_bank_tech.repository.UserRepository;
import com.paulotech.api_bank_tech.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private EmailService emailService;

    private static final String FILE_PATH = "C:\\Users\\Paulo Cesar\\OneDrive\\Documentos\\Java\\api-bank-tech\\src" +
            "\\main" +
            "\\java\\com\\paulotech\\api_bank_tech\\service\\impl\\bank_statement.pdf";

    public byte[] generateStatement(String accountNumber, String startDate, String endDate) throws IOException {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        // Get transactions within date range
        List<Transaction> transactionList = transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .filter(transaction -> !transaction.getCreatedAt().isBefore(start))
                .filter(transaction -> !transaction.getCreatedAt().isAfter(end))
                .toList();

        User user = userRepository.findByAccountNumber(accountNumber);
        if (user == null) {
            throw new IllegalArgumentException("User not found with account number: " + accountNumber);
        }

        String customerName = String.join(" ", user.getFirstName(), user.getLastName(), user.getOtherName());

        // Create PDF document
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Bank info table
            PdfPTable bankInfoTable = new PdfPTable(1);

            PdfPCell bankName = new PdfPCell(new Phrase("Bank tech from Paulotech"));
            bankName.setBorder(Rectangle.NO_BORDER);
            bankName.setBackgroundColor(BaseColor.BLUE);
            bankName.setPadding(20f);

            PdfPCell bankAddress = new PdfPCell(new Phrase("Rua da Paz, 123"));
            bankAddress.setBorder(Rectangle.NO_BORDER);

            bankInfoTable.addCell(bankName);
            bankInfoTable.addCell(bankAddress);
            document.add(bankInfoTable);

            // Statement info table
            PdfPTable statementTable = new PdfPTable(2);

            PdfPCell customerInfo = new PdfPCell(new Phrase("Start date: " + startDate));
            customerInfo.setBorder(Rectangle.NO_BORDER);

            PdfPCell statementInfo = new PdfPCell(new Phrase("STATEMENT OF ACCOUNT"));
            statementInfo.setBorder(Rectangle.NO_BORDER);

            PdfPCell stopDate = new PdfPCell(new Phrase("End date: " + endDate));
            stopDate.setBorder(Rectangle.NO_BORDER);

            PdfPCell name = new PdfPCell(new Phrase("Customer name: " + customerName));
            name.setBorder(Rectangle.NO_BORDER);

            PdfPCell space = new PdfPCell();
            space.setBorder(Rectangle.NO_BORDER);

            PdfPCell address = new PdfPCell(new Phrase("Customer address: " + user.getAddress()));
            address.setBorder(Rectangle.NO_BORDER);

            statementTable.addCell(customerInfo);
            statementTable.addCell(statementInfo);
            statementTable.addCell(stopDate);
            statementTable.addCell(name);
            statementTable.addCell(space);
            statementTable.addCell(address);

            document.add(statementTable);
            document.add(Chunk.NEWLINE);

            // Transactions table
            PdfPTable transactionTable = new PdfPTable(4);
            transactionTable.setWidthPercentage(100);

            // Table headers
            PdfPCell date = new PdfPCell(new Phrase("Date"));
            date.setBackgroundColor(BaseColor.BLUE);
            date.setBorder(Rectangle.NO_BORDER);

            PdfPCell transactionType = new PdfPCell(new Phrase("Transaction type"));
            transactionType.setBackgroundColor(BaseColor.BLUE);
            transactionType.setBorder(Rectangle.NO_BORDER);

            PdfPCell transactionAmount = new PdfPCell(new Phrase("Amount"));
            transactionAmount.setBackgroundColor(BaseColor.BLUE);
            transactionAmount.setBorder(Rectangle.NO_BORDER);

            PdfPCell status = new PdfPCell(new Phrase("Status"));
            status.setBackgroundColor(BaseColor.BLUE);
            status.setBorder(Rectangle.NO_BORDER);

            transactionTable.addCell(date);
            transactionTable.addCell(transactionType);
            transactionTable.addCell(transactionAmount);
            transactionTable.addCell(status);

            // Table rows
            transactionList.forEach(transaction -> {
                transactionTable.addCell(new Phrase(transaction.getCreatedAt().toString()));
                transactionTable.addCell(new Phrase(transaction.getTransactionType()));
                transactionTable.addCell(new Phrase(transaction.getAmount().toString()));
                transactionTable.addCell(new Phrase(transaction.getStatus()));
            });

            document.add(transactionTable);

            log.info("Bank statement generated successfully");

            // Write to file (optional)
            try (OutputStream fileOutputStream = new FileOutputStream(FILE_PATH)) {
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
            }

            EmailDetails emailDetails = EmailDetails.builder()
                    .recipient(user.getEmail())
                    .subject("STATEMENT OF ACCOUNT")
                    .messageBody("Kindly find attached your bank statement.")
                    .attachment(FILE_PATH)
                    .build();
            emailService.sendEmailWithAttachment(emailDetails);

            return byteArrayOutputStream.toByteArray();

        } catch (DocumentException e) {
            log.error("Error generating PDF document", e);
            throw new IOException("Error generating PDF document", e);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
            byteArrayOutputStream.close();
        }
    }
}