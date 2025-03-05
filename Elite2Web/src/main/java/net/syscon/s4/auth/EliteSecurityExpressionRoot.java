package net.syscon.s4.auth;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;

import net.syscon.s4.common.beans.UserRoleInfo;
import net.syscon.s4.global.Omss40Service;

public class EliteSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
	private Object filterObject;
	private Object returnObject;
	private MethodInvocation invocation;
	private Authentication authentication;

	private Omss40Service omss40Service;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(EliteSecurityExpressionRoot.class.getName());

	public EliteSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation, Omss40Service omss40Service) {
		super(authentication);
		this.invocation = invocation;
		this.authentication = authentication;
		this.omss40Service = omss40Service;
	}

	public boolean hasEliteRole(String role) {
		String userId = this.authentication.getName();
		if (userId != null && !userId.isEmpty()) {
			UserRoleInfo info = this.omss40Service.getUserRoleInfo(userId);
			String methodMapping = this.invocation.getMethod().getAnnotation(RequestMapping.class).value()[0];
			String[] linksplit = methodMapping.split("/");
			String link = linksplit[1];
			link = link.toUpperCase();
			try {
				if ("no".equals(role)) {
					return true;
				} else {
					String usrRole = info.getUserRoles().get(link);					
					if (usrRole != null && !usrRole.isEmpty()) {
						if ("full".equals(usrRole) || usrRole.equals(role)) {
							return true;
						}
					}
				}
			} catch (Exception ex) {
				logger.error("In hasEliteRole exception{} ", ex.getMessage());
			}
		}
		return false;
	}
	
	public boolean hasEliteReportRole(String role, String reportId) {
		String userId = this.authentication.getName();
		if (userId != null && !userId.isEmpty()) {
			UserRoleInfo info = this.omss40Service.getUserRoleInfo(userId);
			if ("no".equals(role)) {
				return true;
			} else {
				String usrRole = info.getUserRoles().get(reportId);					
				if (usrRole != null && !usrRole.isEmpty()) {
					if ("full".equals(usrRole)) {
						return true;
					} else if (usrRole.equals(role)) {
						return true;
					}
				}				
			}
		}
		return false;
	}

	//

	@Override
	public Object getFilterObject() {
		return this.filterObject;
	}

	@Override
	public Object getReturnObject() {
		return this.returnObject;
	}

	@Override
	public Object getThis() {
		return this;
	}

	@Override
	public void setFilterObject(Object obj) {
		this.filterObject = obj;
	}

	@Override
	public void setReturnObject(Object obj) {
		this.returnObject = obj;
	}
}
