package net.chrone.creditpay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.chrone.creditpay.model.AccountDetail;
import net.chrone.creditpay.model.AccountDetailExample;

public interface AccountDetailMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    long countByExample(AccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByExample(AccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByPrimaryKey(String dtlId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insert(AccountDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insertSelective(AccountDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    List<AccountDetail> selectByExample(AccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    AccountDetail selectByPrimaryKey(String dtlId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExampleSelective(@Param("record") AccountDetail record, @Param("example") AccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExample(@Param("record") AccountDetail record, @Param("example") AccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKeySelective(AccountDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_account_detail
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKey(AccountDetail record);
    
    Map<String,Object> countAccountDetail(AccountDetail accountDetail);

	List<AccountDetail> listAccountDetailPage(AccountDetail accountDetail);
	
}