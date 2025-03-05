package net.syscon.s4.pkgs.oms_miscellaneous.impl;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousRepository;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;

/*
 * Below comments are copied from package OMS_MISCELLANEOUS
 * =====================================================================================
   vcp_version  CONSTANT    VARCHAR2(60):='10.2.1.2  17-Jul-2012';
-- =====================================================================================
-- MODIFICATION HISTORY
-- Person        DATE         Version    Comments
-- -----------   ----------   ---------  ----------------------------------------------
-- Himanshu      17-Jul-2012  10.2.1.2   HPQC#3728 Added function Show version and commented procedure show version
-- Niko          22-JAN-09    10.2.1.1   Added a new FUNCTION GET_PROFILE_VALUE
-- Johnny        19-JUN-08    10.2.1.0   Added Fill mode for format mask.
-- SURYA         09-AUG-00    4.9.0.0    Added the Procedure SHOW_VERSION and Added the
--                                       Modification History
-- Girish        06-OCT-00    4.10.0.0   Modified FUNCTION get_agency_location
--
-- Girish        04-DEC-00    4.10.0.0   Modified FUNCTION get_agency_location
--                                       Changed FUNCTION name GET_PROFILE_VAL back to
--                                       GET_PROFILE_VALUE.
--               05-DEC-00    4.10.0.0   Commented a line in Modification history of the
--                                       package specification.
-- Jagdeep       03-APR-01    4.10.0.0   Added commented procedure get_random_offenders
--                                       as requested by Don
-- Vipul         17-APR-01    4.11.0.0   Modified the function GET_AGENCY_LOCATION, removed
--                                       join with Living units for community bookings.
-- Neil          24-FEB-05    10.1.2     Added new version of GetDescCode to get ref code
--                                       when there is a parent.
-- Neil          14-JUN-05    1.3        Added get_movement_reason_desc
-- Rajshree      21-APR-06    2.0        Modified function check_off_book_sched for schedule data model change.
-- Rajshree      09-May-06    2.8        Corrected the version to be inconsistance with PVCS.
-- Rajshree      20-Jul-06    2.9        Changed the length of variable in get_agencu_location function.Defect Id 3407.
-- David Ng      27-Jul-06    2.10       Function Check_off_book_class_progs use offender_program_profiles table instead
-- NIKO          09-FEB-2006  2.10.1.0   Commented out function  f_getphone
-- ====================================================================================================================
--------------------------------------------------------------------------------------------
-- @@@ Edwin Ip 23-SEP-1999                                                               --
--     Get random offenders and inserts into a temporary table for the use of the report. --
--     For New Jersey RANDOM DRUG SELECTION Report.                                       --
-- @@@ Edwin Ip 08-DEC-1999.
--     Modified GET_RANDOM_OFFENDERS to select only ACTIVE-IN offenders.
-- @@@ Jagdeep 03-APR-2001 Please do not delete this commented procedure
 --------------------------------------------------------------------------------------------
*/
@Service
public class OmsMiscellaneousServiceImpl implements OmsMiscellaneousService {

	@Autowired
	private OmsMiscellaneousRepository omsMiscellaneousRepository;
	
	private static Logger logger = LogManager.getLogger(OmsMiscellaneousServiceImpl.class.getName());
    //this method is used  to get ref code when there is a parent.
	@Override
	public String getDescCode(final String areaClass) {
		return omsMiscellaneousRepository.getDescCode(areaClass);
	}

	@Override
	public Map<String, Object> getProfileValues(final String profileType, final String profileCode) {
		final Map<String, Object> returnMap = new HashedMap<String, Object>();
		String profileValue = null;
		String profileValue2 = null;
		final SystemProfiles symProfile = omsMiscellaneousRepository.getProfileValue(profileType, profileCode);
		if (symProfile != null) {
			profileValue = symProfile.getProfileValue();
			profileValue2 = symProfile.getProfileValue2();
		}
		returnMap.put("P_PROFILE_VALUE", profileValue);
		returnMap.put("P_PROFILE_VALUE_2", profileValue2);
		return returnMap;
	}

	@Override
	public String staffName(final Long staffId) {
		return omsMiscellaneousRepository.staffName(staffId);
	}

	@Override
	public String getDescCode(final String domain, final String refCode) {
		return omsMiscellaneousRepository.getDescCode(domain, refCode);
	}
	
	//This method is used for get_profile_value
	@Override
	public String getProfileValue(final SystemProfiles symProfile) {
		SystemProfiles sys = null;
		try {
			////This method is used for get_profile_values
			sys = omsMiscellaneousRepository.getProfileValues(symProfile);
		}catch (Exception e) {
			logger.error("getProfileValues", e);
			return null;
		}
		return sys.getProfileValue();
	}

}