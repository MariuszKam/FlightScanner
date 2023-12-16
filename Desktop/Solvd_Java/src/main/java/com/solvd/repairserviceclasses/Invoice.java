package com.solvd.repairserviceclasses;

public class Invoice {
    private int invoiceNumber;
    private double totalAmount;

    public Invoice(int invoiceNumber, double totalAmount) {
        this.invoiceNumber = invoiceNumber;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}