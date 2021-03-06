package net.chrone.creditpay.mapper;

import net.chrone.creditpay.model.PayPlan;
import net.chrone.creditpay.model.PayPlanDCStatisticsDTO;
import net.chrone.creditpay.model.PayPlanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PayPlanMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    long countByExample(PayPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    int deleteByExample(PayPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    int deleteByPrimaryKey(String planId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    int insert(PayPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    int insertSelective(PayPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    List<PayPlan> selectByExample(PayPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    PayPlan selectByPrimaryKey(String planId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    int updateByExampleSelective(@Param("record") PayPlan record, @Param("example") PayPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    int updateByExample(@Param("record") PayPlan record, @Param("example") PayPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    int updateByPrimaryKeySelective(PayPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pay_plan
     *
     * @mbggenerated Wed Sep 25 10:15:35 CST 2019
     */
    int updateByPrimaryKey(PayPlan record);
    Map<String,Object> getPayPlanByPageCount(PayPlan payPlan);

    List<PayPlan> getPayPlanByPage(PayPlan payPlan);

    List<Map<String,Object>> listPayPlayStatistics(PayPlan payPlan);

    Map<String,Object> countPayPlayStatistics(PayPlan payPlan);

    Map<String, Object> countPayPlayDCStatistics(PayPlanDCStatisticsDTO dcStatisticsDTO);

    List<PayPlanDCStatisticsDTO> pagePayPlayDCStatistics(PayPlanDCStatisticsDTO dcStatisticsDTO);
}