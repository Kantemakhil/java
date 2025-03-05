/**
 * 
 */
package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.triggers.SystemProfilesTjnRepository;
import net.syscon.s4.triggers.SystemProfilesTjnService;


@Service
public class SystemProfilesTjnServiceImpl implements SystemProfilesTjnService {
	@Autowired
	private SystemProfilesTjnRepository systemProfilesTjnRepository;
	
	private static final String INSERTING = "INSERTING";
	private static final String UPDATING = "UPDATING";
	private static final String DELETING = "DELETING";

	@Override
	@Transactional
	public void systemProfilesTjn(final SystemProfiles old,final SystemProfiles newprofile,final String operation) {
		if(operation.equalsIgnoreCase(INSERTING))
			systemProfilesTjnRepository.insert(newprofile);
		else if(operation.equalsIgnoreCase(UPDATING))
			systemProfilesTjnRepository.update(newprofile);
		else if(operation.equalsIgnoreCase(DELETING))
			systemProfilesTjnRepository.update(old);
		
	}

}
