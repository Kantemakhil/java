package net.syscon.s4.cm.courtcasesandorders.maintenance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.SentenceCalcTypescommitBean;
import net.syscon.s4.common.beans.SentenceCustodyStatus;
import net.syscon.s4.common.beans.SentenceCustodyStatusCommitBean;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.beans.SentenceTermscommitBean;
import net.syscon.s4.common.beans.SentenceUpdateReasons;
import net.syscon.s4.common.beans.SentenceUpdateReasonscommitBean;

@EliteController
public class OimsreqsController {
	@Autowired
	private OimsreqsService oimsreqsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimsreqsController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@RequestMapping(value = "/oimsreqs/senCalcExecuteQuery", method = RequestMethod.POST)
	public List<SentenceCalcTypes> senCalcExecuteQuery(@RequestBody final SentenceCalcTypes searchBean) {
		List<SentenceCalcTypes> searchResult = new ArrayList<SentenceCalcTypes>();
		try {
			searchResult = oimsreqsService.senCalcExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimsreqs/senCalcCommit", method = RequestMethod.POST)
	public @ResponseBody List<SentenceCalcTypes> senCalcCommit(
			@RequestBody final SentenceCalcTypescommitBean commitBean) {
		List<SentenceCalcTypes> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimsreqsService.senCalcCommit(commitBean);
		} catch (Exception e) {
			final SentenceCalcTypes error = new SentenceCalcTypes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);

		}
		return liReturn;
	}

	/**
	 * getting rgCat LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/rgCatRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCatRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimsreqsService.rgCatRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgSent LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/rgSentRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSentRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimsreqsService.rgSentRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgSvcObl LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/rgSvcOblRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSvcOblRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimsreqsService.rgSvcOblRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgTermCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/rgTermCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTermCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimsreqsService.rgTermCodeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<LegalUpdateReasons> rgReasonRecordGroup() {
		List<LegalUpdateReasons> recordList = new ArrayList<LegalUpdateReasons>();
		try {
			recordList = oimsreqsService.rgReasonRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgFunctionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/rgFunctionTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFunctionTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimsreqsService.rgFunctionTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/senTermsExecuteQuery", method = RequestMethod.POST)
	public List<SentenceTerms> senTermsExecuteQuery(@RequestBody final SentenceTerms searchBean) {
		List<SentenceTerms> searchResult = new ArrayList<>();
		try {
			searchResult = oimsreqsService.senTermsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimsreqs/senTermsCommit", method = RequestMethod.POST)
	public @ResponseBody List<SentenceTerms> senTermsCommit(@RequestBody final SentenceTermscommitBean commitBean) {
		List<SentenceTerms> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimsreqsService.senTermsCommit(commitBean);
		} catch (Exception e) {
			final SentenceTerms error = new SentenceTerms();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/senUpdExecuteQuery", method = RequestMethod.POST)
	public List<SentenceUpdateReasons> senUpdExecuteQuery(@RequestBody final SentenceUpdateReasons searchBean) {
		List<SentenceUpdateReasons> searchResult = new ArrayList<>();
		try {
			searchResult = oimsreqsService.senUpdExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimsreqs/senUpdCommit", method = RequestMethod.POST)
	public @ResponseBody List<SentenceUpdateReasons> senUpdCommit(
			@RequestBody final SentenceUpdateReasonscommitBean commitBean) {
		List<SentenceUpdateReasons> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimsreqsService.senUpdCommit(commitBean);
		} catch (Exception e) {
			final SentenceUpdateReasons error = new SentenceUpdateReasons();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsreqs/getNbtStatusValue", method = RequestMethod.POST)
	public SentenceUpdateReasons getNbtStatusValue(@RequestBody SentenceUpdateReasons sentenceUpdateReasons) {
		try {
			sentenceUpdateReasons = oimsreqsService.getNbtStatusValue(sentenceUpdateReasons);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return sentenceUpdateReasons;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimsreqs/senCalcKeyDelrec", method = RequestMethod.POST)
	public Integer senCalcKeyDelrec(@RequestBody final SentenceCalcTypes paramBean) {
		Integer liReturn = 0;
		try {
			liReturn = oimsreqsService.senCalcKeyDelrec(paramBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	
	@GetMapping("oimsreqs/getCustodyStatus")
	public List<ReferenceCodes> getCustodyStatus() {
		List<ReferenceCodes> custodyData=Collections.checkedList(new ArrayList<ReferenceCodes>(), ReferenceCodes.class);
		try {
			custodyData = oimsreqsService.getCustodyStatus();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return custodyData ;
	}
	
	@GetMapping("oimsreqs/getOrderStatus")
	public List<ReferenceCodes> getOrderStatus() {
		List<ReferenceCodes> custodyData=Collections.checkedList(new ArrayList<ReferenceCodes>(), ReferenceCodes.class);
		try {
			custodyData = oimsreqsService.getOrderStatus();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return custodyData ;
	}
	
	@PostMapping("oimsreqs/custodyCommit")
	public Integer custodyCommit(@RequestBody final SentenceCustodyStatusCommitBean  commitBean) {
	Integer liReturn = null;
		try {
			commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oimsreqsService.custodyCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PostMapping("oimsreqs/fetchCustodyStatus")
	public List<SentenceCustodyStatus> fetchCustodyStatus(@RequestBody SentenceCustodyStatus status ) {
		List<SentenceCustodyStatus> custodyData=Collections.checkedList(new ArrayList<SentenceCustodyStatus>(), SentenceCustodyStatus.class);
		try {
			custodyData = oimsreqsService.fetchCustodyStatus(status);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return custodyData ;
	}
}