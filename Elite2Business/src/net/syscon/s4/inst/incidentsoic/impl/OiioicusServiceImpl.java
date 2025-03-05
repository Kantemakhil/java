package net.syscon.s4.inst.incidentsoic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.im.incidentsoic.beans.VOffenderOicSanctions;
import net.syscon.s4.im.incidentsoic.beans.VOicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.VOicHearings;
import net.syscon.s4.im.incidentsoic.beans.VOicIncidents;
import net.syscon.s4.inst.incidentsoic.OiioicusRepository;
import net.syscon.s4.inst.incidentsoic.OiioicusService;

/**
 * class OiioicusServiceImpl
 */
@Service
public class OiioicusServiceImpl extends BaseBusiness implements OiioicusService {

	@Autowired
	private OiioicusRepository oiioicusDao;

	/**
	 * Creates new OiioicusServiceImpl class Object
	 */
	public OiioicusServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<String> offBkgOnCheckDeleteMastervOicInciCur(final VOicIncidents paramBean) {
		return oiioicusDao.offBkgOnCheckDeleteMastervOicInciCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<String> vOicInciOnCheckDeleteMastervOicHearCur(final VOicHearings paramBean) {
		return oiioicusDao.vOicInciOnCheckDeleteMastervOicHearCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<String> vOicHearOnCheckDeleteMastervOicHearResCur(final VOicHearingResults paramBean) {
		return oiioicusDao.vOicHearOnCheckDeleteMastervOicHearResCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<String> vOicHearResOnCheckDeleteMastervOffOicSanctCur(final VOffenderOicSanctions paramBean) {
		return oiioicusDao.vOicHearResOnCheckDeleteMastervOffOicSanctCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public SystemProfiles getProfileValuevsProfvalCur(final SystemProfiles paramBean) {
		return oiioicusDao.getProfileValuevsProfvalCur(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOicIncidents> vOicInciSearchVOicIncidents(final VOicIncidents searchRecord) {
		return oiioicusDao.vOicInciSearchVOicIncidents(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOicHearings> vOicHearSearchVOicHearings(final VOicHearings searchRecord) {
		return oiioicusDao.vOicHearSearchVOicHearings(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOicHearingResults> vOicHearResSearchVOicHearingResults(final VOicHearingResults searchRecord) {
		return oiioicusDao.vOicHearResSearchVOicHearingResults(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderOicSanctions> vOffOicSanctSearchVOffenderOicSanctions(
			final VOffenderOicSanctions searchRecord) {
		return oiioicusDao.vOffOicSanctSearchVOffenderOicSanctions(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgOicHearingTypeRecordGroup() {
		return oiioicusDao.rgOicHearingTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgIncidentTypeRecordGroup() {
		return oiioicusDao.rgIncidentTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgOffenceTypeRecordGroup(final String date) {
		return oiioicusDao.rgOffenceTypeRecordGroup(date);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSanctionCodeRecordGroup() {
		return oiioicusDao.rgSanctionCodeRecordGroup();

	}

	@Override
	public List<String> getHearingStaffNameList() {
		return oiioicusDao.getHearingStaffNameList();
	}

	@Override
	public List<String> getHearingResultsOicOffenceDes() {
		return oiioicusDao.getHearingResultsOicOffenceDes();
	}

	@Override
	public List<String> getHearingResultsType() {
		return oiioicusDao.getHearingResultsType();
	}

	@Override
	public List<String> getDiscOicSanctionDes() {
		return oiioicusDao.getDiscOicSanctionDes();
	}

	@Override
	public List<String> getDiscStatusDes() {
		return oiioicusDao.getDiscStatusDes();
	}

}
