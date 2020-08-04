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

import erp_test.bean.OutboundDetail;
import erp_test.vo.OutBoundDetailVo;


@Mapper
public interface OutboundDetailDao {
	
	/*插入一条出库明细表信息*/
	@Insert("insert into OutboundDetail (outboundId,commodityId,outboundNum) "
			+ "values(#{outboundId},#{commodityId},#{outboundNum})")
	public int insertOutboundDetail(OutboundDetail outboundDetail);
	
	/*删除特价信息*/
	@Delete("delete from OutboundDetail where outboundId=#{outboundId} and commodityId=#{commodityId}")
	public int deleteOutboundDetail(@Param("outboundId") Integer outboundId, @Param("commodityId") Integer commodityId);

	/*修改特价信息*/
	@Update("update OutboundDetail set "
			+ "outboundNum=#{outboundNum} "
			+ "where outboundId=#{outboundId} and commodityId=#{commodityId} ")
	public int updateOutboundDetail(OutboundDetail outboundDetail);
	
	/*查询特价主表*/
	@Select("select * from OutboundDetail ")
	public List<OutboundDetail> findOutboundDetail();
	
	/*通过出库ID和商品ID查询*/
	@Select("select * from OutboundDetail where outboundId=#{outboundId} and commodityId=#{commodityId} ")
	public OutboundDetail findOutboundDetailById(@Param("outboundId") Integer outboundId, @Param("commodityId") Integer commodityId);
	
	/*查看所有出库明细信息*/
	@Select("select * from outboundDetail")
	@Results({@Result(property="commodity",column="commodityId",one=@One(select = "erp_test.dao.CommodityDao.findCommodityById")),
		@Result(property="outBoundMasterVo",column="outboundId",one=@One(select = "erp_test.dao.OutboundOrderDao.findOutboundOrderById"))
	})
	public List<OutBoundDetailVo> findOutBoundDetailVo();
	
	@Select("select * from outboundDetail where outboundId=#{outboundId}")
	@Results({@Result(property="commodity",column="commodityId",one=@One(select = "erp_test.dao.CommodityDao.findCommodityById")),
		@Result(property="outBoundMasterVo",column="outboundId",one=@One(select = "erp_test.dao.OutboundOrderDao.findOutboundOrderById"))
	})
	public List<OutBoundDetailVo> findOutBoundDetailByOutboundId(@Param("outboundId") Integer outboundId);
}
