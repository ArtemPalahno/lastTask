package ua.nure.palagno.db.entity;

import java.io.Serializable;

public class Status implements Serializable {
	private int id;
	private String statusName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Status() {
	}

	public Status(int id, String statusName) {

		this.id = id;
		this.statusName = statusName;
	}
}
