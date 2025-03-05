package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.inst.securitythreatgroups.OidstgaeRepository;

@Repository
public class OidstgaeRepositoryImpl extends RepositoryBase implements OidstgaeRepository {

	private static Logger logger = LogManager.getLogger(OidstgaeRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper(" description ")).put("MODE", new FieldMapper("mode"))
			.put("REASON", new FieldMapper("reason")).build();
	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper(" description ")).build();
	private final Map<String, FieldMapper> stgRelationshipsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", new FieldMapper("stgId")).put("RELATIONSHIP_SEQ", new FieldMapper("relationshipSeq"))
			.put("RELATIONSHIP_TYPE", new FieldMapper("relationshipType"))
			.put("RELATED_STG_ID", new FieldMapper("relatedStgId")).put("REASON", new FieldMapper("reason"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate")).put("EXPIRED_BY", new FieldMapper("expiredBy"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_LEVEL", new FieldMapper("stgLevel")).put("STG_ID", new FieldMapper("stgId"))
			.put("STG_CODE", new FieldMapper("stgCode")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).build();

	/**
	 * Creates new OidstgaeRepositoryImpl class Object
	 */
	public OidstgaeRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StgRelationships
	 *
	 * @return List<StgRelationships>
	 *
	 * 
	 */
	public List<StgRelationships> stgRltExecuteQuery(final StgRelationships objSearchDao) {
		final String sql = getQuery("OIDSTGAE_STGRLT_FIND_STG_RELATIONSHIPS");
		final RowMapper<StgRelationships> StgRelationshipsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StgRelationships.class, stgRelationshipsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("stgId", objSearchDao.getStgId(), "relationshipType", objSearchDao.getRelationshipType()),
				StgRelationshipsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstStgRelationships
	 *            List<StgRelationships>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer stgRltInsertStgRelationships(final StgRelationships lstStgRelationships) {
		final String sql = getQuery("OIDSTGAE_STGRLT_INSERT_STG_RELATIONSHIPS");
		final SqlParameterSource paramSource = new BeanPropertySqlParameterSource(lstStgRelationships);
		final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		try {
			namedParameterJdbcTemplate.update(sql, paramSource, generatedKeyHolder);
			return 1;
		} catch (final Exception e) {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStgRelationships
	 *            List<StgRelationships>
	 *
	 * 
	 */
	public Integer stgRltUpdateStgRelationships(final List<StgRelationships> lstStgRelationships) {
		final String sql = getQuery("OIDSTGAE_STGRLT_UPDATE_STG_RELATIONSHIPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgRelationships stgRelationships : lstStgRelationships) {
			parameters.add(new BeanPropertySqlParameterSource(stgRelationships));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgRelationships.size() == returnArray.length) {
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
	public List<SecurityThreatGroups> rgStg2RecordGroup(final BigDecimal stgId) {
		final String sql = getQuery("OIDSTGAE_FIND_RGSTG2");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("stgId", stgId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgStg1RecordGroup(final BigDecimal stgId) {
		final String sql = getQuery("OIDSTGAE_FIND_RGSTG1");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("stgId", stgId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgStg3RecordGroup(final BigDecimal stgId) {
		final String sql = getQuery("OIDSTGAE_FIND_RGSTG3");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("stgId", stgId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> recReason2RecordGroup() {
		final String sql = getQuery("OIDSTGAE_FIND_RECREASON2");
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
	public List<SecurityThreatGroups> recStg2RecordGroup() {
		final String sql = getQuery("OIDSTGAE_FIND_RECSTG2");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);

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
	public List<SecurityThreatGroups> recStgRecordGroup() {
		final String sql = getQuery("OIDSTGAE_FIND_RECSTG");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);

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
	public List<ReferenceCodes> recReasonRecordGroup() {
		final String sql = getQuery("OIDSTGAE_FIND_RECREASON");
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
	 * stgRltPreInsertPRE-INSERT
	 *
	 * @param params
	 *
	 */
	public Long stgRltPreInsertPreInsert(final Long stgId) {
		final String sql = getQuery("OIDSTGAE_STG_RLT_PREINSERT_PREINSERT");
		Long relationSeq =null; 
		try {
			relationSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", stgId), Long.class);
		}catch (Exception e) {
			logger.error("", e);
		}
		return relationSeq;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgRelationshipsPreInsert
	 *
	 * @param params
	 *
	 */
	public Long stgRelationshipsPreInsert(final StgRelationships paramBean) {
		final String sql = getQuery("OIDSTGAE_STG_RELATIONSHIPS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", paramBean.getStgId()), Long.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidstgaeOnCommitON-COMMIT
	 *
	 * @param params
	 *
	 */
	public Integer oidstgaeOnCommitOnCommit(final StgRelationships paramBean) {
		final String sql = getQuery("OIDSTGAE_OIDSTGAE_ONCOMMIT_ONCOMMIT");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("stgId", paramBean.getStgId(), "relatedStgId", paramBean.getRelatedStgId(),
							"relationshipType", paramBean.getRelationshipType(), "reason", paramBean.getReason()),
					Integer.class);
		} catch (Exception e) {
			logger.error("", e);
		}
		return count;
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
		final String sql = getQuery("OIDSTGAE_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public String cgwhenNewFormInstance() {
		final String sql = getQuery("OIDSTGAE_CGWHEN_NEW_FORM_INSTANCE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			return null;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstancestg_lov_lvl_cur
	 *
	 * @param params
	 *
	 */
	public String cgwhennewforminstancestgLovLvlCur() {
		final String sql = getQuery("OIDSTGAE_CGWHEN_NEW_FORM_INSTANCE_STG_LOV_LVL_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgpostQueryRltReason
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgpostQueryRltReason(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDSTGAE_CGPOST_QUERY_RLT_REASON");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgpostQueryRltGroup
	 *
	 * @param params
	 *
	 */
	public SecurityThreatGroups cgpostQueryRltGroup(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIDSTGAE_CGPOST_QUERY_RLT_GROUP");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgpostQueryRlt2Group
	 *
	 * @param params
	 *
	 */
	public SecurityThreatGroups cgpostQueryRlt2Group(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIDSTGAE_CGPOST_QUERY_RLT2_GROUP");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgpostQueryRlt2Reason
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgpostQueryRlt2Reason(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDSTGAE_CGPOST_QUERY_RLT2_REASON");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assAlianceStgASS_ALIANCE_STG
	 *
	 * @param params
	 *
	 */
	public StgRelationships assaliancestgassAlianceStg(final StgRelationships paramBean) {
		final String sql = getQuery("OIDSTGAE_ASS_ALIANCE_STG_ASS_ALIANCE_STG");
		final RowMapper<StgRelationships> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StgRelationships.class,
				stgRelationshipsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	@Override
	public Long assAlianceStgassAlianceStg(final Long relatedStgId, final Long stgId) {
		final String sql = getQuery("OIDSTGAE_ASS_ALIANCE_STGASS_ALIANCE_STG");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("relatedStgId", relatedStgId, "stgId", stgId), Long.class);
	}

	@Override
	public void deActivateAlianceUpdate(final StgRelationships data) {
		final String sql = getQuery("OIDSTGAE_DE_ACTIVATE_ALIANCE_UPDATE");
		namedParameterJdbcTemplate.update(sql,
				createParams("expiryDate", data.getExpiryDate(), "modifyUserId", data.getModifyUserId(), "expiredBy",
						data.getModifyUserId(), "relatedStgId", data.getRelatedStgId(), "stgId", data.getStgId(),
						"reason", data.getReason()));
	}

	@Override
	public void reActivateAlianceUpdate(final StgRelationships data) {
		final String sql = getQuery("OIDSTGAE_RE_ACTIVATE_ALIANCE_UPDATE");
		namedParameterJdbcTemplate.update(sql,
				createParams("expiryDate", data.getExpiryDate(), "expiredBy", data.getModifyUserId(), "modifyUserId",
						data.getModifyUserId(), "relatedStgId", data.getStgId(), "stgId", data.getRelatedStgId(),
						"reason", data.getReason()));
	}

	
	public void reActiveEnemy(final StgRelationships data) {
		final String sql = getQuery("OIDSTGAE_RE_ACTIVE_ENEMY");
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("expiryDate", data.getExpiryDate(), "expiredBy", data.getExpiredBy(), "modifyUserId",
							data.getModifyUserId(), "relatedStgId", data.getRelatedStgId(), "stgId", data.getStgId(),
							"reason", data.getReason(), "relationship_seq", data.getRelationshipSeq()));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void omsStgNonaEnemychildToStg(final StgRelationships data) {
		final String sql = getQuery("OIDSTGAE_OMS_STG_NONA_ENEMYCHILD_TO_STG");
		final Map<String, Object> param = new HashMap<>();
		param.put("relatedStgId", data.getRelatedStgId());
		param.put("stgId", data.getStgId());
		param.put("reason", data.getReason());
		param.put("effectiveDate", data.getEffectiveDate());
		param.put("createUserId", data.getCreateDatetime());
		param.put("activeFlag", data.getActiveFlag());
		param.put("expiryDate", data.getExpiryDate());
		param.put("expiredBy", data.getExpiredBy());
		param.put("commentText", data.getCommentText());
		namedParameterJdbcTemplate.update(sql, param);
	}

	public void deActiveEnemy(final StgRelationships data) {
		final String sql = getQuery("OIDSTGAE_DE_ACTIVE_ENEMY");
		namedParameterJdbcTemplate.update(sql,
				createParams("expiryDate", data.getExpiryDate(), "expiredBy", data.getExpiredBy(), "modifyUserId",
						data.getModifyUserId(), "relatedStgId", data.getRelatedStgId(), "stgId", data.getStgId(),
						"reason", data.getReason(), "relationship_seq", data.getRelationshipSeq()));
	}

	@Override
	public BigDecimal stgRltGroupPostChange(final BigDecimal stgId, final BigDecimal relatedStgId) {
		final String sql = getQuery("OIDSTGAE_STG_RLT_GROUP_POST_CHANGE");
		BigDecimal returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("stgId", stgId, "relatedStgId", relatedStgId), BigDecimal.class);
		} catch (Exception e) {
			return returnValue;
		}
		return returnValue;
	}

	@Override
	public BigDecimal stgRltCheckBoxChange(final BigDecimal stgId, final BigDecimal relatedStgId) {
		final String sql = getQuery("OIDSTGAE_STG_RLT_CHECK_BOX_CHANGE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("stgId", stgId, "relatedStgId", relatedStgId), BigDecimal.class);
	}

	@Override
	public BigDecimal stgRelationshipsCheckBoxChange(final BigDecimal stgId, final BigDecimal relatedStgId) {
		final String sql = getQuery("OIDSTGAE_STG_RELATIONSHIPS_CHECK_BOX_CHANGE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("stgId", stgId, "relatedStgId", relatedStgId), BigDecimal.class);
	}

}
