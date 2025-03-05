package net.syscon.s4.iwp;

import java.util.List;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;

/**
 * Interface OcumaoffRepository
 */
public interface OcumaoffRepository {
	List<StaffLocationRoles> addOfficerExecuteQuery(final StaffLocationRoles searchBean);

	Integer updateSupervosor(final List<StaffLocationRoles> updateList);

}
