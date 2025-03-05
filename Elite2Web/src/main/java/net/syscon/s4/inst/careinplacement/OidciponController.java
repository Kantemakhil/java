package net.syscon.s4.inst.careinplacement;

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
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetailsCommitBean;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OidciponController
 */
@EliteController
public class OidciponController {

	@Autowired
	private OidciponService oidciponService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidciponController.class.getName());

	/**
	 * getting rgPlacementReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/rgPlacementReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPlacementReasonRecordGroup(
			@RequestParam(value = "placementType") final String placementType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidciponService.rgPlacementReasonRecordGroup(placementType);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgPlacementReasonRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgPlacementType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/rgPlacementTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPlacementTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidciponService.rgPlacementTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgPlacementTypeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocs LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/rgAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocsRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidciponService.rgAgyLocsRecordGroup(caseloadId);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In rgAgyLocsRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgRequestedBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/rgRequestedByRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRequestedByRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidciponService.rgRequestedByRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgRequestedByRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgAuthorizedBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/rgAuthorizedByRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAuthorizedByRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidciponService.rgAuthorizedByRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgAuthorizedByRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgDurationType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/rgDurationTypeRecordGroup", method = RequestMethod.GET)
	public List<PlacementDurations> rgDurationTypeRecordGroup(
			@RequestParam(value = "placementType") final String placementType) {
		List<PlacementDurations> recordList = new ArrayList<PlacementDurations>();
		try {
			recordList = oidciponService.rgDurationTypeRecordGroup(placementType);
		} catch (Exception e) {
			final PlacementDurations obj = new PlacementDurations();
			logger.error("In rgDurationTypeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgReleasedBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/rgReleasedByRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReleasedByRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidciponService.rgReleasedByRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgReleasedByRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/offCipDetailsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCipDetails> offCipDetailsExecuteQuery(@RequestBody final OffenderCipDetails searchBean) {
		List<OffenderCipDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oidciponService.offCipDetailsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderCipDetails bean = new OffenderCipDetails();
			logger.error("In offCipDetailsExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcipon/offCipDetailsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCipDetailsCommit(@RequestBody final OffenderCipDetailsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidciponService.offCipDetailsCommit(commitBean);
			if(liReturn == 1) {
				prosmainService.enableTriggers(commitBean, authorization, "133");
			}
		} catch (Exception e) {
			logger.error("In offCipDetailsCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidciponService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("In sysPflExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/dtValidationForInactiveOff", method = RequestMethod.POST)
	public String dtValidationForInactiveOff(@RequestBody final OffenderExternalMovements searchBean) {
		String strMsg = null;
		try {
			strMsg = oidciponService.dtValidationForInactiveOff(searchBean);
		} catch (Exception e) {
			final OffenderExternalMovements bean = new OffenderExternalMovements();
			logger.error("In OffenderExternalMovements method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return strMsg;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/defaultDurationType", method = RequestMethod.POST)
	public List<PlacementDurations> defaultDurationType(@RequestBody final PlacementDurations searchBean) {
		List<PlacementDurations> lstPlacements = new ArrayList<PlacementDurations>();
		try {
			lstPlacements = oidciponService.defaultDurationType(searchBean);
		} catch (Exception e) {
			final PlacementDurations bean = new PlacementDurations();
			logger.error("In defaultDurationType method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return lstPlacements;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcipon/checkDate", method = RequestMethod.POST)
	public Integer checkDate(@RequestBody final VHeaderBlock searchBean) {
		Integer inValue = 0;
		try {
			inValue = oidciponService.checkDate(searchBean);
		} catch (Exception e) {
			final OffenderExternalMovements bean = new OffenderExternalMovements();
			logger.error("In checkDate method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return inValue;
	}

}