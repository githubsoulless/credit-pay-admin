package net.chrone.creditpay.mapper;

import java.util.List;
import net.chrone.creditpay.model.Agent;
import net.chrone.creditpay.model.AgentExample;
import net.chrone.creditpay.model.AgentProfitVO;

import org.apache.ibatis.annotations.Param;

public interface AgentMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    long countByExample(AgentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByExample(AgentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByPrimaryKey(String agentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insert(Agent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insertSelective(Agent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    List<Agent> selectByExample(AgentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    Agent selectByPrimaryKey(String agentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExampleSelective(@Param("record") Agent record, @Param("example") AgentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExample(@Param("record") Agent record, @Param("example") AgentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKeySelective(Agent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKey(Agent record);

	int getAgentByPageCount(Agent agent);

	List<Agent> getAgentByPage(Agent agent);
	
	String getSUBAgentIdByAgentId(String agentId);

	int getAgentUserStatisticsCount(Agent agent);

	List<Agent> getAgentUserStatisticsByPage(Agent agent);
}