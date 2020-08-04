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

import erp_test.bean.OrderMaster;
import erp_test.vo.OrderDetailInformationVo;
import erp_test.vo.OrderMasterInformationVo;

@Mapper
public interface OrderMasterDao {
	//useGeneratedKeys 参数只针对 insert 语句生效，默认为 false。
	//当设置为 true 时，表示如果插入的表以自增列为主键，则允许 JDBC 支持自动生成主键，并可将自动生成的主键返回
	//keyProperty=”对应的主键的对象”。
	
	/*插入一条订单主表信息*/
	@Insert("insert into OrderMaster(clientId,employeeId,orderTotal,transTotal,orderDate,orderState,outboundNum,commoditySum) "
			+ "values(#{clientId},#{employeeId},#{orderTotal},#{transTotal},#{orderDate},#{orderState},#{outboundNum},#{commoditySum})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
	public int insertOrderMaster(OrderMaster orderMaster);

	
	/*删除一条订单主表信息*/
	@Delete("delete from OrderMaster where orderId=#{orderId}")
	public int deleteOrderMaster(@Param("orderId") Integer orderId);
	
	
	/*修改一条订单主表信息*/
	@Update("update OrderMaster set "
			+ "clientId=#{clientId},employeeId=#{employeeId},orderTotal=#{orderTotal},transTotal=#{transTotal},"
			+ "orderDate=#{orderDate},orderState=#{orderState},outboundNum=#{outboundNum},commoditySum=#{commoditySum} "
			+ "where orderId=#{orderId}")
	public int updateOrderMaster(OrderMaster orederMaster);
	
	
	/*通过订单id查询订单主表*/
	@Select("select * from OrderMaster where orderId=#{orderId} ")
	public OrderMaster findOrderMasterById(@Param("orderId") Integer orderId);
	
	
	/*查询所有的订单主表*/
	@Select("select * from OrderMaster")
	public List<OrderMaster> findAllOrderMasterList();
	
	//@Select("select * from OrderMaster, OrderDetail, Commodity, Client, where OrderMaster.orderId=OrderDetail.orderId "
		//	+ "and Commodity.commodityId=OrderDetail.commodityId and Client.clientId=OrderMaster.clientId")
	//public List<OrderVoTest> find();
	
	
	/*根据订单状态查询--已付款  未付款*/
	@Select("select * from OrderMaster where orderState=#{orderState}")
	public List<OrderMaster> findOrderMasterByOrderState(@Param("orderState") String orderState);
	
	
	/*查看历史销售订单,返回所有需要的信息--返回按单据查看的Vo层对象*/
	@Select("select * from OrderMaster")
	@Results({@Result(property="employee",column="employeeId",one=@One(select = "erp_test.dao.EmployeeDao.findEmployeeById")),
				@Result(property="client",column="clientId",one=@One(select = "erp_test.dao.ClientDao.findClientById"))
			})
	public List<OrderMasterInformationVo> findOrderMasterInformations();
	
	
	/*根据订单id查询订单主表并返回用户与部员信息*/
	@Select("select * from OrderMaster where orderId=#{orderId}")
	@Results({@Result(property="employee",column="employeeId",one=@One(select = "erp_test.dao.EmployeeDao.findEmployeeById")),
				@Result(property="client",column="clientId",one=@One(select = "erp_test.dao.ClientDao.findClientById"))
			})
	public OrderMasterInformationVo findOrderMasterInformationsById(@Param("orderId") Integer orderId);
	
	
	
	
}
