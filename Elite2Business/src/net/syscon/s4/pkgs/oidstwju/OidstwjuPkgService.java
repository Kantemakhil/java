package net.syscon.s4.pkgs.oidstwju;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OidstwjuPkgService {

	Integer chkNonAssociation(final OffenderIndSchedules offSch);

	Integer insNotification(final OffenderIndSchedules offSch, final String userName);

}
