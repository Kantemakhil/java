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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * class OidescapControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidescapControllerMockTestCase  extends AbstractMockTestCase {
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderEscapesExecuteQueryTestMethod() {
	try {
		final OffenderEscapes offenderEscapesRecords = new OffenderEscapes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(offenderEscapesRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidescap/offEscExecuteQuery", offenderEscapesRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
          resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderEscapesCommitTestMethod() {
	try {
		final OffenderEscapes offenderEscapesRecords = new OffenderEscapes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(offenderEscapesRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidescap/offEscCommit", offenderEscapesRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderEscapesRecordGroupTestMethod() {
	try {
		final AgencyLocations bean = new AgencyLocations();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(bean);
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oidescap/cgfkOffEscEscapeAgyLocIdRecordGroup", bean).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderEscapesEscortRecordGroupTestMethod() {
	try {
		final ReferenceCodes bean = new ReferenceCodes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(bean);
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oidescap/cgfkOffEscEscapeEscortCodRecordGroup", bean).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderEscapesCircumstanRecordGroupTestMethod() {
	try {
		final ReferenceCodes bean = new ReferenceCodes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(bean);
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oidescap/cgfkOffEscEscapeCircumstanRecordGroup", bean).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void systemProfilesExecuteQueryTestMethod() {
	try {
		final SystemProfiles bean = new SystemProfiles();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(bean);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidescap/sysPflExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
}