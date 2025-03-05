//package net.syscon.s4.services.test;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import java.math.BigDecimal;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.hamcrest.core.IsNull;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import net.sycon.s4.im.beans.TagSearchGetOffenderIdentifiers;
//import net.sycon.s4.im.beans.TagSearchGetOffenderRecords;
//import net.syscon.s4.common.SpringContextLocator;
//import net.syscon.s4.common.beans.Images;
//import net.syscon.s4.global.OsiosearController;
///**
// * @author Arkin Software Technologies 
// * @version 1.0 
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
//public class OcdxprogControllerMockTestCase  {
//	/**
//	* Logger object used to print the log in the file
//	*/
//	private static Logger logger = Logger.getLogger(ocdxprogControllerMockTestCase.class);
//
//private MockMvc mockMvc;
//
//@Autowired
//private WebApplicationContext ctx;
//
//@Before
//
//public void setup() {
//SpringContextLocator.setDataSourceDetails("testDatasource.xml");
//this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).dispatchOptions(true).build();
//
//}
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void offenderPrgObligationsExecuteQueryTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/ocdxprogOffPrgObligationsExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void offenderPrgObligationsCommitTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/ocdxprogOffPrgObligationsCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void offenderPrgObligationsRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdxprogRgAvailabilityCodeRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void offenderPrgObligationsRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdxprogRgProgramRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void offenderPrgObligationsRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdxprogRgEndReasonRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void offenderProgramProfilesExecuteQueryTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/ocdxprogOffProgramProfilesExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void offenderProgramProfilesCommitTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/ocdxprogOffProgramProfilesCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//@Configuration
//@EnableWebMvc
//public static class BeanConfiguration {
//@Bean
//
//public ocdxprogController contactController() {
//return new ocdxprogController();
//}
//}
//}