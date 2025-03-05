package net.syscon.s4.global.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.global.EliteAuthenticationService;
import net.syscon.s4.global.Omss40Repository;

@Service
public class EliteAuthenticationServiceImpl extends BaseBusiness implements EliteAuthenticationService {
	
	private static Logger logger = LogManager.getLogger(EliteAuthenticationServiceImpl.class);

	@Autowired
	private Omss40Repository omss40Repository;

	@Override
	public String getUserStatus(final String userId) {
		String status = omss40Repository.getStatus(userId);
		return status;
	}

}
