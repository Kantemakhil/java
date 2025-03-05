package net.syscon.s4.inst.offenderissuestracking;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.OffenderGrievanceTxns;
import net.syscon.s4.im.beans.OffenderGrievanceTxnsCommitBean;
import net.syscon.s4.im.beans.OffenderGrievances;
import net.syscon.s4.im.beans.OffenderGrievancesCommitBean;

/**
 * Interface OidissueService
 */
public interface OidissueService {
	List<GrievanceTxns> rgTxnTypeRecordGroup(String grieveType);

	List<ReferenceCodes> rgFindingAllRecordGroup();

	List<OffenderGrievanceTxns> offenderGrievancesKeyDelrec(OffenderGrievanceTxns paramBean);

	ReferenceCodes isActiveTxn(ReferenceCodes paramBean);

	List<ReferenceCodes> rgLevelRecordGroup();

	List<ReferenceCodes> rgGrievReasonRecordGroup(String grieveType ,String user);

	List<OffenderGrievances> offenderGrievancesExecuteQuery(OffenderGrievances objOffenderGrievances);

	List<OffenderGrievanceTxns> offenderGrievanceTxnsExecuteQuery(OffenderGrievanceTxns objOffenderGrievanceTxns);

	List<ReferenceCodes> rgLevelAllRecordGroup();

	List<ReferenceCodes> rgStatusRecordGroup();

	AgencyLocations offenderGrievancesWhenNewRecordInstance(AgencyLocations paramBean);

	List<AgencyLocations> rgAgyLocAllRecordGroup();

	ReferenceCodes offenderGrievanceTxnsWhenValidateRecord(ReferenceCodes paramBean);

	List<GrievanceTypes> rgGrievTypeRecordGroup(String user);

	Integer offenderGrievanceTxnsCommit(OffenderGrievanceTxnsCommitBean CommitBean);

	List<StaffMembers> rgStaffRecordGroup(String userName);

	List<StaffMembers> rgStaffAllRecordGroup();

	List<GrievanceTxns> rgTxnTypeAllRecordGroup(String grieveType);

	List<ReferenceCodes> rgFindingRecordGroup();

	List<AgencyLocations> rgAgyLocRecordGroup();

	OffenderGrievanceTxns offenderGrievancesOnCheckDeleteMaster(OffenderGrievanceTxns paramBean);

	OffenderGrievanceTxns offenderGrievancesPostQuery(OffenderGrievanceTxns paramBean);

	ReferenceCodes offenderGrievanceTxnsPostQuery(ReferenceCodes paramBean);

	Integer offenderGrievancesCommit(OffenderGrievancesCommitBean CommitBean);

	OffenderGrievanceTxns offenderGrievanceTxnsWhenNewRecordInstance(OffenderGrievanceTxns paramBean);

	ReferenceCodes oidissueKeyCommit(ReferenceCodes paramBean);

	List<OffenderGrievances> offBkgOnCheckDeleteMaster(OffenderGrievances paramBean);

	List<VHeaderBlock> offbkgGlobalQuery(VHeaderBlock searchBean);

	String offenderGrievancesPostQueryReportDate(String agyLocId);

	Long daysRespondData(String grieveType, String txnType);

	List<OffenderGrievances> maxGrievanceIdComparison(Long grievanceId);

	Integer validationStaff(Long grievanceId);

	Integer oidissueWhenNewFormInstance(String userName);

}
