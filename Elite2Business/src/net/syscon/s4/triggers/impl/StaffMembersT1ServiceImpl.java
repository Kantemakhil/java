package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.StaffMembersT1Repository;
import net.syscon.s4.triggers.StaffMembersT1Service;
@Service
public class StaffMembersT1ServiceImpl implements StaffMembersT1Service {

	@Autowired
	private StaffMembersT1Repository staffMembersT1Repository;

	public Integer StaffMembersT1Trigger(Integer staffId) throws CustomException {
		Integer vNumRows;

		vNumRows = staffMembersT1Repository.getCountAddresses(staffId);
		if (vNumRows > 0) {
			throw new CustomException("Cannot DELETE the staff member record because staff address records exists.");
		}
		vNumRows = staffMembersT1Repository.getCountPhones(staffId);
		if (vNumRows > 0) {
			throw new CustomException("Cannot DELETE the staff member record because staff phone records exists.");
		}
		vNumRows = staffMembersT1Repository.getCountInternetAddresses(staffId);
		if (vNumRows > 0) {
			throw new CustomException(
					"Cannot DELETE the staff member record because staff internet addresses records exists.");
		}
		return vNumRows;

	}

}
