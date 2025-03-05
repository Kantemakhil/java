package net.syscon.s4.pkgs.pims_weight;

import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;

public interface PimsWeightService {

	String getSupLevel(final Long pOffenderBookId,final String userName);

	String getCaseloadType(final String userName);

	Long officerWork(final StaffMemberRoles searchRecord, final String userName);

	Long getWeighting(final StaffWorkAssignmentsV1 element);

	String timeServedCode(final Long offenderBookId, final Long chargeSeq, final Long seq);
}