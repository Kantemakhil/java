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
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CommunityHeaderStatuses;
import net.syscon.s4.im.beans.OffenderBookingAgyLocsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * OcdaltccController
 */
@EliteController
public class OcdaltccController {
	@Autowired
	private OcdaltccService ocdaltccService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdaltccController.class.getName());

	/**
	 * getting offenderStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaltcc/offenderStatusRecordGroup", method = RequestMethod.GET)
	public List<CommunityHeaderStatuses> offenderStatusRecordGroup() {
		List<CommunityHeaderStatuses> recordList = new ArrayList<>();
		try {
			recordList = ocdaltccService.offenderStatusRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Ocdaltcc:", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffagy1DspDescription22 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaltcc/cgfkOffagy1DspDescription22RecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffagy1DspDescription22RecordGroup(
			@RequestParam(value = "offenderBookId") final Long offenderBookId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = ocdaltccService.cgfkOffagy1DspDescription22RecordGroup(offenderBookId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Ocdaltcc:", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting cgfkOffagy1DspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaltcc/cgfkOffagy1DspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdaltccService.cgfkOffagy1DspDescriptionRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Ocdaltcc:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaltcc/offagyExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookingAgyLocs> offagyExecuteQuery(@RequestBody final OffenderBookingAgyLocs searchBean) {
		List<OffenderBookingAgyLocs> searchResult = new ArrayList<>();
		try {
			searchResult = ocdaltccService.offagyExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderBookingAgyLocs bean = new OffenderBookingAgyLocs();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocdaltcc/offagyCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offagyCommit(@RequestBody final OffenderBookingAgyLocsCommitBean commitBean,  @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(username);
		try {
			liReturn = ocdaltccService.offagyCommit(commitBean);
			if(0 != liReturn ) {
				prosmainService.enableTriggers(commitBean, authorization, "89");
			}
		} catch (final Exception e) {
			logger.error("Exception : Ocdaltcc", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaltcc/evntDate", method = RequestMethod.POST)
	public OffenderBookingEvent evntDate(@RequestBody final OffenderBookingAgyLocs searchBean) {
		OffenderBookingEvent searchResult = new OffenderBookingEvent();
		try {
			searchResult = ocdaltccService.evntDate(searchBean);
		} catch (final Exception e) {
			logger.error("evntDate :", e);
		}
		return searchResult;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdaltcc/offagy1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookingAgyLocs> offagy1ExecuteQuery(@RequestBody final OffenderBookingAgyLocs searchBean) {
		List<OffenderBookingAgyLocs> searchResult = new ArrayList<>();
		try {
			searchResult = ocdaltccService.offagy1ExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderBookingAgyLocs bean = new OffenderBookingAgyLocs();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

}