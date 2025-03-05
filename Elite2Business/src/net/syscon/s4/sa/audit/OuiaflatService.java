package net.syscon.s4.sa.audit;

import java.util.List;

/**
 * Interface OuiaflatService
 */
public interface OuiaflatService {
	List<TagLoginAlerts> loginAlertsBlkExecuteQuery(TagLoginAlerts objTagLoginAlerts);

	Integer loginAlertsBlkCommit(TagLoginAlertscommitBean commitBean);

}
