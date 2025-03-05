package net.syscon.s4.inst.automatedcounts;
import java.util.List;

import net.syscon.s4.im.beans.VIntLocOffenders;

public interface OiiinrolRepository {
	List<VIntLocOffenders> rollListExecuteQuery(String agyLocId, Integer internalLocationId);

}
	