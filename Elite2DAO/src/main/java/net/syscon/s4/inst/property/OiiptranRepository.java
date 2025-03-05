package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.CorporateAddressV;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Interface OiiptranRepository
 */
public interface OiiptranRepository {
	List<OffenderPptyItemTxns> itmTxExecuteQuery(OffenderPptyItemTxns objOffenderPptyItemTxns);

	List<OffenderPptyItems> vPheadOnCheckDeleteMaster(OffenderPptyItems paramBean);

	List<OffenderPptyItems> offPiExecuteQuery(OffenderPptyItems objOffenderPptyItems);

	List<CorporateAddressV> cgfkchkItmTxItmTxCorp(CorporateAddressV paramBean);

	OffenderPptyContainers cgfkchkOffPiOffPiOffCon(OffenderPptyContainers paramBean);

	ReferenceCodes getDescriptionOfToStatus(String code);

	ReferenceCodes getDescriptionOfReceivedForm(String code);

	ReferenceCodes getDescriptionOfPropertyCode(String code);

	ReferenceCodes getDescriptionOfColor(String code);

	ReferenceCodes getDescriptionOfConditionCode(String code);

	ReferenceCodes getDescriptionOfAgyLocId(String code);

	AgencyInternalLocations cgfkchkOffConOffConPpty(final AgencyInternalLocations paramBean);

	List<String> findReceivedList();

	List<String> findTypeList();

	List<String> findColorList();

	List<String> findConditionList();

	List<String> findFacilityList();

	List<String> findagyLocationList(String caseLoadId);

	List<Images> offPiImagesExecuteQuery(Images searchBean);

	List<String> getImageFlag(Integer offenderBookId, Integer propertyItemSeq);
}
