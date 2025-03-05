package net.syscon.s4.pkgs.tag_establishment.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentRepository;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;

/*
 * Below comments are copied from package TAG_ESTABLISHMENT.
||    Purpose: This package provides procedures for establishments and living units.
||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
||    --------------------
||    Person              DATE            Version    Comments
||    ---------           ------------    ---------  ----------------------------------------------------------------------------------
||    Srinivas            16-JUL-2013        2.16    HPQC:20398 Modified the Adjust_ocupants procedure to 
||                                                   modifying the main cursor code to resolve ORA-600 error.
||                                                   based on the discussions with Donald Roy 
||    Anand                17-08-2010        2.15    Issue# 3310 Modified PROCEDURE adjust_occupants.
||    Niko                 10-09-2009        2.14    Modified PROCEDURE adjust_occupants to update table - AGENCY_INTERNAL_LOCATIONS
||                                                   WHERE ( no_of_occupant + NVL(p_no_adjustments,0) ) >= 0
||    Niko                 24-08-2009        2.13    Added a new exception - PRAGMA EXCEPTION_INIT (ex_no_available_bed, -20875)
||                                                   to PROCEDURE adjust_occupants
||                                                   Validated living unit availability before updating AGENCY_INTERNAL_LOCATIONS
||    Niko                 23-09-2008        2.12    Added a new procedure - GET_AGENCY-ADDRESS
||                                                   Modified the address select statement
||    Niko                 05-11-2007        2.11    Updated the PROCEDURE adjust_occupants with latest UK version
||    Venu                 13-07-2006        2.10    Defect# 3330: Modified adjust_occupants procedure to lock the record before updating internal locations.
||    D Rice        	    27-Jun-2006   	   2.9     Defect# 2887 - Added AND clause to cursor: vs_agycur in PROCEDURE: default_agency
||					  	                                     so that only ACTIVE agencies are included in the query resultset.
||    Rajshree             29-05-2006           2.8  Changed get_active_agy_loc_desc to function.
||    Rajshree             23-05-2006           2.7  Added get_active_agy_loc_desc.
||    Krishna              16-May-2006          2.6  Added get_area_desc proc.
||    Claus                09-FEB-2006          2.5  Added get_caseload_desc.
||    Rajshree             27-OCT-2005          2.4  Added default_agency procedure.
||    Laurence             22-SEP-2005          2.3  Applied formatting tool.
||                                                   Changed show_version from function to procedure.
||    Laurence             15-SEP-2005          2.2  Added get_int_loc_area_level().
||                                                   Removed grants from script as no longer required.
||    Patrick              25-AUG-2005          2.1  Moved some functions from OIMMHOLO package
||                                                   for sharing:
||                                                   FUNCTION get_living_unit_code ( p_living_unit_id living_units.living_unit_id%TYPE ) RETURN VARCHAR2;
||                                                   FUNCTION get_parent_living_unit_id ( p_living_unit_id living_units.living_unit_id%TYPE ) RETURN NUMBER;
||                                                   FUNCTION is_living_unit_active (p_living_unit_id LIVING_UNITS.LIVING_UNIT_ID%TYPE) RETURN BOOLEAN;
||                                                   FUNCTION get_next_lu_id RETURN living_units.living_unit_id%TYPE;
||                                                   FUNCTION show_version RETURN VARCHAR2;
||    Neil                 03-AUG-2005          2.0  Created the Package..
*/
@Service
public class TagEstablishmentServiceImpl implements TagEstablishmentService {

	@Autowired
	private TagEstablishmentRepository tagEstablishmentRepository;
	final private Logger logger = LogManager.getLogger(TagEstablishmentServiceImpl.class.getName());

	/*
	 * This procedure is migrated from oracle TAG_ESTABLISHMENT
	 * 
	 * @Procedure GET_HOUSING_LABELS to be used for returning p_level_1_code,
	 * p_level_2_code, p_level_3_code, p_level_4_code search results.
	 */
	@Override
	public AgencyLocations getHousingLabels(final String agyLocId) {
		return tagEstablishmentRepository.selectHousingCur(agyLocId);

	}

	@Override
	public String getAgyLocDesc(final String pAgyLocId) {
		return tagEstablishmentRepository.getAgyLocDesc(pAgyLocId);
	}

	@Override
	public String defaultAgency(final String pGlobalCaseloadId) {
		String vAgyLocId = null;
		String vDescription = null;
		Integer vCount;
		final Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			vCount = tagEstablishmentRepository.getCountVsAgycur(pGlobalCaseloadId);
			if (vCount.equals(1)) {
				final AgencyLocations obj = tagEstablishmentRepository.getVsAgyLocCur(pGlobalCaseloadId);
				vAgyLocId = obj.getAgyLocId();
				vDescription = obj.getDescription();
			}
			retMap.put("P_AGY_LOC_ID", vAgyLocId);
			retMap.put("P_DESCRIPTION", vDescription);
		} catch (Exception e) {
			logger.error("defaultAgency :" + e);
		}
		return vAgyLocId;
	}

	@Override
	public String getActiveAgyLocDesc(final String caseloadId) {
		return tagEstablishmentRepository.getActiveAgyLocDesc(caseloadId);
	}
	/*
	 * This procedure is migrated from oracle TAG_ESTABLISHMENT
	 * 
	 * @Procedure ADJUST_OCCUPANTS to be used for update AGENCY_INTERNAL_LOCATIONS
	 * search results.
	 */
	@Override
	public OffenderExternalMovements adjustOccupants(final Integer internalLocationId,final Integer pNoAdjustments,String user) {
		OffenderExternalMovements off=new OffenderExternalMovements();
		List<OffenderExternalMovements> list=tagEstablishmentRepository.lockRecordC(internalLocationId,pNoAdjustments);
		for(OffenderExternalMovements off1:list) {
			off=off1;
		}
		tagEstablishmentRepository.updateAgencyLocations(pNoAdjustments, internalLocationId,user);
		return off;
	}
	
	

}