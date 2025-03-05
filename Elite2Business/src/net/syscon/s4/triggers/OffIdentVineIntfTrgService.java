package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifier;

public interface OffIdentVineIntfTrgService {
	
	 void offIdentVineIntfTrg(final List<OffenderIdentifier> offIdList,final List<OffenderIdentifier> oldUpdateList, final String operation) ;
}
