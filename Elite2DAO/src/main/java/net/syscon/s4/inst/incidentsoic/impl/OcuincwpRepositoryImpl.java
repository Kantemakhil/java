package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.incidentsoic.beans.OffenderWeapons;
import net.syscon.s4.inst.incidentsoic.OcuincwpRepository;

@Repository
public class OcuincwpRepositoryImpl extends RepositoryBase implements OcuincwpRepository {
	
	@Autowired
	protected NamedParameterJdbcTemplate  namedParameterJdbcTemplate ;
	
	private static Logger logger = LogManager.getLogger(OcuincwpRepositoryImpl.class);
	
	private final Map<String, FieldMapper> incidentOffenderWeaponMappping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("PARTY_SEQ",  new FieldMapper("partySeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("WEAPONS_USED", new FieldMapper("weaponsUsed"))
			.put("WEAPONS_DETAIL", new FieldMapper("weaponsDetail"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();
	
	private final Map<String, FieldMapper> insertOffenderWeaponMappping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("PARTY_SEQ",  new FieldMapper("partySeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("WEAPONS_USED", new FieldMapper("weaponeUsed"))
			.put("WEAPONS_DETAIL", new FieldMapper("weaponsDetail"))
			.build();
	
	
	
	
	@Override
	public List<OffenderWeapons> offenderWeaponsData(OffenderWeapons objSearchDao) {
		
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OCUINCWP_DATA_OFFENDER_WEAPONS"),incidentOffenderWeaponMappping)
				.build();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if(objSearchDao!=null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getAgencyIncidentId() != null) {
				sqlQuery.append(" IWO.AGENCY_INCIDENT_ID =:agencyIncidentId" +" AND " );
				params.addValue("agencyIncidentId", objSearchDao.getAgencyIncidentId());
			}
			if (objSearchDao != null && objSearchDao.getPartySeq() != null) {
				sqlQuery.append(" IWO.PARTY_SEQ = :partySeq" );
				params.addValue("partySeq", objSearchDao.getPartySeq());
			}
			
		}
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<OffenderWeapons> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderWeapons.class, incidentOffenderWeaponMappping);
		List<OffenderWeapons> returnList = new ArrayList<>();
		try {
			returnList = (namedParameterJdbcTemplate.query(preparedSql, params,offenderRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}

		return returnList;

	}

	@Override
	public Integer offenderWeaponcommitUpdate(List<OffenderWeapons> updateList) {
		final String sql = getQuery("OCUINCWP_OFFEDNERWEAPONE_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderWeapons list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}


	@Override
	public Integer offenderWeaponePreInsert(Integer offenderbookId, String agencyIncidentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer offenderWeaponcommitInsert(List<OffenderWeapons> lInsertList) {
		final String sql = getQuery("OCUINCWP_INSERT_VALUES_OFFENDER_WEAPONS");
		
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderWeapons list : lInsertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (lInsertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	

	@Override
	public Integer offenderWeaponcommitDelete(List<OffenderWeapons> deleteList) {
		final String sql = getQuery("OCUINCWP_OFFENDER_WEAPONE_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderWeapons list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "INCIDENT_OFFENDER_WEAPONS";
			String whereClause = "AGENCY_INCIDENT_ID =:agencyIncidentId  and PARTY_SEQ =:partySeq AND WEAPONS_USED=:weaponsUsed";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offenderWeaponcommitDelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}


	
}
