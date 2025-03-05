package net.syscon.s4.pkgs.tag_workflow_adhoc.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.im.beans.CreateAdhocEmail;
import net.syscon.s4.pkgs.Message;
import net.syscon.s4.pkgs.MessageQueue;
import net.syscon.s4.pkgs.tag_workflow_adhoc.TagWorkflowAdhocRepository;
import net.syscon.s4.pkgs.tag_workflow_adhoc.TagWorkflowAdhocService;

@Service
public class TagWorkflowAdhocServiceImpl implements TagWorkflowAdhocService {

	@Autowired
	private TagWorkflowAdhocRepository tagWorkflowAdhocRepository;

	// Global variables
	private static final String gEmailSubject = null;
	private static final String gEmailBody = null;
	private static final String gEmailSender = null;
	// private String gEmailFrom = null;

	@Override
	public Integer createAdhocEmail(final CreateAdhocEmail bean) {
		final Integer count = tagWorkflowAdhocRepository.insertAdhocEmail(bean);
		if (bean.getpEmailBody() != null) {
			// dbms_lob.writeappend(lv_body, length(p_email_body), p_email_body);
		}
		/*
		 * lv_xml := Tag_Wfmsg.create_xml; Tag_Wfmsg.append ('OFFENDER_BOOK_ID',
		 * p_offender_book_id, lv_xml); Tag_Wfmsg.append ('EMAIL_ID', lv_email_id,
		 * lv_xml); tag_workflow.create_email ( p_exe_trigger_name => 'ADHOC_EMAIL'
		 * ,p_params => lv_xml ,p_offender_book_id => p_offender_book_id ,p_event_date
		 * => sysdate ,p_key => TO_CHAR(lv_email_id));
		 */
		return count;
	}

	@Override
	public String getEmailBody() {
		return gEmailBody;
	}

	@Override
	public String getEmailSubject() {
		return gEmailSubject;
	}

	@Override
	public String getEmailSender() {
		return gEmailSender;
	}

	@Override
	public String createAdhocWorkFlow(TagWorkflowBrowseQueue bean) {
		TagWorkflowBrowseQueue msgQue = new TagWorkflowBrowseQueue();
		String retVal;

		try {
			if (bean.getWorkflowType() !=null) {
			if (bean.getWorkflowType().equals("MEMO")) {
				msgQue.setTeamId(bean.getTeamId());
				msgQue.setWorkflowType(msgQue.getWorkflowType());
				msgQue.setSeverityCode(msgQue.getSeverityCode());
				if (msgQue.getAcknowledgementRequired().equals("Y")) {
					msgQue.setAcknowledgementRequired("Y");
				} else {
					msgQue.setAcknowledgementRequired(null);
				}
				msgQue.setAcknowledgementSubject(bean.getAcknowledgementSubject());
				msgQue.setMessageText(bean.getMessageText());
				msgQue.setOffenderBookId(bean.getOffenderBookId());
				msgQue.setTeamId(bean.getTeamId());
				msgQue.setStaffId(bean.getStaffId());
				msgQue.setDueDate(bean.getDueDate());
				Long result = insertWrokMessage(bean);

			} else if (bean.getWorkflowType().equals("TASK")) {
				msgQue.setWorkflowType(bean.getWorkflowType());
				msgQue.setMessageText(bean.getMessageText());
				msgQue.setTeamId(bean.getTeamId());
				msgQue.setWorkId(bean.getWorkId());
				msgQue.setStaffId(bean.getStaffId());
				msgQue.setOffenderBookId(bean.getOffenderBookId());
				msgQue.setDueDate(bean.getDueDate());
				Long result = insertWrokMessage(bean);

			} else if (bean.getWorkflowType().equals("CNOTE")) {

			}
			}
			retVal = "1";
		} catch (Exception e) {
			retVal = "2";
		}
		return retVal;

	}

	public Long insertWrokMessage(TagWorkflowBrowseQueue bean) {
		String queueName = null;
		Message msg = new Message();
		if (msg.getTeamMemberId() != null) {
			Long staffId = tagWorkflowAdhocRepository.selectTeamMembers(bean);
		} else {
			// Tag_Error.raise_app_error
		}
		queueName = "TAGWF1.TAG_WF_ROUTER";
		msg.setTeamId(bean.getTeamId().longValue());
		if (bean.getTeamMemberId() != null) {
			// msg.setStaffId(staffId);
		}
		if (bean.getStaffId() != null) {
			// msg.setStaffId(staffId1);
		}
		msg.setCompleteCommentText(bean.getMessageText());
		msg.setOffenderBookId(bean.getOffenderBookId().longValue());
		msg.setAssignDate(new Date());
		msg.setSpareDate(new Date());
		msg.setWorkId(bean.getWorkId().longValue());
		msg.setDueDate(bean.getDueDate());
		msg.setTriggereReason("WORKMESSAGE");
		msg.setWorkTriggger("WORKMESSAGE");
		msg.setWorkFlowType(bean.getWorkflowType());
		Long workFlowId = bean.getWorkId().longValue();
		msg.setWorkFlowId(bean.getWorkId().toString());
		msg.setOriginalMsGrid(bean.getWorkId().toString());
		msg.setNoteSourseCode(bean.getSeverityCode());
		msg.setSpareNumber(Integer.parseInt(bean.getAcknowledgementRequired()));
		msg.setCompleteCommentText(bean.getAcknowledgementSubject());
		// send_message ( p_queue_name => l_queue_name,
		// p_message => l_message,
		// p_message_handle => l_message_handle );
		return workFlowId;

	}

}