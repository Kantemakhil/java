package net.syscon.s4.inst.securitythreatgroups;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StgSearchV1;
import net.syscon.s4.common.beans.StgSearchV1CommitBean;

/**
 * OsistgkwController
 */
@EliteController
public class OsistgkwController {
	@Autowired
	private OsistgkwService osistgkwService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsistgkwController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<StgSearchV1>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osistgkw/stgSearchV1ExecuteQuery", method = RequestMethod.POST)
	public List<StgSearchV1> stgSearchV1ExecuteQuery(@RequestBody final StgSearchV1 searchBean) {
		List<StgSearchV1> searchResult = new ArrayList<>();
		try {
			searchResult = osistgkwService.stgSearchV1ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("stgSearchV1ExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/osistgkw/stgSearchV1Commit", method = RequestMethod.POST)
	public Integer stgSearchV1Commit(@RequestBody final StgSearchV1CommitBean commitBean) {
		int liReturn = 0;
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = osistgkwService.stgSearchV1Commit(commitBean);
		} catch (Exception e) {

			logger.error("stgSearchV1Commit", e);
		}
		return liReturn;
	}

}