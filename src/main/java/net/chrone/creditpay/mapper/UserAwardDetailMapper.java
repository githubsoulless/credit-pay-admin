package net.chrone.creditpay.mapper;

import java.util.List;
import net.chrone.creditpay.model.UserAwardDetail;
import net.chrone.creditpay.model.UserAwardDetailExample;
import org.apache.ibatis.annotations.Param;

public interface UserAwardDetailMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    long countByExample(UserAwardDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    int deleteByExample(UserAwardDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    int insert(UserAwardDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    int insertSelective(UserAwardDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    List<UserAwardDetail> selectByExample(UserAwardDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    UserAwardDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAwardDetail record, @Param("example") UserAwardDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    int updateByExample(@Param("record") UserAwardDetail record, @Param("example") UserAwardDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    int updateByPrimaryKeySelective(UserAwardDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_award_detail
     *
     * @mbg.generated Thu Mar 28 15:12:21 CST 2019
     */
    int updateByPrimaryKey(UserAwardDetail record);
}