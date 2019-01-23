package net.chrone.creditpay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.chrone.creditpay.model.AgentProfitVO;
import net.chrone.creditpay.model.PayProfitsDetail;
import net.chrone.creditpay.model.PayProfitsDetailExample;

public interface PayProfitsDetailMapper extends ParentMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int countByExample(PayProfitsDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int deleteByExample(PayProfitsDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int insert(PayProfitsDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int insertSelective(PayProfitsDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */List<PayProfitsDetail> selectByExample(PayProfitsDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	PayProfitsDetail selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */int updateByExampleSelective(@Param("record") PayProfitsDetail record,@Param("example") PayProfitsDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */int updateByExample(@Param("record") PayProfitsDetail record,@Param("example") PayProfitsDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int updateByPrimaryKeySelective(PayProfitsDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pay_profits_detail
	 * @mbggenerated  Tue Nov 21 18:17:01 CST 2017
	 */
	int updateByPrimaryKey(PayProfitsDetail record);

    
    List<PayProfitsDetail> listPayProfitsDetail(String profitsId);
    
    List<AgentProfitVO> listAgentPayProfits(AgentProfitVO agentProfitVO);
    
    Map<String, Object> countAgentPayProfits(AgentProfitVO agentProfitVO);
    
    List<AgentProfitVO> listAgentUpProfits(AgentProfitVO agentProfitVO);
    
    Map<String, Object> countAgentUpProfits(AgentProfitVO agentProfitVO);
    
    List<AgentProfitVO> listAgentProfits(AgentProfitVO agentProfitVO);
    
    Map<String, Object> countAgentProfits(AgentProfitVO agentProfitVO);
    
    List<AgentProfitVO> listAgentFastProfits(AgentProfitVO agentProfitVO);
    
    Map<String, Object> countAgentFastProfits(AgentProfitVO agentProfitVO);

	int getAgentProfitsStatistics(AgentProfitVO agentProfitVO);

	List<AgentProfitVO> getAgentProfitsStatisticsByPage(AgentProfitVO agentProfitVO);

	int getAgentProfitsStatisticsCount(AgentProfitVO agentProfitVO);
    
}