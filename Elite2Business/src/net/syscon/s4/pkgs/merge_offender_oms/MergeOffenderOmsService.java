package net.syscon.s4.pkgs.merge_offender_oms;

import net.syscon.s4.im.beans.Offenders;

public interface MergeOffenderOmsService {

	public void mergeOffenderRecords(Offenders fromOffenders,Offenders toOffenders, String user, Long mergeOffenderRecords);
	
}
