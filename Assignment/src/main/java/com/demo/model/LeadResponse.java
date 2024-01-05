package com.demo.model;

import java.util.List;

public class LeadResponse {
	private String status;
	private List<Lead> data;

	public LeadResponse(String status, List<Lead> data) {
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Lead> getData() {
		return data;
	}

	public void setData(List<Lead> data) {
		this.data = data;
	}

	// getters and setters
}