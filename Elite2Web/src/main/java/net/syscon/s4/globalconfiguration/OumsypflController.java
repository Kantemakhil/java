package net.syscon.s4.globalconfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.globalconfiguration.impl.OumsypflServiceImpl;
import net.syscon.s4.im.beans.Dual;

/**
 * class OumsypflController
 */
@EliteController
public class OumsypflController {

	@Autowired
	private OumsypflService oumsypflService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumsypflController.class.getName());

	/**
	 * getting cgfkSysPflProfileType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsypfl/cgfkSysPflProfileTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkSysPflProfileTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oumsypflService.cgfkSysPflProfileTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In cgfkSysPflProfileTypeRecordGroup:", e);
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
	@RequestMapping(value = "/oumsypfl/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		final SystemProfiles bean = new SystemProfiles();
		try {
			searchResult = oumsypflService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In sysPflExecuteQuery:", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	
	 
		@PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/oumsypfl/getInsightMode", method = RequestMethod.GET)
		public List<SystemProfiles> getInsightMode() {
			List<SystemProfiles> searchResult = new ArrayList<>();
			final SystemProfiles bean = new SystemProfiles();
			SystemProfiles searchBean = new SystemProfiles();
			try {
				searchBean.setProfileCode("INSIGHT_MODE");
				searchResult = oumsypflService.sysPflExecuteQuery(searchBean);
			} catch (Exception e) {
				logger.error("In getInsightMode:"+ e.getMessage());
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsypfl/sysPflCommit", method = RequestMethod.POST)
	public @ResponseBody String sysPflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumsypflService.sysPflCommit(commitBean);
		} catch (Exception e) {
			logger.error("In sysPflCommit:", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkchkSysPflSystemProfil LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsypfl/cgfkchkSysPflSystemProfil", method = RequestMethod.GET)
	public ReferenceCodes cgfkchkSysPflSystemProfil(@RequestBody final ReferenceCodes paramBean) {
		ReferenceCodes recordList = new ReferenceCodes();
		try {
			recordList = oumsypflService.cgfkchkSysPflSystemProfil(paramBean);
		} catch (Exception e) {
			logger.error("In cgfkchkSysPflSystemProfil:", e);
		}
		return recordList;
	}

	/**
	 * getting cgwhenNewFormInstance 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsypfl/cgwhenNewFormInstance", method = RequestMethod.GET)
	public Dual cgwhenNewFormInstance(final String createUserId) {
		Dual recordList = new Dual();
		try {
			recordList = oumsypflService.cgwhenNewFormInstance(createUserId);
		} catch (Exception e) {
			logger.error("In cgwhenNewFormInstance:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsypfl/getSystemProfileRecords", method = RequestMethod.POST)
	public List<SystemProfiles> getSystemProfileRecords(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		final SystemProfiles bean = new SystemProfiles();
		try {
			searchResult = oumsypflService.getSystemProfileRecords(searchBean);
		} catch (Exception e) {
			logger.error("In sysPflExecuteQuery:", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@RequestMapping(value = "/oumsypfl/updateSystemProfileCache", method = RequestMethod.GET)
	public @ResponseBody Integer updateSystemProfileCache() {
		Integer result=0;
		try {
			loadSystemProfileData();
			result=1;

		}catch(Exception e) {
			logger.error(e);
		}
		return result;
	}

	@PostConstruct
	public void loadSystemProfileData(){
		SystemProfiles searchObj=new SystemProfiles();
		List<SystemProfiles> searchResult = new ArrayList<>();
		searchResult = oumsypflService.sysPflExecuteQuery(searchObj);
		if(searchResult!=null && !searchResult.isEmpty()) {
			for(SystemProfiles data:searchResult) {
				OumsypflServiceImpl.systemProfileCache.put(data.getProfileCode(), data.getProfileValue());
			}
		}



	}

}