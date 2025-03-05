package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.incidentsoic.beans.ExternalInvestigationOffenses;
import net.syscon.s4.inst.incidentsoic.OcucieidRepository;

@Repository
public class OcucieidRepositoryImpl extends RepositoryBase implements OcucieidRepository {

	private static Logger logger = LogManager.getLogger(OcucieidRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> iepLeveRowMapper = new ImmutableMap.Builder<String, FieldMapper>()
	.put("CONTACT_DATETIME", new FieldMapper("contactDate"))
	.build();

	@Override
	public Integer checkForInsertOrUpdateExternalInvst(String userName) {
		final String sql = getQuery("OCUCIEID_CHECK_FOR_INSERT_OR_UPDATE_EXTERNAL_INVST");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userName), Integer.class);
		} catch (Exception e) {
			count = 0;
			logger.error("error in checkForInsertOrUpdateExternalInvst() " + e);
		}
		return count;
	}

	@Override
	public Integer checkForDeleteExternalInvst(String userName) {

		final String sql = getQuery("OCUCIEID_CHECK_FOR_DELETE_EXTERNAL_INVST");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userName), Integer.class);
		} catch (Exception e) {
			count = 0;
			logger.error("error in checkForDeleteExternalInvst() " + e);
		}
		return count;
	}

	@Override
	public List<ExternalInvestigationOffenses> getAllExternalInvstDetails(
			ExternalInvestigationOffenses externalInvestigationOffenses) {

		List<ExternalInvestigationOffenses> returnList = new ArrayList<ExternalInvestigationOffenses>();

		String sql = getQuery("OCUCIEID_GET_ALL_EXTERNAL_INVST_DETAILS");
		final RowMapper<ExternalInvestigationOffenses> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				ExternalInvestigationOffenses.class, iepLeveRowMapper);

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("agencyIncidentId", externalInvestigationOffenses.getAgencyIncidentId(), "chargeSeq",
							externalInvestigationOffenses.getChargeSeq()),rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getAllExternalInvstDetails method :: " + e);
		}

		return returnList;

	}

	@Override
	public Integer insertExternalInvstDetails(List<ExternalInvestigationOffenses> insertList) {
		String sql = getQuery("OCUCIEID_INSERT_EXTERNAL_INVST_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ExternalInvestigationOffenses externalInvestigationOffenses : insertList) {
			externalInvestigationOffenses.setEidSeq(getEidSeq());
			parameters.add(new BeanPropertySqlParameterSource(externalInvestigationOffenses));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertIepLevelRecord method :: " + e);
		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer updateExternalInvstDetails(List<ExternalInvestigationOffenses> updateList) {

		String sql = getQuery("OCUCIEID_UPDATE_EXTERNAL_INVST_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ExternalInvestigationOffenses externalInvestigationOffenses : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(externalInvestigationOffenses));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in updateExternalInvstDetails method :: " + e);
		}
		return (updateList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer deteleExternalInvstDetails(List<ExternalInvestigationOffenses> deleteList) {

		String sql = getQuery("OCUCIEID_DELETE_EXTERNAL_INVST_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ExternalInvestigationOffenses externalInvestigationOffenses : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(externalInvestigationOffenses));
		}
		try {
			String tableName = "agency_incident_charges_ext_inv_detls";
			String whereClause = "agency_incident_id=:agencyIncidentId and charge_seq=:chargeSeq  and eid_seq=:eidSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deteleExternalInvstDetails", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in updateExternalInvstDetails method :: " + e);
		}
		return (deleteList.size() == returnArray.length) ? 1 : 0;
	}
	
	private Integer getEidSeq() {
		final String sql = getQuery("OCUCIEID_GET_EID_SEQ");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("error in getEidSeq() " + e);
			return null;
		}
	}

}
