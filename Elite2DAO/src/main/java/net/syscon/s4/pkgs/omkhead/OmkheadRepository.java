package net.syscon.s4.pkgs.omkhead;

public interface OmkheadRepository {
	String inOutStatus(final Long offBookId);

	Integer offenderBookingsUpdateOut(final Long offBookId, final String userName);

	Integer offenderBookingsUpdateIn(final Long offBookId, final String userName);
}