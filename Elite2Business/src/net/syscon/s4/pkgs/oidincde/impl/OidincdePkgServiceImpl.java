package net.syscon.s4.pkgs.oidincde.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.oidincde.OidincdePkgRepository;
import net.syscon.s4.pkgs.oidincde.OidincdePkgService;

@Service
public class OidincdePkgServiceImpl implements OidincdePkgService {

	@Autowired
	private OidincdePkgRepository oidincdeRepository;

	@Override
	public List<Offenders> getOffDetailsByBookId(final Long offenderBookId) {
		return oidincdeRepository.getOffDetailsByBookId(offenderBookId);
	}

}
