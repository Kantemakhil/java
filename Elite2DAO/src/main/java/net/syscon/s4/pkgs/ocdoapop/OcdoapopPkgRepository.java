package net.syscon.s4.pkgs.ocdoapop;

import net.syscon.s4.im.beans.VAddresses;

public interface OcdoapopPkgRepository {

	VAddresses fetchVAddressRecord(final Long pAddressId);
}
