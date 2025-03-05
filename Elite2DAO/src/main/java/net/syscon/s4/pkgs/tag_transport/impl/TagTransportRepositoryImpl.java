package net.syscon.s4.pkgs.tag_transport.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.transportation.beans.VAssignOffenderTrips;
import net.syscon.s4.inst.transportation.beans.VLocalTripOffenders;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.pkgs.tag_termination.impl.TagTerminationRepositoryImpl;
import net.syscon.s4.pkgs.tag_transport.TagTransportRepository;

@Repository
public class TagTransportRepositoryImpl extends RepositoryBase implements TagTransportRepository {

	private static Logger logger = LogManager.getLogger(TagTerminationRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offMoveDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	
	public Integer tagtransportGenerateSchedules(String routeName) {
		final String sql = getQuery("TAG_TRANSPORT_GENERATE_SCHEDULES");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("route_name", routeName),
					Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return count;
	}

	
	
	public Integer tagtransportInsert(ScheduledTrips ob, Date lSchDate, Integer routeLength) {
		String sql = getQuery("TAG_TRANSPORT_INSERT_SCHEDULED_TRIPS");
		Integer count = 0;
		
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("l_sch_date", lSchDate);
		map.addValue("p_trip_code", ob.getTripCode());
		map.addValue("p_departure_time", ob.getDepartureDate());
		map.addValue("route_length", routeLength);
		map.addValue("p_route_name", ob.getRouteName());
		map.addValue("create_user_id", ob.getCreateUserId());
		try {
			count = namedParameterJdbcTemplate.update(sql, map);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " schPlannerInsertScheduledTrips in error " + e.getMessage());
		}
		return count;
	}

