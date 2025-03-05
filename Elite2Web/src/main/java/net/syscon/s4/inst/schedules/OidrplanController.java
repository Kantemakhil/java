package net.syscon.s4.inst.schedules;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OffenderChecklistDetails;
import net.syscon.s4.im.beans.OffenderChecklistDetailsCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ReleasePlans;
import net.syscon.s4.im.beans.ReleasePlansCommitBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.OcuoccupService;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupants;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupantsCommitBean;

@EliteController
public class OidrplanController {
	@Autowired
	private OidrplanService oidrplanService;
	@Autowired
	private OcuoccupService ocuoccupService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrplanController.class.getName());

	/**
	 * getting rgCaseManagers LOV values
	 * 
	 * @param caseLoadId {@link String}
	 * @return a list of the StaffMembers {@link StaffMembers} for the matched
	 *         caseLoadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/rgCaseManagersRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgCaseManagersRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidrplanService.rgCaseManagersRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("rgCaseManagersRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgParoleAgents LOV values
	 * 
	 * @param caseLoadId {@link String}
	 * @return a list of the StaffMembers {@link StaffMembers} for the matched
	 *         caseLoadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/rgParoleAgentsRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgParoleAgentsRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidrplanService.rgParoleAgentsRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("rgParoleAgentsRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgPlanStatus LOV values
	 * 
	 * @return a list of the ReferenceCodes {@link ReferenceCodes}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/rgPlanStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPlanStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oidrplanService.rgPlanStatusRecordGroup(userName);
		} catch (Exception e) {
			logger.error("rgPlanStatusRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgEmploymentStatus LOV values
	 * 
	 * @return a list of the ReferenceCodes {@link ReferenceCodes}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/rgEmploymentStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEmploymentStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidrplanService.rgEmploymentStatusRecordGroup();
		} catch (Exception e) {
			logger.error("rgEmploymentStatusRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgProposedHousing LOV values
	 * 
	 * @param rootOffenderId {@link String}
	 * @return a list of the VAddresses {@link VAddresses} for the matched
	 *         rootOffenderId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/rgProposedHousingRecordGroup", method = RequestMethod.GET)
	public List<VAddresses> rgProposedHousingRecordGroup(
			@RequestParam(value = "rootOffenderId") final String rootOffenderId) {
		List<VAddresses> recordList = new ArrayList<VAddresses>();
		try {
			recordList = oidrplanService.rgProposedHousingRecordGroup(rootOffenderId);
		} catch (Exception e) {
			logger.error("rgProposedHousingRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgProposedEmployment LOV values
	 * 
	 * @param offenderBookId {@link Long}
	 * @return a list of the OffenderEmployments {@link OffenderEmployments} for the
	 *         matched offenderBookId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/rgProposedEmploymentRecordGroup", method = RequestMethod.GET)
	public List<OffenderEmployments> rgProposedEmploymentRecordGroup(
			@RequestParam("offenderBookId") final Long offenderBookId) {
		List<OffenderEmployments> recordList = new ArrayList<OffenderEmployments>();
		try {
			recordList = oidrplanService.rgProposedEmploymentRecordGroup(offenderBookId);
		} catch (Exception e) {
			logger.error("rgProposedEmploymentRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgChecklistAns LOV values
	 * 
	 * @param profileType {@link String}
	 * @return a list of the ProfileCodes {@link ProfileCodes} for the matched
	 *         profileType
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/rgChecklistAnsRecordGroup", method = RequestMethod.GET)
	public List<ProfileCodes> rgChecklistAnsRecordGroup(@RequestParam("profileType") final String profileType) {
		List<ProfileCodes> recordList = new ArrayList<ProfileCodes>();
		try {
			recordList = oidrplanService.rgChecklistAnsRecordGroup(profileType);
		} catch (Exception e) {
			logger.error("rgChecklistAnsRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link ReleasePlans}
	 * @return a list of the InternalScheduleReasons {@link ReleasePlans} for the
	 *         matched ReleasePlans
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/releasePlansExecuteQuery", method = RequestMethod.POST)
	public List<ReleasePlans> releasePlansExecuteQuery(@RequestBody final ReleasePlans searchBean) {
		List<ReleasePlans> searchResult = new ArrayList<>();
		try {
			searchResult = oidrplanService.releasePlansExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("releasePlansExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link ReleasePlansCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching
	 *         passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/releasePlansCommit", method = RequestMethod.POST)
	public @ResponseBody Integer releasePlansCommit(@RequestBody final ReleasePlansCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidrplanService.releasePlansCommit(commitBean);
		} catch (Exception e) {
			logger.error("releasePlansCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffenderChecklistDetails}
	 * @return a list of the OffenderChecklistDetails
	 *         {@link OffenderChecklistDetails} for the matched
	 *         OffenderChecklistDetails
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/offChecklistDetExecuteQuery", method = RequestMethod.POST)
	public List<OffenderChecklistDetails> offChecklistDetExecuteQuery(
			@RequestBody final OffenderChecklistDetails searchBean) {
		List<OffenderChecklistDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oidrplanService.offChecklistDetExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offChecklistDetExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link OffenderChecklistDetailsCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching
	 *         passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/offChecklistDetCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offChecklistDetCommit(
			@RequestBody final OffenderChecklistDetailsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (Optional.ofNullable(commitBean).isPresent()) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidrplanService.offChecklistDetCommit(commitBean);
		} catch (Exception e) {
			logger.error("offChecklistDetCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgCaseManagers LOV values
	 * 
	 * @param releasePlans {@link ReleasePlans}
	 * @return the ReferenceCodes {@link ReferenceCodes} for the matched
	 *         releasePlans
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrplan/rpReadyForApproval", method = RequestMethod.POST)
	public ReferenceCodes rpReadyForApproval(@RequestBody final ReleasePlans releasePlans) {
		ReferenceCodes recordList = null;
		try {
			recordList = oidrplanService.rpReadyForApproval(releasePlans);
		} catch (Exception e) {
			logger.error("rpReadyForApproval", e);
		}
		return recordList;
	}

	// copied from OcuoccupController for security reasons 
	 
	 @PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidrplan/rpOtherOccupantsExecuteQuery", method = RequestMethod.POST)
		public List<RpOtherOccupants> rpOtherOccupantsExecuteQuery(@RequestBody final RpOtherOccupants searchBean) {
			List<RpOtherOccupants> searchResult = new ArrayList<>();
			try {
				searchResult = ocuoccupService.rpOtherOccupantsExecuteQuery(searchBean);
			} catch (Exception e) {
				final RpOtherOccupants bean = new RpOtherOccupants();
				logger.error("rpOtherOccupantsExecuteQuery: ", e);
				searchResult.add(bean);
			}
			return searchResult;
		}

		/**
		 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
		 * database table
		 * 
		 * @Param commitBean
		 */
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidrplan/rpOtherOccupantsCommit", method = RequestMethod.POST)
		public @ResponseBody Integer rpOtherOccupantsCommit(@RequestBody final RpOtherOccupantsCommitBean commitBean) {
			int liReturn = 0;
			try {
				if (commitBean != null) {
					String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
					commitBean.setCreateUserId(userName);
				}
				liReturn = ocuoccupService.rpOtherOccupantsCommit(commitBean);
			} catch (Exception e) {
				logger.error("rpOtherOccupantsCommit: ", e);
			}
			return liReturn;
		}

		/**
		 * getting rgContacted LOV values
		 */
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidrplan/rgContactedRecordGroup", method = RequestMethod.GET)
		public List<ReferenceCodes> rgContactedRecordGroup() {
			List<ReferenceCodes> recordList = new ArrayList<>();
			try {
				recordList = ocuoccupService.rgContactedRecordGroup();
			} catch (Exception e) {
				final ReferenceCodes obj = new ReferenceCodes();
				logger.error("rgContactedRecordGroup: ", e);
				obj.setErrorMessage(e.getMessage());
				recordList.add(obj);
			}
			return recordList;
		}

		/**
		 * getting rgPersonName LOV values
		 */

		@PreAuthorize("hasEliteRole('read')")

		@RequestMapping(value = "/oidrplan/rgPersonNameRecordGroup", method = RequestMethod.GET)
		public List<OffenderContactPersons> rgPersonNameRecordGroup(
				@RequestParam(value = "offenderBookId") final String offenderBookId) {
			List<OffenderContactPersons> recordList = new ArrayList<OffenderContactPersons>();
			try {
				recordList = ocuoccupService.rgPersonNameRecordGroup(offenderBookId);
			} catch (Exception e) {
				final OffenderContactPersons obj = new OffenderContactPersons();
				logger.error("rgPersonNameRecordGroup: ", e);
				recordList.add(obj);
			}
			return recordList;
		}

		/**
		 * getting rgContactTypes LOV values
		 */

		@PreAuthorize("hasEliteRole('read')")

		@RequestMapping(value = "/oidrplan/rgContactTypesRecordGroup", method = RequestMethod.GET)
		public List<ReferenceCodes> rgContactTypesRecordGroup() {
			List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
			try {
				recordList = ocuoccupService.rgContactTypesRecordGroup();
			} catch (Exception e) {
				final ReferenceCodes obj = new ReferenceCodes();
				logger.error("rgContactTypesRecordGroup: ", e);
				obj.setErrorMessage(e.getMessage());
				recordList.add(obj);
			}
			return recordList;
		}

		/**
		 * getting rgRelationships LOV values
		 */

		@PreAuthorize("hasEliteRole('read')")

		@RequestMapping(value = "/oidrplan/rgRelationshipsRecordGroup", method = RequestMethod.GET)
		public List<ReferenceCodes> rgRelationshipsRecordGroup(
				@RequestParam(value = "contactCode") final String contactCode) {
			List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
			try {
				recordList = ocuoccupService.rgRelationshipsRecordGroup(contactCode);
			} catch (Exception e) {
				final ReferenceCodes obj = new ReferenceCodes();
				logger.error("rgRelationshipsRecordGroup: ", e);
				obj.setErrorMessage(e.getMessage());
				recordList.add(obj);
			}
			return recordList;
		}

}