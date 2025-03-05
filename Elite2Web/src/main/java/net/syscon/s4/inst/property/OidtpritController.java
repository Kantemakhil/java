package net.syscon.s4.inst.property;

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
import net.syscon.s4.common.beans.ItemTransactionStatii;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;

/**
 * Class OidtpritController
 */
@EliteController
public class OidtpritController {
	@Autowired
	private OidtpritService oidtpritService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidtpritController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @return List<OffenderPptyItemTxns>
	 * @Param offenderPptyItemTxns
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/itmTxExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItemTxns> itmTxSearchOffenderPptyItemTxns(
			@RequestBody final OffenderPptyItemTxns offenderPptyItemTxns) {
		List<OffenderPptyItemTxns> searchResult = new ArrayList<>();
		try {
			searchResult = oidtpritService.itmTxSearchOffenderPptyItemTxns(offenderPptyItemTxns);
		} catch (Exception e) {
			logger.error("itmTxSearchOffenderPptyItemTxns", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidtprit/itmTxCommit", method = RequestMethod.POST)
	public @ResponseBody Integer itmTxCommit(@RequestBody final OffenderPptyItemTxnsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			if ((commitBean.getInsertList() != null) && (commitBean.getInsertList().size() > 0)) {
				liReturn = oidtpritService.itmTxInsertOffenderPptyItemTxns(commitBean.getInsertList());
			}
			if ((commitBean.getUpdateList() != null) && (commitBean.getUpdateList().size() > 0)) {
				liReturn = oidtpritService.itmTxUpdateOffenderPptyItemTxns(commitBean.getUpdateList());
			}
			if ((commitBean.getDeleteList() != null) && (commitBean.getDeleteList().size() > 0)) {
				commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = oidtpritService.itmTxDeleteOffenderPptyItemTxns(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("itmTxCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkItmTxFromStatusCode LOV values
	 *
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/cgfkItmTxFromStatusCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> oidtpritCgfkItmTxFromStatusCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidtpritService.cgfkItmTxFromStatusCodeRecordGroup();
		} catch (Exception e) {
			logger.error("oidtpritCgfkItmTxFromStatusCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkItmTxToStatusCode LOV values
	 *
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/cgfkItmTxToStatusCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkItmTxToStatusCodeRecordGroup(
			@RequestParam(value = "selectedFromStatus") final String selectedFromStatus) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		ItemTransactionStatii itemTransactionStatii = new ItemTransactionStatii();
		itemTransactionStatii.setItemStatusFrom(selectedFromStatus);
		try {
			recordList = oidtpritService.cgfkItmTxToStatusCodeRecordGroup(itemTransactionStatii);
		} catch (Exception e) {
			logger.error("cgfkItmTxToStatusCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkItmTxPropertyContainer LOV values
	 *
	 * @return List<ReferenceCodes>
	 *
	 * @param offenderPptyContainers
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/cgfkItmTxPropertyContainerRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkItmTxPropertyContainerRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId, @RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidtpritService.cgfkItmTxPropertyContainerRecordGroup(caseloadId,offenderBookId);
		} catch (Exception e) {
			logger.error("cgfkItmTxPropertyContainerRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @return List<OffenderPptyItems>
	 * @Param offenderPptyItems
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/offPiExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItems> offPiSearchOffenderPptyItems(
			@RequestBody final OffenderPptyItems offenderPptyItems) {
		List<OffenderPptyItems> searchResult = new ArrayList<>();
		try {
			searchResult = oidtpritService.offPiSearchOffenderPptyItems(offenderPptyItems);
		} catch (Exception e) {
			logger.error("offPiSearchOffenderPptyItems", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 *
	 * @Param commitBean
	 *
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidtprit/offPiCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPiCommit(@RequestBody final OffenderPptyItemsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			if ((commitBean.getUpdateList() != null) && (commitBean.getUpdateList().size() > 0)) {
				for (OffenderPptyItems bean : commitBean.getUpdateList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
					
				}
				liReturn = oidtpritService.offPiUpdateOffenderPptyItems(commitBean.getUpdateList());
			}
		} catch (Exception e) {
			logger.error("offPiCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @return List<SystemProfiles>
	 * @Param systemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflSearchSystemProfiles(@RequestBody final SystemProfiles systemProfiles) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidtpritService.sysPflSearchSystemProfiles(systemProfiles);
		} catch (Exception e) {
			logger.error("sysPflSearchSystemProfiles", e);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 *
	 * @return List<Object>
	 * @param offenderPptyItemTxns
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/vpheadoncheckdeletemasteritmtxcur", method = RequestMethod.POST)
	public @ResponseBody List<Object> vPheadOnCheckDeleteMasteritmTxCur(
			@RequestBody final OffenderPptyItemTxns offenderPptyItemTxns) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.vPheadOnCheckDeleteMasteritmTxCur(offenderPptyItemTxns);
		} catch (Exception e) {
			logger.error("vPheadOnCheckDeleteMasteritmTxCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 *
	 * @return Object
	 * @param offenderPptyContainers
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/itmtxwhenvalidaterecordregitems", method = RequestMethod.POST)
	public @ResponseBody Object itmTxWhenValidateRecordregItems(
			@RequestBody final OffenderPptyContainers offenderPptyContainers) {
		OffenderPptyContainers dataObj = new OffenderPptyContainers();
		;
		try {
			dataObj = oidtpritService.itmTxWhenValidateRecordregItems(offenderPptyContainers);
		} catch (Exception e) {
			logger.error("itmTxWhenValidateRecordregItems", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 *
	 * @return Object
	 * @param referenceCodes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/offpipostquerycpropertytypedesc", method = RequestMethod.POST)
	public @ResponseBody Object offPiPostQuerycPropertyTypeDesc(@RequestBody final ReferenceCodes referenceCodes) {
		Object dataObj = null;
		try {
			dataObj = new Object();
			dataObj = oidtpritService.offPiPostQuerycPropertyTypeDesc(referenceCodes);
		} catch (Exception e) {
			logger.error("offPiPostQuerycPropertyTypeDesc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 *
	 * @return List<Object>
	 * @param offenderPptyContainers
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpritCgfkchkitmtxitmtxoffconc", method = RequestMethod.POST)
	public @ResponseBody List<OffenderPptyContainers> cgfkchkItmTxItmTxOffConc(
			@RequestBody final OffenderPptyContainers offenderPptyContainers) {
		List<OffenderPptyContainers> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = oidtpritService.cgfkchkItmTxItmTxOffConc(offenderPptyContainers);
		} catch (Exception e) {
			logger.error("cgfkchkItmTxItmTxOffConc", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param offenderPptyContainers
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpritCgfkchkitmtxitmtxoffc", method = RequestMethod.POST)
	public @ResponseBody List<OffenderPptyContainers> cgfkchkitmtxitmtxoffc(
			@RequestBody OffenderPptyContainers offenderPptyContainers) {
		List<OffenderPptyContainers> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = oidtpritService.cgfkchkItmTxItmTxOffConc(offenderPptyContainers);
		} catch (Exception e) {
			logger.error("cgfkchkitmtxitmtxoffc", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 *
	 * @return List<Object>
	 * @param referenceCodes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/cgfkchkitmtxitmtxrefcodc", method = RequestMethod.POST)
	public @ResponseBody List<Object> cgfkchkItmTxItmTxRefCodc(@RequestBody final ReferenceCodes referenceCodes) {
		List<Object> dataObj = new ArrayList<Object>();
		try {
			dataObj = oidtpritService.cgfkchkItmTxItmTxRefCodc(referenceCodes);
		} catch (Exception e) {
			logger.error("cgfkchkItmTxItmTxRefCodc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 *
	 * @return List<Object>
	 * @param offenderPptyContainers
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtpritCgfkchkoffpioffpioffconc", method = RequestMethod.POST)
	public @ResponseBody List<OffenderPptyContainers> cgfkchkOffPiOffPiOffConc(
			@RequestBody final OffenderPptyContainers offenderPptyContainers) {
		List<OffenderPptyContainers> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = oidtpritService.cgfkchkOffPiOffPiOffConc(offenderPptyContainers);
		} catch (Exception e) {
			logger.error("cgfkchkOffPiOffPiOffConc", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 *
	 * @return List<Object>
	 * @param omsModules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/printreportoldgetprintformatcur", method = RequestMethod.GET)
	public @ResponseBody List<Object> printReportOldgetPrintFormatCur(@RequestBody final OmsModules omsModules) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.printReportOldgetPrintFormatCur(omsModules);
		} catch (Exception e) {
			logger.error("printReportOldgetPrintFormatCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param caseloadFormatPrinter
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/printreportoldgetprinternamecur", method = RequestMethod.GET)
	public @ResponseBody List<Object> printReportOldgetPrinterNameCur(
			@RequestBody final CaseloadFormatPrinter caseloadFormatPrinter) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.printReportOldgetPrinterNameCur(caseloadFormatPrinter);
		} catch (Exception e) {
			logger.error("printReportOldgetPrinterNameCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 *
	 * @return List<Object>
	 * @param sysytemProfies
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/runreportonthewebrolecur", method = RequestMethod.GET)
	public @ResponseBody List<Object> runReportOnTheWebroleCur(@RequestBody final SystemProfiles sysytemProfies) {
		List<Object> dataObj = new ArrayList<Object>();
		try {
			dataObj = oidtpritService.runReportOnTheWebroleCur(sysytemProfies);
		} catch (Exception e) {
			logger.error("runReportOnTheWebroleCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 *
	 * @return OmsModules
	 *
	 * @param omsModules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/runreportonthewebgetprintformatcur", method = RequestMethod.GET)
	public @ResponseBody List<Object> runReportOnTheWebgetPrintFormatCur(@RequestBody final OmsModules omsModules) {
		List<Object> listOfRecords = null;
		try {
			listOfRecords = oidtpritService.runReportOnTheWebgetPrintFormatCur(omsModules);
		} catch (Exception e) {
			logger.error("runReportOnTheWebgetPrintFormatCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query calling
	 *
	 * @return List<Object>
	 * @param omsModules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/printreportgetprintformatcur", method = RequestMethod.GET)
	public @ResponseBody List<Object> printReportgetPrintFormatCur(@RequestBody final OmsModules omsModules) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.printReportgetPrintFormatCur(omsModules);
		} catch (Exception e) {
			logger.error("printReportgetPrintFormatCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 *
	 * @return List<Object>
	 * @param caseloadFormatPrinter
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/printreportgetprinternamecur", method = RequestMethod.GET)
	public @ResponseBody List<Object> printReportgetPrinterNameCur(
			@RequestBody final CaseloadFormatPrinter caseloadFormatPrinter) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.printReportgetPrinterNameCur(caseloadFormatPrinter);
		} catch (Exception e) {
			logger.error("printReportgetPrinterNameCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 *
	 * @return List<Object>
	 * @param systemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/printreportrolecur", method = RequestMethod.GET)
	public @ResponseBody List<Object> printReportroleCur(@RequestBody final SystemProfiles systemProfiles) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.printReportroleCur(systemProfiles);
		} catch (Exception e) {
			logger.error("printReportroleCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 *
	 * @return List<Object>
	 * @param systemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/printreportrolepwd", method = RequestMethod.GET)
	public @ResponseBody List<Object> printReportrolePwd(@RequestBody final SystemProfiles systemProfiles) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.printReportrolePwd(systemProfiles);
		} catch (Exception e) {
			logger.error("printReportrolePwd", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 *
	 * @return List<Object>
	 * @param systemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtprit/printreportlreportalias", method = RequestMethod.GET)
	public @ResponseBody List<Object> printReportlReportAlias(@RequestBody final SystemProfiles systemProfiles) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.printReportlReportAlias(systemProfiles);
		} catch (Exception e) {
			logger.error("printReportlReportAlias", e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 *
	 * @return List<Object>
	 * @param systemProfiles
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/oidtprit/printreportlsystemprofilesdesccur", method = RequestMethod.GET)
	public @ResponseBody List<Object> printReportlSystemProfilesDescCur(
			@RequestBody final SystemProfiles systemProfiles) {
		List<Object> dataObj = null;
		try {
			dataObj = new ArrayList<Object>();
			dataObj = oidtpritService.printReportlSystemProfilesDescCur(systemProfiles);
		} catch (Exception e) {
			logger.error("printReportlSystemProfilesDescCur", e);
		}
		return dataObj;
	}

	/**
	 * getting cgfkItmTxPropertyLocation LOV values
	 */
	@RequestMapping(value = "/oidtprit/cgfkItmTxPropertyLocationRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> getcgfkItmTxPropertyLocationRecordGroup(
			@RequestParam(value = "caseLoadId") final String caseLoadId,
			@RequestParam(value = "offenderbookid") final Integer offenderbookid) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidtpritService.cgfkItmTxPropertyLocationRgroup(caseLoadId, offenderbookid);
		} catch (Exception e) {
			logger.error("getcgfkItmTxPropertyLocationRecordGroup", e);
		}
		return recordList;
	}

}