package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.triggers.SystemPofilesTjnRepository;
import net.syscon.s4.triggers.SystemPofilesTjnService;

@Service
public class SystemPofilesTjnServiceImpl implements SystemPofilesTjnService {
	
	@Autowired
	private SystemPofilesTjnRepository systemPofilesTjnRepository;

	@Override
	public Integer systemProfilesForInserting(List<SystemProfiles> lstSystemProfiles, String status) {
		if (status.equals("INSERTING")) {
			return systemPofilesTjnRepository.insertingSystemProfile(lstSystemProfiles);
		} else if (status.equals("UPDATING")) {
			return systemPofilesTjnRepository.updatingSystemProfile(lstSystemProfiles);
		} else {
			return systemPofilesTjnRepository.deletingSystemProfile(lstSystemProfiles);
		}
	}

}
