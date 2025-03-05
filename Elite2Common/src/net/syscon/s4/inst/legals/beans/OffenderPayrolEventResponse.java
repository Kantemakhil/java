package net.syscon.s4.inst.legals.beans;

import java.util.HashMap;
import java.util.Map;

public class OffenderPayrolEventResponse {
    private Long paroleEventId;
    private String eventResponse;
    Map<String, String> adjustmentResponse;

    public String getEventResponse() {
        return eventResponse;
    }

    public void setEventResponse(String eventResponse) {
        this.eventResponse = eventResponse;
    }

    public void addAdjustmentResponse(String key,String responseMessage) {
        if(null==adjustmentResponse) {
            this.adjustmentResponse = new HashMap<>();
        }
        this.adjustmentResponse.put(key, responseMessage);
    }

    public Map<String, String> getAdjustmentResponse() {
        return adjustmentResponse;
    }

    public Long getParoleEventId() {
        return paroleEventId;
    }

    public void setParoleEventId(Long paroleEventId) {
        this.paroleEventId = paroleEventId;
    }
    

}
