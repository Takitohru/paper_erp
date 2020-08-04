package erp_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.Supplier;
import erp_test.bean.Supplier;
import erp_test.dao.SupplierDao;
import erp_test.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierDao supplierDao;
	
	@Override
	public void insert(Supplier supplier) {
		// TODO Auto-generated method stub
		supplierDao.insertSupplier(supplier);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		supplierDao.deleteCommodity(id);
	}

	@Override
	public void update(Supplier supplier) {
		// TODO Auto-generated method stub
		supplierDao.updateSupplier(supplier);
	}

	@Override
	public List<Supplier> findAllSupplier() {
		// TODO Auto-generated method stub
		return supplierDao.findAllSupplierList();
	}

	@Override
	public Supplier findSupplierById(int id) {
		// TODO Auto-generated method stub
		return supplierDao.findSupplierById(id);
	}
	
	public List<Supplier> findSupplierByFuzzySupplierName(String str) {
		// TODO Auto-generated method stub
		return supplierDao.findSupplierByFuzzySupplierName(str);
	}
}
