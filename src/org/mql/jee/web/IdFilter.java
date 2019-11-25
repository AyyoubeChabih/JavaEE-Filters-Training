package org.mql.jee.web;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
@WebFilter(
		urlPatterns = { 
				"/IdFilter", 
				"/filters/*"
		}, 
		servletNames = { "controller" }
)
*/
public class IdFilter implements Filter {
	private Vector<Integer> idList;
	private String prefix = "/WEB-INF/views/";
	private String suffix = ".jsp";
	
	public void init(FilterConfig config) throws ServletException {
		idList = new Vector<Integer>();
		String source = config.getInitParameter("source"); // paramettre de la servlet
		prefix = config.getServletContext().getInitParameter("prefix"); // paramettre context
		suffix = config.getServletContext().getInitParameter("suffix"); // paramettre context
		System.out.println("Source = " + source);
		System.out.println("prefix = " + prefix);
		System.out.println("Suffix = " + suffix);
	}
	
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(">> IdFilter.doFilter()");
		String param = request.getParameter("id");
		try {
			int id = Integer.parseInt(param);
			if (idList.contains(id)) {
				request.setAttribute("message", "Identificateur déjà existe");
				request.getRequestDispatcher(prefix + "Error" + suffix).forward(request, response);				
			} else {
				idList.add(id);
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("message", "Identificateur non valide");
			request.getRequestDispatcher(prefix + "Error" + suffix).forward(request, response);
		}
	}
}
