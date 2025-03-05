package net.syscon.s4.pkgs.oidtroju.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.MovementReason;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;
import net.syscon.s4.inst.legalscreens.releasenotification.OffenderReleaseNotis;
import net.syscon.s4.inst.movementexternal.beans.OffenderNotCompletions;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.pkgs.oidtroju.OidtrojuPkgRepository;

@Repository
public class OidtrojuPkgRepositoryImpl extends RepositoryBase implements OidtrojuPkgRepository {

	final static Logger logger = LogManager.getLogger(OidtrojuPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

//This procedure is used to get_notif_info_cur
	@Override
	public MovementReason getNotifInfoCur(final OffenderExternalMovements offExtMov) {
		final String sql = getQuery("GET_NOTF_INFO_CUR");
		MovementReason retObj = new MovementReason();
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_mvt_type",
					offExtMov.getMovementType(), "p_mvt_reason", offExtMov.getMovementReasonCode()),
					new BeanPropertyRowMapper<MovementReason>(MovementReason.class));
		} catch (Exception e) {
			logger.error("", e);
		}
		return retObj;
	}

	// This procedure is used to get_cnt_pend_cur
	@Override
	public Long getCntPendCur(final OffenderExternalMovements offExtMov) {
		final String sql = getQuery("GET_CNT_PEN_CUR");
		return namedParameterJdbcTemplate
				.queryForObject(sql,
						createParams("p_mvt_type", offExtMov.getMovementType(), "p_mvt_reason",
								offExtMov.getMovementType(), "p_off_book_id", offExtMov.getOffenderBookId()),
						Long.class);
	}

	// This procedure is used to check_for_notif_rec_cur
	@Override
	public Long getCheckforNotifCur(final OffenderExternalMovements offExtMov) {
		final String sql = getQuery("CHECK_FOR_NOTIF_REC_CUR");
		return namedParameterJdbcTemplate
				.queryForObject(sql,
						createParams("p_mvt_type", offExtMov.getMovementType(), "p_mvt_reason",
								offExtMov.getMovementType(), "p_off_book_id", offExtMov.getOffenderBookId()),
						Long.class);
	}

	// This procedure is used to get_all_notif_rec_cur
	@Override
	public List<OffenderReleaseNotis> getAllNotifRecCur(final OffenderExternalMovements offExtMov) {
		final String sql = getQuery("GET_ALL_NOTIF_REC_CUR");
		final RowMapper<OffenderReleaseNotis> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderReleaseNotis.class,
				mapping);
		return namedParameterJdbcTemplate
				.query(sql,
						createParams("p_mvt_type", offExtMov.getMovementType(), "p_mvt_reason",
								offExtMov.getMovementType(), "p_off_book_id", offExtMov.getOffenderBookId()),
						rowMapper);
	}

	// This procedure is used to get_next_noti_move_seq_cur
	@Override
	public Long getNextNotiMovSeqCur(final Long cpOffBookId, final Long cpNotiSeq) {
		final String sql = getQuery("GET_NEXT_NOTI_MOVE_SEQ_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("cp_off_book_id", cpOffBookId, "cp_noti_seq", cpNotiSeq), Long.class);
	}

	// -- Populate offender_pend_notifications,
	@Override
	public Integer insertOffenderPendNotification(final OffenderPendNotifications bean) {
		final String sql = getQuery("INSERT_OFFENDER_PEND_NOTIFICATIONS");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(bean));
	}

	// Populate offender_not_completions
	@Override
	public Integer insertOffenderNotCompletion(final OffenderNotCompletions offNtCpm) {
		final String sql = getQuery("INSERT_OFFENDER_NOT_COMPLETIONS");
		final OffenderNotCompletions bean = new OffenderNotCompletions();
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(bean));
	}

}
