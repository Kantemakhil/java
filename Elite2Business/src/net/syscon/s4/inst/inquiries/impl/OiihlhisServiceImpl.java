package net.syscon.s4.inst.inquiries.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.inquiries.OiihlhisRepository;
import net.syscon.s4.inst.inquiries.OiihlhisService;

@Service
public class OiihlhisServiceImpl extends BaseBusiness implements OiihlhisService {

	@Autowired
	private OiihlhisRepository oiihlhisRepository;

	public List<BedAssignmentHistories> bedAhExecuteQuery(BedAssignmentHistories searchRecord) {
		List<BedAssignmentHistories> list = oiihlhisRepository.bedAhExecuteQuery(searchRecord);
		list.stream().map(data -> {
			LivingUnits livingUnits = oiihlhisRepository.postQuery(data.getLivingUnitId());
			if (livingUnits != null) {
				data.setAgyLocId(livingUnits.getAgyLocId());
				data.setDspDescription(livingUnits.getDescription());
			}
			return data;
		}).collect(Collectors.toList());
		return list;
	}

}