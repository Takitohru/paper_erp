package erp_test.bean;

public class InventoryDetail {
	private Integer commodityId;
	private Integer safeInventory;
	private Integer inventoryNumber;
	
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getSafeInventory() {
		return safeInventory;
	}
	public void setSafeInventory(Integer safeInventory) {
		this.safeInventory = safeInventory;
	}
	public Integer getInventoryNumber() {
		return inventoryNumber;
	}
	public void setInventoryNumber(Integer inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}
	@Override
	public String toString() {
		return "InventoryDetail [commodityId=" + commodityId + ", safeInventory=" + safeInventory + ", inventoryNumber="
				+ inventoryNumber + "]";
	}
	
	
}
