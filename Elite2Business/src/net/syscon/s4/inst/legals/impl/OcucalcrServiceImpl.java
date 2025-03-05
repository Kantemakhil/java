package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.inst.legals.OcucalcrRepository;
import net.syscon.s4.inst.legals.OcucalcrService;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;

@Service
public class OcucalcrServiceImpl  implements OcucalcrService {

	@Autowired
	OcucalcrRepository ocucalcrRepository;

	@Override
	public List<LovList> populateCalculationReasonList() {
		List<LovList> calcReasonList = new ArrayList<LovList>();
		calcReasonList = ocucalcrRepository.populateCalculationReasonList();
		return calcReasonList;
	}

	@Override
	public String populateStaffName(String username) {
		final Integer staffId=generateStaffId(username);
		final String staffName = ocucalcrRepository.populateStaffName(staffId);
		return staffName;
	}

	private Integer generateStaffId(String username) {
		//Integer staffId=0;
		return ocucalcrRepository.generateStaffId(username);
		//return staffId;
	}

	@Override
	public String calExpDate(SentenceCalculation sentenceCalc) {
		return ocucalcrRepository.calExpDate(sentenceCalc);
	}
	@Override
	public List<LovList> getStaffMembers() {
		List<LovList> calcReasonList = new ArrayList<LovList>();
		calcReasonList = ocucalcrRepository.getStaffMembers();
		return calcReasonList;
	}
}
