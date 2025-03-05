package net.syscon.s4.inst.securitythreatgroups;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StgRelationships;
/**
 * Interface OidstgaeRepository
 */
public interface OidstgaeRepository {
	OmsModules createFormGlobals(OmsModules paramBean);

	String cgwhennewforminstancestgLovLvlCur();

	List<ReferenceCodes> recReason2RecordGroup() ;

	ReferenceCodes cgpostQueryRltReason(ReferenceCodes paramBean);

	ReferenceCodes cgpostQueryRlt2Reason(ReferenceCodes paramBean);

	List<SecurityThreatGroups> recStgRecordGroup() ;

	List<SecurityThreatGroups> rgStg3RecordGroup(BigDecimal stgId) ;

	List<SecurityThreatGroups> recStg2RecordGroup() ;

	Integer stgRltUpdateStgRelationships(List<StgRelationships> lstStgRelationships) ;

	List<StgRelationships> stgRltExecuteQuery(StgRelationships objStgRelationships) ;

	Long stgRelationshipsPreInsert(StgRelationships paramBean);

	List<ReferenceCodes> recReasonRecordGroup() ;

	SecurityThreatGroups cgpostQueryRlt2Group(SecurityThreatGroups paramBean);

	Long stgRltPreInsertPreInsert(Long paramBean);

	StgRelationships assaliancestgassAlianceStg(StgRelationships paramBean);

	List<SecurityThreatGroups> rgStg1RecordGroup(BigDecimal stgId) ;

	List<SecurityThreatGroups> rgStg2RecordGroup(BigDecimal stgId) ;

	SecurityThreatGroups cgpostQueryRltGroup(SecurityThreatGroups paramBean);

	String cgwhenNewFormInstance();

	Integer stgRltInsertStgRelationships(StgRelationships lstStgRelationships) ;

	Integer oidstgaeOnCommitOnCommit(StgRelationships paramBean);

	Long assAlianceStgassAlianceStg(Long relatedStgId, Long stgId);

	void deActivateAlianceUpdate(StgRelationships data);

	void omsStgNonaEnemychildToStg(StgRelationships data);

	void reActiveEnemy(StgRelationships data);

	void deActiveEnemy(StgRelationships data);

	BigDecimal stgRltGroupPostChange(BigDecimal stgId, BigDecimal relatedStgId);

	BigDecimal stgRltCheckBoxChange(BigDecimal stgId, BigDecimal relatedStgId);

	BigDecimal stgRelationshipsCheckBoxChange(BigDecimal stgId, BigDecimal relatedStgId);

	void reActivateAlianceUpdate(StgRelationships data);

}
