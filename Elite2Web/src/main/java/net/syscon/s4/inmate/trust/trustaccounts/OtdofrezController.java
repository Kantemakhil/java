package net.syscon.s4.inmate.trust.trustaccounts;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.OffenderFreezeDisbursements;
import net.syscon.s4.inmate.beans.OffenderFreezeDisbursementsCommitBean;


@EliteController
public class OtdofrezController {
@Autowired
private OtdofrezService otdofrezService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OtdofrezController.class.getName());
	/**
	 *getting cgfkOffFdFreezeReasonCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdofrez/cgfkOffFdFreezeReasonCodeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffFdFreezeReasonCodeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = otdofrezService.cgfkOffFdFreezeReasonCodeRecordGroup();
		} catch(Exception e){
			logger.error(e);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdofrez/offFdExecuteQuery", method=RequestMethod.POST)
	public List<OffenderFreezeDisbursements> offFdExecuteQuery(@RequestBody final OffenderFreezeDisbursements searchBean) {
		List<OffenderFreezeDisbursements> searchResult = new ArrayList<>();
		try {
			searchResult = otdofrezService.offFdExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/otdofrez/offFdCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offFdCommit(@RequestBody final OffenderFreezeDisbursementsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdofrezService.offFdCommit(commitBean);
		}catch(Exception e){

			logger.error(e);
		}
		return liReturn;
	}

}