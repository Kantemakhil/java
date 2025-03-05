package net.syscon.s4.cm.teamsworkflow.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.teamsworkflow.OcdotaskRepository;
import net.syscon.s4.cm.teamsworkflow.OcdotaskService;
import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;

/**
 * Class OcdotaskServiceImpl
 */
@Service
public class OcdotaskServiceImpl extends BaseBusiness implements OcdotaskService {

	@Autowired
	private OcdotaskRepository ocdotaskRepo;
	
	@Autowired
	private EliteDateService eliteDateService;

	/**
	 * Creates new OcdotaskServiceImpl class Object
	 */
	public OcdotaskServiceImpl() {
		// OcdotaskServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<TaskAssignmentHty> tasksExecuteQuery(final TaskAssignmentHty searchRecord) {
		List<TaskAssignmentHty> returnList = ocdotaskRepo.tasksExecuteQuery(searchRecord);
		returnList.forEach(action -> {
			if (action.getAssignmentDate() != null) {
				action.setAssignmentDate(trunc(action.getAssignmentDate()));
			}
		});
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgCompleteRsnRecordGroup() {
		return ocdotaskRepo.rgCompleteRsnRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<StaffMembers> rgStaffRecordGroup(final String teamId) {
		return ocdotaskRepo.rgStaffRecordGroup(teamId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<StaffMembers> rgTeamRecordGroup() {
		return ocdotaskRepo.rgTeamRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgTaskSubTypeRecordGroup() {
		return ocdotaskRepo.rgTaskSubTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgTaskTypeRecordGroup() {
		return ocdotaskRepo.rgTaskTypeRecordGroup();

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

}