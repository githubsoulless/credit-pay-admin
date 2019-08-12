package net.chrone.creditpay.mapper;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.AppUser;
import net.chrone.creditpay.model.Order;
import net.chrone.creditpay.model.OrderExample;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper extends ParentMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */
	long countByExample(OrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */
	int deleteByExample(OrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */
	int deleteByPrimaryKey(String orderNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */
	int insert(Order record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */
	int insertSelective(Order record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */List<Order> selectByExample(OrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */
	Order selectByPrimaryKey(String orderNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */int updateByExampleSelective(@Param("record") Order record,@Param("example") OrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */int updateByExample(@Param("record") Order record,@Param("example") OrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */
	int updateByPrimaryKeySelective(Order record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_order
	 * @mbg.generated  Mon Aug 12 14:05:47 CST 2019
	 */
	int updateByPrimaryKey(Order record);

	Map<String, Object> getOrderByPageCount(Order order);

	List<Order> getOrderByPage(Order order);

	int getUserOrderStaticsPageCount(AppUser appuser);

	List<AppUser> getUserOrderStaticsByPage(AppUser appuser);
}