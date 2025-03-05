package net.syscon.s4.inmate.trust.trustaccounts.impl;

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
import net.syscon.s4.common.beans.OffenderEducations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.OffenderFreezeDisbursements;
import net.syscon.s4.inmate.trust.trustaccounts.OtdofrezRepository;

@Repository
public class OtdofrezRepositoryImpl extends RepositoryBase implements OtdofrezRepository {

	/**
	 * Creates new OtdofrezRepositoryImpl class Object
	 * 
	 */
	private static Logger logger = LogManager.getLogger(OtdofrezRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("FREEZE_REASON_CODE", 			new FieldMapper("freezeReasonCode"))
			.put("MODE", 						new FieldMapper("mode"))
			.build();
	private final Map<String, FieldMapper> offenderFreezeDisbursementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMMENT_TEXT", 				new FieldMapper("commentText"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("OFFENDER_FREEZE_ID", 			new FieldMapper("offenderFreezeId"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("TO_DATE", 					new FieldMapper("toDate"))
			.put("REMOVED_FLAG", 				new FieldMapper("removedFlag"))
			.put("FREEZE_REASON_CODE", 			new FieldMapper("freezeReasonCode"))
			.put("FROM_DATE", 					new FieldMapper("fromDate"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.build();

	public OtdofrezRepositoryImpl() {
		// OtdofrezRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderFreezeDisbursements
	 *
	 * @return List<OffenderFreezeDisbursements>
	 *
	 * 
	 */
	public List<OffenderFreezeDisbursements> offFdExecuteQuery(final OffenderFreezeDisbursements objSearchDao) {
		final String sql = getQuery("OTDOFREZ_OFFFD_FIND_OFFENDER_FREEZE_DISBURSEMENTS");
		final RowMapper<OffenderFreezeDisbursements> OffenderFreezeDisbursementsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderFreezeDisbursements.class, offenderFreezeDisbursementsMapping);
		final ArrayList<OffenderFreezeDisbursements> returnList = (ArrayList<OffenderFreezeDisbursements>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId", objSearchDao.getCaseloadId(), "offenderId",
						objSearchDao.getOffenderId()), OffenderFreezeDisbursementsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderFreezeDisbursements
	 *            List<OffenderFreezeDisbursements>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offFdInsertOffenderFreezeDisbursements(
			final List<OffenderFreezeDisbursements> lstOffenderFreezeDisbursements) {
		final String sql = getQuery("OTDOFREZ_OFFFD_INSERT_OFFENDER_FREEZE_DISBURSEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderFreezeDisbursements offenderfreesDisbmnts : lstOffenderFreezeDisbursements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderfreesDisbmnts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderFreezeDisbursements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer offFdUpdateOffenderFreezeDisbursements(
			final List<OffenderFreezeDisbursements> lstOffenderFreezeDisbursements) {
		final String sql = getQuery("OTDOFREZ_OFFFD_UPDATE_OFFENDER_FREEZE_DISBURSEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderFreezeDisbursements offenderfreesDisbmnts : lstOffenderFreezeDisbursements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderfreesDisbmnts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderFreezeDisbursements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer offFdDeleteOffenderFreezeDisbursements(
			final List<OffenderFreezeDisbursements> lstOffenderFreezeDisbursements) {
		final String sql = getQuery("OTDOFREZ_OFF_BKG_DELETE_OFFENDER_FREEZE_DISBURSEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderFreezeDisbursements offenderfreesDisbmnts : lstOffenderFreezeDisbursements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderfreesDisbmnts));
		}
		try {
			String tableName = "OFFENDER_FREEZE_DISBURSEMENTS";
			String whereClause = "OFFENDER_FREEZE_ID  = :offenderFreezeId AND  CASELOAD_ID = :caseloadId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offFdDeleteOffenderFreezeDisbursements", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderFreezeDisbursements.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkOffFdFreezeReasonCodeRecordGroup() {
		final String sql = getQuery("OTDOFREZ_FIND_CGFKOFFFDFREEZEREASONCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}

	}

	public Object offFdPreInsert(SysDual paramBean) {
		final String sql = getQuery("OTDOFREZ_OFF_FD_PREINSERT");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, mMapping);
		SysDual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffFdOffFdRefCod
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffFdOffFdRefCod(ReferenceCodes paramBean) {
		final String sql = getQuery("OTDOFREZ_CGFKCHK_OFF_FD_OFF_FD_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	public Integer preInsert(OffenderFreezeDisbursements bean) {
		final String sql = getQuery("OTDOFREZ_OFFENDER_FREEZE_DISBURSEMENTS_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("FROM_DATE", bean.getFromDate(), "TO_DATE", bean.getToDate(), "FREEZE_REASON_CODE",
						bean.getFreezeReasonCode(), "CASELOADID", bean.getCaseloadId(), "OFFENDERID",
						bean.getOffenderId()),
				Integer.class);
	}
	
}
