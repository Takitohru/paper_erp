package erp_test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import erp_test.bean.SpecialOfferDetail;


@Mapper
public interface SpecialOfferDetailDao {
	@Select("select * from SpecialOfferetail where specialOfferId=#{specialOfferId} and commodityId=#{commodityId} ")
	SpecialOfferDetail findSpecialOfferDetailById(@Param("specialOfferId") Long specialOfferId, @Param("commodityId") Long commodityId);
}
