package net.syscon.s4.pkgs.oidcoasi;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignments;

public interface OidcoasiPkgRepository {
	BigDecimal getLivingUnitIdFmLU(String pDesc);

	List<OidcoasiOffenderAssignments> getoffAssigQuerySelectOne(final OidcoasiOffenderAssignments offAssign);

	List<OidcoasiOffenderAssignments> getoffAssigQuerySelectSecond(final OidcoasiOffenderAssignments offAssign);

	List<OidcoasiOffenderAssignments> getoffAssigQuerySelectThird(final OidcoasiOffenderAssignments offAssign);

}