package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * interface OidvcontRepository
 */
public interface OidvcontRepository {
	List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(OffenderPptyContainers paramBean);

	Integer conTxInsertOffenderPptyConTxns(List<OffenderPptyConTxns> lstOffenderPptyConTxns);

	ReferenceCodes cgfkchkItmTxItmTxOffPi(ReferenceCodes paramBean);

	Integer itmTxInsertOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns);

	ReferenceCodes cgfkchkOffConOffConRef(ReferenceCodes paramBean);

	ReferenceCodes itmTxPostQuery(String code);

	ReferenceCodes cgfkchkConTxConTxnRefCo(ReferenceCodes paramBean);

	List<OffenderPptyItemTxns> itmTxExecuteQuery(OffenderPptyItemTxns objOffenderPptyItemTxns);

	AgencyInternalLocations cgfkchkOffConOffConPpty(AgencyInternalLocations paramBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers objOffenderPptyContainers);

	List<OffenderPptyConTxns> conTxExecuteQuery(OffenderPptyConTxns objOffenderPptyConTxns);

	Integer itmTxUpdateOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns);

	OffenderPptyItems cgfkchkItmTxItmTxOffPi(OffenderPptyItems paramBean);

	List<ReferenceCodes> cgfkConTxActionCodeRecordGroup(String propertyContainerId);

	ReferenceCodes getDescriptionOfConditionCode(String code);

	Integer offPptyConInsertOffenderAlerts(List<OffenderPptyConTxns> lstOffenderPptyConTxns);

	OffenderPptyItems offPiExecuteQuery(OffenderPptyItems objSearchDao);

	List<OffenderPptyItems> offPItemExecuteQuery(OffenderPptyItems objSearchDao);

	ReferenceCodes getDescriptionOfPropertyCode(String code);

	ReferenceCodes itmTxPostQueryColor(String code);

	Integer updateOffenderPptyContainers(OffenderPptyContainers offenderPptyContainers);

}
