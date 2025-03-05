package net.syscon.s4.inst.incidentsoic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.inst.incidentsoic.OiuincrpRepository;
import net.syscon.s4.inst.incidentsoic.OiuincrpService;

@Service
public class OiuincrpServiceImpl extends BaseBusiness implements OiuincrpService {

	@Autowired
	private OiuincrpRepository oiuincrpDao;

	/**
	 * Creates new OiuincrpBusiness class Object
	 */
	public OiuincrpServiceImpl() {
		
	}
 
	/**
	 * @return List<AgencyIncidentRepairs>
	 * @param AgencyIncidentRepairs
	 *            searchRecord
	 */
	
	public List<AgencyIncidentRepairs> agyIncExecuteQuery(final AgencyIncidentRepairs searchRecord) {
		return oiuincrpDao.agyIncExecuteQuery(searchRecord);

	}


}