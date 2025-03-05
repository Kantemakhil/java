package net.syscon.s4.pkgs.api_off_observastion.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.ApiOffObsStaging;
import net.syscon.s4.pkgs.api_off_observastion.ApiOffObservastionRepository;
import net.syscon.s4.pkgs.api_off_observastion.ApiOffObservastionService;

@Service
public class ApiOffObservastionServiceImpl implements ApiOffObservastionService {
	private final Logger logger = LogManager.getLogger(ApiOffObservastionServiceImpl.class);
	@Autowired
	ApiOffObservastionRepository apiOffObservastionRepository;

	@Override
	public Integer logging(ApiOffObsStaging apiOffObsStaging) {
		Integer result = 0;
		try {
			apiOffObsStaging.setLogId(apiOffObservastionRepository.lvSeq());
			result = apiOffObservastionRepository.logging(apiOffObsStaging);
		} catch (Exception e) {
			result = 0;
			logger.error("logger", e);
		}
		return result;
	}

}
