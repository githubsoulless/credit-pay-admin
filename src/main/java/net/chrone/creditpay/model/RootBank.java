package net.chrone.creditpay.model;

public class RootBank {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_root_bank.bank_no
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    private String bankNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_root_bank.bank_nm
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    private String bankNm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_root_bank.is_use
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    private Integer isUse;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_root_bank.chnl_rule
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    private String chnlRule;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_root_bank.bank_no
     *
     * @return the value of t_root_bank.bank_no
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_root_bank.bank_no
     *
     * @param bankNo the value for t_root_bank.bank_no
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_root_bank.bank_nm
     *
     * @return the value of t_root_bank.bank_nm
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    public String getBankNm() {
        return bankNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_root_bank.bank_nm
     *
     * @param bankNm the value for t_root_bank.bank_nm
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    public void setBankNm(String bankNm) {
        this.bankNm = bankNm == null ? null : bankNm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_root_bank.is_use
     *
     * @return the value of t_root_bank.is_use
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    public Integer getIsUse() {
        return isUse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_root_bank.is_use
     *
     * @param isUse the value for t_root_bank.is_use
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_root_bank.chnl_rule
     *
     * @return the value of t_root_bank.chnl_rule
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    public String getChnlRule() {
        return chnlRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_root_bank.chnl_rule
     *
     * @param chnlRule the value for t_root_bank.chnl_rule
     *
     * @mbg.generated Tue Jan 15 15:13:31 CST 2019
     */
    public void setChnlRule(String chnlRule) {
        this.chnlRule = chnlRule == null ? null : chnlRule.trim();
    }
}