package net.syscon.s4.inst.correspondencetracking;

import java.util.List;

import net.syscon.s4.inst.correspondencetracking.beans.OffMailRestrictions;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLog;

public interface OidomailRepository {

	List<OffenderMailLog> mailLogExecuteQuery(Long offenderBookId);

	List<OffMailRestrictions> mailRestrictionExecuteQuery(Long offenderBookId);

	Integer offenderMailLogInsert(List<OffenderMailLog> insertList);

	Integer offenderMailLogInsertUpdate(List<OffenderMailLog> updateList);

	Integer offenderMailLogInsertDelete(List<OffenderMailLog> deleteList);

	Integer offMailRestrictionsInsert(List<OffMailRestrictions> insertList);

	Integer offMailRestrictionsUpdate(List<OffMailRestrictions> updateList);

	Integer offMailRestrictionsDelete(List<OffMailRestrictions> deleteList);

	Long getOffenderMailLogSequence(Long offenderBookId);

}
