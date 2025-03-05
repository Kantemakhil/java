package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.careinplacement.beans.OffObservationPeriods;
import net.syscon.s4.pkgs.ApiOffObsStaging;
import net.syscon.s4.pkgs.api_off_observastion.ApiOffObservastionService;
import net.syscon.s4.triggers.OffObservationPeriodsT1Repository;
import net.syscon.s4.triggers.OffObservationPeriodsT1Service;

@Service
public class OffObservationPeriodsT1ServiceImpl implements OffObservationPeriodsT1Service {
	private static final Logger logger = LogManager.getLogger(OffObservationPeriodsT1ServiceImpl.class);
	@Autowired
	OffObservationPeriodsT1Repository offObservationPeriodsT1Repository;
	@Autowired
	ApiOffObservastionService apiOffObservastionService;

	@Override
	public Integer offObservationPeriodsT1Trigger(final OffObservationPeriods offObservationPeriods,
			final String sqlOperation) {
		final ApiOffObsStaging apiOffObsStaging = new ApiOffObsStaging();
		final String lvAuthCheck = offObservationPeriodsT1Repository.authCheckCur();
		Integer result = 0;
		try {
			if ("Y".equals(lvAuthCheck)) {
				if ("inserting".equalsIgnoreCase(sqlOperation)) {
					dataMapping(offObservationPeriods, apiOffObsStaging);
					apiOffObsStaging.setCreateDatetime(offObservationPeriods.getCreateDatetime());
					apiOffObsStaging.setCreateUserId(offObservationPeriods.getCreateUserId());
					apiOffObsStaging.setTrigEvent("INSERT");
					result = apiOffObservastionService.logging(apiOffObsStaging);
				} else if ("inserting".equalsIgnoreCase(sqlOperation)) {
					dataMapping(offObservationPeriods, apiOffObsStaging);
					apiOffObsStaging.setModifyUserId(offObservationPeriods.getModifyUserId());
					apiOffObsStaging.setTrigEvent("UPDATE");
					result = apiOffObservastionService.logging(apiOffObsStaging);
				}

			}

		} catch (final Exception e) {
			logger.error("offObservationPeriodsT1Trigger", e);
			return null;
		}
		return result;
	}

	private void dataMapping(final OffObservationPeriods offObservationPeriods,
			final ApiOffObsStaging apiOffObsStaging) {
		apiOffObsStaging.setTableName("OFF_OBSERVATION_PERIODS");
		apiOffObsStaging.setOffenderBookId(offObservationPeriods.getOffenderBookId());
		apiOffObsStaging.setObservationType(offObservationPeriods.getObservationType());
		apiOffObsStaging.setFrequency(offObservationPeriods.getFrequency());
		apiOffObsStaging.setStatusCode(offObservationPeriods.getStatusCode());
		apiOffObsStaging.setModifyDatetime(offObservationPeriods.getModifyDatetime());
		apiOffObsStaging.setObsPeriodId(offObservationPeriods.getObsPeriodId());
	}

}
