package erp_test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import erp_test.bean.Employee;
import erp_test.vo.EmployeeVo;

@Mapper
public interface EmployeeDao {
	
	/*插入职员信息*/
	@Insert("insert into Employee(employeeName,sex,employeeTel,apartmentId) "
			+ "values(#{employeeName},#{sex},#{employeeTel},#{apartmentId})")
    @Options(useGeneratedKeys = true, keyProperty = "employeeId")
	public int insertEmployee(Employee employee);

	
	/*删除职员信息*/
	@Delete("delete from Employee where employeeId=#{employeeId}")
	public int deleteEmployee(@Param("employeeId") Integer employeeId);
	
	/*修改职员信息*/
	@Update("update Employee set "
			+ "employeeName=#{employeeName},sex=#{sex},employeeTel=#{employeeTel},apartmentId=#{apartmentId} "
			+ "where employeeId=#{employeeId}")
	public int updateEmployee(Employee employee);
	
	/*根据职员id查询职员的资料*/
	@Select("select * from Employee where employeeId=#{employeeId}")
	public Employee findEmployeeById(@Param("employeeId") Integer employeeId);
	
	/*查询所有*/
	@Select("select * from Employee")
	public List<Employee> findAllEmployeeList();
	
	/*通过部门ID查询员工信息*/
	@Select("select * from Employee where apartmentId=#{apartmentId}")
	public List<Employee> findEmployeeByApartmentId(@Param("apartmentId") Integer apartmentId);
	
	
	/*查看职员信息,返回所有需要的信息--返回Vo层对象*/
	@Select("select * from Employee")
	@Results({@Result(property="apartment",column="apartmentId",one=@One(select = "erp_test.dao.ApartmentDao.findApartmentById"))
		})
	public List<EmployeeVo> findEmployeeVo();
	
	/*根据订单id查询订单主表并返回用户与部员信息*/
	@Select("select * from Employee where employeeId=#{employeeId}")
	@Results({@Result(property="apartment",column="apartmentId",one=@One(select = "erp_test.dao.ApartmentDao.findApartmentById"))
		})
	public EmployeeVo findEmployeeVoById(@Param("employeeId") Integer employeeId);

	/*模糊查询*/
	@Select("select * from Employee where employeeName like #{employeeName} ")
	@Results({@Result(property="apartment",column="apartmentId",one=@One(select = "erp_test.dao.ApartmentDao.findApartmentById"))
	})
	public List<EmployeeVo> findEmployeeVoByFuzzyEmployeeName(@Param("employeeName") String employeeName);
	
}
