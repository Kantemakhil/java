update json_spec set modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,json_specs='[
  {
    "operation": "shift",
    "spec": {
      "PrisonerDetails": {
        "*": {
          "eliteoffenderid": "prisonerDetails.eliteOffenderId",
          "givenname": "prisonerDetails.givenName",
          "othergivennames": "prisonerDetails.otherGivenNames",
          "familyname": "prisonerDetails.familyName",
          "dateofbirth": "prisonerDetails.dateOfBirth",
          "datetime": "astriaMessageHeader.messageCreationTimestamp"
        }
      },
      "MessageId": {
        "*": {
          "messageid": [
            "astriaMessageHeader.correlationId",
            "astriaMessageHeader.messageId"
          ]
        }
      },
      "AstriaPersonId": {
        "*": {
          "astriapersonid": "prisonerDetails.astriaPersonId"
        }
      },
      "PrisonerAliasDetails": {
        "*": {
          "aliastypecode": "prisonerDetails.aliases[#2].aliasTypeCode",
          "familyname": "prisonerDetails.aliases[#2].familyName",
          "givenname": "prisonerDetails.aliases[#2].givenName",
          "othergivennames": "prisonerDetails.aliases[#2].otherGivenNames",
          "dateofbirth": "prisonerDetails.aliases[#2].dateOfBirth"
        }
      },
      "PrisonerIdentifierDetails": {
        "*": {
          "identifiertypecode": "prisonerDetails.additionalIdentifiers[#2].identifierTypeCode",
          "identifier": "prisonerDetails.additionalIdentifiers[#2].identifier",
          "issuer": "prisonerDetails.additionalIdentifiers[#2].issuer"
        }
      },
      "PrisonerTransferDetails": {
        "*": {
          "transferoutdateandtime": "prisonerEventDetails.prisonerInTransit.transferOutDateAndTime",
          "transferreasoncode": "prisonerEventDetails.prisonerInTransit.transferReasonCode",
          "transfertofacilitycode": "prisonerEventDetails.prisonerInTransit.transferToFacilityCode"
        }
      }
    }
  },
  {
    "operation": "default",
    "spec": {
      "astriaMessageHeader": {
        "sourceSystem": "Elite",
        "messageType": "Prisoner Event",
        "messageTypeCode": "PrEvent",
        "messageTypeVersion": "1"
      },
      "prisonerEventDetails": {
        "eventType": "Prisoner_In_Transit"
      }
    }
  },
  {
    "operation": "sort"
  }
]' where spec_key='TRANSFORM_TRANSFER';
