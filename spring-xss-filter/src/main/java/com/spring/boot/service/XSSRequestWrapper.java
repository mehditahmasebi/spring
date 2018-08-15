package com.spring.boot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

	public XSSRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);

		if (values == null) {
			return null;
		}

		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = stripXSS(values[i]);
		}

		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);

		return stripXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return stripXSS(value);
	}

	private String stripXSS(String value) {
		if(value != null)
			System.out.println("escapeHTML work successfully and escapeHTML value is : " + StringEscapeUtils.escapeHtml(value));
		return StringEscapeUtils.escapeHtml(value);
	}
}