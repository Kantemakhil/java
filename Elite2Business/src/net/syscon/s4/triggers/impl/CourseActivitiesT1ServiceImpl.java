package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.triggers.CourseActivitiesT1Service;

@Service
public class CourseActivitiesT1ServiceImpl implements CourseActivitiesT1Service {

	@Autowired
	private TagServiceService tagServiceService;

	private static final String INST = "INST";
	private static final String N = "N";
	private static final String Y = "Y";

	@Override
	public String courseActivitiesT1Trigger(String caseloadType, String holidayFlag, String userName) {
		String lvCaseloadType;
		final Caseloads obj = tagServiceService.getWorkingCaseload(userName);
		if (caseloadType == null) {

			lvCaseloadType = obj.getCaseloadType();
			caseloadType = lvCaseloadType;
		}

		if (INST.equals(caseloadType)) {
			holidayFlag = N;
		} else {
			holidayFlag = Y;
		}
		
		return holidayFlag;

	}

}
