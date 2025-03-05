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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.Corporate;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;
import net.syscon.s4.inst.property.bean.Printer;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OiddpropController
 */
@EliteController
public class OiddpropController {

	@Autowired
	private OiddpropService oiddpropService;
	@Autowired
	private ProsmainService prosmainService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiddpropController.class.getName());

	/**
	 * getting cgfkItmTxPropertyContainer LOV values
	 */
	@RequestMapping(value = "/oiddprop/cgfkItmTxPropertyContainerRecordGroup", method = RequestMethod.GET)
	public List<OffenderPptyContainers> cgfkItmTxPropertyContainerRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<OffenderPptyContainers> recordList = new ArrayList<OffenderPptyContainers>();
		final OffenderPptyContainers obj = new OffenderPptyContainers();
		try {
			recordList = oiddpropService.cgfkItmTxPropertyContainerRecordGroup(caseloadId, offenderBookId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgDspCorporateName LOV values
	 */
	@RequestMapping(value = "/oiddprop/rgDspCorporateNameRecordGroup", method = RequestMethod.GET)
	public List<Corporate> rgDspCorporateNameRecordGroup() {
		List<Corporate> recordList = new ArrayList<>();
		try {
			recordList = oiddpropService.rgDspCorporateNameRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgDisposedToPerson LOV values
	 */
	@RequestMapping(value = "/oiddprop/rgDisposedToPersonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDisposedToPersonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiddpropService.rgDisposedToPersonRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	
	@RequestMapping(value = "/oiddprop/getDisposedToPersonsGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> getDisposedToPersonsGroup(@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiddpropService.getDisposedToPersonsGroup(offenderBookId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkItmTxFromStatusCodeRecordGroup values
	 */
	@RequestMapping(value = "/oiddprop/cgfkItmTxFromStatusCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiddpropService.cgfkItmTxFromStatusCodeRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@RequestMapping(value = "/oiddprop/itmTxExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItemTxns> itmTxExecuteQuery(@RequestBody final OffenderPptyItemTxns searchBean) {
		List<OffenderPptyItemTxns> searchResult = new ArrayList<>();
		final OffenderPptyItemTxns bean = new OffenderPptyItemTxns();
		try {
			searchResult = oiddpropService.itmTxExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@RequestMapping(value = "/oiddprop/cgfkOffConTrnToAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffConTrnToAgyLocIdRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oiddpropService.cgfkOffConTrnToAgyLocIdRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error("cgfkOffConTrnToAgyLocIdRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiddprop/itmTxCommit", method = RequestMethod.POST)
	public @ResponseBody Integer itmTxCommit(@RequestBody final OffenderPptyItemTxnsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oiddpropService.itmTxCommit(commitBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddprop/offPiExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItems> offPiExecuteQuery(@RequestBody final OffenderPptyItems searchBean) {
		List<OffenderPptyItems> searchResult = new ArrayList<>();
		final OffenderPptyItems bean = new OffenderPptyItems();
		try {
			searchResult = oiddpropService.offPiExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddprop/offConPiExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItems> offConPiExecuteQuery(@RequestBody final OffenderPptyItems searchBean) {
		List<OffenderPptyItems> searchResult = new ArrayList<>();
		final OffenderPptyItems bean = new OffenderPptyItems();
		try {
			searchResult = oiddpropService.offConPiExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/oiddprop/offPiCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPiCommit(@RequestBody final OffenderPptyItemsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oiddpropService.offPiCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "53");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiddprop/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		final SystemProfiles bean = new SystemProfiles();
		try {
			searchResult = oiddpropService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 * 
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/cgfkchkItmTxItmTxOffConc", method = RequestMethod.POST)
	public @ResponseBody OffenderPptyContainers cgfkchkItmTxItmTxOffCon(
			@RequestBody final OffenderPptyContainers paramBean) {
		OffenderPptyContainers listOfRecords = null;
		try {
			listOfRecords = oiddpropService.cgfkchkItmTxItmTxOffCon(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return ReferenceCodes
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/cgfkchkItmTxItmTxRefCod", method = RequestMethod.POST)
	public @ResponseBody ReferenceCodes cgfkchkItmTxItmTxRefCod(@RequestBody final ReferenceCodes paramBean) {
		ReferenceCodes listOfRecords = null;
		try {
			listOfRecords = oiddpropService.cgfkchkItmTxItmTxRefCod(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/cgfkchkOffPiOffPiOffConc", method = RequestMethod.POST)
	public @ResponseBody OffenderPptyContainers cgfkchkOffPiOffPiOffCon(
			@RequestBody final OffenderPptyContainers paramBean) {
		OffenderPptyContainers listOfRecords = null;
		try {
			listOfRecords = oiddpropService.cgfkchkOffPiOffPiOffCon(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return OmsModules
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/printReportOldgetPrintFormatCur", method = RequestMethod.GET)
	public @ResponseBody OmsModules printReportOldgetPrintFormatCur(@RequestBody final OmsModules paramBean) {
		OmsModules dataObj = null;
		try {
			dataObj = oiddpropService.printReportOldgetPrintFormatCur(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return Printer
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/printReportOldgetPrinterNameCur", method = RequestMethod.GET)
	public @ResponseBody Printer printReportOldgetPrinterNameCur(@RequestBody final CaseloadFormatPrinter paramBean) {
		Printer dataObj = null;
		try {
			dataObj = oiddpropService.printReportOldgetPrinterNameCur(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return Printer
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/printReportgetPrinterNameCur", method = RequestMethod.GET)
	public @ResponseBody Printer printReportgetPrinterNameCur(@RequestBody final CaseloadFormatPrinter paramBean) {
		Printer dataObj = null;
		try {
			dataObj = oiddpropService.printReportgetPrinterNameCur(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return SystemProfiles
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/printReportroleCur", method = RequestMethod.GET)
	public @ResponseBody SystemProfiles printReportroleCur(@RequestBody final SystemProfiles paramBean) {
		SystemProfiles dataObj = new SystemProfiles();
		try {
			dataObj = oiddpropService.printReportroleCur(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return SystemProfiles
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/printReportrolePwd", method = RequestMethod.GET)
	public @ResponseBody SystemProfiles printReportrolePwd(@RequestBody final SystemProfiles paramBean) {
		SystemProfiles dataObj = null;
		try {
			dataObj = oiddpropService.printReportrolePwd(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return SystemProfiles
	 * @Param paramBean
	 */
	@RequestMapping(value = "/oiddprop/printReportlReportAlias", method = RequestMethod.GET)
	public @ResponseBody SystemProfiles printReportlReportAlias(@RequestBody final SystemProfiles paramBean) {
		SystemProfiles dataObj = null;
		try {
			dataObj = oiddpropService.printReportlReportAlias(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @Param systemProfiles
	 */
	@RequestMapping(value = "/oiddprop/printReportlSystemProfilesDescCur", method = RequestMethod.GET)
	public @ResponseBody Object printReportlSystemProfilesDescCur(@RequestBody final SystemProfiles systemProfiles) {
		Object dataObj = null;
		try {
			dataObj = oiddpropService.printReportlSystemProfilesDescCur(systemProfiles);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

}