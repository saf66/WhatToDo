package com.example.whattodo;

public class TaskItem {

	private Boolean checked;
	private String title;
	private String subtitle;

	public TaskItem(Boolean checked, String title, String subtitle) {
		this.checked = checked;
		this.title = title;
		this.subtitle = subtitle;
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

}
