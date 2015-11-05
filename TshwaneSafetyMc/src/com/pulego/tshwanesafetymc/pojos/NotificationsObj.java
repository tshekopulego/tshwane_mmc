package com.pulego.tshwanesafetymc.pojos;

public class NotificationsObj {
	  private int id;
	  private String title;
	  private String message;
	  private String sent_by;
	  private String date_sent;
	  private String status;
	  private String pictureurl;
	  private String category_img;
	  private String notificationdate;
	  private String publishedby;
	  private String updated_date;
	  private String updated_by;
	public NotificationsObj() {
		super();
	}
	public NotificationsObj(int id, String title, String message, String sent_by,
			String date_sent, String status, String pictureurl,
			String category_img, String notificationdate, String publishedby,
			String updated_date, String updated_by) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.sent_by = sent_by;
		this.date_sent = date_sent;
		this.status = status;
		this.pictureurl = pictureurl;
		this.category_img = category_img;
		this.notificationdate = notificationdate;
		this.publishedby = publishedby;
		this.updated_date = updated_date;
		this.updated_by = updated_by;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSent_by() {
		return sent_by;
	}
	public void setSent_by(String sent_by) {
		this.sent_by = sent_by;
	}
	public String getDate_sent() {
		return date_sent;
	}
	public void setDate_sent(String date_sent) {
		this.date_sent = date_sent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getCategory_img() {
		return category_img;
	}
	public void setCategory_img(String category_img) {
		this.category_img = category_img;
	}
	public String getNotificationdate() {
		return notificationdate;
	}
	public void setNotificationdate(String notificationdate) {
		this.notificationdate = notificationdate;
	}
	public String getPublishedby() {
		return publishedby;
	}
	public void setPublishedby(String publishedby) {
		this.publishedby = publishedby;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	  
}
