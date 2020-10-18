package com.ninjarmm.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bill {
    private OffsetDateTime date;
    private List<LineItem> lineItems;
    private BigDecimal grandTotal;

    public Bill(){
        this.grandTotal = new BigDecimal(0);
        this.lineItems = new ArrayList<>();
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void calculateTotal(){
        lineItems.forEach(l -> grandTotal = grandTotal.add(l.lineTotal));
    }

    @Override
    public String toString() {
        return "Bill{" +
                "date=" + date +
                ", lineItems=" + lineItems +
                ", grandTotal=" + grandTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(date, bill.date) &&
                Objects.equals(lineItems, bill.lineItems) &&
                Objects.equals(grandTotal, bill.grandTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, lineItems, grandTotal);
    }
}
