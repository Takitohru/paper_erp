package erp_test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import erp_test.bean.Supplier;
import erp_test.bean.Supplier;

@Mapper
public interface SupplierDao {
	/*增*/
	@Insert("insert into Supplier (supplierName,supplierAddress,supplierTel) "
			+ "values("
			+ "#{supplierName},#{supplierAddress},#{supplierTel})")
    @Options(useGeneratedKeys = true, keyProperty = "supplierId")
	public int insertSupplier(Supplier supplier);
	
	/*删*/
	@Delete("delete from Supplier where supplierId=#{supplierId}")
	public int deleteCommodity(@Param("supplierId") Integer supplierId);
	
	/*修改*/
	@Update("update Supplier set "
			+ "supplierName=#{supplierName},supplierAddress=#{supplierAddress},supplierTel=#{supplierTel} "
			+ "where supplierId=#{supplierId}")
	public int updateSupplier(Supplier supplier);
	
	/*查byID*/
	@Select("select * from Supplier where supplierId=#{supplierId} ")
	public Supplier findSupplierById(@Param("supplierId") Integer supplierId);
	
	/*查询所有*/
	@Select("select * from Supplier")
	public List<Supplier> findAllSupplierList();
	
	/*模糊查询*/
	//注：此处为模糊查询，参数中是已拼接好的字符串，如 supplierName= "%"+str+"%"
	@Select("select * from Supplier where supplierName like #{supplierName} ")
	public List<Supplier> findSupplierByFuzzySupplierName(@Param("supplierName") String supplierName);
}
