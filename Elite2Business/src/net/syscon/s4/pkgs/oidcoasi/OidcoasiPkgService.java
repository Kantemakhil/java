package net.syscon.s4.pkgs.oidcoasi;

import java.util.List;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignments;

public interface OidcoasiPkgService {
	 List<OidcoasiOffenderAssignments> offenderAssignmentsQuery(final OidcoasiOffenderAssignments offAssign);
}
