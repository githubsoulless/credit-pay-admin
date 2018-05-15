package net.chrone.creditpay.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgentMenuExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public AgentMenuExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMenuIdIsNull() {
            addCriterion("menu_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("menu_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(String value) {
            addCriterion("menu_id =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(String value) {
            addCriterion("menu_id <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(String value) {
            addCriterion("menu_id >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(String value) {
            addCriterion("menu_id >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(String value) {
            addCriterion("menu_id <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(String value) {
            addCriterion("menu_id <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLike(String value) {
            addCriterion("menu_id like", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotLike(String value) {
            addCriterion("menu_id not like", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<String> values) {
            addCriterion("menu_id in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<String> values) {
            addCriterion("menu_id not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(String value1, String value2) {
            addCriterion("menu_id between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(String value1, String value2) {
            addCriterion("menu_id not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuNmIsNull() {
            addCriterion("menu_nm is null");
            return (Criteria) this;
        }

        public Criteria andMenuNmIsNotNull() {
            addCriterion("menu_nm is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNmEqualTo(String value) {
            addCriterion("menu_nm =", value, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmNotEqualTo(String value) {
            addCriterion("menu_nm <>", value, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmGreaterThan(String value) {
            addCriterion("menu_nm >", value, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmGreaterThanOrEqualTo(String value) {
            addCriterion("menu_nm >=", value, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmLessThan(String value) {
            addCriterion("menu_nm <", value, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmLessThanOrEqualTo(String value) {
            addCriterion("menu_nm <=", value, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmLike(String value) {
            addCriterion("menu_nm like", value, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmNotLike(String value) {
            addCriterion("menu_nm not like", value, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmIn(List<String> values) {
            addCriterion("menu_nm in", values, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmNotIn(List<String> values) {
            addCriterion("menu_nm not in", values, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmBetween(String value1, String value2) {
            addCriterion("menu_nm between", value1, value2, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuNmNotBetween(String value1, String value2) {
            addCriterion("menu_nm not between", value1, value2, "menuNm");
            return (Criteria) this;
        }

        public Criteria andMenuUrlIsNull() {
            addCriterion("menu_url is null");
            return (Criteria) this;
        }

        public Criteria andMenuUrlIsNotNull() {
            addCriterion("menu_url is not null");
            return (Criteria) this;
        }

        public Criteria andMenuUrlEqualTo(String value) {
            addCriterion("menu_url =", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlNotEqualTo(String value) {
            addCriterion("menu_url <>", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlGreaterThan(String value) {
            addCriterion("menu_url >", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlGreaterThanOrEqualTo(String value) {
            addCriterion("menu_url >=", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlLessThan(String value) {
            addCriterion("menu_url <", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlLessThanOrEqualTo(String value) {
            addCriterion("menu_url <=", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlLike(String value) {
            addCriterion("menu_url like", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlNotLike(String value) {
            addCriterion("menu_url not like", value, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlIn(List<String> values) {
            addCriterion("menu_url in", values, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlNotIn(List<String> values) {
            addCriterion("menu_url not in", values, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlBetween(String value1, String value2) {
            addCriterion("menu_url between", value1, value2, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuUrlNotBetween(String value1, String value2) {
            addCriterion("menu_url not between", value1, value2, "menuUrl");
            return (Criteria) this;
        }

        public Criteria andMenuParentIsNull() {
            addCriterion("menu_parent is null");
            return (Criteria) this;
        }

        public Criteria andMenuParentIsNotNull() {
            addCriterion("menu_parent is not null");
            return (Criteria) this;
        }

        public Criteria andMenuParentEqualTo(String value) {
            addCriterion("menu_parent =", value, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentNotEqualTo(String value) {
            addCriterion("menu_parent <>", value, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentGreaterThan(String value) {
            addCriterion("menu_parent >", value, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentGreaterThanOrEqualTo(String value) {
            addCriterion("menu_parent >=", value, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentLessThan(String value) {
            addCriterion("menu_parent <", value, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentLessThanOrEqualTo(String value) {
            addCriterion("menu_parent <=", value, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentLike(String value) {
            addCriterion("menu_parent like", value, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentNotLike(String value) {
            addCriterion("menu_parent not like", value, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentIn(List<String> values) {
            addCriterion("menu_parent in", values, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentNotIn(List<String> values) {
            addCriterion("menu_parent not in", values, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentBetween(String value1, String value2) {
            addCriterion("menu_parent between", value1, value2, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuParentNotBetween(String value1, String value2) {
            addCriterion("menu_parent not between", value1, value2, "menuParent");
            return (Criteria) this;
        }

        public Criteria andMenuSortIsNull() {
            addCriterion("menu_sort is null");
            return (Criteria) this;
        }

        public Criteria andMenuSortIsNotNull() {
            addCriterion("menu_sort is not null");
            return (Criteria) this;
        }

        public Criteria andMenuSortEqualTo(String value) {
            addCriterion("menu_sort =", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortNotEqualTo(String value) {
            addCriterion("menu_sort <>", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortGreaterThan(String value) {
            addCriterion("menu_sort >", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortGreaterThanOrEqualTo(String value) {
            addCriterion("menu_sort >=", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortLessThan(String value) {
            addCriterion("menu_sort <", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortLessThanOrEqualTo(String value) {
            addCriterion("menu_sort <=", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortLike(String value) {
            addCriterion("menu_sort like", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortNotLike(String value) {
            addCriterion("menu_sort not like", value, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortIn(List<String> values) {
            addCriterion("menu_sort in", values, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortNotIn(List<String> values) {
            addCriterion("menu_sort not in", values, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortBetween(String value1, String value2) {
            addCriterion("menu_sort between", value1, value2, "menuSort");
            return (Criteria) this;
        }

        public Criteria andMenuSortNotBetween(String value1, String value2) {
            addCriterion("menu_sort not between", value1, value2, "menuSort");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrIsNull() {
            addCriterion("row_crt_usr is null");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrIsNotNull() {
            addCriterion("row_crt_usr is not null");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrEqualTo(String value) {
            addCriterion("row_crt_usr =", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrNotEqualTo(String value) {
            addCriterion("row_crt_usr <>", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrGreaterThan(String value) {
            addCriterion("row_crt_usr >", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrGreaterThanOrEqualTo(String value) {
            addCriterion("row_crt_usr >=", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrLessThan(String value) {
            addCriterion("row_crt_usr <", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrLessThanOrEqualTo(String value) {
            addCriterion("row_crt_usr <=", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrLike(String value) {
            addCriterion("row_crt_usr like", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrNotLike(String value) {
            addCriterion("row_crt_usr not like", value, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrIn(List<String> values) {
            addCriterion("row_crt_usr in", values, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrNotIn(List<String> values) {
            addCriterion("row_crt_usr not in", values, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrBetween(String value1, String value2) {
            addCriterion("row_crt_usr between", value1, value2, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtUsrNotBetween(String value1, String value2) {
            addCriterion("row_crt_usr not between", value1, value2, "rowCrtUsr");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsIsNull() {
            addCriterion("row_crt_ts is null");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsIsNotNull() {
            addCriterion("row_crt_ts is not null");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsEqualTo(Date value) {
            addCriterion("row_crt_ts =", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsNotEqualTo(Date value) {
            addCriterion("row_crt_ts <>", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsGreaterThan(Date value) {
            addCriterion("row_crt_ts >", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsGreaterThanOrEqualTo(Date value) {
            addCriterion("row_crt_ts >=", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsLessThan(Date value) {
            addCriterion("row_crt_ts <", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsLessThanOrEqualTo(Date value) {
            addCriterion("row_crt_ts <=", value, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsIn(List<Date> values) {
            addCriterion("row_crt_ts in", values, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsNotIn(List<Date> values) {
            addCriterion("row_crt_ts not in", values, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsBetween(Date value1, Date value2) {
            addCriterion("row_crt_ts between", value1, value2, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRowCrtTsNotBetween(Date value1, Date value2) {
            addCriterion("row_crt_ts not between", value1, value2, "rowCrtTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrIsNull() {
            addCriterion("rec_upd_usr is null");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrIsNotNull() {
            addCriterion("rec_upd_usr is not null");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrEqualTo(String value) {
            addCriterion("rec_upd_usr =", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrNotEqualTo(String value) {
            addCriterion("rec_upd_usr <>", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrGreaterThan(String value) {
            addCriterion("rec_upd_usr >", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrGreaterThanOrEqualTo(String value) {
            addCriterion("rec_upd_usr >=", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrLessThan(String value) {
            addCriterion("rec_upd_usr <", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrLessThanOrEqualTo(String value) {
            addCriterion("rec_upd_usr <=", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrLike(String value) {
            addCriterion("rec_upd_usr like", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrNotLike(String value) {
            addCriterion("rec_upd_usr not like", value, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrIn(List<String> values) {
            addCriterion("rec_upd_usr in", values, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrNotIn(List<String> values) {
            addCriterion("rec_upd_usr not in", values, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrBetween(String value1, String value2) {
            addCriterion("rec_upd_usr between", value1, value2, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdUsrNotBetween(String value1, String value2) {
            addCriterion("rec_upd_usr not between", value1, value2, "recUpdUsr");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsIsNull() {
            addCriterion("rec_upd_ts is null");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsIsNotNull() {
            addCriterion("rec_upd_ts is not null");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsEqualTo(Date value) {
            addCriterion("rec_upd_ts =", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsNotEqualTo(Date value) {
            addCriterion("rec_upd_ts <>", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsGreaterThan(Date value) {
            addCriterion("rec_upd_ts >", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsGreaterThanOrEqualTo(Date value) {
            addCriterion("rec_upd_ts >=", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsLessThan(Date value) {
            addCriterion("rec_upd_ts <", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsLessThanOrEqualTo(Date value) {
            addCriterion("rec_upd_ts <=", value, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsIn(List<Date> values) {
            addCriterion("rec_upd_ts in", values, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsNotIn(List<Date> values) {
            addCriterion("rec_upd_ts not in", values, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsBetween(Date value1, Date value2) {
            addCriterion("rec_upd_ts between", value1, value2, "recUpdTs");
            return (Criteria) this;
        }

        public Criteria andRecUpdTsNotBetween(Date value1, Date value2) {
            addCriterion("rec_upd_ts not between", value1, value2, "recUpdTs");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated do_not_delete_during_merge Thu Nov 23 17:06:36 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_agent_menu_inf
     *
     * @mbggenerated Thu Nov 23 17:06:36 CST 2017
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}