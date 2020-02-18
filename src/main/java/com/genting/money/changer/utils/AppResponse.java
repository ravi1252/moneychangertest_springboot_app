package com.genting.money.changer.utils;

import java.util.ArrayList;

public class AppResponse {

	public ValidResponse status;
	public ArrayList<AppError> errors;
	public Object data;

	public AppResponse(ValidResponse status, ArrayList<AppError> errors, Object data) {
		super();
		this.status = status;
		this.errors = errors;
		this.data = data;
	}

	public ArrayList<AppError> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<AppError> errors) {
		this.errors = errors;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ValidResponse getStatus() {
		return status;
	}

	public void setStatus(ValidResponse status) {
		this.status = status;
	}

}
