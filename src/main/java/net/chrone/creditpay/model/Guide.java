package net.chrone.creditpay.model;

import java.io.UnsupportedEncodingException;

public class Guide extends ParentMode{
	
	private static final long serialVersionUID = 1L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_guide.guide_id
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    private String guideId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_guide.title
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_guide.title_name
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    private String titleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_guide.title_img
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    private String titleImg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_guide.status
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_guide.content
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    private byte[] content;
    
    private String contentStr;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_guide.guide_id
     *
     * @return the value of t_guide.guide_id
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_guide.guide_id
     *
     * @param guideId the value for t_guide.guide_id
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_guide.title
     *
     * @return the value of t_guide.title
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_guide.title
     *
     * @param title the value for t_guide.title
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_guide.title_name
     *
     * @return the value of t_guide.title_name
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_guide.title_name
     *
     * @param titleName the value for t_guide.title_name
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName == null ? null : titleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_guide.title_img
     *
     * @return the value of t_guide.title_img
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public String getTitleImg() {
        return titleImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_guide.title_img
     *
     * @param titleImg the value for t_guide.title_img
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg == null ? null : titleImg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_guide.status
     *
     * @return the value of t_guide.status
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_guide.status
     *
     * @param status the value for t_guide.status
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_guide.content
     *
     * @return the value of t_guide.content
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_guide.content
     *
     * @param content the value for t_guide.content
     *
     * @mbg.generated Tue Jul 09 18:26:09 CST 2019
     */
    public void setContent(byte[] content) {
        try {
        	this.content=content;
			this.setContentStr(new String(content,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public String getContentStr() {
		return contentStr;
	}

	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}
}