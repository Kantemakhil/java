package net.syscon.s4.pkgs.ocdoapop.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.pkgs.ocdoapop.OcdoapopPkgRepository;
import net.syscon.s4.pkgs.ocdoapop.OcdoapopPkgService;

@Service
public class OcdoapopPkgServiceImpl implements OcdoapopPkgService {
	@Autowired
	private OcdoapopPkgRepository ocdoapopRepository;

	@Override
	public VAddresses fetchVAddressRecord(final Long pAddressId) {
		return ocdoapopRepository.fetchVAddressRecord(pAddressId);

	}

}
