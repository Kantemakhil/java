package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.inst.movements.proposedmovements.OiuononaRepository;

/**
 * class  OiuononaRepositoryImpl
 */
@Repository
public class OiuononaRepositoryImpl extends RepositoryBase implements OiuononaRepository {

	@Override
	public List<StgRelationships> stgRelationshipsExecuteQuery(StgRelationships objStgRelationships) {
		final String sql = getQuery("OIUONONA_STGRELATIONSHIPS_FIND_STG_RELATIONSHIPS");
		final ArrayList<StgRelationships> returnList = (ArrayList<StgRelationships>) namedParameterJdbcTemplate.query(
				sql, createParams("offender_book_id", objStgRelationships.getOffenderBookId()),
				new BeanPropertyRowMapper<StgRelationships>(StgRelationships.class));
		return returnList;
	}

	@Override
	public List<OffenderNaDetails> offNonAssoExecuteQuery(OffenderNaDetails objOffenderNaDetails) {
		final String sql = getQuery("OIUONONA_OFFNONASSO_FIND_OFFENDER_NA_DETAILS");
		final ArrayList<OffenderNaDetails> returnList = (ArrayList<OffenderNaDetails>) namedParameterJdbcTemplate.query(
				sql, createParams("OFFENDER_ID", objOffenderNaDetails.getOffenderId()),
				new BeanPropertyRowMapper<OffenderNaDetails>(OffenderNaDetails.class));
		return returnList;
	}

}
