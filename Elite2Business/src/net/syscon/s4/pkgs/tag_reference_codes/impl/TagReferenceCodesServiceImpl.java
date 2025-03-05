package net.syscon.s4.pkgs.tag_reference_codes.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesRepository;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesService;

@Service
public class TagReferenceCodesServiceImpl implements TagReferenceCodesService {
	@Autowired
	private TagReferenceCodesRepository TagReferenceCodesRepository;

	@Override
	public ReferenceCodes domainCursor(final String domain, final String refCode) {
	 return TagReferenceCodesRepository.domainCursor(domain, refCode);
		
	}

	@Override
	public String getDescCode(final String domain,final  String code) {
		return TagReferenceCodesRepository.getDescCode(domain, code);
	}

	@Override
	public String defaultDomain(final String domain) {
		return TagReferenceCodesRepository.defaultDomain(domain);
	}

}