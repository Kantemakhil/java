package net.syscon.s4.controllervalidation;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.JsonMappingException;

import net.syscon.s4.common.beans.MessageDTO;
import net.syscon.s4.common.beans.MessageType;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.genericservices.ErrorCodes;

@EnableWebMvc
@ControllerAdvice
public class ControllerValidationHandler {
    @Autowired
    private MessageSource msgSource;

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<MessageDTO> processCustomException(CustomException ex) {
        MessageDTO dto = new MessageDTO();
        ErrorCodes errorCodes = ex.getErrorCodes();
        
        if(null != errorCodes) {
            dto.setErrorMessage(errorCodes.getErrorMessage().replace("'%s'", "."));
            dto.setErrorCode(errorCodes.getErrorCode());
            dto.setMessage(errorCodes.getErrorMessage().replace("'%s'", "."));
        }
        
        
        HttpStatus httpStatusCode = ex.getHttpStatus();
        if (null != httpStatusCode) {
            if (httpStatusCode.is4xxClientError() || httpStatusCode.is5xxServerError()) {
                dto.setType(MessageType.ERROR);
            }
        }
        if (null != SecurityContextHolder.getContext()
                && null != SecurityContextHolder.getContext().getAuthentication()) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            dto.setCreateUserId((String) principal);
        }

        ResponseEntity<MessageDTO> resp = new ResponseEntity<MessageDTO>(dto, httpStatusCode);
        return resp;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        if (error == null) {
            return processGlobalError(result.getGlobalError());
        } else {
            return processFieldError(error);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        MessageDTO message = null;
        JsonMappingException jme = (JsonMappingException) ex.getCause();
        if (jme != null) {
            String msg = jme.getPath().get(0).getFieldName() + " invalid";
            message = new MessageDTO(MessageType.ERROR, msg);
            message.setMessage(msg);
        }
        return message;
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO handleBindException(BindException error) {
        MessageDTO message = null;
        if (error != null) {
            String msg = error.getMessage();
            message = new MessageDTO(MessageType.ERROR, msg);
            message.setMessage(msg);
        }

        return message;
    }

    /**
     * To process Field level errors
     * 
     * @param error
     * @return
     */
    private MessageDTO processFieldError(FieldError error) {
        MessageDTO message = null;
        if (error != null && null != msgSource) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            if (currentLocale != null) {
                final String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
                message = new MessageDTO(MessageType.ERROR, msg);
            }
        }
        return message;
    }

    /**
     * To process global or class level validations.
     * 
     * @param error
     * @return
     */
    private MessageDTO processGlobalError(ObjectError error) {
        MessageDTO message = null;
        if (error != null && msgSource != null) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            if (currentLocale != null) {
                String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
                message = new MessageDTO(MessageType.ERROR, msg);
            }
        }
        return message;
    }
}
