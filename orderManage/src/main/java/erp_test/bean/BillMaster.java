package erp_test.bean;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

@Alias("BillMaster")
public class BillMaster {
    private String billId;
    private int type;
    private int customerid;
    private String customerName;
    private double total=0;
    private double received=0;
    private double unpay=0;
    private double thistime=0;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date create;
    private String name;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BillMaster{" +
                "billId='" + billId + '\'' +
                ", type=" + type +
                ", customerid=" + customerid +
                ", customerName='" + customerName + '\'' +
                ", total=" + total +
                ", received=" + received +
                ", unpay=" + unpay +
                ", thistime=" + thistime +
                ", create=" + create +
                ", name='" + name + '\'' +
                '}';
    }
}
