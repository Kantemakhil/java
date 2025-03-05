package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetails;
import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetailsCommitBean;

public interface OiurepinService {

	List<ReportableIncedentDetails> getReportDetailsExecuteQuery(ReportableIncedentDetails searchBean);

	List<ReportableIncedentDetails> reportableIncedentDetailsCommit(ReportableIncedentDetailsCommitBean commitBean);

	String getUserNameLog(String userNameLog);

}
