package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.OcuoffenRepository;
import net.syscon.s4.inst.legals.OcuoffenService;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.Offenses;

@Service
public class OcuoffenServiceImpl implements OcuoffenService {
	
	@Autowired
	OcuoffenRepository ocuoffenRepository;

	@Override
	public List<Offenses> offencesAgainstOrdersData() {
		List<Offenses> offensesSearchResult = new ArrayList<>();
		offensesSearchResult = ocuoffenRepository.offencesAgainstOrdersData();	
		return offensesSearchResult;
	
	}

}
