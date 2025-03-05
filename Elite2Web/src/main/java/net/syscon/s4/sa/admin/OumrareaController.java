package net.syscon.s4.sa.admin;

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
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.sa.admin.beans.AreasCommitBean;

/**
 * OumrareaController
 */
@EliteController
public class OumrareaController {
	@Autowired
	private OumrareaService oumrareaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumrareaController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumrarea/maintRegExecuteQuery", method = RequestMethod.POST)
	public List<Areas> maintRegExecuteQuery(@RequestBody final Areas searchBean) {
		List<Areas> searchResult = new ArrayList<>();
		try {
			searchResult = oumrareaService.maintRegExecuteQuery(searchBean);
		} catch (Exception e) {
			final Areas bean = new Areas();
			logger.error("Exception : maintRegExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/oumrarea/maintRegCommit", method = RequestMethod.POST)
	public @ResponseBody List<Areas> maintRegCommit(@RequestBody final AreasCommitBean commitBean) {
		List<Areas> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumrareaService.maintRegCommit(commitBean);
		} catch (Exception e) {
			final Areas error = new Areas();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception : maintRegCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgAreaType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumrarea/rgAreaTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumrareaService.rgAreaTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oumrarea:", e);
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
	@RequestMapping(value = "/oumrarea/maintAreaExecuteQuery", method = RequestMethod.POST)
	public List<Areas> maintAreaExecuteQuery(@RequestBody final Areas searchBean) {
		List<Areas> searchResult = new ArrayList<>();
		try {
			searchResult = oumrareaService.maintAreaExecuteQuery(searchBean);
		} catch (Exception e) {
			final Areas bean = new Areas();
			logger.error("Exception : maintAreaExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/oumrarea/maintAreaCommit", method = RequestMethod.POST)
	public @ResponseBody List<Areas> maintAreaCommit(@RequestBody final AreasCommitBean commitBean) {
		List<Areas> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumrareaService.maintAreaCommit(commitBean);
		} catch (Exception e) {
			final Areas error = new Areas();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception : maintAreaCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumrarea/maintSubAreaExecuteQuery", method = RequestMethod.POST)
	public List<Areas> maintSubAreaExecuteQuery(@RequestBody final Areas searchBean) {
		List<Areas> searchResult = new ArrayList<>();
		try {
			searchResult = oumrareaService.maintSubAreaExecuteQuery(searchBean);
		} catch (Exception e) {
			final Areas bean = new Areas();
			logger.error("Exception : maintSubAreaExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/oumrarea/maintSubAreaCommit", method = RequestMethod.POST)
	public @ResponseBody List<Areas> maintSubAreaCommit(@RequestBody final AreasCommitBean commitBean) {
		List<Areas> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			  liReturn = oumrareaService.maintSubAreaCommit(commitBean);
		} catch (Exception e) {
			final Areas error = new Areas();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception : maintSubAreaCommit", e);
		}
		return liReturn;
	}

}