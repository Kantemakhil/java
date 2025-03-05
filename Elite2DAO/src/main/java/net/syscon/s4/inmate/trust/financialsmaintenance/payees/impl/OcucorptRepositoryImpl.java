package net.syscon.s4.inmate.trust.financialsmaintenance.payees.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CorporateTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.trust.financialsmaintenance.payees.OcucorptRepository;

/**
 * Class OcucorptRepositoryImpl
 * 
 */
@Repository
public class OcucorptRepositoryImpl extends RepositoryBase implements OcucorptRepository {
	
	private static Logger logger = LogManager.getLogger(OcucorptRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> corporateTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_ID", new FieldMapper("corporateId")).put("CORPORATE_TYPE", new FieldMapper("corporateType"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OcucorptRepositoryImpl class Object
	 */
	public OcucorptRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CorporateTypes
	 *
	 * @return List<CorporateTypes>
	 *
	 * 
	 */
	public List<CorporateTypes> corporateTypesExecuteQuery(final CorporateTypes objSearchDao) {
		final String sql = getQuery("OCUCORPT_CORPORATETYPES_FIND_CORPORATE_TYPES");
		final RowMapper<CorporateTypes> CorporateTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CorporateTypes.class, corporateTypesMapping);
		List<CorporateTypes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("CORPORATEID", objSearchDao.getCorporateId(),
				"corporateType", objSearchDao.getCorporateType()), CorporateTypesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCorporateTypes
	 *            List<CorporateTypes>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer corporateTypesInsertCorporateTypes(final List<CorporateTypes> lstCorporateTypes) {
		String sql = getQuery("OCUCORPT_CORPORATETYPES_INSERT_CORPORATE_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CorporateTypes corporateTypes : lstCorporateTypes) {
			parameters.add(new BeanPropertySqlParameterSource(corporateTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCorporateTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCorporateTypes
	 *            List<CorporateTypes>
	 *
	 * 
	 */
	public Integer corporateTypesUpdateCorporateTypes(final List<CorporateTypes> lstCorporateTypes) {
		String sql = getQuery("OCUCORPT_CORPORATETYPES_UPDATE_CORPORATE_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CorporateTypes corporateTypes : lstCorporateTypes) {
			parameters.add(new BeanPropertySqlParameterSource(corporateTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCorporateTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCorporateTypes
	 *            List<CorporateTypes>
	 *
	 * 
	 */
	public Integer corporateTypesDeleteCorporateTypes(final List<CorporateTypes> lstCorporateTypes) {
		String sql = getQuery("OCUCORPT_CORPORATETYPES_DELETE_CORPORATE_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CorporateTypes corporateTypes : lstCorporateTypes) {
			parameters.add(new BeanPropertySqlParameterSource(corporateTypes));
		}
		try {
			String tableName = "CORPORATE_TYPES";
			String whereClause = "CORPORATE_ID=:corporateId AND CORPORATE_TYPE=:nbtCorporateType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method corporateTypesDeleteCorporateTypes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCorporateTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCorpTypeRecordGroup() {
		final String sql = getQuery("OCUCORPT_FIND_RGCORPTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * prevCaseloadCorpExists
	 *
	 * @param params
	 *
	 */
	public Integer prevCaseloadCorpExists(final Corporates paramBean) {
		Integer returnData = null;
		final String sql = getQuery("OCUCORPT_PREV_CASELOAD_CORP_EXISTS");
		returnData = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CASELOADID", paramBean.getCaseloadId()), Integer.class);
		return returnData;
	}

}
