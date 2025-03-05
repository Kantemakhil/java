package net.syscon.s4.pkgs.web_eoffender_support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.pkgs.web_eoffender_support.WebEoffenderSupportRepository;
import net.syscon.s4.pkgs.web_eoffender_support.WebEoffenderSupportService;

@Service
public class WebEoffenderSupportServiceImpl implements WebEoffenderSupportService {

	@Autowired
	private WebEoffenderSupportRepository webEoffenderSupportRepository;

	@Override
	public EoffenderDetails getUserFromKey(final String keyLogin) {
		return webEoffenderSupportRepository.getUserFromKey(keyLogin);
	}

}