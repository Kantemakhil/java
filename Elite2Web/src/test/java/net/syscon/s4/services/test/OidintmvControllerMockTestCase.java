package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OidintmvControllerMockTestCase extends AbstractMockTestCase  {
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OidintmvControllerMockTestCase.class);


	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidintmvOffBlkExecuteQuery() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/oidintmvOffBlkExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvOffBlkCommit() {
		final VOffenderAllSchedulesCommitBean voffCommitbean = new VOffenderAllSchedulesCommitBean();
		final List<VOffenderAllSchedules> insertList = new ArrayList<>();
		final VOffenderAllSchedules off1 = new VOffenderAllSchedules();
		off1.setOffenderId(BigDecimal.valueOf(1020650));
		off1.setOffenderBookId(BigDecimal.valueOf(20284));
		off1.setToInternalLocationId(BigDecimal.valueOf(5362));
		insertList.add(off1);
		voffCommitbean.setUpdateList(insertList);
	try {
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(voffCommitbean);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidintmv/offBlkCommit", voffCommitbean).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvRgEstablishmentRecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgEstablishmentRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvRgMovementTypeRecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgMovementTypeRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvRgFromHlocLevel1RecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgFromHlocLevel1RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvRgFromHlocLevel2RecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgFromHlocLevel2RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvRgFromHlocLevel3RecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgFromHlocLevel3RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvRgFromIlocLevel1RecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgFromIlocLevel1RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvRgFromIlocLevel2RecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgFromIlocLevel2RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void getoidintmvRgFromIlocLevel3RecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgFromIlocLevel3RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void getSchedulesRecordGroupMethod() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgToIlocLevel1RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void schedulesRecordGroupTestMethod() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgToIlocLevel2RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void getSchedulesRecordGroupTestMe() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgToIlocLevel3RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void getSchedulesRecordGroupTestMethod() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgSchTypeRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void oidintmvRgSchReasonRecordGroup() {
	try {
		final BedAssignmentHistories tagSearchGetOffenderRecords = new BedAssignmentHistories();
		tagSearchGetOffenderRecords.setOffenderBookId(18733);
		tagSearchGetOffenderRecords.setBedAssignSeq(6);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
		ResultActions resultActions = this.mockMvc
		.perform(get("/oidintmvRgSchReasonRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
}