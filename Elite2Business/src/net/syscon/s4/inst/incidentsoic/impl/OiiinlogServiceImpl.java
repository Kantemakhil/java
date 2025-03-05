package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VAgencyIncidents;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.inst.incidentsoic.OidincdeRepository;
import net.syscon.s4.inst.incidentsoic.OiiinlogRepository;
import net.syscon.s4.inst.incidentsoic.OiiinlogService;

/**
 * Class OiiinlogServiceImpl
 */
@Service
public class OiiinlogServiceImpl extends BaseBusiness implements OiiinlogService {

	/**
	 * Creates new OiiinlogBusiness class Object
	 */
	public OiiinlogServiceImpl() {
		// OiiinlogBusiness
	}

	@Autowired
	private OiiinlogRepository oiiinlogDao;
	
	@Autowired
	private OidincdeRepository oidincdeRepository;

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 *            VAgencyIncidents
	 * @return List<VAgencyIncidents>
	 */
	public List<VAgencyIncidents> vAgyIncExecuteQuery(VAgencyIncidents searchRecord) {
		AgencyIncidents searchBean = new AgencyIncidents();
		List<AgencyInternalLocations> listlocations = new ArrayList<AgencyInternalLocations>();
		 List<AgencyIncidents> returnList = new ArrayList<AgencyIncidents>();
		List<VAgencyIncidents> listagy= oiiinlogDao.vAgyIncExecuteQuery(searchRecord);
		for(final VAgencyIncidents vAgencyIncidents:listagy){
			vAgencyIncidents.setReportid(vAgencyIncidents.getReportedStaffId().toString());
		}
		 returnList = oiiinlogDao.agencyIncidentsExecuteQuery(searchBean);
		for(final VAgencyIncidents vAgencyIncidents: listagy){	
			
			if(vAgencyIncidents.getAgyLocId() !=null && "INST".equals(searchRecord.getCaseloadType())){
				listlocations = oiiinlogDao.rgLevelInternalLocationIdsRecordGroup(vAgencyIncidents.getAgyLocId());
				List<AgencyInternalLocations> filterdList = listlocations.stream().filter(location-> {
						return location.getInternalLocationId().equals(vAgencyIncidents.getInternalLocationId());
					}).collect(Collectors.toList());//It will always give one record.
				vAgencyIncidents.setCode(filterdList!=null && filterdList.size()>0?filterdList.get(0).getInternalLocationCode():null);
				
			}
			
			for(final AgencyIncidents agencyIncidents: returnList) {
				if(vAgencyIncidents.getAgencyIncidentId().equals(agencyIncidents.getAgencyIncidentId())){
						vAgencyIncidents.setLockFlag(agencyIncidents.getLockFlag());
						if(vAgencyIncidents.getLockFlag().equals("Y")){
							vAgencyIncidents.setFlag(true);
							vAgencyIncidents.setAppendDetailesflag(true);
						}else{
							vAgencyIncidents.setFlag(false);
							vAgencyIncidents.setAppendDetailesflag(false);
						}
						vAgencyIncidents.setIncidentDetails(agencyIncidents.getIncidentDetails());
					
					}
				
				
			}
			
			if("COMM".equals(searchRecord.getCaseloadType())){
				vAgencyIncidents.setIntLocDescription(vAgencyIncidents.getDescription());		
				}
			
		}
		return listagy;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOccurTypeRecordGroup(){
		return oiiinlogDao.rgOccurTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @param caseloadId
	 *            CaseloadAgencyLocations
	 * @param caseLoadType 
	 * @return List<CaseloadAgencyLocations>
	 */
	public List<CaseloadAgencyLocations> rgAgyLocRecordGroup(String caseloadId, String caseLoadType) {
		List<CaseloadAgencyLocations> resultList = new ArrayList<>();
		if("INST".equals(caseLoadType)){
		resultList = oiiinlogDao.rgAgyLocRecordGroup(caseloadId);
		for(CaseloadAgencyLocations result : resultList){
			result.setCode(result.getAgyLocId());
		}
		}else {
			resultList = oidincdeRepository.getCommunityOfficesData(caseloadId);
			for(final CaseloadAgencyLocations loadAgency: resultList){
				loadAgency.setCode(loadAgency.getAgyLocId());
			}
		}
		return resultList;	
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @param searchBean
	 *            AgencyInternalLocations
	 * @return List<AgencyInternalLocations>
	 */
	public List<AgencyInternalLocations> rgLevelLocRecordGroup(String agyLocId) {
		AgencyInternalLocations searchBean = new AgencyInternalLocations();
		searchBean.setAgyLocId(agyLocId);
		List<AgencyInternalLocations> resultList = new ArrayList<>();
		resultList = oiiinlogDao.rgLevelLocRecordGroup(searchBean);
		for(AgencyInternalLocations result : resultList){
			result.setCode(result.getInternalLocationCode());
		}
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @param String
	 *            caseloadId
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> rgStaffRecordGroup(String caseloadId) {
		List<StaffMembers> resultList = new ArrayList<StaffMembers>();
		resultList = oiiinlogDao.rgStaffRecordGroup(caseloadId);
		for(StaffMembers result : resultList) {
			result.setDescription(result.getStaffName());
			result.setCode(result.getStaffId());
		}
		return resultList;

	}
	
	@Override
	public List<String> findIncidentTypeDescList() {
		return oiiinlogDao.findIncidentTypeDescList();
	}

	@Override
	public List<String> findIntLocationsList() {
		return oiiinlogDao.findIntLocationsList();
	}
	
	/**
	 * This method is used to call module OIDINCDE
	 *
	 * @
	 */
	public List<String> agencyIncidentsCallModuleOidincde() {
		return oiiinlogDao.agencyIncidentsCallModuleOidincde();
	}
	
	/**
	 * This method is used to call module OIUINCRP
	 *
	 * @
	 */
	public List<String> agencyIncidentsCallModuleOiuincrp() {
		return oiiinlogDao.agencyIncidentsCallModuleOiuincrp();
	}
}