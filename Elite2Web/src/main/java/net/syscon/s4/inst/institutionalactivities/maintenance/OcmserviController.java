package net.syscon.s4.inst.institutionalactivities.maintenance;

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
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.ProgramServicesCommitBean;

/**
 * OcmserviController
 */
@EliteController
public class OcmserviController {
	@Autowired
	private OcmserviService ocmserviService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmserviController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmservi/prgSrvExecuteQuery", method = RequestMethod.POST)
	public List<ProgramServices> prgSrvExecuteQuery(@RequestBody final ProgramServices searchBean) {
		List<ProgramServices> searchResult = new ArrayList<>();
		try {
			searchResult = ocmserviService.prgSrvExecuteQuery(searchBean);
		} catch (Exception e) {
			final ProgramServices bean = new ProgramServices();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocmservi/prgSrvCommit", method = RequestMethod.POST)
	public @ResponseBody ProgramServices prgSrvCommit(@RequestBody final ProgramServicesCommitBean commitBean) {
		ProgramServices liReturn = new ProgramServices();
		String tabName = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmserviService.prgSrvCommit(commitBean);

			if (liReturn.getSealFlag() != null && liReturn.getSealFlag().length() > 2
					&& !liReturn.getSealFlag().isEmpty()) {
				tabName = ocmserviService.getTableName(liReturn.getSealFlag());
				liReturn.setSealFlag(tabName.toUpperCase());
			}
		} catch (Exception e) {
			logger.error("Exception :", e);
			final String errorMsg = "Error: " + e.getMessage();
			 liReturn.setErrorMessage(errorMsg.toUpperCase());
		}
		return liReturn;
	}

	/**
	 * getting rgPsCategory LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmservi/rgPsCategoryRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsCategoryRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmserviService.rgPsCategoryRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmservi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFunctionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmservi/rgFunctionTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFunctionTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmserviService.rgFunctionTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmservi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}