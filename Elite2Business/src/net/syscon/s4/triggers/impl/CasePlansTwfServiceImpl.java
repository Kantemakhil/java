package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.sql.Clob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.CasePlansTwfService;

@Service
public class CasePlansTwfServiceImpl implements CasePlansTwfService {
	
	private static final String ACTIVE = "ACTIVE";
	private static final String COMM = "COMM";
	
	
	@Autowired
	private TagWfmsgService tagWfmsgService;
	
	@Autowired
	private TagWorkflowService tagWorkflowService;

	@Override
	public void casePlansTwf(final CasePlans plans) {
		if(plans!=null && ACTIVE.equals(plans.getCasePlanStatus())) {
			Clob lvXml=null;
			tagWfmsgService.append("case_plan_id", String.valueOf(plans.getCasePlanId()), lvXml);
			StringBuffer buffer=new StringBuffer(plans.getCaseloadType());
			if(plans.getCaseloadType()==null) {
				buffer.delete(0, plans.getCaseloadType().length()-1);
				if(COMM.equals(plans.getCaseloadType())) 
					tagWfmsgService.append("staff_id_to", String.valueOf(plans.getSacStaffId()), lvXml);
				else
					tagWfmsgService.append("staff_id_to", String.valueOf(plans.getInstSacStaffId()), lvXml);
				tagWfmsgService.append("staff_id_to",plans.getAgyLocId(), lvXml);
				tagWorkflowService.createCaseNote(new BigDecimal(plans.getOffenderBookId()),"ASSIGN_OM", null,null, plans.getCreateDateTime(),"AUTO",plans.getCreateUserId());
			}
			
		}//if
		
	}//method

}//class
