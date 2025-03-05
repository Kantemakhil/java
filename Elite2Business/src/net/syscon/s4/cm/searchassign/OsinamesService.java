package net.syscon.s4.cm.searchassign;

import java.util.List;

import net.syscon.s4.common.beans.VNameSearch2;

/**
 * OsinamesService
 */
public interface OsinamesService {

	List<VNameSearch2> nameSrchExecuteQuery(VNameSearch2 searchRecord);
}
