package kr.co.MIND.schedule;

import java.util.Date;

public class ScheduleDTO {
	private String id;
	private int cid;
	private int sid;
	private String title;
	private String sContent;
	private String startDate;
	private String endDate;
	private String area;
	
	private String img_url;
	
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	private int numOfComments;
	public int getNumOfComments() {
		return numOfComments;
	}
	public void setNumOfComments(int numOfComments) {
		this.numOfComments = numOfComments;
	}
	public int getCid() {
		return cid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getsContent() {
		return sContent;
	}
	public void setsContent(String sContent) {
		this.sContent = sContent;
	}


	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

}
