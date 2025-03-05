package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.im.beans.StgRelationshipsCommitBean;

/**
 * Interface OidstgaeService
 */
public interface OidstgaeService {
	Long stgRltPreInsert(Long paramBean);

	List<ReferenceCodes> recReason2RecordGroup();

	ReferenceCodes cgpostQueryRltReason(ReferenceCodes paramBean);

	List<SecurityThreatGroups> recStgRecordGroup();

	List<SecurityThreatGroups> rgStg3RecordGroup(BigDecimal stgId);

	List<SecurityThreatGroups> recStg2RecordGroup();

	SecurityThreatGroups cgpostQueryRltGroup(SecurityThreatGroups paramBean);

	List<StgRelationships> stgRltExecuteQuery(StgRelationships objStgRelationships);

	String stgRltCommit(StgRelationshipsCommitBean commitBean);

	String cgwhenNewFormInstance();

	List<ReferenceCodes> recReasonRecordGroup();

	SecurityThreatGroups cgpostQueryRlt2Group(SecurityThreatGroups paramBean);

	Long stgRelationshipsPreInsert(StgRelationships paramBean);

	StgRelationships assAlianceStg(StgRelationships paramBean);

	List<SecurityThreatGroups> rgStg1RecordGroup(BigDecimal stgId);

	Integer oidstgaeOnCommit(StgRelationships paramBean);

	List<SecurityThreatGroups> rgStg2RecordGroup(BigDecimal stgId);

	ReferenceCodes cgpostQueryRlt2Reason(ReferenceCodes paramBean);

	String stgRelationshipsCommit(StgRelationshipsCommitBean commitBean);

	List<StgRelationships> stgRelationshipsExecuteQuery(StgRelationships searchRecord);

	String groupLovRecordGroupNumber();

	BigDecimal stgRltGroupPostChange(BigDecimal stgId, BigDecimal relatedStgId);

	BigDecimal stgRltCheckBoxChange(BigDecimal stgId, BigDecimal relatedStgId);

	BigDecimal stgRelationshipsCheckBoxChange(BigDecimal stgId, BigDecimal relatedStgId);

}
