package net.syscon.s4.iwp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.TransferBWOfficerCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * OcdtapowController
 */
@EliteController
public class OcdtapowController {
	@Autowired
	private OcdtapowService ocdtapowService;
	@Autowired
	private ProsmainService prosmainService;
	static final String HASELITEROLE = "hasEliteRole('read')";
	static final String EXCEPTION = "Exception :";
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdtapowController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/ocdtapow/staffLrExecuteQuery", method = RequestMethod.POST)
	public List<StaffLocationRoles> staffLrExecuteQuery(@RequestBody final StaffLocationRoles searchBean) {
		List<StaffLocationRoles> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = ocdtapowService.staffLrExecuteQuery(searchBean);
		} catch (final Exception e) {
			final StaffLocationRoles bean = new StaffLocationRoles();
			logger.error(EXCEPTION, e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	

	/**
	 * getting cgfkStaffLrDspDescription LOV values
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/ocdtapow/cgfkStaffLrDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkStaffLrDspDescriptionRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdtapowService.cgfkStaffLrDspDescriptionRecordGroup(caseloadId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error(EXCEPTION, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVOffDetSkillType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdtapow/cgfkVOffDetSkillTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkVOffDetSkillTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtapowService.cgfkVOffDetSkillTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(EXCEPTION, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVOffDetSkillSubType LOV values
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/ocdtapow/cgfkVOffDetSkillSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkVOffDetSkillSubTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdtapowService.cgfkVOffDetSkillSubTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(EXCEPTION, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkStaffLrDspLastName LOV values
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/ocdtapow/cgfkStaffLrDspLastNameRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdtapowService.cgfkStaffLrDspLastNameRecordGroup();
		} catch (final Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error(EXCEPTION, e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize(HASELITEROLE)
	@RequestMapping(value = "/ocdtapow/offBkg1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBkg1ExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = ocdtapowService.offBkg1ExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderBookings bean = new OffenderBookings();
			logger.error(EXCEPTION, e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdtapow/saveData", method = RequestMethod.POST)
	public @ResponseBody TransferBWOfficerCommitBean offBkg1Commit(
			@RequestBody final TransferBWOfficerCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		TransferBWOfficerCommitBean liReturn = null;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdtapowService.saveData(commitBean);
			if(liReturn != null) {
				prosmainService.enableTriggers(commitBean, authorization, "13");
			}
		} catch (final Exception e) {

			logger.error(EXCEPTION, e);
		}
		return liReturn;
	}

}