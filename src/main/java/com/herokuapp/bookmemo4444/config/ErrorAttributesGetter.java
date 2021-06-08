package com.herokuapp.bookmemo4444.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.ServletWebRequest;

public class ErrorAttributesGetter {
	/**
	 * エラー情報を抽出する。
	 *
	 * @param req リクエスト情報
	 * @return エラー情報
	 */
	public static Map<String, Object> getErrorAttributes(HttpServletRequest req) {
		// DefaultErrorAttributes クラスで詳細なエラー情報を取得する
		ServletWebRequest servletWebRequest = new ServletWebRequest(req);
		DefaultErrorAttributes defaultErrorAttributes = new DefaultErrorAttributes();
		ErrorAttributeOptions errorAttributeOptions = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.BINDING_ERRORS,
				ErrorAttributeOptions.Include.EXCEPTION, ErrorAttributeOptions.Include.MESSAGE,
				ErrorAttributeOptions.Include.STACK_TRACE);
		return defaultErrorAttributes.getErrorAttributes(servletWebRequest, errorAttributeOptions);
	}
}
