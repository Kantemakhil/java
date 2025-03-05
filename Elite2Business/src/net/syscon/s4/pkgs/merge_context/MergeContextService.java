package net.syscon.s4.pkgs.merge_context;


import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;

public interface MergeContextService {

	public Offenders setFromOffVals(Long fromRootOffenderId, Long offenderId, String offenderIdDisplay, Long fromOffenderBookId,
			String bookingStatus,String activeFlag,String communityActiveFlag,String agyLocId,String lastName,String firstName);
		
	public Offenders setToOffVals(Long toRootOffenderId, Long offenderId, String offenderIdDisplay, Long toOffenderBookId,
			String bookingStatus,String activeFlag,String communityActiveFlag,String agyLocId,String lastName,String firstName);

	public Offenders setOffenderRec(Offenders offenders, OffenderBookings offenderBookings ,Long rootOffenderId, Long offenderId, String offenderIdDisplay, Long offenderBookId,
			String bookingStatus,String activeFlag,String communityActiveFlag,String agyLocId,String lastName,String firstName);
	
}
