package net.syscon.s4.services.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * Class OidbutabControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidbutabControllerMockTestCase  {
	/**
	* Logger object used to print the log in the file
	*/
//	private static Logger logger = Logger.getLogger(OidbutabControllerMockTestCase.class);


	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
//	@Test
//	public void vHeaderBlockExecuteQueryTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/oidbutabVhbExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockCommitTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/oidbutabVhbCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/oidbutabRgInstitutionRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/oidbutabRgActiveAgencyRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/oidbutabRgDirectionRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/oidbutabRgLuLevel1RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/oidbutabRgLuLevel2RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/oidbutabRgLuLevel3RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/oidbutabRgCityRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
//	public void vHeaderBlockRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/oidbutabRgReasonRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}

}