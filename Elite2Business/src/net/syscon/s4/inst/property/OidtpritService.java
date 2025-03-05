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
 * Interface OidtpritBusiness
 * 
 */
public interface OidtpritService {

	List<ReferenceCodes> cgfkItmTxPropertyContainerRecordGroup(String caseloadId,Integer offenderBookId);

	List<ReferenceCodes> cgfkItmTxToStatusCodeRecordGroup(ItemTransactionStatii itemTransactionStatii);

	List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup();

	ItemTransactionStatii oidtpritCgfkchkItmTxItmTxItmTs(ItemTransactionStatii itemTransactionStatii);

	List<Object> printReportrolePwd(SystemProfiles systemProfiles);

	List<OffenderPptyItemTxns> itmTxSearchOffenderPptyItemTxns(OffenderPptyItemTxns offenderPptyItemTxns);

	List<OffenderPptyItems> offPiSearchOffenderPptyItems(OffenderPptyItems offenderPptyItems);

	Object offPiPostQuerycPropertyTypeDesc(ReferenceCodes referenceCodes);

	List<Object> printReportlReportAlias(SystemProfiles systemProfiles);

	List<Object> cgfkchkItmTxItmTxRefCodc(ReferenceCodes referenceCodes);

	List<Object> printReportOldgetPrinterNameCur(CaseloadFormatPrinter caseloadFormatPrinter);

	Integer itmTxUpdateOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns);

	List<Object> printReportgetPrintFormatCur(OmsModules omsModules);

	Integer itmTxDeleteOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns);

	OffenderPptyContainers itmTxWhenValidateRecordregItems(OffenderPptyContainers offenderPptyContainers);

	List<Object> printReportOldgetPrintFormatCur(OmsModules omsModules);

	Integer itmTxInsertOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns);

	List<Object> runReportOnTheWebroleCur(SystemProfiles systemProfiles);

	List<OffenderPptyContainers> oidtpritCgfkchkItmTxItmTxOffc(OffenderPptyContainers offenderPptyContainers);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles systemProfiles);

	Integer offPiUpdateOffenderPptyItems(List<OffenderPptyItems> lstOffenderPptyItems);

	List<Object> runReportOnTheWebgetPrintFormatCur(OmsModules omsModules);

	List<Object> printReportroleCur(SystemProfiles systemProfiles);

	OffenderPptyContainers oidtpritItmTxWhenValidateRecordregItems(OffenderPptyContainers offenderPptyContainers);

	List<Object> printReportgetPrinterNameCur(CaseloadFormatPrinter caseloadFormatPrinters);

	List<Object> vPheadOnCheckDeleteMasteritmTxCur(OffenderPptyItemTxns offenderPptyItemTxns);

	List<OffenderPptyContainers> cgfkchkItmTxItmTxOffConc(OffenderPptyContainers offenderPptyContainers);

	List<OffenderPptyContainers> cgfkchkOffPiOffPiOffConc(OffenderPptyContainers offenderPptyContainers);

	List<Object> printReportlSystemProfilesDescCur(SystemProfiles systemProfiles);

	List<ReferenceCodes> cgfkItmTxPropertyLocationRgroup(String caseloadid, Integer offenderbookid);
}
