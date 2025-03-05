package net.syscon.s4.inst.securitythreatgroups.impl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.securitythreatgroups.OidmbrvlRepository;


@Repository
public class OidmbrvlRepositoryImpl extends RepositoryBase implements OidmbrvlRepository{



private final Map<String, FieldMapper> offenderStgDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
.put("VAL_DATE", 						new FieldMapper("valDate"))
.put("DETAIL_SEQ", 						new FieldMapper("detailSeq"))
.put("STG_SEQ", 						new FieldMapper("stgSeq"))
.put("ACTION_CODE", 					new FieldMapper("actionCode"))
.put("REASON_CODE", 					new FieldMapper("reasonCode"))
.put("MAX(VAL_DATE)", 					new FieldMapper(" max(valDate) "))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 					new FieldMapper("description"))
.build();

	/**
	 * Creates new OidmbrvlRepositoryImpl class Object
	 */
	public OidmbrvlRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderStgDetails
	 *
	 * @return List<OffenderStgDetails>
	 */
	public List<OffenderStgDetails> offenderStgDetailsExecuteQuery(final OffenderStgDetails objSearchDao) {
		final String sql = getQuery("OIDMBRVL_OFFENDERSTGDETAILS_FIND_OFFENDER_STG_DETAILS");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		final StringBuffer paramQuery = new StringBuffer(sql);
		List<OffenderStgDetails> returnList = Collections.emptyList();
		params.addValue("offenderBookId", objSearchDao.getOffenderBookId());
		params.addValue("stgSeq", objSearchDao.getStgSeq());
		if (paramQuery != null) {
			if (objSearchDao.getValDate() != null) {
				paramQuery.append(" AND TO_CHAR(VAL_DATE, 'DD/MM/YYYY') = :valDate ");
				params.addValue("valDate", new SimpleDateFormat("dd/MM/yyyy").format(objSearchDao.getValDate()));

			}
			if (objSearchDao.getActionCode() != null && !objSearchDao.getActionCode().isEmpty()) {
				paramQuery.append(" AND ACTION_CODE = :actionCode ");
				params.addValue("actionCode", objSearchDao.getActionCode());

			}
			if (objSearchDao.getReasonCode() != null && !objSearchDao.getReasonCode().isEmpty()) {
				paramQuery.append(" AND REASON_CODE = :reasonCode ");
				params.addValue("reasonCode", objSearchDao.getReasonCode());

			}
			if (objSearchDao.getCommentText() != null && !objSearchDao.getCommentText().isEmpty()) {
				paramQuery.append(" AND COMMENT_TEXT LIKE :commentText ");
				params.addValue("commentText", objSearchDao.getCommentText());

			}
			paramQuery.append("  ORDER BY VAL_DATE DESC  ");

			final String preparedSql = paramQuery.toString();

			final RowMapper<OffenderStgDetails> offenderStgDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderStgDetails.class, offenderStgDetailsMapping);
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, offenderStgDetailsRowMapper);
		}
		return returnList;
	}


	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderStgDetails
	 *            List<OffenderStgDetails>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offenderStgDetailsInsertOffenderStgDetails(final List<OffenderStgDetails> lstOffenderStgDetails) {
		final String sql = getQuery("OIDMBRVL_OFFENDERSTGDETAILS_INSERT_OFFENDER_STG_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderStgDetails offenderStgDetails : lstOffenderStgDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderStgDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderStgDetails.size() == returnArray.length) {
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
		final String sql = getQuery("OIDMBRVL_FIND_RGACTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		final String sql = getQuery("OIDMBRVL_FIND_RGREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderStgDetailsPreInsertPRE-INSERT
	 *
	 * @param params
	 *
	 */
	public BigDecimal offenderStgDetailsPreInsertPreInsert(final OffenderStgDetails paramBean) {
		final String sql = getQuery("OIDMBRVL_OFFENDER_STG_DETAILS_PREINSERTPRE_INSERT");
		final BigDecimal returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId(), "stgSeq", paramBean.getStgSeq()),
				BigDecimal.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDMBRVL_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * validateValDate
	 *
	 * @param params
	 *
	 */
	public Date validateValDate(final OffenderStgDetails paramBean) {
		final String sql = getQuery("OIDMBRVL_VALIDATE_VAL_DATE");
		final Date returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId(), "stgSeq", paramBean.getStgSeq()),
				Date.class);
		return returnObj;
	}

	public void updateOffenderStgAffiliationsAppealDate(final OffenderStgDetails paramBean) {
		final String sql = getQuery("OIDMBRVL_UPDATE_OFFENDER_STG_AFFILIATIONS_APPEAL_DATE");
		final Map<String, Object> param = new HashMap<>();
		param.put("valDate", paramBean.getValDate());
		param.put("offenderBookId", paramBean.getOffenderBookId());
		param.put("stgSeq", paramBean.getStgSeq());
		param.put("modifyUserId", paramBean.getModifyUserId());
		namedParameterJdbcTemplate.update(sql, param);
	}

}
