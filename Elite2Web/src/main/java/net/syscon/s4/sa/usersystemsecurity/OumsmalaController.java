package net.syscon.s4.sa.usersystemsecurity;

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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRolesCommitBean;

/**
 * class OumsmalaController
 * 
 */
@EliteController
public class OumsmalaController {
	@Autowired
	private OumsmalaService oumsmalaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumsmalaController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmala/smExecuteQuery", method = RequestMethod.POST)
	public List<StaffMembers> smExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		try {
			searchResult = oumsmalaService.smExecuteQuery(searchBean);
		} catch (Exception e) {
			StaffMembers bean = new StaffMembers();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 * getting cgfkSlrPosition LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmala/cgfkSlrPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkSlrPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumsmalaService.cgfkSlrPositionRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkSlrRole LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmala/cgfkSlrRoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkSlrRoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumsmalaService.cgfkSlrRoleRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkSlrStaffUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmala/cgfkSlrStaffUnitRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkSlrStaffUnitRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumsmalaService.cgfkSlrStaffUnitRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkSlrScheduleType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmala/cgfkSlrScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkSlrScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumsmalaService.cgfkSlrScheduleTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCalAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmala/cgfkCalAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkCalAgyLocIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oumsmalaService.cgfkCalAgyLocIdRecordGroup();
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
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmala/calExecuteQuery", method = RequestMethod.POST)
	public List<AgencyLocations> calExecuteQuery(@RequestBody AgencyLocations searchBean) {
		List<AgencyLocations> searchResult = new ArrayList<AgencyLocations>();
		try {
			searchResult = oumsmalaService.calExecuteQuery(searchBean);
		} catch (Exception e) {
			AgencyLocations bean = new AgencyLocations();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oumsmala/slrExecuteQuery", method = RequestMethod.POST)
	public List<StaffLocationRoles> slrExecuteQuery(@RequestBody final StaffLocationRoles searchBean) {
		List<StaffLocationRoles> searchResult = new ArrayList<>();
		try {
			searchResult = oumsmalaService.slrExecuteQuery(searchBean);
		} catch (Exception e) {
			StaffLocationRoles bean = new StaffLocationRoles();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oumsmala/slrCommit", method = RequestMethod.POST)
	public @ResponseBody StaffLocationRoles slrCommit(@RequestBody final StaffLocationRolesCommitBean commitBean) {
		StaffLocationRoles liReturn = new StaffLocationRoles();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumsmalaService.slrCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmala/cguvchkSlrPk", method = RequestMethod.POST)
	public Integer cguvchkSlrPk(@RequestBody final StaffLocationRoles searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = oumsmalaService.cguvchkSlrPk(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
}