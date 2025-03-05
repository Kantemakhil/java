package net.syscon.s4.pkgs.oumagyht.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousRepository;
import net.syscon.s4.pkgs.oumagyht.OumagyhtPkgRepository;
import net.syscon.s4.pkgs.oumagyht.OumagyhtPkgService;
/*
 * Below comments are copied from package OUMAGYHT 
 * supports various functions for AGENCY_LOCATIONS_T1 trigger -- For keeping the
 * history of the AGENCY_LOCATIONS table. -- -- MODIFICATION HISTORY -- Person
 * Date Comments -- --------- -----------
 * ------------------------------------------ -- GJC 14 Oct 2006 2.1
 * SHOW_VERSION changed from procedure to function -- Patrick 27-JUN-2005 1.2
 * Added function get_address_owner_code -- Patrick 17-JUN-2005 Initial Version
 * 
 */
@Service
public class OumagyhtPkgServiceImpl extends RepositoryBase implements OumagyhtPkgService {
	@Autowired
	private OumagyhtPkgRepository oumagyhtRepository;
	@Autowired
	@Qualifier("OmsMiscellaneous_mg")
	private OmsMiscellaneousRepository omsMiscellaneousRepository;

	@Override
	public Integer insertIntoAgyLocAmendments(final AgencyLocationAmendments agyLocAmendments) {
		String lvNewValue;
		String lvOldValue;
		if ("REF_CODE".equals(agyLocAmendments.getpDescType())) {
			lvNewValue = omsMiscellaneousRepository.getDescCode(agyLocAmendments.getpDomain(),
					agyLocAmendments.getpNewValue());
			lvOldValue = omsMiscellaneousRepository.getDescCode(agyLocAmendments.getpDomain(),
					agyLocAmendments.getpOldValue());
		} else if ("DATE".equals(agyLocAmendments.getpDescType())) {
			lvNewValue = agyLocAmendments.getpNewValue(); 
			lvOldValue = agyLocAmendments.getpOldValue(); 
		} else {
			lvNewValue = agyLocAmendments.getpNewValue();
			lvOldValue = agyLocAmendments.getpOldValue();
		}
		agyLocAmendments.setLvNewValue(lvNewValue);
		agyLocAmendments.setLvOldValue(lvOldValue);
		agyLocAmendments.setOriginalValue(lvOldValue != null ? lvOldValue : agyLocAmendments.getpOldValue());
		agyLocAmendments.setNewValue(lvNewValue != null ? lvNewValue : agyLocAmendments.getpNewValue());
		agyLocAmendments.setLvAgyLocId(agyLocAmendments.getLvAgyLocId());
		return oumagyhtRepository.insertIntoAgyLocAmendments(agyLocAmendments);
	}

	@Override
	public Boolean checkChanged(final String pOldValue, final String pNewValue) {
		if ((Optional.ofNullable(pOldValue).isPresent() && pNewValue == null)
				|| (Optional.ofNullable(pNewValue).isPresent() && pOldValue == null)) {
			return true;
		}
		else if ((pNewValue!= null &&pOldValue!=null) && !pNewValue.equals(pOldValue)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public String getAddressOwnerCode(final Long pAddrId) {
		String result = null;
		final List<Addresses> getAgyLocIdCur = oumagyhtRepository.getAddressOwnerCode(pAddrId);
		if (!getAgyLocIdCur.isEmpty()) {
			for (final Addresses obj : getAgyLocIdCur) {
				if (Optional.ofNullable(obj).isPresent() && Optional.ofNullable(obj.getOwnerCode()).isPresent()) {
					result = obj.getOwnerCode();
				}
			}
		}
		return result;
	}


}