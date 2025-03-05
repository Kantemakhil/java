package net.syscon.s4.inst.movements.maintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movements.maintenance.OumemoveRepository;

/**
 * Class OumemoveRepositoryImpl
 */
@Repository
public class OumemoveRepositoryImpl extends RepositoryBase implements OumemoveRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumemoveRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 								new FieldMapper("code"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVEMENT_TYPE", 						new FieldMapper("movementType"))
			.put("MOVEMENT_REASON_CODE", 				new FieldMapper("movementReasonCode"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("OPEN_CONTACT_FLAG", 					new FieldMapper("openContactFlag"))
			.put("CLOSE_CONTACT_FLAG", 					new FieldMapper("closeContactFlag"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("LIST_SEQ", 							new FieldMapper("listSeq"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("NOTIFICATION_TYPE", 					new FieldMapper("notificationType"))
			.put("NOTIFICATION_FLAG", 					new FieldMapper("notificationFlag"))
			.put("BILLING_SERVICE_FLAG", 				new FieldMapper("billingServiceFlag"))
			.put("TRANSPORTATION_FLAG", 				new FieldMapper("transportationFlag"))
			.put("HEADER_STATUS_FLAG", 					new FieldMapper("headerStatusFlag"))
			.put("IN_MOVEMENT_TYPE", 					new FieldMapper("inMovementType"))
			.put("IN_MOVEMENT_REASON_CODE", 			new FieldMapper("inMovementReasonCode"))
			.put("ESC_RECAP_FLAG", 						new FieldMapper("escRecapFlag"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("UPDATE_ALLOWED_FLAG", 				new FieldMapper("updateAllowedFlag"))
			.put("TRN_REQ_APVL_FLAG", 					new FieldMapper("trnReqApvlFlag"))
			.put("TRN_SCHD_TRIP_FLAG", 					new FieldMapper("trnSchdTripFlag"))
			.put("TRN_ASSIGN_PRIORITY_FLAG", 			new FieldMapper("trnAssignPriorityFlag"))
			.put("TRN_P_LIST_FLAG", 					new FieldMapper("trnpListFlag"))
			.put("TRN_PRIORITY_SEQ", 					new FieldMapper("trnPrioritySeq"))
			.put("SUBSEQUENT_MV_TYPE", 					new FieldMapper("subSequentMvType"))
			.put("SUBSEQUENT_MV_RSN_CODE", 				new FieldMapper("subSequentMvRsnCode"))
			.put("REASON_FLAG", 						new FieldMapper("reasonFlag"))
			.put("DRIVE_TO_OIDUMAPP_FLAG", 				new FieldMapper("driveToOidumappFlag"))
			.put("INACTIVATE_SENT", 					new FieldMapper("inActivateSent"))
			.put("SUSPEND_REACTIVATE_SENT", 			new FieldMapper("suspendReactiveSent"))
			.put("CODE", 								new FieldMapper("code"))
			.build();

	/**
	 * Creates new OumemoveRepositoryImpl class Object
	 */
	public OumemoveRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            MovementReasons
	 *
	 * @return List<MovementReasons>
	 */
	public List<MovementReasons> moveRsnExecuteQuery(final MovementReasons objSearchDao) {

		final String sql = getQuery("OUMEMOVE_MOVERSN_FIND_MOVEMENT_REASONS");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getMovementType() != null && !objSearchDao.getMovementType().isEmpty()) {
				pSql.append(" MOVEMENT_TYPE LIKE :movementType  AND ");
				param.addValue("movementType", objSearchDao.getMovementType());
			}
			if (objSearchDao.getMovementReasonCode() != null && !objSearchDao.getMovementReasonCode().isEmpty()) {
				pSql.append(" MOVEMENT_REASON_CODE LIKE :movementReasonCode AND ");
				param.addValue("movementReasonCode", objSearchDao.getMovementReasonCode());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty() && !objSearchDao.getDescription().trim().equals("")) {
				pSql.append(" DESCRIPTION LIKE :description AND ");
				param.addValue("description", objSearchDao.getDescription().trim());
			}

			if (objSearchDao.getHeaderStatusFlag() != null && !objSearchDao.getHeaderStatusFlag().isEmpty()) {
				if (objSearchDao.getHeaderStatusFlag().equals("true")) {
					objSearchDao.setHeaderStatusFlag("Y");
				} else {
					objSearchDao.setHeaderStatusFlag("N");
				}
				pSql.append(" HEADER_STATUS_FLAG LIKE :headerStatusFlag AND ");
				param.addValue("headerStatusFlag", objSearchDao.getHeaderStatusFlag());
			}
			if (objSearchDao.getNotificationFlag() != null && !objSearchDao.getNotificationFlag().isEmpty()) {
				if (objSearchDao.getNotificationFlag().equals("true")) {
					objSearchDao.setNotificationFlag("Y");
				} else {
					objSearchDao.setNotificationFlag("N");
				}
				pSql.append(" NOTIFICATION_FLAG LIKE :notificationFlag AND ");
				param.addValue("notificationFlag", objSearchDao.getNotificationFlag());
			}
			if (objSearchDao.getNotificationType() != null && !objSearchDao.getNotificationType().isEmpty()) {
				if (objSearchDao.getNotificationType().equals("true")) {
					objSearchDao.setNotificationType("Y");
				} else {
					objSearchDao.setNotificationType("N");
				}
				pSql.append(" NOTIFICATION_TYPE LIKE :notificationType AND ");
				param.addValue("notificationType", objSearchDao.getNotificationType());
			}
			if (objSearchDao.getBillingServiceFlag() != null && !objSearchDao.getBillingServiceFlag().isEmpty()) {
				if (objSearchDao.getBillingServiceFlag().equals("true")) {
					objSearchDao.setBillingServiceFlag("Y");
				} else {
					objSearchDao.setBillingServiceFlag("N");
				}
				pSql.append(" BILLING_SERVICE_FLAG LIKE :billingServiceFlag AND ");
				param.addValue("billingServiceFlag", objSearchDao.getBillingServiceFlag());
			}
			if (objSearchDao.getCloseContactFlag() != null && !objSearchDao.getCloseContactFlag().isEmpty()) {
				if (objSearchDao.getCloseContactFlag().equals("true")) {
					objSearchDao.setCloseContactFlag("Y");
				} else {
					objSearchDao.setCloseContactFlag("N");
				}
				pSql.append(" CLOSE_CONTACT_FLAG LIKE :closeContactFlag AND ");
				param.addValue("closeContactFlag", objSearchDao.getCloseContactFlag());
			}
			if (objSearchDao.getTransportationFlag() != null && !objSearchDao.getTransportationFlag().isEmpty()) {
				if (objSearchDao.getTransportationFlag().equals("true")) {
					objSearchDao.setTransportationFlag("Y");
				} else {
					objSearchDao.setTransportationFlag("N");
				}
				pSql.append(" TRANSPORTATION_FLAG LIKE :transportationFlag AND ");
				param.addValue("transportationFlag", objSearchDao.getTransportationFlag());
			}

			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().isEmpty()) {
				if (objSearchDao.getActiveFlag().equals("true")) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				pSql.append(" ACTIVE_FLAG LIKE :activeFlag AND ");
				param.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getListSeq() != null) {
				pSql.append(" LIST_SEQ LIKE :listSeq AND ");
				param.addValue("listSeq", objSearchDao.getListSeq());
			}
		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" Order by MOVEMENT_TYPE,MOVEMENT_REASON_CODE,DESCRIPTION");
		final RowMapper<MovementReasons> senCalcRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				MovementReasons.class, movementReasonsMapping);
		List<MovementReasons> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, param, senCalcRowMapper);
		} catch (Exception e) {
			logger.error("moveRsnExecuteQuery", e);
		}
		return returnList;

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstMovementReasons
	 *            List<MovementReasons>
	 *
	 * @return List<Integer>
	 */
	public Integer moversnInsertMovementReasons(final List<MovementReasons> lstMovementReasons) {
		final String sql = getQuery("OUMEMOVE_MOVERSN_INSERT_MOVEMENT_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MovementReasons movementReasons : lstMovementReasons) {
			parameters.add(new BeanPropertySqlParameterSource(movementReasons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstMovementReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstMovementReasons
	 *            List<MovementReasons>
	 */
	public Integer moveRsnUpdateMovementReasons(final List<MovementReasons> lstMovementReasons) {
		final String sql = getQuery("OUMEMOVE_MOVERSN_UPDATE_MOVEMENT_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MovementReasons movementReasons : lstMovementReasons) {
			parameters.add(new BeanPropertySqlParameterSource(movementReasons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstMovementReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstMovementReasons
	 *            List<MovementReasons>
	 */
	public Integer moveRsnDeleteMovementReasons(final List<MovementReasons> lstMovementReasons) {
		final String sql = getQuery("OUMEMOVE_MOVERSN_DELETE_MOVEMENT_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MovementReasons movementReasons : lstMovementReasons) {
			parameters.add(new BeanPropertySqlParameterSource(movementReasons));
		}
		try {
			batchUpdatePreDeletedRows("MOVEMENT_REASONS", "MOVEMENT_TYPE=:movementType AND MOVEMENT_REASON_CODE=:movementReasonCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in moveRsnDeleteMovementReasons"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstMovementReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkMoveRsnMovementReasonRecordGroup() {
		final String sql = getQuery("OUMEMOVE_FIND_CGFKMOVERSNMOVEMENTREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkMoveRsnMovementReasonRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkMoveRsnMovementTypeRecordGroup() {
		final String sql = getQuery("OUMEMOVE_FIND_CGFKMOVERSNMOVEMENTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkMoveRsnMovementTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkMovementReasons
	 *
	 * @param params
	 */

	public Integer cgrichkMovementReasonsScheduleCheck(final MovementReasons movementReasons) {
		final String sql = getQuery("OUMEMOVE_CGRICHK_SCHEDULE_MOVEMENT_REASONS");
		Integer returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("movementType",
				movementReasons.getMovementType(), "movementReasonCode", movementReasons.getMovementReasonCode()),
				Integer.class);
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkMovementReasons
	 *
	 * @param params
	 */
	public Integer cgrichkMovementReasonsExterMovment(final MovementReasons movementReasons) {
		final String sql = getQuery("OUMEMOVE_CGRICHK_MOVEMENT_REASONS");
		Integer returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("movementType",
				movementReasons.getMovementType(), "movementReasonCode", movementReasons.getMovementReasonCode()),
				Integer.class);
		return returnValue;
	}

}
