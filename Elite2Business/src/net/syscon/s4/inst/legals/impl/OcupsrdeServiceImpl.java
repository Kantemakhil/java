package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.inst.legals.OcupsrdeRepository;
import net.syscon.s4.inst.legals.OcupsrdeService;
import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.CourtReport;
import net.syscon.s4.inst.legals.beans.CourtReportCommitBean;
import net.syscon.s4.inst.legals.beans.OrderType;
import net.syscon.s4.inst.legals.beans.StaffDetails;
import net.syscon.s4.inst.legals.beans.TeamResponsible;
import net.syscon.s4.inst.programswithoutschedules.OsuntaskService;


@Service
public class OcupsrdeServiceImpl implements OcupsrdeService {
	
	@Autowired
	OcupsrdeRepository ocupsrdeRepository;
	
	@Autowired
	private OsuntaskService osuntaskService;

	@Override
	public List<CourtReport> populateReport(Integer eventId) {
		List<CourtReport> reportList = new ArrayList<CourtReport>();
		reportList = ocupsrdeRepository.populateReport(eventId);
		for(int i=0;i<reportList.size();i++) {
			CourtReport courtReport = reportList.get(i);
			if(courtReport.getReportType()!=null) {
				courtReport.setNbtReportType(String.valueOf(setPostQueryReportType(courtReport.getReportType())));
			}
			if(courtReport.getAgyLocId()!=null) {
				courtReport.setNbtagyLocDesc(String.valueOf(setPostQueryAgyDesc(courtReport.getAgyLocId())));
			}
			if(courtReport.getStatus()!=null) {
				courtReport.setNbtStatus(String.valueOf(setPostQueryReportstatus(courtReport.getStatus())));
			}
		}
		return reportList;
	}
	
	public Object setPostQueryReportType(String reportType) {
		return ocupsrdeRepository.setPostQueryReportType(reportType);
		}
	
	public Object setPostQueryAgyDesc(String agyLocId) {
		return ocupsrdeRepository.setPostQueryAgyDesc(agyLocId);
		}
	
	public Object setPostQueryCourtSeriousnessLevel(String courtSeriousnessLevel) {
		return ocupsrdeRepository.setPostQueryCourtSeriousnessLevel(courtSeriousnessLevel);
		}
	
	public Object setPostQueryReportstatus(String reportStatus) {
		return ocupsrdeRepository.setPostQueryReportstatus(reportStatus);
		}
	
	@Override
	public List<OrderType> populateReportType() {
		List<OrderType> resultList = new ArrayList<OrderType>();
		resultList = ocupsrdeRepository.populateReportType();
		return resultList;
	}

	@Override
	public List<AssignReport> populateAssignReport(CourtReport courtReport) {
		List<AssignReport> assignReportList = new ArrayList<AssignReport>();
		assignReportList = ocupsrdeRepository.populateAssignReport(courtReport);
		return assignReportList;
	}
	
	@Override
	public List<OrderType> populateFunctionType() {
		List<OrderType> resultList = new ArrayList<OrderType>();
		resultList = ocupsrdeRepository.populateFunctionType();
		return resultList;
	}
	
	@Override
	public List<OrderType> populateAreaType() {
		List<OrderType> resultList = new ArrayList<OrderType>();
		resultList = ocupsrdeRepository.populateAreaType();
		return resultList;
	}
	
	@Override
	public List<OrderType> populateArea(String areaType) {
		List<OrderType> resultList = new ArrayList<OrderType>();
		resultList = ocupsrdeRepository.populateArea(areaType);
		return resultList;
	}
	
	@Override
	public List<TeamResponsible> populateTeamResponsible(String areaCode, String functionType) {
		List<TeamResponsible> resultList = new ArrayList<TeamResponsible>();
		resultList = ocupsrdeRepository.populateTeamResponsible(areaCode,functionType);
		return resultList;
	}
	
