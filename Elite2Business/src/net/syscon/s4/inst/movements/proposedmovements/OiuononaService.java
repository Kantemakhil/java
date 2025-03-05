package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNaDetailsCommitBean;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.im.beans.StgRelationshipsCommitBean;

/**
 * interface OiuononaService
 */
public interface OiuononaService {
	List<StgRelationships> stgRelationshipsExecuteQuery(StgRelationships objStgRelationships) ;

	Integer offNonAssoCommit(OffenderNaDetailsCommitBean CommitBean) ;

	Integer stgRelationshipsCommit(StgRelationshipsCommitBean CommitBean) ;

	List<OffenderNaDetails> offNonAssoExecuteQuery(OffenderNaDetails objOffenderNaDetails) ;


}
