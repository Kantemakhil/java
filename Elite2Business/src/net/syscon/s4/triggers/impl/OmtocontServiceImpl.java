package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.pkgs.oms_property.OmsPropertyService;
import net.syscon.s4.pkgs.oms_trigger_objects.OmsTriggerObjectsService;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.triggers.OmtocontRepository;
import net.syscon.s4.triggers.OmtocontService;
/*
============================================================
 Below comments are copied from OMTOCONT Trigger
============================================================
  MODIFICATION HISTORY
  Person       Date      version      Comments
-----------  ---------   -----------  -------------------------------
  Niko       20/03/2008  2.3          Modified internal location id with TR
  Rajshree   21/07/2006  2.2          Commented comment_text = null,Defect ID 3503
  Surya      13/12/2005  2.1          Replaced the references of property
                                      storage id with the internal location id.
*/
@Service
public class OmtocontServiceImpl implements OmtocontService {
	private final Logger logger = LogManager.getLogger(OmtocontServiceImpl.class);
	@Autowired
	OmtocontRepository omtocontRepository;
	@Autowired
	OmsPropertyService omsPropertyService;
	@Autowired
	OmsUtilsService omsUtilsService;
	@Autowired
	OmsTriggerObjectsService omsTriggerObjectsService;

	@Override
	public Integer omtocontTrg(final OffenderPptyContainers offenPptyConta, final String sqlOperation) {
		Integer result = 0;
		String lActionCode = null;
		String lAgyLocId = null;
		String lFromAgyLocId = null;
		String lToAgyLocId = null;
		String lSealMark = null;
		final OffenderPptyConTxns offenderPptyConTxns = new OffenderPptyConTxns();
		// Getting old record from OFFENDER_PPTY_CONTAINERS table
		final OffenderPptyContainers old = omtocontRepository
				.getOffenderPptyContainers(offenPptyConta.getPropertyContainerId());
		try {
//			-- check if container should be unsealed
			if ((old != null && old.getSealMark() != null) && offenPptyConta != null && 
					(offenPptyConta.getSealMark() == null || !offenPptyConta.getSealMark().equals(old.getSealMark()))) {
					
					
//					(Optional.ofNullable(old).isPresent() && Optional.ofNullable(old.getSealMark()).isPresent())
//					&& Optional.ofNullable(offenPptyConta).isPresent() && (Optional.ofNullable(offenPptyConta.getSealMark()).isPresent()
//							|| !(offenPptyConta.getSealMark().equalsIgnoreCase(old.getSealMark())))) {
				offenderPptyConTxns.setPropertyContainerId(offenPptyConta.getPropertyContainerId());
				offenderPptyConTxns.setActionCode("UNSEAL");
				offenderPptyConTxns.setAgyLocId(old.getAgyLocId());
				offenderPptyConTxns.setSealMark(null);
				offenderPptyConTxns.setCommentText(offenPptyConta.getCommentText());
				offenderPptyConTxns.setInternalLocationId(offenPptyConta.getInternalLocationId());
				offenderPptyConTxns.setTrnFromAgyLocId(null);
				offenderPptyConTxns.setTrnToAgyLocId(null);
				offenderPptyConTxns.setCreateUserId(offenPptyConta.getCreateUserId());
				// inserting data into OFFENDER_PPTY_CON_TXNS table
				result = omsTriggerObjectsService.createContainerTransaction(offenderPptyConTxns);
			}
			if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
				lActionCode = "CREATE";
				lAgyLocId = offenPptyConta.getAgyLocId();
				lFromAgyLocId = null;
				lToAgyLocId = null;
				lSealMark = null;
			} else {
				lSealMark = offenPptyConta.getSealMark();
				if (Optional.ofNullable(old).isPresent() && Optional.ofNullable(old.getTrnToAgyLocId()).isPresent()) {
					if ("OUT".equals(old.getAgyLocId()) && offenPptyConta.getAgyLocId().equalsIgnoreCase(old.getTrnToAgyLocId())) {
						lActionCode = "TR-IN";
						lAgyLocId = offenPptyConta.getAgyLocId();
						lFromAgyLocId = old.getTrnFromAgyLocId();
						lToAgyLocId = old.getTrnToAgyLocId();
						offenPptyConta.setInternalLocationId(omsPropertyService.getTranRoomStorageId(lToAgyLocId));
						offenPptyConta.setTrnFromAgyLocId(null);
						offenPptyConta.setTrnToAgyLocId(null);
					}
				} else if (Optional.ofNullable(offenPptyConta).isPresent()
						&& Optional.ofNullable(offenPptyConta.getTrnToAgyLocId()).isPresent()) {
					lActionCode = "TR-OUT";
					lAgyLocId = old.getAgyLocId();
					lFromAgyLocId = old.getAgyLocId();
					lToAgyLocId = offenPptyConta.getTrnToAgyLocId();
					offenPptyConta.setTrnFromAgyLocId(old.getAgyLocId());
					offenPptyConta.setAgyLocId("OUT");
					offenPptyConta.setInternalLocationId(null);
				}
			}

			if (Optional.ofNullable(lActionCode).isPresent()) {
				offenderPptyConTxns.setPropertyContainerId(offenPptyConta.getPropertyContainerId());
				offenderPptyConTxns.setActionCode(lActionCode);
				offenderPptyConTxns.setAgyLocId(lAgyLocId);
				offenderPptyConTxns.setSealMark(lSealMark);
				offenderPptyConTxns.setCommentText(offenPptyConta.getCommentText());
				offenderPptyConTxns.setInternalLocationId(offenPptyConta.getInternalLocationId());
				offenderPptyConTxns.setTrnFromAgyLocId(lFromAgyLocId);
				offenderPptyConTxns.setTrnToAgyLocId(lToAgyLocId);
				offenderPptyConTxns.setCreateUserId(offenPptyConta.getCreateUserId());
				// inserting data into OFFENDER_PPTY_CON_TXNS table
				result = omsTriggerObjectsService.createContainerTransaction(offenderPptyConTxns);
			}
//			 -- check if container should be sealed
			if ((offenPptyConta != null && offenPptyConta.getSealMark() != null) && old != null && 
					(old.getSealMark() == null || !offenPptyConta.getSealMark().equals(old.getSealMark()))) {
//			if ((Optional.ofNullable(old).isPresent() && Optional.ofNullable(old.getSealMark()).isPresent())
//					&& Optional.ofNullable(offenPptyConta).isPresent() && (Optional.ofNullable(offenPptyConta.getSealMark()).isPresent()
//							|| !(offenPptyConta.getSealMark().equalsIgnoreCase(old.getSealMark())))) {
				offenderPptyConTxns.setPropertyContainerId(offenPptyConta.getPropertyContainerId());
				offenderPptyConTxns.setActionCode("SEAL");
				offenderPptyConTxns.setAgyLocId(old.getAgyLocId());
				offenderPptyConTxns.setSealMark(offenPptyConta.getSealMark());
				offenderPptyConTxns.setCommentText(offenPptyConta.getCommentText());
				offenderPptyConTxns.setInternalLocationId(offenPptyConta.getInternalLocationId());
				offenderPptyConTxns.setTrnFromAgyLocId(null);
				offenderPptyConTxns.setTrnToAgyLocId(null);
				offenderPptyConTxns.setCreateUserId(offenPptyConta.getCreateUserId());
				// inserting data into OFFENDER_PPTY_CON_TXNS table
				result = omsTriggerObjectsService.createContainerTransaction(offenderPptyConTxns);

			}
		} catch (final Exception e) {
			logger.error("omtocontTrg", e);
			result = 0;
			final String msg = omsUtilsService.displayUserMessage(10, e.getMessage());
			logger.info(msg);
		}
		return result;
	}

}
