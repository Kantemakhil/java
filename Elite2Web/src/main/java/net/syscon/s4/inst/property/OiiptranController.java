package net.syscon.s4.inst.property;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Class OiiptranController
 */
@EliteController
public class OiiptranController {

	@Autowired
	private OiiptranService oiiptranService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiptranController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/offPiExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItems> offPiExecuteQuery(@RequestBody final OffenderPptyItems searchBean) {
		List<OffenderPptyItems> searchResult = new ArrayList<>();
		try {
			searchResult = oiiptranService.offPiExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offPiExecuteQuery: ", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/itmTxExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItemTxns> itmTxExecuteQuery(@RequestBody final OffenderPptyItemTxns searchBean) {
		List<OffenderPptyItemTxns> searchResult = new ArrayList<>();
		try {
			searchResult = oiiptranService.itmTxExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("itmTxExecuteQuery: ", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/findReceivedList", method = RequestMethod.GET)
	public @ResponseBody List<String> findReceivedList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiiptranService.findReceivedList();
		} catch (Exception e) {
			logger.error("findReceivedList: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/findTypeList", method = RequestMethod.GET)
	public @ResponseBody List<String> findTypeList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiiptranService.findTypeList();
		} catch (Exception e) {
			logger.error("findTypeList: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/findColorList", method = RequestMethod.GET)
	public @ResponseBody List<String> findColorList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiiptranService.findColorList();
		} catch (Exception e) {
			logger.error("findColorList: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/findConditionList", method = RequestMethod.GET)
	public @ResponseBody List<String> findConditionList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiiptranService.findConditionList();
		} catch (Exception e) {
			logger.error("findConditionList: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/findFacilityList", method = RequestMethod.GET)
	public @ResponseBody List<String> findFacilityList(
			) {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiiptranService.findFacilityList();
		} catch (Exception e) {
			logger.error("findFacilityList: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/findagyLocationList", method = RequestMethod.GET)
	public @ResponseBody List<String> findagyLocationList(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiiptranService.findagyLocationList(caseLoadId);
		} catch (Exception e) {
			logger.error("findagyLocationList: ", e);
		}
		return listOfRecords;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiptran/offPiImagesExecuteQuery", method = RequestMethod.POST)
	public List<Images> offPiImagesExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<Images>();
		try {
			searchResult = oiiptranService.offPiImagesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("itmTxExecuteQuery: ", e);
		}
		return searchResult;
	}
}