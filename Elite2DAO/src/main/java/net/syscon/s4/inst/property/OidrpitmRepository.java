package net.syscon.s4.inst.property;


import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;

import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Interface OidrpitmRepository
 * 
 */
public interface OidrpitmRepository {
	List<ReferenceCodes> cgfkOffPiPropertyTypeRecordGroup();

	List<OffenderPptyItems> offPiExecuteQuery(OffenderPptyItems obj);

	Integer offpiInsertOffenderPptyItems(List<OffenderPptyItems> list);

	List<ReferenceCodes> rgCondnRecordGroup();

	List<OffenderPptyItems> vPheadOnCheckDeleteMaster(OffenderPptyItems paramBean);

	Integer offPiUpdateOffenderPptyItems(List<OffenderPptyItems> list);

	List<ReferenceCodes> cgfkOffPiReceivedFromRecordGroup();

	List<ReferenceCodes> offPiPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> cgwhenNewFormInstance(SysDual paramBean);

	Integer offPiDeleteOffenderPptyItems(List<OffenderPptyItems> list);

	List<ReferenceCodes> rgColorRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<ReferenceCodes> cgfkchkOffPiOffPiRef(ReferenceCodes paramBean);

	Integer offEmPreInsertc(Integer offBookid);
	
	List<ReferenceCodes> offRecForm();

	String getDescrption(String propertyType);

}
