package net.syscon.s4.inst.casemanagement;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatuses;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatusesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OidistatController
 */
@EliteController
public class OidistatController {
@Autowired
private OidistatService oidistatService;
@Autowired
private ProsmainService prosmainService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OidistatController.class.getName());
	/**
	 *getting rgImprisonmentSta LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidistat/rgImprisonmentStaRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgImprisonmentStaRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidistatService.rgImprisonmentStaRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgImprisonmentStaRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgAgyLocId LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidistat/rgAgyLocIdRecordGroup",method=RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocIdRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId)  {
		List<AgencyLocations> recordList =new ArrayList<AgencyLocations>();
		try {
			recordList = oidistatService.rgAgyLocIdRecordGroup(caseloadId);
		} catch(Exception e){
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In rgAgyLocIdRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@param searchBean {@link OffenderImprisonStatuses}
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidistat/offImpsExecuteQuery", method=RequestMethod.POST)
	public List<OffenderImprisonStatuses> offImpsExecuteQuery(@RequestBody final  OffenderImprisonStatuses searchBean) {
		List<OffenderImprisonStatuses> searchResult = new ArrayList<>();
		try {
			searchResult = oidistatService.offImpsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderImprisonStatuses bean = new OffenderImprisonStatuses();
			logger.error("In offImpsExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 *@param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidistat/offImpsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offImpsCommit(@RequestBody final OffenderImprisonStatusesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidistatService.offImpsCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "114");
			}
		}catch(Exception e){
			if(e.getMessage().equals("101")) {
				return 101;
			} else {
				logger.error("In offImpsCommit method : ", e);
			}
		}
		return liReturn;
	}
	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 *@param commitBean {@link OffenderImprisonStatusesCommitBean}
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidistat/offImpsUpdateCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offImpsUpdateCommit(@RequestBody final OffenderImprisonStatusesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidistatService.offImpsUpdateCommit(commitBean);
		}catch(Exception e){
			logger.error("In offImpsUpdateCommit method : ", e);
		}
		return liReturn;
	}
	/**
	 *Fetching the record from database table
	 *@param searchBean  {@link OffenderExternalMovements}
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidistat/chkImpDate", method=RequestMethod.POST)
	public  Integer chkImpDate(@RequestBody final OffenderExternalMovements searchBean) {
		Integer inValue = 0;
		try {
			inValue = oidistatService.chkImpDate(searchBean);
		} catch (Exception e) {
			final OffenderExternalMovements bean = new OffenderExternalMovements();
			logger.error("In chkImpDate method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return inValue;
	}
	
	/**
	 *Fetching the record from database table
	 *@param searchBean
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidistat/chkImpDateEffectiveTime", method=RequestMethod.POST)
	public  Integer chkImpDateEffectiveTime(@RequestBody final OffenderExternalMovements searchBean) {
		Integer inValue = 0;
		try {
			inValue = oidistatService.chkImpDateEffectiveTime(searchBean);
		} catch (Exception e) {
			final OffenderExternalMovements bean = new OffenderExternalMovements();
			logger.error("In chkImpDateEffectiveTime method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return inValue;
	}

}