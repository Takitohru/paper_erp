package erp_test.bean;

public class Commodity {
	private Integer commodityId;	//商品ID
	private String commodityName;	//商品名称
	private String brand;			//商品品牌
	private String paperClass;		//商品纸种
	private String paperSize;		//商品规格
	private Integer paperWeight;	//商品重量
	private Double price;			//商品价格
	private Double vipAccount;		//vip折扣	
	private String rank;            //少了一个商品级别rank
	private Integer safeInventory;	//安全库存
	private Integer inventoryNumber;//当前库存数
	
	
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPaperClass() {
		return paperClass;
	}
	public void setPaperClass(String paperClass) {
		this.paperClass = paperClass;
	}
	public String getPaperSize() {
		return paperSize;
	}
	public void setPaperSize(String paperSize) {
		this.paperSize = paperSize;
	}
	public Integer getPaperWeight() {
		return paperWeight;
	}
	public void setPaperWeight(Integer paperWeight) {
		this.paperWeight = paperWeight;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getVipAccount() {
		return vipAccount;
	}
	public void setVipAccount(Double vipAccount) {
		this.vipAccount = vipAccount;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
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
		return "Commodity [commodityId=" + commodityId + ", commodityName=" + commodityName + ", brand=" + brand
				+ ", paperClass=" + paperClass + ", paperSize=" + paperSize + ", paperWeight=" + paperWeight
				+ ", price=" + price + ", vipAccount=" + vipAccount + ", rank=" + rank + ", safeInventory="
				+ safeInventory + ", inventoryNumber=" + inventoryNumber + "]";
	}

	
}
