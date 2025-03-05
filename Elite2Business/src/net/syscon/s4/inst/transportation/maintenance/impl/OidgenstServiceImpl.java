package net.syscon.s4.inst.transportation.maintenance.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.inst.transportation.maintenance.OidgenstRepository;
import net.syscon.s4.inst.transportation.maintenance.OidgenstService;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripParameters;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripParametersCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Trips;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_transport.TagTransportService;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;

@Service
public class OidgenstServiceImpl implements OidgenstService {

	@Autowired
	private OidgenstRepository oidgenstRepository;
	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private TagTransportService tagtransportservice;

	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;

	@Autowired
	private TagUtilsService tagUtilsService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ScheduledTripParameters> schPlannerExecuteQuery(ScheduledTripParameters searchRecord) {
		return oidgenstRepository.schPlannerExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSCH_PLANNER
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer schPlannerCommit(ScheduledTripParametersCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = oidgenstRepository.schPlannerInsertScheduledTripParameters(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidgenstRepository.schPlannerUpdateScheduledTripParameters(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ScheduledTrips> scheduledTripsExecuteQuery(ScheduledTrips searchRecord) {
		return oidgenstRepository.scheduledTripsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSCHEDULED_TRIPS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public ScheduledTrips scheduledTripsCommit(ScheduledTripsCommitBean commitBean) {
		int liReturn = 0;
		ScheduledTrips st = new ScheduledTrips();
		Trips tObj = new Trips();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			String tripCode = commitBean.getInsertList().get(0).getTripCode();
			for (ScheduledTrips data : commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
				data.setCancelFlag("N");
				String vSeg = null;
				Integer count = 0;
				count = oidgenstRepository.ifExistCur(data);
				if (count > 0) {
					st.setSealFlag("2");
//					st.setDepartureDate(data.getDepartureDate());
					return st;
				}
				data.setScheduledTripId(oidgenstRepository.getSchedSeq());
//				:scheduled_trips.trip_code := :trips.trip_code;
				data.setTripCode(data.getTripCode());

				vSeg = tagUtilsService.getSysProfile("CLIENT", "SEGMENT_TYPE");
				if (vSeg != null && vSeg.equals(ApplicationConstants.YFLAG)) {
					data.getEstDepartureTime().setDate(data.getDepartureDate().getDate());
					data.getEstDepartureTime().setMonth(data.getDepartureDate().getMonth());
					data.getEstDepartureTime().setYear(data.getDepartureDate().getYear());
				} else {
					data.setEstDepartureTime(null);
				}
				if (data.getTripType().equalsIgnoreCase("LOCAL")) {
					if (data.getEstDepartureTime() != null) {
						data.setCompletionDate(trunc(tagtransportservice.getRouteLen(data.getRouteName(),
								data.getEstDepartureTime(), data.getDepartureDate())));
						data.setEstCompletionTime(tagtransportservice.getRouteLen(data.getRouteName(),
								data.getEstDepartureTime(), data.getDepartureDate()));
					} else {
						data.setCompletionDate(data.getDepartureDate());
						data.setEstCompletionTime(null);
					}
				} else {
					tagtransportservice.getDayTravel(data.getRouteName(), data.getDepartureDate(),
							data.getCompletionDate());
				}
				data.setWeekDay(getDay(data.getDepartureDate() != null ? data.getDepartureDate().getDate() : null));
				data.setEndDay(getDay(data.getCompletionDate() != null ? data.getCompletionDate().getDate() : null));
			}
			st.setTripCode(commitBean.getInsertList().get(0).getTripCode());
			oidgenstRepository.oidgenstUpdateTrips(st);
			liReturn = oidgenstRepository.scheduledTripsInsertScheduledTrips(commitBean.getInsertList());
			st.setSealFlag(liReturn + "");
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			String tripCode = commitBean.getUpdateList().get(0).getTripCode();
			for (ScheduledTrips data : commitBean.getUpdateList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
				String vSeg = tagUtilsService.getSysProfile("CLIENT", "SEGMENT_TYPE");
				if (vSeg != null && vSeg.equals(ApplicationConstants.YFLAG)) {
					data.getEstDepartureTime().setDate(data.getDepartureDate().getDate());
					data.getEstDepartureTime().setMonth(data.getDepartureDate().getMonth());
					data.getEstDepartureTime().setYear(data.getDepartureDate().getYear());
				} else {
					data.setEstDepartureTime(null);
				}
				if (data.getTripType().equalsIgnoreCase("LOCAL")) {
					if (data.getEstDepartureTime() != null) {
						data.setCompletionDate(trunc(tagtransportservice.getRouteLen(data.getRouteName(),
								data.getEstDepartureTime(), data.getDepartureDate())));
						data.setEstCompletionTime(tagtransportservice.getRouteLen(data.getRouteName(),
								data.getEstDepartureTime(), data.getDepartureDate()));
					} else {
						data.setCompletionDate(data.getDepartureDate());
						data.setEstCompletionTime(null);
					}
				} else {
					tagtransportservice.getDayTravel(data.getRouteName(), data.getDepartureDate(),
							data.getCompletionDate());
				}
					st.setTripCode(commitBean.getUpdateList().get(0).getTripCode());
					data.setWeekDay(getDay(data.getDepartureDate() != null ? data.getDepartureDate().getDay() : null));
					data.setEndDay(getDay(data.getCompletionDate() != null ? data.getCompletionDate().getDay() : null));
			}
			oidgenstRepository.oidgenstUpdateTrips(st);
			liReturn = oidgenstRepository.scheduledTripsUpdateScheduledTrips(commitBean.getUpdateList());
			st.setSealFlag(liReturn + "");
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidgenstRepository.scheduledTripsDeleteScheduledTrips(commitBean.getDeleteList());
			st.setSealFlag(liReturn + "");
		}
		return st;

	}

	String getDay(int day) {
		String dayName = "";
		switch (day) {
		case 0:
			dayName = "SUN";
			break;
		case 1:
			dayName = "MON";
			break;
		case 2:
			dayName = "TUE";
			break;
		case 3:
			dayName = "WED";
			break;
		case 4:
			dayName = "THU";
			break;
		case 5:
			dayName = "FRI";
			break;
		case 6:
			dayName = "SAT";
			break;
		}
		return dayName;
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

	public List<ReferenceCodes> rgRouteRecordGroup() {
		return oidgenstRepository.rgRouteRecordGroup();
	}

	@Override
	public ScheduledTrips scheduledTripsvalidate(ScheduledTrips bean) {

		Integer vCurNum = oidgenstRepository.scheduledTripsvalidate(bean);
		;
		Date vMdate = oidgenstRepository.scheduledGenerateDate(bean);
		bean.setvCurNum(vCurNum);
		bean.setvMdate(vMdate);
		return bean;
	}

	@Override
	public ScheduledTrips scheduledGenerateCommit(List<ScheduledTripParameters> schTripParamLst) {
		ScheduledTrips schTrip = new ScheduledTrips();
		List<ScheduledTripParameters> list = new ArrayList<>();
		if (schTripParamLst != null && schTripParamLst.size() > 0) {
			try {
				for (ScheduledTripParameters obj : schTripParamLst) {
					Long vMaxWeek = 0l;
					if (vMaxWeek < obj.getWeekNo()) {
						vMaxWeek = obj.getWeekNo();
					}
					if (obj.getSunday() != null) {
						tagtransportservice.generateSchedules(obj.getTripCode(), obj.getSunday(), obj.getWeekNo(),
								"SUN", vMaxWeek, obj.getEstDepartureTime(), obj.getStartDate(), obj.getEndDate(),
								obj.getCreateUserId());
					}
					if (obj.getMonday() != null) {
						tagtransportservice.generateSchedules(obj.getTripCode(), obj.getMonday(), obj.getWeekNo(),
								"MON", vMaxWeek, obj.getEstDepartureTime(), obj.getStartDate(), obj.getEndDate(),
								obj.getCreateUserId());
					}
					if (obj.getTuesday() != null) {
						tagtransportservice.generateSchedules(obj.getTripCode(), obj.getTuesday(), obj.getWeekNo(),
								"TUE", vMaxWeek, obj.getEstDepartureTime(), obj.getStartDate(), obj.getEndDate(),
								obj.getCreateUserId());
					}
					if (obj.getWednesday() != null) {
						tagtransportservice.generateSchedules(obj.getTripCode(), obj.getWednesday(), obj.getWeekNo(),
								"WED", vMaxWeek, obj.getEstDepartureTime(), obj.getStartDate(), obj.getEndDate(),
								obj.getCreateUserId());
					}
					if (obj.getThursday() != null) {
						tagtransportservice.generateSchedules(obj.getTripCode(), obj.getThursday(), obj.getWeekNo(),
								"THU", vMaxWeek, obj.getEstDepartureTime(), obj.getStartDate(), obj.getEndDate(),
								obj.getCreateUserId());
					}
					if (obj.getFriday() != null) {
						tagtransportservice.generateSchedules(obj.getTripCode(), obj.getFriday(), obj.getWeekNo(),
								"FRI", vMaxWeek, obj.getEstDepartureTime(), obj.getStartDate(), obj.getEndDate(),
								obj.getCreateUserId());
					}
					if (obj.getSaturday() != null) {
						tagtransportservice.generateSchedules(obj.getTripCode(), obj.getSaturday(), obj.getWeekNo(),
								"SAT", vMaxWeek, obj.getEstDepartureTime(), obj.getStartDate(), obj.getEndDate(),
								obj.getCreateUserId());
					}
				}
				ScheduledTrips temp = new ScheduledTrips();
				schTrip.setSealFlag("sucess");
				Date vSdate = eliteDateService.getDBTime();
				vSdate = oidgenstRepository.getMaxDate(schTrip);
				if (vSdate != null && eliteDateService.getDBTime().compareTo(vSdate) > 0) {
					vSdate = eliteDateService.getDBTime();
					LocalDateTime today1 = vSdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
					today1 = today1.plusMonths(1);
					Date vEdate = Date.from(today1.atZone(ZoneId.systemDefault()).toInstant());
					temp.setStartDate(vSdate);
					temp.setEndDate(vEdate);
				}
			} catch (Exception e) {
				schTrip.setSealFlag("failed");
			}
		}
		return schTrip;
	}

}
