package net.syscon.s4.common.screenworkflow;


import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.common.beans.ScreenFlowWork;
public interface ScreenWorkFlowService {
	List<ScreenFlowWork> getWorkFlowScreens() throws SQLException;
}






