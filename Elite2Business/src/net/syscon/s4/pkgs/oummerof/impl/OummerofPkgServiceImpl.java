package net.syscon.s4.pkgs.oummerof.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.oummerof.OummerofPkgRepository;
import net.syscon.s4.pkgs.oummerof.OummerofPkgService;
@Service
public class OummerofPkgServiceImpl implements OummerofPkgService{

	@Autowired
	private OummerofPkgRepository oummerofPkgRepository;
	@Override
	public Integer checkIsBothBookingsActive(BigDecimal getpFromRootOffId, BigDecimal getpToRootOffId) {
		Integer returnValue= 0;
		Integer countFromRoot= oummerofPkgRepository.getActiveRecordCount(getpFromRootOffId);
		Integer countToRoot= oummerofPkgRepository.getActiveRecordCount(getpToRootOffId);
		if(countFromRoot > 0 && countToRoot > 0) {
			returnValue = 1;
		}
		return returnValue;
	}

}
