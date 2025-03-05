package net.syscon.s4.inst.programswithoutschedules;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.im.beans.CreateAdhocEmail;
import net.syscon.s4.im.beans.EmailRecipients;
import net.syscon.s4.im.beans.Offenders;

/**
 * Interface OsuemailRepository
 */
public interface OsuemailRepository {
	List<Work> rgWorksRecordGroup(String caseloadType);

	Integer createAdhocEmail(CreateAdhocEmail createAdhocEmail);

	String getEmailBody();

	String getEmailSubject();

	String getEmailSender();

	List<EmailRecipients> getEmailRecipients(long workId);

	Offenders getOffendersDetails(Integer offenderBookId);

	Work getEmailBodySubject(Integer workId);

	Integer sendOsuemail(CreateAdhocEmail createAdhocEmail);

	Long adhocEmailSeq();

	Integer saveAdhocEmailRecipients(List<EmailRecipients> emailRecipientsList);

}
