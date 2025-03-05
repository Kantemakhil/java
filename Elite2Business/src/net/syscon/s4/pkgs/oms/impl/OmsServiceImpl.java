package net.syscon.s4.pkgs.oms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.pkgs.oms.OmsRepository;
import net.syscon.s4.pkgs.oms.OmsService;

/*
 * Below comments are copied from package OMS
 * Purpose: General Procedures and Functions for OMS system
--

-- MODIFICATION HISTORY
-- Person        DATE         Version     Comments
-- -----------   ----------   ----------  ----------------------------------------------------
-- GJC         14-Oct-06  Remove DBMS_OUTPUT Calls
-- David Ng      1999/02/17               First Draft
-- PWONG         01-24-2000               Add the exception handler to function System_Profile
-- jal           26may00                  added GET_VERSION(), SHOW_VERSION()
-- SURYA         09-AUG-00    4.9.0.0     Commented GET_VERSION(), Modified SHOW_VERSION() and
--                                        Modified the Modification History
*/
@Service
public class OmsServiceImpl implements OmsService {
	@Autowired
	private OmsRepository omsRepository;

	// This FUNCTION System_Profile is migrated from oracle
	@Override
	public String systemProfile(final String statusValue) {
		// This method is migrated from oracle
		List<SystemProfiles> list = omsRepository.getResults("CLIENT", "B/C_STATUS");
		String vProfileValue = null, vProfileValue2 = null;
		for (SystemProfiles obj : list) {
			vProfileValue = (String) obj.getProfileValue();
			vProfileValue2 = (String) obj.getProfileValue2();
		}
		if (statusValue.equals("1"))
			return vProfileValue;
		else if (statusValue.equals("2"))
			return vProfileValue2;
		else
			return null;

	}

}