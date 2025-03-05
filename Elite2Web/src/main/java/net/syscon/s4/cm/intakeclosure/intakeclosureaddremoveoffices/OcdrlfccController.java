package net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices.OcdrlfccService;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.im.beans.OffenderBookingAgyLocsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcdrlfccController {
	@Autowired
	private OcdrlfccService ocdrlfccService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdrlfccController.class.getName());

	/**
	 * getting cgfkOffagy1DspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrlfcc/cgfkOffagy1DspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdrlfccService.cgfkOffagy1DspDescriptionRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocdrlfcc:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrlfcc/offagyExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookingAgyLocs> offagyExecuteQuery(@RequestBody final OffenderBookingAgyLocs searchBean) {
		List<OffenderBookingAgyLocs> searchResult = new ArrayList<>();
		try {
			searchResult = ocdrlfccService.offagyExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocdrlfcc/offagyCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offagyCommit(@RequestBody final OffenderBookingAgyLocsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(username);
		try {
			liReturn = ocdrlfccService.offagyCommit(commitBean);
			if(0 != liReturn ) {
				prosmainService.enableTriggers(commitBean, authorization, "96");
			}
		} catch (Exception e) {
			logger.error("Exception : Ocdrlfcc", e);
		}
		return liReturn;
	}

}