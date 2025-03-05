package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffenderBookingsT4Repository;
import net.syscon.s4.triggers.OffenderBookingsT4Service;
@Service
public class OffenderBookingsT4ServiceImpl implements OffenderBookingsT4Service {
	@Autowired
	private OffenderBookingsT4Repository offenderBookingsT4Repository;
	@Override
	public void offenderBookingsT4(Long offenderBookId, String userId) {
		offenderBookingsT4Repository.offenderBookingsT4Insert(offenderBookId,userId);
		
	}

}
