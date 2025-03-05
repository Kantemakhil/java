package net.syscon.s4.inst.securitythreatgroupmaintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VStgLocationMembers;
import net.syscon.s4.inst.classification.beans.Assessments;

/**
 * Interface OiistgmbRepository
 */

public interface OiistgmbRepository {

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<AgencyLocations> getLocationDescription(AgencyLocations paramBean);

	List<OffenderAssessments> getStatus(OffenderAssessments paramBean);

	List<VStgLocationMembers> vStgLocationMembersExecuteQuery(VStgLocationMembers objMember);

	List<ReferenceCodes> vStgLocationMembersPreQuerygetOffenderId(ReferenceCodes paramBean);

	List<LivingUnits> livingUnitsExecuteQuery(LivingUnits objLivingUnits);

	String getStatus();

	List<Assessments> getStatus(Assessments paramBean);

	List<OffenderStgAffiliations> getNumberOfMembers(OffenderStgAffiliations paramBean);

	Integer getCountOfNumber(String agyLocId,BigDecimal livingUnitId,Long stgId);

	String getStatusAssesments(String profileType);

	String getStatusOffenderAssesmentsDataOne(Long offenderBookId, String profileType);

	String getStatusOffenderAssesmentsDataTwo(Long offenderBookId, String profileType);

	String getStatusData(String assesmentOne);

}
