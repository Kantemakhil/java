INSERT INTO json_spec
(id, spec_key, json_specs, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 33, 'TRANSFORM_OFFENDER_PAY_DETAILS','[
  {
    "operation": "shift",
    "spec": {
      "OffenderDetails": {
        "payRunId": "payRunDetails.payRunId",
        "payRunNarration": "payRunDetails.payRunNarration",
        "startdate": "payRunDetails.payRunStart",
        "endDate": "payRunDetails.payRunEnd",
        "payDetailList": "payRunDetails.payDetails"
      },
      "messageId": {
        "*": {
          "messageid": ["astriaMessageHeader.correlationId", "astriaMessageHeader.messageId"]
        }
      },
      "messageTimeStamp": {
        "*": {
          "datetime": "astriaMessageHeader.messageCreationTimestamp"
        }
      }
    }
  },
  {
    "operation": "modify-default-beta",
    "spec": {
      "payRunDetails": {
        "payRunStart": null
      }
    }
  },
  {
    "operation": "default",
    "spec": {
      "astriaMessageHeader": {
        "sourceSystem": "Elite",
        "messageType": "Prisoner Pay",
        "messageTypeCode": "PrPay",
        "messageTypeVersion": "1.0.0"
      },
	   "payRunDetails": {
        "payRunStart": null
      }
    }
  },
  {
    "operation": "sort"
  }

]', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
WHERE NOT EXISTS (SELECT 1 FROM json_spec WHERE spec_key='TRANSFORM_OFFENDER_PAY_DETAILS'); 
