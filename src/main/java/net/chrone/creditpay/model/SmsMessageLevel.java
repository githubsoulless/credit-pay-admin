package net.chrone.creditpay.model;

public class SmsMessageLevel {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_smsmessage_level.id
	 * @mbggenerated  Mon Dec 04 18:10:07 CST 2017
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_smsmessage_level.level_id
	 * @mbggenerated  Mon Dec 04 18:10:07 CST 2017
	 */
	private Integer levelId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_smsmessage_level.id
	 * @return  the value of t_smsmessage_level.id
	 * @mbggenerated  Mon Dec 04 18:10:07 CST 2017
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_smsmessage_level.id
	 * @param id  the value for t_smsmessage_level.id
	 * @mbggenerated  Mon Dec 04 18:10:07 CST 2017
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_smsmessage_level.level_id
	 * @return  the value of t_smsmessage_level.level_id
	 * @mbggenerated  Mon Dec 04 18:10:07 CST 2017
	 */
	public Integer getLevelId() {
		return levelId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_smsmessage_level.level_id
	 * @param levelId  the value for t_smsmessage_level.level_id
	 * @mbggenerated  Mon Dec 04 18:10:07 CST 2017
	 */
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
}