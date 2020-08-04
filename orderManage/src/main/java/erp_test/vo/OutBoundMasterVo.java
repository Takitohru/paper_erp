package erp_test.vo;

import erp_test.bean.Client;
import erp_test.bean.Employee;
import erp_test.bean.OutboundOrder;

public class OutBoundMasterVo extends OutboundOrder
{
	private Client client;
	private Employee employee;
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
