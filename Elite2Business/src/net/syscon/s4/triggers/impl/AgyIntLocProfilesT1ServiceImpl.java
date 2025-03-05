package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.triggers.AgyIntLocAmendments;
import net.syscon.s4.triggers.AgyIntLocProfilesT1Repository;
import net.syscon.s4.triggers.AgyIntLocProfilesT1Service;

@Service
public class AgyIntLocProfilesT1ServiceImpl implements AgyIntLocProfilesT1Service {
	@Autowired
	AgyIntLocProfilesT1Repository agyIntLocProfilesT1Repository;
	@Autowired
	OmsMiscellaneousService omsMiscellaneousService;

	// checking the presence of data
	@Override
	public Boolean isChanged(final String pOldVal, final String pNewVal) {
		if (pOldVal == null && Optional.ofNullable(pNewVal).isPresent()) {
			return true;
		}
		if (pNewVal == null && Optional.ofNullable(pOldVal).isPresent()) {
			return true;
		}
		if (pNewVal != pOldVal) {
			return true;
		}
		return false;
	}

	// Below method is call after insert or delete or update on AGY_INT_LOC_PROFILES table
	@Override
	public Integer agyIntLocProfilesT1Trigger(final String operationType, final AgyIntLocAmendments oldRef,
			final AgyIntLocAmendments newRef) {
		Integer result = 0;
		AgyIntLocAmendments inputobj = new AgyIntLocAmendments();
		if ("INSERTING".equals(operationType)) {
			inputobj = new AgyIntLocAmendments();
			inputobj.setInternalLocationId(newRef.getInternalLocationId());
			inputobj.setAmendUserId(newRef.getAmendUserId());
			inputobj.setActionCode("A");
			// To return the description of a type of code from REFERENCE_CODES table.
			String getDescOde = omsMiscellaneousService.getDescCode("IL_PROF", newRef.getIntLocProfileType());
			if (getDescOde != null) {
				inputobj.setColumnName(getDescOde);
			}
			// To return the description of a type of code from REFERENCE_CODES table.
			String getDescOde1 = omsMiscellaneousService.getDescCode(newRef.getIntLocProfileType(),
					newRef.getIntLocProfileCode());
			if (getDescOde1 != null) {
				inputobj.setNewValue(getDescOde1);
			}
			// used to inserting record into agy_int_loc_amendments table
			result = agyIntLocProfilesT1Repository.insertAgyIntLocAmendments(inputobj);
		}
		if ("UPDATING".equals(operationType)) {
			if (isChanged(oldRef.getIntLocProfileCode(), newRef.getIntLocProfileCode())) {
				inputobj = new AgyIntLocAmendments();
				inputobj.setInternalLocationId(newRef.getInternalLocationId());
				inputobj.setAmendUserId(newRef.getAmendUserId());
				inputobj.setActionCode("U");
				// To return the description of a type of code from REFERENCE_CODES table.
				inputobj.setColumnName(omsMiscellaneousService.getDescCode("IL_PROF", newRef.getIntLocProfileType()));
				// To return the description of a type of code from REFERENCE_CODES table.
				inputobj.setOldValue(omsMiscellaneousService.getDescCode(newRef.getIntLocProfileType(),
						oldRef.getIntLocProfileCode()));
				// To return the description of a type of code from REFERENCE_CODES table.
				inputobj.setNewValue(omsMiscellaneousService.getDescCode(newRef.getIntLocProfileType(),
						newRef.getIntLocProfileCode()));
				// used to inserting record into agy_int_loc_amendments table
				result = agyIntLocProfilesT1Repository.insertAgyIntLocAmendments(inputobj);
			}

		}
		if ("DELETING".equals(operationType)) {
			inputobj = new AgyIntLocAmendments();
			inputobj.setInternalLocationId(oldRef.getInternalLocationId());
			inputobj.setAmendUserId(oldRef.getAmendUserId());
			inputobj.setActionCode("D");
			// To return the description of a type of code from REFERENCE_CODES table.
			inputobj.setColumnName(omsMiscellaneousService.getDescCode("IL_PROF", oldRef.getIntLocProfileType()));
			inputobj.setOldValue(
					// To return the description of a type of code from REFERENCE_CODES table.
					omsMiscellaneousService.getDescCode(oldRef.getIntLocProfileType(), oldRef.getIntLocProfileCode()));
			// used to inserting record into agy_int_loc_amendments table
			result = agyIntLocProfilesT1Repository.insertAgyIntLocAmendments(inputobj);
		}
		return result;
	}

}
