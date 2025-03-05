package net.syscon.s4.pkgs.oms_trigger_objects.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.pkgs.oms_trigger_objects.DeleteItemTransactionRepository;
import net.syscon.s4.pkgs.oms_trigger_objects.DeleteItemTransactionService;

@Service
public class DeleteItemTransactionServiceImpl implements DeleteItemTransactionService {
	
	@Autowired
	private DeleteItemTransactionRepository deleteItemTransactionRepository; 
    //below method is used to delete item transaction from OFFENDER_PPTY_ITEM_TXNS table
	@Override
	public void deleteItemTransaction(final Integer offenderBookId,final  Integer propertyItemSeq,final String pToStatusCode,String modifyUserId) {
		deleteItemTransactionRepository.offenderPptyItemTxnsDelete(offenderBookId, propertyItemSeq, pToStatusCode,modifyUserId);
	}
	
	@Override
	public void updateItemTransaction(final OffenderPptyItems items) {
		deleteItemTransactionRepository.offenderPptyItemTxnsUpdate(items);
	}
}
