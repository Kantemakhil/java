package net.syscon.s4.globaloffenderrecords.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.globaloffenderrecords.OcunawrnRepository;
import net.syscon.s4.globaloffenderrecords.OcunawrnService;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

@Service
public class OcunawrnServiceImpl implements OcunawrnService {

	private static Logger logger = LogManager.getLogger(OcunawrnServiceImpl.class.getName());

	@Autowired
	private OcunawrnRepository ocunawrnRepository;

	@Override
	public VOffenderAllSchedulesCommitBean checkNonAssociationConflicts(VOffenderAllSchedulesCommitBean commitBean) {
		String returnMessage = "";
		try {
			if (commitBean != null && commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
				BigDecimal offenderBookId = commitBean.getInsertList().get(0).getOffenderBookId();
				List<OffenderNonAssociations> nonAssociationList = ocunawrnRepository
						.getNonAssociationsOffenders(offenderBookId);
				for (VOffenderAllSchedules vOffSch : commitBean.getInsertList()) {
					if ("Y".equals(vOffSch.getNonAssociationFlag())) {
						if (!nonAssociationList.isEmpty()) {
							boolean isNonAssConflict = false;
							for (OffenderNonAssociations obj : nonAssociationList) {
								int count = 0;
								List<OffenderIndSchedules> offSchedules = ocunawrnRepository
										.checkOffenderScheduleConflicts(obj, vOffSch);
								if (offSchedules != null && offSchedules.size() > 0) {
									isNonAssConflict = true;
									for (OffenderIndSchedules nonAssOffCourse : offSchedules) {
										String message = "";
										count++;
										if (count == 1) {
											message = obj.getLastName() + " " + obj.getFirstName() + ", "
													+ obj.getOffenderIdDisplay() + "\n";
										}
										java.util.Date stDate = nonAssOffCourse.getStartTime();
										java.util.Date enDate = nonAssOffCourse.getEndTime();
										java.util.Date apDate = nonAssOffCourse.getEventDate();
										SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
										String startTime = stDate != null ? sdf.format(stDate) : "";
										String endTime = enDate != null ? sdf.format(enDate) : null;
										SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
										String appDate = sdf1 != null ? sdf1.format(apDate) : "";
										message = message + appDate + ", " + startTime
												+ (endTime != null ? ", " + endTime : "") + "\n";
										returnMessage = returnMessage + message;
									}
									returnMessage = returnMessage + "\n";
								}
							}
							if(!isNonAssConflict) {
								vOffSch.setNonAssociationFlag("N");
							}
						} else {
							vOffSch.setNonAssociationFlag("N");
						}
					}
				}
			}
			
			if (commitBean != null && commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
				BigDecimal offenderBookId = commitBean.getUpdateList().get(0).getOffenderBookId();
				List<OffenderNonAssociations> nonAssociationList = ocunawrnRepository
						.getNonAssociationsOffenders(offenderBookId);
				for (VOffenderAllSchedules vOffSch : commitBean.getUpdateList()) {
					if ("Y".equals(vOffSch.getNonAssociationFlag())) {
						if (!nonAssociationList.isEmpty()) {
							boolean isNonAssConflict = false;
							for (OffenderNonAssociations obj : nonAssociationList) {
								int count = 0;
								List<OffenderIndSchedules> offSchedules = ocunawrnRepository
										.checkOffenderScheduleConflicts(obj, vOffSch);
								if (offSchedules != null && offSchedules.size() > 0) {
									isNonAssConflict = true;
									for (OffenderIndSchedules nonAssOffCourse : offSchedules) {
										String message = "";
										count++;
										if (count == 1) {
											message = obj.getLastName() + " " + obj.getFirstName() + ", "
													+ obj.getOffenderIdDisplay() + "\n";
										}
										java.util.Date stDate = nonAssOffCourse.getStartTime();
										java.util.Date enDate = nonAssOffCourse.getEndTime();
										java.util.Date apDate = nonAssOffCourse.getEventDate();
										SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
										String startTime = stDate != null ? sdf.format(stDate) : "";
										String endTime = enDate != null ? sdf.format(enDate) : null;
										SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
										String appDate = sdf1 != null ? sdf1.format(apDate) : "";
										message = message + appDate + ", " + startTime
												+ (endTime != null ? ", " + endTime : "") + "\n";
										returnMessage = returnMessage + message;
									}
									returnMessage = returnMessage + "\n";
								}
							}
							if(!isNonAssConflict) {
								vOffSch.setNonAssociationFlag("N");
							}
						} else {
							vOffSch.setNonAssociationFlag("N");
						}
					}
				}
			}
			
			if ("".equals(returnMessage)) {
				commitBean.setNonAssConflictMsg(ApplicationConstants.EMPTYDATA);
			} else {
				commitBean.setNonAssConflictMsg(getFinalMessageString(returnMessage));
			}
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " checkNonAssociationConflicts", e);
		}
		return commitBean;
	}
	
	public String getFinalMessageString(final String messageData) {
		return "ocdclogs.nonassociationconflictmsg \n\n"
				+ messageData + " \n ocdclogs.doyouwanttocontinue ";
	}

}
