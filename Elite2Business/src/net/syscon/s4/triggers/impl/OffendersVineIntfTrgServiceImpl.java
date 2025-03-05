package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.AnAudit;
import net.syscon.s4.pkgs.MeAudit;
import net.syscon.s4.pkgs.OrAudit;
import net.syscon.s4.triggers.OffendersVineIntfTrRepository;
import net.syscon.s4.triggers.OffendersVineIntfTrgService;
/* =========================================================
   Below comments are copied from OFFENDERS_VINE_INTF_TRG Trigger
========================================================= */
/*
MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
--------------------
Person      Date        Comments
---------   ---------   ------------------------------------------
EDWARD      10-DEC-2008 Initial version.
Edward      06-JAN-2009 Use tag_error package for exception handling.
*/
@Service
public class OffendersVineIntfTrgServiceImpl extends BaseBusiness implements OffendersVineIntfTrgService {

	@Autowired
	private OffendersVineIntfTrRepository repository;

	@Override
	public void offendersVineIntfTrg(final List<Offenders> offenderList, final String operation) {
		BigDecimal vRootOffId = null;
		Long vOffId = null;
		BigDecimal vOldRootOffId;
		OffenderBookings vBookAnRec = null;
		OffenderBookings vBookOrRec = null;

		if (operation.equals("INSERT")) {
			for (Offenders data : offenderList) {
				vRootOffId = data.getRootOffenderId();
				vOffId = data.getOffenderId();
				vBookAnRec = repository.curActBookAn(vRootOffId, vOffId);
				if (vBookAnRec != null) {
					AnAudit insertAnAudit = new AnAudit();
					insertAnAudit.setActionType("A");
					insertAnAudit.setAgyLocId(vBookAnRec.getAgyLocId());
					insertAnAudit.setOffenderIdDisplay(data.getOffenderIdDisplay());
					insertAnAudit.setBookingNo(vBookAnRec.getBookingNo());
					insertAnAudit.setFirstName(data.getFirstName());
					insertAnAudit.setLastName(data.getLastName());
					insertAnAudit.setMiddleName(data.getMiddleName());
					insertAnAudit.setSuffix(data.getSuffix());
					insertAnAudit.setBirthDate(data.getBirthDate());
					insertAnAudit.setOffenderId(data.getOffenderId());
					insertAnAudit.setOffenderBookId(vBookAnRec.getOffenderBookId());
					insertAnAudit.setCreateUserId(data.getCreateUserId());
					repository.insertAnAudit(insertAnAudit);
				}

			}
		} else if (operation.equals("UPDATE")) {
			for (Offenders data : offenderList) {
				Offenders obj = repository.offendersExecuteQuery(data.getOffenderId());
				if (!equals(data.getOffenderIdDisplay(),obj.getOffenderIdDisplay())
						|| (!(data.getFirstName() != null ? data.getFirstName() : ' ')
								.equals((obj.getFirstName() != null ? obj.getFirstName() : ' ')))
						|| (!(data.getMiddleName() != null ? data.getMiddleName() : ' ')
								.equals((obj.getMiddleName() != null ? obj.getMiddleName() : ' ')))
						|| (!data.getLastName().equals(obj.getLastName()))
						|| (data.getBirthDate().compareTo(obj.getBirthDate()) != 0)
						|| (!(data.getRaceCode() != null ? data.getRaceCode() : ' ')
								.equals((obj.getRaceCode() != null ? obj.getRaceCode() : ' ')))
						|| (!data.getSexCode().equals(obj.getSexCode()))
						&& data.getRootOffenderId() == obj.getRootOffenderId()) {
					vOffId = data.getOffenderId();
					vRootOffId = data.getRootOffenderId();
					vBookOrRec = repository.curActBookOr(vOffId);
					if (vBookOrRec != null) {
						OrAudit updateOrAudit = new OrAudit();
						updateOrAudit.setActionType("U");
						updateOrAudit.setAgyLocId(vBookOrRec.getAgyLocId());
						updateOrAudit.setOffenderIdDisplay(data.getOffenderIdDisplay());
						updateOrAudit.setBookingNo(vBookOrRec.getBookingNo());
						updateOrAudit.setBookingBeginDate(vBookOrRec.getBookingBeginDate());
						updateOrAudit.setFirstName(data.getFirstName());
						updateOrAudit.setMiddleName(data.getMiddleName());
						updateOrAudit.setLastName(data.getLastName());
						updateOrAudit.setBirthDate(data.getBirthDate());
						updateOrAudit.setRaceCode(data.getRaceCode());
						updateOrAudit.setSexCode(data.getSexCode());
						updateOrAudit.setOffenderId(data.getOffenderId());
						updateOrAudit.setModifyUserId(data.getModifyUserId());
						updateOrAudit.setGenderCode(data.getGenderCode());
						Integer result = repository.updateOrAudit(updateOrAudit);
						if (result != 1) {
							OrAudit insertOrAudit = new OrAudit();
							insertOrAudit.setActionType("U");
							insertOrAudit.setAgyLocId(vBookOrRec.getAgyLocId());
							insertOrAudit.setOffenderIdDisplay(data.getOffenderIdDisplay());
							insertOrAudit.setBookingNo(vBookOrRec.getBookingNo());
							insertOrAudit.setBookingBeginDate(vBookOrRec.getBookingBeginDate());
							insertOrAudit.setFirstName(data.getFirstName());
							insertOrAudit.setMiddleName(data.getMiddleName());
							insertOrAudit.setLastName(data.getLastName());
							insertOrAudit.setBirthDate(data.getBirthDate());
							insertOrAudit.setRaceCode(data.getRaceCode());
							insertOrAudit.setSexCode(data.getSexCode());
							insertOrAudit.setOffenderId(data.getOffenderId());
							insertOrAudit.setOffenderBookId(vBookOrRec.getOffenderBookId());
							insertOrAudit.setCreateUserId(data.getCreateUserId());
							insertOrAudit.setGenderCode(data.getGenderCode());
							repository.insertOrAudit(insertOrAudit);
						}
					} else {
						vBookAnRec = repository.curActBookAn(vRootOffId, vOffId);
						if (vBookAnRec != null) {
							AnAudit updateAnAudit = new AnAudit();
							updateAnAudit.setActionType("U");
							updateAnAudit.setAgyLocId(vBookAnRec.getAgyLocId());
							updateAnAudit.setOffenderIdDisplay(data.getOffenderIdDisplay());
							updateAnAudit.setBookingNo(vBookAnRec.getBookingNo());
							updateAnAudit.setFirstName(data.getFirstName());
							updateAnAudit.setLastName(data.getLastName());
							updateAnAudit.setMiddleName(data.getMiddleName());
							updateAnAudit.setSuffix(data.getSuffix());
							updateAnAudit.setBirthDate(data.getBirthDate());
							updateAnAudit.setOffenderBookId(vBookAnRec.getOffenderBookId());
							updateAnAudit.setOffenderId(data.getOffenderId());
							updateAnAudit.setModifyUserId(data.getModifyUserId());
							Integer count = repository.updateAnAudit(updateAnAudit);
							if (count != 1) {
								AnAudit insertAnAudit = new AnAudit();
								insertAnAudit.setActionType("U");
								insertAnAudit.setAgyLocId(vBookAnRec.getAgyLocId());
								insertAnAudit.setOffenderIdDisplay(data.getOffenderIdDisplay());
								insertAnAudit.setBookingNo(vBookAnRec.getBookingNo());
								insertAnAudit.setFirstName(data.getFirstName());
								insertAnAudit.setLastName(data.getLastName());
								insertAnAudit.setMiddleName(data.getMiddleName());
								insertAnAudit.setSuffix(data.getSuffix());
								insertAnAudit.setBirthDate(data.getBirthDate());
								insertAnAudit.setOffenderId(data.getOffenderId());
								insertAnAudit.setOffenderBookId(vBookAnRec.getOffenderBookId());
								insertAnAudit.setCreateUserId(data.getCreateUserId());
								repository.insertAnAudit(insertAnAudit);

							}

						}

					}

				} else if (data.getRootOffenderId() != obj.getRootOffenderId()) {
					vOffId = data.getOffenderId();
					vRootOffId = data.getRootOffenderId();
					vOldRootOffId = obj.getRootOffenderId();
					vBookAnRec = repository.curActBookAnMe(vRootOffId, vOffId, vOldRootOffId);
					if (vBookAnRec != null) {
						AnAudit updateAnAudit = new AnAudit();
						updateAnAudit.setActionType("U");
						updateAnAudit.setAgyLocId(vBookAnRec.getAgyLocId());
						updateAnAudit.setOffenderIdDisplay(data.getOffenderIdDisplay());
						updateAnAudit.setBookingNo(vBookAnRec.getBookingNo());
						updateAnAudit.setFirstName(obj.getFirstName());
						updateAnAudit.setLastName(obj.getLastName());
						updateAnAudit.setMiddleName(obj.getMiddleName());
						updateAnAudit.setSuffix(obj.getSuffix());
						updateAnAudit.setBirthDate(obj.getBirthDate());
						updateAnAudit.setOffenderBookId(vBookAnRec.getOffenderBookId());
						updateAnAudit.setOffenderId(obj.getOffenderId());
						updateAnAudit.setModifyUserId(data.getModifyUserId());
						Integer count = repository.updateAnAudit(updateAnAudit);

						if (count != 1) {
							AnAudit insertAnAudit = new AnAudit();
							insertAnAudit.setActionType("U");
							insertAnAudit.setAgyLocId(vBookAnRec.getAgyLocId());
							insertAnAudit.setOffenderIdDisplay(data.getOffenderIdDisplay());
							insertAnAudit.setBookingNo(vBookAnRec.getBookingNo());
							insertAnAudit.setFirstName(obj.getFirstName());
							insertAnAudit.setLastName(obj.getLastName());
							insertAnAudit.setMiddleName(obj.getMiddleName());
							insertAnAudit.setSuffix(obj.getSuffix());
							insertAnAudit.setBirthDate(obj.getBirthDate());
							insertAnAudit.setOffenderId(obj.getOffenderId());
							insertAnAudit.setOffenderBookId(vBookAnRec.getOffenderBookId());
							insertAnAudit.setCreateUserId(data.getCreateUserId());
							repository.insertAnAudit(insertAnAudit);
						}

						if (repository.curMeExist(obj.getOffenderIdDisplay()) > 0) {
							MeAudit insertMeAudit = new MeAudit();
							insertMeAudit.setActionType("A");
							insertMeAudit.setAgyLocId(vBookAnRec.getAgyLocId());
							insertMeAudit.setOldOffenderIdDisplay(obj.getOffenderIdDisplay());
							insertMeAudit.setNewOffenderIdDisplay(data.getOffenderIdDisplay());
							insertMeAudit.setNewBookingNo(vBookAnRec.getBookingNo());
							insertMeAudit.setCreateUserId(data.getCreateUserId());
							repository.insertMeAudit(insertMeAudit);

						}

					}
				}

			}
		} else if (operation.equals("DELETE")) {
			for (Offenders data : offenderList) {
				Offenders obj = repository.offendersExecuteQuery(data.getOffenderId());
				vRootOffId = obj.getRootOffenderId();
				vOffId = obj.getOffenderId();
				vBookAnRec = repository.curActBookAn(vRootOffId, vOffId);
				if (vBookAnRec != null) {
					AnAudit insertAnAudit = new AnAudit();
					insertAnAudit.setActionType("D");
					insertAnAudit.setAgyLocId(vBookAnRec.getAgyLocId());
					insertAnAudit.setOffenderIdDisplay(obj.getOffenderIdDisplay());
					insertAnAudit.setBookingNo(vBookAnRec.getBookingNo());
					insertAnAudit.setFirstName(obj.getFirstName());
					insertAnAudit.setLastName(obj.getLastName());
					insertAnAudit.setMiddleName(obj.getMiddleName());
					insertAnAudit.setSuffix(obj.getSuffix());
					insertAnAudit.setBirthDate(obj.getBirthDate());
					insertAnAudit.setOffenderId(obj.getOffenderId());
					insertAnAudit.setOffenderBookId(vBookAnRec.getOffenderBookId());
					insertAnAudit.setCreateUserId(data.getCreateUserId());
					repository.insertAnAudit(insertAnAudit);

				}
			}
		}
	

	}

	private boolean equals(String oldValue,String newValue) {
		if(oldValue == null && newValue == null) 
			return true;
		else if((oldValue != null && newValue == null) || (oldValue == null && newValue != null)) 
			return false;
		else {
			return newValue.equalsIgnoreCase(oldValue);
		}
	}
	}


