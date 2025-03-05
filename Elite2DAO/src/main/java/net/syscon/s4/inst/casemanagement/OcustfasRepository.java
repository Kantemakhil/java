package net.syscon.s4.inst.casemanagement;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;

public interface OcustfasRepository {
	CasePlans checkReassignment(CasePlans paramBean);

	List<CasePlans> casePlansExecuteQuery(CasePlans objCasePlans);

	Integer casePlansPreInsert(CasePlans paramBean);

	Integer casePlansInsertCasePlans(List<CasePlans> lstCasePlans);

	List<StaffMembers> rgStaffNameRecordGroup(String agyLocId);

	Integer casePlansUpdateCasePlans(List<CasePlans> lstCasePlans);

	Integer offmrPreInsertc(Long paramBean);

	Integer offmrSacStaffIdInsertc(String lastName, String firstName, String userId);

	StaffMembers cgfkchkOffBkgsOffBkgStafc(StaffMembers paramBean);

	String casePlansPreInsertUnClass();

	Integer casePlansPreInsertMaxAssSeq(CasePlans offenderPropertyItemObj);

	String casePlansPreInsertSuperVisionLevel(Integer paramBean, Long offenderBookId);

	String agencyLocations(String agyLocId);

	Integer casePlansCount(Long offenderBookId);

	String casePlansNextReviewDatePreInsert(CasePlans offenderPropertyItemObj);

	StaffMembersV2 casPlnPreQuerySacStaffId(CasePlans offenderPropertyItemObj);
	
	List<OffenderCriminogenicNeeds> getOldDataOffenderCriminogenicNeeds(Long offenderBookId);
	
	List<OffApV1> getOldDataPlanOfActon(long offCrimNeedId);
	
	Integer getCasePlanconditions(Long offenderBookId);
	
	Integer caseplansUpdateCasePlansStatus(Integer offenderBookId, String userId);
	
	String getCasePlanPreInsertCommClass();


}
