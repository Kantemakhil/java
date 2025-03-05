package net.syscon.s4.globaloffenderrecords;

import java.util.List;

import net.syscon.s4.common.beans.OffenderAlertsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderAlerts;

/**
 * Interface OcualertService
 */
public interface OcualertService {

	List<OffenderAlerts> alertExecuteQuery(OffenderAlerts objOffenderAlerts);

	Integer alertCommit(OffenderAlertsCommitBean commitBean);

	List<ReferenceCodes> rgAlertDescription(String domain);

}
