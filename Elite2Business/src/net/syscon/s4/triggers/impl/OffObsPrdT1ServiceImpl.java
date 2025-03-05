package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.careinplacement.beans.OffObsPrdCharacteristics;
import net.syscon.s4.inst.careinplacement.beans.OffObservationPeriods;
import net.syscon.s4.pkgs.ApiOffObsStaging;
import net.syscon.s4.pkgs.api_off_observastion.ApiOffObservastionService;
import net.syscon.s4.triggers.OffObsPrdT1Repository;
import net.syscon.s4.triggers.OffObsPrdT1Service;

@Service
public class OffObsPrdT1ServiceImpl implements OffObsPrdT1Service {
	private final Logger logger = LogManager.getLogger(OffObsPrdT1ServiceImpl.class);
	@Autowired
	OffObsPrdT1Repository offObsPrdT1Repository;
	@Autowired
	ApiOffObservastionService apiOffObservastionService;

	@Override
	public Integer offObsPrdT1Trigger(final OffObsPrdCharacteristics offObsPrdChara, final String sqlOperation) {
		Integer result = 0;
		ApiOffObsStaging apiOffObsStaging = new ApiOffObsStaging();
		final String authCheckCur = offObsPrdT1Repository.authCheckCur();
		if ("Y".equals(authCheckCur)) {
			final Long getFreqCur = offObsPrdT1Repository.getFreqCur(offObsPrdChara.getObservationType(),
					offObsPrdChara.getCharacteristicsCode());
			final OffObservationPeriods getPeriodFreqCur = offObsPrdT1Repository
					.getPeriodFreqCur(offObsPrdChara.getOffenderBookId(), offObsPrdChara.getObsPeriodId());
			final String statusCode = Optional.ofNullable(offObsPrdChara.getStatusCode()).isPresent()
					? offObsPrdChara.getStatusCode()
					: "A";
			final String lvStatusCode = Optional.ofNullable(getPeriodFreqCur.getStatusCode()).isPresent()
					? getPeriodFreqCur.getStatusCode()
					: "A";
			try {
				if ("inserting".equals(sqlOperation)) {
					if (!"E".equals(statusCode) && !"E".equals(lvStatusCode)) {
						apiOffObsStaging = new ApiOffObsStaging();
						dataMapper(offObsPrdChara, apiOffObsStaging, getFreqCur, lvStatusCode);
						result = apiOffObservastionService.logging(apiOffObsStaging);
					}

				} else if ("updating".equals(sqlOperation)) {
					apiOffObsStaging = new ApiOffObsStaging();
					dataMapper(offObsPrdChara, apiOffObsStaging, getFreqCur, lvStatusCode);
					apiOffObsStaging.setTrigEvent("UPDATE");
					result = apiOffObservastionService.logging(apiOffObsStaging);
				} else {
					apiOffObsStaging = new ApiOffObsStaging();
					dataMapper(offObsPrdChara, apiOffObsStaging, getFreqCur, lvStatusCode);
					apiOffObsStaging.setTrigEvent("UPDATE");
					result = apiOffObservastionService.logging(apiOffObsStaging);
				}
			} catch (final Exception e) {
				logger.error("offObsPrdT1Trigger", e);
			}
		}
		return result;
	}

	private void dataMapper(final OffObsPrdCharacteristics offObsPrdChara, final ApiOffObsStaging apiOffObsStaging,
			final Long getFreqCur, final String lvStatusCode) {
		apiOffObsStaging.setTableName("OFF_OBS_PRD_CHARACTERISTICS");
		apiOffObsStaging.setTrigEvent("INSERT");
		apiOffObsStaging.setOffenderBookId(offObsPrdChara.getOffenderBookId());
		apiOffObsStaging.setObservationType(offObsPrdChara.getObservationType());
		apiOffObsStaging.setFrequency(getFreqCur);
		apiOffObsStaging.setStatusCode(lvStatusCode);
		apiOffObsStaging.setModifyDatetime(offObsPrdChara.getModifyDatetime());
		apiOffObsStaging.setObsPeriodId(offObsPrdChara.getObsPrdCharId());
		apiOffObsStaging.setCreateDatetime(offObsPrdChara.getCreateDatetime());
		apiOffObsStaging.setCreateUserId(offObsPrdChara.getCreateUserId());
		apiOffObsStaging.setModifyDatetime(offObsPrdChara.getModifyDatetime());
	}

}
