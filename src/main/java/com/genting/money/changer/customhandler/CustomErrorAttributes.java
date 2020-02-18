package com.genting.money.changer.customhandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.genting.money.changer.utils.AppError;
import com.genting.money.changer.utils.ValidResponse;

/**
 * 
 * @author Ravi
 * 
 *         Spring boot app by default returns the below json response for all
 *         /error response.
 * 
 *         {
 *          "timestamp": "2020-02-17T16:13:47.474+0000",
 *          "status": 500,
 *         "error": "Internal Server Error", 
 *         "message": "Invalid input", "path":
 *         "/coupons" 
 *         }
 * 
 *         Overriding this to below 
 *         { "status": "fail", 
 *         "errors": null, 
 *         "data":null 
 *         }
 * 
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

		// Let Spring handle the error first, we will modify later :)
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

		ArrayList<AppError> errors = new ArrayList<AppError>();
		errors.add(new AppError(errorAttributes.get("status").toString(), errorAttributes.get("error").toString()));
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("status", ValidResponse.failure);
		resMap.put("errors", errors);
		resMap.put("data", null);
		
		return resMap;

	}

}
