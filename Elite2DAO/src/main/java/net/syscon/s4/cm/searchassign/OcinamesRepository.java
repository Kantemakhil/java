package net.syscon.s4.cm.searchassign;

import java.util.List;

import net.syscon.s4.im.beans.VPimsNameSearch;

/**
 * Interface OcinamesRepository
 */
public interface OcinamesRepository {
	List<VPimsNameSearch> vNSearchExecuteQuery(VPimsNameSearch objSearch);
}
