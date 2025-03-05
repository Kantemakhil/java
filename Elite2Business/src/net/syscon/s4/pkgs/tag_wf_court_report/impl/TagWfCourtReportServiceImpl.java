package net.syscon.s4.pkgs.tag_wf_court_report.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.pkgs.tag_wf_court_report.TagWfCourtReportRepository;
import net.syscon.s4.pkgs.tag_wf_court_report.TagWfCourtReportService;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;

@Service
public class TagWfCourtReportServiceImpl implements TagWfCourtReportService {
	@Autowired
	private TagWfCourtReportRepository TagWfCourtReportRepository;

	@Autowired
	private TagWfmsgService tagWfmsgService;
	@Autowired
	private TagWorkflowService tagWorkflowService;

	private static Logger logger = LogManager.getLogger(TagWfCourtReportServiceImpl.class.getName());

	@Override
	public Integer updateOrderStatus(final BigDecimal pOffenderBookId, final BigDecimal pMsgId, final String userName) {
		Integer returnValue = 0;
		Long lvEventId = null;
		try {
			lvEventId = TagWfCourtReportRepository.getEventIdForUpOrdStatus(pOffenderBookId, pMsgId);
			if (lvEventId != null) {
				TagWfCourtReportRepository.updateOrderStatusForOrders(pOffenderBookId, pMsgId, userName);
			}
			returnValue = 1;
		} catch (Exception e) {
			logger.error("updateOrderStatusForOrders :", e);
		}
		return returnValue;
	}

	@Override
	public void createReportDone(final Orders orders) {
		try {
			// TODO in below statements (Tag_Wfmsg.create_xml) xml type code is present
			/**
			 * lv_xml XMLTYPE; lv_xml := Tag_Wfmsg.create_xml;
			 * 
			 * lvXml = tagWfmsgService.createXml();
			 * Tag_Wfmsg.append('report_type',p_report_type,lv_xml);
			 * Tag_Wfmsg.append('court_name',p_court_desc,lv_xml);
			 * Tag_Wfmsg.append('completion_date',p_compl_date,lv_xml);
			 * Tag_Wfmsg.append('staff_id',p_user,lv_xml);
			 */
			Object lvXml = null;
			tagWorkflowService.createWorkflow(orders.getWorkflowId(), null, lvXml, orders.getOffenderBookId(),
					orders.getCompleteDate(), null, null, orders.getCreateUserId(),"");

			tagWorkflowService.createCaseNote(orders.getOffenderBookId(),"REPORT_DONE", lvXml,orders.getEventId(), orders.getCompleteDate(),"AUTO",
					orders.getCreateUserId());

			tagWorkflowService.completeWorkflow("WORKMESSAGE", orders.getWorkflowId(), null, null);
		} catch (Exception e) {
			logger.error("createReportDone", e);
		}
	}
}