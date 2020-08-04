package erp_test.service;

import java.util.List;

import erp_test.bean.Apartment;
import erp_test.bean.Apartment;

public interface ApartmentService 
{
		/**
		 * 处理与部门有关的逻辑业务层
		 * */
	
	public void insert(Apartment apartment);
	public void delete(int id);
	public void update(Apartment apartment);
	public List<Apartment> findAllApartment();
	public Apartment findApartmentById(int id);
	
	public List<Apartment> findApartmentByFuzzyApartmentName(String str);
}
