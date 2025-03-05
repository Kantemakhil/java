package net.syscon.s4.pkgs.oms_search.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.oms_search.OmsSearchRepository;
import net.syscon.s4.pkgs.oms_search.OmsSearchService;

@Service
public class OmsSearchServiceImpl implements OmsSearchService {

	@Autowired
	private OmsSearchRepository omsSearchRepository;

	@Override
	public Integer checkOffenderIdDisplay(final String offIdDisplay) {
		Integer retVal = omsSearchRepository.checkOffenderIdDisplay(offIdDisplay);
		if (retVal != null)
			return retVal;
		else
			return 0;
	}

}