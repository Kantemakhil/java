package net.syscon.s4.pkgs.oms_trigger_objects;

import net.syscon.s4.im.beans.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

public interface OmsTriggerObjectsRepository {
	Integer createItemTransaction(OffenderPptyItemTxns offenderPptyItemTxns);
	Integer createContainerTransaction(OffenderPptyConTxns offenderPptyConTxns);
	Integer deleteItemTransaction(OffenderPptyItemTxns offenderPptyItemTxns);
	Integer changeItemsAgencyLocation(OffenderPptyItems offenderPptyItems);
	Integer updateItemTransaction(OffenderPptyItemTxns offenderPptyItemTxns);
}
