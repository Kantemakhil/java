package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.services.config.EliteSpringConfig;

/***
 * 
 * class OculcaseControllerMockTestCase
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OculcaseControllerMockTestCase extends AbstractMockTestCase{

	
	@Test
	public void  populateLinkCaseTestMethod() {
		try {
			final CourtCases courtCase = new CourtCases();
			courtCase.setcaseId(Long.valueOf(12477));
			courtCase.setCase_Seq(Long.valueOf(2));
			courtCase.setOffenderBookId(Long.valueOf(19644));
			courtCase.setAgy_loc_id("SDC");
			courtCase.setBeginDate(new Date());
			courtCase.setCaseInfoNumber("19655");
			courtCase.setCaseInfoPrefix("DOCKET");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(courtCase);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oculcase/populateLinkCase")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Syscon District Court")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void  populateLinkLovTypeTestMethod() {
		try {
			final CourtCases courtCase = new CourtCases();
			courtCase.setCase_Seq(Long.valueOf(2));
			courtCase.setOffenderBookId(Long.valueOf(19644));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(courtCase);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oculcase/populateLinkLovType?case_Seq=2+&OffenderBookID=19644")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Syscon District Court")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void  populateSelectHearingTestMethod() {
		try {
			final CourtEvent courtEvent = new CourtEvent();
			courtEvent.setCaseId(Long.valueOf(12054));
			courtEvent.setOffenderBookId(Long.valueOf(19644));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(courtEvent);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oculcase/populateSelectHearing?caseId=77933+&OffenderBookID=19644")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Syscon District Court")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void  chkSentencesTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oculcase/chkSentences?offenderBookId=19644+&caseId=12057").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(" ")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void  linkCaseTestMethod() {
		try {
			final CourtEvent courtEvent = new CourtEvent();
			courtEvent.setCaseId(Long.valueOf(12477));
			courtEvent.setCaseIdl(Integer.valueOf(12477));
			courtEvent.setOffenderBookId(Long.valueOf(19644));
			courtEvent.setEventId(Long.valueOf(126196));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(courtEvent);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oculcase/linkCase")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is(" ")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void  unLinkCaseTestMethod() {
		try {
			final CourtEvent courtEvent = new CourtEvent();
			courtEvent.setCaseId(Long.valueOf(12477));
			courtEvent.setCaseIdl(Integer.valueOf(12477));
			courtEvent.setOffenderBookId(Long.valueOf(19644));
			courtEvent.setEventId(Long.valueOf(126196));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(courtEvent);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oculcase/unLinkCase")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is(" ")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
