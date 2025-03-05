package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.OidinpliRepository;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
/**
 * class OidinpliRepositoryImpl
 */
@Repository
public class OidinpliRepositoryImpl extends RepositoryBase implements OidinpliRepository {

	private static Logger logger = LogManager.getLogger(OidinpliRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VHousingMoves
	 *
	 * @return List<VHousingMoves>
	 *
	 * @throws SQLException
	 */
	public List<VHousingMoves> extrMoveExecuteQuery(VHousingMoves objSearchDao) {
		String sql = getQuery("OIDINPLI_EXTRMOVE_FIND_V_HOUSING_MOVES");
		if (objSearchDao.getFromLivingUnitId() != null) {
			sql+=  " AND from_living_unit_id = '" + objSearchDao.getFromLivingUnitId()+"'";
		}
		if (objSearchDao.getToLivingUnitId() != null) {
			sql+=  " AND from_living_unit_id = '" + objSearchDao.getToLivingUnitId()+"'";
		}
		if (objSearchDao.getMovementType() != null) {
			sql += " and movement_type =  '"+ objSearchDao.getMovementType()+"'";
		}
		if (objSearchDao.getMovementReason() != null) {
			sql += " and movement_reason = '" + objSearchDao.getMovementReason() +"'";
		}

		sql += " and status_code = " + " 'APP' ";

		sql += " and txn_status = " + " 'PEN' ";

		sql += (" AND detail_seq = ( SELECT MAX(vhm.detail_seq) \n " + " FROM offender_loc_chng_dtls vhm \n"
				+ "WHERE vhm.offender_book_id = v_housing_moves.offender_book_id \n "
				+ " AND vhm.location_seq = v_housing_moves.location_seq  \n" + " AND vhm.txn_status != 'CREQ'" + " ) ");
		if (objSearchDao.getOffenderIdDisplay() != null) {
			sql += " AND offender_id_display =" + objSearchDao.getOffenderIdDisplay() + " %"
					+ objSearchDao.getOffenderIdDisplay()  + "%";

		}
		sql = sql + " ORDER BY detail_seq DESC,location_seq";
		List<VHousingMoves> returnList = new ArrayList<VHousingMoves>();
		try {
			returnList = (ArrayList<VHousingMoves>) namedParameterJdbcTemplate.query(sql,
					createParams("agyId", objSearchDao.getCurrAgyId()),
					new BeanPropertyRowMapper<VHousingMoves>(VHousingMoves.class));
		} catch (DataAccessException e) {
			logger.error("Exception in extrMoveExecuteQuery ", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderProposedIntlocs List<OffenderProposedIntlocs>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer statDetInsertOffenderProposedIntlocs(
			final List<OffenderProposedIntlocs> lstOffenderProposedIntlocs) {
		String sql = getQuery("OIDINPLI_STATDET_INSERT_OFFENDERPROPOSEDINTLOCS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProposedIntlocs offenderProposedIntlocs : lstOffenderProposedIntlocs) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProposedIntlocs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderProposedIntlocs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLocRecordGroup(String caseLoadId) {
		final String sql = getQuery("OIDINPLI_FIND_RGLOC");
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId),
					new BeanPropertyRowMapper<LivingUnits>(LivingUnits.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return recordList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<InternalScheduleReasons>
	 */
	public List<InternalScheduleReasons> rgMoveReasonRecordGroup(String movementType) {
		final String sql = getQuery("OIDINPLI_FIND_RGMOVEREASON");
		List<InternalScheduleReasons> returnList = new ArrayList<InternalScheduleReasons>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("move_type", movementType),
					new BeanPropertyRowMapper<InternalScheduleReasons>(InternalScheduleReasons.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	public List<InternalScheduleReasons> rgMoveTypeRecordGroup() {
		final String sql = getQuery("OIDINPLI_FIND_RGMOVETYPE");
		List<InternalScheduleReasons> returnList = new ArrayList<InternalScheduleReasons>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<InternalScheduleReasons>(InternalScheduleReasons.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProposedIntlocs List<OffenderProposedIntlocs>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer inmaDetUpdateOffenderProposedIntlocs(VHousingMoves commitBean) {
		final String sql = getQuery("OIDINPLI_INMADET_UPDATE_OFFENDERPROPOSEDINTLOCS");
		int returnArray = 0;
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(commitBean);
		returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Long capacityCur(Long livingUnitId) {
		Long capacityCur = null;
		final String sql = getQuery("OIDINPLI_CAPACITY_CUR");
		try {
			capacityCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_living_unit_id", livingUnitId),
					Long.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " capacityCur() ", e);
		}
		return capacityCur;
	}

	@Override
	public Long occupiedCur(Long livingUnitId) {
		Long occupiedCur = null;
		final String sql = getQuery("OIDINPLI_OCCUPIED_CUR");
		try {
			occupiedCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_living_unit_id", livingUnitId),
					Long.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " occupiedCur() ", e);
		}
		return occupiedCur;
	}

	@Override
	public Long getMaxBedAssignSeqCur(Long offenderBookId) {
		Long getMaxBedCount = null;
		final String sql = getQuery("OIDINPLI_GET_MAX_BED_ASSIGN_SEQ_CUR");
		try {
			getMaxBedCount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offenderBookId), Long.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getMaxBedAssignSeqCur() ", e);
		}
		return getMaxBedCount;
	}

	@Override
	public Long nextBedSeqCur(Long offenderBookId) {
		Long nextBedSeq = null;
		final String sql = getQuery("OIDINPLI_NEXT_BED_SEQ_CUR");
		try {
			nextBedSeq = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offenderBookId), Long.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " nextBedSeqCur() ", e);
		}
		return nextBedSeq;
	}

	@Override
	public Integer offBookUpdate(VHousingMoves updateBean) {
		Integer updateCount = 0;
		final String sql = getQuery("OIDINPLI_OFFENDER_BOOKINGS_UPDATE");
		try {
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(updateBean);
			updateCount = namedParameterJdbcTemplate.update(sql, sqlParam);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " offBookUpdate() ", e);
		}
		if (updateCount > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer bedAssignmentHistoriesUpdt(VHousingMoves updateBean) {
		Integer updateCount = 0;
		final String sql = getQuery("OIDINPLI_BED_ASSIGNMENT_HISTORIES_UPDATE");
		try {
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(updateBean);
			updateCount = namedParameterJdbcTemplate.update(sql, sqlParam);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " bedAssignmentHistoriesUpdt() ", e);
		}
		if (updateCount > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer bedAssignmentHistoriesInsert(VHousingMoves commitBean) {
		Integer insertCount = 0;
		final String sql = getQuery("OIDINPLI_BED_ASSIGNMENT_HISTORIES_INSERT");
		try {
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(commitBean);
			insertCount = namedParameterJdbcTemplate.update(sql, sqlParam);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " bedAssignmentHistoriesInsert() ", e);
		}
		if (insertCount > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<ReferenceCodes> rgLocFromRecordGroup(String agyLocId) {
		final String sql = getQuery("OIDINPLI_LOC_FROM_RECORDGROUP");

		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " rgLocFromRecordGroup() ", e);
		}
		return recordList;
	}

	@Override
	public List<ReferenceCodes> rgLocToRecordGroup(String agyLocId) {
		final String sql = getQuery("OIDINPLI_LOC_TO_RECORDGROUP");
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " rgLocFromRecordGroup() ", e);
		}
		return recordList;
	}
	
	/**
	 * Fetch the records from database table
	 *
	 * @param offenderBookId
	 * 
	 * @throws SQLException
	 */
	@Override
	public Integer poteSchCur(Integer offenderBookId) {
		Integer PoteSchCur = 0;
		final String sql = getQuery("OIDINPLI_POTE_SCH_CURSOR");
		try {
			PoteSchCur = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offenderBookId), Integer.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " poteSchCur() ", e);
		}
		return PoteSchCur;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param offenderBookId
	 * 
	 * @throws SQLException
	 */
	@Override
	public Integer stgNonAssoCur(Integer offenderBookId ,Integer rootOffenderId) {
		Integer StgNonAssoCur = 0;
		final String sql = getQuery("OIDINPLI_STGNON_ASSO_CURSOR");
		try {
			StgNonAssoCur = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offenderBookId,"root_offender_id",rootOffenderId), Integer.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " poteSchCur() ", e);
		}
		return StgNonAssoCur;
	}

	
}
