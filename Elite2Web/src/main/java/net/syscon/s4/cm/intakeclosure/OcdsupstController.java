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

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.OffSupervisionStsHty;
import net.syscon.s4.common.beans.OffSupervisionStsHtyCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
//import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * OcdsupstController
 */
@EliteController
public class OcdsupstController {
	@Autowired
	private OcdsupstService ocdsupstService;
//	@Autowired
//	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdsupstController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsupst/offBkgAgyLocExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookingAgyLocs> offBkgAgyLocExecuteQuery(@RequestBody final OffenderBookingAgyLocs searchBean) {
		List<OffenderBookingAgyLocs> searchResult = new ArrayList<>();
		try {
			searchResult = ocdsupstService.offBkgAgyLocExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderBookingAgyLocs bean = new OffenderBookingAgyLocs();
			logger.error("Exception in offBkgAgyLocExecuteQuery:", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsupst/getProfileValue", method = RequestMethod.GET)
	public String getProfileValue() {
		String profileValue = null;
		try {
			profileValue = ocdsupstService.getProfileValue();
		} catch (final Exception e) {
			logger.error("Exception in getProfileValue:", e);
		}
		return profileValue;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsupst/getBillableFlag", method = RequestMethod.GET)
	public ReferenceCodes getBillableFlag(@RequestParam(value = "code") final String code) {
		ReferenceCodes beanObj = new ReferenceCodes();
		try {
			beanObj = ocdsupstService.getBillableFlag(code);
		} catch (final Exception e) {
			logger.error("Exception in getBillableFlag:", e);
		}
		return beanObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsupst/supHistoryExecuteQuery", method = RequestMethod.POST)
	public List<OffSupervisionStsHty> supHistoryExecuteQuery(@RequestBody final OffSupervisionStsHty searchBean) {
		List<OffSupervisionStsHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocdsupstService.supHistoryExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderBookingAgyLocs bean = new OffenderBookingAgyLocs();
			logger.error("Exception in supHistoryExecuteQuery:", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * method: offBkge`
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdsupst/suphstyCommit", method = RequestMethod.POST)
	public Integer suphstyCommit(@RequestBody final OffSupervisionStsHtyCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			commitBean.setCreateUserId(userName);
			liReturn = ocdsupstService.suphstyCommit(commitBean);
//			if (liReturn == 1 && commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()
//					&& "Y".equals(commitBean.getInsertList().get(0).getBillableFlag())) {
//				prosmainService.enableTriggers(commitBean, authorization, "59");
//			}
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsupst/getsupStartDate", method = RequestMethod.POST)
	public FeeAccountProfiles getsupStartDate(@RequestBody final VHeaderBlock bean) {
		FeeAccountProfiles liReturn = new FeeAccountProfiles();
		try {
			liReturn = ocdsupstService.getsupStartDate(bean);
		} catch (Exception e) {
			logger.error("Exception in getsupStartDate:", e);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsupst/getIntakeRevCount", method = RequestMethod.POST)
	public Integer getIntakeRevCount(@RequestBody final VHeaderBlock bean) {
		Integer liReturn = 0;
		try {
			liReturn = ocdsupstService.getIntakeRevCount(bean);
		} catch (Exception e) {
			logger.error("Exception in getIntakeRevCount:", e);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsupst/getFeeAccountsData", method = RequestMethod.GET)
	public  List<FeeAccountProfiles> getFeeAccountsData(@RequestBody final Integer offenderBookId ) {
		 List<FeeAccountProfiles> liReturn = new ArrayList<>();
		try {
			liReturn = ocdsupstService.getFeeAccountsData(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception in getFeeAccountsData:", e);
		}
		return liReturn;
	}
	
}
