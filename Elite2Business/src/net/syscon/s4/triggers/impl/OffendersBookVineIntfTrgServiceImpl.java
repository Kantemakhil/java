package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.AnAudit;
import net.syscon.s4.pkgs.OrAudit;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgRepository;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

/* =========================================================
   Below comments are copied from OFFENDERS_VINE_INTF_TRG Trigger
========================================================= */
/*
MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
--------------------
Person      Date        Comments
---------   ---------   ------------------------------------------
EDWARD      10-DEC-2008 Initial version.
Edward      06-JAN-2009 Use tag_error package for exception handling.
*/
@Service
public class OffendersBookVineIntfTrgServiceImpl implements OffendersBookVineIntfTrgService {
	Logger logger = LogManager.getLogger(OffendersBookVineIntfTrgRepositoryImpl.class);
	@Autowired
	private OffendersBookVineIntfTrgRepository offendersBookVineIntfTrgRepository;

	@Transactional
	@Override
	public Integer OffendersBookVineIntfTrgTrigger(final OffenderBookings newOffe,String operation) {
		Integer result = 0;
		List<OrAudit> orAuditList = new ArrayList<OrAudit>();
		OrAudit orAudit = new OrAudit();
		final OffenderBookings oldOffe = offendersBookVineIntfTrgRepository.getOffenderBookings(newOffe.getOffenderBookId());
		final Offenders vOffRec = offendersBookVineIntfTrgRepository.curOff(oldOffe.getOffenderId());
		try {
		if(operation!=null && operation.equals(ApplicationConstants.INSERTING)){
			if ("Y".equals(newOffe.getActiveFlag())) {
				dataMapping(newOffe, orAudit, vOffRec);
				orAudit.setOffenderBookId(newOffe.getOffenderBookId());
				orAuditList.add(orAudit);
				result = offendersBookVineIntfTrgRepository.insert(orAuditList);
			}
		}
			
		else if(operation!=null && operation.equals(ApplicationConstants.UPDATING)) {
			if ((newOffe.getOffenderId() != oldOffe.getOffenderId() && "Y".equals(newOffe.getActiveFlag()))
					|| ("N".equals(oldOffe.getActiveFlag()) && "Y".equals(newOffe.getActiveFlag()))
					|| oldOffe.getActiveFlag() == null && "Y".equals(newOffe.getActiveFlag())) {
				orAudit = new OrAudit();
				orAuditList = new ArrayList<OrAudit>();
				dataMapping(newOffe, orAudit, vOffRec);
				orAudit.setActionType("U");
				orAuditList.add(orAudit);
				result = offendersBookVineIntfTrgRepository.update(orAuditList);
				if (newOffe.getOffenderId() != oldOffe.getOffenderId()) {
					final AnAudit anAudit = new AnAudit();
					final List<AnAudit> anAuditList = new ArrayList<AnAudit>();
					anAuditDataMapping(newOffe, anAudit, vOffRec);
					anAudit.setOffenderId(oldOffe.getOffenderId()!=null?oldOffe.getOffenderId().longValue():null);
					anAudit.setBookingNo(oldOffe.getBookingNo());
					anAuditList.add(anAudit);
					result = offendersBookVineIntfTrgRepository.anAuditUpdate(anAuditList);
				}
			}
		}
			
		else if(operation!=null && operation.equals(ApplicationConstants.DELETE)) {
			if ("Y".equals(oldOffe.getActiveFlag())) {
				final Offenders vOffRecDel = offendersBookVineIntfTrgRepository.curOff(oldOffe.getOffenderId());
				orAudit = new OrAudit();
				orAuditList = new ArrayList<OrAudit>();
				dataMapping(oldOffe, orAudit, vOffRecDel);
				orAudit.setActionType("D");
				orAudit.setOffenderBookId(newOffe.getOffenderBookId());
				orAuditList.add(orAudit);
				result = offendersBookVineIntfTrgRepository.delete(orAuditList);
			}
		}
			
		} catch (final Exception e) {
			result = 0;
			logger.error("OffendersBookVineIntfTrgTrigger", e);
		}
		return result;
	}

	private void dataMapping(final OffenderBookings newOffe, final OrAudit orAudit, final Offenders vOffRec) {
		orAudit.setActionType("A");
		orAudit.setAgyLocId(newOffe.getAgyLocId());
		orAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
		orAudit.setBookingNo(newOffe.getBookingNo());
		orAudit.setBookingBeginDate(newOffe.getBookingBeginDate());
		orAudit.setFirstName(vOffRec.getFirstName());
		orAudit.setMiddleName(vOffRec.getMiddleName());
		orAudit.setLastName(vOffRec.getLastName());
		orAudit.setBirthDate(vOffRec.getBirthDate());
		orAudit.setRaceCode(vOffRec.getRaceCode());
		orAudit.setSexCode(vOffRec.getSexCode());
		orAudit.setReleaseDate(null);
		orAudit.setRelReasonCode(null);
		if (newOffe.getOffenderId() != null) {
			orAudit.setOffenderId(newOffe.getOffenderId().longValue());
		}
		orAudit.setModifiedDate(new Date());
		orAudit.setOffenderBookId(newOffe.getOffenderBookId());
		orAudit.setCreateUserId(newOffe.getCreateUserId());
		orAudit.setModifyUserId(newOffe.getModifyUserId());
		orAudit.setCreateDatetime(new Date());
		orAudit.setModifyDatetime(new Date());
	}

	private void anAuditDataMapping(final OffenderBookings newOffe, final AnAudit orAudit, final Offenders vOffRec) {
		orAudit.setActionType("U");
		orAudit.setAgyLocId(newOffe.getAgyLocId());
		orAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
		orAudit.setBookingNo(newOffe.getBookingNo());
		orAudit.setFirstName(vOffRec.getFirstName());
		orAudit.setMiddleName(vOffRec.getMiddleName());
		orAudit.setLastName(vOffRec.getLastName());
		orAudit.setBirthDate(vOffRec.getBirthDate());
		if (newOffe.getOffenderId() != null) {
			orAudit.setOffenderId(newOffe.getOffenderId().longValue());
		}
		orAudit.setModifiedDate(new Date());
		orAudit.setOffenderBookId(newOffe.getOffenderBookId());
		orAudit.setCreateUserId(newOffe.getCreateUserId());
		orAudit.setModifyUserId(newOffe.getModifyUserId());
		orAudit.setCreateDatetime(new Date());
		orAudit.setModifyDatetime(new Date());
	}

}
