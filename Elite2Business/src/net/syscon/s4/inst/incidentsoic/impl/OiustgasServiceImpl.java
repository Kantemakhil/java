package net.syscon.s4.inst.incidentsoic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyIncidentAssoTostg;
import net.syscon.s4.common.beans.AgencyIncidentAssoTostgCommitBean;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.incidentsoic.OiustgasRepository;
import net.syscon.s4.inst.incidentsoic.OiustgasService;

@Service
public class OiustgasServiceImpl extends BaseBusiness implements OiustgasService {

	@Autowired
	private OiustgasRepository oiustgasRepository;

	/**
	 * Creates new OiustgasServiceImpl class Object
	 */
	public OiustgasServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AgencyIncidentAssoTostg> agencyIncidentAssoTostgPreInsert(final AgencyIncidentAssoTostg paramBean) {

		return (List<AgencyIncidentAssoTostg>) oiustgasRepository.agencyIncidentAssoTostgPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SystemProfiles populateRg() {

		return oiustgasRepository.populateRg();

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyIncidentAssoTostg> agencyIncidentAssoTostgExecuteQuery(
			final AgencyIncidentAssoTostg searchRecord) {
		return oiustgasRepository.agencyIncidentAssoTostgExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_INCIDENT_ASSO_TOSTG
	 *
	 * @throws SQLException
	 */

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroups> rgStgRecordGroup() {
		return oiustgasRepository.rgStgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroups> rgStgORecordGroup() {
		return oiustgasRepository.rgStgORecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroups> rgStgLRecordGroup() {
		return oiustgasRepository.rgStgLRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroups> stgGrpRecordGroup() {
		List<SecurityThreatGroups> returnList = new ArrayList<>();
		final SystemProfiles returnObj = populateRg();
		if ("1".equals(returnObj.getProfileValue())) {
			returnList = oiustgasRepository.rgStgORecordGroup();
		} else if ("2".equals(returnObj.getProfileValue())) {
			returnList = oiustgasRepository.rgStgRecordGroup();
		} else if ("3".equals(returnObj.getProfileValue())) {
			returnList = oiustgasRepository.rgStgLRecordGroup();
		}
		return returnList;

	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @return Integer
	 * @Param lstAgencyIncidentAssoTostg
	 */
	@Transactional
	public Integer agencyincidentassotostgInsertAgencyIncidentAssoTostg(final List<AgencyIncidentAssoTostg> object) {
		final List<AgencyIncidentAssoTostg> resultList = new ArrayList<>();
		Integer tranResult = 0;
		for (final AgencyIncidentAssoTostg result : object) {
			result.setSeqNo(Integer.valueOf(oiustgasRepository.agencyIncidentAssoTostgPreInsertInc(result).toString()));
			resultList.add(result);
			tranResult = oiustgasRepository.agencyincidentassotostgInsertAgencyIncidentAssoTostg(resultList);
			resultList.clear();
		}
		return tranResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @return Integer
	 * @Param lstAgencyIncidentAssoTostg
	 */
	@Transactional
	public Integer agencyIncidentAssoTostgUpdateAgencyIncidentAssoTostg(
			final List<AgencyIncidentAssoTostg> lstAgencyIncidentAssoTostg) {
		return oiustgasRepository.agencyIncidentAssoTostgUpdateAgencyIncidentAssoTostg(lstAgencyIncidentAssoTostg);
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @return Integer
	 * @Param lstAgencyIncidentAssoTostg
	 */
	@Transactional
	public Integer agencyIncidentAssoTostgDeleteAgencyIncidentAssoTostg(
			final List<AgencyIncidentAssoTostg> lstAgencyIncidentAssoTostg) {
		return oiustgasRepository.agencyIncidentAssoTostgDeleteAgencyIncidentAssoTostg(lstAgencyIncidentAssoTostg);
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@Transactional
	public Integer agencyIncidentAssoTostgCommit(final AgencyIncidentAssoTostgCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = agencyincidentassotostgInsertAgencyIncidentAssoTostg(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> {
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = agencyIncidentAssoTostgUpdateAgencyIncidentAssoTostg(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = agencyIncidentAssoTostgDeleteAgencyIncidentAssoTostg(commitBean.getDeleteList());
		}
		return liReturn;
	}
}