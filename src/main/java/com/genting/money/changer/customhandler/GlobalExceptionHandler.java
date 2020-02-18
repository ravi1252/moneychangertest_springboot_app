package com.genting.money.changer.customhandler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.genting.money.changer.exception.InvalidInputDataException;
import com.genting.money.changer.utils.AppError;
import com.genting.money.changer.utils.AppResponse;
import com.genting.money.changer.utils.AppResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidInputDataException.class)
	@ResponseBody
	public AppResponse invalidDataInput(InvalidInputDataException ex, HttpServletResponse response){
		 
		ArrayList<AppError> errors = new ArrayList<AppError>();
		errors.add(new AppError("InvalidInputData", ex.getMessage()));
		
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return new AppResponse(AppResponseStatus.failure, errors, null);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public AppResponse exception(Exception ex, HttpServletResponse response){
		
		ArrayList<AppError> errors = new ArrayList<AppError>();
		errors.add(new AppError("InternalServerError", ex.getMessage()));
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new AppResponse(AppResponseStatus.failure, errors, null);
	}

}
