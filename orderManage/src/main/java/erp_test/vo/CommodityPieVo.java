package erp_test.vo;

public class CommodityPieVo 
{
	private String CommodityName;
	private Integer  value;
	public CommodityPieVo() {
	}
	public String getCommodityName() {
		return CommodityName;
	}
	public void setCommodityName(String commodityName) {
		CommodityName = commodityName;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	 public CommodityPieVo(String commodityName, Integer value) {
	        this.CommodityName = commodityName;
	        this.value = value;
	    }
	@Override
	public String toString() {
		return "CommodityPieVo [CommodityName=" + CommodityName + ", value=" + value + "]";
	}

}
