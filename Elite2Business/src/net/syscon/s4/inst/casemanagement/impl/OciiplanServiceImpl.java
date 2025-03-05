package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.OciiplanRepository;
import net.syscon.s4.inst.casemanagement.OciiplanService;
import net.syscon.s4.inst.casemanagement.beans.CasePlans;
import net.syscon.s4.pkgs.tag_main.TagMainService;

@Service
public class OciiplanServiceImpl extends BaseBusiness implements OciiplanService {

	@Autowired
	private OciiplanRepository ociiplanRepository;
	
	@Autowired	
	private TagMainService tagMainService;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OciiplanServiceImpl class Object
	 */
	public OciiplanServiceImpl() {
		// OciiplanServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CasePlans> casePlansExecuteQuery(CasePlans searchRecord) {
		return ociiplanRepository.casePlansExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCASE_PLANS
	 *
	 * 
	 */
	@Transactional
	public Integer casePlansCommit(CasePlans CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgInstAgyLocRecordGroup(final String caseLoadId) {
		return ociiplanRepository.rgInstAgyLocRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> comInstAgyLocRecordGroup(final String caseLoadId) {
		return ociiplanRepository.comInstAgyLocRecordGroup(caseLoadId);

	}

	@Override
	public List<ReferenceCodes> rgVerifiedFilterRecordGroup() {
		ReferenceCodes returnval = new ReferenceCodes();
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnval.setCode("Both");
		returnval.setDescription(returnval.getCode());
		returnList.add(returnval);
		returnval = new ReferenceCodes();
		returnval.setCode("Yes");
		returnval.setDescription(returnval.getCode());
		returnList.add(returnval);
		returnval = new ReferenceCodes();
		returnval.setCode("No");
		returnval.setDescription(returnval.getCode());
		returnList.add(returnval);
		return returnList;
	}

	@Override
	public Boolean ociiplanTagMainGetOffender(String caseLoadId, String caseLoadType, String OffenderIdDisplay,String userName) {
		Boolean returnBoolValue = false;
		String whereClause1 = tagMainService.defBkgWhere("OCDIPLAN");
		String whereClause = whereClause1.toString().toUpperCase().replaceAll(":GLOBAL.CASELOAD_TYPE", ":caseLoadType").replaceAll(":GLOBAL.CASELOAD_ID", ":caseLoadId").replaceAll(":CASELOAD_ID", ":caseLoadId").replaceAll(":CASELOAD_TYPE", ":caseLoadType");
		List<VHeaderBlock> returnList = ociiplanRepository.ociiplanCaseplansTagMainGetOffender(whereClause, caseLoadId, caseLoadType, OffenderIdDisplay,userName);
		if(returnList.size() > 0) {
			returnBoolValue =  true;
		}else {
			returnBoolValue = false;
		}
		return returnBoolValue;
	}

}