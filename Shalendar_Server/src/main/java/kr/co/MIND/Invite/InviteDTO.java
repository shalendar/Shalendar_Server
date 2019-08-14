package kr.co.MIND.Invite;

public class InviteDTO {
	private String sender;
	private String receiver;
	private String senderName;
	private String sender_img;
	private int cid;
	private String cName;
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSender_img() {
		return sender_img;
	}
	public void setSender_img(String sender_img) {
		this.sender_img = sender_img;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	
}
