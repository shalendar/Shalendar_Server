package kr.co.MIND.shareList;

import java.util.List;

public class ShareListDTO {
	private String id;
	private String cid;
	private List<ShareListDTO> shareList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public List<ShareListDTO> getShareList() {
		return shareList;
	}
	public void setShareList(List<ShareListDTO> shareList) {
		this.shareList = shareList;
	}
	
	
}
