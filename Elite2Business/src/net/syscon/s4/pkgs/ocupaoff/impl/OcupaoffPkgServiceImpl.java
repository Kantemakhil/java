package net.syscon.s4.pkgs.ocupaoff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.pkgs.ocupaoff.OcupaoffPkgRepository;
import net.syscon.s4.pkgs.ocupaoff.OcupaoffPkgService;

@Service
public class OcupaoffPkgServiceImpl implements OcupaoffPkgService {
	@Autowired
	private OcupaoffPkgRepository ocupaoffRepository;

	@Override
	public Integer updateVOffCrseEvents(final OffenderCourseAttendance bean) {
		return ocupaoffRepository.updateVOffCourseEvents(bean);
	}

	@Override
	public void offeCourseAttendance(final OffenderCourseAttendance offcrsAtt, final String userName) {
		offcrsAtt.setModifyUserId(userName);
		ocupaoffRepository.updateCourseAttendance(offcrsAtt);
	}
}
