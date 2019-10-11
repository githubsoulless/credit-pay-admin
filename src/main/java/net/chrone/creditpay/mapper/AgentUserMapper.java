package net.chrone.creditpay.mapper;

import java.util.List;
import net.chrone.creditpay.model.AgentUser;
import net.chrone.creditpay.model.AgentUserExample;
import org.apache.ibatis.annotations.Param;

public interface AgentUserMapper extends ParentMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */
	int countByExample(AgentUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */
	int deleteByExample(AgentUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */int deleteByPrimaryKey(@Param("agentId") String agentId,@Param("loginId") String loginId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */
	int insert(AgentUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */
	int insertSelective(AgentUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */List<AgentUser> selectByExample(AgentUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */AgentUser selectByPrimaryKey(@Param("agentId") String agentId,@Param("loginId") String loginId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */int updateByExampleSelective(@Param("record") AgentUser record,@Param("example") AgentUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */int updateByExample(@Param("record") AgentUser record,@Param("example") AgentUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */
	int updateByPrimaryKeySelective(AgentUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agent_user
	 * @mbggenerated  Tue Dec 05 10:05:02 CST 2017
	 */
	int updateByPrimaryKey(AgentUser record);

    int deleteByAgentId(String agentId);
}