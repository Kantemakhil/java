package net.syscon.s4.iwp;

import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRolesCommitBean;
import java.util.List;

/**
 * Interface OcumaoffService
 */
public interface OcumaoffService {
	List<StaffLocationRoles> addOfficerExecuteQuery(final StaffLocationRoles searchBean);

	Integer updateSupervosor(final StaffLocationRolesCommitBean commitBean);

}
