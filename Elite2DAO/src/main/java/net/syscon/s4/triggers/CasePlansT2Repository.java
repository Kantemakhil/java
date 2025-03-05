package net.syscon.s4.triggers;


import net.syscon.s4.im.beans.CasePlans;


public interface CasePlansT2Repository {
	
	Integer workFlowsCount(CasePlans casePlans);

	Integer workFlowsIdNextVal();

	Integer insertWorkFlows(CasePlans casePlans, Integer lvWorkFlowId);

	Integer insertWorkFlowsLogs(Integer lvWorkFlowId, String userName,String locateAgyLocId);
	
	}


