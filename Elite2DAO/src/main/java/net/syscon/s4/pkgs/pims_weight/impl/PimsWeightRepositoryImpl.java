package net.syscon.s4.pkgs.pims_weight.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;
import net.syscon.s4.pkgs.PotConcurrencies;
import net.syscon.s4.pkgs.TempWeightings;
import net.syscon.s4.pkgs.pims_weight.PimsWeightRepository;

@Repository
public class PimsWeightRepositoryImpl extends RepositoryBase implements PimsWeightRepository {

	private static Logger logger = LogManager.getLogger(PimsWeightRepositoryImpl.class.getName());

	final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();

	@Override
	public Long getMaxAssSeqCur(final String pCaseloadType, final Long pOffenderBookId) {
		final String sql = getQuery("GET_MAX_ASS_SEQ_CUR");
		Long maxSeq = null;
		try {
			maxSeq = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_CASELOAD_TYPE", pCaseloadType), Long.class);
		} catch (Exception e) {
			logger.error("getMaxAssSeqCur :" + e);
			maxSeq = null;
		}
		return maxSeq;
	}

	@Override
	public String getCaseloadType(final String createUserId) {
		final String sql = getQuery("GET_CASELOAD_TYPE_CUR");
		String caseLoadType = null;
		try {
			caseLoadType = namedParameterJdbcTemplate.queryForObject(sql, createParams("createUserId",createUserId), String.class);
		} catch (Exception e) {
			logger.error("getCaseloadType :" + e);
			caseLoadType = null;
		}
		return caseLoadType;
	}

