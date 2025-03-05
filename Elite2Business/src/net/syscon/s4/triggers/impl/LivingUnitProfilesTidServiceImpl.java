package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.im.beans.LivingUnitProfiles;
import net.syscon.s4.triggers.LivingUnitProfilesTidRepository;
import net.syscon.s4.triggers.LivingUnitProfilesTidService;

@Service
public class LivingUnitProfilesTidServiceImpl implements LivingUnitProfilesTidService {
	private final Logger logger = LogManager.getLogger(LivingUnitProfilesTidServiceImpl.class.getName());
	@Autowired
	LivingUnitProfilesTidRepository livingUnitProfilesTidRepository;

	@Override
	public Integer livingUnitProfilesTid(final LivingUnitProfiles livingUnitProfilesNew,
			final LivingUnitProfiles livingUnitProfilesOld, final String sqlOperation) {
		AgyIntLocProfiles agyIntLocProfiles = new AgyIntLocProfiles();
		Integer result = 0;
		try {
			if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
				agyIntLocProfiles = new AgyIntLocProfiles();
				agyIntLocProfiles.setInternalLocationId(livingUnitProfilesNew.getLivingUnitId().longValue());
				agyIntLocProfiles.setIntLocProfileCode(livingUnitProfilesNew.getIntLocProfileType());
				agyIntLocProfiles.setIntLocProfileType(livingUnitProfilesNew.getIntLocProfileCode());
				agyIntLocProfiles.setCreateUserId(livingUnitProfilesNew.getCreateUserId());
				result = livingUnitProfilesTidRepository.insert(agyIntLocProfiles);
			} else if ("DELETING".equalsIgnoreCase(sqlOperation)) {
				agyIntLocProfiles = new AgyIntLocProfiles();
				agyIntLocProfiles.setInternalLocationId(livingUnitProfilesOld.getLivingUnitId().longValue());
				agyIntLocProfiles.setIntLocProfileCode(livingUnitProfilesOld.getIntLocProfileType());
				agyIntLocProfiles.setIntLocProfileType(livingUnitProfilesOld.getIntLocProfileCode());
				result = livingUnitProfilesTidRepository.delete(agyIntLocProfiles);
			}
		} catch (final Exception e) {
			logger.error("livingUnitProfilesTid", e);
			return result;
		}
		return result;
	}

}
