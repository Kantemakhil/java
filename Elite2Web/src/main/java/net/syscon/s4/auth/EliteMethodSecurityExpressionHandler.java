package net.syscon.s4.auth;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.stereotype.Component;

import net.syscon.s4.global.Omss40Service;

@Component
public class EliteMethodSecurityExpressionHandler extends OAuth2MethodSecurityExpressionHandler {

	@Autowired
	private Omss40Service omss40Service;
	
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
	
	@Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {        
        final EliteSecurityExpressionRoot root = new EliteSecurityExpressionRoot(authentication, invocation, omss40Service);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
