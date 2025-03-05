UPDATE json_spec
SET  json_specs='[
  {
    "operation": "shift",
    "spec": {
      "OffenderDetails": {
        "payRunId": "payRunDetails.payRunId",
        "payRunNarration": "payRunDetails.payRunNarration",
        "startdate": "payRunDetails.payRunStart",
        "endDate": "payRunDetails.payRunEnd",
        "payDetailList": {
          "*": {
            "offenderIdDisplay": "payRunDetails.payDetails[#2].eliteOffenderId",
            "payActualAmount": "payRunDetails.payDetails[#2].amount"
          }
        }
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
    "operation": "modify-overwrite-beta",
    "spec": {
      "payRunDetails": {
        "payRunId": "=toString",
        "payDetails": {
          "*": {
            "amount": "=toDouble"
          }
        }
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

]
',modify_datetime = current_timestamp,
modify_user_id = 'OMS_OWNER'
WHERE spec_key='TRANSFORM_OFFENDER_PAY_DETAILS' 
