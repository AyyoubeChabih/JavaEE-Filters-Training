package org.mql.jee.actions;

import org.mql.jee.models.Company;

public class CompanyAction {
	private Company model;
	
	public CompanyAction() {
	}
	
	public void setModel(Company model) {
		this.model = model;
	}
	
	public String addCompany() {
		System.out.println(">> addCompany() : " + model);
		return "AddCompanyOK";
	}
}
