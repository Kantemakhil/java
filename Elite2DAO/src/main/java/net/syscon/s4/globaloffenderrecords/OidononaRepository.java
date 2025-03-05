package net.syscon.s4.globaloffenderrecords;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.StgRelationships;

/**
 * Interface OidononaRepository
 */
public interface OidononaRepository {
	List<ReferenceCodes> cgfklkpOffNadOffNadRe2(ReferenceCodes paramBean);

	List<Object> getStgGroupDescription(SecurityThreatGroups paramBean);

	List<OffenderNonAssociations> offNaExecuteQuery(OffenderNonAssociations object);

	List<ReferenceCodes> cgfkchkOffNadOffNadRe2(ReferenceCodes paramBean);

	Integer offNadUpdateOffenderNaDetails(OffenderNaDetails object);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<ReferenceCodes> cgfklkpOffNadOffNadRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkchkOffNadOffNadRef(ReferenceCodes paramBean);

	List<OffenderNaDetails> cgrichkOffenderNonAssociat(OffenderNaDetails paramBean);

	List<ReferenceCodes> offNaDspRecipRsnRecordGroup();

	List<ReferenceCodes> offNaValidateRsn(ReferenceCodes paramBean);

	List<OffenderNaDetails> offNadExecuteQuery(OffenderNaDetails object);

	List<StgRelationships> stgRelationshipsExecuteQuery(StgRelationships object);

	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer offNadInsertOffenderNaDetails(List<OffenderNaDetails> object);

	List<ReferenceCodes> cgfkOffNadDspDescriptionRecordGroup();

	List<VHeaderBlock> cgfkOffNaDspOffenderIdDiRecordGroup();

	List<ReferenceCodes> cgfkOffNadDspDescription3RecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<ReferenceCodes> offNaChkRsn(ReferenceCodes paramBean);

	Integer offNaInsertOffenderNonAssociations(List<OffenderNonAssociations> object);

	List<VHeaderBlock> getlastFirstName(Long nsOffenderId, Long offenderId,String userName);

	String getstgDesc(BigDecimal relatedStgId);

	Integer getNbtActiveFlg(Long offenderId, Long nsOffenderId);

	Integer compareEffectiveDatec(String effectiveDate);

	Integer perInsert(Long offenderId, Long nsOffenderId);

	Integer offNadInsertList(List<OffenderNonAssociations> insertList);

	Integer offNaInsertOffenderNonAssociations(OffenderNonAssociations obj);

	Integer updateOffenderNonAssociation(OffenderNonAssociations obj);

	Integer getMaxVal(Long rootOffenderId, Long nsOffenderId);

	Integer updateOffenderNonAssociationfromWeb(OffenderNonAssociations obj);

	Integer updateOffenderNaDetails(OffenderNonAssociations obj);

	Long getRootOffenderId(String offIdDisplay,String userName);
	
	Integer offNadUpdateOffenderNaDetailsDouble(OffenderNaDetails obj);

	Integer updateOffenderNonAssociationfromWebDouble(OffenderNonAssociations data);

	void updateOffenderNonAssociationfromWebReciprocalReason(OffenderNaDetails newObj);
	
	List<ReferenceCodes> getActiveStaffMembers();

}
