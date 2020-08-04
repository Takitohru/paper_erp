package erp_test.service;

import java.util.List;

import erp_test.bean.Company;

public interface CompanyService
{
	/**
	 * 处理与公司信息有关的逻辑业务层  --调用Dao层方法实现业务
	 * */
	public void update(Company company);
	public Company findCompanyById(String id);
	public List<Company> findAllCompany();
	public void insert(Company company);
	
}
