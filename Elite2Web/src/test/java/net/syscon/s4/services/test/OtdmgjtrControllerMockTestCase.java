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
//import net.syscon.s4.services.config.EliteSpringConfig;
///**
// Class OtdmgjtrControllerMockTestCase
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { EliteSpringConfig.class })
//@WebAppConfiguration
//@WithMockCustomUser
//public class OtdmgjtrControllerMockTestCase  extends AbstractMockTestCase {
//
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void glTransactionsExecuteQueryTestMethod() {
////	try {
//// ResultActions resultActions = this.mockMvc
////		.perform(post("/otdmgjtrGlTxnExecuteQuery").contentType(MediaType.APPLICATION_JSON)
////		.content().accept(MediaType.APPLICATION_JSON_UTF8))
////		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
////			.andDo(print());
////			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
////		} catch (Exception e) {
////			logger.error("", e);
////		}
//		}
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void glTransactionsCommitTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/otdmgjtrGlTxnCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void glTransactionsRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/otdmgjtrCgfkGlTxnPayeeCorporateIdRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void glTransactionsRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/otdmgjtrCgfkGlTxn1AccountCodeRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void glTransactionsRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/otdmgjtrCgfkGlTxnAccountCodeRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void glTransactionsRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/otdmgjtrCgfkGlTxnPayeePersonIdRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void glTransactionsExecuteQueryTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/otdmgjtrGlTxn1ExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void glTransactionsCommitTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/otdmgjtrGlTxn1Commit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//public otdmgjtrController contactController() {
//return new otdmgjtrController();
//}
//}
//}