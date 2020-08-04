package erp_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.Company;
import erp_test.dao.CompanyDao;
import erp_test.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDao companyDao;
	
	@Override
	public void update(Company company) {
		// TODO Auto-generated method stub
		companyDao.updateCompany(company);
	}

	@Override
	public Company findCompanyById(String id) {
		// TODO Auto-generated method stub
		return companyDao.findCompanyById(id);
	}
	
	@Override
	public List<Company> findAllCompany() {
		// TODO Auto-generated method stub
		return companyDao.findAllCompanyList();
	}

	@Override
	public void insert(Company company) {
		// TODO Auto-generated method stub
		companyDao.insertCompany(company);
	}



}
