package net.syscon.s4.pkgs.elite_proposed_movement.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;
import net.syscon.s4.pkgs.elite_proposed_movement.EliteProposedMovementRepository;

@Repository
public class EliteProposedMovementRepositoryImpl extends RepositoryBase implements EliteProposedMovementRepository {

	private static Logger logger = LogManager.getLogger(EliteProposedMovementRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offMoveDetMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public String ifRoleAssigned(String userId, String roleNm) {
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_IF_ROLE_ASSIGNED");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId, "roleNm", roleNm),
					String.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " ifRoleAssigned() ", e);
			return null;
		}
	}

	@Override
	public Integer getSeqCurInst(Integer offenderBookId) {
		Integer seqCur = null;
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_GET_SEQ_CUR_INST");
		try {
			seqCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Integer.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getSeqCurInst() ", e);
		}
		return seqCur;
	}

	@Override
	public Integer getSeqCur(Integer pOffBkg, Integer pLocSeq) {
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_GET_SEQ_CUR_LOC_DTLS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_off_bkg", pOffBkg, "p_loc_seq", pLocSeq), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getSeqCur in error ");
			return null;
		}
	}

	@Override
	public Integer insertLocChngDtls(OffenderLocChngDtls insertBean) {
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_INSERT_LOC_CHNG_DTLS");
		int returnArray = 0;
		try {
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(insertBean);
			returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		} catch (DataAccessException e) {
			logger.error(this.getClass().getName() + " insertLocChngDtls in error ");
		}
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffenderLocChngDtls> statusInmCurInstLoc(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderLocChngDtls> returnList = new ArrayList<>();
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_STATUS_INM_CUR_INT_LOC");
		final RowMapper<OffenderLocChngDtls> offMovDetRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLocChngDtls.class, offMoveDetMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_choice", pChoice, "p_off_bkg", pOffBkg, "p_loc_seq", pMoveSeq), offMovDetRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " statusInmCurInstLoc in error ");
		}
		return returnList;
	}

	@Override
	public List<OffenderLocChngDtls> maxStatusInmCurInstLoc(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderLocChngDtls> returnList = new ArrayList<>();
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_MAX_STATUS_INM_CUR_INT_LOC");
		final RowMapper<OffenderLocChngDtls> offMovDetRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLocChngDtls.class, offMoveDetMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_choice", pChoice, "p_off_bkg", pOffBkg, "p_loc_seq", pMoveSeq), offMovDetRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " maxStatusInmCurInstLoc in error ");
		}
		return returnList;
	}

	@Override
	public String ifIntrNonAssoExists(Integer offenderBookId, String currAgyId, Integer toLivingUnitId) {
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_IF_INTR_NON_ASSO_EXISTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId,
					"curr_agy_id", currAgyId, "to_living_unit_id", toLivingUnitId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getSeqCur in error ");
			return null;
		}

	}

	@Override
	public String impStatusCur(Integer offenderBookId) {
		String vImpStatus;
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_IMPSTATUS_CUR");
		vImpStatus = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId),
				String.class);
		return vImpStatus;
	}

	@Override
	public Integer sanctionCur(Integer offenderBookId) {
		Integer sanctionCur;
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_SANCTION_CUR");
		sanctionCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId),
				Integer.class);
		return sanctionCur;
	}

	@Override
	public Integer stgAffiCur(Integer offenderBookId) {
		Integer vDescp;
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_STGAFFI_CUR");
		vDescp = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId),
				Integer.class);
		return vDescp;
	}

	@Override
	public List<OffenderMovementDetails> statusInmCur(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderMovementDetails> returnList = new ArrayList<OffenderMovementDetails>();
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_STATUS_INM_CUR");
		final RowMapper<OffenderMovementDetails> offMovDetRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMovementDetails.class, offMoveDetMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_choice", pChoice, "p_off_bkg", pOffBkg, "p_move_seq", pMoveSeq),
					offMovDetRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " statusInmCur in error ");
		}
		return returnList;
	}

	@Override
	public List<OffenderMovementDetails> maxStatusInmCur(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderMovementDetails> returnList = new ArrayList<OffenderMovementDetails>();
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_MAX_STATUS_INM_CUR");
		final RowMapper<OffenderMovementDetails> offMovDetRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMovementDetails.class, offMoveDetMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_choice", pChoice, "p_off_bkg", pOffBkg, "p_move_seq", pMoveSeq),
					offMovDetRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " maxStatusInmCur in error ");
		}
		return returnList;
	}

	@Override
	public OffenderMovementDetails statusNonInmCur() {
		OffenderMovementDetails statusNonInmCur = null;
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENT_STATUS_NON_INM_CUR");
		try {
			statusNonInmCur = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					OffenderMovementDetails.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " statusNonInmCur in error ");
			statusNonInmCur = new OffenderMovementDetails();
		}
		return statusNonInmCur;
	}

	@Override
	public OffenderMovementDetails maxStatusNonInmCur() {
		OffenderMovementDetails maxStatusNonInmCur = null;
		final String sql = getQuery("ELITE_PROPOSED_MOVEMENTT_MAX_STATUS_NON_INM_CUR");
		try {
			maxStatusNonInmCur = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					OffenderMovementDetails.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " maxStatusNonInmCur in error ");
			maxStatusNonInmCur = new OffenderMovementDetails();
		}
		return maxStatusNonInmCur;
	}

	@Override
	public Integer insertExtMvmtDtls(OffenderMovementDetails insertBean) {

		String sql = getQuery("ELITE_PROPOSED_MOVEMENTT_INSERTINTO_OFFENDERMOVEMENTDETAILS");
		int returnArray = 0;
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(insertBean);
		returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Long getSeqCur(Long pOffBkg, Long pMoveSeq) {
		Long res = null;
		String sql = getQuery("ELITE_PROPOSED_MOVEMENTT_GET_SEQ_CUR_OFFENDER_MOVEMENT_DETAILS");
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBkg", pOffBkg, "pMoveSeq", pMoveSeq),
					Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getSeqCur in error ");
		}
		return res;
	}
}
