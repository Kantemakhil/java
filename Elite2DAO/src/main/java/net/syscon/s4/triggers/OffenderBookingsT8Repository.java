package net.syscon.s4.triggers;

import java.util.List;

public interface OffenderBookingsT8Repository {
	List<Long> lCheckExistCur(final Long rootOffenderId);
	Integer trgEventIdSeq();
	Integer offenderAssocPEventNotifsInsert(final Long lOffenderBookIdOld,final Integer trgEventIdSeq,String createUserId );

}
