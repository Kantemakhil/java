package net.syscon.s4.triggers.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.pkgs.oms_property.OmsPropertyService;
import net.syscon.s4.pkgs.oms_trigger_objects.DeleteItemTransactionService;
import net.syscon.s4.triggers.OffenderPptyItemsT1Repository;
import net.syscon.s4.triggers.OffenderPptyItemsT1Service;
import net.syscon.s4.triggers.OmtocontService;

/*
============================================================
    Below comments are copied from OFFENDER_PPTY_ITEMS_T1 Trigger
============================================================
  MODIFICATION HISTORY
   Person       Date      version      Comments
   -----------------------------------------------------------------------------------------
   Rana      31-AUG-2011     2.4       HPQC#9966,ensure that disposed_to_offender_flag is being correctly populated.
   Steve     19-OCT-2009     2.3       Ensure that disposed_to_person_id and disposed_to_offender_flag is
                                       being correctly populated.
   Niko      20-MAR-2008     2.2       Modified when inserting logic
   Niko      18-MAR-2008     2.1       Added 3 new colunms when insert into offender_ppty_item_txns
   Surya     13/12/2005      2.0       Re-created for the data model changes.
*/
@Service
public class OffenderPptyItemsT1ServiceImpl implements OffenderPptyItemsT1Service {

	private static Logger logger = LogManager.getLogger(OffenderPptyItemsT1ServiceImpl.class.getName());

	private static final String REGISTERED = "REGISTERED";
	private static final String INSERTING = "INSERTING";
	private static final String UPDATING = "UPDATING";
	private static final String STORED = "STORED";
	private static final String TRANSIT = "TRANSIT";
	private static final String CELL = "CELL";
	private static final String OUT = "OUT";
	private static final String DISPOSED = "DISPOSED";
	private static final String DELETING = "DELETING";
	private static final String MISSING = "MISSING";

	@Autowired
	private OmsPropertyService omsPropertyService;

	@Autowired
	private DeleteItemTransactionService deleteItemTransactionService;

	@Autowired
	private OffenderPptyItemsT1Repository offenderPptyItemsT1Repository;

	@Autowired
	private OmtocontService omtocontService;

	@Override
	public void offenderPptyItemsT1(final OffenderPptyItems old, final OffenderPptyItems newbean,
			final String operation) {
		String vStatusCode = null, lAgyLocId = null, vDisposedToOffenderFlag = null;
		Integer vDisposedToPersonId = null, vPropertyContainerId = null, vDisposedToCorpId = null;
		String userId=null;
		if (operation.equalsIgnoreCase(INSERTING)) {
			vStatusCode = REGISTERED;
			lAgyLocId = newbean.getAgyLocId();
		} // if
		else if (operation.equalsIgnoreCase(UPDATING) && !newbean.getStatusCode().equals(REGISTERED)) {
			vStatusCode = newbean.getStatusCode();
			OffenderPptyContainers bean = new OffenderPptyContainers();
			if (old.getStatusCode().equals(STORED) && (newbean.getStatusCode().equals(CELL)
					|| newbean.getStatusCode().equals(OUT) || newbean.getStatusCode().equals(MISSING)
					|| newbean.getStatusCode().equals(DISPOSED) || newbean.getStatusCode().equals(STORED))) {
				// setting Data
				bean.setAgyLocId(newbean.getAgyLocId());
				bean.setPropertyContainerId(old.getPropertyContainerId());
				bean.setCommentText(newbean.getCommentText());
				bean.setInternalLocationId(newbean.getInternalLocationId());
				bean.setTrnFromAgyLocId(newbean.getTrnFromAgyLocId());
				bean.setCreateUserId(newbean.getCreateUserId());
				bean.setModifyUserId(newbean.getCreateUserId());
				// bean.setTrnToAgyLocId(newbean.getTrnFromAgyLocId());
				// Trigger Call OmtocontTrg
				omtocontService.omtocontTrg(bean, UPDATING);
				omsPropertyService.unsealContainer(old.getPropertyContainerId());
			}
			if (!old.getStatusCode().equals(TRANSIT) && newbean.getStatusCode().equals(STORED)) {
				// setting Data
				bean.setAgyLocId(newbean.getAgyLocId());
				bean.setPropertyContainerId(old.getPropertyContainerId());
				bean.setCommentText(newbean.getCommentText());
				bean.setInternalLocationId(newbean.getInternalLocationId());
				bean.setTrnFromAgyLocId(newbean.getTrnFromAgyLocId());
				bean.setCreateUserId(newbean.getCreateUserId());
				bean.setModifyUserId(newbean.getCreateUserId());
				// bean.setTrnToAgyLocId(newbean.getTrnFromAgyLocId());
				// Trigger Call OmtocontTrg
				omtocontService.omtocontTrg(bean, UPDATING);
				omsPropertyService.unsealContainer(newbean.getPropertyContainerId());
			}

			if (newbean.getStatusCode().equals(OUT))
				lAgyLocId = omsPropertyService.getUsrAgyLoc();
			else if (old.getStatusCode().equals(OUT)
					&& (newbean.getStatusCode().equals(CELL) || newbean.getStatusCode().equals(MISSING)))
				lAgyLocId = omsPropertyService.getUsrAgyLoc();
			else if (old.getStatusCode().equals(OUT) && (newbean.getStatusCode().equals(STORED)))
				lAgyLocId = omsPropertyService.getConAgyLoc(newbean.getPropertyContainerId());
			else if (newbean.getStatusCode().equals(DISPOSED)) {
				lAgyLocId = newbean.getAgyLocId();
				vDisposedToPersonId = newbean.getDisposedToPersonId();
				vDisposedToOffenderFlag = newbean.getDisposedToOffenderFlag();
			} else
				lAgyLocId = newbean.getAgyLocId();

			if (!newbean.getStatusCode().equals(STORED) || !newbean.getStatusCode().equals(TRANSIT))
				vPropertyContainerId = null;
			else
				vPropertyContainerId = newbean.getPropertyContainerId();

			if (old.getStatusCode().equals(DISPOSED)) {
				vDisposedToCorpId = null;
				vDisposedToPersonId = null;
				vDisposedToOffenderFlag = null;
			}

		} // else if
		else if (operation.equalsIgnoreCase(DELETING)) {
			if (old.getStatusCode().equals(REGISTERED))
				// Deleting From DeleteItemTransaction Trigger
				deleteItemTransactionService.deleteItemTransaction(old.getOffenderBookId(), old.getPropertyItemSeq(),
						REGISTERED,newbean.getModifyUserId());

		} // else if

		if (operation.equalsIgnoreCase(INSERTING)
				|| (operation.equalsIgnoreCase(UPDATING) && !newbean.getStatusCode().equals(REGISTERED))) {
			// Inserting into OFFENDER_PPTY_ITEM_TXNS table
			if(newbean.getActionCode() == null) {
				newbean.setActionCode(newbean.getStatusCode());
			}
			if(newbean.getModifyUserId()!=null) {
				userId=newbean.getModifyUserId();
			}else {
				userId=newbean.getCreateUserId();
			}
			offenderPptyItemsT1Repository.insert(old, newbean, vPropertyContainerId, lAgyLocId, vDisposedToPersonId,userId);
		}
		else if (operation.equalsIgnoreCase(UPDATING) && newbean.getStatusCode().equals(REGISTERED)
				&& newbean.getCommentText() != null)
			// Deleting From DeleteItemTransaction Trigger
			deleteItemTransactionService.updateItemTransaction(newbean);

	}// method

}// class
