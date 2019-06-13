package net.chrone.creditpay.mapper;

import java.util.List;
import java.util.Map;

import net.chrone.creditpay.model.DayTrans;
import net.chrone.creditpay.model.DayTransExample;
import org.apache.ibatis.annotations.Param;

public interface DayTransMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    long countByExample(DayTransExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    int deleteByExample(DayTransExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    int deleteByPrimaryKey(@Param("transDt") String transDt, @Param("userId") String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    int insert(DayTrans record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    int insertSelective(DayTrans record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    List<DayTrans> selectByExample(DayTransExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    DayTrans selectByPrimaryKey(@Param("transDt") String transDt, @Param("userId") String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    int updateByExampleSelective(@Param("record") DayTrans record, @Param("example") DayTransExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    int updateByExample(@Param("record") DayTrans record, @Param("example") DayTransExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    int updateByPrimaryKeySelective(DayTrans record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_day_trans
     *
     * @mbg.generated Tue Jun 11 14:32:15 CST 2019
     */
    int updateByPrimaryKey(DayTrans record);
    
    List<Map<String, Object>> sumUserPlanOrder(@Param("orderDt")String orderDt);
    List<Map<String, Object>> sumUserFastOrder(@Param("orderDt")String orderDt);

	void batchAdd(@Param("tempList")List<DayTrans> tempList);
}