	@Override
	public List<StaffDetails> populateStaffDetails(Integer teamId) {
		List<StaffDetails> resultList = new ArrayList<StaffDetails>();
		resultList = ocupsrdeRepository.populateStaffDetails(teamId);
		return resultList;
	}
	
	@Override
	public List<TeamResponsible> populateStaffLovDetails(Integer teamId) {
		List<TeamResponsible> resultList = new ArrayList<TeamResponsible>();
		resultList = ocupsrdeRepository.populateStaffLovDetails(teamId);
		return resultList;
	}

	@Override
	public Integer insertUpdateDeleteCourtReport(CourtReportCommitBean courtReportCommitBean) {
		int liReturn = 0;
		if (courtReportCommitBean.getInsertList() != null && courtReportCommitBean.getInsertList().size() > 0) {
			List<CourtReport> recordSavingObject = new ArrayList<>();
			if (courtReportCommitBean.getInsertList().size() > 0) {
				for (int i = 0; i < courtReportCommitBean.getInsertList().size(); i++) {
					final CourtReport courtReport = courtReportCommitBean.getInsertList().get(i);
					recordSavingObject.clear();
					recordSavingObject.add(courtReport);
					
					liReturn = insertNewCourtReport(recordSavingObject);
					}
			}
		}
		return liReturn;
		}
	
	public Integer generateOrderId(){
		return ocupsrdeRepository.getPreInsertOrderId();
	}
	
	public String getStatusDesc(String reportStatus ){
		return ocupsrdeRepository.getStatusDesc(reportStatus);
	}
	
	@Transactional
	private Integer insertNewCourtReport(List<CourtReport> insertedRecord) {
		int liReturn = 0;
		liReturn = ocupsrdeRepository.insertNewCourtReport(insertedRecord);
		return liReturn;
	}

	@Override
	public Boolean isReportExist(CourtReport courtReport) {
		boolean isExist=false;
		isExist=ocupsrdeRepository.isReportExist(courtReport);
		return isExist;
	}
	
	@Transactional
	public Integer create_team_assign_hty_new(CourtReport courtReportAssign)  {
		int liReturn = 0;
		List<TagWorkflowBrowseQueue> taskList=new ArrayList<>();
		TagWorkflowBrowseQueue taskDetails=new TagWorkflowBrowseQueue();
		if(courtReportAssign.getOffenderBookId() != null) {
			taskDetails.setOffenderBookId(courtReportAssign.getOffenderBookId().intValue());
		}
		taskDetails.setOffenderId(courtReportAssign.getOffenderId());
		taskDetails.setTeamId(courtReportAssign.getTeamId());
		taskDetails.setTeamMemberId(courtReportAssign.getTeamMemberId());
		taskDetails.setStaffId(ocupsrdeRepository.getStaffId(courtReportAssign.getTeamMemberId()));
		taskDetails.setDateRequested(courtReportAssign.getDateRequested());
		taskDetails.setEventDate(courtReportAssign.getDateRequested());
		taskDetails.setAssignmentDate(courtReportAssign.getAssignmentDate());
		taskDetails.setDueDate(courtReportAssign.getDueDate());
		taskDetails.setOrderId(courtReportAssign.getOrderId());
		taskDetails.setFunctionType(courtReportAssign.getFunctionType());
		taskDetails.setWorkType(courtReportAssign.getWorkType());
		taskDetails.setWorkId(ocupsrdeRepository.getWorkId());
		taskDetails.setCreateUserId(courtReportAssign.getCreateUserId());
		taskDetails.setMessageText(courtReportAssign.getCommentText());
		
		taskDetails.setSourceName(courtReportAssign.getSourceName());
		taskList=osuntaskService.submitAdhocWorkflow(taskDetails);
		if(taskList!=null && !taskList.isEmpty() && taskList.get(0).getFunctionType()!=null) {
			liReturn=Integer.valueOf(taskList.get(0).getFunctionType());
		}
		
		//liReturn = ocupsrdeRepository.create_team_assign_hty_new(courtReportAssign);
		return liReturn;
	}
	
	
}
