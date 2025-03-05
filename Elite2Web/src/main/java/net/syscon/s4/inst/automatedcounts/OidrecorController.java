package net.syscon.s4.inst.automatedcounts;

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
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountsCommitBean;

@EliteController
public class OidrecorController {
	@Autowired
	private OidrecorService oidrecorService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrecorController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrecor/agencyCountsExecuteQuery", method = RequestMethod.POST)
	public List<AgencyCounts> agencyCountsExecuteQuery(@RequestBody AgencyCounts searchBean) {
		List<AgencyCounts> searchResult = new ArrayList<>();
		try {
			searchResult = oidrecorService.agencyCountsExecuteQuery(searchBean);
		} catch (Exception e) {
			AgencyCounts bean = new AgencyCounts();
			logger.error("agencyCountsExecuteQuery", e);
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
	@RequestMapping(value = "/oidrecor/agencyCountsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyCountsCommit(@RequestBody AgencyCountsCommitBean commitBean) {

		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidrecorService.agencyCountsCommit(commitBean);
		} catch (Exception e) {

			logger.error("agencyCountsCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkRecountRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrecor/cgfkRecountRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkRecountRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidrecorService.cgfkRecountRsnRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("cgfkRecountRsnRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}