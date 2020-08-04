package erp_test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import erp_test.bean.OrderDetail;
import erp_test.vo.OrderDetailInformationVo;
import erp_test.vo.OutBoundDetailVo;

@Mapper
public interface OrderDetailDao {
	
	
	/*插入一条订单明细*/
	@Insert("insert into OrderDetail(orderId,commodityId,commodityNum,commodityFreight,outBoundNumber) "
			+ "values(#{orderId},#{commodityId},#{commodityNum},#{commodityFreight},#{outBoundNumber})")
	public int insertOrderDetail(OrderDetail orderDetail);
	
	
	/*删除一条订单明细*/
	@Delete("delete from OrderDetail where orderId=#{orderId} and commodityId=#{commodityId}")
	public int deleteOrderDetail(@Param("orderId") Integer orderId, @Param("commodityId") Integer commodityId);
	
	
	
	/*修改一条订单明细*/
	@Update("update OrderDetail set "
			+ "commodityNum=#{commodityNum},commodityFreight=#{commodityFreight} "
			+ "where orderId=#{orderId} and commodityId=#{commodityId} ")
	public int updateOrderDetail(OrderDetail orderDetail);
	
	
	
	/*查询所有的订单明细 */
	@Select("select * from OrderDetail ")
	public List<OrderDetail> findAllOrderDetailList();
	
	
	/*通过订单Id与商品Id查询订单明细 */
	
	
	@Select("select * from OrderDetail where orderId=#{orderId}")
	public OrderDetail findOrderDetailById(@Param("orderId") Integer orderId);
	
	
	/*将订单明细表中的数据一条一条的取出来跟主表关联*/
	@Select("select * from OrderDetail")
	@Results({@Result(property="commodity",column="commodityId",one=@One(select = "erp_test.dao.CommodityDao.findCommodityById")),
		@Result(property="orderMasterInformationVo",column="orderId",one=@One(select = "erp_test.dao.OrderMasterDao.findOrderMasterInformationsById"))
	})
	public List<OrderDetailInformationVo> findOrderDetail();
	
	
	/*根据订单id返回所有数据*/
	@Select("select * from OrderDetail where orderId=#{orderId}")
	@Results({@Result(property="commodity",column="commodityId",one=@One(select = "erp_test.dao.CommodityDao.findCommodityById")),
		@Result(property="orderMasterInformationVo",column="orderId",one=@One(select = "erp_test.dao.OrderMasterDao.findOrderMasterInformationsById"))
	})
	public List<OrderDetailInformationVo> findOrderDetailListById(@Param("orderId") Integer orderId);
	
	/*修改某个订单商品的发货量*/
	@Update("update OrderDetail set "
			+ "commodityNum=#{commodityNum},commodityFreight=#{commodityFreight},outBoundNumber=#{outBoundNumber} "
			+ "where orderId=#{orderId} and commodityId=#{commodityId} ")
	public int updateOrderDetailForOutBoundNum(OrderDetail orderDetail);
	
	/*通过订单Id查询订单明细 */
	@Select("select * from OrderDetail where orderId=#{orderId}")
	public List<OrderDetail> findOrderDetailByOrderId(@Param("orderId") Integer orderId);
}
