package net.syscon.s4.inst.institutionalactivities.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.OciphistRepository;
import net.syscon.s4.inst.institutionalactivities.OciphistService;
import net.syscon.s4.inst.institutionalactivities.beans.prgPayBatchesBean;

@Service
public class OciphistServiceImpl extends BaseBusiness implements OciphistService {

	@Autowired
	private OciphistRepository ociphistRepository;

	@Override
	public List<prgPayBatchesBean> prgPayBatchesExecuteQuery(prgPayBatchesBean searchBean) {
		return ociphistRepository.prgPayBatchesExecuteQuery(searchBean);
	}

	@Override
	public List<VOffenderCourseAttendances> payDetailsExecuteQuery(prgPayBatchesBean searchBean) {
		return ociphistRepository.payDetailsExecuteQuery(searchBean);
	}
}
