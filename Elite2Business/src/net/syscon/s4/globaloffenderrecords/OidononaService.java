package net.syscon.s4.globaloffenderrecords;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNaDetailsCommitBean;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderNonAssociationsCommitBean;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.im.beans.StgRelationshipsCommitBean;

/**
 * Interface OidononaService
 */
public interface OidononaService {

	List<OffenderNonAssociations> offNaExecuteQuery(OffenderNonAssociations object);

	List<ReferenceCodes> offNaDspRecipRsnRecordGroup();

	List<OffenderNaDetails> offNadExecuteQuery(OffenderNaDetails object);

	List<StgRelationships> stgRelationshipsExecuteQuery(StgRelationships object);

	List<ReferenceCodes> cgfkOffNadDspDescriptionRecordGroup();

	Integer offNaCommit(OffenderNonAssociationsCommitBean commitBean);

	Integer offNadCommit(OffenderNaDetailsCommitBean commitBean);

	Integer stgRelationshipsCommit(StgRelationshipsCommitBean commitBean);

	List<VHeaderBlock> cgfkOffNaDspOffenderIdDiRecordGroup();

	List<ReferenceCodes> cgfkOffNadDspDescription3RecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer compareEffectiveDatec(String effectiveDate);

	Integer getMaxVal(Long rootOffenderId, Long nsOffenderId);

	List<VHeaderBlock> getlastFirstName(String nsOffenderId, Long offenderId,String userName);
	
	List<ReferenceCodes> getActiveStaffMembers();

}
