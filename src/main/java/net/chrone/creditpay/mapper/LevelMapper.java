package net.chrone.creditpay.mapper;

import java.util.List;
import net.chrone.creditpay.model.Level;
import net.chrone.creditpay.model.LevelExample;
import org.apache.ibatis.annotations.Param;

public interface LevelMapper extends ParentMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */
	long countByExample(LevelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */
	int deleteByExample(LevelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */
	int deleteByPrimaryKey(Integer levelId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */
	int insert(Level record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */
	int insertSelective(Level record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */List<Level> selectByExample(LevelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */
	Level selectByPrimaryKey(Integer levelId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */int updateByExampleSelective(@Param("record") Level record,@Param("example") LevelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */int updateByExample(@Param("record") Level record,@Param("example") LevelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */
	int updateByPrimaryKeySelective(Level record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_level_inf
	 * @mbg.generated  Thu Apr 25 18:45:08 CST 2019
	 */
	int updateByPrimaryKey(Level record);
}