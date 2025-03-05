package net.syscon.s4.inst.property;


import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;

import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;

/**
 * Interface OidrpitmService
 * 
 * 
 */
public interface OidrpitmService {
	List<ReferenceCodes> cgfkOffPiReceivedFromRecordGroup();

	List<ReferenceCodes> cgfkOffPiPropertyTypeRecordGroup();

	List<OffenderPptyItems> vPheadOnCheckDeleteMaster(OffenderPptyItems paramBean);

	List<ReferenceCodes> rgColorRecordGroup();

	List<OffenderPptyItems> offPiExecuteQuery(OffenderPptyItems objOffPptyItems);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	Integer offPiCommit(OffenderPptyItemsCommitBean commitBean);

	List<ReferenceCodes> cgfkchkOffPiOffPiRef(ReferenceCodes paramBean);

	List<ReferenceCodes> rgCondnRecordGroup();

	List<ReferenceCodes> offPiPostQuery(ReferenceCodes paramBean);
	
	List<ReferenceCodes> offRecForm();

}
