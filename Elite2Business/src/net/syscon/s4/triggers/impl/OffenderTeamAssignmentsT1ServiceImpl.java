package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.triggers.OffenderTeamAssignmentsT1Repository;
import net.syscon.s4.triggers.OffenderTeamAssignmentsT1Service;

@Service
public class OffenderTeamAssignmentsT1ServiceImpl implements OffenderTeamAssignmentsT1Service {

	@Autowired
	private OffenderTeamAssignmentsT1Repository offenderTeamAssignmentsT1Repository;

	@Override
	public void offenderTeamAssignmentsT1Trigger(final OffenderTeamAssignments oldRec) {
		// Inserting Records into OFFENDER_TEAM_ASSIGN_HTY Table
		offenderTeamAssignmentsT1Repository.insertIntoOffenderTeamAssignHty(oldRec);
	}

}
