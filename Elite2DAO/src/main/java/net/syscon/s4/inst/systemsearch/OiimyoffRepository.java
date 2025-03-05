package net.syscon.s4.inst.systemsearch;

import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock;

public interface OiimyoffRepository {
	List<VHeaderBlock> getMyOffenderList(final VHeaderBlock vHeaderBlock);
	String getMyOffenderCasePlanRole(Integer offenderBookId);
}
