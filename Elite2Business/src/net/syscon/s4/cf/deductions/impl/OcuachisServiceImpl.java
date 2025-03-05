package net.syscon.s4.cf.deductions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.OcuachisRepository;
import net.syscon.s4.cf.deductions.OcuachisService;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.genericservices.BaseBusiness;

@Service
public class OcuachisServiceImpl extends BaseBusiness implements OcuachisService{
	@Autowired
	private OcuachisRepository ocuachisRepository;
	@Override
	public List<FeeAccountProfiles> accountHistoryQuery(FeeAccountProfiles searchObject) {
		return ocuachisRepository.accountHistoryQuery(searchObject);
	}

	
	@Override
	public String getDescription(FeeAccountProfiles searchObject) {	
		return ocuachisRepository.getDescription(searchObject);
	}
}
