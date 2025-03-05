package net.syscon.s4.pkgs.transfer_booking_oms;

import net.syscon.s4.im.beans.Offenders;

public interface TransferBookingOmsService {

	public void transferBookingRecords(Long pMergeTxnId, Offenders fromOffenders, Offenders toOffenders, String user);

}
