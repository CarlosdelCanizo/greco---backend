package com.greco.security;

import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.sql.DataSource;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	private static String REALM="CRM_REALM";
	private static final int TEN_DAYS = 60 * 60 * 24 * 10;
	private static final int ONE_DAY = 60 * 60 * 24;
	private static final int THIRTY_DAYS = 60 * 60 * 24 * 30;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	PasswordEncoder passwordEncoder;


	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private CrmUserDetailsService crmUserDetailsService;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//clients.jdbc(dataSource);

		clients.inMemory()
	        .withClient("crmClient1")
            .secret(passwordEncoder.encode("crmSuperSecret"))
            .authorizedGrantTypes("password", "refresh_token")
            .scopes("read", "write", "trust")
            .accessTokenValiditySeconds(ONE_DAY)
            .refreshTokenValiditySeconds(THIRTY_DAYS);

	}

    /**
     * set a few properties (token store, user approvals, and AuthenticationManager, userDetailService)
	 * of the Authorization Server endpoints.
	 * @param endpoints Authorization Server endpoints
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
		.authenticationManager(authenticationManager)
		.userDetailsService(crmUserDetailsService);
	}

	/**
     * override the security realm of the Authorization Server
	 * @param oauthServer
     * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.realm(REALM);
	}
}
