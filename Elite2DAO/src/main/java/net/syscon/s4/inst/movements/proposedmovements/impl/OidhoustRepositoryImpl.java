package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.proposedmovements.OidhoustRepository;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;


/**
 * Class OidhoustRepositoryImpl
 * 
 */
@Repository
public class OidhoustRepositoryImpl extends RepositoryBase implements OidhoustRepository {

	private static Logger logger = LogManager.getLogger(OidhoustRepositoryImpl.class.getName());


	/**
	 * Logger object used to print the log in the file
	 */
	private final Map<String, FieldMapper> vHouseMoveMapping = (Map<String, FieldMapper>) new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType")).put("NBT_APPR_DATE", new FieldMapper("nbtApprDate"))
			.put("AGY_ID", new FieldMapper("agyId")).put("AGY_DIR", new FieldMapper("agyDir"))
			.put("MOVE_TYPE", new FieldMapper("moveType")).put("MOVE_RESN", new FieldMapper("moveResn"))
			.put("APP_NEW", new FieldMapper("appNew")).put("APP_PEND", new FieldMapper("appPend"))
			.put("APP_APP", new FieldMapper("appApp")).put("APP_DEN", new FieldMapper("appDen"))
			.put("TXN_PEND", new FieldMapper("txnPend")).put("TXN_SCHD", new FieldMapper("txnSchd"))
			.put("TXN_COMP", new FieldMapper("txnComp")).put("TXN_CANC", new FieldMapper("txnCanc"))
			.put("DIFF_LOC", new FieldMapper("diffLoc")).put("CTRL_CANC_REQ", new FieldMapper("ctrlCancReq"))
			.put("NO_BKG", new FieldMapper("noBkg")).put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("approvalDate", new FieldMapper("approvalDate")).put("alertCode", new FieldMapper("alertCode"))
			.put("secLevel", new FieldMapper("secLevel")).put("impStatus", new FieldMapper("impStatus"))
			.put("sancCode", new FieldMapper("sancCode")).put("potSchFlag", new FieldMapper("potSchFlag"))
			.put("nonAssoFlag", new FieldMapper("nonAssoFlag")).put("commentText", new FieldMapper("commentText"))
			.build();

	private final Map<String, FieldMapper> offPropIntLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> offLocChngDtlsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain")).put("canDisplay", new FieldMapper("canDisplay")).build();

	
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> rgAgyIdRecordGroup(String caseloadId) {
		final String sql = getQuery("OIDHOUST_AGY_LOCID_RECORDGROUP");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("caseload_id", caseloadId),
					referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in "+ this.getClass().getName() + " rgAgyIdRecordGroup", e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> rgLocFromRecordGroup(String agyLocId) {
		final String sql = getQuery("OIDHOUST_LOC_FROM_RECORDGROUP");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("agy_id", agyLocId), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in "+ this.getClass().getName() + " rgLocFromRecordGroup", e);
		}
		return refList;
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */

