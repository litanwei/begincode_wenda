package net.begincode.core.model;

import java.io.Serializable;
import java.util.Date;

public class BegincodeUser implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.begincode_user_id
     *
     * @mbggenerated
     */
    private Integer begincodeUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.login_name
     *
     * @mbggenerated
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.pwd
     *
     * @mbggenerated
     */
    private String pwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.nickname
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.cdate
     *
     * @mbggenerated
     */
    private Date cdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.sex
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.tel_phone
     *
     * @mbggenerated
     */
    private String telPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.receive_email
     *
     * @mbggenerated
     */
    private String receiveEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.pic
     *
     * @mbggenerated
     */
    private String pic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.check_flag
     *
     * @mbggenerated
     */
    private String checkFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.gag
     *
     * @mbggenerated
     */
    private String gag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.course
     *
     * @mbggenerated
     */
    private String course;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.open_id
     *
     * @mbggenerated
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.access_token
     *
     * @mbggenerated
     */
    private String accessToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.user_source_id
     *
     * @mbggenerated
     */
    private Integer userSourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.delete_flag
     *
     * @mbggenerated
     */
    private String deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column begincode_user.invite_code
     *
     * @mbggenerated
     */
    private String inviteCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table begincode_user
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.begincode_user_id
     *
     * @return the value of begincode_user.begincode_user_id
     *
     * @mbggenerated
     */
    public Integer getBegincodeUserId() {
        return begincodeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.begincode_user_id
     *
     * @param begincodeUserId the value for begincode_user.begincode_user_id
     *
     * @mbggenerated
     */
    public void setBegincodeUserId(Integer begincodeUserId) {
        this.begincodeUserId = begincodeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.login_name
     *
     * @return the value of begincode_user.login_name
     *
     * @mbggenerated
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.login_name
     *
     * @param loginName the value for begincode_user.login_name
     *
     * @mbggenerated
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.pwd
     *
     * @return the value of begincode_user.pwd
     *
     * @mbggenerated
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.pwd
     *
     * @param pwd the value for begincode_user.pwd
     *
     * @mbggenerated
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.nickname
     *
     * @return the value of begincode_user.nickname
     *
     * @mbggenerated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.nickname
     *
     * @param nickname the value for begincode_user.nickname
     *
     * @mbggenerated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.cdate
     *
     * @return the value of begincode_user.cdate
     *
     * @mbggenerated
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.cdate
     *
     * @param cdate the value for begincode_user.cdate
     *
     * @mbggenerated
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.sex
     *
     * @return the value of begincode_user.sex
     *
     * @mbggenerated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.sex
     *
     * @param sex the value for begincode_user.sex
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.tel_phone
     *
     * @return the value of begincode_user.tel_phone
     *
     * @mbggenerated
     */
    public String getTelPhone() {
        return telPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.tel_phone
     *
     * @param telPhone the value for begincode_user.tel_phone
     *
     * @mbggenerated
     */
    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.email
     *
     * @return the value of begincode_user.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.email
     *
     * @param email the value for begincode_user.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.receive_email
     *
     * @return the value of begincode_user.receive_email
     *
     * @mbggenerated
     */
    public String getReceiveEmail() {
        return receiveEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.receive_email
     *
     * @param receiveEmail the value for begincode_user.receive_email
     *
     * @mbggenerated
     */
    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.pic
     *
     * @return the value of begincode_user.pic
     *
     * @mbggenerated
     */
    public String getPic() {
        return pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.pic
     *
     * @param pic the value for begincode_user.pic
     *
     * @mbggenerated
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.check_flag
     *
     * @return the value of begincode_user.check_flag
     *
     * @mbggenerated
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.check_flag
     *
     * @param checkFlag the value for begincode_user.check_flag
     *
     * @mbggenerated
     */
    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.gag
     *
     * @return the value of begincode_user.gag
     *
     * @mbggenerated
     */
    public String getGag() {
        return gag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.gag
     *
     * @param gag the value for begincode_user.gag
     *
     * @mbggenerated
     */
    public void setGag(String gag) {
        this.gag = gag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.course
     *
     * @return the value of begincode_user.course
     *
     * @mbggenerated
     */
    public String getCourse() {
        return course;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.course
     *
     * @param course the value for begincode_user.course
     *
     * @mbggenerated
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.open_id
     *
     * @return the value of begincode_user.open_id
     *
     * @mbggenerated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.open_id
     *
     * @param openId the value for begincode_user.open_id
     *
     * @mbggenerated
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.access_token
     *
     * @return the value of begincode_user.access_token
     *
     * @mbggenerated
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.access_token
     *
     * @param accessToken the value for begincode_user.access_token
     *
     * @mbggenerated
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.user_source_id
     *
     * @return the value of begincode_user.user_source_id
     *
     * @mbggenerated
     */
    public Integer getUserSourceId() {
        return userSourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.user_source_id
     *
     * @param userSourceId the value for begincode_user.user_source_id
     *
     * @mbggenerated
     */
    public void setUserSourceId(Integer userSourceId) {
        this.userSourceId = userSourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.delete_flag
     *
     * @return the value of begincode_user.delete_flag
     *
     * @mbggenerated
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.delete_flag
     *
     * @param deleteFlag the value for begincode_user.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column begincode_user.invite_code
     *
     * @return the value of begincode_user.invite_code
     *
     * @mbggenerated
     */
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column begincode_user.invite_code
     *
     * @param inviteCode the value for begincode_user.invite_code
     *
     * @mbggenerated
     */
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}