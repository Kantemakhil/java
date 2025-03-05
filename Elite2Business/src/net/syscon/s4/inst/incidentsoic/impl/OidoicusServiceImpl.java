package net.syscon.s4.inst.incidentsoic.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;
import net.syscon.s4.inst.incidentsoic.OidoicusRepository;
import net.syscon.s4.inst.incidentsoic.OidoicusService;

@Service
public class OidoicusServiceImpl extends BaseBusiness implements OidoicusService {

	@Autowired
	private OidoicusRepository oidoicusRepository;
	
	/**
	 * Creates new OidoicusServiceImpl class Object
	 */
	public OidoicusServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param offenderbookid
	 * @return List<Object>
	 */
	public List<Object> offBkgOnCheckDeleteMastervOicInciCur(final String offenderbookid)  {
		return oidoicusRepository.offBkgOnCheckDeleteMastervOicInciCur(offenderbookid);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 * @return List<VOicIncidents>
	 */
	public List<VOicIncidents> vOicInciSearchVOicIncidents(final VOicIncidents searchRecord)  {
		List<VOicIncidents> returnList = new ArrayList<>();
		returnList = oidoicusRepository.vOicInciSearchVOicIncidents(searchRecord);
		for(final VOicIncidents obj:returnList){
			final String description = oidoicusRepository.getDescriptionOfStaffId(Integer.parseInt(obj.getReportedStaffId().toString()));
			obj.setStaffIdDescription(description);
		}
		
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgIncTypeRecordGroup()  {
		return oidoicusRepository.rgIncTypeRecordGroup();
	}

	@Override
	public List<String> findLocationList() {
		return oidoicusRepository.findLocationList();
	}

	@Override
	public List<IncidentStaffReport> staffReportsData(final VOicIncidents objSearchDao) {
					return oidoicusRepository.staffReportsData( objSearchDao);
		 		}
}