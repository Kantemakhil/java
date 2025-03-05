package net.syscon.s4.inst.offenderissuestracking;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.OffenderGrievanceTxns;
import net.syscon.s4.im.beans.OffenderGrievances;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OidissueRepository
 */
public interface OidissueRepository {
	ReferenceCodes isActiveTxn(ReferenceCodes paramBean);

	List<GrievanceTxns> rgTxnTypeRecordGroup(String grieveType);

	List<ReferenceCodes> rgFindingAllRecordGroup();

	List<ReferenceCodes> rgGrievReasonAllRecordGroup();

	String offenderGrievancesPostQueryReportDate(String agyLocId);

	Long offenderGrievancesPreInsert();

	List<ReferenceCodes> rgLevelRecordGroup();

	AgencyLocations offenderGrievancesWhenNewRecordInstance(AgencyLocations paramBean);

	ReferenceCodes offenderGrievancesPostQuerylvGrievanceDescCur(ReferenceCodes paramBean);

	Integer offenderGrievanceTxnsDeleteOffenderGrievanceTxns(List<OffenderGrievanceTxns> lstOffenderGrievanceTxns);

	List<ReferenceCodes> rgGrievReasonRecordGroup(String grieveType ,String user );

	List<OffenderGrievances> offenderGrievancesExecuteQuery(OffenderGrievances objOffenderGrievances);

	ReferenceCodes offenderGrievanceTxnsWhenValidateRecord(ReferenceCodes paramBean);

	List<OffenderGrievanceTxns> offenderGrievanceTxnsExecuteQuery(OffenderGrievanceTxns objOffenderGrievanceTxns);

	Integer oidissueWhenNewFormInstance(String userName);

	List<ReferenceCodes> rgLevelAllRecordGroup();

	ReferenceCodes offenderGrievanceTxnsWhenNewRecordInstance(ReferenceCodes paramBean);

	OmsModules createFormGlobals(OmsModules paramBean);

	Long daysRespondData(String grieveType, String txnType);

	OffenderGrievanceTxns offenderGrievancesPostQuery(OffenderGrievanceTxns paramBean);

	Integer offenderGrievanceTxnsUpdateOffenderGrievanceTxns(List<OffenderGrievanceTxns> lstOffenderGrievanceTxns);

	ReferenceCodes offenderGrievanceTxnsPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> rgStatusRecordGroup();

	List<OffenderGrievances> offBkgOnCheckDeleteMaster(OffenderGrievances paramBean);

	List<AgencyLocations> rgAgyLocAllRecordGroup();

	OffenderGrievanceTxns offenderGrievanceTxnsWhenNewRecordInstance(OffenderGrievanceTxns paramBean);

	List<GrievanceTypes> rgGrievTypeRecordGroup(String user);

	Long offenderGrievanceTxnsPreInsert(Long paramBean);

	List<StaffMembers> rgStaffRecordGroup(String userName);

	List<StaffMembers> rgStaffAllRecordGroup();

	List<GrievanceTxns> rgTxnTypeAllRecordGroup(String grieveType);

	List<ReferenceCodes> rgFindingRecordGroup();

	List<AgencyLocations> rgAgyLocRecordGroup();

	List<OffenderGrievanceTxns> offenderGrievancesKeyDelrec(OffenderGrievanceTxns paramBean);

	Integer offenderGrievanceTxnsInsertOffenderGrievanceTxns(List<OffenderGrievanceTxns> lstOffenderGrievanceTxns);

	StaffMembers offenderGrievanceTxnsPostQuery(StaffMembers paramBean);

	OffenderGrievanceTxns offenderGrievancesOnCheckDeleteMaster(OffenderGrievanceTxns paramBean);

	Integer offenderGrievancesInsertOffenderGrievances(List<OffenderGrievances> lstOffenderGrievances);

	ReferenceCodes oidissueKeyCommit(ReferenceCodes paramBean);

	List<VHeaderBlock> offbkgInstGlobalQuery(VHeaderBlock searchBean);

	String caseloadTypeData(String userId);

	Integer offenderGrievancesUpdateOffenderGrievances(List<OffenderGrievances> lstOffenderGrievances);

	OffenderGrievanceTxns offenderGrievancesPostQueryStatus(OffenderGrievances paramBean);

	OffenderGrievanceTxns offenderGrievancesPostQueryStatusDesc(OffenderGrievances paramBean);

	List<OffenderGrievanceTxns> offenderGrievancesPostQueryStatusGrievanceLevel(OffenderGrievances paramBean);

	String offenderGrievancesPostQueryGrievanceLevel(OffenderGrievanceTxns paramBean);

	String offenderGrievancesPostQueryAgyLocId(OffenderGrievances paramBean);

	Integer offenderGrievanceTxnsUpdateOffenderGrievanceTxnsPreInsert(OffenderGrievanceTxns lstOffenderGrievanceTxns);

	List<OffenderGrievances> maxGrievanceIdComparison(Long grievanceId);

	Long maxGrievanceId();

	String maxGrievanceType(Long paramBean);

	Integer offenderGrievanceTxnsDaysLeft(OffenderGrievanceTxns searchRecord);

	GrievanceTypes offenderGrievanceTxnsWhenValidateRecord(String grievType);

	Integer grievanceIdCur(Long grievanceId);
	Integer validationStaff(Long grievanceId);
	
	Integer offenderGrievancesDeleteOffenderGrievances(List<OffenderGrievances> lstOffenderGrievances);


}
