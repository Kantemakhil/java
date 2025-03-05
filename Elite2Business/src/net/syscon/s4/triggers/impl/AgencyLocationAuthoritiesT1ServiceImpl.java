package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.pkgs.oumagyht.impl.OumagyhtPkgServiceImpl;
import net.syscon.s4.triggers.AgencyLocationAuthoritiesT1Service;

@Service
public class AgencyLocationAuthoritiesT1ServiceImpl implements AgencyLocationAuthoritiesT1Service {
	@Autowired
	OumagyhtPkgServiceImpl oumagyhtServiceImpl;

	@Override
	public Integer agencyLocationAuthoritiesT1Trigger(final AgencyLocations oldRef, final AgencyLocations newRef,
			final String operationType) {
		Integer result = 0;
		AgencyLocationAmendments agyLocnAmendments = new AgencyLocationAmendments();
		if (oumagyhtServiceImpl.checkChanged(oldRef.getAgyLocId(), newRef.getAgyLocId())
				&& "INSERTING".equals(operationType) && "DELETING".equals(operationType)) {
			agyLocnAmendments = new AgencyLocationAmendments();
			agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			agyLocnAmendments.setpColName("AGY_LOC_ID");
			agyLocnAmendments.setpOldValue(oldRef.getAgyLocId());
			agyLocnAmendments.setpNewValue(newRef.getAgyLocId());
			agyLocnAmendments.setpDomain("");
			agyLocnAmendments.setpDescType("");
			result = oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		if (oumagyhtServiceImpl.checkChanged(oldRef.getLocalAuthorityCode(), newRef.getLocalAuthorityCode())
				|| "INSERTING".equals(operationType) || "DELETING".equals(operationType)) {
			agyLocnAmendments = new AgencyLocationAmendments();

			agyLocnAmendments.setpColName("LOCAL_AUTHORITY_CODE");
			agyLocnAmendments.setpOldValue(oldRef.getLocalAuthorityCode());
			agyLocnAmendments.setpNewValue(newRef.getLocalAuthorityCode());
			agyLocnAmendments.setpDomain("LOCAL_AUTH");
			agyLocnAmendments.setpDescType("REF_CODE");
			if ("INSERTING".equals(operationType)) {
				agyLocnAmendments.setLvAgyLocId(newRef.getAgyLocId());
			} else if ("UPDATING".equals(operationType) || "DELETING".equals(operationType)) {
				agyLocnAmendments.setLvAgyLocId(oldRef.getAgyLocId());
			}
			result = oumagyhtServiceImpl.insertIntoAgyLocAmendments(agyLocnAmendments);
		}
		return result;
	}

}
