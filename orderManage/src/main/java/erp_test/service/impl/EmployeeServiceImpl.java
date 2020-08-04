package erp_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.Employee;
import erp_test.dao.EmployeeDao;
import erp_test.service.EmployeeService;
import erp_test.vo.EmployeeVo;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public void insert(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.insertEmployee(employee);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		employeeDao.deleteEmployee(id);
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.updateEmployee(employee);
	}

	@Override
	public List<Employee> findAllEmployee() {
		// TODO Auto-generated method stub
		return employeeDao.findAllEmployeeList();
	}

	@Override
	public Employee findEmployeeById(int id) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployeeById(id);
	}

	@Override
	public List<EmployeeVo> findAllEmployeeVo() {
		// TODO Auto-generated method stub
		return employeeDao.findEmployeeVo();
	}

	@Override
	public EmployeeVo findEmployeeByIdVo(int id) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployeeVoById(id);
	}  
    
	public List<EmployeeVo> findEmployeeVoByFuzzyEmployeeName(String str) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployeeVoByFuzzyEmployeeName(str);
	}

	@Override
	public List<Employee> findEmployeeByApartmentId(int id) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployeeByApartmentId(id);
	}
}
