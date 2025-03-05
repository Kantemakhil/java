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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class OsipsearControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void tagPersonSearchgetPersonsExecuteQueryTestMethod() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearPersonsExecuteQuery", tagSearchGetOffenderRecords)
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
	public void tagPersonSearchgetPersonsRecordGroupTestMethod() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(get("/osipsearRgLanguageCodeRecordGroup", tagSearchGetOffenderRecords)
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
	public void osipsearRgMaritalStatusRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/osipsearRgMaritalStatusRecordGroup").contentType(MediaType.APPLICATION_JSON)
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
	public void osipsearRgSexCodeRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/osipsear/RgSexCodeRecordGroup").contentType(MediaType.APPLICATION_JSON)
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
	public void osipsearRgSearchTypeRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/osipsearRgSearchTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
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
	public void osipsearRgIdentifierTypeRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/osipsearRgIdentifierTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
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
	public void vPersonAddressesExecuteQueryTestMethod() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearPerAddrExecuteQuery", tagSearchGetOffenderRecords)
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
	public void personIdentifiersExecuteQueryTestMethod() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearPerIdentExecuteQuery", tagSearchGetOffenderRecords)
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
	public void personIdentifiersCommitTestMethod() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearPerIdentCommit", tagSearchGetOffenderRecords)
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
	public void imagesExecuteQueryTestMethod() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearImageExecuteQuery", tagSearchGetOffenderRecords)
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
	public void personEmploymentsExecuteQueryTestMethod() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearPerEmpExecuteQuery", tagSearchGetOffenderRecords)
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
	public void personEmploymentsCommitTestMethod() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearPerEmpCommit", tagSearchGetOffenderRecords)
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
	public void osipsearPsPersonNameExecuteQuery() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearPsPersonNameExecuteQuery", tagSearchGetOffenderRecords)
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
	public void osipsearPsPersonNameCommit() {
		try {
			Object tagSearchGetOffenderRecords = new Object();
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/osipsearPsPersonNameCommit", tagSearchGetOffenderRecords)
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