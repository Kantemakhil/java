package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.OcuoffenRepository;
import net.syscon.s4.inst.legals.OcuoffenService;
import net.syscon.s4.inst.legals.OumorcodRepository;
import net.syscon.s4.inst.legals.OumorcodService;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.OffenseResultCodes;
import net.syscon.s4.inst.legals.beans.Offenses;

@Service
public class OumorcodServiceImpl implements OumorcodService {
	
	@Autowired
	OumorcodRepository oumorcodRepository;

	@Override
	public List<OffenseResultCodes> offencesResultsCodes() {
		List<OffenseResultCodes> offencesResultsCodesData = new ArrayList<>();
		offencesResultsCodesData = oumorcodRepository.offencesResultsCodes();	
		return offencesResultsCodesData;
	
	}

}

