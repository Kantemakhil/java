package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Interface OiiptranService
 */
public interface OiiptranService {

	List<OffenderPptyItemTxns> itmTxExecuteQuery(OffenderPptyItemTxns objOffenderPptyItemTxns);

	List<OffenderPptyItems> vPheadOnCheckDeleteMaster(OffenderPptyItems paramBean);

	List<OffenderPptyItems> offPiExecuteQuery(OffenderPptyItems objOffenderPptyItems);

	List<String> findReceivedList();

	List<String> findTypeList();

	List<String> findColorList();

	List<String> findConditionList();

	List<String> findFacilityList();

	List<String> findagyLocationList(String caseLoadId);

	List<Images> offPiImagesExecuteQuery(Images searchBean);
}
