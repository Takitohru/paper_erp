package erp_test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import erp_test.bean.Client;


@Mapper
public interface ClientDao {
	/*增*/
	@Insert("insert into Client(clientName,clientAddress,clientTel,isVip) "
			+ "values(#{clientName},#{clientAddress},#{clientTel},#{isVip})")
    @Options(useGeneratedKeys = true, keyProperty = "clientId")
	public int insertClient(Client client);
	
	/*删除*/
	@Delete("delete from Client where clientId=#{clientId}")
	public int deleteClient(@Param("clientId") Integer clientId);
	
	
	/*修改*/
	@Update("update Client set "
			+ "clientName=#{clientName},clientAddress=#{clientAddress},clientTel=#{clientTel},"
			+ "isVip=#{isVip} "
			+ "where clientId=#{clientId}")
	public int updateClient(Client client);
	
	
	/*通过Id查询客户信息*/
	@Select("select * from Client where clientId=#{clientId} ")
	public Client findClientById(@Param("clientId") int clientId);
	
	
	/*查询所有*/
	@Select("select * from Client")
	public List<Client> findAllClientList();
	
	/*模糊查询*/
	//注：此处为模糊查询，参数中是已拼接好的字符串，如 clientName= "%"+str+"%"
	@Select("select * from Client where clientName like #{clientName} ")
	public List<Client> findClientByFuzzyClientName(@Param("clientName") String clientName);
}
