package net.syscon.s4.pkgs.merge_context.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.merge_context.MergeContextService;

@Service
public class MergeContextServiceImpl implements MergeContextService {

	@Override
	public Offenders setFromOffVals(Long fromRootOffenderId, Long offenderId, String offenderIdDisplay,
			Long fromOffenderBookId, String bookingStatus, String activeFlag, String communityActiveFlag,
			String agyLocId, String lastName, String firstName) {

		Offenders fromOffenders = new Offenders();
		OffenderBookings fromOffenderBookings = new OffenderBookings();
		fromOffenders = setOffenderRec(fromOffenders, fromOffenderBookings, fromRootOffenderId, offenderId,
				offenderIdDisplay, fromOffenderBookId, bookingStatus, activeFlag, communityActiveFlag, agyLocId,
				lastName, firstName);

		return fromOffenders;
	}

	@Override
	public Offenders setToOffVals(Long toRootOffenderId, Long offenderId, String offenderIdDisplay,
			Long toOffenderBookId, String bookingStatus, String activeFlag, String communityActiveFlag, String agyLocId,
			String lastName, String firstName) {

		Offenders toOffenders = new Offenders();
		OffenderBookings toOffenderBookings = new OffenderBookings();
		toOffenders = setOffenderRec(toOffenders, toOffenderBookings, toRootOffenderId, offenderId, offenderIdDisplay,
				toOffenderBookId, bookingStatus, activeFlag, communityActiveFlag, agyLocId, lastName, firstName);

		return toOffenders;

	}

	@Override
	public Offenders setOffenderRec(Offenders offenders, OffenderBookings offenderBookings, Long rootOffenderId,
			Long offenderId, String offenderIdDisplay, Long offenderBookId, String bookingStatus, String activeFlag,
			String communityActiveFlag, String agyLocId, String lastName, String firstName) {
		
		Offenders offendersSettingRec = new Offenders();
		offendersSettingRec.setRootOffenderId(BigDecimal.valueOf(rootOffenderId));
		offendersSettingRec.setOffenderId(offenderId);
		offendersSettingRec.setOffenderIdDisplay(offenderIdDisplay);
		offendersSettingRec.setOffenderBookId(offenderBookId);
		offendersSettingRec.setBookingStatus(bookingStatus);
		offendersSettingRec.setActiveFlag(activeFlag);
		offendersSettingRec.setCommunityActiveFlag(communityActiveFlag);
		offendersSettingRec.setAgyLocId(agyLocId);
		offendersSettingRec.setLastName(lastName);
		offendersSettingRec.setFirstName(firstName);
		
		return offendersSettingRec;
	}

}
