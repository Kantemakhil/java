package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.WorksT1Repository;
import net.syscon.s4.triggers.WorksT1Service;

@Service
public class WorksT1ServiceImpl implements WorksT1Service {
	@Autowired
	private WorksT1Repository worksT1Repository;

	@Override
	public Long worksT1Trigger(Long workId) {
		Long lvWorkId;
		if (workId == null) {
			lvWorkId = worksT1Repository.getNextWorkId();
			workId = lvWorkId;
		}
		return workId;
	}

}
