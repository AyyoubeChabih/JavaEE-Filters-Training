package org.mql.jee.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mql.jee.actions.CompanyAction;
import org.mql.jee.models.Company;


public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String prefix = "/WEB-INF/views/";
	private String suffix = ".jsp";
	private CompanyAction action;
	private String source;
	
	public void init() throws ServletException {
		action = new CompanyAction();
		ServletConfig config = getServletConfig();
		source = config.getInitParameter("source"); // paramettre de la servlet
		prefix = config.getServletContext().getInitParameter("prefix"); // paramettre context
		suffix = config.getServletContext().getInitParameter("suffix"); // paramettre context
		System.out.println("Source = " + source);
		System.out.println("prefix = " + prefix);
		System.out.println("Suffix = " + suffix);
	}
/*	
	public void init(ServletConfig config) throws ServletException {
		
	}
*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String view = "";
		if (uri.endsWith("/addCompany")) {
			System.out.println(">> ajout d'une nouvelle entreprise");
			action.setModel((Company)request.getAttribute("model"));
			view = action.addCompany();
		}
		
		request.getRequestDispatcher(prefix + view + suffix).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
