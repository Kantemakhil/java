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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OidmpconController
 */
@EliteController
public class OidmpconController {

	@Autowired
	private OidmpconService oidmpconService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidmpconController.class.getName());

	/**
	 * getting rgLocationAll LOV values
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/rgLocationAllRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgLocationAllRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oidmpconService.rgLocationAllRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("rgLocationAllRecordGroup: ", e);
			logger.error("", e);
		}
		return recordList;
	}
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/getAllLocations", method = RequestMethod.GET)
	public List<AgencyInternalLocations> getAllLocations(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oidmpconService.getAllLocations(caseloadId);
		} catch (Exception e) {
			logger.error("error in getAllLocations: "+ e.getMessage());
			logger.error("", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/offConExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyContainers> offConExecuteQuery(@RequestBody final OffenderPptyContainers searchBean) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		try {
			searchResult = oidmpconService.offConExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offConExecuteQuery: ", e);
			searchResult.add(new OffenderPptyContainers());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmpcon/offConCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offConCommit(@RequestBody final OffenderPptyContainersCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidmpconService.offConCommit(commitBean);
		} catch (Exception e) {
			logger.error("offConCommit: ", e);
		}
		return liReturn;
	}
	
	@RequestMapping(value = "/oidmpcon/offConUpdateSeal", method = RequestMethod.POST)
	public @ResponseBody Integer offConUpdateSeal(@RequestBody final OffenderPptyContainers commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidmpconService.offConUpdateSeal(commitBean);
			if(1==liReturn) {
				OffenderPptyContainersCommitBean offenderPptyContainersCommitBean =  new OffenderPptyContainersCommitBean();
				List<OffenderPptyContainers> OffenderPptyContainers = new ArrayList<OffenderPptyContainers>();
				OffenderPptyContainers.add(commitBean);
				offenderPptyContainersCommitBean.setUpdateList(OffenderPptyContainers);
				prosmainService.enableTriggers(offenderPptyContainersCommitBean, authorization, "52");
			}
		} catch (Exception e) {
			logger.error("offConCommit: ", e);
		}
		return liReturn;
	}
	
	@RequestMapping(value = "/oidmpcon/offConUpdateMultipleSeal", method = RequestMethod.POST)
	public @ResponseBody Integer offConUpdateMultipleSeal(@RequestBody  List<OffenderPptyContainers> commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			liReturn = oidmpconService.offConUpdateMultipleSeal(commitBean);
			if(1==liReturn) {
				OffenderPptyContainersCommitBean offenderPptyContainersCommitBean =  new OffenderPptyContainersCommitBean();
				offenderPptyContainersCommitBean.setUpdateList(commitBean);
				prosmainService.enableTriggers(offenderPptyContainersCommitBean, authorization, "52");
			}
		} catch (Exception e) {
			logger.error("offConCommit: ", e);
		}
		return liReturn;
	}
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/findRgContainerCode", method = RequestMethod.GET)
	public @ResponseBody List<String> findRgContainerCode() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oidmpconService.findRgContainerCode();
		} catch (Exception e) {
			logger.error("findRgContainerCode: ", e);
		}
		return listOfRecords;
	}
	
	/**
	 * Fetching the record from database table
	 * It returns 1 or 0
	 * @Param searchRecord
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/checkStorageLocation", method = RequestMethod.GET)
	public @ResponseBody Integer checkStorageLocation(@RequestParam(value = "internalLocId") final String internalLocId) {
		Integer listOfRecords = null;
		try {
			listOfRecords = oidmpconService.checkStorageLocation(internalLocId);
		} catch (Exception e) {
			logger.error("checkStorageLocation: ", e);
		}
		return listOfRecords;
	}
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/checkPptyItems", method = RequestMethod.GET)
	public @ResponseBody List<String> checkPptyItems(
			@RequestParam(value = "propertyConId") final Integer propertyConId) {
		List<String> dataObj = new ArrayList<>();
		try {
			dataObj = oidmpconService.checkPptyItems(propertyConId);
		} catch (Exception e) {
			logger.error("checkPptyItems:",e);
		}
		return dataObj;
	}
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/cgfkchkOffConOffConPpty", method = RequestMethod.POST)
	public AgencyInternalLocations cgfkchkOffConOffConPpty(@RequestBody final AgencyInternalLocations paramBean) {
		AgencyInternalLocations searchResult = new AgencyInternalLocations();
		try {
			searchResult = oidmpconService.cgfkchkOffConOffConPpty(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffConOffConPpty: ", e);
		}
		return searchResult;
	}
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/checkContainerEmptyValue", method = RequestMethod.GET)
	public @ResponseBody Integer checkContainerEmptyValue(@RequestParam(value = "propertyConId") final Integer propertyConId) {
		Integer listOfRecords = null;
		try {
			listOfRecords = oidmpconService.checkContainerEmptyValue(propertyConId);
		} catch (Exception e) {
			logger.error("checkContainerEmptyValue: ", e);
		}
		return listOfRecords;
	}

	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/getSealMarkValueOfpropertyConId", method = RequestMethod.GET)
	public @ResponseBody OffenderPptyContainers getSealMarkValueOfpropertyConId(@RequestParam(value = "propertyConId") final Integer propertyConId) {
		 OffenderPptyContainers listOfRecords = new OffenderPptyContainers();
		try {
		listOfRecords = oidmpconService.getSealMarkValueOfpropertyConId(propertyConId);
		} catch (Exception e) {
			logger.error("getSealMarkValueOfpropertyConId: ", e);
		}
		return listOfRecords;
	}
	
	/*
	 * This method is used to get location value
	 * param agyLocId
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/getLocationValue", method = RequestMethod.GET)
	public @ResponseBody AgencyInternalLocations getLocationValue(@RequestParam(value = "agyLocId") final String agyLocId) {
		AgencyInternalLocations listOfRecords = new AgencyInternalLocations();
		try {
		listOfRecords = oidmpconService.getLocationValue(agyLocId);
		} catch (Exception e) {
			logger.error("getLocationValue: ", e);
		}
		return listOfRecords;
	}
	/**
	 * Used to get the Record group values for location
	 * @param parentField
	 * @return recordList
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/getLocationValuesOfLov", method = RequestMethod.GET)
	public List<AgencyInternalLocations> getLocationValuesOfLov(
			@RequestParam(value = "parentField") final String parentField) {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oidmpconService.getLocationValuesOfLov(parentField);
		} catch (Exception e) {
			logger.error("getLocationValuesOfLov: ", e);
		}
		return recordList;
	}
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpcon/getAgyLocationValuesOfLov", method = RequestMethod.GET)
	public List<AgencyInternalLocations> getAgyLocationValuesOfLov(
			@RequestParam(value = "agyLocId") final String agyLocId,@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oidmpconService.getAgyLocationValuesOfLov(agyLocId,caseloadId);
		} catch (Exception e) {
			logger.error("error in getAgyLocationValuesOfLov: "+ e.getMessage());
		}
		return recordList;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidmpcon/insertContainerImg",method=RequestMethod.POST)
	public @ResponseBody Integer insertContainerImg(@RequestBody final OffenderPptyContainers offenderPptyContainers) {
		int liReturn = 0;
		try {
			liReturn = oidmpconService.insertContainerImg(offenderPptyContainers);
		}catch(Exception e){
			logger.error("insertContainerImg",e);
		}
		return liReturn;
	}
	
		@PreAuthorize("hasEliteRole('full')")
		@RequestMapping(value="/oidmpcon/updateConatinerIntLocation",method=RequestMethod.POST)
		public @ResponseBody Integer updateConatinerIntLocation(@RequestBody final OffenderPptyContainers offenderPptyContainers) {
			int liReturn = 0;
			try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				offenderPptyContainers.setCreateUserId(userName);
				offenderPptyContainers.setModifyUserId(userName);
				liReturn = oidmpconService.updateConatinerIntLocation(offenderPptyContainers);
			}catch(Exception e){
				logger.error("insertContainerImg",e);
			}
			return liReturn;
		}
	
}