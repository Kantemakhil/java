package net.syscon.s4.pkgs.oms_trigger_objects;

import net.syscon.s4.im.beans.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;

public interface OmsTriggerObjectsService {
	Integer createItemTransaction(OffenderPptyItemTxns offenderPptyItemTxns);
	Integer createContainerTransaction(OffenderPptyConTxns offenderPptyConTxns);

}
