package net.syscon.s4.inst.offenderissuestracking.maintenance;

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
import net.syscon.s4.im.beans.GrievanceReasons;
import net.syscon.s4.im.beans.GrievanceReasonsCommitBean;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTxnsCommitBean;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.GrievanceTypesCommitBean;

/**
 * OimissueController
 */
@EliteController
public class OimissueController {
	@Autowired
	private OimissueService oimissueService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimissueController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimissue/grievanceTypesExecuteQuery", method=RequestMethod.POST)
	public List<GrievanceTypes> grievanceTypesExecuteQuery(@RequestBody final GrievanceTypes searchBean) {
		List<GrievanceTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oimissueService.grievanceTypesExecuteQuery(searchBean);
		} catch (Exception e) {
			GrievanceTypes bean = new GrievanceTypes();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oimissue/grievanceTypesCommit",method=RequestMethod.POST)
	public @ResponseBody List<GrievanceTypes> grievanceTypesCommit(@RequestBody final GrievanceTypesCommitBean commitBean) {
		List<GrievanceTypes> liReturn = new ArrayList<>();

		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimissueService.grievanceTypesCommit(commitBean);
		}catch(Exception e){
			final GrievanceTypes error = new GrievanceTypes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimissue/grievanceReasonsExecuteQuery", method=RequestMethod.POST)
	public List<GrievanceReasons> grievanceReasonsExecuteQuery(@RequestBody final GrievanceReasons searchBean) {
		List<GrievanceReasons> searchResult = new ArrayList<>();
		try {
			searchResult = oimissueService.grievanceReasonsExecuteQuery(searchBean);
		} catch (Exception e) {
			final GrievanceReasons bean = new GrievanceReasons();
			logger.error("Exception :grievanceReasonsExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oimissue/grievanceReasonsCommit",method=RequestMethod.POST)
	public @ResponseBody List<GrievanceReasons> grievanceReasonsCommit(@RequestBody final GrievanceReasonsCommitBean commitBean) {
		List<GrievanceReasons> liReturn = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimissueService.grievanceReasonsCommit(commitBean);
		}catch(Exception e){
			final GrievanceReasons error = new GrievanceReasons();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception : grievanceReasonsCommit", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimissue/grievanceTxnsExecuteQuery", method=RequestMethod.POST)
	public List<GrievanceTxns> grievanceTxnsExecuteQuery(@RequestBody final GrievanceTxns searchBean) {
		List<GrievanceTxns> searchResult = new ArrayList<>();
		try {
			searchResult = oimissueService.grievanceTxnsExecuteQuery(searchBean);
		} catch (Exception e) {
			final GrievanceTxns bean = new GrievanceTxns();
			logger.error("Exception : grievanceTxnsExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oimissue/grievanceTxnsCommit",method=RequestMethod.POST)
	public @ResponseBody List<GrievanceTxns> grievanceTxnsCommit(@RequestBody final GrievanceTxnsCommitBean commitBean) {
		List<GrievanceTxns> liReturn = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimissueService.grievanceTxnsCommit(commitBean);
		}catch(Exception e){
			final GrievanceTxns error = new GrievanceTxns();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	/**
	 * This method is used to find the child records count
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimissue/cgrichkMovementReasonsDeleteCheck", method = RequestMethod.POST)
	public GrievanceTxns cgrichkMovementReasonsDeleteCheck(@RequestBody final GrievanceTxns searchBean) {
		GrievanceTxns searchBeanData = null;
		try {
			searchBeanData = oimissueService.cgrichkMovementReasonsDeleteCheck(searchBean);
		} catch (Exception e) {
			logger.error("Exception : cgrichkMovementReasonsDeleteCheck:", e);
		}
		return searchBeanData;
	}
	
	/**
	 * This method is used to find the child records count
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimissue/onDeleteReasons", method = RequestMethod.POST)
	public GrievanceReasons onDeleteReasons(@RequestBody final GrievanceReasons searchBean) {
		GrievanceReasons searchBeanData = null;
		try {
			searchBeanData = oimissueService.onDeleteReasons(searchBean);
		} catch (Exception e) {
			logger.error("Exception : cgrichkMovementReasonsDeleteCheck:", e);
		}
		return searchBeanData;
	}
	
	/**
	 * This method is used to find the child records count
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimissue/getTabSecuityEnable", method = RequestMethod.GET)
	public GrievanceTxns getTabSecuityEnable() {
		GrievanceTxns searchBeanData = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(); 
		try {
			searchBeanData = oimissueService.getTabSecuityEnable(userName);
		} catch (Exception e) {
			logger.error("Exception : getTabSecuityEnable:", e);
		}
		return searchBeanData;
	}

}