	@Override
	public Integer tagtransportGetRouteLen(String route) {
		String sql = getQuery("TAG_TRANSPORT_GET_ROUTE_LEN");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_route", route), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " tagtransportGetRouteLen in error ");
		}
		return count;
	}

	@Override
	public Date tagtransportCompTimeCur(Integer pRouteLen, Date pEstDepTime, Date pDepDate) {
		Date date = null;
		String sql = getQuery("TAG_TRANSPORT_COMP_TIME_CUR");
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_route_len", pRouteLen, "p_dep_date", pDepDate, "p_est_dep_time", pEstDepTime),
					Date.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " tagtransportGetRouteLen in error ");
		}
		return date;
	}

	@Override
	public Integer tagtransportOvernightCur(String route) {
		String sql = getQuery("TAG_TRANSPORT_OVERNIGHT_CUR");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_route", route), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " tagtransportOvernightCur in error ");
		}
		return count;
	}

	@Override
	public RouteStopDetails tagtransportMxStopCur(String route) {
		String sql = getQuery("TAG_TRANSPORT_MX_STOP_CUR");
		RouteStopDetails mxStopCur = null;
		try {
			mxStopCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_route", route),
					RouteStopDetails.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " tagtransportMxStopCur in error ");
			mxStopCur = new RouteStopDetails();
		}
		return mxStopCur;
	}

	@Override
	public RouteStopDetails tagtransportMxNightCur(String route) {
		String sql = getQuery("TAG_TRANSPORT_MX_NIGHT_CUR");
		RouteStopDetails mxNightCur = null;
		try {
			mxNightCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_route", route),
					RouteStopDetails.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " tagtransportMxNightCur in error ");
			mxNightCur = new RouteStopDetails();
		}
		return mxNightCur;
	}

	@Override
	public Long getvSegmentLength(final String routeName) {
		final String sql = getQuery("TAG_TRANSPORT_GET_V_SEGMENT_LENGTH");
		Long vsegmentlength = null;
		try {
			vsegmentlength = namedParameterJdbcTemplate.queryForObject(sql, createParams("routeName", routeName),
					Long.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getvSegmentLength() ", e);
		}
		return vsegmentlength;
	}

	@Override
	public Integer updateScheduledTrips(String routeName, String userName, Long vSegmentLength) {
		final String sql = getQuery("TAG_TRANSPORT_UPDATE_SCHEDULED_TRIPS");
		Integer updatecount = 0;
		try {
			updatecount = namedParameterJdbcTemplate.update(sql, createParams("p_route_name", routeName, "modifyUserId",
					userName, "v_segment_length", vSegmentLength));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " updateScheduledTrips() ", e);
			return 0;
		}
		return updatecount;
	}

	@Override
	public List<RouteStopDetails> getLocCur(String routeName) {
		List<RouteStopDetails> returnList = null;
		final String sql = getQuery("TAG_TRANSPORT_GET_ROUTE_STOP_DETAILS");

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("v_route_name", routeName),
					new BeanPropertyRowMapper<RouteStopDetails>(RouteStopDetails.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getLocCur() ", e);
		}
		return returnList;
	}

	@Override
	public Integer ifSegExistsCur(String vFromAgyLoc, String vToAgyLoc) {
		Integer count = 0;
		final String sql = getQuery("TAG_TRANSPORT_GET_COUNT_OF_AGENCY_SEGMENT_LENGTH");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("v_from_agy_loc", vFromAgyLoc, "v_to_agy_loc", vToAgyLoc), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " ifSegExistsCur() ", e);
		}
		return count;
	}

	@Override
	public Integer pAgencySegmentLengths(String vFromAgyLoc, String vToAgyLoc, final String userName) {
		Integer insertcount = 0;
		final String sql = getQuery("TAG_TRANSPORT_INSERT_P_AGENCY_SEGMENT_LENGTH");
		try {
			insertcount = namedParameterJdbcTemplate.update(sql,
					createParams("v_from_agy_loc", vFromAgyLoc, "v_to_agy_loc", vToAgyLoc, "userName", userName));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " pAgencySegmentLengths() ", e);
			return 0;
		}
		return insertcount;
	}

	@Override
	public Integer offenderProposedMvmnts(Long pOffBkg, Long pMoveSeq) {
		Integer res = 0;
		String sql = getQuery("TAG_TRANSPORT_OFFENDER_PROPOSED_MVMNTS");
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBkg", pOffBkg, "pMoveSeq", pMoveSeq),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "offenderProposedMvmnts in error ");
		}
		return res;
	}

	@Override
	public Long getSeqCur(Long pOffBkg, Long pMoveSeq) {
		Long res = null;
		String sql = getQuery("TAG_TRANSPORT_GET_SEQ_CUR_OFFENDER_MOVEMENT_DETAILS");
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBkg", pOffBkg, "pMoveSeq", pMoveSeq),
					Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getSeqCur in error ");
		}
		return res;
	}

	@Override
	public Long getSeqCurOne(Long pInmId) {
		String sql = getQuery("TAG_TRANSPORT_GET_SEQ_CUR_ONE");
		Long res = null;
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pInmId", pInmId), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getSeqCur in error ");
		}
		return res;
	}

	@Override
	public Long insertMvmntDetails(Long offenderBookId, Long movementSeq, Long detailSeq, String statusCode,
			String recordedBy, String appRsn, String txnStatus, String txnRsn) {
		Long res = null;
		String sql = getQuery("TAG_TRANSPORT_INSERT_MVMNT_DETAILS");
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "movementSeq", movementSeq, "detailSeq", detailSeq,
							"statusCode", statusCode, "recordedBy", recordedBy, "appRsn", appRsn, "txnStatus",
							txnStatus, "txnRsn", txnRsn),
					Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "insertMvmntDetails in error ");
		}
		return res;
	}

	@Override
	public Integer nonAdmittedInmateMvmts(Long pInmId) {
		Integer res = 0;
		String sql = getQuery("TAG_TRANSPORT_NON_ADMITTED_INMATE_MVMTS");
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pInmId", pInmId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getSeqCur in error ");
		}
		return res;
	}

	@Override
	public Integer insertNadmMvmntDetails(Long pInmId, String pStatus, String pTxnStat, Date pRecdDt, String pAppRsn,
			String pTxnRsn, String pUser, Long pDetSeq) {
		Long res = null;
		String sql = getQuery("TAG_TRANSPORT_INSERT_NADM_MVMNT_DETAILS");
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pInmId", pInmId, "pDetSeq", pDetSeq,
					"pStatus", pStatus, "pAppRsn", pAppRsn, "pTxnStat", pTxnStat, "pTxnRsn", pTxnRsn), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "insertMvmntDetails in error ");
		}
		return null;
	}

	@Override
	public List<ScheduledTrips> schTripCur(String pTripCode, Date pDepDt) {
		String sql = getQuery("TAG_TRANSPORT_SCH_TRIP_CUR");
		List<ScheduledTrips> res = new ArrayList<>();
		try {
			res = namedParameterJdbcTemplate.query(sql, createParams("pTripCode", pTripCode, "pDepDt", pDepDt),
					new BeanPropertyRowMapper<ScheduledTrips>(ScheduledTrips.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "schTripCur in error ");
		}
		return res;
	}

	@Override
	public Long overnightCur(String pRoute) {
		String sql = getQuery("TAG_TRANSPORT_OVERNIGHT_CUR");
		Long res = 0L;
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pRoute", pRoute), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "overnightCur in error ");
		}
		return res;
	}

	@Override
	public List<BigDecimal> mxStopCur(String pRoute) {
		String sql = getQuery("TAG_TRANSPORT_MX_STOP_CUR");
		List<BigDecimal> res = new ArrayList<>();
		try {
			res = namedParameterJdbcTemplate.query(sql, createParams("pRoute", pRoute),
					new BeanPropertyRowMapper<BigDecimal>(BigDecimal.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "mxStopCur in error ");
		}
		return res;
	}

	@Override
	public List<BigDecimal> mxNightCur(String pRoute) {
		String sql = getQuery("TAG_TRANSPORT_MX_NIGHT_CUR");
		List<BigDecimal> res = new ArrayList<>();
		try {
			res = namedParameterJdbcTemplate.query(sql, createParams("pRoute", pRoute),
					new BeanPropertyRowMapper<BigDecimal>(BigDecimal.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "mxNightCur in error ");
		}
		return res;
	}

	@Override
	public Integer scheduledTrips(String vRoute1, Date vCompDt, Long vSchId) {
		Integer res = 0;
		String sql = getQuery("TAG_TRANSPORT_SCHEDULED_TRIPS");
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("vRoute1", vRoute1, "vCompDt", vCompDt, "vSchId", vSchId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "mxNightCur in error ");
		}
		return res;
	}

	@Override
	public String tripDetCur(Long pTripId) {
		String sql = getQuery("TAG_TRANSPORT_TRIP_DET_CUR");
		String vAction = null;
		try {
			vAction = namedParameterJdbcTemplate.queryForObject(sql, createParams("pTripId", pTripId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "tripDetCur in error ");
		}
		return vAction;
	}

	@Override
	public Long ifExMovExists(Long pEventId) {
		String sql = getQuery("TAG_TRANSPORT_IF_EXIST_MOV");
		Long res = null;
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pEventId", pEventId), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "ifExMovExists in error ");
		}
		return res;
	}

	public Integer oidaotstUpdateOffenderIndSchedules(VLocalTripOffenders bean) {
		final String sql = getQuery("TAG_TRANSPORT_UPDATE_OFFENDER_IND_SCHEDULES");
		Integer updateOffenderIndSchedules = 0;
		try {
			updateOffenderIndSchedules = namedParameterJdbcTemplate.update(sql,
					createParams("p_event_id", bean.getEventId(), "p_offender_book_id", bean.getOffenderBookId(),
							"p_scheduled_trip_id", bean.getScheduledTripId()));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " oidaotstUpdateOffenderIndSchedules() ",
					e);
			return 0;
		}
		return updateOffenderIndSchedules;

	}

	public Integer oidaotstUpdateOffenderCourtEvents(VLocalTripOffenders bean) {
		final String sql = getQuery("TAG_TRANSPORT_UPDATE_OFFENDER_COURT_EVENTS");
		Integer UpdateCourtEvent = 0;
		try {
			UpdateCourtEvent = namedParameterJdbcTemplate.update(sql, createParams("p_event_id", bean.getEventId(),
					"p_offender_book_id", bean.getOffenderBookId(), "p_scheduled_trip_id", bean.getScheduledTripId()));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " oidaotstUpdateOffenderCourtEvents() ",
					e);
			return 0;
		}
		return UpdateCourtEvent;
	}

	public Integer oidaotstremoveOffenderFromTrip(VLocalTripOffenders bean) {
		final String sql = getQuery("TAG_TRANSPORT_REMOVE_OFFENDER_FROM_TRIPS");
		Integer removeOffenderFromTrip = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "non_admitted_inmate_mvmts";
			String whereCondition = "scheduled_trip_id = :p_scheduled_trip_id AND non_adm_inmate_id = :p_inmate_id";
			inputMap.put("p_scheduled_trip_id", bean.getScheduledTripId());
			inputMap.put("p_inmate_id", bean.getInmateId());
			inputMap.put("modifyUserId", bean.getCreateUserId());
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in oidaotstremoveOffenderFromTrip " + e.getMessage());
		}
		try {
			removeOffenderFromTrip = namedParameterJdbcTemplate.update(sql,
					createParams("p_inmate_id", bean.getInmateId(), "p_scheduled_trip_id", bean.getScheduledTripId()));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " oidaotstremoveOffenderFromTrip() ", e);
			return 0;
		}
		return removeOffenderFromTrip;
	}

	public Integer assignOffenderToTrip(VAssignOffenderTrips bean) {
		Integer assignOffenderToTrip = 0;
		final String sql = getQuery("TAG_TRANSPORT_ASSIGN_OFFENDER_TO_TRIPS");
		try {
			assignOffenderToTrip = namedParameterJdbcTemplate.update(sql, createParams());
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " oidaotstremoveOffenderFromTrip() ", e);
			return 0;
		}

		return assignOffenderToTrip;
	}

	public Integer ScheduledTripsNowait(Integer scheduledTripId) {
		Integer ScheduledTrips = 0;
		final String sql = getQuery("TAG_TRANSPORT_SCHEDULED_TRIPS_NOWAIT");
		try {
			ScheduledTrips = namedParameterJdbcTemplate.update(sql, createParams("scheduled_trip_id", scheduledTripId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " ScheduledTripsNowait() ", e);
			return 0;
		}

		return ScheduledTrips;
	}

	public Integer TotalOffenderIdScheduledId(Integer scheduledTripId, Integer offenderBookId) {
		Integer ScheduledTrips = 0;
		final String sql = getQuery("TAG_TRANSPORT_TOTAL_SCHEDULED_TRIPS");
		try {
			ScheduledTrips = namedParameterJdbcTemplate.update(sql,
					createParams("scheduled_trip_id", scheduledTripId, "offender_book_id", offenderBookId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " TotalOffenderIdScheduledId() ", e);
			return 0;
		}

		return ScheduledTrips;
	}

	public Integer UpdateOffenderIndSchedules(VAssignOffenderTrips bean) {
		final String sql = getQuery("TTAG_TRANSPORT_UPDATE_OFFENDER_IND_SCHEDULES");
		Integer updateOffenderIndSchedules = 0;
		try {
			updateOffenderIndSchedules = namedParameterJdbcTemplate.update(sql,
					createParams("p_event_id", bean.getEventId(), "p_offender_book_id", bean.getOffenderBookId(),
							"p_scheduled_trip_id", bean.getScheduledTripId()));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " UpdateOffenderIndSchedules() ", e);
			return 0;
		}
		return updateOffenderIndSchedules;

	}

	public Integer UpdateOffenderCourtEvents(VAssignOffenderTrips bean) {
		final String sql = getQuery("TAG_TRANSPORT_UPDATE_OFFENDER_COURT_EVENTS");
		Integer UpdateCourtEvent = 0;
		try {
			UpdateCourtEvent = namedParameterJdbcTemplate.update(sql, createParams("p_event_id", bean.getEventId(),
					"p_offender_book_id", bean.getOffenderBookId(), "p_scheduled_trip_id", bean.getScheduledTripId()));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " UpdateOffenderCourtEvents() ", e);
			return 0;
		}
		return UpdateCourtEvent;
	}

	public Integer TotalScheduledTripId(Integer scheduledTripId) {
		Integer ScheduledTrips = 0;
		final String sql = getQuery("TAG_TRANSPORT_COUNT_SCHEDULED_TRIPS");
		try {
			ScheduledTrips = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("scheduled_trip_id", scheduledTripId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " TotalScheduledTripId() ", e);
			return 0;
		}

		return ScheduledTrips;
	}

	public Integer retrieveOidaotstCriteria(String fromDate, String toDate, String lFromAgyLocId, String lToAgyLocId) {
		final String sql = getQuery("TAG_TRANSPORT_OFFSCHEDS_FIND_V_ASSIGN_OFFENDER_TRIPS");
		Integer retrieveData = 0;
		String lCriteriaString = "";
		String lDefaultWhere;
		String lStartTime = "";
		String lEndTime = "";
		String lScheduleType = "";
		String lScheduleReason = "";
		String lGender = "";
		String lOffenderIdDisplay = "";
		StringBuffer sqlAppend = new StringBuffer(sql);
		try {
			if (fromDate != null) {
				lCriteriaString = " l_from_date = " + fromDate;
			}
			if (toDate != null) {
				lCriteriaString = " l_to_date = " + toDate;
			}
			if (lFromAgyLocId != null) {
				lCriteriaString = " l_from_agy_loc_id = " + lFromAgyLocId;
			}
			if (lToAgyLocId != null) {
				lCriteriaString = " l_to_agy_loc_id = " + lToAgyLocId;
			}
			if (lStartTime != null) {
				lCriteriaString = " l_to_agy_loc_id = " + lStartTime;
			}
			if (lEndTime != null) {
				lCriteriaString = " l_end_time = " + lEndTime;
			}
			if (lScheduleType != null) {
				lCriteriaString = " l_schedule_type = " + lScheduleType;
			}
			if (lScheduleReason != null) {
				lCriteriaString = " l_schedule_reason = " + lScheduleReason;
			}
			if (lGender != null) {
				lCriteriaString = " l_gender = " + lGender;
			}
			if (lOffenderIdDisplay != null) {
				lCriteriaString = " l_offender_id_display = " + lOffenderIdDisplay;
			}
			sqlAppend.append("WHERE" + lCriteriaString);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " retrieveOidaotstCriteria() ", e);
		}
		return retrieveData;
	}

	public Integer tripscheckAnyNonAssocnExists(Integer rootOffenderId,Integer offenderBookId) {
		Integer checkAnyNonAssocnExists = 0;
		final String sql = getQuery("TAG_TRANSPORT_OFFTRIPS_NEW_RECORD_INSTANCE");
		try {
			checkAnyNonAssocnExists = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_root_offender_id",rootOffenderId,"offender_book_id", offenderBookId), Integer.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " checkAnyNonAssocnExists() ", e);
		}
		return checkAnyNonAssocnExists;
	}

	public Integer lRootOffenderIdCur(Integer offenderBookId) {
		Integer lRootOffenderIdCur = 0;
		final String sql = getQuery("TAG_TRANSPORT_OFFENDER_BOOKINGS_RECORDS");
		try {
			lRootOffenderIdCur = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offenderBookId), Integer.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " lRootOffenderIdCur() ", e);
		}
		return lRootOffenderIdCur;
	}

	public Integer offShedscheckAnyNonAssocnExists(List<VAssignOffenderTrips> offenderBookId) {
		Integer checkAnyNonAssocnExists = 0;
		final String sql = getQuery("TAG_TRANSPORT_OFFSCHEDS_NEW_RECORD_INSTANCE");
		try {
			checkAnyNonAssocnExists = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offenderBookId), Integer.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " offShedscheckAnyNonAssocnExists() ",
					e);
		}
		return checkAnyNonAssocnExists;
	}

	@Override
	public List<OffenderMovementDetails> statusInmCur(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderMovementDetails> returnList = new ArrayList<OffenderMovementDetails>();
		final String sql = getQuery("TAG_TRANSPORT_STATUS_INM_CUR");
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
	public OffenderMovementDetails statusNonInmCur() {
		OffenderMovementDetails statusNonInmCur = null;
		final String sql = getQuery("TAG_TRANSPORT_STATUS_NON_INM_CUR");
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
	public List<OffenderMovementDetails> maxStatusInmCur(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderMovementDetails> returnList = new ArrayList<OffenderMovementDetails>();
		final String sql = getQuery("TAG_TRANSPORT_MAX_STATUS_INM_CUR");
		final RowMapper<OffenderMovementDetails> offMovDetRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMovementDetails.class, offMoveDetMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_choice", pChoice, "p_off_bkg", pOffBkg, "p_move_seq", pMoveSeq),
					offMovDetRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " statusNonInmCur in error ");
		}
		return returnList;
	}

	@Override
	public OffenderMovementDetails maxStatusNonInmCur() {
		OffenderMovementDetails maxStatusNonInmCur = null;
		final String sql = getQuery("TAG_TRANSPORT_MAX_STATUS_NON_INM_CUR");
		try {
			maxStatusNonInmCur = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					OffenderMovementDetails.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " maxStatusInmCur in error ");
			maxStatusNonInmCur = new OffenderMovementDetails();
		}
		return maxStatusNonInmCur;
	}

	@Override
	public Integer getSeqCur(Integer pOffBkg) {
		Integer seqCur = null;
		final String sql = getQuery("TAG_TRANSPORT_GET_SEQ_CUR");
		try {
			seqCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBkg", pOffBkg), Integer.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getSeqCur() ", e);
		}
		return seqCur;
	}

	@Override
	public List<OffenderLocChngDtls> statusInmCurInstLoc(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderLocChngDtls> returnList = new ArrayList<>();
		final String sql = getQuery("TAG_TRANSPORT_STATUS_INM_CUR_INT_LOC");
		final RowMapper<OffenderLocChngDtls> offMovDetRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLocChngDtls.class, offMoveDetMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_choice", pChoice, "p_off_bkg", pOffBkg, "p_loc_seq", pMoveSeq),
					offMovDetRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " statusInmCurInstLoc in error ");
		}
		return returnList;
	}
	@Override
	public List<OffenderLocChngDtls> maxStatusInmCurInstLoc(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderLocChngDtls> returnList = new ArrayList<>();
		final String sql = getQuery("TAG_TRANSPORT_MAX_STATUS_INM_CUR_INT_LOC");
		final RowMapper<OffenderLocChngDtls> offMovDetRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLocChngDtls.class, offMoveDetMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_choice", pChoice, "p_off_bkg", pOffBkg, "p_loc_seq", pMoveSeq),
					offMovDetRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " maxStatusInmCurInstLoc in error ");
		}
		return returnList;
	}


	@Override
	public Integer getSeqCurInst(Integer offenderBookId) {
		Integer seqCur = null;
		final String sql = getQuery("TAG_TRANSPORT_GET_SEQ_CUR_INST");
		try {
			seqCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Integer.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getSeqCurInst() ", e);
		}
		return seqCur;
	}

	@Override
	public String impStatusCur(Integer offenderBookId) {
		String vImpStatus;
		final String sql = getQuery("TAG_TRANSPORT_IMPSTATUS_CUR");
		vImpStatus = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId),
				String.class);
		return vImpStatus;
	}

	@Override
	public Integer sanctionCur(Integer offenderBookId) {
		Integer sanctionCur;
		final String sql = getQuery("TAG_TRANSPORT_SANCTION_CUR");
		sanctionCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId),
				Integer.class);
		return sanctionCur;
	}

	@Override
	public Integer stgAffiCur(Integer offenderBookId) {
		Integer vDescp;
		final String sql = getQuery("TAG_TRANSPORT_STGAFFI_CUR");
		vDescp = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId),
				Integer.class);
		return vDescp;
	}

	@Override
	public Integer getSeqCurr(Integer offenderBookId, Long movementSeq) {
		Integer pDetSeq = 0;
		final String sql = getQuery("TAG_TRANSPORT_GETSEQ_CUR");
		pDetSeq = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offender_book_id", offenderBookId, "movement_seq", movementSeq), Integer.class);
		return pDetSeq;
	}

	@Override
	public Long getSeqCurr(Long nonAdmInmateId) {
		Long pDetSeq = null;
		final String sql = getQuery("TAG_TRANSPORT_NONADMITTEDINMATE_GETSEQ_CUR");
		pDetSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("non_adm_inmate_id", nonAdmInmateId),
				Long.class);
		return pDetSeq;
	}

	@Override
	public String ifRoleAssigned(String userId, String roleNm) {

		final String sql = getQuery("TAG_TRANSPORT_IF_ROLE_ASSIGNED");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId, "roleNm", roleNm),
					String.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getImprisonmentStatus(Long offenderBookId) {
		String impresmntStatus = null;
		final String sql = getQuery("TAG_TRANSPORT_IMPRESMENT_STATUS");

		impresmntStatus = namedParameterJdbcTemplate.queryForObject(sql, createParams("offBkg", offenderBookId),
				String.class);

		return impresmntStatus;

	}

	@Override
	public Integer nonAssoCur(Long offenderBookId, String agyLocId) {
		Integer nonAsscr = 0;

		final String sql = getQuery("TAG_TRANSPORT_NON_ASS_COUNT");

		nonAsscr = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offBkg", offenderBookId, "agyLoc", agyLocId), Integer.class);

		return nonAsscr;
	}

	@Override
	public Integer nonAssoNsCur(Long offenderBookId, String agyLocId) {

		Integer nonAssNscr = 0;
		final String sql = getQuery("TAG_TRANSPORT_NON_ASS_NS_COUNT");

		nonAssNscr = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offBkg", offenderBookId, "agyLoc", agyLocId), Integer.class);

		return nonAssNscr;

	}

	@Override
	public Date getSchDate(String dayName , Date fromDate ,Integer week) {
		final String sql = getQuery("TAG_TRANSPORT_GET_SCHEDULE_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("day", dayName , "fromdate" ,fromDate,"week" ,week), Date.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getSchDate in error ");
			return null;
		}

	}



	@Override
	public Integer insertLocChngDtls(OffenderLocChngDtls insertBean) {
		final String sql = getQuery("TAG_TRANSPORT_INSERT_LOC_CHNG_DTLS");
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
	public Integer getSeqCur(Integer pOffBkg, Integer pLocSeq) {
		final String sql = getQuery("TAG_TRANSPORT_GET_SEQ_CUR_LOC_DTLS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_bkg", pOffBkg , "p_loc_seq" ,pLocSeq), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getSeqCur in error ");
			return null;
		}

	}

	@Override
	public Integer insertLocChngDtls(Long pOffBkg, Integer pLocSeq, Integer pDetSeq, String pStatus,
			String recordedBy, Date pRecdDt, String pAppRsn, String pTxnStat, String pTxnRsn) {
		Long res = null;
		String sql = getQuery("TAG_TRANSPORT_INSERT_LOC_CHNG_DTLS");
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_bkg", pOffBkg, "p_loc_seq", pLocSeq,"p_det_seq", pDetSeq,
					"p_status", pStatus,"p_recd_dt", pRecdDt, "p_app_rsn", pAppRsn, "p_txn_stat", pTxnStat, "p_txn_rsn", pTxnRsn), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "insertLocChngDtls in error ");
		}
		return null;

	}


	@Override
	public String ifIntrNonAssoExists(Integer offenderBookId, String currAgyId, Integer toLivingUnitId) {
		final String sql = getQuery("TAG_TRANSPORT_IF_INTR_NON_ASSO_EXISTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", offenderBookId,
					"curr_agy_id", currAgyId, "to_living_unit_id", toLivingUnitId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getSeqCur in error ");
			return null;
		}
	}



	@Override
	public Integer insertOffMovDtls(OffenderMovementDetails insertBean) {
		
			String sql = getQuery("TAG_TRANSPORT_INSERTINTO_OFFENDERMOVEMENTDETAILS");
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
	public Integer updateoffPrpMvmnts(OffenderMovementDetails remove) {
	Integer updateoffPrpMvmnts=0;
	String sql = getQuery("TAG_TRANSPORT_UPDATE_OFF_PROPOSED_MVMTS");
	try {
		updateoffPrpMvmnts = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",remove.getOffenderBookId(), "movementSeq",remove.getMovementSeq() ), Integer.class);
	} catch (Exception e) {
		logger.error(this.getClass().getName() + "updateoffPrpMvmnts in error ");
	}
	return updateoffPrpMvmnts;

	}



	@Override
	public List<VLocalTripOffenders> existsNonAsso(Long scheduledTripId, Long offenderBookId) {
		 String sql = getQuery("TAG_TRANSPORT_NON_ASSO_EXISTS");
		 List<VLocalTripOffenders> res = new ArrayList<>();
		 try {
				res = namedParameterJdbcTemplate.query(sql, createParams("pscheduledTripId", scheduledTripId,"pOffenderBookId",offenderBookId),
						new BeanPropertyRowMapper<VLocalTripOffenders>(VLocalTripOffenders.class));
			} catch (Exception e) {
				logger.error(this.getClass().getName() + "existsNonAss in error ");
			}
		
		return res;
		
	}



	@Override
	public String ifNonAssocBetween(Long pOffBkg1, Long pOffBkg2) {
		 String sql = getQuery("TAG_TRANSPORT_IF_NON_ASSOC_BETWEEN");
		 String nonAssoBetween=null;
		 try {
			 nonAssoBetween = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_bkg1", pOffBkg1,"p_off_bkg2",pOffBkg2),
					 String.class);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + "existsNonAss in error ");
			}

		return nonAssoBetween;
	}



	@Override
	public Integer existsNonAssoCount(Long offenderBookId, Long scheduledTripId) {
          
		Integer count = 0;
		final String sql = getQuery("TAG_TRANSPORT_GET_COUNT_OF_AGENCY_SEGMENT_LENGTH");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", offenderBookId, "p_scheduled_trip_id", scheduledTripId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " ifSegExistsCur() ", e);
		}
		return count;

		
	}



	@Override
	public Integer insertMvmntDetails(OffenderMovementDetails insertBean) {
		final String sql = getQuery("TAG_TRANSPORT_INSERT_MVMNT_DETAILS");
		int returnArray = 0;
		try {
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(insertBean);
			returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		} catch (DataAccessException e) {
			logger.error("Exception occured in " + this.getClass().getName() + " insertMvmntDetails() ", e);
		}
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}
	}



	@Override
	public Integer insertNadmMvmntDetails(OffenderMovementDetails insertBean) {
		final String sql = getQuery("TAG_TRANSPORT_INSERT_NADM_MVMNT_DETAILS");
		int returnArray = 0;
		try {
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(insertBean);
			returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
		} catch (DataAccessException e) {
			logger.error("Exception occured in " + this.getClass().getName() + " insertNadmMvmntDetails() ", e);
		}
		if (returnArray > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
}
