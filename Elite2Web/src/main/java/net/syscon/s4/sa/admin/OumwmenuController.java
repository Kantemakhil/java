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
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.WorkflowFoldersCommitBean;
import net.syscon.s4.sa.admin.beans.WorkflowScreenCommitBean;


@EliteController
public class OumwmenuController {
@Autowired
private OumwmenuService oumwmenuService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OumwmenuController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumwmenu/wfFoldersExecuteQuery", method = RequestMethod.POST)
	public List<WorkFlowFolders> wfFoldersExecuteQuery(@RequestBody final WorkFlowFolders searchBean) {
		List<WorkFlowFolders> searchResult = new ArrayList<>();
		try {
			searchResult = oumwmenuService.wfFoldersExecuteQuery(searchBean);
		} catch (Exception e) {
			final WorkFlowFolders bean = new WorkFlowFolders();
			logger.error("Exception in wfFoldersExecuteQuery :",e);
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
	@RequestMapping(value="/oumwmenu/wfFoldersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer wfFoldersCommit(@RequestBody final WorkflowFoldersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumwmenuService.wfFoldersCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception  in wfFoldersCommit :",e);
		}
		return liReturn;
	}

	/**
	 *getting rgMenusName LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumwmenu/rgMenusNameRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMenusNameRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<>();
		try {
			recordList = oumwmenuService.rgMenusNameRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgMenusNameRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}


	
	/**
	 * getting rgCaseloadType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumwmenu/rgCaseloadTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCaseloadTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oumwmenuService.rgCaseloadTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : oumwmenu:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgScreensModuleName LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumwmenu/rgScreensModuleNameRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> rgScreensModuleNameRecordGroup() {
		List<OmsModules> recordList =new ArrayList<>();
		try {
			recordList = oumwmenuService.rgScreensModuleNameRecordGroup();
		} catch(Exception e){
			final OmsModules obj = new OmsModules();
			logger.error("Exception in rgScreensModuleNameRecordGroup:",e);
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
	@RequestMapping(value="/oumwmenu/wfScreensExecuteQuery", method = RequestMethod.POST)
	public List<WorkflowScreens> wfScreensExecuteQuery(@RequestBody final WorkflowScreens searchBean) {
		List<WorkflowScreens> searchResult = new ArrayList<>();
		try {
			searchResult = oumwmenuService.wfScreensExecuteQuery(searchBean);
		} catch (Exception e) {
			final WorkflowScreens bean = new WorkflowScreens();
			logger.error("Exception :",e);
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
	@RequestMapping(value="/oumwmenu/wfScreensCommit",  method = RequestMethod.POST)
	public @ResponseBody Integer wfScreensCommit(@RequestBody final WorkflowScreenCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumwmenuService.wfScreensCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception :",e);
		}
		return liReturn;
	}

	
	
	
	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumwmenu/wfFoldersPostQuery",  method = RequestMethod.POST)
	public @ResponseBody ReferenceCodes wfFoldersPostQuery(@RequestBody final WorkFlowFolders  paramBean) {
		ReferenceCodes ref =  new ReferenceCodes();
		try {
			ref = oumwmenuService.wfFoldersPostQuery(paramBean);
		}catch(Exception e){

			logger.error("Exception :",e);
		}
		return ref;
	}
	

}


