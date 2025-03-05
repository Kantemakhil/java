package net.syscon.s4.cm.searchassign;

import java.util.List;

import net.syscon.s4.common.beans.VNameSearch2;

/**
 * Interface OsinamesRepository
 * 
 */
public interface OsinamesRepository {
	List<VNameSearch2> nameSrchExecuteQuery(VNameSearch2 searchRecord);
	
	public String getDescription(String agyLocId);

}
