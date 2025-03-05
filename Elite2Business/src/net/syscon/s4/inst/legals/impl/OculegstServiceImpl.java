package net.syscon.s4.inst.legals.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import net.syscon.s4.inst.legals.OculegstRepository;
import net.syscon.s4.inst.legals.OculegstService;
import net.syscon.s4.inst.legals.beans.UpdateReason;
import net.syscon.s4.inst.legals.beans.UpdateUser;
import net.syscon.s4.inst.legals.beans.Sentences;

@Service
public class OculegstServiceImpl implements OculegstService{

	@Autowired
	OculegstRepository oculegstRepository;
	@Override
	public List<UpdateReason> getUpdateCaseReason() {
		return oculegstRepository.getUpdateCaseReason();
	}
	
	@Override
	public List<Sentences> populateUpdateReason(String category, String sentenceCalcType) {
		List<Sentences> resultList = new ArrayList<Sentences>();
		resultList = oculegstRepository.populateUpdateReason(category,sentenceCalcType);
		return resultList;
	}
	
	@Override
	public UpdateUser getUpdateUser(String name) {		
		return oculegstRepository.getUpdateUser(name);
	}

	@Override
	public List<UpdateReason> getUpdateConditionReason() {
		return oculegstRepository.getUpdateConditionReason();
	}

}
