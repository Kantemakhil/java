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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkillsCommitBean;
import net.syscon.s4.sa.usersystemsecurity.beans.VMemberSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.VMemberSkillsCommitBean;

/**
 * OuisdireController
 */
@EliteController
public class OuisdireController {
	@Autowired
	private OuisdireService ouisdireService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuisdireController.class.getName());

	/**
	 * getting nomRegionRg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/nomRegionRgRecordGroup", method = RequestMethod.GET)
	public List<Areas> nomRegionRgRecordGroup() {
		List<Areas> recordList = new ArrayList<Areas>();
		try {
			recordList = ouisdireService.nomRegionRgRecordGroup();
		} catch (Exception e) {
			final Areas obj = new Areas();
			logger.error("Exception : nomRegionRgRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkStskSkillType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkStskSkillTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkStskSkillTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ouisdireService.cgfkStskSkillTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkStskSkillTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkStskSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkStskSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkStskSubTypeRecordGroup(@RequestParam("subType") final String subType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ouisdireService.cgfkStskSubTypeRecordGroup(subType);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkStskSubTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVmsSexCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkVmsSexCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkVmsSexCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ouisdireService.cgfkVmsSexCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkVmsSexCodeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVmsAgencyLocationType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkVmsAgencyLocationTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkVmsAgencyLocationTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ouisdireService.cgfkVmsAgencyLocationTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkVmsAgencyLocationTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVmsAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkVmsAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkVmsAgyLocIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ouisdireService.cgfkVmsAgyLocIdRecordGroup();
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : cgfkVmsAgyLocIdRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVmsCity LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkVmsCityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkVmsCityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ouisdireService.cgfkVmsCityRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkVmsCityRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVmsScheduleType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkVmsScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkVmsScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ouisdireService.cgfkVmsScheduleTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkVmsScheduleTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVmsPosition LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkVmsPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkVmsPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ouisdireService.cgfkVmsPositionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkVmsPositionRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkVmsRole LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/cgfkVmsRoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkVmsRoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ouisdireService.cgfkVmsRoleRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkVmsRoleRecordGroup:", e);
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
	@RequestMapping(value = "/ouisdire/vms1ExecuteQuery", method = RequestMethod.POST)
	public List<VMemberSkills> vms1ExecuteQuery(@RequestBody final VMemberSkills searchBean) {
		List<VMemberSkills> searchResult = new ArrayList<>();
		try {
			searchResult = ouisdireService.vms1ExecuteQuery(searchBean);
		} catch (Exception e) {
			final VMemberSkills bean = new VMemberSkills();
			logger.error("Exception : vms1ExecuteQuery", e);
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
	@RequestMapping(value = "/ouisdire/vms1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer vms1Commit(@RequestBody final VMemberSkillsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ouisdireService.vms1Commit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Ouisdire", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/stskExecuteQuery", method = RequestMethod.POST)
	public List<StaffSkills> stskExecuteQuery(@RequestBody final StaffSkills searchBean) {
		List<StaffSkills> searchResult = new ArrayList<>();
		try {
			searchResult = ouisdireService.stskExecuteQuery(searchBean);
		} catch (Exception e) {
			final StaffSkills bean = new StaffSkills();
			logger.error("Exception : stskExecuteQuery", e);
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
	@RequestMapping(value = "/ouisdire/stskCommit", method = RequestMethod.POST)
	public @ResponseBody Integer stskCommit(@RequestBody final StaffSkillsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ouisdireService.stskCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Ouisdire", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouisdire/hmPhoneExecuteQuery", method = RequestMethod.POST)
	public List<Phones> hmPhoneExecuteQuery(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<>();
		try {
			searchResult = ouisdireService.hmPhoneExecuteQuery(searchBean);
		} catch (Exception e) {
			final Phones bean = new Phones();
			logger.error("Exception : hmPhoneExecuteQuery", e);
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
	@RequestMapping(value = "/ouisdire/bsPhoneExecuteQuery", method = RequestMethod.POST)
	public List<Phones> busPhoneExecuteQuery(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<>();
		try {
			searchResult = ouisdireService.busPhoneExecuteQuery(searchBean);
		} catch (Exception e) {
			final Phones bean = new Phones();
			logger.error("Exception : busPhoneExecuteQuery", e);
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
	@RequestMapping(value = "/ouisdire/emailExecuteQuery", method = RequestMethod.POST)
	public List<InternetAddresses> emailExecuteQuery(@RequestBody final InternetAddresses searchBean) {
		List<InternetAddresses> searchResult = new ArrayList<>();
		try {
			searchResult = ouisdireService.emailExecuteQuery(searchBean);
		} catch (Exception e) {
			final InternetAddresses bean = new InternetAddresses();
			logger.error("Exception : emailExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}