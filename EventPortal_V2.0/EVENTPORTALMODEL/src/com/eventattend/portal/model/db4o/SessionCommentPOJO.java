package com.eventattend.portal.model.db4o;

public class SessionCommentPOJO {
	
	private String scId;
	private String scSessionId;
	private String scUserId;
	private String scComment;
	private int scCommentLikeCount = 0;
	private String scTime;
	
	public SessionCommentPOJO() {
	}


	public SessionCommentPOJO(String scSessionId) {		
		this.scSessionId = scSessionId;
		//this.scUserId = scUserId;
	}


	public String getScId() {
		return scId;
	}


	public void setScId(String scId) {
		this.scId = scId;
	}


	public String getScSessionId() {
		return scSessionId;
	}


	public void setScSessionId(String scSessionId) {
		this.scSessionId = scSessionId;
	}


	public String getScUserId() {
		return scUserId;
	}


	public void setScUserId(String scUserId) {
		this.scUserId = scUserId;
	}


	public String getScComment() {
		return scComment;
	}


	public void setScComment(String scComment) {
		this.scComment = scComment;
	}

	public int getScCommentLikeCount() {
		return scCommentLikeCount;
	}

	public void setScCommentLikeCount(int scCommentLikeCount) {
		this.scCommentLikeCount = scCommentLikeCount;
	}
	public String getScTime() {
		return scTime;
	}
	public void setScTime(String scTime) {
		this.scTime = scTime;
	}		
	
}
