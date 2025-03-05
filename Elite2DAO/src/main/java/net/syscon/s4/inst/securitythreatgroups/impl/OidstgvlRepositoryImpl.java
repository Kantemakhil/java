package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.securitythreatgroups.OidstgvlRepository;

/**
 * Class OidstgvlRepositoryImpl
 */
@Repository
public class OidstgvlRepositoryImpl extends RepositoryBase implements OidstgvlRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstgvlRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> stgValidationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", 						new FieldMapper("stgId"))
			.put("MAX(VALIDATION_DATE)", 		new FieldMapper("max(validationDate)"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTION", 						new FieldMapper("action"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("DESIGNATION", 				new FieldMapper("designation"))
			.put("REASON", 						new FieldMapper("reason"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();

	/**
	 * Creates new OidstgvlRepositoryImpl class Object
	 */
	public OidstgvlRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StgValidations
	 *
	 * @return List<StgValidations>
	 */
	public List<StgValidations> stgValidationsExecuteQuery(final StgValidations objSearchDao) {
		final String sql = getQuery("OIDSTGVL_STGVALIDATIONS_FIND_STG_VALIDATIONS");
		final RowMapper<StgValidations> StgValRowMapper = Row2BeanRowMapper.makeMapping(sql, StgValidations.class,
				stgValidationsMapping);
		List<StgValidations> returnList = new ArrayList<StgValidations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STGID", objSearchDao.getStgId()),
					StgValRowMapper);
		} catch (Exception e) {
			logger.error("stgValidationsExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstStgValidations
	 *            List<StgValidations>
	 *
	 * @return List<Integer>
	 */
	public Integer stgvalidationsInsertStgValidations(final List<StgValidations> lstStgValidations) {
		final String sql = getQuery("OIDSTGVL_STGVALIDATIONS_INSERT_STG_VALIDATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgValidations stgValidations : lstStgValidations) {
			parameters.add(new BeanPropertySqlParameterSource(stgValidations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgValidations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgActionRecordGroup() {
		final String sql = getQuery("OIDSTGVL_FIND_RGACTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("stgValidationsWhenCreateRecord", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgDesignationRecordGroup() {
		final String sql = getQuery("OIDSTGVL_FIND_RGDESIGNATION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("stgValidationsWhenCreateRecord", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		final String sql = getQuery("OIDSTGVL_FIND_RGREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("stgValidationsWhenCreateRecord", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgValidationsWhenCreateRecordWHEN-CREATE-RECORD
	 *
	 * @param params
	 */
	public List<StgValidations> stgValidationsWhenCreateRecord(final StgValidations paramBean) {
		final String sql = getQuery("OIDSTGVL_STG_VALIDATIONS_WHENCREATERECORD");
		final RowMapper<StgValidations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StgValidations.class,
				stgValidationsMapping);
		List<StgValidations> returnList = new ArrayList<StgValidations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("stgValidationsWhenCreateRecord", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgValidationsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> stgValidationsPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDSTGVL_STG_VALIDATIONS_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("stgValidationsPostQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidstgvlKeyCommit
	 *
	 * @param params
	 *
	 */

	public Long oidstgvlKeyCommit() {
		final String sql = getQuery("OIDSTGVL_OIDSTGVL_KEYCOMMIT");
		Long returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	@Override
	public BigDecimal validationSeqData(final BigDecimal stgId) {
		BigDecimal returnObj = null;
		final String sql = getQuery("OIDSTGVL_STG_VALIDATIONS_WHENCREATERECORD");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), BigDecimal.class);
		return returnObj;
	}

	@Override
	public Date reviewDateData(final Long stgId) {
		Date returnObj = null;
		final String sql = getQuery("OIDSTGVL_MAX_VALIDATION_DATE");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), Date.class);
		return returnObj;
	}

}
