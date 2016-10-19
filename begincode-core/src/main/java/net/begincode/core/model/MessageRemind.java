package net.begincode.core.model;

import java.util.Date;

/*
 * 信息提醒类
 * 获得关于@的相关信息
 */
public class MessageRemind {
	/*
	 * 总投票数
	 */
	private int vote_count;
	/*
	 * 总回答数
	 */
	private	int answer_count;
	/*
	 * 总浏览数
	 */
	private int view_count;
	/*
	 * 回答的名字
	 */
	private String answer_username;
	/**
	 * 回答人的id
	 */
	private int answer_userid;
	/**
	 * 标签名
	 */
	private	String label_name;
	/*
	 * @的内容
	 */
	private String answer_content;
	/*
	 * 回答的时间
	 */
	private Date create_date;
	/**
	 * 问题id
	 */
	private int problem_id;
	/**
	 * 提醒信息id
	 */
	private int message_id;
	/**
	 * 问题的用户名
	 */
	private String pr_username;
	/**
	 * 问题创建的时间
	 */
	private Date pr_createtime;
	/**
	 * 问题的内容
	 */
	private String pr_content;
	
	
	public int getVote_count() {
		return vote_count;
	}
	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}
	public int getAnswer_count() {
		return answer_count;
	}
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public String getAnswer_username() {
		return answer_username;
	}
	public void setAnswer_username(String answer_username) {
		this.answer_username = answer_username;
	}
	public int getAnswer_userid() {
		return answer_userid;
	}
	public void setAnswer_userid(int answer_userid) {
		this.answer_userid = answer_userid;
	}
	public String getLabel_name() {
		return label_name;
	}
	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}
	public String getAnswer_content() {
		return answer_content;
	}
	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public int getProblem_id() {
		return problem_id;
	}
	public void setProblem_id(int problem_id) {
		this.problem_id = problem_id;
	}
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public String getPr_username() {
		return pr_username;
	}
	public void setPr_username(String pr_username) {
		this.pr_username = pr_username;
	}
	public Date getPr_createtime() {
		return pr_createtime;
	}
	public void setPr_createtime(Date pr_createtime) {
		this.pr_createtime = pr_createtime;
	}
	public String getPr_content() {
		return pr_content;
	}
	public void setPr_content(String pr_content) {
		this.pr_content = pr_content;
	}
	@Override
	public String toString() {
		return "MessageRemind [vote_count=" + vote_count + ", answer_count=" + answer_count + ", view_count="
				+ view_count + ", answer_username=" + answer_username + ", answer_userid=" + answer_userid
				+ ", label_name=" + label_name + ", answer_content=" + answer_content + ", create_date=" + create_date
				+ ", problem_id=" + problem_id + ", message_id=" + message_id + ", pr_username=" + pr_username
				+ ", pr_createtime=" + pr_createtime + ", pr_content=" + pr_content + "]";
	}
	
	
	
	

	
	
	
}