	@Override
	public List<ReferenceCodes> rgLocToRecordGroup(String agyLocId) {
		final String sql = getQuery("OIDHOUST_LOC_TO_RECORDGROUP");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("agy_id", agyLocId), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in "+ this.getClass().getName() + " rgLocToRecordGroup", e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public List<ReferenceCodes> rgMoveTypeRecordGroup() {
		final String sql = getQuery("OIDHOUST_RG_MOVEMENT_TYPE_RECORDGROUP");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in "+ this.getClass().getName() + " rgMoveTypeRecordGroup", e);
		}
		return refList;
	}
	
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */

	@Override
	public List<ReferenceCodes> rgMoveReasonRecordGroup(final String movementType) {
		final String sql = getQuery("OIDHOUST_RG_MOVEMENT_REASON_RECORDGROUP");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("move_type", movementType),
					referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in "+ this.getClass().getName() + " rgMoveReasonRecordGroup", e);
		}
		return refList;

	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */

	@Override
	public List<ReferenceCodes> rgAppStatusRecordGroup() {
		final String sql = getQuery("OIDHOUST_RG_APPROVAL_STATUS_RECORDGROUP");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in "+ this.getClass().getName() + " rgAppStatusRecordGroup", e);
		}
		return refList;
	}
	
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */


	@Override
	public List<ReferenceCodes> rgTxnStatusRecordGroup() {
		final String sql = getQuery("OIDHOUST_TXN_STATUS_RECORDGROUP");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in "+ this.getClass().getName() + " rgTxnStatusRecordGroup", e);
		}
		return refList;
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param searchBean VHousingMoves
	 *
	 * @return List<VHousingMoves>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<VHousingMoves> housMoveExecuteQuery(VHousingMoves searchBean) {
		String sql = getQuery("OIDHOUST_EXTRMOVE_FIND_V_HOUSE_MOVES");
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		inParameterSource.addValue("agyId", searchBean.getCurrAgyId());
		if (searchBean != null) {
			if (searchBean.getFromLivingUnitId() != null) {
				sql = sql + " AND from_living_unit_id = :fromLivingUnitId  ";

				inParameterSource.addValue("fromLivingUnitId", searchBean.getFromLivingUnitId());
			}
			if (searchBean.getToLivingUnitId() != null) {
				sql = sql + " AND to_living_unit_id = :toLivingUnitId  ";
				inParameterSource.addValue("toLivingUnitId", searchBean.getToLivingUnitId());
			}
			if (searchBean.getMovementType() != null) {
				sql = sql + " and movement_type =  :movementType ";
				inParameterSource.addValue("movementType", searchBean.getMovementType());
			}
			if (searchBean.getMovementReason() != null) {
				sql = sql + " and movement_reason = :movementReason ";
				inParameterSource.addValue("movementReason", searchBean.getMovementReason());
			}
			if (searchBean.getStatusObj() != null && searchBean.getStatusObj().size() > 0) {
				sql = sql + " and status_code IN  (:statusObj) ";
				inParameterSource.addValue("statusObj", searchBean.getStatusObj());
			}
			if (searchBean.getTxnObj() != null && searchBean.getTxnObj().size() > 0) {
				sql = sql + " and txn_status IN  (:txnObj) ";
				inParameterSource.addValue("txnObj", searchBean.getTxnObj());
			}
			sql = sql
					+ "  AND detail_seq = ( SELECT MAX(vhm.detail_seq)  FROM offender_loc_chng_dtls vhm WHERE vhm.offender_book_id = v_housing_moves.offender_book_id "
					+ "  AND vhm.location_seq = v_housing_moves.location_seq  AND vhm.txn_status != 'CREQ')";
			if (searchBean.getCancReq() != null && "Y".equals(searchBean.getCancReq())) {
				sql = sql
						+ " AND EXISTS (SELECT 1  FROM offender_loc_chng_dtls vhm WHERE vhm.offender_book_id = v_housing_moves.offender_book_id "
						+ "  AND vhm.location_seq = v_housing_moves.location_seq  AND vhm.txn_status = 'CREQ')";
			}
		}
		sql = sql + " ORDER BY detail_seq DESC,location_seq";
		final RowMapper<VHousingMoves> vHousemovRowMapper = Row2BeanRowMapper.makeMapping(sql, VHousingMoves.class,
				vHouseMoveMapping);
		List<VHousingMoves> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, inParameterSource, vHousemovRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in "+ this.getClass().getName() + " extrMoveExecuteQuery ", e);
		}
		return returnObj;
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param searchBean VHousingMoves
	 *
	 * @return List<VHousingMoves>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<OffenderProposedIntlocs> populateInmaDetails(VHousingMoves searchBean) {
		final String sql = getQuery("OIDHOUST_INMATE_DETAILS_EXECUTEQUERY");
		final RowMapper<OffenderProposedIntlocs> offPropIntLocRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProposedIntlocs.class, offPropIntLocMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), offPropIntLocRowMapper);
	}
	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param updateBean 
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer inmateCommit(VHousingMoves updateBean) {
		final String sql = getQuery("OIDHOUST_INMATE_DET_COMMIT_QUERY");
		int returnArray = 0;
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(updateBean);
		returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchBean populateStatDetDetails
	 *
	 * @return List<populateStatDetDetails>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<OffenderLocChngDtls> populateStatDetDetails(VHousingMoves searchBean) {
		final String sql = getQuery("OIDHOUST_GET_STATE_DET_EXECUTEQUERY");
		final RowMapper<OffenderLocChngDtls> offLocChngDtlsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLocChngDtls.class, offLocChngDtlsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offender_book_id", searchBean.getOffenderBookId(),
				"location_seq", searchBean.getLocationSeq()), offLocChngDtlsRowMapper);
	}

	@Override
	public String getCurInmAppStatus(VHousingMoves searchBean) {
		String sql = getQuery("OIDHOUST_CUR_INM_APP_STATUS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id",
					searchBean.getOffenderBookId(), "location_seq", searchBean.getLocationSeq()), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception occured in "+ this.getClass().getName() + "getCurInmAppStatus in error ", e);
			return null;
		}
	}

}