package erp_test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import erp_test.bean.Account;


@Mapper
public interface AccountDao {
	/*用户验证合法性*/
	@Select("select * from Account where id=#{id} and pwd=#{pwd}")
	public Account findAccount(@Param("id") String id, @Param("pwd") String pwd);
	
	@Select("select * from Account")
	public Account getAccount();
}