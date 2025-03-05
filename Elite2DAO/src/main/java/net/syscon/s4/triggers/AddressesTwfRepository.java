package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.Addresses;

public interface AddressesTwfRepository {

	Addresses getAddressesObject(Long addressId);

	BigDecimal getOffenderBookId(BigDecimal ownerId);

	BigDecimal getAddressId(BigDecimal offenderBookId);

	Integer getDistinct1(BigDecimal offenderBookId, String modifyUserId, String createUserId);
}
