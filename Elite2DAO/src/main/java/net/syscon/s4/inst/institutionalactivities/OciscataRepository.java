package net.syscon.s4.inst.institutionalactivities;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
/**
 * Interface OciscataRepository
 */
public interface OciscataRepository {
	List<OffenderBookings> ociscataWhenNewFormInstance(OffenderBookings paramBean);

	List<ReferenceCodes> rgPsProvTypeRecordGroup();

	List<ReferenceCodes> rgServicesRecordGroup(String category);

	List<ReferenceCodes> rgPsNeedsRecordGroup();

	List<VCourseActivities> vCrsActExecuteQuery(VCourseActivities obj);

	List<ReferenceCodes> rgAcpProviderInstRecordGroup(String caseloadId);

	List<ReferenceCodes> rgRegionRecordGroup();

	List<Teams> rgTeamAgyLocsRecordGroup();

	String setupDefaults(BigDecimal listSeq);

	List<ReferenceCodes> rgPsAgeRangeRecordGroup();

	Integer vCrsActUpdateVCourseActivities(List<VCourseActivities> list);

	List<ReferenceCodes> rgTeamUnpaidWkRecordGroup(String userName);

	List<ReferenceCodes> rgEthnicityRecordGroup();

	List<ReferenceCodes> rgTeamAcpRecordGroup(String userName);

	List<ReferenceCodes> rgPsOffGrpsRecordGroup();

	List<ReferenceCodes> rgAreasRecordGroup(String environment, String region);

	List<ReferenceCodes> rgCorpLocsRecordGroup(String category);

	List<ReferenceCodes> rgPsSexRecordGroup();

	List<ReferenceCodes> rgAgyLocClRecordGroup();

	List<Areas> vCrsActPreQuery(Areas paramBean);

	List<ReferenceCodes> rgCsldCodeRecordGroup();

	List<ReferenceCodes> rgPsCategoryRecordGroup();

	List<ReferenceCodes> rgPsAvailRecordGroup();

	List<ReferenceCodes> rgAgyLocsRecordGroup();

	List<ProgramServices> ociscataWhenNewFormInstance(ProgramServices paramBean);

	List<ReferenceCodes> rgProviderDttoRecordGroup();
	
	String getDefaultDomain();
	
	String getDefaultAgency(String caseloadId);
	
	String getDescCode(String strCode,String strDesc);
	
	String getAccProgram(BigDecimal programId);
	
	List<ProgramsNonAssocTmp> getProgramsNonAssTmp();
	
	Integer getProgramsNonAssTmpCount();
	
	ProgramsNonAssocTmp chkNaPrgSrvConflictRt(ProgramsNonAssocTmp progNonAssTemp, String coursePhase);
	
	ReferenceCodes getCommDefaults(String caseloadId);

	Integer vCrsActWhenNewRecordInstance(Long crystalId);
	
	Integer checkNonAssociationConflict(BigDecimal OffenderBookIdOne,BigDecimal OffenderBookIdTwo);

	List<OffenderNonAssociations> checkNonAssociations(final BigDecimal offenderBookId,String userId);
	
	List<Offenders> getOffenderDetails(BigDecimal offenderBookId);
	
	List<Offenders> getOffenderDetailsByInd(BigDecimal offenderBookId,List<Integer> listOfProgramIds);
	
	List<Offenders> getOffenderDetailsByGang(BigDecimal offenderBookId,List<Integer> listOfProgramIds);
	
	String offenderName(Long offenderId);


	
}
