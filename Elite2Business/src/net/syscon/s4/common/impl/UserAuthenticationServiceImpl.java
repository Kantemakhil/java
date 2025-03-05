package net.syscon.s4.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.UserAuthenticationDao;
import net.syscon.s4.common.UserAuthenticationService;
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
	
	@Autowired
	private UserAuthenticationDao userAuthenticationDao;

	@Override
	public boolean authenticate(final String username, final String password) {
		return userAuthenticationDao.authenticate(username, password);
	}

}
