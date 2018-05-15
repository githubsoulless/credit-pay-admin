package net.chrone.creditpay.mapper;

import java.util.List;
import net.chrone.creditpay.model.OperationLog;
import net.chrone.creditpay.model.OperationLogExample;
import org.apache.ibatis.annotations.Param;

public interface OperationLogMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    long countByExample(OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByExample(OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insert(OperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insertSelective(OperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    List<OperationLog> selectByExample(OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    OperationLog selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExampleSelective(@Param("record") OperationLog record, @Param("example") OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExample(@Param("record") OperationLog record, @Param("example") OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKeySelective(OperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_operation_log
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKey(OperationLog record);
    
    List<OperationLog> listOperationLog(OperationLog operationLog);
	
	int countOperationLog(OperationLog operationLog);
}