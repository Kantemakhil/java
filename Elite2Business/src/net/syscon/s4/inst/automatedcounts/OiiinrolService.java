package net.syscon.s4.inst.automatedcounts;
import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.im.beans.VIntLocOffenders;

public interface OiiinrolService  {
	Integer rollListCommit(VIntLocOffenders CommitBean);

	List<VIntLocOffenders> rollListExecuteQuery(String agyLocId, Integer internalLocationId) throws SQLException;

}
