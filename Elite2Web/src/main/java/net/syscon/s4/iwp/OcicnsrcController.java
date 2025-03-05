package net.syscon.s4.iwp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;

@EliteController
public class OcicnsrcController {

	private static Logger logger = LogManager.getLogger(OcicnsrcController.class.getName());

	@Autowired
	private OcicnsrcService ocicnsrcService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocicnsrc/staffmembersRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> staffmembersRecordGroup(@RequestParam("caseLoadType") String caseLoadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocicnsrcService.getAllStaffNamesByCaseload(caseLoadType);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in  staffmembersRecordGroup " + e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocicnsrc/facilitiesList", method = RequestMethod.GET)
	public List<ReferenceCodes> getAllFacilites(@RequestParam("caseLoadType") String caseLoadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocicnsrcService.getAllFacilities(caseLoadType);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in  getAllFacilites " + e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocicnsrc/casenoteexecuteQuery", method = RequestMethod.POST)
	public List<OffenderCaseNotes> casenoteexecuteQuery(@RequestBody final OffenderCaseNotes searchBean) {
		List<OffenderCaseNotes> recordList = new ArrayList<OffenderCaseNotes>();
		try {
			recordList = ocicnsrcService.casenoteexecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in  casenoteexecuteQuery " + e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocicnsrc/getStaffId", method = RequestMethod.GET)
	public Integer getStaffId() {
		Integer staffId = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			staffId = ocicnsrcService.getStaffId(userName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getStaffId " + e);
		}
		return staffId;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocicnsrc/checkPermisionForLov", method = RequestMethod.GET)
	public Boolean checkPermisionForLov() {
		Boolean permision = true;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			permision = ocicnsrcService.checkPermisionForLov(userName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getStaffId " + e);
		}
		return permision;
	}

}
