package net.syscon.s4.globalrbac;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.StaffMembersCommitBean;

/**
 * Interface OumpersdService
 */
public interface OumpersdService {

	Integer staffCommit(StaffMembersCommitBean commitBean);

	List<StaffMembers> staffExecuteQuery(final Integer staffId);

}
