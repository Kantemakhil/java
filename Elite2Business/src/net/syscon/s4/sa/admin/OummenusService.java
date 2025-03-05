package net.syscon.s4.sa.admin;
import java.util.List;

import net.syscon.s4.common.beans.MenuSecuritiesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VMenuSecs;
/**
 * Interface OummenusService 
 */
public interface OummenusService  {
	List<VMenuSecs> vMenuSecsExecuteQuery(VMenuSecs objVMenuSecs) ;

	Integer vMenuSecsCommit(MenuSecuritiesCommitBean commitBean) ;

	List<OmsModules> rgMenuSecDescRecordGroup() ;

}
