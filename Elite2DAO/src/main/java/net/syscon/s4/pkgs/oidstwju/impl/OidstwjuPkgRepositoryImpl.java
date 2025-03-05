
package net.syscon.s4.pkgs.oidstwju.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.pkgs.oidstwju.OidstwjuPkgRepository;

@Repository
public class OidstwjuPkgRepositoryImpl extends RepositoryBase implements OidstwjuPkgRepository {
	private static Logger logger = LogManager.getLogger(OidstwjuPkgRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> offIndSchWMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public Integer firstSelect(final String pAgyLocId, final Integer pOffenderBookId) {
		final String sql = getQuery("OIDSTWJU_FIRTS_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_agy_loc_id", pAgyLocId, "p_offender_book_id", pOffenderBookId), Integer.class);
	}

	@Override
	public Integer secondSelect(final String pAgyLocId, final Integer pOffenderBookId) {
		final String sql = getQuery("OIDSTWJU_FIRTS_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_agy_loc_id", pAgyLocId, "p_offender_book_id", pOffenderBookId), Integer.class);
	}

	@Override
	public String cancelCur(final String pAgyLocId, final Integer pOffenderBookId) {
		final String sql = getQuery("CANCEL_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_agy_loc_id", pAgyLocId, "p_offender_book_id", pOffenderBookId), String.class);
	}

	@Override
	public Integer scheduleNaCur(final String pAgyLocId, final Integer pOffenderBookId) {
		final String sql = getQuery("SCHEDULE_NA_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_agy_loc_id", pAgyLocId, "p_offender_book_id", pOffenderBookId), Integer.class);
	}

	@Override
	public Timestamp dateConversion() {
		Date date=null;
		Timestamp timestamp=null;
		final String sql = getQuery("DATE_CONVERSION");
		date= namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
		timestamp=new Timestamp(date.getTime()); 
		return timestamp;
	}

	@Override
	public List<Object[]> notReqCur(final String vMvtType, final String vMvtReason) {
		final String sql = getQuery("NOT_REQ_CUR");
		final RowMapper<Object[]> offIndSchWaitRM = Row2BeanRowMapper.makeMapping(sql, Object[].class,
				offIndSchWMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("v_mvt_type", vMvtType, "v_mvt_reason", vMvtReason),
				offIndSchWaitRM);
	}

	@Override
	public Integer getOffPCntCur(final Integer pOffenderBookId, final String vMvtType, final String vMvtReason) {
		final String sql = getQuery("GET_OFF_P_CNT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("v_mvt_type", vMvtType, "v_mvt_reason", vMvtReason, "v_off_book_id", pOffenderBookId),
				Integer.class);
	}

	@Override
	public Long getNotRecCur(final Integer pOffenderBookId, final String vMvtType, final String vMvtReason) {
		final String sql = getQuery("GET_NOT_REC_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("v_mvt_type", vMvtType, "v_mvt_reason", vMvtReason, "v_off_book_id", pOffenderBookId),
				Long.class);
	}

	@Override
	public List<Object[]> getRelNotRecCur(final Integer pOffenderBookId, final String vMvtType,
			final String vMvtReason) {
		final String sql = getQuery("GET_REL_NOT_REC_CUR");
		final RowMapper<Object[]> offIndSchWaitRM = Row2BeanRowMapper.makeMapping(sql, Object[].class,
				offIndSchWMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("v_mvt_type", vMvtType, "v_mvt_reason", vMvtReason, "v_off_book_id", pOffenderBookId),
				offIndSchWaitRM);
	}

	@Override
	public Integer getNextmoveSeqCur(final Integer pOffenderBookId, final Integer notiSeq) {
		final String sql = getQuery("GET_NEXTMOVE_SEQ_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("v_off_book_id", pOffenderBookId, "v_noti_seq", notiSeq), Integer.class);
	}

	@Override
	public Integer offenderPendNotifications(final OffenderIndSchedules sch) {
		final String sql = getQuery("OFFENDER_PEND_NOTIFICATIONS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("v_off_book_id", sch.getOffenderBookId(), "v_noti_seq", sch.getvNotiSeq(), "v_noti_m_seq",
						sch.getvNotiMSeq(), "v_mvt_type", sch.getMovementType(), "v_mvt_reason",
						sch.getMovementReasonCode(), "v_mvt_date", sch.getModifyDatetime(), "v_sch_id",
						sch.getScheduledTripId(), "createUserId", sch.getCreateUserId()));
	}

	@Override
	public Integer offenderNotCompletions(final OffenderIndSchedules sch) {
		final String sql = getQuery("OFFENDER_NOT_COMPLETIONS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("v_off_book_id", sch.getOffenderBookId(), "v_noti_seq", sch.getvNotiSeq(), "v_noti_m_seq",
						sch.getvNotiMSeq(), "createUserId", sch.getCreateUserId()));
	}
}