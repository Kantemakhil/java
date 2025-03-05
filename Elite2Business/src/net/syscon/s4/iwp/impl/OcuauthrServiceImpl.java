package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.iwp.OcuauthrRepository;
import net.syscon.s4.iwp.OcuauthrService;

/**
 * Class OcuauthrServiceImpl
 */
@Service
public class OcuauthrServiceImpl extends BaseBusiness implements OcuauthrService {

	@Autowired
	private OcuauthrRepository ocuauthrRepository;

	public List<TaskAssignmentHty> tskAssHtyExecuteQuery(final TaskAssignmentHty tskAsshty) {
		List<TaskAssignmentHty> listTaskAsstHty = null;
		listTaskAsstHty = ocuauthrRepository.tskAssHtyExecuteQuery(tskAsshty);
		listTaskAsstHty.forEach(bean -> {

			if (bean.getTeamId() != null) {
				final Teams teams = ocuauthrRepository.teamCodeAndDescriptionPostquery(bean.getTeamId());
				if (teams != null) {
					bean.setCode(teams.getTeamCode());
					bean.setDescription(teams.getDescription());
				}
			}

			if (bean.getStaffId() != null) {
				final StaffMembers staffMemebers = ocuauthrRepository
						.lastNameAndfirstNamePostquery(bean.getStaffId().longValue());
				if (staffMemebers != null) {
					bean.setLastName(staffMemebers.getLastName());
					bean.setFirstName(staffMemebers.getFirstName());
				}
			}

		});
		return listTaskAsstHty;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStaffNameRecordGroup(final String teamId) {
		return ocuauthrRepository.rgStaffNameRecordGroup(teamId);

	}

}