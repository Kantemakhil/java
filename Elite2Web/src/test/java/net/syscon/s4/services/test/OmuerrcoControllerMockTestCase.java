package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapesCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OmuerrcoControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OmuerrcoControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderEscapesExecuteQueryTestMethod() {
		try {
			final OffenderEscapes offEscBean = new OffenderEscapes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offEscBean);
			ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/omuerrco/offEscExecuteQuery", offEscBean).contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderEscapesCommitTestMethod() {
		try {
			final OffenderEscapesCommitBean offEscBean = new OffenderEscapesCommitBean();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offEscBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/omuerrco/offEscCommit", offEscBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void cgfkOffEscSecurityBreachRecordGroupTestMethod() {
		try {
			final ReferenceCodes refBean = new ReferenceCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(refBean);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuerrco/cgfkOffEscSecurityBreachCRecordGroup", refBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void cgfkOffEscArrestAgyCodeRecordGroupTestMethod() {
		try {
			final ReferenceCodes refBean = new ReferenceCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(refBean);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuerrco/cgfkOffEscArrestAgyCodeRecordGroup", refBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}