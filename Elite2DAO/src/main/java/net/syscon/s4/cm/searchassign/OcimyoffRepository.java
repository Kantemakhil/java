package net.syscon.s4.cm.searchassign;

import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

public interface OcimyoffRepository {
	List<VHeaderBlock> getMyOffenderList(final VHeaderBlock vHeaderBlock);

	List<OffenderSentConditions> offenderConditionExcuteQuery(VHeaderBlock paramBean);
}
