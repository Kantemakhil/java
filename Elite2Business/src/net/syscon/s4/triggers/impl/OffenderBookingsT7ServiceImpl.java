package net.syscon.s4.triggers.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctions;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.triggers.OffenderBookingsT7Repository;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffenderTeamAssignmentsT1Service;

@Service
public class OffenderBookingsT7ServiceImpl implements OffenderBookingsT7Service {
	@Autowired
	private OffenderBookingsT7Repository offenderBookingsT7Repository;
	
	@Autowired
	private OffenderTeamAssignmentsT1Service offenderTeamAssignmentsT1Service;
	

	@Override
	public Integer offenderBookingsT7Trigger(final OffenderBookings offenderBookings) {
		final OffenderTeamAssignments inputObj = new OffenderTeamAssignments();
		final OffenderBookings oldObject = offenderBookingsT7Repository
				.getOffenderBookings(offenderBookings.getOffenderBookId());
		if (Optional.ofNullable(offenderBookings.getAgyLocId()).isPresent()) {
			final List<AgyLocTeamFunctions> agyLocTeamFunctionsList = offenderBookingsT7Repository
					.getAgyLocTeamFunsCur(offenderBookings.getAgyLocId());
			if (!agyLocTeamFunctionsList.isEmpty()) {
				for (final AgyLocTeamFunctions agyLocTeamFunctions : agyLocTeamFunctionsList) {
					final Long isOffTeamAssExiVal = offenderBookingsT7Repository.isOffTeamAssignExists(
							offenderBookings.getOffenderBookId(), agyLocTeamFunctions.getFunctionType());
					if (isOffTeamAssExiVal > 0) {
						if ("Y".equals(agyLocTeamFunctions.getOverwrittenFlag())) {
							dataMapping(offenderBookings, inputObj, agyLocTeamFunctions);
							offenderBookingsT7Repository.offenderTeamAssignmentsDelete(inputObj);
							offenderBookingsT7Repository.insOffenderTeamAssignments(inputObj);
						} else {
							dataMapping(offenderBookings, inputObj, agyLocTeamFunctions);
							offenderBookingsT7Repository.insOffenderTeamAssignments(inputObj);
						}
					}
				}
			}
		}
		if (Optional.ofNullable(offenderBookings.getAgyLocId()).isPresent()
				&& !offenderBookings.getAgyLocId().equals(oldObject.getAgyLocId())) {
			final List<AgyLocTeamFunctions> agyLocTeamFunctionsList = offenderBookingsT7Repository
					.getAgyLocTeamFunsCur(oldObject.getAgyLocId());
			if (!agyLocTeamFunctionsList.isEmpty()) {
				for (final AgyLocTeamFunctions agyLocTeamFunctions : agyLocTeamFunctionsList) {
					final Long isOffTeamAssExiVal = offenderBookingsT7Repository.isOffTeamAssignExists(
							oldObject.getOffenderBookId(), agyLocTeamFunctions.getFunctionType());
					if (isOffTeamAssExiVal > 0) {
						if ("Y".equals(agyLocTeamFunctions.getOverwrittenFlag())) {
							dataMapping(oldObject, inputObj, agyLocTeamFunctions);
							offenderBookingsT7Repository.offenderTeamAssignmentsDelete(inputObj);
							offenderTeamAssignmentsT1Service.offenderTeamAssignmentsT1Trigger(inputObj);
							offenderBookingsT7Repository.insOffenderTeamAssignments(inputObj);
						} else {
							dataMapping(oldObject, inputObj, agyLocTeamFunctions);
							offenderBookingsT7Repository.insOffenderTeamAssignments(inputObj);
						}
					}
				}
			}
		}
		return null;
	}

	private void dataMapping(final OffenderBookings offenderBookings, final OffenderTeamAssignments inputObj,
			final AgyLocTeamFunctions agyLocTeamFunctions) {
		inputObj.setOffenderBookId(offenderBookings.getOffenderBookId());
		inputObj.setFunctionType(agyLocTeamFunctions.getFunctionType());
		inputObj.setTeamId(agyLocTeamFunctions.getTeamId());
		inputObj.setAssignmentDate(new Date());
		inputObj.setCreateDatetime(new Date());
		inputObj.setCreateUserId(offenderBookings.getCreateUserId());
		inputObj.setModifyDatetime(new Date());
		inputObj.setModifyUserId(offenderBookings.getModifyUserId());
	}

}
