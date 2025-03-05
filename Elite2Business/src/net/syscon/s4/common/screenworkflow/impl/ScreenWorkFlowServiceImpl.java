package net.syscon.s4.common.screenworkflow.impl;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.common.beans.ScreenFlowWork;
import net.syscon.s4.common.screenworkflow.ScreenWorkFlowRepository;
import net.syscon.s4.common.screenworkflow.ScreenWorkFlowService;

@Service
public class ScreenWorkFlowServiceImpl implements ScreenWorkFlowService {
	
	@Autowired
	ScreenWorkFlowRepository screenFlow;
	
	@Override
	public List<ScreenFlowWork> getWorkFlowScreens() throws SQLException {  
		return (List<ScreenFlowWork>) screenFlow.getWorkFlowScreens();
	}
}
