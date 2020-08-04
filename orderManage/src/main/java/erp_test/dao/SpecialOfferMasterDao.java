package erp_test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import erp_test.bean.SpecialOfferMaster;


@Mapper
public interface SpecialOfferMasterDao {
	@Select("select * from SpecialOfferMaster where specialOfferId=#{specialOfferId} ")
	SpecialOfferMaster findSpecialOfferMasterById(@Param("specialOfferId") Long specialOfferId);
}
