package org.mql.jee.web;

import java.io.IOException;import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.mql.jee.models.Company;

public class ParametersFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {
	}

	public int getIntParameter(String name, ServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter(name));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public Company getCompany(ServletRequest request) {
		return new Company(
			getIntParameter("id", request),
			request.getParameter("name"),
			request.getParameter("city")
		);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(">> parametersFilter.doFilter()");
		Company cmp = getCompany(request);
		request.setAttribute("model", cmp);
		chain.doFilter(request, response);
	}
}
