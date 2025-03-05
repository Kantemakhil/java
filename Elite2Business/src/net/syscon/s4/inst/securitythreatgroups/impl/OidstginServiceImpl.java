package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VStgSet;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentPartiesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentsCommitBean;
import net.syscon.s4.inst.securitythreatgroups.OidstginRepository;
import net.syscon.s4.inst.securitythreatgroups.OidstginService;

/**
 * Class OidstginServiceImpl
 */
@Service
public class OidstginServiceImpl extends BaseBusiness implements OidstginService {

	@Autowired
	private OidstginRepository oidstginRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<StaffMembers> populateStaff(final StaffMembers paramBean) {
		final List<StaffMembers> staffMembersList = oidstginRepository.populateStaff(paramBean);
		return staffMembersList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<AgencyIncidents> agyIncExecuteQuery(final AgencyIncidents searchRecord) {
		final List<AgencyIncidents> returnList = oidstginRepository.agyIncExecuteQuery(searchRecord);
		returnList.forEach(action -> {
			if (action.getAgyLocId() != null) {
				final String facility = oidstginRepository.tagEstablishMent(action.getAgyLocId());
				if (facility != null) {
					action.setAgyLocId(facility);
				}
			}
			if (action.getInternalLocationId() != null) {
				final AgencyInternalLocations location = oidstginRepository
						.getInternalLocations(action.getInternalLocationId());
				if (location != null) {
					action.setLevelCode(location.getDescription());
				}
			}
			if (action.getReportedStaffId() != null) {
				final String reportBy = oidstginRepository.staffMembers(action.getReportedStaffId());
				if (reportBy != null) {
					action.setReportStaffIdAsCode(reportBy);
				}
			}
		});
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_INC
	 *
	 * 
	 */
	@Transactional
	public Integer agyIncCommit(final AgencyIncidentsCommitBean commitBean) {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<AgencyIncidentParties> agencyIncidentPartiesExecuteQuery(final AgencyIncidentParties searchRecord) {
		final List<AgencyIncidentParties> returnList = oidstginRepository
				.agencyIncidentPartiesExecuteQuery(searchRecord);
		returnList.stream().forEach(data -> {
			final Offenders retval = oidstginRepository.agencyIncidentPartiesPostQuery(data.getOffenderBookId());
			if (retval != null) {
				data.setOffenderIdDisplay(retval.getOffenderIdDisplay());
				data.setLname(retval.getLastName());
			}
			final BigDecimal stgId = oidstginRepository.getDescriptionStgId(searchRecord.getStgId(),
					data.getOffenderBookId());
			if (stgId != null) {
				final String description = oidstginRepository.getDescription(stgId);
				if (description != null) {
					data.setDescription(description);
				}
			} else {
				final BigDecimal stgIdOne = oidstginRepository
						.getDescriptionStgIdElseCondition(data.getOffenderBookId());
				if (stgIdOne != null) {
					final String description = oidstginRepository.getDescription(stgIdOne);
					if (description != null) {
						data.setDescription(description);
					}
				}
			}
		});

		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_INCIDENT_PARTIES
	 *
	 * 
	 */
	@Transactional
	public Integer agencyIncidentPartiesCommit(final AgencyIncidentPartiesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param paramBean
	 *
	 * 
	 */
	public List<VStgSet> populateStgGroup(final VStgSet paramBean) {
		return oidstginRepository.populateStgGroup(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param ofenderIdDisplay
	 *
	 * 
	 */
	public String offenderIdDisplay(final BigDecimal ofenderIdDisplay,String userId) {
		return oidstginRepository.offenderIdDisplay(ofenderIdDisplay,userId);
	}

}