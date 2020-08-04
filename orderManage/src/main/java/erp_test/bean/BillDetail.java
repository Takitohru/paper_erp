package erp_test.bean;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

@Alias("BillBetail")
public class BillDetail {
    private String billId;
    private int orderId;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date orderDate;
    private double orderTotal;
    private double transTotal;
    private double payable;
    private double received;
    private double unpay;
    private double thistime;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public double getTransTotal() {
        return transTotal;
    }

    public void setTransTotal(double transTotal) {
        this.transTotal = transTotal;
    }

    public double getPayable() {
        return payable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }

    public double getReceived() {
        return received;
    }

    public void setReceived(double received) {
        this.received = received;
    }

    public double getUnpay() {
        return unpay;
    }

    public void setUnpay(double unpay) {
        this.unpay = unpay;
    }

    public double getThistime() {
        return thistime;
    }

    public void setThistime(double thistime) {
        this.thistime = thistime;
    }
}
