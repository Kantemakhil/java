package net.syscon.s4.inst.programswithoutschedules.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.programswithoutschedules.OiuvlcteRepository;
import net.syscon.s4.inst.programswithoutschedules.OiuvlcteService;

@Service
public class OiuvlcteServiceImpl extends BaseBusiness implements OiuvlcteService {

	@Autowired
	private OiuvlcteRepository oiuvlcteRepository;
	

	@Override
	public VAddresses perExecuteQuery(final CourseActivities serachObj) {
		VAddresses addSearch=new VAddresses();
		CourseActivities actObj;
		actObj = oiuvlcteRepository.getCrsDetails(serachObj);
		if (actObj != null) {
			if (actObj.getServicesAddressId() != null) {
				addSearch = oiuvlcteRepository.getAdresss (actObj.getServicesAddressId());
				
			}
			if (actObj.getInternalLocationId() != null) {
				final String facility = oiuvlcteRepository.getFacilityDet(actObj);
				addSearch.setFacility(facility);
			}

		}
		return addSearch;
	}

}