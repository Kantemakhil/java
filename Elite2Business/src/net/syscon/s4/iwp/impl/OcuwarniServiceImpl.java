package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.offenderissuestracking.beans.VStaffLocationRoles;
import net.syscon.s4.iwp.OcuwarniRepository;
import net.syscon.s4.iwp.OcuwarniService;

/**
 * Class OcuwarniServiceImpl
 */
@Service
public class OcuwarniServiceImpl extends BaseBusiness implements OcuwarniService {

	@Autowired
	private OcuwarniRepository ocuwarniRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderCaseNotes> offCaseNotesExecuteQuery(final OffenderCaseNotes searchRecord) {
		final List<OffenderCaseNotes> offenderCaseNotesList = ocuwarniRepository.offCaseNotesExecuteQuery(searchRecord);
		if (offenderCaseNotesList != null && !offenderCaseNotesList.isEmpty()) {
			for (final OffenderCaseNotes offenderCaseNotes : offenderCaseNotesList) {
				if (Optional.ofNullable(offenderCaseNotes).isPresent()) {
					offenderCaseNotes.setpNbtNoteSourceCodeDesc(
							ocuwarniRepository.offCaseNotesExecutePostQuery(offenderCaseNotes));
				}
			}
		}
		return offenderCaseNotesList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgConSubTypeRecordGroup() {
		return ocuwarniRepository.rgConSubTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VStaffLocationRoles> rgStaffNameRecordGroup(final Long offenderBookId, final String caseLoadId,
			final String agyLocId) {
		return ocuwarniRepository.rgStaffNameRecordGroup(offenderBookId, caseLoadId, agyLocId);

	}

}