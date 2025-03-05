package net.syscon.s4.inst.workflow.managingteams.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.workflow.managingteams.OcuhvteaRepository;
import net.syscon.s4.inst.workflow.managingteams.OcuhvteaService;
import net.syscon.s4.inst.workflow.managingteams.beans.VOffenderTeamAssignHty;

/**
 * Class OcuhvteaServiceImpl
 */
@Service
public class OcuhvteaServiceImpl extends BaseBusiness implements OcuhvteaService {

	@Autowired
	private OcuhvteaRepository ocuhvteaRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VOffenderTeamAssignHty> offVteamHtyExecuteQuery(final VOffenderTeamAssignHty searchRecord) {
		return ocuhvteaRepository.offVteamHtyExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		return ocuhvteaRepository.rgFunctionRecordGroup();

	}

	@Override
	public List<VOffenderTeamAssignHty> offBkgOnCheckDeleteMaster(final VOffenderTeamAssignHty paramBean) {
		return ocuhvteaRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

}