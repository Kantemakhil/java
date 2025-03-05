package net.syscon.s4.inst.demographicsbiometrics;

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
import net.syscon.s4.im.beans.HealthRecordDetails;
import net.syscon.s4.im.beans.HealthRecordDetailsCommitBean;
import net.syscon.s4.im.beans.OffHealthRecordsData;
import net.syscon.s4.im.beans.OffHealthRecordsDataCommitBean;

@EliteController
public class OcdhealtController {
	
	@Autowired
	private OcdhealtService ocdhealtService;
	
	private static Logger logger = LogManager.getLogger(OcdhealtController.class.getName());
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdhealt/getUserRoleForHealUser", method = RequestMethod.GET)
	public Integer getUserRoleForHealUser() {
		final ReferenceCodes obj = new ReferenceCodes();
		Integer accessCount=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			accessCount = ocdhealtService.getUserRoleForHealUser(userName);
		} catch (Exception e) {
			obj.setErrorMessage(e.getMessage());
			return accessCount;
		}
		return accessCount;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdhealt/getUserRoleForHealAdvUser", method = RequestMethod.GET)
	public Integer getUserRoleForHealAdvUser() {
		final ReferenceCodes obj = new ReferenceCodes();
		Integer accessCount=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			accessCount = ocdhealtService.getUserRoleForHealAdvUser(userName);
		} catch (Exception e) {
			obj.setErrorMessage(e.getMessage());
			return accessCount;
		}
		return accessCount;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdhealt/getOffenderRowHealthExecuteQuery", method = RequestMethod.POST)
	public List<OffHealthRecordsData> getOffenderRowHealthExecuteQuery(@RequestBody final OffHealthRecordsData searchBean) {
		List<OffHealthRecordsData> recordList = new ArrayList<OffHealthRecordsData>();
		try {
			recordList = ocdhealtService.getOffenderRowHealthExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffHealthRecordsData error = new OffHealthRecordsData();
			logger.error("In getOffenderPeriodExecuteQuery method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdhealt/offenderRowHealthDataCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffHealthRecordsData> offenderRowHealthDataCommit(@RequestBody final OffHealthRecordsDataCommitBean commitBean) {
		List<OffHealthRecordsData> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdhealtService.offenderRowHealthDataCommit(commitBean);
		} catch (Exception e) {
			final OffHealthRecordsData error = new OffHealthRecordsData();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception : offenderObservationPeriodDataCommit", e);
		}
		return liReturn;
	}
	
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdhealt/getHealthDetailExecuteQuery", method = RequestMethod.POST)
	public List<HealthRecordDetails> getHealthDetailExecuteQuery(@RequestBody final HealthRecordDetails searchBean) {
		List<HealthRecordDetails> recordList = new ArrayList<HealthRecordDetails>();
		try {
			recordList = ocdhealtService.getHealthDetailExecuteQuery(searchBean);
		} catch (Exception e) {
			final HealthRecordDetails error = new HealthRecordDetails();
			logger.error("In getOffenderPeriodExecuteQuery method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdhealt/healthRecordDetailDataCommit", method = RequestMethod.POST)
	public @ResponseBody List<HealthRecordDetails> healthRecordDetailDataCommit(@RequestBody final HealthRecordDetailsCommitBean commitBean) {
		List<HealthRecordDetails> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdhealtService.healthRecordDetailDataCommit(commitBean);
		} catch (Exception e) {
			final HealthRecordDetails error = new HealthRecordDetails();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception : offenderObservationPeriodDataCommit", e);
		}
		return liReturn;
	}

}
