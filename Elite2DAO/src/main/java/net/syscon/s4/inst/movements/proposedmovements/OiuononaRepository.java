package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.StgRelationships;

/**
 * interface OiuononaRepository
 */
public interface OiuononaRepository {
	
	List<StgRelationships> stgRelationshipsExecuteQuery(StgRelationships objStgRelationships) ;

	List<OffenderNaDetails> offNonAssoExecuteQuery(OffenderNaDetails objOffenderNaDetails) ;


}
