package net.chrone.creditpay.mapper;

import net.chrone.creditpay.model.CardValuateFee;

public interface CardValuateFeeMapper extends ParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_card_valuate_fee
     *
     * @mbggenerated Sat Oct 12 11:12:34 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_card_valuate_fee
     *
     * @mbggenerated Sat Oct 12 11:12:34 CST 2019
     */
    int insert(CardValuateFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_card_valuate_fee
     *
     * @mbggenerated Sat Oct 12 11:12:34 CST 2019
     */
    int insertSelective(CardValuateFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_card_valuate_fee
     *
     * @mbggenerated Sat Oct 12 11:12:34 CST 2019
     */
    CardValuateFee selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_card_valuate_fee
     *
     * @mbggenerated Sat Oct 12 11:12:34 CST 2019
     */
    int updateByPrimaryKeySelective(CardValuateFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_card_valuate_fee
     *
     * @mbggenerated Sat Oct 12 11:12:34 CST 2019
     */
    int updateByPrimaryKey(CardValuateFee record);

    int selectFeeByPrimaryKey(int i);

    int selectOldFeeByPrimaryKey(int i);



}