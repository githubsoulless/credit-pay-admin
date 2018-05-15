package net.chrone.creditpay.mapper;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.CouponDetail;
import net.chrone.creditpay.model.CouponDetailExample;
import org.apache.ibatis.annotations.Param;

public interface CouponDetailMapper extends ParentMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */
	int countByExample(CouponDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */
	int deleteByExample(CouponDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */
	int insert(CouponDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */
	int insertSelective(CouponDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */List<CouponDetail> selectByExample(CouponDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */
	CouponDetail selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */int updateByExampleSelective(@Param("record") CouponDetail record,@Param("example") CouponDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */int updateByExample(@Param("record") CouponDetail record,@Param("example") CouponDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */
	int updateByPrimaryKeySelective(CouponDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_coupon_detail
	 * @mbggenerated  Wed Nov 29 15:21:57 CST 2017
	 */
	int updateByPrimaryKey(CouponDetail record);

	Map<String, Object> countCouponDetail(CouponDetail couponDetail);
	
	List<CouponDetail> listCouponDetail(CouponDetail couponDetail);
	
	Map<String, Object> countCouponDetailByStatus();
}