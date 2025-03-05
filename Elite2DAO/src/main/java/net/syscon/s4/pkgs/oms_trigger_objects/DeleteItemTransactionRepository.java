package net.syscon.s4.pkgs.oms_trigger_objects;

import net.syscon.s4.inst.property.bean.OffenderPptyItems;

public interface DeleteItemTransactionRepository {
	Integer offenderPptyItemTxnsDelete(final Integer offenderBookId,final Integer propertyItemSeq,final String pToStatusCode,String modifyUserId);
	
	Integer offenderPptyItemTxnsUpdate(final OffenderPptyItems item);

}