	@Override
	public String getSupLevelCur(final Long pOffenderBookId, final Long cpAssSeq) {
		final String sql = getQuery("GET_SUP_LEVEL_CUR");
		String reviewSuperLeveltype = null;
		try {
			reviewSuperLeveltype = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "CP_ASS_SEQ", cpAssSeq), String.class);
		} catch (Exception e) {
			logger.error("getSupLevelCur :" + e);
			reviewSuperLeveltype = null;
		}
		return reviewSuperLeveltype;
	}




	@Override
	public List<StaffWorkAssignmentsV1> offaaasCur(final StaffMemberRoles bean) {
		final String sql = getQuery("WORK_OFFICER_OFFASS_CUR");
		List<StaffWorkAssignmentsV1> list = new ArrayList<StaffWorkAssignmentsV1>();
		final RowMapper<StaffWorkAssignmentsV1> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffWorkAssignmentsV1.class, mMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("p_agy_loc_id", bean.getCalAgyLocId(), "p_staff_id", bean.getStaffId(),
					"p_position", bean.getPosition(), "p_role", bean.getRole(), "p_from_date", bean.getFromDate()), rowMapper);
		} catch (Exception e) {
			logger.error("offaaasCur :" + e);
			return Collections.emptyList();
		}
		return list;
	}

	@Override
	public Long selectWeight(final String orderType, final String orderCode, final String pComponent,
			final String lvSupLevel, final String lvTimeServed) {
		final String sql = getQuery("SELECT_WEIGHTING");
		try {
			return namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("p_order_type", orderType, "p_order_code", orderCode, "p_component",
									pComponent, "lv_sup_level", lvSupLevel, "lv_time_served", lvTimeServed),
							Long.class);
		} catch (Exception e) {
			logger.error("selectWeight :" + e);
			return 0L;
		}
	}

	@Override
	public Integer insertTempWeightings(final TempWeightings bean) {
		final String sql = getQuery("INSERT_TEMP_WEIGHTINGS");
		Integer count = null;
		final TempWeightings temps = new TempWeightings();
		if (temps.getTmpWeiId() != null) {
			temps.setTmpWeiId(temps.getTmpWeiId() + 1l);
		}
		//bean.setTmpWeiId(temps.getTmpWeiId() + 1l);
		try {
			count = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(bean));
		} catch (Exception e) {
			logger.error("insertTempWeightings :" + e);
			return 0;
		}
		return count;

	}
	
	@Override
	public Long userEnv() {
		final String sql = getQuery("WORK_OFFICER_USERENV");
		Long userEnv = null;
		try {
			userEnv = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("userEnv :" + e);
		}
		return userEnv;
	}

	@Override
	public Integer insertTempWeightingsOne(final TempWeightings bean) {
		final String sql = getQuery("INSERT_TEMP_WEIGHTINGS");
		Integer count = null;
		final TempWeightings temps = new TempWeightings();
		try {
			count = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(temps));
		} catch (Exception e) {
			logger.error("insertTempWeightingsOne :" + e);
			return 0;
		}
		return count;

	}

	@Override
	public List<TempWeightings> allRecsCur(final Long lvSessionId) {
		final String sql = getQuery("ALL_RECS_CUR");
		List<TempWeightings> list = new ArrayList<TempWeightings>();
		final RowMapper<TempWeightings> rowMapper = Row2BeanRowMapper.makeMapping(sql, TempWeightings.class, mMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("lv_session_id", lvSessionId), rowMapper);
		} catch (Exception e) {
			logger.error("allRecsCur :" + e);
			return Collections.emptyList();
		}
		return list;

	}

	@Override
	public List<PotConcurrencies> conCur(final String pOrderType, final String pOrderCode) {
		final String sql = getQuery("CON_CUR_WEIGHTING");
		List<PotConcurrencies> list = new ArrayList<PotConcurrencies>();
		final RowMapper<PotConcurrencies> rowMapper = Row2BeanRowMapper.makeMapping(sql, PotConcurrencies.class,
				mMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_order_type", pOrderType, "p_order_code", pOrderCode), rowMapper);
		} catch (Exception e) {
			logger.error("allRecsCur :" + e);
			return Collections.emptyList();
		}
		return list;
	}

	@Override
	public List<TempWeightings> conTmpWeiCur(final TempWeightings bean) {
		final String sql = getQuery("ALL_RECS_CUR");
		List<TempWeightings> list = new ArrayList<TempWeightings>();
		final RowMapper<TempWeightings> rowMapper = Row2BeanRowMapper.makeMapping(sql, TempWeightings.class, mMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_book_id", bean.getBookId(), "p_order_code", bean.getOrderCode(), "p_order_type",
							bean.getOrderType(), "p_component", bean.getComponent(), "lv_session_id",
							bean.getSessionId()),
					rowMapper);
		} catch (Exception e) {
			logger.error("conTmpWeiCur :" + e);
			return Collections.emptyList();
		}
		return list;
	}

	@Override
	public Long lvOverWeighting(final Long overriddenBy, final Long lvSessionId) {
		final String sql = getQuery("LV_OVER_WEIGHTING");
		Long weighting = null;
		try {
			weighting = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("overridden_by", overriddenBy, "lv_session_id", lvSessionId), Long.class);
		} catch (Exception e) {
			logger.error("lvOverWeighting :" + e);
		}
		return weighting;
	}

	@Override
	public Integer updatrWeightingsOne(final Long weighting, final Long roWid, final String userName) {
		final String sql = getQuery("UPDATE_TEMP_WEIGHTINGS_ONE");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("weighting", weighting, "ROWID", roWid, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updatrWeightingsOne :" + e);
			return 0;
		}
	}

	@Override
	public Integer updatrWeightingsTwo(final Long overridden_by, final Long tmpWeiId, final Long lvSessionId,
			final String userName) {
		final String sql = getQuery("UPDATE_TEMP_WEIGHTINGS_TWO");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("tmp_wei_id", overridden_by, "overridden_by",
					tmpWeiId, "lv_session_id", lvSessionId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updatrWeightingsTwo :" + e);
			return 0;
		}
	}

	@Override
	public Integer updatrWeightingsThree(final Long overriddenBy, final Long roWid, final String userName) {
		final String sql = getQuery("UPDATE_TEMP_WEIGHTINGS_THREE");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("overridden_by", overriddenBy, "ROWID", roWid, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updatrWeightingsThree :" + e);
			return 0;
		}
	}

	@Override
	public Integer updateWeightingsFour(final Long weighting, final Long roWid, final String userName) {
		final String sql = getQuery("UPDATE_TEMP_WEIGHTINGS_FOUR");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("weighting", weighting, "ROWID", roWid, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateWeightingsFour :" + e);
			return 0;
		}
	}

	@Override
	public Integer updateWeightingsFive(final Long allTmpWeiId, final Long conTmpWeiId, final Long lvSessionId,
			final String userName) {
		final String sql = getQuery("UPDATE_TEMP_WEIGHTINGS_FIVE");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("ALL_TMP_WEI_ID", allTmpWeiId, "CON_TMP_WEI_ID",
					conTmpWeiId, "LV_SESSION_ID", lvSessionId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateWeightingsFive :" + e);
			return 0;
		}
	}

	@Override
	public Integer updateWeightingsSix(final Long overriddenBy, final Long roWid, final String userName) {
		final String sql = getQuery("UPDATE_TEMP_WEIGHTINGS_SIX");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("tmp_wei_id", overriddenBy, "ROWID", roWid, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateWeightingsSix :" + e);
			return 0;
		}
	}

	@Override
	public Integer updateWeightingsSeven(final Long weighting, final Long roWid, final String userName) {
		final String sql = getQuery("UPDATE_TEMP_WEIGHTINGS_FOUR");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("weighting", weighting, "ROWID", roWid, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateWeightingsSeven :" + e);
			return 0;
		}
	}

	@Override
	public Long calculatedWeighting(final Long lvSessionId) {
		final String sql = getQuery("SELECT_CALCULATED_WEIGHTING");
		Long clcWeght = null;
		try {
			clcWeght = namedParameterJdbcTemplate.queryForObject(sql, createParams("LV_SESSION_ID", lvSessionId),
					Long.class);
		} catch (Exception e) {
			logger.error("calculatedWeighting :" + e);
		}
		return clcWeght;
	}

	@Override
	public Integer deleteWeightings(final Long lvSessionId, final String userName) {
		final String sql = getQuery("UPDATE_TEMP_WEIGHTINGS_FOUR");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("LV_SESSION_ID", lvSessionId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateWeightingsSeven :" + e);
			return 0;
		}
	}

}