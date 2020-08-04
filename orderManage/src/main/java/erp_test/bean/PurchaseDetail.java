package erp_test.bean;

public class PurchaseDetail {
	private Integer purchaseId;
	private Integer commodityId;
	private Double purchasePrice;
	private Integer quantity;
	
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PurchaseDetail [purchaseId=" + purchaseId + ", commodityId=" + commodityId + ", purchasePrice="
				+ purchasePrice + ", quantity=" + quantity + "]";
	}
	
}
