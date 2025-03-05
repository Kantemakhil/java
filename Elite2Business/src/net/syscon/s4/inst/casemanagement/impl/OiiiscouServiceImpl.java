package net.syscon.s4.inst.casemanagement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.OiiiscouRepository;
import net.syscon.s4.inst.casemanagement.OiiiscouService;
import net.syscon.s4.inst.casemanagement.beans.VPrisonStatusCount;
import net.syscon.s4.inst.casemanagement.beans.VPrisonTotal;

@Service
public class OiiiscouServiceImpl extends BaseBusiness implements OiiiscouService {

	@Autowired
	private OiiiscouRepository oiiiscouRepository;

	/**
	 * Creates new OiiiscouServiceImpl class Object
	 */
	public OiiiscouServiceImpl() {

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public AgencyLocations CgfkchkAgyLocAgyLocIdAg(AgencyLocations paramBean) {
		AgencyLocations agencyLocationsList = oiiiscouRepository.cgfkchkAgyLocAgyLocIdAg(paramBean);
		return agencyLocationsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param params
	 *
	 */
	public List<ReferenceCodes> CgfdgetVPrisnCtDrvImpris(ReferenceCodes paramBean) {
		List<ReferenceCodes> referenceCodes = oiiiscouRepository.cgfdgetVPrisnCtDrvImpris(paramBean);
		final ReferenceCodes staticReference = new ReferenceCodes();
		staticReference.setCode("NO STATUS RECORDED");
		staticReference.setDescription("NO STATUS RECORDED");
		referenceCodes.add(staticReference);
		final ReferenceCodes totStaticReference = new ReferenceCodes();
		totStaticReference.setCode("TOT");
		totStaticReference.setDescription("Totals");
		referenceCodes.add(totStaticReference);
		return referenceCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param params
	 */
	public SystemProfiles GetProfileValue(SystemProfiles paramBean) {
		SystemProfiles systemProfiles = oiiiscouRepository.getProfileValue(paramBean);
		return systemProfiles;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VPrisonStatusCount> vPrisnCtExecuteQuery(final String agyLocId) {
		List<VPrisonStatusCount> resultList = oiiiscouRepository.vPrisnCtExecuteQuery(agyLocId);
		resultList.forEach(result -> {
			if (result.getImprisonmentStatus() == null || result.getImprisonmentStatus().equals("null")) {
				result.setImprisonmentStatus("NO STATUS RECORDED");
			}
		});
		return resultList;

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstV_PRISN_CT
	 */
	@Transactional
	public Integer vPrisnCtCommit(VPrisonStatusCount CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 */
	public List<VPrisonTotal> vPrisnTotExecuteQuery(final String agyLocId) {
		return oiiiscouRepository.vPrisnTotExecuteQuery(agyLocId);

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstV_PRISN_TOT
	 */
	@Transactional
	public Integer vPrisnTotCommit(VPrisonTotal CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<AgencyLocations> cgfkAgyLocAgyLocIdRecordGroup(final String caseloadId) {
		return oiiiscouRepository.cgfkAgyLocAgyLocIdRecordGroup(caseloadId);

	}
}