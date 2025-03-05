package net.syscon.s4.inst.correspondencetracking;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.correspondencetracking.beans.OffMailRestrictions;
import net.syscon.s4.inst.correspondencetracking.beans.OffMailRestrictionsCommitBean;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLog;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLogCommitBean;
import net.syscon.s4.inst.correspondencetracking.beans.OidomailCommonCommitBean;

public interface OidomailService {

	List<ReferenceCodes> getContactTypeLov();
	
	List<OffenderMailLog> mailLogExecuteQuery(Long offenderBookId);

	List<OffMailRestrictions> mailRestrictionExecuteQuery(Long offenderBookId);

	Integer offenderMailLogsCommit(OffenderMailLogCommitBean commitBean);

	Integer mailLogAndResrtrictionsCommonSave(OidomailCommonCommitBean commitBean);

	Integer offMailRestrictionsCommit(OffMailRestrictionsCommitBean commitBean);

}
