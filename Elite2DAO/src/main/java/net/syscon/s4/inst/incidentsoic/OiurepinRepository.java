package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetails;

public interface OiurepinRepository {

	List<ReportableIncedentDetails> getReportDetailsExecuteQuery(ReportableIncedentDetails searchBean);

    Integer reportableIncedentDetailsInsertData(List<ReportableIncedentDetails> insertList);

	Integer reportableIncedentDetailsUpdateData(List<ReportableIncedentDetails> updateList);

	Integer reportableIncedentDetailsDeleteData(List<ReportableIncedentDetails> deleteList);

	String getUserNameLog(String userNameLog);

}
