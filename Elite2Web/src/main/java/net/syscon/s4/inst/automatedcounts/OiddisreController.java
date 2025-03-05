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

/**
 * Class OiddisreController
 */
@EliteController
public class OiddisreController {
	@Autowired
	private OiddisreService oiddisreService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiddisreController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddisre/agencyCountsExecuteQuery", method = RequestMethod.POST)
	public List<AgencyCounts> agencyCountsExecuteQuery(@RequestBody AgencyCounts searchBean) {
		List<AgencyCounts> searchResult = new ArrayList<>();
		try {
			searchResult = oiddisreService.agencyCountsExecuteQuery(searchBean);
		} catch (Exception e) {
			AgencyCounts bean = new AgencyCounts();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oiddisre/agencyCountsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyCountsCommit(@RequestBody AgencyCounts bean) {
		int liReturn = 0;
		try {
			if (bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
				bean.setModifyUserId(userName);
			}
			liReturn = oiddisreService.agencyCountsCommit(bean);
		} catch (Exception e) {

			logger.error("Exception : Oiddisre", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkDiscrepRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddisre/cgfkDiscrepRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkDiscrepRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oiddisreService.cgfkDiscrepRsnRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oiddisre:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}