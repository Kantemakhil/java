package net.syscon.s4.pkgs.tag_person_search.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.syscon.s4.im.beans.PersonProfiles;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.tag_person_search.TagPersonSearchRepository;
import net.syscon.s4.pkgs.tag_person_search.TagPersonSearchService;

/*
 * Below comments are copied from package tag_person_search
 * ======================================================================================
   v_version CONSTANT VARCHAR2(50) := '11.2.1.0   18-JUN-2014';
-- ======================================================================================
MODIFICATION HISTORY
 --------------------------------------------------------------------------------------
 Person      Date         Version   Comments
 ---------   -----------  --------- ---------------------------------------------------
 Kumar          18-JUN-2014  11.2.1.0  Tag11g - Amended insert_person_profile_types to stop duplicates.
 Vikas Grover 13-APR-2007  2.2.1.1  TAG 10gR2 : get_person_employment_address, get_person_emp_address_details changed according to new datamodel 
 Vikas Grover 23-FEB-2007  2.2.1.0  TAG 10gR2 : Title column Removed, not required for Product
 		 					 		  	 Ext_No column is added in function get_person_emp_address_details 
										 and get_default_staff_person 
 GJC         14-Oct-2006  2.2       Remove DBMS_OUTPUT calls
 Claus       14-Dec-2005  2.1       D# 47. Added getnextempseq and getnextidseg
 Surya       07-Sep-2005  2.0       Initial Draft.
*/
@Service
public class TagPersonSearchServiceImpl implements TagPersonSearchService {
	@Autowired
	private TagPersonSearchRepository tagPersonSearchRepository;

	private static Logger logger = LogManager.getLogger(TagPersonSearchServiceImpl.class.getName());

	private static final String S = "S";
	private static final String P = "P";
	private static final String I = "I";
	private static final String N = "N";

	@Override
	@Transactional
	public List<PersonProfiles> insertPersonProfileTypes(final Long personId, final String userName) {
		final List<PersonProfiles> returnList = new ArrayList<PersonProfiles>();
		try {
			// person_profiles Insert Operation 419
			tagPersonSearchRepository.insertPersonProfileTypes(personId, userName);
		} catch (Exception e) {
			logger.error("insertPersonProfileTypes : ", e);
		}
		return returnList;
	}

	/*
	 * This methos is used to get_partial_soundex_persons
	 * get_partial_soundex_persons Procedure
	 */
	@Override
	public List<Persons> getPartialSoundexPersons(final Persons persons) {

		List<Persons> returnList = new ArrayList<Persons>();
		Date[] lvFrToDateArry = new Date[2];

		if (S.equals(persons.getSearchType())) {
			if (persons.getBirthYear() != null) {
				persons.setBirthRange(persons.getBirthRange() != null ? persons.getBirthRange() : 2);

				// get_date_range 211
				lvFrToDateArry = getDateRange(persons.getBirthYear(), persons.getBirthRange());
				persons.setLvFromDate(lvFrToDateArry[0]);
				persons.setLvToDate(lvFrToDateArry[1]);
			}
			// This methos is used to get persons list
			returnList = tagPersonSearchRepository.pResultSet(persons);
		} else if (P.equals(persons.getSearchType())) {
			// This methos is used to get persons list
			returnList = tagPersonSearchRepository.pResultSetOne(persons);
		}
		return returnList;
	}

	@Override
	public Date[] getDateRange(final Integer birthYear, final Integer birthRange) {
		Date[] retArray = new Date[2];
		Date frmDate = null;
		Date toDate = null;
		String frmStrg = null;
		String toStrg = null;
		final Calendar calendar = Calendar.getInstance();

		try {
			if (birthYear != null && birthRange != null) {
				if (birthYear != null && birthRange != null) {
					frmStrg = "01-01-".concat(birthYear != null ? birthYear.toString() : "1850");
					frmDate = new SimpleDateFormat("dd-MM-yyyy").parse(frmStrg);
					calendar.setTime(frmDate);
					calendar.add(Calendar.MONTH, -(birthRange * 12));
					retArray[0] = calendar.getTime();

					toStrg = "31-12-".concat(birthYear != null ? birthYear.toString() : "2150");
					toDate = new SimpleDateFormat("dd-MM-yyyy").parse(toStrg);
					calendar.setTime(toDate);
					calendar.add(Calendar.MONTH, (birthRange * 12));
					retArray[1] = calendar.getTime();
				}
			}
		} catch (Exception e) {
			logger.error("getDateRange", e);
		}
		return retArray;
	}

	@Override
	public List<Persons> getPersons(final Persons persons) throws Exception {
		List<Persons> returnList = new ArrayList<Persons>();
		Date[] lvFrToDateArry = new Date[2];

		if (N.equals(persons.getSearchType()) || S.equals(persons.getSearchType())
				|| P.equals(persons.getSearchType())) {
			if (persons.getBirthYear() != null) {
				persons.setBirthRange(persons.getBirthRange() != null ? persons.getBirthRange() : 2);
				// get_date_range 80
				lvFrToDateArry = getDateRange(persons.getBirthYear(), persons.getBirthRange());
				persons.setLvFromDate(lvFrToDateArry[0]);
				persons.setLvToDate(lvFrToDateArry[1]);
			}
			// p_result_set 87
			returnList = tagPersonSearchRepository.pResultSetOneFP(persons);
		} else if (I.equals(persons.getSearchType())) {
			if (persons.getPersonId() != null && persons.getIdentifierType() == null
					&& persons.getIdentifier() == null) {
				// p_result_set 120
				returnList = tagPersonSearchRepository.pResultSetTwo(persons.getPersonId());

			} else if (persons.getPersonId() == null && persons.getIdentifierType() != null
					&& persons.getIdentifier() != null) {
				// p_result_set 140
				returnList = tagPersonSearchRepository.pResultSetThree(persons.getIdentifierType(),
						persons.getIdentifier());

			} else if (persons.getPersonId() != null && persons.getIdentifierType() != null
					&& persons.getIdentifier() != null) {
				// p_result_set 163
				returnList = tagPersonSearchRepository.pResultSetFour(persons.getIdentifierType(),
						persons.getIdentifier());
			}
		} else {
			throw new Exception("Please choose a valid search type.");
		}
		return returnList;
	}

	@Override
	public Long getNextIdSeq(final Long pPersonId) {
		return tagPersonSearchRepository.getNextIdSeq(pPersonId);
	}

	@Override
	public Long getNextEmpSeq(final Long pPersonId) {
		return tagPersonSearchRepository.getNextEmpSeq(pPersonId);
	}

}