package erp_test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import erp_test.bean.Company;


@Mapper
public interface CompanyDao {
	/*插入*/
	@Insert("insert into Company(id,companyName,companyAddress,companyTel) "
			+ "values(#{id},#{companyName},#{companyAddress},#{companyTel})")
	public int insertCompany(Company company);

	
	/*删除*/
	@Delete("delete from Company where id=#{id}")
	public int deleteCompany(@Param("id") String id);
	
	
	/*修改*/
	@Update("update Company set "
			+ "companyName=#{companyName},companyAddress=#{companyAddress},companyTel=#{companyTel} "
			+ "where id=#{id}")
	public int updateCompany(Company company);
	
	
	/*通过id查询公司信息*/
	@Select("select * from Company where id=#{id} ")
	public Company findCompanyById(@Param("id") String id);
	
	
	/*查询公司所有信息*/
	@Select("select * from Company")
	public List<Company> findAllCompanyList();
}
