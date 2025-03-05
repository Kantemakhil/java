package net.syscon.s4.inst.movements.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.AgyIntLocProfilesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnitProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.LivingUnitsCommitBean;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

@EliteController
public class OimmholoController {
	@Autowired
	private OimmholoService oimmholoService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimmholoController.class.getName());

	/**
	 * getting rgAgyLocLov LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/rgAgyLocLovRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocLovRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = oimmholoService.rgAgyLocLovRecordGroup(userName);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgDeactLuRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/rgDeactLuRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDeactLuRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimmholoService.rgDeactLuRsnRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgUsedFor LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/rgUsedForRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUsedForRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimmholoService.rgUsedForRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgHouUnitAtt LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/rgHouUnitAttRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgHouUnitAttRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimmholoService.rgHouUnitAttRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgNonAssoType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/rgNonAssoTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgNonAssoTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimmholoService.rgNonAssoTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgSupLvlType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/rgSupLvlTypeRecordGroup", method = RequestMethod.GET)
	public List<AssessmentResults> rgSupLvlTypeRecordGroup() {
		List<AssessmentResults> recordList = new ArrayList<AssessmentResults>();
		try {
			recordList = oimmholoService.rgSupLvlTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgHouUnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/rgHouUnTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgHouUnTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimmholoService.rgHouUnTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/livUnitsExecuteQuery", method = RequestMethod.POST)
	public List<LivingUnits> livUnitsExecuteQuery(@RequestBody final LivingUnits searchBean) {
		List<LivingUnits> searchResult = new ArrayList<>();
		try {
			searchResult = oimmholoService.livUnitsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/livUnitsDialogExecuteQuery", method = RequestMethod.POST)
	public List<LivingUnits> livUnitsDialogExecuteQuery(@RequestBody final LivingUnits searchBean) {
		List<LivingUnits> searchResult = new ArrayList<>();
		try {
			searchResult = oimmholoService.livUnitsDialogExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimmholo/livUnitsCommit", method = RequestMethod.POST)
	public @ResponseBody String livUnitsCommit(@RequestBody final LivingUnitsCommitBean commitBean) {
		String liReturn = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimmholoService.livUnitsCommit(commitBean);

			if (liReturn != null && liReturn.length() > 2) {
				String tabName = oimmholoService.getTableName(liReturn);
				liReturn = tabName;
			}
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/usedForExecuteQuery", method = RequestMethod.POST)
	public List<AgyIntLocProfiles> usedForExecuteQuery(@RequestBody final AgyIntLocProfiles searchBean) {
		List<AgyIntLocProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oimmholoService.usedForExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimmholo/usedForCommit", method = RequestMethod.POST)
	public @ResponseBody Integer usedForCommit(@RequestBody final AgyIntLocProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimmholoService.usedForCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/luProfExecuteQuery", method = RequestMethod.POST)
	public List<LivingUnitProfiles> luProfExecuteQuery(@RequestBody final LivingUnitProfiles searchBean) {
		List<LivingUnitProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oimmholoService.luProfExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/cellBlockData", method = RequestMethod.POST)
	public @ResponseBody String cellBlockData(@RequestBody final LivingUnits obj) {
		String liReturn = null;
		try {
			liReturn = oimmholoService.cellBlockData(obj);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/getResDescValues", method = RequestMethod.POST)
	public LivingUnits getResDescValues(@RequestBody final LivingUnits searchBean) {
		LivingUnits searchResult = null;
		try {
			searchResult = oimmholoService.getResDescValues(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/getActiveFlagValidation", method = RequestMethod.GET)
	public @ResponseBody Long getActiveFlagValidation(@RequestParam("livingUintId") final Integer livingUintId) {
		Long liReturn = null;
		try {
			liReturn = oimmholoService.getActiveFlagValidation(livingUintId);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/getFlagValidation", method = RequestMethod.GET)
	public @ResponseBody Long getFlagValidation(@RequestParam("livingUintId") final Integer livingUintId) {
		Long liReturn = null;
		try {
			liReturn = oimmholoService.getFlagValidation(livingUintId);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/checkInheritAttributes", method = RequestMethod.GET)
	public @ResponseBody Long checkInheritAttributes(@RequestParam("livingUintId") final Integer livingUintId) {
		Long liReturn = null;
		try {
			liReturn = oimmholoService.checkInheritAttributes(livingUintId);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/attributsData", method = RequestMethod.GET)
	public @ResponseBody String attributsData(@RequestParam("livingUintId") final Integer livingUintId) {
		String liReturn = null;
		try {
			liReturn = oimmholoService.attributsData(livingUintId);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimmholo/nonAssociationData", method = RequestMethod.GET)
	public @ResponseBody String nonAssociationData(@RequestParam("livingUintId") final Integer livingUintId) {
		String liReturn = null;
		try {
			liReturn = oimmholoService.nonAssociationData(livingUintId);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimmholo/butChangeEvent", method = RequestMethod.POST)
	public @ResponseBody Integer butChangeEvent(@RequestBody final LivingUnits obj) {
		Integer liReturn = null;
		try {
			liReturn = oimmholoService.butChangeEvent(obj);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	
	@RequestMapping(value = "/oimmholo/iepLevelCommit", method = RequestMethod.POST)
	public @ResponseBody Integer iepLevelCommit(@RequestBody final AgyIntLocProfilesCommitBean lstAgyIntLocProfiles) {
		Integer liReturn = null;
		try {
			if (lstAgyIntLocProfiles != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				lstAgyIntLocProfiles.setCreateUserId(userName);
			}
			liReturn = oimmholoService.iepLevelCommit(lstAgyIntLocProfiles);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@GetMapping("/oimmholo/getIEPCode")
	public String  getIEPCode(Long internalLocationId,String agyLocId) {
		String iepCode = null;;
		try {
			iepCode=oimmholoService.getIEPCode(internalLocationId,agyLocId);
		}catch (Exception e) {
			iepCode=e.getMessage();
			logger.error("Exception :", e);
		}
		return iepCode==null?ApplicationConstants.EMPTYDATA:iepCode;
	}
	
	@GetMapping("/oimmholo/getFacilityIepLevel")
	public IepLevelBean getFacilityIepLevel(String agyLocId) {
		IepLevelBean profiles=new IepLevelBean();
		try {
			profiles=oimmholoService.getFacilityIepLevel(agyLocId);
		}catch (Exception e) {
			logger.error("Exception :", e);
		}
		return profiles;
	}
	
	@GetMapping("/oimmholo/getIEPExcecuteQuery")
	public String  getIEPExcecuteQuery(Long internalLocationId) {
		String iepCode = null;;
		try {
			iepCode=oimmholoService.getIEPExcecuteQuery(internalLocationId);
		}catch (Exception e) {
			iepCode=e.getMessage();
			logger.error("Exception :", e);
		}
		return iepCode==null?ApplicationConstants.EMPTYDATA:iepCode;
	}
	
	
}