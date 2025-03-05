package net.syscon.s4.cm.intakeclosure;

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
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookingEventCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcdcloseServiceImpl
 */
@EliteController
public class OcdcloseController {
	@Autowired
	private OcdcloseService ocdcloseService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcloseController.class.getName());

	/**
	 * getting cgfkObeDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclose/cgfkObeDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkObeDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdcloseService.cgfkObeDspDescriptionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkObeDspDescriptionRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkObeDspDescription2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclose/agencyLocationRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> agencyLocationRecordGroup(
			@RequestParam(value = "offenderBookId") final Integer offenderBookId,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = ocdcloseService.agencyLocationRecordGroup(offenderBookId, caseloadId);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : agencyLocationRecordGroup:", e);
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
	@RequestMapping(value = "/ocdclose/obeExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookingEvent> obeExecuteQuery(@RequestBody final OffenderBookingEvent searchBean) {
		List<OffenderBookingEvent> searchResult = new ArrayList<>();
		try {
			searchResult = ocdcloseService.obeExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderBookingEvent bean = new OffenderBookingEvent();
			logger.error("Exception : obeExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
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
	@RequestMapping(value = "/ocdclose/obeCommit", method = RequestMethod.POST)
	public @ResponseBody Integer obeCommit(@RequestBody final OffenderBookingEventCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
        List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(username);
		try {
			liReturn = ocdcloseService.obeCommit(commitBean, authorization);
			if(1==liReturn) {
				ocdcloseService.updateSentStatus(commitBean.getInsertList().get(0));
				prosmainService.enableTriggers(commitBean, authorization, "7");
			}
		} catch (Exception e) {
			logger.error("Exception : obeCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclose/checkInstitution", method = RequestMethod.POST)
	public Integer checkInstitution(@RequestBody final OffenderBookingEvent searchBean) {
		int liReturn = 0;
		try {
			liReturn = ocdcloseService.checkInstitution(searchBean);
		} catch (Exception e) {
			logger.error("Exception : checkInstitution", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclose/checkMultyCaseload", method = RequestMethod.POST)
	public Integer checkMultyCaseload(@RequestBody final OffenderBookingEvent searchBean) {
		int liReturn = 0;
		try {
			liReturn = ocdcloseService.checkMultyCaseload(searchBean);
		} catch (Exception e) {
			logger.error("Exception : checkMultyCaseload", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclose/getBokingBeginDate", method = RequestMethod.POST)
	public OffenderBookingEvent getBokingBeginDate(@RequestBody final VHeaderBlock searchBean) {
		OffenderBookingEvent returnDate = new OffenderBookingEvent();
		try {
			returnDate = ocdcloseService.getBokingBeginDate(searchBean);
		} catch (Exception e) {
			logger.error("Exception : getBokingBeginDate", e);
		}
		return returnDate;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclose/preInsert", method = RequestMethod.POST)
	public Integer preInsert(@RequestBody final OffenderBookingEvent searchBean) {
		Integer returnDate = null;
		final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(user);
		try {
			returnDate = ocdcloseService.obePreInsertTwo(searchBean);
		} catch (Exception e) {
			logger.error("Exception : preInsert", e);
		}
		return returnDate;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclose/tagTerminationChkTasks", method = RequestMethod.POST)
	public Integer tagTerminationChkTasks(@RequestBody final OffenderBookingEvent searchBean) {
		Integer count = 0;
		try {
			count = ocdcloseService.tagTerminationChkTasks(searchBean);
		} catch (Exception e) {
			logger.error("Exception : tagTerminationChkTasks", e);
		}
		return count;
	}
	
	/**
	 *Check for Active Orders / Court Reports present
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclose/isActiveOrderPresent", method = RequestMethod.GET)
	public Boolean isActiveOrderPresent(@RequestParam final Integer offenderBookId) {
		Boolean isChargeLinked = false;
		try {
			isChargeLinked = ocdcloseService.isActiveOrderPresent(offenderBookId);
		} catch (Exception e) {
			logger.error("isActiveOrderPresent", e);
		}
		return isChargeLinked;
	}

}