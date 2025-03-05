package net.syscon.s4.cm.searchassign;

import java.util.List;

import net.syscon.s4.im.beans.VPimsNameSearch;

/**
 * Interface OcinamesService
 */
public interface OcinamesService {

	List<VPimsNameSearch> vNSearchExecuteQuery(VPimsNameSearch objSearch);

}
