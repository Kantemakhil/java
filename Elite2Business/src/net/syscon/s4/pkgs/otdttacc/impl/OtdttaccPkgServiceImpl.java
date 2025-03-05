package net.syscon.s4.pkgs.otdttacc.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.otdttacc.OtdttaccPkgRepository;
import net.syscon.s4.pkgs.otdttacc.OtdttaccPkgService;

@Service
public class OtdttaccPkgServiceImpl implements OtdttaccPkgService {
	@Autowired
	private OtdttaccPkgRepository otdttaccRepository;

	private static final String YES = "Y";
	private static final String NO = "N";

	@Override
	public String isDuplicateOffenders(final Long pRootOffenderId) {
		final List<BigDecimal> tabRootOffId = otdttaccRepository.getListOfRootOffenderId();
		if (tabRootOffId.contains(pRootOffenderId)) {
			return YES;
		} else {
			tabRootOffId.add(BigDecimal.valueOf(pRootOffenderId));
			return NO;
		}
	}
}