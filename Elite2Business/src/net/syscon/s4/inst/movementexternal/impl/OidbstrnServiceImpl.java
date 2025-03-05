package net.syscon.s4.inst.movementexternal.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movementexternal.OidbstrnRepository;
import net.syscon.s4.inst.movementexternal.OidbstrnService;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;

@Service
public class OidbstrnServiceImpl extends BaseBusiness implements OidbstrnService {

	private static Logger logger = LogManager.getLogger(OidbstrnServiceImpl.class.getName());
	@Autowired
	private OidbstrnRepository oidbstrnRepository;
	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;
	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;
	@Autowired
	private OffenderIndSchedulesT3Service offenderIndSchedulesT3Service;
	@Autowired
	private OffenderIndSchedulesT4Service offenderIndSchedulesT4Service;
	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OidbstrnServiceImpl class Object
	 */
	public OidbstrnServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> offAllSchExecuteQuery(String caseLoad, VOffenderAllSchedules searchRecord) {
		return oidbstrnRepository.offAllSchExecuteQuery(caseLoad, searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ALL_SCH
	 *
	 * 
	 */
	@Transactional
	public String offAllSchCommit(VOffenderAllSchedulesCommitBean commitBean) {
		String liReturn = null;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				OffenderIndSchedules bean = new OffenderIndSchedules();
				List<OffenderIndSchedules> insetBeanList = new ArrayList<>();
				for (VOffenderAllSchedules element : commitBean.getInsertList()) {
					Integer valid = oidbstrnRepository.oidbstrnDuplicateExists(element);
					if (element.getOffenderBookId() != null) {
						bean.setOffenderBookId(Integer.valueOf(element.getOffenderBookId().toString()));
					}
					bean.setEventDate(element.getEventDate());
					bean.setStartTime(element.getStartTime());
					bean.getStartTime().setDate(element.getEventDate().getDate());
					bean.setEventClass("EXT_MOV");
					bean.setEventType("TRN");
					if (element.getOutcomeReasonCode() != null) {
						bean.setEventStatus("CANC");
						bean.setOutcomeReasonCode(element.getOutcomeReasonCode());
					} else {
						bean.setEventStatus("SCH");
						bean.setOutcomeReasonCode(null);
					}
					if(valid == 1) {
						return "2";
					}
					bean.setEventSubType(element.getEventSubType());
					bean.setCommentText(element.getCommentText());
					bean.setAgyLocId(element.getAgyLocId());
					bean.setToAgyLocId(element.getToAgyLocId());
					bean.setEscortCode(element.getEscortCode());
					bean.setCreateUserId(commitBean.getCreateUserId());
					bean.setDirectionCode("OUT");
					insetBeanList.add(bean);
					insetBeanList = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(insetBeanList);
					insetBeanList = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(insetBeanList);
					for (OffenderIndSchedules element1 : insetBeanList) {
						offenderIndSchedulesT3Service.getVnumRows(element1);
						String sqlOperation = "INSERTING";
						offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(element1, sqlOperation);
					}
					liReturn = oidbstrnRepository.tagSchedulesCreateSchedules(insetBeanList).toString();
					insetBeanList.clear();
				}
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				OffenderIndSchedules bean = new OffenderIndSchedules();
				List<OffenderIndSchedules> updtBeanList = new ArrayList<>();
				for (VOffenderAllSchedules element : commitBean.getUpdateList()) {
					if (element.getOutcomeReasonCode() != null) {
						bean.setEventStatus("CANC");
						bean.setOutcomeReasonCode(element.getOutcomeReasonCode());
					} else {
						bean.setEventStatus("SCH");
						bean.setOutcomeReasonCode(null);
					}
					bean.setEventDate(element.getEventDate());
					bean.setStartTime(element.getStartTime());
					bean.setProvStateCode(element.getProvStateCode());
					bean.setEscortCode(element.getEscortCode());
					bean.setCommentText(element.getCommentText());
					bean.setEventId(Integer.valueOf(element.getEventId().toString()));
					bean.setModifyUserId(commitBean.getCreateUserId());
					bean.setEventType(element.getEventType());
					bean.setAgyLocId(element.getAgyLocId());
					bean.setEventSubType(element.getEventSubType());
					bean.setToAgyLocId(element.getToAgyLocId());
					if(element.getInChargeStaffId()!=null) {
					bean.setInChargeStaffId(element.getInChargeStaffId().intValue());
					}
					bean.setEventOutcome(element.getEventOutcome());
					bean.setCommentText(element.getCommentText()!=null?element.getCommentText():null);
					bean.setEndTime(element.getEndTime());
				
					updtBeanList.add(bean);
					updtBeanList = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(updtBeanList);
					updtBeanList = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(updtBeanList);
					for (OffenderIndSchedules element1 : updtBeanList) {
					String sqlOperation = "UPDATING";
					offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(element1, sqlOperation);
					}
					liReturn = oidbstrnRepository.tagSchedulesUpdateSchedules(updtBeanList).toString();
					updtBeanList.forEach(bo -> {
						offenderIndSchedulesT4Service.offenderIndSchedulesT4(null, bo, null);
					});

					updtBeanList.clear();
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<MovementReasons> rgReasonRecordGroup() {
		final List<MovementReasons> resultList = oidbstrnRepository.rgReasonRecordGroup();
		resultList.forEach(result -> {
			if (result.getListSeq() == 0) {
				result.setCanDisplay(false);
			}
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseLoadId) {
		return oidbstrnRepository.rgAgyLocRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAllAgyLocRecordGroup(String agyLocId) {
		List<AgencyLocations> resultList = oidbstrnRepository.rgAllAgyLocRecordGroup();
		resultList=resultList.stream().filter(C-> !C.getAgyLocId().equals(agyLocId)).collect(Collectors.toList());
		for (AgencyLocations result : resultList) {
			if (result.getAgyLocId().equals("TRN") || result.getAgyLocId().equals("OUT")) {
				result.setCanDisplay(false);
			} else {
				result.setCanDisplay(true);
			}
		}
		return resultList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgEscortRecordGroup() {
		return oidbstrnRepository.rgEscortRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		return oidbstrnRepository.rgCancelReasonRecordGroup();

	}

}
