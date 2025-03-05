package net.syscon.s4.inst.transportation.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderExternalMovement;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.inst.transportation.maintenance.OimstripRepository;
import net.syscon.s4.inst.transportation.maintenance.OimstripService;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripAssignments;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripAssignmentsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Trips;
import net.syscon.s4.inst.transportation.maintenance.beans.TripsCommitBean;
import net.syscon.s4.pkgs.tag_transport.TagTransportService;

/**
 * Class OimstripServiceImpl
 */
@Service
public class OimstripServiceImpl implements OimstripService {

	@Autowired
	private OimstripRepository oimstripRepository;

	@Autowired
	private TagTransportService tagTransportService;

	@Autowired
	private EliteDateService eliteDateService;

	public int ynAlertAlert() {
		return (message("Trips", "Trip does not exist. Do you want to create this trip?"));
	}

	public int cancelSchTripAlert() {
		return (message("Cancel Trip", "Do you want to continue the route pattern for the Trip?"));
	}

	private int message(String string, String string2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Trips> tripsExecuteQuery(Trips searchRecord) {
		return oimstripRepository.tripsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTRIPS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer tripsCommit(TripsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(ele -> {
				ele.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = oimstripRepository.tripsInsertTrips(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(ele -> {
				ele.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oimstripRepository.tripsCommit(commitBean.getUpdateList());
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
		List<ScheduledTrips> list = oimstripRepository.scheduledTripsExecuteQuery(searchRecord);
		if (list.size() > 0) {
			list.forEach(obj -> {
				List<ScheduledTrips> listup = oimstripRepository
						.scheduledTripsExecuteQueryExtra(obj.getScheduledTripId());
				listup.forEach(k -> {
					obj.setOptCap(k.getOptCap());
					obj.setPhysCap(k.getPhysCap());
				});
			});
		}
		return list;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSCHEDULED_TRIPS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer scheduledTripsCommit(ScheduledTripsCommitBean commitBean) {
		int liReturn = 0;
		String cancelFlag = "Y";
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ScheduledTrips obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if (obj.getCancelFlag().equals("Y")) {
					if (obj.getvAction() == null) {
						if (obj.getvNum() > 0) {
							List<OffenderExternalMovement> offBkgList = oimstripRepository
									.offBkgCur(obj.getScheduledTripId());
							offBkgList.forEach(item -> {
								tagTransportService.removeFromTrip(obj.getScheduledTripId(), item.getOffenderBookId(),
										item.getMovementSeq(), null, "APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
							});

							List<BigDecimal> offNadmtCurList = oimstripRepository.offNadmtCur(obj.getScheduledTripId());
							offNadmtCurList.forEach(e -> {
								tagTransportService.removeFromTrip(obj.getScheduledTripId(), null, null, e.longValue(),
										"APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
							});

						} else {
							if ("ŸES".equalsIgnoreCase(obj.getBtnStatus())) {
								tagTransportService.pCancelTrip(obj.getTripCode(), obj.getDepartureDate(),
										obj.getScheduledTripId(), obj.getRouteName());
								List<OffenderExternalMovement> offBkgList = oimstripRepository
										.offBkgCur(obj.getScheduledTripId());
								offBkgList.forEach(item -> {
									tagTransportService.removeFromTrip(obj.getScheduledTripId(),
											item.getOffenderBookId(), item.getMovementSeq(), null, "APP", "PEND",
											obj.getvAction(),commitBean.getCreateUserId());
								});
								List<BigDecimal> offNadmtCurList = oimstripRepository
										.offNadmtCur(obj.getScheduledTripId());
								offNadmtCurList.forEach(e -> {
									tagTransportService.removeFromTrip(obj.getScheduledTripId(), null, null,
											e.longValue(), "APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
								});
								List<ScheduledTrips> schidCurList = oimstripRepository.schidCur(obj.getTripCode(),
										obj.getDepartureDate());
								for (ScheduledTrips item : schidCurList) {
									obj.setvAction(tagTransportService.getTripStatus(item.getScheduledTripId()));
									List<OffenderExternalMovement> offBkgList1 = oimstripRepository
											.offBkgCur(item.getScheduledTripId());
									for (OffenderExternalMovement offEXtOne : offBkgList1) {
										tagTransportService.removeFromTrip(obj.getScheduledTripId(),
												offEXtOne.getOffenderBookId(), offEXtOne.getMovementSeq(), null, "APP",
												"PEND", obj.getvAction(),commitBean.getCreateUserId());
									}
									List<BigDecimal> offNadmtCurList1 = oimstripRepository
											.offNadmtCur(item.getScheduledTripId());
									offNadmtCurList1.forEach(e -> {
										tagTransportService.removeFromTrip(obj.getScheduledTripId(), null, null,
												e.longValue(), "APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
									});
								}
							} else if ("NO".equalsIgnoreCase(obj.getBtnStatus())) {
								List<OffenderExternalMovement> offBkgList = oimstripRepository
										.offBkgCur(obj.getScheduledTripId());
								offBkgList.forEach(item -> {
									tagTransportService.removeFromTrip(obj.getScheduledTripId(),
											item.getOffenderBookId(), item.getMovementSeq(), null, ApplicationConstants.APP, "PEND",
											obj.getvAction(),commitBean.getCreateUserId());
								});

								List<BigDecimal> offNadmtCurList = oimstripRepository
										.offNadmtCur(obj.getScheduledTripId());
								offNadmtCurList.forEach(e -> {
									tagTransportService.removeFromTrip(obj.getScheduledTripId(), null, null,
											e.longValue(), "APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
								});
							}
						}
					} else {
						if ("ŸES".equalsIgnoreCase(obj.getBtnStatus())) {
							List<OffenderExternalMovement> offBkgList = oimstripRepository
									.offBkgCur(obj.getScheduledTripId());
							offBkgList.forEach(item -> {
								tagTransportService.removeFromTrip(obj.getScheduledTripId(), item.getOffenderBookId(),
										item.getMovementSeq(), null, "APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
							});

							List<BigDecimal> offNadmtCurList = oimstripRepository.offNadmtCur(obj.getScheduledTripId());
							offNadmtCurList.forEach(e -> {
								tagTransportService.removeFromTrip(obj.getScheduledTripId(), null, null, e.longValue(),
										"APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
							});
						} else {
							if ("ŸES".equalsIgnoreCase(obj.getBtnStatus())) {
								tagTransportService.pCancelTrip(obj.getTripCode(), obj.getDepartureDate(),
										obj.getScheduledTripId(), obj.getRouteName());
								List<OffenderExternalMovement> offBkgList = oimstripRepository
										.offBkgCur(obj.getScheduledTripId());
								offBkgList.forEach(item -> {
									tagTransportService.removeFromTrip(obj.getScheduledTripId(),
											item.getOffenderBookId(), item.getMovementSeq(), null, "APP", "PEND",
											obj.getvAction(),commitBean.getCreateUserId());
								});

								List<BigDecimal> offNadmtCurList = oimstripRepository
										.offNadmtCur(obj.getScheduledTripId());
								offNadmtCurList.forEach(e -> {
									tagTransportService.removeFromTrip(obj.getScheduledTripId(), null, null,
											e.longValue(), "APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
								});

								List<ScheduledTrips> schidCurList = oimstripRepository.schidCur(obj.getTripCode(),
										obj.getDepartureDate());
								for (ScheduledTrips item : schidCurList) {
									obj.setvAction(tagTransportService.getTripStatus(item.getScheduledTripId()));
									List<OffenderExternalMovement> offBkgList1 = oimstripRepository
											.offBkgCur(item.getScheduledTripId());
									for (OffenderExternalMovement offEXtOne : offBkgList1) {
										tagTransportService.removeFromTrip(obj.getScheduledTripId(),
												offEXtOne.getOffenderBookId(), offEXtOne.getMovementSeq(), null, "APP",
												"PEND", obj.getvAction(),commitBean.getCreateUserId());
									}
									List<BigDecimal> offNadmtCurList1 = oimstripRepository
											.offNadmtCur(item.getScheduledTripId());
									offNadmtCurList1.forEach(e -> {
										tagTransportService.removeFromTrip(obj.getScheduledTripId(), null, null,
												e.longValue(), "APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
									});
								}
							} else if ("NO".equalsIgnoreCase(obj.getBtnStatus())) {
								List<OffenderExternalMovement> offBkgList = oimstripRepository
										.offBkgCur(obj.getScheduledTripId());
								offBkgList.forEach(item -> {
									tagTransportService.removeFromTrip(obj.getScheduledTripId(),
											item.getOffenderBookId(), item.getMovementSeq(), null, "APP", "PEND",
											obj.getvAction(),commitBean.getCreateUserId());
								});

								List<BigDecimal> offNadmtCurList = oimstripRepository
										.offNadmtCur(obj.getScheduledTripId());
								offNadmtCurList.forEach(e -> {
									tagTransportService.removeFromTrip(obj.getScheduledTripId(), null, null,
											e.longValue(), "APP", "PEND", obj.getvAction(),commitBean.getCreateUserId());
								});
							}
						}
					}
					this.cancelSchTrip(obj);
				} else {
					cancelFlag = "N";
				}
			}
			liReturn = oimstripRepository.scheduledTripsUpdateScheduledTrips(commitBean.getUpdateList());
			if (liReturn == 1) {
				if (cancelFlag.equals(ApplicationConstants.NFLAG)) {
					// UPDATE QUERY Update trips set active_flg = 'Y', ex = null mmodd , modt =
					// cuurti, where tr
					oimstripRepository.triptableupdate(commitBean.getUpdateList().get(0).getTripCode(),
							commitBean.getUpdateList().get(0).getModifyUserId());
				}
			}
		}
		return liReturn;
	}

	private void cancelSchTrip(ScheduledTrips obj) {
		obj.setvBkg(oimstripRepository.ifOffOnTrip(obj.getTripCode(), obj.getDepartureDate()));
		obj.setvNonAdt(oimstripRepository.ifNadtOnTrip(obj.getTripCode(), obj.getDepartureDate()));
		if ("YES".equalsIgnoreCase(obj.getBtnStatus())) {
			if (obj.getvBkg() != null || obj.getvNonAdt() != null) {
				if (obj.getBtnStatus().equals("Yes")) {
					tagTransportService.pCancelTrip(obj.getTripCode(), obj.getDepartureDate(), obj.getScheduledTripId(),
							obj.getRouteName());
				}
				obj.setCancelFlag("Y");
				// obj.setCancelBy(obj.getCreateUserId());
				obj.setCancelDate(eliteDateService.getDBTime());
				List<BigDecimal> vSchidList = oimstripRepository.getSchIdCur(obj.getTripCode(), obj.getDepartureDate());
				vSchidList.forEach(vSchid -> {
					this.clearOffSch(vSchid.longValue());
				});
			} else {
				obj.setCancelFlag("Y");
				// obj.setCancelBy(obj.getCreateUserId());
				obj.setCancelDate(eliteDateService.getDBTime());
				tagTransportService.pCancelTrip(obj.getTripCode(), obj.getDepartureDate(), obj.getScheduledTripId(),
						obj.getRouteName());
			}
		} else if ("NO".equalsIgnoreCase(obj.getBtnStatus())) {
			obj.setCancelFlag("Y");
			// obj.setCancelBy(obj.getCreateUserId());
			obj.setCancelDate(eliteDateService.getDBTime());
			this.clearOffSch(obj.getScheduledTripId());
		}

	}

	private void clearOffSch(Long pSchId) {
		List<BigDecimal> vEventIdList = oimstripRepository.getEventIdCur(pSchId);
		vEventIdList.forEach(vEventId -> {
			if (tagTransportService.ifExMovExists(vEventId.longValue()) == 0) {
				oimstripRepository.vOffenderAllSchedules2(vEventId.longValue());
			}
		});
		oimstripRepository.nonAdmittedInmateMvmts(pSchId);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<ScheduledTripAssignments> scheduledTripAssignmentsExecuteQuery(ScheduledTripAssignments searchRecord) {
		return oimstripRepository.scheduledTripAssignmentsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSCHEDULED_TRIP_ASSIGNMENTS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer scheduledTripAssignmentsCommit(ScheduledTripAssignmentsCommitBean commitBean) {
		int liReturn = 0, vCount = 0, vIfAssigne = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (ScheduledTripAssignments ele : commitBean.getInsertList()) {
				ele.setCreateUserId(commitBean.getCreateUserId());
				// vCount = oimstripRepository.vehCur(ele.getAssignedId(),
				// ele.getScheduledTripId());
				vIfAssigne = oimstripRepository.ifAssignedCur(ele.getAssignedId(), ele.getDepartureDate(),
						ele.getCompletionDate());
				if (vIfAssigne > 1) {
					return 3;
				}
			}
			liReturn = oimstripRepository
					.scheduledTripAssignmentsInsertScheduledTripAssignments(commitBean.getInsertList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimstripRepository
					.scheduledTripAssignmentsDeleteScheduledTripAssignments(commitBean.getDeleteList());
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
	public List<ScheduledTripAssignments> staffAssignmentExecuteQuery(ScheduledTripAssignments searchRecord) {
		return oimstripRepository.staffAssignmentExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAFF_ASSIGNMENT
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer staffAssignmentCommit(ScheduledTripAssignmentsCommitBean commitBean) {
		int liReturn = 0, vCount = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(ele->ele.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oimstripRepository.staffAssignmentInsertScheduledTripAssignments(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimstripRepository.staffAssignmentUpdateScheduledTripAssignments(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimstripRepository.staffAssignmentDeleteScheduledTripAssignments(commitBean.getDeleteList());
		}

		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgTripTypeRecordGroup() {
		return oimstripRepository.rgTripTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgStaffIdRecordGroup() {
		return oimstripRepository.rgStaffIdRecordGroup();

	}

	@Override
	public Integer tagtransportCTrip(String tripCode) {
		Integer vCount = oimstripRepository.tagtransportCTrip(tripCode);
		return vCount;
	}

	@Override
	public List<ScheduledTrips> scheduleGenerateOidgenst(String tripCode) {
		return oimstripRepository.scheduleGenerateOidgenst(tripCode);
	}

	@Override
	public Integer tripsOidgenstInsert(Trips tripsModel) {
		return oimstripRepository.tripsOidgenstInsert(tripsModel);
	}

}