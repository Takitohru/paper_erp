package erp_test.bean;

import java.util.Date;

public class Purchase {
	private Integer purchaseId;
	private Integer supplierId;
	private Integer employeeId;
	private Double totalPrice;
	private String status;
	private Date purchaseDate;
	
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", supplierId=" + supplierId + ", employeeId=" + employeeId
				+ ", totalPrice=" + totalPrice + ", status=" + status + ", purchaseDate=" + purchaseDate + "]";
	}
	
	
}
