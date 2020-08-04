package erp_test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import erp_test.bean.Apartment;
import erp_test.bean.Apartment;


@Mapper
public interface ApartmentDao {
	
	/*插入*/
	@Insert("insert into Apartment(apartmentName) "
			+ "values(#{apartmentName})")
    @Options(useGeneratedKeys = true, keyProperty = "apartmentId")
	public int insertApartment(Apartment apartment);

	
	/*删除*/
	@Delete("delete from Apartment where apartmentId=#{apartmentId}")
	public int deleteApartment(@Param("apartmentId") Integer apartmentId);
	
	
	/*修改*/
	@Update("update Apartment set "
			+ "apartmentName=#{apartmentName} "
			+ "where apartmentId=#{apartmentId}")
	public int updateApartment(Apartment apartment);
	
	
	/*通过id查询*/
	@Select("select * from Apartment where apartmentId=#{apartmentId} ")
	public Apartment findApartmentById(@Param("apartmentId") Integer apartmentId);
	
	
	/*查询*/
	@Select("select * from Apartment")
	public List<Apartment> findAllApartmentList();
	
	/*模糊查询*/
	//注：此处为模糊查询，参数中是已拼接好的字符串，如 apartmentName= "%"+str+"%"
	@Select("select * from Apartment where apartmentName like #{apartmentName} ")
	public List<Apartment> findApartmentByFuzzyApartmentName(@Param("apartmentName") String apartmentName);

}
