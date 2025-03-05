package net.syscon.s4.globaloffenderrecords;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderAlerts;

/**
 * Interface OcualertRepository
 */
public interface OcualertRepository {
	List<OffenderAlerts> alertExecuteQuery(OffenderAlerts objOffenderAlerts);

	ReferenceCodes postQuery(ReferenceCodes searchRecord);

	List<ReferenceCodes> rgAlertDescription(String domain);

}
