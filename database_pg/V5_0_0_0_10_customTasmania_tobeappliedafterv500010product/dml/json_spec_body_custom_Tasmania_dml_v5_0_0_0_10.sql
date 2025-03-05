update oms_owner.json_spec 
 set   modify_datetime = current_timestamp
       ,modify_user_id = user 
       ,json_specs = '[
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
	  "MessageId":{
       "*": {
         "messageid": ["astriaMessageHeader.correlationId","astriaMessageHeader.messageId"]
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
      "PrisonerAdmissionDetails": {
        "*": {
          "admissiondateandtime": "prisonerEventDetails.prisonerAdmission.admissionDateAndTime",
           "admissionreason": "prisonerEventDetails.prisonerAdmission.admissionReason",
           "housinglevelonecode": "prisonerEventDetails.prisonerAdmission.housingLevelOneCode",
          "housinglevelonetypecode": "prisonerEventDetails.prisonerAdmission.housingLevelOneTypeCode",
          "housingleveltwocode": "prisonerEventDetails.prisonerAdmission.housingLevelTwoCode",
          "housingleveltwotypecode": "prisonerEventDetails.prisonerAdmission.housingLevelTwoTypeCode",
          "housinglevelthreecode": "prisonerEventDetails.prisonerAdmission.housingLevelThreeCode",
          "housinglevelthreetypecode": "prisonerEventDetails.prisonerAdmission.housingLevelThreeTypeCode",
          "housinglevelfourcode": "prisonerEventDetails.prisonerAdmission.housingLevelFourCode",
          "housinglevelfourtypecode": "prisonerEventDetails.prisonerAdmission.housingLevelFourTypeCode",
          "housinglocationcode": "prisonerEventDetails.prisonerAdmission.housingLocationCode",
          "housingfacilitycode": "prisonerEventDetails.prisonerAdmission.housingFacilityCode"
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
        "messageTypeCode": "Elite-PrEvent",
        "messageTypeVersion": "1"
        
      },
      "prisonerEventDetails": {
        "eventType": "Prisoner_Admission"
      }
    }
  },
  {
  "operation": "sort"
  }
  

  ]'::bytea 
 where spec_key = 'TRANSFORM_ADMISSION';

update oms_owner.json_spec 
 set   modify_datetime = current_timestamp
       ,modify_user_id = user 
       ,json_specs = '[
  {
    "operation": "shift",
    "spec": {
      "PrisonerDetails":  {
        "*": {
          "eliteoffenderid": "prisonerDetails.eliteOffenderId",
          "givenname": "prisonerDetails.givenName",
          "othergivennames": "prisonerDetails.otherGivenNames",
          "familyname": "prisonerDetails.familyName",
          "dateofbirth": "prisonerDetails.dateOfBirth",
		  "datetime": "astriaMessageHeader.messageCreationTimestamp"
		 
		   
        }
      },
	  "MessageId":{
       "*": {
         "messageid": ["astriaMessageHeader.correlationId","astriaMessageHeader.messageId"]
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
       "PrisonerIdentifierDetails":  {
        "*": {
          "identifiertypecode": "prisonerDetails.additionalIdentifiers[#2].identifierTypeCode",
          "identifier": "prisonerDetails.additionalIdentifiers[#2].identifier",
          "issuer": "prisonerDetails.additionalIdentifiers[#2].issuer"
        }
      },
      "PrisonerHousingDetails": {
        "*": {
          "newhousingdateandtime": "prisonerEventDetails.prisonerHousing.newHousingDateAndTime",
          "newhousinglevelonecode": "prisonerEventDetails.prisonerHousing.newHousingLevelOneCode",
          "newhousinglevelonetypecode": "prisonerEventDetails.prisonerHousing.newHousingLevelOneTypeCode",
          "newhousingleveltwocode": "prisonerEventDetails.prisonerHousing.newHousingLevelTwoCode",
          "newhousingleveltwotypecode": "prisonerEventDetails.prisonerHousing.newHousingLevelTwoTypeCode",
          "newhousinglevelthreecode": "prisonerEventDetails.prisonerHousing.newHousingLevelThreeCode",
          "newhousinglevelthreecode": "prisonerEventDetails.prisonerHousing.newHousingLevelThreeTypeCode",
          "newhousinglevelfourcode": "prisonerEventDetails.prisonerHousing.newHousingLevelFourCode",
          "newhousinglevelfourtypecode": "prisonerEventDetails.prisonerHousing.newHousingLevelFourTypeCode",
          "newhousinglocationcode": "prisonerEventDetails.prisonerHousing.newHousingLocationCode",
          "newhousingfacilitycode": "prisonerEventDetails.prisonerHousing.newHousingFacilityCode"
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
        "messageTypeCode": "Elite-PrEvent",
        "messageTypeVersion": "1"
        
      },
      "prisonerEventDetails": {
        "eventType": "Prisoner_Housing"
      }
    }
  },
  {
  "operation": "sort"
  }

  ]
'::bytea 
 where spec_key = 'TRANSFORM_HOUSING';

update oms_owner.json_spec 
 set   modify_datetime = current_timestamp
       ,modify_user_id = user 
       ,json_specs = '[
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
	   "MessageId":{
       "*": {
         "messageid": ["astriaMessageHeader.correlationId","astriaMessageHeader.messageId"]
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
        "messageTypeCode": "Elite-PrEvent",
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

  ]'::bytea 
 where spec_key = 'TRANSFORM_TRANSFER';

update oms_owner.json_spec 
 set   modify_datetime = current_timestamp
       ,modify_user_id = user 
       ,json_specs = '[
  {
    "operation": "shift",
    "spec": {
      "PrisonerDetails":  {
        "*": {
          "eliteoffenderid": "prisonerDetails.eliteOffenderId",
          "givenname": "prisonerDetails.givenName",
          "othergivennames": "prisonerDetails.otherGivenNames",
          "familyname": "prisonerDetails.familyName",
          "dateofbirth": "prisonerDetails.dateOfBirth",
		  "datetime": "astriaMessageHeader.messageCreationTimestamp"
		  
        }
      },
	  "MessageId":{
       "*": {
         "messageid": ["astriaMessageHeader.correlationId","astriaMessageHeader.messageId"]
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
      "PrisonerIdentifierDetails":  {
        "*": {
           "identifiertypecode": "prisonerDetails.additionalIdentifiers[#2].identifierTypeCode",
          "identifier": "prisonerDetails.additionalIdentifiers[#2].identifier",
          "issuer": "prisonerDetails.additionalIdentifiers[#2].issuer"
        }
      },
      "PrisonerReleaseDetails": {
        "*": {
          "releasedateandtime": "prisonerEventDetails.prisonerRelease.releasedateandtime",
          "releasereasoncode": "prisonerEventDetails.prisonerRelease.releasereasoncode"
          
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
        "messageTypeCode": "Elite-PrEvent",
        "messageTypeVersion": "1"
       
      },
      "prisonerEventDetails": {
        "eventType": "Prisoner_Release"
      }
    }
  },
  {
  "operation": "sort"
  }

  ]'::bytea 
 where spec_key = 'TRANSFORM_RELEASE';