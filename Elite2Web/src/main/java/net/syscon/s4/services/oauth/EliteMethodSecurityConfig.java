package net.syscon.s4.services.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import net.syscon.s4.auth.EliteMethodSecurityExpressionHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class EliteMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @SuppressWarnings("unused")
	@Autowired
    private EliteOAuthSecurityConfig securityConfig;
    
    @Autowired
    private EliteMethodSecurityExpressionHandler handler;
    
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return this.handler;
    }
}
