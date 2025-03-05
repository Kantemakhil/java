package net.syscon.s4.iwp;

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

import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransferCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssigned;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssignedCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OcdexpowController {
	@Autowired
	private OcdexpowService ocdexpowService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdexpowController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<ExtOwnershipTransfer>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdexpow/extOtExecuteQuery", method = RequestMethod.POST)
	public List<ExtOwnershipTransfer> extOtExecuteQuery(@RequestBody ExtOwnershipTransfer searchBean) {
		List<ExtOwnershipTransfer> searchResult = new ArrayList<>();
		try {
			searchResult = ocdexpowService.extOtExecuteQuery(searchBean);
		} catch (Exception e) {
			ExtOwnershipTransfer bean = new ExtOwnershipTransfer();
			logger.error("Exception :", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update in the
	 * database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdexpow/extOtCommit", method = RequestMethod.POST)
	public @ResponseBody Integer extOtCommit(@RequestBody ExtOwnershipTransferCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdexpowService.extOtCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkExtOtAgyLocIdTo LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdexpow/cgfkExtOtAgyLocIdToRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkExtOtAgyLocIdToRecordGroup(
			@RequestParam("agyLocIdFrom") final String agylocidfrom) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdexpowService.cgfkExtOtAgyLocIdToRecordGroup(agylocidfrom);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffMembers LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdexpow/rgStaffMembersRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffMembersRecordGroup(@RequestParam("agyLocIdFrom") final String agylocidfrom) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdexpowService.rgStaffMembersRecordGroup(agylocidfrom);
		} catch (Exception e) {
			StaffMembers obj = new StaffMembers();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkExtOtAgyLocIdFrom LOV values 
	 * Fetching the record from database table
	 * @Param searchRecord
	 * @return List<AgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdexpow/cgfkExtOtAgyLocIdFromRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkExtOtAgyLocIdFromRecordGroup(@RequestParam("caseloadId") final String caseloadid) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdexpowService.cgfkExtOtAgyLocIdFromRecordGroup(caseloadid);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<VOffenderAssigned>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdexpow/vOffenderAssignedExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAssigned> vOffenderAssignedExecuteQuery(@RequestBody VOffenderAssigned searchBean) {
		List<VOffenderAssigned> searchResult = new ArrayList<>();
		try {
			searchResult = ocdexpowService.vOffenderAssignedExecuteQuery(searchBean);
		} catch (Exception e) {
			VOffenderAssigned bean = new VOffenderAssigned();
			logger.error("Exception :", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update in the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdexpow/vOffenderAssignedCommit", method = RequestMethod.POST)
	public Integer vOffenderAssignedCommit(@RequestBody VOffenderAssignedCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
        List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdexpowService.vOffenderAssignedCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "10");
			}
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

}