package net.syscon.s4.inst.workflow.maintenance;

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

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.cm.teamsworkflow.beans.WorkCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkFunction;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkFunctionCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplate;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplateCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTrigger;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTriggerCommitBean;

/**
 * Class OcmworksController
 */
@EliteController
public class OcmworksController {
	@Autowired
	private OcmworksService ocmworksService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmworksController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/wfWorkTypesExecuteQuery", method = RequestMethod.POST)
	public List<Work> wfWorkTypesExecuteQuery(@RequestBody Work searchBean) {
		List<Work> searchResult = new ArrayList<>();
		try {
			searchResult = ocmworksService.wfWorkTypesExecuteQuery(searchBean);
		} catch (Exception e) {
			Work bean = new Work();
			logger.error("Exception in wfWorkTypesExecuteQuery :", e);
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
	@RequestMapping(value = "/ocmworks/wfWorkTypesCommit", method = RequestMethod.POST)
	public @ResponseBody Work wfWorkTypesCommit(@RequestBody WorkCommitBean commitBean) {
		Work liReturn = new Work();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmworksService.wfWorkTypesCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in wfWorkTypesCommit: Ocmworks", e);
		}
		return liReturn;
	}

	/**
	 * getting rgWorkflowType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/rgWorkflowTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkflowTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmworksService.rgWorkflowTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgWorkflowTypeRecordGroup: Ocmworks:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/rgAgyLocTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAgyLocTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmworksService.rgAgyLocTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgAgyLocTypeRecordGroup: Ocmworks:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/rgWorkTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmworksService.rgWorkTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgWorkTypeRecordGroup: Ocmworks:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/rgWorkSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup(@RequestParam(value = "workType") final String workType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmworksService.rgWorkSubTypeRecordGroup(workType);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgWorkSubTypeRecordGroup: Ocmworks:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgModules LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/rgModulesRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> rgModulesRecordGroup() {
		List<OmsModules> recordList = new ArrayList<>();
		try {
			recordList = ocmworksService.rgModulesRecordGroup();
		} catch (Exception e) {
			OmsModules obj = new OmsModules();
			logger.error("Exception in rgModulesRecordGroup: Ocmworks:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTemplates LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/rgTemplatesRecordGroup", method = RequestMethod.GET)
	public List<IwpTemplates> rgTemplatesRecordGroup() {
		List<IwpTemplates> recordList = new ArrayList<>();
		try {
			recordList = ocmworksService.rgTemplatesRecordGroup();
		} catch (Exception e) {
			IwpTemplates obj = new IwpTemplates();
			logger.error("Exception in rgTemplatesRecordGroup: Ocmworks:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTriggerName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/rgTriggerNameRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTriggerNameRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmworksService.rgTriggerNameRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgTriggerNameRecordGroup: Ocmworks:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFunction LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/rgFunctionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmworksService.rgFunctionRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgFunctionRecordGroup: Ocmworks:", e);
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
	@RequestMapping(value = "/ocmworks/wfIwpTemplatesExecuteQuery", method = RequestMethod.POST)
	public List<WorkIwpTemplate> wfIwpTemplatesExecuteQuery(@RequestBody WorkIwpTemplate searchBean) {
		List<WorkIwpTemplate> searchResult = new ArrayList<>();
		try {
			searchResult = ocmworksService.wfIwpTemplatesExecuteQuery(searchBean);
		} catch (Exception e) {
			WorkIwpTemplate bean = new WorkIwpTemplate();
			logger.error("Exception in wfIwpTemplatesExecuteQuery:", e);
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
	@RequestMapping(value = "/ocmworks/wfIwpTemplatesCommit", method = RequestMethod.POST)
	public @ResponseBody WorkIwpTemplate wfIwpTemplatesCommit(@RequestBody WorkIwpTemplateCommitBean commitBean) {
		WorkIwpTemplate liReturn = new WorkIwpTemplate();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmworksService.wfIwpTemplatesCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in wfIwpTemplatesCommit: Ocmworks", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/wfTriggersExecuteQuery", method = RequestMethod.POST)
	public List<WorkTrigger> wfTriggersExecuteQuery(@RequestBody WorkTrigger searchBean) {
		List<WorkTrigger> searchResult = new ArrayList<>();
		try {
			searchResult = ocmworksService.wfTriggersExecuteQuery(searchBean);
		} catch (Exception e) {
			WorkTrigger bean = new WorkTrigger();
			logger.error("Exception in wfTriggersExecuteQuery:", e);
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
	@RequestMapping(value = "/ocmworks/wfTriggersCommit", method = RequestMethod.POST)
	public @ResponseBody WorkTrigger wfTriggersCommit(@RequestBody WorkTriggerCommitBean commitBean) {
		WorkTrigger liReturn = new WorkTrigger();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmworksService.wfTriggersCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception wfTriggersCommit : Ocmworks", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/wfFunctionsExecuteQuery", method = RequestMethod.POST)
	public List<WorkFunction> wfFunctionsExecuteQuery(@RequestBody WorkFunction searchBean) {
		List<WorkFunction> searchResult = new ArrayList<>();
		try {
			searchResult = ocmworksService.wfFunctionsExecuteQuery(searchBean);
		} catch (Exception e) {
			WorkFunction bean = new WorkFunction();
			logger.error("Exception wfFunctionsExecuteQuery:", e);
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
	@RequestMapping(value = "/ocmworks/wfFunctionsCommit", method = RequestMethod.POST)
	public @ResponseBody WorkFunction wfFunctionsCommit(@RequestBody WorkFunctionCommitBean commitBean) {
		WorkFunction liReturn = new WorkFunction();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmworksService.wfFunctionsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception wfFunctionsCommit: Ocmworks", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/wfEmailRecipientsExecuteQuery", method = RequestMethod.POST)
	public List<InternetAddresses> wfEmailRecipientsExecuteQuery(@RequestBody InternetAddresses searchBean) {
		List<InternetAddresses> searchResult = new ArrayList<>();
		try {
			searchResult = ocmworksService.wfEmailRecipientsExecuteQuery(searchBean);
		} catch (Exception e) {
			InternetAddresses bean = new InternetAddresses();
			logger.error("Exception wfEmailRecipientsExecuteQuery:", e);
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
	@RequestMapping(value = "/ocmworks/wfEmailRecipientsCommit", method = RequestMethod.POST)
	public @ResponseBody InternetAddresses wfEmailRecipientsCommit(
			@RequestBody InternetAddressesCommitBean commitBean) {
		InternetAddresses liReturn = new InternetAddresses();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmworksService.wfEmailRecipientsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception wfEmailRecipientsCommit: Ocmworks", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/wfEmailReturnExecuteQuery", method = RequestMethod.POST)
	public List<InternetAddresses> wfEmailReturnExecuteQuery(@RequestBody InternetAddresses searchBean) {
		List<InternetAddresses> searchResult = new ArrayList<>();
		try {
			searchResult = ocmworksService.wfEmailReturnExecuteQuery(searchBean);
		} catch (Exception e) {
			InternetAddresses bean = new InternetAddresses();
			logger.error("Exception wfEmailReturnExecuteQuery:", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmworks/wfWorkEmailExecuteQuery", method = RequestMethod.POST)
	public List<Work> wfWorkEmailExecuteQuery(@RequestBody Work searchBean) {
		List<Work> searchResult = new ArrayList<>();
		try {
			searchResult = ocmworksService.wfWorkEmailExecuteQuery(searchBean);
		} catch (Exception e) {
			Work bean = new Work();
			logger.error("Exception wfWorkEmailExecuteQuery:", e);
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
	@RequestMapping(value = "/ocmworks/wfWorkEmailCommit", method = RequestMethod.POST)
	public @ResponseBody Integer wfWorkEmailCommit(@RequestBody WorkCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmworksService.wfWorkEmailCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception wfWorkEmailCommit: Ocmworks", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmworks/checkdays", method = RequestMethod.POST)
	public @ResponseBody Integer checkdays(@RequestBody WorkTrigger commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocmworksService.checkdays(commitBean);
		} catch (Exception e) {

			logger.error("Exception checkdays: Ocmworks", e);
		}
		return liReturn;
	}

}