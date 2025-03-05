package net.syscon.s4.inst.property;
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

import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * @Class OidrpitmController
 */
@EliteController
public class OidrpitmController {
@Autowired
private OidrpitmService oidrpitmService;
@Autowired
private ProsmainService prosmainService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OidrpitmController.class.getName());
	/**
	 *getting rgColor LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrpitm/rgColorRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgColorRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidrpitmService.rgColorRecordGroup();
		} catch (Exception e) {
			logger.error("rgColorRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgCondn LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrpitm/rgCondnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCondnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidrpitmService.rgCondnRecordGroup();
		} catch (Exception e) {
			logger.error("rgCondnRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkOffPiReceivedFrom LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrpitm/cgfkOffPiReceivedFromRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffPiReceivedFromRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidrpitmService.cgfkOffPiReceivedFromRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkOffPiReceivedFromRecordGroup", e);
		}
		return recordList;
	}

	/**
	 *getting cgfkOffPiPropertyType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidrpitm/cgfkOffPiPropertyTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffPiPropertyTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidrpitmService.cgfkOffPiPropertyTypeRecordGroup();
		} catch(Exception e){
			logger.error("cgfkOffPiPropertyTypeRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@RequestMapping(value="/oidrpitm/offPiExecuteQuery", method=RequestMethod.POST)
	public List<OffenderPptyItems> offPiExecuteQuery(@RequestBody final OffenderPptyItems searchBean) {
		List<OffenderPptyItems> searchResult = new ArrayList<>();
		final OffenderPptyItems bean = new OffenderPptyItems();
		try {
			searchResult = oidrpitmService.offPiExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offPiExecuteQuery",e);
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
	@RequestMapping(value="/oidrpitm/offPiCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offPiCommit(@RequestBody final OffenderPptyItemsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidrpitmService.offPiCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "51");
			}
		}catch(Exception e){
			logger.error("offPiCommit",e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidrpitm/sysPflExecuteQuery", method=RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidrpitmService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("sysPflExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 *getting rgCondn LOV values 
	 */
	@RequestMapping(value = "/oidrpitm/offRecForm", method = RequestMethod.GET)
	public List<ReferenceCodes> offRecForm() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidrpitmService.offRecForm();
		} catch (Exception e) {
			logger.error("rgCondnRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}