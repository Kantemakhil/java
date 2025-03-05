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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.im.beans.SecurityThreatGroupsCommitBean;

/**
 * Class OimtgngsController
 */
@EliteController
public class OimtgngsController {
	@Autowired
	private OimtgngsService oimtgngsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimtgngsController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimtgngs/secGrpExecuteQuery", method = RequestMethod.POST)
	public List<SecurityThreatGroups> secGrpExecuteQuery(@RequestBody final SecurityThreatGroups searchBean) {
		List<SecurityThreatGroups> searchResult = new ArrayList<>();
		try {
			searchResult = oimtgngsService.secGrpExecuteQuery(searchBean);
		} catch (Exception e) {
			final SecurityThreatGroups bean = new SecurityThreatGroups();
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
	@RequestMapping(value = "/oimtgngs/secGrpCommit", method = RequestMethod.POST)
	public @ResponseBody Integer secGrpCommit(@RequestBody final SecurityThreatGroupsCommitBean commitBean) {
		int liReturn = 0;
		   String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		 try {
		     	liReturn = oimtgngsService.secGrpCommit(commitBean);
		} catch (Exception e) {

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
	@RequestMapping(value = "/oimtgngs/secGrp1ExecuteQuery", method = RequestMethod.POST)
	public List<SecurityThreatGroups> secGrp1ExecuteQuery(@RequestBody final SecurityThreatGroups searchBean) {
		List<SecurityThreatGroups> searchResult = new ArrayList<>();
		try {
			searchResult = oimtgngsService.secGrp1ExecuteQuery(searchBean);
		} catch (Exception e) {
			final SecurityThreatGroups bean = new SecurityThreatGroups();
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
	@RequestMapping(value = "/oimtgngs/secGrp1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer secGrp1Commit(@RequestBody final SecurityThreatGroupsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
	
		try {
			liReturn = oimtgngsService.secGrp1Commit(commitBean);
		} catch (Exception e) {

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
	@RequestMapping(value = "/oimtgngs/secGrp2ExecuteQuery", method = RequestMethod.POST)
	public List<SecurityThreatGroups> secGrp2ExecuteQuery(@RequestBody final SecurityThreatGroups searchBean) {
		List<SecurityThreatGroups> searchResult = new ArrayList<>();
		try {
			searchResult = oimtgngsService.secGrp2ExecuteQuery(searchBean);
		} catch (Exception e) {
			final SecurityThreatGroups bean = new SecurityThreatGroups();
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
	@RequestMapping(value = "/oimtgngs/secGrp2Commit", method = RequestMethod.POST)
	public @ResponseBody Integer secGrp2Commit(@RequestBody final SecurityThreatGroupsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimtgngsService.secGrp2Commit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * This method used to get the Duplicate Record
	 * 
	 * @Param stgCode
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimtgngs/getDuplicateStgCode", method = RequestMethod.GET)
	public @ResponseBody Integer getDuplicateStgCode(@RequestParam(value = "stgCode") final String stgCode) {
		Integer liReturn = 0;
		try {
			liReturn = oimtgngsService.getDuplicateStgCode(stgCode);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * This method used to get the Duplicate Record
	 * 
	 * @Param stgCode
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimtgngs/getDuplicateGangsStgCode", method = RequestMethod.GET)
	public @ResponseBody Integer getDuplicateGangsStgCode(@RequestParam(value = "stgCode") final String stgCode) {
		Integer liReturn = 0;
		try {
			liReturn = oimtgngsService.getDuplicateGangsStgCode(stgCode);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * This method used to get the Duplicate Record
	 * 
	 * @Param stgCode
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimtgngs/getDuplicateSetsStgCode", method = RequestMethod.GET)
	public @ResponseBody Integer getDuplicateSetsStgCode(@RequestParam(value = "stgCode") final String stgCode) {
		Integer liReturn = 0;
		try {
			liReturn = oimtgngsService.getDuplicateSetsStgCode(stgCode);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * This method used to get the Duplicate Record
	 * 
	 * @Param stgId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimtgngs/offStgCur", method = RequestMethod.GET)
	public @ResponseBody Integer offStgCur(@RequestParam(value = "stgId") final Integer stgId) {
		Integer liReturn = 0;
		try {
			liReturn = oimtgngsService.offStgCur(stgId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * This method used to get the Duplicate Record
	 * 
	 * @Param stgId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimtgngs/offStgCurSecGrp", method = RequestMethod.GET)
	public @ResponseBody Integer offStgCurSecGrp(@RequestParam(value = "stgId") final Integer stgId) {
		Integer liReturn = 0;
		try {
			liReturn = oimtgngsService.offStgCurSecGrp(stgId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
}