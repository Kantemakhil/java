package net.syscon.s4.inst.movementexternal.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderIndSchedule;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.movementexternal.OiiwltwjRepository;
import net.syscon.s4.inst.movementexternal.OiiwltwjService;
import net.syscon.s4.inst.movementexternal.beans.VTransferWaitingLists2;
import net.syscon.s4.inst.movementexternal.beans.VTransferWaitingLists2CommitBean;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitLists;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;

/**
 * Class OiiwltwjServiceImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Service
public class OiiwltwjServiceImpl extends BaseBusiness implements OiiwltwjService {

	@Autowired
	private OiiwltwjRepository oiiwltwjRepository;

	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;

	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;

	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;

	@Autowired
	private OffenderIndSchedulesT4Service offenderIndSchedulesT4Service;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiwltwjServiceImpl.class.getName());

	/**
	 * Creates new OiiwltwjServiceImpl class Object
	 */
	public OiiwltwjServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public VHeaderBlock CgfkchkVTwlVTwlVOffBkg(VHeaderBlock paramBean) {
		VHeaderBlock vHeaderBlock = oiiwltwjRepository.cgfkchkVTwlVTwlVOffBkg(paramBean);
		return vHeaderBlock;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<VTransferWaitingLists2> RefreshCheckSum(VTransferWaitingLists2 paramBean) {
		List<VTransferWaitingLists2> vTransferWaitingLists2List = oiiwltwjRepository.refreshCheckSum(paramBean);
		return vTransferWaitingLists2List;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes GetParentCode(ReferenceCodes paramBean) {
		ReferenceCodes referenceCodesList = oiiwltwjRepository.getParentCode(paramBean);
		return referenceCodesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VTransferWaitingLists2> vTwlExecuteQuery(String caseLoadId,String userName) {
		List<VTransferWaitingLists2> resultList = oiiwltwjRepository.vTwlExecuteQuery(caseLoadId,userName);
		if (resultList.isEmpty()) {
			VTransferWaitingLists2 waitingError = new VTransferWaitingLists2();
			waitingError.setErrorMessage("To");
			resultList.add(waitingError);
		}
		return resultList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_TWL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String vTwlCommit(VTransferWaitingLists2CommitBean CommitBean) {
		String liReturn = null;
		try {
			if (CommitBean.getUpdateList() != null && CommitBean.getUpdateList().size() > 0) {
				OffenderIndSchedule offenderSchedules = new OffenderIndSchedule();
				offenderSchedules.setModifyUserId(CommitBean.getCreateUserId());
				OffenderIndSchedules schedules = new OffenderIndSchedules();
				List<OffenderIndSchedule> offenderSchedulesUpdateList = new ArrayList<>();
				OffenderIndSchWaitLists offenderSchedulesList = new OffenderIndSchWaitLists();
				List<OffenderIndSchWaitLists> offenderSchedulesListUpdateList = new ArrayList<>();
				for (VTransferWaitingLists2 updateData : CommitBean.getUpdateList()) {
					List<OffenderIndSchedules> list1 = new ArrayList<>();
					for(OffenderIndSchedules sch:list1) {
						BeanUtils.copyProperties(updateData, sch);
						sch.setAgyLocId(updateData.getAgyLocId());
						sch.setSealFlag(updateData.getActiveFlag());
					}
					offenderSchedules.setToAgyLocId(updateData.getAgencyLocationTo());
					offenderSchedules.setEventId(Long.valueOf(updateData.getEventId() + ""));
					if (updateData.getWaitListStatus().equals("CON")) {
						offenderSchedules.setEventStatus("SSH");
						offenderSchedules.setModifyUserId(CommitBean.getCreateUserId());
						offenderSchedulesUpdateList.add(offenderSchedules);
						try {
							BeanUtils.copyProperties(updateData, schedules);
							OffenderCaseNotes notes = new OffenderCaseNotes();
							BeanUtils.copyProperties(offenderSchedules, notes);
							BeanUtils.copyProperties(offenderSchedules, schedules);

							list1 = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(list1);
							list1 = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(list1);
							offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(schedules, "UPDATING");
							 offenderIndSchedulesT4Service.offenderIndSchedulesT4(notes, schedules, null);
						} catch (Exception e) {
							logger.error(e);
						}
						liReturn = oiiwltwjRepository
								.oiiwltwjUpdateScheduleInfoOffenderIndSchedulesSsh(offenderSchedulesUpdateList)
								.toString();
						offenderSchedulesUpdateList.clear();
					} else {
						offenderSchedulesUpdateList.add(offenderSchedules);
						OffenderCaseNotes notes = new OffenderCaseNotes();
						BeanUtils.copyProperties(offenderSchedules, notes);
						BeanUtils.copyProperties(offenderSchedules, schedules);
						offenderIndSchedulesT4Service.offenderIndSchedulesT4(notes, schedules, null);

						list1 = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(list1);
						list1 = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(list1);
						offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(schedules, "UPDATING");
						liReturn = oiiwltwjRepository
								.oiiwltwjUpdateScheduleInfoOffenderIndSchedules(offenderSchedulesUpdateList).toString();
						offenderSchedulesUpdateList.clear();
					}
					offenderSchedules = null;
					offenderSchedulesList.setWaitListStatus(updateData.getWaitListStatus());
					offenderSchedulesList.setTransferPriority(updateData.getTransferPriority());
					offenderSchedulesList.setEventId(Integer.valueOf(updateData.getEventId().toString()));
					offenderSchedulesList.setOutcomeReasonCode(updateData.getOutcomeReasonCode());
					offenderSchedulesList.setModifyUserId(CommitBean.getCreateUserId());
					offenderSchedulesListUpdateList.add(offenderSchedulesList);
					liReturn = oiiwltwjRepository
							.oiiwltwjUpdateScheduleInfoOffenderIndSchedulesWaitList(offenderSchedulesListUpdateList)
							.toString();
					offenderSchedulesListUpdateList.clear();
				}

			}
		} catch (Exception e) {
			logger.error("vTwlCommit", e);
			liReturn = e.getMessage();
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkVTwlDspDescriptionRecordGroup() {
		return oiiwltwjRepository.cgfkVTwlDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> cgfkVTwlAgencyLocationToRecordGroup() {
		List<AgencyLocations> refList = oiiwltwjRepository.cgfkVTwlAgencyLocationToRecordGroup();
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkVTwlDspDescription3RecordGroup() {
		return oiiwltwjRepository.cgfkVTwlDspDescription3RecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		return oiiwltwjRepository.rgCancelReasonRecordGroup();

	}

	@Override
	public AgencyLocations cgfkchkVTwlVTwlAgyLoc(String param) {
		AgencyLocations paramBean = new AgencyLocations();
		paramBean.setAgyLocId(param);
		AgencyLocations result = oiiwltwjRepository.cgfkchkVTwlVTwlAgyLoc(paramBean);
		return result;
	}

}