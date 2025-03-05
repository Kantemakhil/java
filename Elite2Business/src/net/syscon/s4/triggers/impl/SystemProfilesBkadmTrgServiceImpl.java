package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.SystemProfilesBkadmTrgService;
@Service
public class SystemProfilesBkadmTrgServiceImpl implements SystemProfilesBkadmTrgService {
	

	// TODO this is from audit
	/* SystemProfilesBkadmTrg trigger related to dbms
	 * dbms_scheduler.set_attribute(
                name => 'BKADM_QUEUE_SCHEDULE'
               ,attribute => 'repeat_interval'
               ,value => 'FREQ=SECONDLY; INTERVAL=' || lv_value);
	  */
}
