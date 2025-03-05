package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNaDetailsCommitBean;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.im.beans.StgRelationshipsCommitBean;
import net.syscon.s4.inst.movements.proposedmovements.OiuononaRepository;
import net.syscon.s4.inst.movements.proposedmovements.OiuononaService;

/**
 * class  OiuononaServiceImpl
 */
@Service
public class OiuononaServiceImpl implements OiuononaService {
	
	@Autowired
	private OiuononaRepository oiuononaRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param objStgRelationships StgRelationships
	 *
	 * @throws SQLException
	 */
	@Override
	public List<StgRelationships> stgRelationshipsExecuteQuery(StgRelationships objStgRelationships) {
		return oiuononaRepository.stgRelationshipsExecuteQuery(objStgRelationships);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param CommitBean OffenderNaDetailsCommitBean
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer offNonAssoCommit(OffenderNaDetailsCommitBean CommitBean) {
		return null;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param CommitBean StgRelationshipsCommitBean
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer stgRelationshipsCommit(StgRelationshipsCommitBean CommitBean) {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objStgRelationships OffenderNaDetails
	 *
	 * @throws SQLException
	 */
	@Override
	public List<OffenderNaDetails> offNonAssoExecuteQuery(OffenderNaDetails objOffenderNaDetails) {
		return oiuononaRepository.offNonAssoExecuteQuery(objOffenderNaDetails);
	}
	
	

}
