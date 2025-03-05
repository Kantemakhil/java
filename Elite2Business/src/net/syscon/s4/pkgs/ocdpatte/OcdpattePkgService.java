package net.syscon.s4.pkgs.ocdpatte;

import java.util.Date;
import java.util.Map;

public interface OcdpattePkgService {

	Map<String, Object> getActOutcomeFlag(final String eventType, final String outcomeCode, final Date eventDate);

	String getProviderType(final String agyLocId);

	String getPaylock(final Long eventId);
}
