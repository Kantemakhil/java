package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ItemTransactionStatii;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.OidtpritRepository;
import net.syscon.s4.inst.property.OidtpritService;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.triggers.OffenderPptyItemsT1Service;

/**
 * Class OidtpritServiceImpl
 */
@Service
public class OidtpritServiceImpl extends BaseBusiness implements OidtpritService {

	private static final String UPDATING = "UPDATING";

	@Autowired
	private OidtpritRepository oidtpritDao;

	@Autowired
	private OffenderPptyItemsT1Service offenderPptyItemsT1Service;

	/**
	 * Creates new OidtpritBusiness class Object
	 */
	public OidtpritServiceImpl() {
		super();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param offenderPptyItemTxns
	 *
	 * 
	 */
	public List<Object> vPheadOnCheckDeleteMasteritmTxCur(final OffenderPptyItemTxns offenderPptyItemTxns) {
		return oidtpritDao.vPheadOnCheckDeleteMasteritmTxCur(offenderPptyItemTxns);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param offenderPptyContainers
	 *
	 * 
	 */
	public OffenderPptyContainers oidtpritItmTxWhenValidateRecordregItems(
			final OffenderPptyContainers offenderPptyContainers) {
		return oidtpritDao.oidtpritItmTxWhenValidateRecordregItems(offenderPptyContainers);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param offenderPptyContainers
	 *
	 * 
	 */
	public OffenderPptyContainers itmTxWhenValidateRecordregItems(final OffenderPptyContainers offenderPptyContainers) {
		return oidtpritDao.itmTxWhenValidateRecordregItems(offenderPptyContainers);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param referenceCodes
	 *
	 * 
	 */
	public Object offPiPostQuerycPropertyTypeDesc(final ReferenceCodes referenceCodes) {
		return oidtpritDao.offPiPostQuerycPropertyTypeDesc(referenceCodes);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param offenderPptyContainers
	 *
	 * 
	 */
	public List<OffenderPptyContainers> cgfkchkItmTxItmTxOffConc(final OffenderPptyContainers offenderPptyContainers) {
		return oidtpritDao.cgfkchkItmTxItmTxOffConc(offenderPptyContainers);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param offenderPptyContainers
	 *
	 * 
	 */
	public List<OffenderPptyContainers> oidtpritCgfkchkItmTxItmTxOffc(
			final OffenderPptyContainers offenderPptyContainers) {
		return oidtpritDao.oidtpritCgfkchkItmTxItmTxOff2c(offenderPptyContainers);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param itemTransactionStatii
	 *
	 * 
	 */
	public ItemTransactionStatii oidtpritCgfkchkItmTxItmTxItmTs(final ItemTransactionStatii itemTransactionStatii) {
		return oidtpritDao.oidtpritCgfkchkItmTxItmTxItmTs(itemTransactionStatii);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param referenceCodes
	 *
	 * 
	 */
	public List<Object> cgfkchkItmTxItmTxRefCodc(final ReferenceCodes referenceCodes) {
		return oidtpritDao.cgfkchkItmTxItmTxRefCodc(referenceCodes);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param offenderPptyContainers
	 *
	 * 
	 */
	public List<OffenderPptyContainers> cgfkchkOffPiOffPiOffConc(final OffenderPptyContainers offenderPptyContainers) {
		return oidtpritDao.cgfkchkOffPiOffPiOffConc(offenderPptyContainers);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param omsModules
	 *
	 * 
	 */
	public List<Object> printReportOldgetPrintFormatCur(final OmsModules omsModules) {
		return oidtpritDao.printReportOldgetPrintFormatCur(omsModules);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param caseloadFormatPrinter
	 *
	 * 
	 */
	public List<Object> printReportOldgetPrinterNameCur(final CaseloadFormatPrinter caseloadFormatPrinter) {
		return oidtpritDao.printReportOldgetPrinterNameCur(caseloadFormatPrinter);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param systemProfiles
	 *
	 * 
	 */
	public List<Object> runReportOnTheWebroleCur(final SystemProfiles systemProfiles) {
		return oidtpritDao.runReportOnTheWebroleCur(systemProfiles);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param omsModules
	 *
	 * 
	 */
	public List<Object> runReportOnTheWebgetPrintFormatCur(final OmsModules omsModules) {
		return oidtpritDao.runReportOnTheWebgetPrintFormatCur(omsModules);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param omsModules
	 *
	 * 
	 */
	public List<Object> printReportgetPrintFormatCur(final OmsModules omsModules) {
		return oidtpritDao.printReportgetPrintFormatCur(omsModules);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param caseloadFormatPrinter
	 *
	 * 
	 */
	public List<Object> printReportgetPrinterNameCur(final CaseloadFormatPrinter caseloadFormatPrinter) {
		return oidtpritDao.printReportgetPrinterNameCur(caseloadFormatPrinter);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param systemProfiles
	 *
	 * 
	 */
	public List<Object> printReportroleCur(final SystemProfiles systemProfiles) {
		return oidtpritDao.printReportroleCur(systemProfiles);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param systemProfiles
	 *
	 * 
	 */
	public List<Object> printReportrolePwd(final SystemProfiles systemProfiles) {
		return oidtpritDao.printReportrolePwd(systemProfiles);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param systemProfiles
	 *
	 * 
	 */
	public List<Object> printReportlReportAlias(final SystemProfiles systemProfiles) {
		return oidtpritDao.printReportlReportAlias(systemProfiles);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param systemProfiles
	 *
	 * 
	 */
	public List<Object> printReportlSystemProfilesDescCur(final SystemProfiles systemProfiles) {
		return oidtpritDao.printReportlSystemProfilesDescCur(systemProfiles);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param offenderPptyItemTxns
	 *
	 * 
	 */
	public List<OffenderPptyItemTxns> itmTxSearchOffenderPptyItemTxns(final OffenderPptyItemTxns offenderPptyItemTxns) {
		return oidtpritDao.itmTxSearchOffenderPptyItemTxns(offenderPptyItemTxns);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderPptyItemTxns
	 *
	 * 
	 */
	@Transactional
	public Integer itmTxInsertOffenderPptyItemTxns(final List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		return oidtpritDao.itmTxInsertOffenderPptyItemTxns(lstOffenderPptyItemTxns);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderPptyItemTxns
	 *
	 * 
	 */
	@Transactional
	public Integer itmTxUpdateOffenderPptyItemTxns(final List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		return oidtpritDao.itmTxUpdateOffenderPptyItemTxns(lstOffenderPptyItemTxns);
	}

	/**
	 * Delete the records from database table
	 *
	 * @param lstOffenderPptyItemTxns
	 *
	 * 
	 */
	@Transactional
	public Integer itmTxDeleteOffenderPptyItemTxns(final List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		return oidtpritDao.itmTxDeleteOffenderPptyItemTxns(lstOffenderPptyItemTxns);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param offenderPptyItems
	 *
	 * 
	 */
	public List<OffenderPptyItems> offPiSearchOffenderPptyItems(final OffenderPptyItems offenderPptyItems) {
		List<OffenderPptyItems> returnList = new ArrayList<>();
		if (offenderPptyItems.getPropertyContainerId() != null) {
			returnList = oidtpritDao.offPiPropertyContainerIdOffenderPptyItems(offenderPptyItems);
		} else {
			returnList = oidtpritDao.offPiSearchOffenderPptyItems(offenderPptyItems);
		}
		for (final OffenderPptyItems obj : returnList) {
			if (obj.getPropertyType() != null) {
				final ReferenceCodes toStatus = oidtpritDao.getDescriptionOfPropertyCode(obj.getPropertyType());
				if (toStatus != null) {
					obj.setPropertyTypeDesc(toStatus.getDescription());
				}
			}
			if (obj.getColor() != null) {
				final ReferenceCodes toStatus = oidtpritDao.getDescriptionOfColor(obj.getColor());
				if (toStatus != null) {
					obj.setColor(toStatus.getDescription());
				}
			}
			if (obj.getConditionCode() != null) {
				final ReferenceCodes toStatus = oidtpritDao.getDescriptionOfConditionCode(obj.getConditionCode());
				if (toStatus != null) {
					obj.setConditionCode(toStatus.getDescription());
				}
			}
		}
		return returnList;

	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderPptyItems
	 *
	 * 
	 */
	@Transactional
	public Integer offPiUpdateOffenderPptyItems(final List<OffenderPptyItems> lstOffenderPptyItems) {
		Integer result = 0;
		// For getting old list data
		List<OffenderPptyItems> oldList = new ArrayList<OffenderPptyItems>();
		for (OffenderPptyItems bean : lstOffenderPptyItems) {
			bean = oidtpritDao.offPiUpdateOffenderPptyItems(bean);
			oldList.add(bean);
		}
		for (final OffenderPptyItems obj : lstOffenderPptyItems) {
			obj.setConfirmFlag("N");
			if (obj.getPropertyType() != null) {
				final ReferenceCodes toStatus = oidtpritDao.getCodeOfPropertyCode(obj.getPropertyType());
				if (toStatus != null) {
					obj.setPropertyTypeDesc(toStatus.getCode());
				}
			}
//			if (obj.getColor() != null) {
//				final ReferenceCodes toStatus = oidtpritDao.getCodeOfColor(obj.getColor());
//				if (toStatus != null) {
//					obj.setColor(toStatus.getCode());
//				}
//			}
			/*if (obj.getConditionCode() != null) {
				final ReferenceCodes toStatus = oidtpritDao.getCodeOfConditionCode(obj.getConditionCode());
				if (toStatus != null) {
					obj.setConditionCode(toStatus.getCode());
				}
			}*/
		}

		result = oidtpritDao.offPiUpdateOffenderPptyItems(lstOffenderPptyItems);
		for (int i = 0; i < lstOffenderPptyItems.size(); i++) {
			//Trigger call
			offenderPptyItemsT1Service.offenderPptyItemsT1(oldList.get(i),lstOffenderPptyItems.get(i), UPDATING);
		}
		return result;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param systemProfiles
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles systemProfiles) {
		return oidtpritDao.sysPflSearchSystemProfiles(systemProfiles);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup() {
		List<ReferenceCodes> listref = null;
		listref = oidtpritDao.cgfkItmTxFromStatusCodeRecordGroup();
		return listref;

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @param itemTransactionStatii
	 * 
	 */
	public List<ReferenceCodes> cgfkItmTxToStatusCodeRecordGroup(final ItemTransactionStatii itemTransactionStatii) {
		List<ReferenceCodes> listRefCodes = oidtpritDao.cgfkItmTxToStatusCodeRecordGroup(itemTransactionStatii);
		for (final ReferenceCodes reference : listRefCodes) {
			reference.setCode(reference.getItemToStatus());
		}
		return listRefCodes;

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return offenderPptyContainers
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkItmTxPropertyContainerRecordGroup(final String caseloadId,
			final Integer offenderBookId) {
		return oidtpritDao.cgfkItmTxPropertyContainerRecordGroup(caseloadId, offenderBookId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkItmTxPropertyLocationRgroup(final String caseloadid, final Integer offenderbookid) {
		return oidtpritDao.cgfkItmTxPropertyLocationRgroup(caseloadid, offenderbookid);

	}
}