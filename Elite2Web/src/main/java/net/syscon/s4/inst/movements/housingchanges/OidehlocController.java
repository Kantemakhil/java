package net.syscon.s4.inst.movements.housingchanges;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OidehlocController
 * 
 */
@EliteController
public class OidehlocController {
	@Autowired
	private OidehlocService oidehlocService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidehlocController.class.getName());

	/**
	 * getting rgAssignmentReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidehloc/rgAssignmentReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidehlocService.rgAssignmentReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgAssignmentReasonRecordGroup method : ", e);
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
	@RequestMapping(value = "/oidehloc/vOffBkgExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> vOffBkgExecuteQuery(@RequestBody final VHeaderBlock searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidehlocService.vOffBkgExecuteQuery(searchBean);
		} catch (Exception e) {
			final VHeaderBlock bean = new VHeaderBlock();
			logger.error("In vOffBkgExecuteQuery method : ", e);
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
	@RequestMapping(value = "/oidehloc/vOffBkgCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vOffBkgCommit(@RequestBody final VHeaderBlockCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		Integer liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidehlocService.vOffBkgCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "76");
			}
		} catch (Exception e) {
			logger.error("In vOffBkgCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidehloc/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidehlocService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("In sysPflExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidehloc/nonAssocationOffendersList", method = RequestMethod.POST)
	public @ResponseBody VHeaderBlockCommitBean nonAssocationOffendersList(
			@RequestBody final VHeaderBlockCommitBean commitBean) {

		VHeaderBlockCommitBean returnBean = new VHeaderBlockCommitBean();
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);

		try {
			returnBean = oidehlocService.nonAssocationOffendersList(commitBean);

		} catch (Exception e) {
			logger.error("In vOffBkgCommit method : ", e);
		}
		return returnBean;
	}

}