package net.syscon.s4.pkgs.ocdpatte.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.pkgs.ocdpatte.OcdpattePkgRepository;
import net.syscon.s4.pkgs.ocdpatte.OcdpattePkgService;

@Service
public class OcdpattePkgServiceImpl implements OcdpattePkgService {
	@Autowired
	private OcdpattePkgRepository OcdpatteRepository;

	private static String N = "N";

	@Override
	public Map<String, Object> getActOutcomeFlag(final String eventType, final String outcomeCode,
			final Date eventDate) {
		final Map<String, Object> outParams = new HashMap<String, Object>();
		List<CourseActivities> payNdAbsenceList = null;
		String flag1 = null;
		String flag2 = null;

		payNdAbsenceList = OcdpatteRepository.getActOutcomeFlag(eventType, outcomeCode, eventDate);
		for (final CourseActivities courseActivities : payNdAbsenceList) {
			flag1 = courseActivities.getActiveFlag() != null ? courseActivities.getActiveFlag() : N;
			flag2 = courseActivities.getAgencyLocationType() != null ? courseActivities.getAgencyLocationType() : N;
		}
		outParams.put("P_PAY_FLAG", flag1);
		outParams.put("P_AUTHORISED_ABSENCE_FLAG", flag2);

		return outParams;
	}

	@Override
	public String getProviderType(final String agyLocId) {
		return OcdpatteRepository.getProviderType(agyLocId);
	}

	@Override
	public String getPaylock(final Long eventId) {
		return OcdpatteRepository.getPaylock(eventId);
	}
}
