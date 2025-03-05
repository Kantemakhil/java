package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.StaffMembersT2Repository;
import net.syscon.s4.triggers.StaffMembersT2Service;

@Service
public class StaffMembersT2ServiceImpl implements StaffMembersT2Service {

	@Autowired
	private StaffMembersT2Repository staffMembersT2Repository;

	@Override
	public Integer getProfileValueFromSystemProfiles() {
		
		return staffMembersT2Repository.getProfileValueFromSystemProfiles();
	}

}
