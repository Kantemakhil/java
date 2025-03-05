package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.ItemTransactionStatii;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;

import net.syscon.s4.im.beans.OmsModules;

import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Interface OidtpritRepository
 * 
 */
public interface OidtpritRepository {
	List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup();

	List<ReferenceCodes> cgfkItmTxToStatusCodeRecordGroup(ItemTransactionStatii itemTransactionStatii);

	List<ReferenceCodes> cgfkItmTxPropertyContainerRecordGroup(String caseloadId, Integer offenderBookId);

	List<Object> printReportlReportAlias(SystemProfiles systemProfiles);

	List<Object> printReportOldgetPrinterNameCur(CaseloadFormatPrinter caseloadFormatPrinters);

	List<OffenderPptyItemTxns> itmTxSearchOffenderPptyItemTxns(OffenderPptyItemTxns offenderPptyItemTxns);

	List<OffenderPptyItems> offPiSearchOffenderPptyItems(OffenderPptyItems offenderPptyItems);

	List<Object> printReportroleCur(SystemProfiles systemProfiles);

	List<OffenderPptyContainers> cgfkchkItmTxItmTxOffConc(OffenderPptyContainers offenderPptyContainers);

	Object offPiPostQuerycPropertyTypeDesc(ReferenceCodes referenceCodes);

	List<Object> cgfkchkItmTxItmTxRefCodc(ReferenceCodes referenceCodes);

	List<OffenderPptyContainers> cgfkchkOffPiOffPiOffConc(OffenderPptyContainers offenderPptyContainers);

	Integer itmTxUpdateOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns);

	List<Object> runReportOnTheWebgetPrintFormatCur(OmsModules omsModules);

	List<Object> printReportgetPrintFormatCur(OmsModules omsModules);

	List<Object> vPheadOnCheckDeleteMasteritmTxCur(OffenderPptyItemTxns offenderPptyItemTxns);

	List<Object> printReportgetPrinterNameCur(CaseloadFormatPrinter caseloadFormatPrinter);

	Integer itmTxDeleteOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns);

	Integer itmTxInsertOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns);

	List<Object> runReportOnTheWebroleCur(SystemProfiles systemProfiles);

	List<Object> printReportlSystemProfilesDescCur(SystemProfiles systemProfiles);

	OffenderPptyContainers itmTxWhenValidateRecordregItems(OffenderPptyContainers offenderPptyContainers);

	List<Object> printReportOldgetPrintFormatCur(OmsModules omsModules);

	List<Object> printReportrolePwd(SystemProfiles systemProfiles);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles systemProfiles);

	Integer offPiUpdateOffenderPptyItems(List<OffenderPptyItems> lstOffenderPptyItems);

	List<OffenderPptyContainers> oidtpritCgfkchkItmTxItmTxOff2c(OffenderPptyContainers offenderPptyContainers);

	ItemTransactionStatii oidtpritCgfkchkItmTxItmTxItmTs(ItemTransactionStatii itemTransactionStatii);

	OffenderPptyContainers oidtpritItmTxWhenValidateRecordregItems(OffenderPptyContainers offenderPptyContainers);

	List<ReferenceCodes> cgfkItmTxPropertyLocationRgroup(String caseloadid, Integer offenderbookid);

	ReferenceCodes getDescriptionOfPropertyCode(String code);

	ReferenceCodes getDescriptionOfColor(String code);

	ReferenceCodes getDescriptionOfConditionCode(String code);

	ReferenceCodes getCodeOfPropertyCode(String description);

	ReferenceCodes getCodeOfConditionCode(String description);

	ReferenceCodes getCodeOfColor(String description);

	List<OffenderPptyItems> offPiPropertyContainerIdOffenderPptyItems(OffenderPptyItems offenderPptyItems);

	OffenderPptyItems offPiUpdateOffenderPptyItems(OffenderPptyItems bean);
}
