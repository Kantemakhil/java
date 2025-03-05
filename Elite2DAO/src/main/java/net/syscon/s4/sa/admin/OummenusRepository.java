package net.syscon.s4.sa.admin;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VMenuSecs;

/**
 * Interface OummenusRepository
 */
public interface OummenusRepository {

	Integer vMenuSecsPreInsert(MenuSecurities paramBean);

	List<VMenuSecs> vMenuSecsExecuteQuery(VMenuSecs objVMenuSecs);

	Integer vMenuSecsInsertQuery(List<MenuSecurities> lstVMenuSecs);

	Integer vMenuSecsUpdateQuery(List<MenuSecurities> lstVMenuSecs);

	Integer vMenuSecsDeleteQuery(List<MenuSecurities> lstVMenuSecs);

	List<OmsModules> rgMenuSecDescRecordGroup();

	Integer descUpdateQuery(String description, String modifyUserId, String moduleName);

	Integer vMenuSecsPreInsertPreUpdate(MenuSecurities bean);
	
	OmsModules getOmsModule(final String moduleName);

	Integer getParentMenuExistCount(BigDecimal menuId); 

}
