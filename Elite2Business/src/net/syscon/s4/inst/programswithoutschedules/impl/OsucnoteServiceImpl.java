package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.core.impl.EliteDateRepositoryImpl;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.inst.programswithoutschedules.OsucnoteRepository;
import net.syscon.s4.inst.programswithoutschedules.OsucnoteService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;

/**
 * Class OsucnoteServiceImpl */
@Service
public class OsucnoteServiceImpl extends BaseBusiness implements OsucnoteService{

	@Autowired
	private OsucnoteRepository osucnoteRepository;
	@Autowired
	private TagWorkflowService tagWorkflowService;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private Omss40Service omss40Service;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public String getDisplayAuto(final String offenderBookId)  {
		return osucnoteRepository.getDisplayAuto(offenderBookId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 *@throws SQLException
	 */
	public List<ReferenceCodes> rgWorksRecordGroup(final String caseLoadType)  {
		return osucnoteRepository.rgWorksRecordGroup(caseLoadType);
	}

	@Override
	public List<TagWorkflowBrowseQueue> submitAdhocWorkflow(final TagWorkflowBrowseQueue newMemoModel) {
		Date dueDate = null;
		final List<TagWorkflowBrowseQueue> finalList = new ArrayList<TagWorkflowBrowseQueue>();
		final String finalMsg = getSubString(newMemoModel.getFirstName(),newMemoModel.getMessageText());
		newMemoModel.setMessageText(newMemoModel.getMessageText());
		final Integer staffId = getStaffId(newMemoModel.getSenderId());
		newMemoModel.setStaffId(staffId);
		//final Date transDate = trunc(eliteDateService.getDBTime());
		final String transDate = omss40Service.getServerTime();
		try {
			dueDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(transDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newMemoModel.setDueDate(dueDate);

//		final String result = osucnoteRepository.submitAdhocWorkflow(newMemoModel);
		String result=tagWorkflowService.insertCaseNote(newMemoModel);

		newMemoModel.setFunctionType(result);
		finalList.add(newMemoModel);
		return finalList;
	}
	private Integer getStaffId(final String msgId) {
		return osucnoteRepository.getStaffId(msgId);
	}

	private String getSubString(final String offName,final String messageText) {

		return osucnoteRepository.getSubString(offName,messageText);
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, date.getHours());
			calender.set(Calendar.MINUTE, date.getMinutes());
			calender.set(Calendar.SECOND, date.getSeconds());
			calender.set(Calendar.MILLISECOND, 0);
			return calender.getTime();
		}
		return null;
	}


}