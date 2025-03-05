package net.syscon.s4.inst.workflow.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.maintenance.OcmdeftmRepository;
import net.syscon.s4.inst.workflow.maintenance.OcmdeftmService;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctions;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctionsCommitBean;

/**
 * Class OcmdeftmServiceImpl
 */
@Service
public class OcmdeftmServiceImpl extends BaseBusiness implements OcmdeftmService {

	@Autowired
	private OcmdeftmRepository ocmdeftmRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgyLocTeamFunctions> agyTmFnExecuteQuery(final AgencyLocations searchRecord) {
		List<AgyLocTeamFunctions> returnList;
		String teamIdDesc;
		returnList = ocmdeftmRepository.agyTmFnExecuteQuery(searchRecord);
		for (final AgyLocTeamFunctions object : returnList) {
			teamIdDesc = ocmdeftmRepository.getTeamIdDescription(String.valueOf(object.getTeamId()));
			object.setTeamIdDesc(teamIdDesc);

		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_TM_FN
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer agyTmFnCommit(final AgyLocTeamFunctionsCommitBean commitBean) {
		int liReturn = 0;
		String checkExist;
		long agyLocTeamFunctionId;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final AgyLocTeamFunctions obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				if (obj.getActiveFlag() != null && "Y".equals (obj.getActiveFlag())) {
					checkExist = ocmdeftmRepository.checkAgyLOcTeamExist(obj.getAgyLocId(), obj.getFunctionType());
					if (checkExist != null && "Y".equals (checkExist)) {
						liReturn = 2;
						return liReturn;

					}
				}
				agyLocTeamFunctionId = ocmdeftmRepository.getAgencyTeamID();
				obj.setAgyLocTeamFunctionId(agyLocTeamFunctionId);
			}
			liReturn = ocmdeftmRepository.agyTmFnInsertAgyLocTeamFunctions(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (final AgyLocTeamFunctions obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if (obj.getActiveFlag() != null && "Y".equals (obj.getActiveFlag()) && obj.getSealFlag() != null
						&& "N".equals (obj.getSealFlag()) ) {
					checkExist = ocmdeftmRepository.checkAgyLOcTeamExist(obj.getAgyLocId(), obj.getFunctionType());
					if (checkExist != null && "Y".equals (checkExist)) {
						liReturn = 2;
						return liReturn;
					}
				}
			}
			liReturn = ocmdeftmRepository.agyTmFnUpdateAgyLocTeamFunctions(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String agencyLocationType, final String caseloadId) {
		return ocmdeftmRepository.rgAgyLocRecordGroup(agencyLocationType, caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAgyLocTypeRecordGroup(String userId) {
		return ocmdeftmRepository.rgAgyLocTypeRecordGroup(userId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		return ocmdeftmRepository.rgFunctionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	@Override
	public List<ReferenceCodes> getWrittenFlagCodes() {
		final List<ReferenceCodes> list = new ArrayList<>();
		final ReferenceCodes obj = new ReferenceCodes();
		obj.setCode("Y");
		obj.setDescription("Yes");
		list.add(obj);
		final ReferenceCodes object = new ReferenceCodes();
		object.setCode("N");
		object.setDescription("No");
		list.add(object);
		return list;
	}

}