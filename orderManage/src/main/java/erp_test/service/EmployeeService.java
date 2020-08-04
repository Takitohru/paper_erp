package erp_test.service;

import java.util.List;


import erp_test.bean.Employee;
import erp_test.vo.EmployeeVo;

public interface EmployeeService 
{
	/**
	 * 处理与职员信息有关的逻辑业务层  --调用Dao层方法实现业务
	 * */
	public void insert(Employee employee);
	public void delete(int id);
	public void update(Employee employee);
	public List<Employee> findAllEmployee();
	public Employee findEmployeeById(int id);
	
	public List<EmployeeVo> findAllEmployeeVo();
	public EmployeeVo findEmployeeByIdVo(int id);
	public List<Employee> findEmployeeByApartmentId(int id);
	
	public List<EmployeeVo> findEmployeeVoByFuzzyEmployeeName(String str);
}
