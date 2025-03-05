package net.syscon.s4.pkgs.comunity_pkg;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.common.beans.StaffMembers;

public interface ComunityPkgService {
	Long getOfficerPo(final VStaffLocation objSearchDao);

	Integer getPrimaryOwnPerOfficer(final StaffMembers searchRecord);
}
