package net.syscon.s4.pkgs.oms_trigger_objects;

import net.syscon.s4.inst.property.bean.OffenderPptyItems;

public interface DeleteItemTransactionService {
	
	void deleteItemTransaction(final Integer offenderBookId,final Integer propertyItemSeq,final String pToStatusCode,String modifyUserId);
	
	void updateItemTransaction(final OffenderPptyItems items);

}
