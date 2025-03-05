package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffenderBookingsT8Repository;
import net.syscon.s4.triggers.OffenderBookingsT8Service;
@Service
public class OffenderBookingsT8ServiceImpl implements OffenderBookingsT8Service {

	@Autowired
	private OffenderBookingsT8Repository offenderBookingsT8Repository;
	@Override
	public void offenderBookingsT8(final Long rootOffenderId,String createUserId) {
		Long lOffenderBookIdOld=null;
		Integer trgEventIdSeq=null;
		List<Long> list=offenderBookingsT8Repository.lCheckExistCur(rootOffenderId);
		for(Long l:list) {
			lOffenderBookIdOld=l;
			
			trgEventIdSeq=offenderBookingsT8Repository.trgEventIdSeq();
			offenderBookingsT8Repository.offenderAssocPEventNotifsInsert(lOffenderBookIdOld, trgEventIdSeq,createUserId);
		}

	}
}
