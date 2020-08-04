package erp_test.bean;

public class Client {
	private Integer clientId;		//客户ID
	private String  clientName;		//客户名称
	private String  clientAddress;	//客户性别
	private String  clientTel;		//客户电话
	private String  isVip;			//是否为vip
	//这里有个问题，在mysql中没有boolean，一般用tinyint代替，问题在于在java中需不需要将0和1转换为true和false
	
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getClientTel() {
		return clientTel;
	}
	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}
	public String getIsVip() {
		return isVip;
	}
	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientAddress=" + clientAddress
				+ ", clientTel=" + clientTel + ", isVip=" + isVip + "]";
	}
	

}
