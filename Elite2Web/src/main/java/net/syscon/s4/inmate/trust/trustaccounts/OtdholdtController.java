package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Offenders;

/**
 * class OtdholdtController
 */
@EliteController
public class OtdholdtController {
@Autowired
private OtdholdtService otdholdtService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OtdholdtController.class.getName());
	/**
	 *getting cgfkOffTxnSubAccountType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdholdt/cgfkOffTxnSubAccountTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = otdholdtService.cgfkOffTxnSubAccountTypeRecordGroup(caseLoadId);
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
		    logger.error("In cgfkOffTxnSubAccountTypeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdholdt/offTxnExecuteQuery", method=RequestMethod.POST)
	public BigDecimal offTxnExecuteQuery(@RequestBody final Offenders searchBean) {
		BigDecimal searchResult = BigDecimal.ZERO;
		try {
			searchResult = otdholdtService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
		logger.error("In offTxnExecuteQuery method : ", e);
			searchResult = BigDecimal.ZERO;
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/otdholdt/offTxnCommit",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> offTxnCommit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		Map<String,Object> liReturn = new HashMap<String, Object>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otdholdtService.offTxnCommit(commitBean);
		}catch(Exception e){
		logger.error("In offTxnCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdholdt/sysPflExecuteQuery", method=RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdholdtService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
		logger.error("In sysPflExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdholdt/getExistingHoldAmount", method = RequestMethod.POST)
	public @ResponseBody BigDecimal getExistingHoldAmount(@RequestBody final Offenders searchBean ) {
		BigDecimal bgExistingHoldAmt = null;
		try {
			bgExistingHoldAmt = otdholdtService.getExistingHoldAmount(searchBean);
		} catch (Exception e) {
			logger.error("In getExistingHoldAmount method :", e);
		}
		return bgExistingHoldAmt;
	}
	
	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdholdt/getSubAccountBalance", method = RequestMethod.POST)
	public @ResponseBody BigDecimal getSubAccountBalance(@RequestBody final Offenders searchBean ) {
		BigDecimal bgSubAccountAmt = null;
		try {
			bgSubAccountAmt = otdholdtService.getSubAccountBalance(searchBean);
		} catch (Exception e) {
			logger.error("In getSubAccountBalance method :", e);
		}
		return bgSubAccountAmt;
	}
	

}