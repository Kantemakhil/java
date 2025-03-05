package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.Corporate;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemEvents;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.Persons;
import net.syscon.s4.inst.property.bean.Printer;

/**
 * Interface OiddpropRepository
 */
public interface OiddpropRepository {

	ReferenceCodes cgfkchkOffPiOffPiRefCod(ReferenceCodes paramBean);

	List<OffenderPptyContainers> cgfkItmTxPropertyContainerRecordGroup(String caseloadId, final Integer offenderBookId);

	Integer itmTxInsertOffenderPptyItemTxns(List<OffenderPptyItemTxns> offPptyItemTxns);

	List<Corporate> rgDspCorporateNameRecordGroup();

	List<OffenderPptyItems> offPiExecuteQuery(OffenderPptyItems offenderPptyItems);

	List<OffenderPptyItems> offConPiExecuteQuery(OffenderPptyItems offenderPptyItems);

	OffenderPptyContainers cgfkchkOffPiOffPiOffCon(OffenderPptyContainers paramBean);

	Integer offPiUpdateOffenderPptyItems(List<OffenderPptyItems> offenderPptyItems);

	ReferenceCodes cgfkchkOffPiOffPiRef(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkItmTxItmTxRefCod(ReferenceCodes paramBean);

	List<OffenderPptyItemTxns> itmTxExecuteQuery(OffenderPptyItemTxns offPptyItemTxns);

	List<ReferenceCodes> cgfkOffConTrnToAgyLocIdRecordGroup(String agyLocId);

	List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	OffenderPptyContainers cgfkchkItmTxItmTxOffCon(OffenderPptyContainers paramBean);

	List<ReferenceCodes> rgDisposedToPersonRecordGroup();

	Integer itmTxUpdateOffenderPptyItemTxns(List<OffenderPptyItemTxns> offPptyItemTxns);

	OmsModules printReportOldgetPrintFormatCur(OmsModules paramBean);

	Printer printReportOldgetPrinterNameCur(CaseloadFormatPrinter paramBean);

	Printer printReportgetPrinterNameCur(CaseloadFormatPrinter paramBean);

	SystemProfiles printReportroleCur(SystemProfiles paramBean);

	Object printReportlSystemProfilesDescCur(SystemProfiles systemProfiles);

	ReferenceCodes getDescriptionOfColor(String code);

	ReferenceCodes getDescriptionOfConditionCode(String code);

	Integer getEventSeq(Integer OffenderBookId);

	Integer itmTxInsertOffenderPptyItemEvents(List<OffenderPptyItemEvents> insertEventList);

	Integer updateOffenderPptyContainers(List<OffenderPptyContainers> updateCon);

	List<OffenderPptyItems> getOffenderPptyDetails(OffenderPptyContainers offenderPptyContainers);

	OffenderPptyItems getOldDateOfOffenderPptyItems(OffenderPptyItems bean);
	
	List<ReferenceCodes> getDisposedToPersonsGroup(Integer offenderBookId);

}
