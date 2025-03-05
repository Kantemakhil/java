package net.syscon.s4.cm.teamsworkflow.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import net.syscon.s4.common.TaskDescriptionParser;

public class OcdalertTaskDescriptioParser extends TaskDescriptionParser {
	
	@Override
	public Map<String, Object> parseFormData(String description) {
		Map<String, Object> formDataMap = new LinkedHashMap<>();
		if (description != null) {
			try {
				JSONObject obj = new JSONObject(description);
				if (obj.getJSONObject("uniqueId") != null) {
					JSONObject uniqueId = obj.getJSONObject("uniqueId");
					if (uniqueId.has("offenderBookId") && uniqueId.get("offenderBookId") != null) {
						formDataMap.put("offenderBookId", uniqueId.getInt("offenderBookId"));
					}
					if (uniqueId.has("offenderId") && uniqueId.get("offenderId") != null) {
						formDataMap.put("offenderId", uniqueId.getInt("offenderId"));
					}
				}
				if (obj.getJSONObject("formData") != null) {
					JSONObject formData = obj.getJSONObject("formData");
					if (formData.has("id") && formData.get("id") != null) {
						formDataMap.put("id", formData.getInt("id"));
					}
					if (formData.has("eventDate") && formData.getString("eventDate") != null) {
						formDataMap.put("eventDate", formData.getString("eventDate"));
					}

					if (formData.has("comment") && formData.getString("comment") != null) {
						formDataMap.put("comment", formData.getString("comment"));
					}

					if (formData.has("dueDate") && formData.getString("dueDate") != null) {
						formDataMap.put("dueDate", formData.getString("dueDate"));
					}

				}

			} catch (Exception e) {
				System.out.println(e);

			}

		}
		return formDataMap;
	}

}
