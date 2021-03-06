package net.chrone.creditpay.mapper;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.MgrUser;
import net.chrone.creditpay.model.MgrUserExample;
import org.apache.ibatis.annotations.Param;

public interface MgrUserMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    long countByExample(MgrUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByExample(MgrUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByPrimaryKey(String loginId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insert(MgrUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insertSelective(MgrUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    List<MgrUser> selectByExample(MgrUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    MgrUser selectByPrimaryKey(String loginId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExampleSelective(@Param("record") MgrUser record, @Param("example") MgrUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExample(@Param("record") MgrUser record, @Param("example") MgrUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKeySelective(MgrUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mgr_user
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKey(MgrUser record);

	int getMgrUserByPageCount(Map<String, Object> map);

	List<MgrUser> getMgrUserByPage(Map<String, Object> map);
}