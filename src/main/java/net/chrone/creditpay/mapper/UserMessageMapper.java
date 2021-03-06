package net.chrone.creditpay.mapper;

import java.util.List;
import net.chrone.creditpay.model.UserMessage;
import net.chrone.creditpay.model.UserMessageExample;
import org.apache.ibatis.annotations.Param;

public interface UserMessageMapper extends ParentMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */
	int countByExample(UserMessageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */
	int deleteByExample(UserMessageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */int deleteByPrimaryKey(@Param("messageId") String messageId,@Param("userId") String userId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */
	int insert(UserMessage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */
	int insertSelective(UserMessage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */List<UserMessage> selectByExample(UserMessageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */UserMessage selectByPrimaryKey(@Param("messageId") String messageId,@Param("userId") String userId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */int updateByExampleSelective(@Param("record") UserMessage record,@Param("example") UserMessageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */int updateByExample(@Param("record") UserMessage record,@Param("example") UserMessageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */
	int updateByPrimaryKeySelective(UserMessage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_message
	 * @mbggenerated  Fri Dec 01 15:15:53 CST 2017
	 */
	int updateByPrimaryKey(UserMessage record);
	
	int countUserMessage(UserMessage userMessage);
	
	List<UserMessage> listUserMessage(UserMessage userMessage);
}