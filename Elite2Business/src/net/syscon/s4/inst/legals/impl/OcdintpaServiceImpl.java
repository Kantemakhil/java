package net.syscon.s4.inst.legals.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.OcdintpaRepository;
import net.syscon.s4.inst.legals.OcdintpaService;
import net.syscon.s4.inst.legals.beans.InterestedParties;

@Service
public class OcdintpaServiceImpl implements OcdintpaService {
	
	@Autowired
	OcdintpaRepository ocdintpaRepository;

	@Override
	public List<InterestedParties> executeQuery(InterestedParties interestedParties) {
		return ocdintpaRepository.executeQuery(interestedParties);
	}
	
	@Override
	public Integer insertInterestedParties(List<InterestedParties> insertList) {
		return ocdintpaRepository.insertInterestedParties(insertList);
	}
	
	@Override
	public Integer updateInterestedParties(List<InterestedParties> updateList) {
		return ocdintpaRepository.updateInterestedParties(updateList);
	}
	
	@Override
	public Integer deleteInterestedParties(List<InterestedParties> deleteList) {
		return ocdintpaRepository.deleteInterestedParties(deleteList);
	}

}
