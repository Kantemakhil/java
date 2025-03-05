package net.syscon.s4.pkgs.tag_workflow_adhoc;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.im.beans.CreateAdhocEmail;
import net.syscon.s4.pkgs.MessageQueue;

public interface TagWorkflowAdhocService {

	Integer createAdhocEmail(final CreateAdhocEmail bean);

	String getEmailBody();

	String getEmailSubject();

	String getEmailSender();

	String createAdhocWorkFlow(final TagWorkflowBrowseQueue bean);

	Long insertWrokMessage(final TagWorkflowBrowseQueue bean);

}