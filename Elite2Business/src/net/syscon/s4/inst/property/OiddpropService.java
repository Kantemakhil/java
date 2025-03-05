package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.Corporate;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;
import net.syscon.s4.inst.property.bean.Persons;
import net.syscon.s4.inst.property.bean.Printer;

/**
 * Interface OiddpropService
 */
public interface OiddpropService {
	
	List<OffenderPptyContainers> cgfkItmTxPropertyContainerRecordGroup(String caseloadId,final Integer offenderBookId);

	List<Corporate> rgDspCorporateNameRecordGroup();

	List<OffenderPptyItems> offPiExecuteQuery(OffenderPptyItems offenderPptyItems);
	
	List<OffenderPptyItems> offConPiExecuteQuery(OffenderPptyItems offenderPptyItems);

	List<OffenderPptyItemTxns> itmTxExecuteQuery(OffenderPptyItemTxns offPptyItemTxns);
	
	List<ReferenceCodes> cgfkOffConTrnToAgyLocIdRecordGroup(String agyLocId);

	Integer itmTxCommit(OffenderPptyItemTxnsCommitBean commitBean);

	List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	ReferenceCodes cgfkchkOffPiOffPiRef(ReferenceCodes paramBean);

	List<ReferenceCodes> rgDisposedToPersonRecordGroup();

	ReferenceCodes cgfkchkItmTxItmTxRefCod(ReferenceCodes paramBean);

	Integer offPiCommit(OffenderPptyItemsCommitBean commitBean);

	ReferenceCodes cgfkchkOffPiOffPiRefCod(ReferenceCodes paramBean);

	OffenderPptyContainers cgfkchkItmTxItmTxOffCon(OffenderPptyContainers paramBean);

	OffenderPptyContainers cgfkchkOffPiOffPiOffCon(OffenderPptyContainers paramBean);

	OmsModules printReportOldgetPrintFormatCur(OmsModules paramBean);

	Printer printReportOldgetPrinterNameCur(CaseloadFormatPrinter paramBean);

	Printer printReportgetPrinterNameCur(CaseloadFormatPrinter paramBean);

	SystemProfiles printReportroleCur(SystemProfiles paramBean);

	SystemProfiles printReportrolePwd(SystemProfiles paramBean);

	SystemProfiles printReportlReportAlias(SystemProfiles paramBean);

	Object printReportlSystemProfilesDescCur(SystemProfiles systemProfiles);
	
	List<ReferenceCodes> getDisposedToPersonsGroup(Integer offenderBookId);
	
}
