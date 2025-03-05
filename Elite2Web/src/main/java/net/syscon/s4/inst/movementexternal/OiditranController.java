package net.syscon.s4.inst.movementexternal;

import java.math.BigDecimal;
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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VOffExm;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisService;
import net.syscon.s4.inst.movements.housingchanges.OidchlocService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OiditranController {
	@Autowired(required = true)
	private OiditranService oiditranService;
	
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private OidadmisService oidadmisService;
	
	@Autowired
	private OidchlocService oidchlocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiditranController.class.getName());

	/**
	 * getting moveRsnLov LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/moveRsnLovRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> moveRsnLovRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiditranService.moveRsnLovRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method moveRsnLovRecordGroup" + e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmToAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/cgfkOffEmToAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<CaseloadAdmOtherProfiles> cgfkOffEmToAgyLocIdRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<CaseloadAdmOtherProfiles> recordList = new ArrayList<CaseloadAdmOtherProfiles>();
		try {
			recordList = oiditranService.cgfkOffEmToAgyLocIdRecordGroup(caseloadId);
		} catch (Exception e) {
			final CaseloadAdmOtherProfiles obj = new CaseloadAdmOtherProfiles();
			logger.error("In method cgfkOffEmToAgyLocIdRecordGroup" + e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/offEmExecuteQueryVoffExm", method = RequestMethod.GET)
	public List<VOffExm> offEmExecuteQuery(@RequestParam(value = "caseloadId") final String caseloadId) {

		List<VOffExm> searchResult = new ArrayList<>();
		try {
			searchResult = oiditranService.offEmExecuteQuery(caseloadId);
		} catch (Exception e) {
			final VOffExm bean = new VOffExm();
			logger.error("In method offEmExecuteQuery" + e);
			bean.setErrorMessage(e.getMessage());
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiditran/offEmCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEmCommit(@RequestBody final OffenderExternalMovementsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			
			liReturn = oiditranService.offEmCommit(commitBean);
			if(1 == liReturn) {
				commitBean.setInsertList(null);
				prosmainService.enableTriggers(commitBean, authorization, "69");
			}
		} catch (Exception e) {
			logger.error("In method offEmCommit" + e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/findToAgyLocIdList", method = RequestMethod.GET)
	public @ResponseBody List<String> findToAgyLocIdList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiditranService.findToAgyLocIdList();
		} catch (Exception e) {
			logger.error("In method findToAgyLocIdList" + e);
		}
		return listOfRecords;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. update the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiditran/offBkgCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offBkgCommit(@RequestBody final List<OffenderBookings> commitBean) {
		int liReturn = 0;
		try {
			liReturn = oiditranService.offBkgCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method offBkgCommit" + e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/getCountOfAgyInCase", method = RequestMethod.GET)
	public CaseloadAdmOtherProfiles getCountOfAgyInCase(@RequestParam(value = "caseloadId") final String caseloadId) {

		CaseloadAdmOtherProfiles searchResult = new CaseloadAdmOtherProfiles();
		try {
			searchResult = oiditranService.getCountOfAgyInCase(caseloadId);
		} catch (Exception e) {
			logger.error("In method getCountOfAgyInCase" + e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/noOfBedAvailableInTheGivenLocation", method = RequestMethod.GET)
	public Integer noOfBedAvailableInTheGivenLocation(
			@RequestParam(value = "livingUnitId") final BigDecimal living_unit_id) {

		Integer availabelBeds = 0;
		try {
			availabelBeds = oiditranService.noOfBedAvailableInTheGivenLocation(living_unit_id);
		} catch (Exception e) {
			logger.error("In method noOfBedAvailableInTheGivenLocation() " + e);
		}
		return availabelBeds;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/cgfkOffEmDspDescriptionAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEmDspDescriptionAgyLocIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidadmisService.cgfkOffEmDspDescriptionAgyLocIdRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescriptionAgyLocIdRecordGroup", e);
		}
		return recordList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/checkNonIndGangConficts", method = RequestMethod.POST)
	public List<BedAssignmentHistories> checkNonIndGangConficts(@RequestBody final List<BedAssignmentHistories> searchList) {
		List<BedAssignmentHistories> searchResult = new ArrayList<>();
		try {
			searchResult = oidchlocService.checkNonIndGangConficts(searchList);
		} catch (Exception e) {
			logger.error("net.syscon.s4.inst.movementexternal.OiditranController.checkNonIndGangConficts", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiditran/checkAllConficts", method = RequestMethod.POST)
	public BedAssignmentHistories checkAllConficts(@RequestBody final BedAssignmentHistories searchBean) {
		BedAssignmentHistories searchResult = new BedAssignmentHistories();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidchlocService.checkAllConficts(searchBean);
		} catch (Exception e) {
			logger.error("net.syscon.s4.inst.movementexternal.OiditranController.checkAllConficts()", e);
		}
		return searchResult;
	}
}