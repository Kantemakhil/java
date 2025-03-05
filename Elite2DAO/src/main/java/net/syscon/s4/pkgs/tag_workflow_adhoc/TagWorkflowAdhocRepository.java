package net.syscon.s4.pkgs.tag_workflow_adhoc;

import org.springframework.stereotype.Repository;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.im.beans.CreateAdhocEmail;
import net.syscon.s4.pkgs.MessageQueue;

@Repository
public interface TagWorkflowAdhocRepository {

	Integer insertAdhocEmail(final CreateAdhocEmail bean);

	Long selectTeamMembers(final TagWorkflowBrowseQueue bean);
}