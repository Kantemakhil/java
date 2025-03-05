package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;

/**
 * @Class OtdoalloController
 */
@EliteController
public class OtdoalloController {
	@Autowired
	private OtdoalloService otdoalloService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdoalloController.class.getName());

	/**
	 * getting cgfkOffDedDeductionStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/cgfkOffDedDeductionStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffDedDeductionStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdoalloService.cgfkOffDedDeductionStatusRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDrReceiptTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/cgfkOffDrReceiptTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup() {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
			recordList = otdoalloService.cgfkOffDrReceiptTxnTypeRecordGroup();
		} catch (Exception e) {
			final TransactionTypes obj = new TransactionTypes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDedDeductionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/cgfkOffDedDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(
			@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = otdoalloService.cgfkOffDedDeductionTypeRecordGroup(caseLoadId);
		} catch (Exception e) {
			final DeductionTypes obj = new DeductionTypes();
			logger.error(e);
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
	@RequestMapping(value = "/otdoallo/offDedExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductions> offDedExecuteQuery(@RequestBody final OffenderDeductions searchBean) {
		List<OffenderDeductions> searchResult = new ArrayList<>();
		try {
			searchResult = otdoalloService.offDedExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderDeductions bean = new OffenderDeductions();
			logger.error(e);
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
	@RequestMapping(value = "/otdoallo/offDedCommit", method = RequestMethod.POST)
	public @ResponseBody String offDedCommit(@RequestBody final OffenderDeductionsCommitBean commitBean) {
		String liReturn = "0";
		try {
			if(commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			}
			liReturn = otdoalloService.offDedCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error("Exception in offDedCommit service ",e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/offDrExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductionReceipts> offDrExecuteQuery(@RequestBody final OffenderDeductionReceipts searchBean) {
		List<OffenderDeductionReceipts> searchResult = new ArrayList<>();
		try {
			searchResult = otdoalloService.offDrExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderDeductionReceipts bean = new OffenderDeductionReceipts();
			logger.error(e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/offDrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offDrCommit(@RequestBody final OffenderDeductionReceiptsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if(commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
				}
			liReturn = otdoalloService.offDrCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdoalloService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/offDrValidateRecieptTxnType", method = RequestMethod.POST)
	public OffenderDeductionReceipts offDrValidateRecieptTxnType(@RequestBody final OffenderDeductionReceipts searchBean) {
		OffenderDeductionReceipts searchResult = new OffenderDeductionReceipts();
		try {
			searchResult = otdoalloService.offDrValidateRecieptTxnType(searchBean);
		} catch (Exception e) {
			final OffenderDeductionReceipts bean = new OffenderDeductionReceipts();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/offDedValidateDeductionType", method = RequestMethod.POST)
	public OffenderDeductions offDedValidateDeductionType(@RequestBody final OffenderDeductions searchBean) {
		OffenderDeductions searchResult = new OffenderDeductions();
		try {
			searchResult = otdoalloService.offDedValidateDeductionType(searchBean);
		} catch (Exception e) {
			final OffenderDeductions bean = new OffenderDeductions();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/offDrKeyDelrec", method = RequestMethod.GET)
	public List<String> offDrKeyDelrec(@RequestParam(value="caseloadId")final String caseloadId,
			@RequestParam(value="offenderId")final Long offenderId,
			@RequestParam(value="deductionType") final String deductionType) {
		List<String> searchResult = new ArrayList<>();
		try {
			searchResult = otdoalloService.offDrKeyDelrec(caseloadId, offenderId, deductionType);
		} catch (Exception e) {
			logger.error("otdoallo offDrKeyDelrec", e);
		}
		return searchResult;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdoallo/cntDedRcpt", method = RequestMethod.GET)
	public BigDecimal cntDedRcpt(@RequestParam(value="offenderDeductionId")final BigDecimal offenderDeductionId) {
		BigDecimal searchResult = BigDecimal.ZERO;
		try {
			searchResult = otdoalloService.cntDedRcpt(offenderDeductionId);
		} catch (Exception e) {
			logger.error("otdoallo cntDedRcpt", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdoallo/insertOnNotAvaliable", method = RequestMethod.POST)
	public String insertOnNotAvaliable(@RequestBody final OffenderDeductions offDeducBean) {
		String liReturn = "0";
		try {
			liReturn = otdoalloService.insertOnNotAvaliable(offDeducBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error("otdoallo insertOnNotAvaliable", e);
		}
		return liReturn;
	}

}