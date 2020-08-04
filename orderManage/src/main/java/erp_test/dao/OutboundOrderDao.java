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

import erp_test.bean.OutboundOrder;
import erp_test.vo.OutBoundMasterVo;


@Mapper
public interface OutboundOrderDao {
	
	/*插入一条出库主表信息*/
	@Insert("insert into OutboundOrder(outboundDate,employeeId,clientId) "
			+ "values(#{outboundDate},#{employeeId},#{clientId})")
    @Options(useGeneratedKeys = true, keyProperty = "outboundId")
	public int insertOutboundOrder(OutboundOrder outboundOrder);
	
	/*删除出库信息*/
	@Delete("delete from OutboundOrder where outboundId=#{outboundId}")
	public int deleteOutboundOrder(@Param("outboundId") Integer outboundId);

	/*修改出库信息*/
	@Update("update OutboundOrder set "
			+ "outboundDate=#{outboundDate},employeeId=#{employeeId},clientId=#{clientId} "
			+ "where outboundId=#{outboundId}")
	public int updateOutboundOrder(OutboundOrder outboundOrder);
	
	/*查询出库主表*/
	@Select("select * from OutboundOrder")
	public List<OutboundOrder> findOutboundOrder();
	
	/*通过ID查询出库主表*/
	@Select("select * from OutboundOrder where outboundId=#{outboundId} ")
	@Results({@Result(property="employee",column="employeeId",one=@One(select = "erp_test.dao.EmployeeDao.findEmployeeById")),
		@Result(property="client",column="clientId",one=@One(select = "erp_test.dao.ClientDao.findClientById"))
	})
	public OutBoundMasterVo findOutboundOrderById(@Param("outboundId") Integer outboundId);
	
	/*查询所有的出库主表 按单据*/
	@Select("select * from OutboundOrder")
	@Results({@Result(property="employee",column="employeeId",one=@One(select = "erp_test.dao.EmployeeDao.findEmployeeById")),
		@Result(property="client",column="clientId",one=@One(select = "erp_test.dao.ClientDao.findClientById"))
	})
	public List<OutBoundMasterVo> findOutboundOrderByForm();
	
	
}
