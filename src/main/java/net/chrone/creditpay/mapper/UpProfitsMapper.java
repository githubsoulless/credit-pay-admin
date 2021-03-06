package net.chrone.creditpay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.chrone.creditpay.model.UpProfits;
import net.chrone.creditpay.model.UpProfitsExample;

public interface UpProfitsMapper extends ParentMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int countByExample(UpProfitsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int deleteByExample(UpProfitsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int deleteByPrimaryKey(String profitsId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int insert(UpProfits record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int insertSelective(UpProfits record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */List<UpProfits> selectByExample(UpProfitsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	UpProfits selectByPrimaryKey(String profitsId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */int updateByExampleSelective(@Param("record") UpProfits record,@Param("example") UpProfitsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */int updateByExample(@Param("record") UpProfits record,@Param("example") UpProfitsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int updateByPrimaryKeySelective(UpProfits record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_up_profits
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int updateByPrimaryKey(UpProfits record);
	
	Map<String,Object> countUpProfits(UpProfits upProfits);

	List<UpProfits> listUpProfitsPage(UpProfits upProfits);
	
	UpProfits getUpProfitsByProfitsId(String profitsId);
	

}