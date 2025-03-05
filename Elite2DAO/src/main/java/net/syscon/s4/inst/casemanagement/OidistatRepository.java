package net.syscon.s4.inst.casemanagement;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBillingProfiles;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyBillingProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatuses;

/**
 * Interface OidistatRepository
 */
public interface OidistatRepository {
	OffenderImprisonStatuses offImpsPreInsertStatus(OffenderImprisonStatuses paramBean);

	OffenderImprisonStatuses offBkgOnCheckDeleteMaster(OffenderImprisonStatuses paramBean);

	String processBillProfileCaseLoads(String agyLocId);

	String processBillProfilGetCountCur(String caseloadId);

	List<ReferenceCodes> rgImprisonmentStaRecordGroup();

	Integer processBillProfileAgencyDelayDays(AgencyBillingProfiles paramBean);

	List<OffenderImprisonStatuses> offImpsExecuteQuery(OffenderImprisonStatuses objOffImpSt);

	List<AgencyLocations> rgAgyLocIdRecordGroup(String caseloadId);

	Date chkImpDateMovement(OffenderExternalMovements paramBean);

	Integer processBillProfileDelayDays(OffenderImprisonStatuses paramBean);

	Date chkImpDateEffective(OffenderImprisonStatuses paramBean);

	OffenderImprisonStatuses offImpsPostQuery(OffenderImprisonStatuses paramBean);

	Integer offImpsInsertOffenderImprisonStatuses(List<OffenderImprisonStatuses> lstOffImpSta);

	Long offImpsPreInsert(OffenderImprisonStatuses paramBean);

	Date processBillProfileMaxEffective(OffenderImprisonStatuses paramBean);

	AgencyBillingProfiles processBillProfileRecord(OffenderImprisonStatuses paramBean);

	Integer offImpsUpdateOffenderImprisonStatuses(List<OffenderImprisonStatuses> lstOffImpSta);

	OffenderImprisonStatuses chkImpDateEffectiveTime(OffenderImprisonStatuses paramBean);

	String getProfileValueClient();

	Date processBillProfileMaxEffectiveDateSys(Date effectiveTime);

	Integer offenderBillingProfilesUpdateEqualOrLess(OffenderBillingProfiles paramBean);

	Integer offenderBillingProfilesUpdateGreater(OffenderBillingProfiles paramBean);

	Integer processBillProfileCount(OffenderBillingProfiles paramBean);

	Integer offenderBillingProfilesUpdateNotEqualFrequency(OffenderBillingProfiles paramBean);

	Integer offenderBillingProfilesInsertEqualFrequency(OffenderBillingProfiles paramBean);

	Integer offenderUpdateImprisonStatuses(OffenderImprisonStatuses paramBean);

}
