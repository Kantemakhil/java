package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.triggers.SystemProfilesT1Repository;
import net.syscon.s4.triggers.SystemProfilesT1Service;

@Service
public class SystemProfilesT1ServiceImpl implements SystemProfilesT1Service {

	@Autowired
	private SystemProfilesT1Repository systemProfileT1Repository;

//	@Override
//	public String insertDbPatches(SystemProfiles systemPrfles) {
//		Integer returnVal = systemProfileT1Repository.insertDbPatches(systemPrfles);
//		return returnVal.toString();
//	}

	@Override
	public void insertDbPatches(List<SystemProfiles> systemProfile) {
		// Inserting Data into DB_PATCHES Table
		systemProfileT1Repository.insertDbPatches(systemProfile);
	
	}

}
