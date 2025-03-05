package net.syscon.s4.inst.programswithoutschedules.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CreateAdhocEmail;
import net.syscon.s4.im.beans.EmailRecipients;
import net.syscon.s4.im.beans.NewEmailCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.programswithoutschedules.OsuemailRepository;
import net.syscon.s4.inst.programswithoutschedules.OsuemailService;

/**
 * Class OsuemailServiceImpl
 */
@Service
public class OsuemailServiceImpl extends BaseBusiness implements OsuemailService {

	private static final String EMAIL_RTN = "EMAIL_RTN";
	@Autowired
	private OsuemailRepository osuemailRepository;

	/**
	 * This method is used to execute a record group
	 *
	 */
	@Override
	public List<Work> rgWorksRecordGroup(final String caseloadType) {
		return osuemailRepository.rgWorksRecordGroup(caseloadType);
	}

	/**
	 * This method is used to send a mail
	 *
	 */
	@Transactional
	@Override
	public Integer createAdhocEmail(final NewEmailCommitBean newEmailCommitBean) {
		final CreateAdhocEmail createAdhocEmail = new CreateAdhocEmail();
		createAdhocEmailData(newEmailCommitBean, createAdhocEmail);
		createAdhocEmail.setEmailId(osuemailRepository.adhocEmailSeq());
		sendEmailData(newEmailCommitBean, createAdhocEmail);
		final int result = osuemailRepository.sendOsuemail(createAdhocEmail);
		if (result == 1) {
			return osuemailRepository.saveAdhocEmailRecipients(createAdhocEmail.getEmailRecipientsList());
		}
		return result;
	}

	private void createAdhocEmailData(final NewEmailCommitBean newEmailCommitBean,
			final CreateAdhocEmail createAdhocEmail) {
		createAdhocEmail.setpWorkflowType(newEmailCommitBean.getWorkflowType());
		createAdhocEmail.setpWorkId(newEmailCommitBean.getWorkId());
		createAdhocEmail.setpOffenderBookId(newEmailCommitBean.getOffenderBookId());
		createAdhocEmail.setpEmailSubject(newEmailCommitBean.getEmailSubject());
		createAdhocEmail.setpEmailBody(newEmailCommitBean.getEmailBody());
		createAdhocEmail.setpEmailSender(newEmailCommitBean.getReturnAddress());
		createAdhocEmail.setpEmailFrom(newEmailCommitBean.getReturnAddress());
		createAdhocEmail.setCreateDatetime(newEmailCommitBean.getCreateDatetime());
		createAdhocEmail.setCreateUserId(newEmailCommitBean.getCreateUserId());
		createAdhocEmail.setModifyDatetime(newEmailCommitBean.getModifyDatetime());
		createAdhocEmail.setModifyUserId(newEmailCommitBean.getModifyUserId());
	}

	private void sendEmailData(final NewEmailCommitBean newEmailCommitBean, final CreateAdhocEmail createAdhocEmail) {
		final List<EmailRecipients> emailRecipientsList = new ArrayList<>();
		if (newEmailCommitBean != null && newEmailCommitBean.getEmailRecipientsList() != null
				&& !newEmailCommitBean.getEmailRecipientsList().isEmpty()) {
			for (final EmailRecipients emailRecipients : newEmailCommitBean.getEmailRecipientsList()) {
				if (emailRecipients != null && emailRecipients.getInternetAddressClass() != null
						&& emailRecipients.getInternetAddress() != null) {
					if (emailRecipients.getToAddress() != null && emailRecipients.getCcAddress() != null
							&& emailRecipients.getBccAddress() != null
							&& (emailRecipients.getToAddress().equals("Y") || emailRecipients.getCcAddress().equals("Y")
									|| emailRecipients.getBccAddress().equals("Y"))) {
						emailRecipients.setEmailId(createAdhocEmail.getEmailId());
						emailRecipients.setCreateDatetime(newEmailCommitBean.getCreateDatetime());
						emailRecipients.setCreateUserId(newEmailCommitBean.getCreateUserId());
						emailRecipients.setModifyDatetime(newEmailCommitBean.getModifyDatetime());
						emailRecipients.setModifyUserId(newEmailCommitBean.getModifyUserId());
						emailRecipientsList.add(emailRecipients);
					}
				}
			}
		}
		if (!emailRecipientsList.isEmpty()) {
			createAdhocEmail.setEmailRecipientsList(emailRecipientsList);
		}
	}

	/**
	 * Fetching the record from database table
	 */
	@Override
	public NewEmailCommitBean osuemailExecuteQuery(final NewEmailCommitBean newEmailCommitBean) {
		final List<EmailRecipients> emailRecipientsList = osuemailRepository
				.getEmailRecipients(newEmailCommitBean.getWorkId());
		if (!emailRecipientsList.isEmpty()) {
			emailRecipientData(newEmailCommitBean, emailRecipientsList);
		}

		return newEmailCommitBean;
	}

	private void emailRecipientData(final NewEmailCommitBean newEmailCommitBean,
			final List<EmailRecipients> emailRecipientsList) {
		EmailRecipients emailRecipients;
		final List<EmailRecipients> recipientsList = new ArrayList<>();
		for (final EmailRecipients eRecipients : emailRecipientsList) {
			if (eRecipients != null) {
				emailRecipients = new EmailRecipients();
				emailRecipients.setNbtEmailTo(false);
				emailRecipients.setNbtEmailCc(false);
				emailRecipients.setNbtEmailBcc(false);
				emailRecipients.setToAddress("N");
				emailRecipients.setCcAddress("N");
				emailRecipients.setBccAddress("N");
				emailRecipients.setOwnerId(eRecipients.getOwnerId());
				emailRecipients.setInternetAddressClass(eRecipients.getInternetAddressClass());
				emailRecipients.setInternetAddress(eRecipients.getInternetAddress());
				if (eRecipients.getInternetAddressClass() != null && !eRecipients.getInternetAddressClass().equals("")
						&& !eRecipients.getInternetAddressClass().equals(EMAIL_RTN)) {
					emailRecipients.setNbtEmailTo(true);
					emailRecipients.setToAddress("Y");
					eRecipients.setInternetAddressClass("EMAIL_TO");
				}
				if (eRecipients.getInternetAddressClass().equals(EMAIL_RTN)) {
					newEmailCommitBean.setReturnAddress(eRecipients.getInternetAddress());
				}
				if (!eRecipients.getInternetAddressClass().equals(EMAIL_RTN)) {
					recipientsList.add(emailRecipients);
				}
				if (eRecipients.getEmailBody() != null) {
					newEmailCommitBean.setEmailBody(eRecipients.getEmailBody());
				}
				if (eRecipients.getEmailSubject() != null) {
					newEmailCommitBean.setEmailSubject(eRecipients.getEmailSubject());
				}
			}
		}
		if (!recipientsList.isEmpty()) {
			newEmailCommitBean.setEmailRecipientsList(recipientsList);
		}
	}

	/**
	 * getting offender Name and id values
	 */
	@Override
	public String getOffendersDetails(final Integer offenderBookId) {
		final Offenders offenders = osuemailRepository.getOffendersDetails(offenderBookId);
		if (offenders != null) {
			return offenders.getFirstName();
		}
		return null;
	}

}