package net.syscon.s4.inst.movementexternal;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.common.beans.VNameSearchCommitBean;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OiinamesController
 */
@EliteController
public class OiinamesController {
	@Autowired
	private OiinamesService oiinamesService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OsiosearService osiosearService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiinamesController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiinames/nameSrchExecuteQuery", method = RequestMethod.POST)
	public List<VNameSearch> nameSrchExecuteQuery(@RequestBody final VNameSearch searchBean) {
		List<VNameSearch> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oiinamesService.nameSrchExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("nameSrchExecuteQuery", e);
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
	@RequestMapping(value = "/oiinames/nameSrchCommit", method = RequestMethod.POST)
	public @ResponseBody Integer nameSrchCommit(@RequestBody final VNameSearchCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oiinamesService.nameSrchCommit(commitBean);
		} catch (Exception e) {
			logger.error("nameSrchCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiinames/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oiinamesService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiinames/findAgyLocIdList", method = RequestMethod.POST)
	public @ResponseBody List<String> findAgyLocIdList(@RequestBody  AgencyIncidents agencyIncidents) {
		List<String> listOfRecords = null;
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			listOfRecords = oiinamesService.findAgyLocIdList(userName);
		} catch (Exception e) {
			logger.error("findAgyLocIdList: ", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiinames/findLivingUnitsList", method = RequestMethod.POST)
	public @ResponseBody List<String> findLivingUnitsList(@RequestBody  AgencyIncidents agencyIncidents) {
		List<String> listOfRecords = null;
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			listOfRecords = oiinamesService.findLivingUnitsList(userName);
		} catch (Exception e) {
			logger.error("findLivingUnitsList: ", e);
		}
		return listOfRecords;
	}

	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiinames/findActiveFlagList", method = RequestMethod.POST)
	public @ResponseBody List<String> findActiveFlagList(@RequestBody  AgencyIncidents agencyIncidents) {
		List<String> listOfRecords = null;
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			listOfRecords = oiinamesService.findActiveFlagList();
		} catch (Exception e) {
			logger.error("findActiveFlagList: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param sysDual
	 * @return List<Dual>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiinames/cgwhenNewFormInstance", method = RequestMethod.GET)
	public @ResponseBody List<Dual> cgwhenNewFormInstance(final SysDual sysDual) {
		List<Dual> returnList = new ArrayList<>();
		try {
			returnList = oiinamesService.cgwhenNewFormInstance(sysDual);
		} catch (Exception e) {
			logger.error("cgwhenNewFormInstancec: ", e);
		}
		return returnList;
	}
	
	/**
	 * getting rgGrievType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiinames/findAgyLocIdListLov", method = RequestMethod.GET)
	public List<Caseloads> findAgyLocIdListLov() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		try {
			recordList = oiinamesService.findAgyLocIdListLov();
		} catch (Exception e) {
			logger.error("findAgyLocIdListLov", e);
		}
		return recordList;
	}
	
	/** OSIOSEAR controller duplicated
	 * return Header block details
	 * 
	 * @param searchBean
	 * @return List<VHeaderBlock>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiinames/offbkgGlobalQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offbkgGlobalQuery(@Valid @RequestBody final VHeaderBlock searchBean, @RequestHeader HttpHeaders headers) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<VHeaderBlock> searchResult = new ArrayList<>();
		final VHeaderBlock bean = new VHeaderBlock();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.offbkgGlobalQuery(searchBean);
			if(searchResult == null){
				searchResult = new ArrayList<>();
				bean.setErrorMessage("Agency Location Type  Not Provided");
				searchResult.add(bean);
				return searchResult;
			} else {
				for(VHeaderBlock vHeaderBlock : searchResult) {
					vHeaderBlock.setCreateuserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
				}
			}
		} catch (Exception e) {
			logger.error("Exception in ", e);
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OIINAMES");
	}

}