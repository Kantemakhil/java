package net.syscon.s4.inst.demographicsbiometrics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.OffenderProfileDetailsCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.demographicsbiometrics.impl.OidpidenServiceImpl;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidpinfoController
 */
@EliteController
public class OidpinfoController {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpinfoController.class.getName());

	@Autowired
	private OidpinfoService oidpinfoservice;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OidpidenServiceImpl oidpidenService;

	/**
	 * Fetching the Offenders record from database table
	 * 
	 * @return List<Offenders>
	 * @Param Offenders searchBean
	 */

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/offNameExecuteQuery", method = RequestMethod.POST)
	public List<Offenders> offNameSearchOffenders(@RequestBody final Offenders searchBean) {
		List<Offenders> searchResult = new ArrayList<Offenders>();
		final Offenders obj = new Offenders();
		
		try {
			searchResult = oidpinfoservice.offNameSearchOffenders(searchBean);
		} catch (Exception e) {
			logger.error("offNameSearchOffenders:",e);
			obj.setErrorMessage(e.getMessage());
			searchResult.add(obj);
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
	@RequestMapping(value = "/oidpinfo/offNameCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offNameCommit(@RequestBody final OffendersCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidpinfoservice.offNameCommit(commitBean);
			if(1 == liReturn) {
				OffendersCommitBean offCommitBean = new OffendersCommitBean();
				List<Offenders> updatedList = new ArrayList<Offenders>();
				updatedList.add(commitBean.getUpdateList().get(0));
				offCommitBean.setUpdateList(updatedList);
				prosmainService.enableTriggers(offCommitBean, authorization, "103");
			}
		} catch (Exception e) {
			logger.error("offNameCommit:",e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Dual>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/cgWhenNewFormInstancec", method = RequestMethod.GET)
	public @ResponseBody List<Dual> cgwhenNewFormInstancec() {
		List<Dual> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = oidpinfoservice.cgwhenNewFormInstancec();
		} catch (Exception e) {
			logger.error("cgwhenNewFormInstancec:",e);
		}
		return listOfRecords;
	}

	/**
	 * getting cgfkOffNameDspDescription LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/cgfkOffNameDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffNameDspDescriptionRgroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidpinfoservice.cgfkOffNameDspDescriptionRgroup();
		} catch (Exception e) {
			logger.error("cgfkOffNameDspDescriptionRgroup:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffPdDspDescription2 LOV values
	 * 
	 * @return List<ProfileCodes>
	 * @param ProfileCodes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/cgfkOffPdDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ProfileCodes> cgfkOffPdDspDescriptionRgroup(
			@RequestParam(value = "profileType") final String profileType) {
		List<ProfileCodes> recordList = new ArrayList<ProfileCodes>();
		final ProfileCodes obj = new ProfileCodes();
		try {
			recordList = oidpinfoservice.cgfkOffPdDspDescriptionRgroup(profileType);
		} catch (Exception e) {
			logger.error("cgfkOffPdDspDescriptionRgroup:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgBirthState LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/rgBirthStateRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgBirthStateRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidpinfoservice.rgBirthStateRecordGroup();
		} catch (Exception e) {
			logger.error("rgBirthStateRecordGroup:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderProfileDetails>
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/offPdExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(
			@RequestBody final OffenderProfileDetails searchBean) {
		List<OffenderProfileDetails> searchResult = new ArrayList<>();
		final OffenderProfileDetails bean = new OffenderProfileDetails();
		try {
			searchResult = oidpinfoservice.offPdSearchOffenderProfileDetails(searchBean);
		} catch (Exception e) {
			logger.error("offPdSearchOffenderProfileDetails:",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/oidpinfo/offPdCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPdCommit(@RequestBody final OffenderProfileDetailsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		Integer liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		
		commitBean.setCreateUserId(userName);
		OffenderProfileDetailsCommitBean processCommitBean = setProcessCommitBean(commitBean);
		try {
			liReturn = oidpinfoservice.offPdCommit(commitBean);
			if(0 != liReturn ) {
				prosmainService.enableTriggers(processCommitBean, authorization, "95");
			}
		} catch (Exception e) {
			logger.error("offPdCommit:",e);
		}
		return liReturn;
	}
	
	private OffenderProfileDetailsCommitBean setProcessCommitBean(OffenderProfileDetailsCommitBean commitBean) {
		OffenderProfileDetailsCommitBean processCommitBean = new OffenderProfileDetailsCommitBean();
		try {
			if(commitBean!= null && commitBean.getUpdateList()!=null) {
				List<OffenderProfileDetails> insertList = commitBean.getUpdateList().stream().filter(i -> i.getModifyDatetime() ==null && i.getModifyUserId() == null).collect(Collectors.toList());
				List<OffenderProfileDetails> updateList = commitBean.getUpdateList().stream().filter(i -> i.getModifyDatetime() !=null && i.getModifyUserId() != null).collect(Collectors.toList());
				processCommitBean.setInsertList(insertList);
				processCommitBean.setUpdateList(updateList);
			}	
		} catch (Exception e) {
			logger.error("Exception in setProcessCommitBean : {} ", e.getMessage());
		}
		
		return processCommitBean;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/offBkgOnCheckDeleteMasteroffPdCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> offbkgoncheckdeletemasteroffpdcur(
			@RequestBody final OffenderProfileDetails paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = oidpinfoservice.offBkgOnCheckDeleteMasteroffPdCur(paramBean);
		} catch (Exception e) {
			logger.error("offbkgoncheckdeletemasteroffpdcur:",e);

		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return ReferenceCodes
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/offNamePostQuerycOffBirthState", method = RequestMethod.POST)
	public @ResponseBody ReferenceCodes offnamepostquerycoffbirthstate(@RequestBody final ReferenceCodes paramBean) {
		ReferenceCodes dataObj = new ReferenceCodes();
		try {
			dataObj = oidpinfoservice.offNamePostQuerycOffBirthState(paramBean);
		} catch (Exception e) {
			logger.error("offnamepostquerycoffbirthstate:",e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return ProfileTypes
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/profileCodePostChange", method = RequestMethod.POST)
	public @ResponseBody ProfileTypes profilecodepostchange(@RequestBody final ProfileTypes paramBean) {
		ProfileTypes dataObj = new ProfileTypes();
		try {
			dataObj = oidpinfoservice.profileCodePostChange(paramBean);
		} catch (Exception e) {
			logger.error("profilecodepostchange:",e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return ProfileTypes
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/dspDescriptionWhenValidateItemprofileTypes", method = RequestMethod.POST)
	public @ResponseBody ProfileTypes dspDescriptionWhenValidateItemprofileTypes(
			@RequestBody final ProfileTypes paramBean) {
		ProfileTypes dataObj = new ProfileTypes();
		try {
			dataObj = oidpinfoservice.dspDescriptionWhenValidateItemprofileTypes(paramBean);
		} catch (Exception e) {
			logger.error("dspDescriptionWhenValidateItemprofileTypes:",e);
		}
		return dataObj;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpinfo/checkProfileDetails", method = RequestMethod.GET)
	public @ResponseBody  List<OffenderProfileDetails> checkProfileDetails(@RequestParam(value = "offenderBookId") final String offenderBookId,
			@RequestParam(value = "caseloadType") final String caseloadType, @RequestParam(value = "profileCategory") final String profileCategory)					
			 {
	  String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	  List<OffenderProfileDetails>  dataObj = new ArrayList<OffenderProfileDetails>();
		try {
			dataObj = oidpidenService.checkProfileDetails(offenderBookId,caseloadType,profileCategory,userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

}