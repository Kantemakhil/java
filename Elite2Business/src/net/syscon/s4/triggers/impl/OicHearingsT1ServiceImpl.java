package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.triggers.OicHearingsT1Repository;
import net.syscon.s4.triggers.OicHearingsT1Service;

@Service
public class OicHearingsT1ServiceImpl implements OicHearingsT1Service {
	@Autowired
	OicHearingsT1Repository oicHearingsT1Repository;

	@Override
	public List<OicHearings> oicHearingsT1Tgr(final List<OicHearings> oicHearingsList) {
		if (!oicHearingsList.isEmpty()) {
			for (final OicHearings newObj : oicHearingsList) {
				final OicHearings old = oicHearingsT1Repository.getOicHearings(newObj);
				if (newObj.getSealFlag() == null || (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
					if (null == newObj.getEventId() && Optional.ofNullable(newObj.getScheduleDate()).isPresent())
						newObj.setEventId(oicHearingsT1Repository.lEventId());
				}

			}
		}
		return oicHearingsList;
	}

}
