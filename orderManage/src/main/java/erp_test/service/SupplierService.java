package erp_test.service;

import java.util.List;

import erp_test.bean.Supplier;
import erp_test.bean.Supplier;

public interface SupplierService
{
	/**
	 * 处理与供应商有关的逻辑业务层  --调用Dao层方法实现业务
	 * */
	
	public void insert(Supplier supplier);
	public void delete(int id);
	public void update(Supplier supplier);
	public List<Supplier> findAllSupplier();
	public Supplier findSupplierById(int id);
	
	public List<Supplier> findSupplierByFuzzySupplierName(String str);
}
