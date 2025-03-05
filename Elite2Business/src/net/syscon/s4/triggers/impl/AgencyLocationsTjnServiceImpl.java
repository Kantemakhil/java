package net.syscon.s4.triggers.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.triggers.AgencyLocationsTjnRepository;
import net.syscon.s4.triggers.AgencyLocationsTjnService;
@Service
public class AgencyLocationsTjnServiceImpl implements AgencyLocationsTjnService{
	@Autowired
	private AgencyLocationsTjnRepository agencyLocationsTjnRepository;
	@Transactional	
	@Override
	public void agencyLocationsTjn(final AgencyLocations newAgencyLocations) {
		final AgencyLocations oldAgencyLocations=new AgencyLocations();//TODO Need to get old data based on primary key..but could not found in DB
		if(Objects.isNull(oldAgencyLocations) && Objects.nonNull(newAgencyLocations)) {
			agencyLocationsTjnRepository.insertAgencyLocationsJn(newAgencyLocations, "INS");
		}if(Objects.nonNull(oldAgencyLocations) && Objects.nonNull(newAgencyLocations) && !newAgencyLocations.equals(oldAgencyLocations)) {
			agencyLocationsTjnRepository.insertAgencyLocationsJn(oldAgencyLocations, "UPD");
		}if(Objects.isNull(newAgencyLocations) && Objects.nonNull(oldAgencyLocations)) {
			agencyLocationsTjnRepository.insertAgencyLocationsJn(oldAgencyLocations, "DEL");
		}
	}

}
