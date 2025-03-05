package net.syscon.s4.pkgs.oidbstrn.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.oidbstrn.OidbstrnPkgRepository;
import net.syscon.s4.pkgs.oidbstrn.OidbstrnPkgService;

@Service
public class OidbstrnPkgServiceImpl implements OidbstrnPkgService {

	@Autowired
	private OidbstrnPkgRepository OidbstrnRepository;

	@Override
	public Integer duplicateExists(final VOffenderAllSchedules schedules) {
		return OidbstrnRepository.duplicateExists(schedules);
	}

}
