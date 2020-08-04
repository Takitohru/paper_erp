package erp_test.vo;

import java.sql.Date;

public class InCompleteOrder {
    private int orderId;
    private int clientId;
    private String clientName;
    private double orderTotal;
    private double transTotal;
    private Date orderDate;
    private double payable;
    private double received;
    private double unpay;
    private double thistime;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    @Override
    public String toString() {
        return "InCompleteOrder{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", orderTotal=" + orderTotal +
                ", transTotal=" + transTotal +
                ", orderDate=" + orderDate +
                ", payable=" + payable +
                ", received=" + received +
                ", unpay=" + unpay +
                ", thistime=" + thistime +
                '}';
    }
}
