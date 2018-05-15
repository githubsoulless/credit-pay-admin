package net.chrone.creditpay.mapper;

import java.util.List;
import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.MenuInfExample;
import org.apache.ibatis.annotations.Param;

public interface MenuInfMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    long countByExample(MenuInfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByExample(MenuInfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int deleteByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insert(MenuInf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int insertSelective(MenuInf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    List<MenuInf> selectByExample(MenuInfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    MenuInf selectByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExampleSelective(@Param("record") MenuInf record, @Param("example") MenuInfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByExample(@Param("record") MenuInf record, @Param("example") MenuInfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKeySelective(MenuInf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu_inf
     *
     * @mbg.generated Thu Nov 16 10:53:39 CST 2017
     */
    int updateByPrimaryKey(MenuInf record);

	List<MenuInf> getMenuByUserRole(String roleId);
}