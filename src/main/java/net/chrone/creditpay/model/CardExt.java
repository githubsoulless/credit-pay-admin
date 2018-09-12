package net.chrone.creditpay.model;

/**
 * 
 * 存放信用卡对应快捷通道的额外信息<br>
 * 当通道为huifu的时候,存放信息如下：<br>
 * reserve1=user_cust_id,<br>
 * reserve2=acct_id,<br>
 * reserve3=bind_card_id,<br>
 * reserve4=cash_bind_card_id,<br>
 * reserve5=储蓄卡:{"bindCashCardMsg":""}    信用卡:{"bindFastPayCardSmsOrder":"20180831175500000004","bindFastPayCardErrorMsg":"","fastPaySmsOrder":""}<br>
 * reserve6= msg2
 * 
 * @author yasin
 * 2018年8月29日
 */
public class CardExt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_inf_ext.card_no
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    private String cardNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_inf_ext.chnl_code
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    private String chnlCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_inf_ext.reserve1
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    private String reserve1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_inf_ext.reserve2
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    private String reserve2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_inf_ext.reserve3
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    private String reserve3;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_inf_ext.reserve4
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    private String reserve4;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_inf_ext.reserve5
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    private String reserve5;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_card_inf_ext.reserve6
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    private String reserve6;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_inf_ext.card_no
     *
     * @return the value of t_card_inf_ext.card_no
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_inf_ext.card_no
     *
     * @param cardNo the value for t_card_inf_ext.card_no
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_inf_ext.chnl_code
     *
     * @return the value of t_card_inf_ext.chnl_code
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public String getChnlCode() {
        return chnlCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_inf_ext.chnl_code
     *
     * @param chnlCode the value for t_card_inf_ext.chnl_code
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public void setChnlCode(String chnlCode) {
        this.chnlCode = chnlCode == null ? null : chnlCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_inf_ext.reserve1
     *
     * @return the value of t_card_inf_ext.reserve1
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public String getReserve1() {
        return reserve1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_inf_ext.reserve1
     *
     * @param reserve1 the value for t_card_inf_ext.reserve1
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_inf_ext.reserve2
     *
     * @return the value of t_card_inf_ext.reserve2
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public String getReserve2() {
        return reserve2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_inf_ext.reserve2
     *
     * @param reserve2 the value for t_card_inf_ext.reserve2
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_inf_ext.reserve3
     *
     * @return the value of t_card_inf_ext.reserve3
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public String getReserve3() {
        return reserve3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_inf_ext.reserve3
     *
     * @param reserve3 the value for t_card_inf_ext.reserve3
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_inf_ext.reserve4
     *
     * @return the value of t_card_inf_ext.reserve4
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public String getReserve4() {
        return reserve4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_inf_ext.reserve4
     *
     * @param reserve4 the value for t_card_inf_ext.reserve4
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4 == null ? null : reserve4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_inf_ext.reserve5
     *
     * @return the value of t_card_inf_ext.reserve5
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public String getReserve5() {
        return reserve5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_inf_ext.reserve5
     *
     * @param reserve5 the value for t_card_inf_ext.reserve5
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5 == null ? null : reserve5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_card_inf_ext.reserve6
     *
     * @return the value of t_card_inf_ext.reserve6
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public String getReserve6() {
        return reserve6;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_card_inf_ext.reserve6
     *
     * @param reserve6 the value for t_card_inf_ext.reserve6
     *
     * @mbg.generated Wed Aug 29 10:10:32 CST 2018
     */
    public void setReserve6(String reserve6) {
        this.reserve6 = reserve6 == null ? null : reserve6.trim();
    }
}