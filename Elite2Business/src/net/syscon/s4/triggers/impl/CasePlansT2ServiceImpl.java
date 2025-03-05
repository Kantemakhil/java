package net.syscon.s4.triggers.impl;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.triggers.CasePlansT2Repository;
import net.syscon.s4.triggers.CasePlansT2Service;


@Service
public class CasePlansT2ServiceImpl implements CasePlansT2Service{
	
	@Autowired
	CasePlansT2Repository casePlansT2Repository;


	@Transactional
	@Override
	public void casePlansT2(final CasePlans casePlans,final String userName) {
		if(Objects.nonNull(casePlans)) {
			int workFlowsCount = casePlansT2Repository.workFlowsCount(casePlans);
			if(workFlowsCount==0) {
				casePlans.setCreateUserId(userName);
				int lvWorkFlowId=casePlansT2Repository.workFlowsIdNextVal();
				casePlansT2Repository.insertWorkFlows(casePlans, lvWorkFlowId);
				casePlansT2Repository.insertWorkFlowsLogs(lvWorkFlowId, userName,casePlans.getAgyLocId());
			}
		}
	}
}
