package net.syscon.s4.triggers.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Repository;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Service;
/*
==============================================================================================================================
   Below comments are copied from OFFENDER_IND_SCHEDULES_T4 Trigger
==============================================================================================================================
  MODIFICATION HISTORY
   Person       Date      version      Comments
  Rajshree   25/01/2006  2.2          Changed the record date to date_creation,Added time creation.Also added code to
                                      enter 'BLANK' when null value is updated or any value is updated to null.
  Rajshree   06/01/2006  2.1          Fixed issue of closing the cursor.
  Rajshree   04/01/2006  2.0          This trigger is will create a new case note entry that includes the old details and
                                      the update done to the feild for records that triggered an autometic case note.
*/
@Service
public class OffenderIndSchedulesT4ServiceImpl implements OffenderIndSchedulesT4Service {
	Logger logger = LogManager.getLogger(OffenderIndSchedulesT4ServiceImpl.class);
	@Autowired
	OffenderIndSchedulesT4Repository offenderIndSchedulesT4Repository;

	@Override
	public String offenderIndSchedulesT4(final OffenderCaseNotes offenderCaseNotes,
			final OffenderIndSchedules offenderIndSchedules, final String source) {
		final List<OffenderCaseNotes> offeCaseNotesList = new ArrayList<OffenderCaseNotes>();
		OffenderCaseNotes targetObj = new OffenderCaseNotes();
		final SimpleDateFormat sdf = new SimpleDateFormat("HH24:mm");

		String result = "0";
		StringBuffer pNewText = new StringBuffer();
		// Getting Old Records From OFFENDER_IND_SCHEDULES Table
		final OffenderIndSchedules offenderIndSchedulesOld = offenderIndSchedulesT4Repository
				.getOffenderIndSchedules(offenderIndSchedules.getEventId());
		String oldEndTime =null;
		if(offenderIndSchedulesOld!=null) {
		  oldEndTime = Optional.ofNullable(offenderIndSchedulesOld.getEndTime()).isPresent()
				? sdf.format(offenderIndSchedulesOld.getEndTime())
				: sdf.format(new Date());
		}
		final String newEndTime = Optional.ofNullable(offenderIndSchedules.getEndTime()).isPresent()
				? sdf.format(offenderIndSchedules.getEndTime())
				: sdf.format(new Date());
				// Getting Records from offender_case_notes table
		final OffenderCaseNotes getCasenoteDetailsC = offenderIndSchedulesT4Repository
				.getCasenoteDetailsC(offenderIndSchedules.getEventId());
		if(offenderIndSchedulesOld!=null) {
		if (offenderIndSchedulesOld.getEventDate().compareTo(offenderIndSchedules.getEventDate()) != 0
				|| offenderIndSchedulesOld.getStartTime().compareTo(offenderIndSchedules.getStartTime()) != 0
				|| !oldEndTime.equals(newEndTime)
				|| !(offenderIndSchedulesOld.getAgyLocId()!=null && offenderIndSchedulesOld.getAgyLocId().equals(offenderIndSchedules.getAgyLocId()!=null ?offenderIndSchedules.getAgyLocId():null))
				|| (offenderIndSchedulesOld.getInChargeStaffId()!=null && offenderIndSchedulesOld.getInChargeStaffId() != offenderIndSchedules.getInChargeStaffId())
				|| !(offenderIndSchedulesOld.getEventOutcome()!=null && offenderIndSchedulesOld.getEventOutcome().equals(offenderIndSchedules.getEventOutcome()))
				||(offenderIndSchedulesOld.getCommentText()!=null && !offenderIndSchedulesOld.getCommentText().equals(offenderIndSchedules.getCommentText()))) {
			if (Optional.ofNullable(getCasenoteDetailsC).isPresent()
					&& Optional.ofNullable(getCasenoteDetailsC !=null?getCasenoteDetailsC.getEventId():null).isPresent()) {

				if (offenderIndSchedulesOld.getEventDate().compareTo(offenderIndSchedules.getEventDate()) != 0) {
					if (Optional.ofNullable(pNewText).isPresent()) {
						pNewText = pNewText.append(pNewText).append(offenderIndSchedulesOld.getEventDate())
								.append(" has been updated to ").append(offenderIndSchedules.getEventDate());
					} else {
						pNewText = pNewText.append(offenderIndSchedulesOld.getEventDate())
								.append(" has been updated to ").append(offenderIndSchedules.getEventDate());
					}

				}
			}
				if (offenderIndSchedulesOld.getStartTime().compareTo(offenderIndSchedules.getStartTime()) != 0) {
					final String oldStartTime = sdf.format(offenderIndSchedulesOld.getStartTime());
					final String newStartTime = sdf.format(offenderIndSchedules.getStartTime());
					if (Optional.ofNullable(pNewText).isPresent()) {
						pNewText = pNewText.append(pNewText).append(oldStartTime).append(" has been updated to ")
								.append(newStartTime);
					} else {
						pNewText = pNewText.append(oldStartTime).append(" has been updated to ").append(newStartTime);
					}

				}
				if (!oldEndTime.equals(newEndTime)) {
					final String lvOldTime = Optional.ofNullable(offenderIndSchedulesOld.getEndTime()).isPresent()
							? sdf.format(offenderIndSchedulesOld.getEndTime())
							: "BLANK";
					final String lvNewTime = Optional.ofNullable(offenderIndSchedules.getEndTime()).isPresent()
							? sdf.format(offenderIndSchedules.getEndTime())
							: "BLANK";
					;
					if (Optional.ofNullable(pNewText).isPresent()) {
						pNewText = pNewText.append(pNewText).append(lvOldTime).append(" has been updated to ")
								.append(lvNewTime);
					} else {
						pNewText = pNewText.append(lvOldTime).append(" has been updated to ").append(lvNewTime);
					}
				}
				if (offenderIndSchedulesOld.getAgyLocId()!= null) {
				if (!offenderIndSchedulesOld.getAgyLocId().equals(offenderIndSchedules.getAgyLocId())) {
					if (Optional.ofNullable(pNewText).isPresent()) {
						pNewText = pNewText.append(pNewText).append(offenderIndSchedulesOld.getAgyLocId())
								.append(" has been updated to ").append(offenderIndSchedules.getAgyLocId());
					} else {
						pNewText = pNewText.append(offenderIndSchedulesOld.getAgyLocId())
								.append(" has been updated to ").append(offenderIndSchedules.getAgyLocId());
					}
				}}
				if (!equals(offenderIndSchedulesOld.getInChargeStaffId()!=null?offenderIndSchedulesOld.getInChargeStaffId().toString():null,offenderIndSchedules.getInChargeStaffId()!=null?offenderIndSchedules.getInChargeStaffId().toString():null)) {
					final String lvOldStaff = Optional.ofNullable(offenderIndSchedulesOld.getInChargeStaffId())
							.isPresent() ? String.valueOf(offenderIndSchedulesOld.getInChargeStaffId()) : "BLANK";
					;
					final String lvNewStaff = Optional.ofNullable(offenderIndSchedules.getInChargeStaffId()).isPresent()
							? String.valueOf(offenderIndSchedules.getInChargeStaffId())
							: "BLANK";
					;
					;
					if (Optional.ofNullable(pNewText).isPresent()) {
						pNewText = pNewText.append(pNewText).append(lvOldStaff).append(" has been updated to ")
								.append(lvNewStaff);
					} else {
						pNewText = pNewText.append(lvOldStaff).append(" has been updated to ").append(lvNewStaff);
					}
				}
				if (!equals(offenderIndSchedulesOld.getEventOutcome(),offenderIndSchedules.getEventOutcome())) {
					final String lvOldOutcome = Optional.ofNullable(offenderIndSchedulesOld.getEventOutcome())
							.isPresent() ? offenderIndSchedulesOld.getEventOutcome() : "BLANK";
					final String lvNewOutcome = Optional.ofNullable(offenderIndSchedules.getEventOutcome()).isPresent()
							? offenderIndSchedules.getEventOutcome()
							: "BLANK";
					if (Optional.ofNullable(pNewText).isPresent()) {
						pNewText = pNewText.append(pNewText).append(lvOldOutcome).append(" has been updated to ")
								.append(lvNewOutcome);
					} else {
						pNewText = pNewText.append(lvOldOutcome).append(" has been updated to ").append(lvNewOutcome);
					}

				}
				if (!equals(offenderIndSchedulesOld.getCommentText(),offenderIndSchedules.getCommentText())) {
					final String lvNewText = Optional.ofNullable(offenderIndSchedules.getCommentText()).isPresent()
							? offenderIndSchedules.getCommentText()
							: "BLANK";
					final String lvOldText = Optional.ofNullable(offenderIndSchedulesOld.getCommentText()).isPresent()
							? offenderIndSchedulesOld.getCommentText()
							: "BLANK";
					if (Optional.ofNullable(pNewText).isPresent()) {
						pNewText = pNewText.append(pNewText).append(lvOldText).append(" has been updated to ")
								.append(lvNewText);
					} else {
						pNewText = pNewText.append(lvOldText).append(" has been updated to ").append(lvNewText);
					}
				}
				pNewText = new StringBuffer(getCasenoteDetailsC!=null?getCasenoteDetailsC.getCaseNoteText():null + " " + pNewText);
				if (pNewText != null && pNewText.length() > 4000) {
					return "The updated case note text is greater than the allowed limit of 4000 characters.";
				}
				targetObj = new OffenderCaseNotes();
				if(getCasenoteDetailsC!=null && source == null) {
				BeanUtils.copyProperties(getCasenoteDetailsC, targetObj);
				targetObj.setCaseNoteId(offenderIndSchedulesT4Repository.caseNoteIdNextval());
				targetObj.setCaseNoteText(pNewText.toString());
				targetObj.setCreateUserId(offenderIndSchedules.getCreateUserId());
				offeCaseNotesList.add(targetObj);

				try {
					// Inserting data in offender_case_notes Table
					final Integer resultVal = offenderIndSchedulesT4Repository.insert(offeCaseNotesList);
					result = resultVal.toString();
				} catch (final Exception e) {
					logger.error("", e);
					result = "0";
				}
			}
		}

		}
		return result;
	}
	 private Boolean equals(String s1,String s2) {
			if(s1 == null && s2==null)
				return true;
			else if ((s1 != null && s2 == null) || (s1 == null && s2 != null))
				return false;
			else
				return s1.equals(s2);
				
		}

}
