package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.OffenderEducations;
import net.syscon.s4.common.beans.OffenderEducationsCommitBean;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 Class OidcnoteControllerMockTestCase
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OidcnoteControllerMockTestCase extends AbstractMockTestCase {

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderCaseNotesExecuteQueryTestMethod() {
	try {
		final OffenderCaseNotes offenderCaseNotes = new OffenderCaseNotes();
		offenderCaseNotes.setOffenderBookId(19084);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(offenderCaseNotes);

			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidcnote/offNotesExecuteQuery", offenderCaseNotes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId", is(19084)));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderCaseNotesCommitTestMethod() {
		final OffenderCaseNotes offenderCaseNotes = new OffenderCaseNotes();
		final List<OffenderCaseNotes> insertList = new ArrayList<OffenderCaseNotes>();
		final List<OffenderCaseNotes> updateList = new ArrayList<OffenderCaseNotes>();
		final List<OffenderCaseNotes> deleteList = new ArrayList<OffenderCaseNotes>();
		offenderCaseNotes.setOffenderBookId(22112);
		offenderCaseNotes.setContactDate(new Date());
		offenderCaseNotes.setContactTime(new Date());		
		offenderCaseNotes.setCaseNoteType("CNOTE");
		offenderCaseNotes.setCaseNoteSubType("COURT");
		offenderCaseNotes.setStaffId(1);
		offenderCaseNotes.setCaseNoteText("KIRAN TEST BY MOCKTEST");
		offenderCaseNotes.setAmendmentFlag("N");
		offenderCaseNotes.setIwpFlag("N");
		offenderCaseNotes.setCheckBox1("N");
		offenderCaseNotes.setCheckBox2("N");
		offenderCaseNotes.setCheckBox3("N");
		offenderCaseNotes.setCheckBox4("N");
		offenderCaseNotes.setCheckBox5("N");
		offenderCaseNotes.setEventId(null);
		offenderCaseNotes.setCaseNoteId(null);
		offenderCaseNotes.setNoteSourceCode("INST");
		offenderCaseNotes.setCreateDatetime(new Date());

		insertList.add(offenderCaseNotes);
		updateList.add(offenderCaseNotes);
		deleteList.add(offenderCaseNotes);
		final OffenderCaseNotesCommitBean offenderCaseNotesCommitBean = new OffenderCaseNotesCommitBean();
		offenderCaseNotesCommitBean.setInsertList(insertList);
		offenderCaseNotesCommitBean.setUpdateList(updateList);
		offenderCaseNotesCommitBean.setDeleteList(deleteList);
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidcnote/offNotesCommit", offenderCaseNotesCommitBean)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(offenderCaseNotesCommitBean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidcnoteRgnoteSourceRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidcnote/rgnoteSourceRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Community")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	 @Test
	public void oidcnoteRgCasenoteTypeRecordGroupTestMethod() {
		 try {
				ResultActions resultActions = this.mockMvc
						.perform(get("/api/oidcnote/rgCasenoteTypeRecordGroup?caseloadType=INST").contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON_UTF8))
						.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
						.andDo(print());
				resultActions.andExpect(jsonPath("$[0].description", is("Alert")));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidcnoteRgCasenoteSubtypeRecordGroupTestMethod() {
		 try {
				ResultActions resultActions = this.mockMvc
						.perform(get("/api/oidcnote/rgCasenoteSubtypeRecordGroup?caseNoteType=ALERT").contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON_UTF8))
						.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
						.andDo(print());
				resultActions.andExpect(jsonPath("$[0].description", is("Admission")));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidcnoteRgStaffnameRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidcnote/rgStaffnameRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].staffId", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
}