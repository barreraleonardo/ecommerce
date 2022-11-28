package com.nocountry.ecommerce.common.exception.handler;

import com.nocountry.ecommerce.common.exception.error.AlreadyExistsException;
import com.nocountry.ecommerce.common.exception.error.ConflictException;
import com.nocountry.ecommerce.common.exception.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {


   //=====================Bad Request=====================//


   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler({
      AlreadyExistsException.class,
      HttpRequestMethodNotSupportedException.class,
   })
   @ResponseBody
   protected ExceptionDetails badRequestHandler(Exception exception, HttpServletRequest request) {
      return new ExceptionDetails(LocalDateTime.now(), exception, request);
   }

   //=====================Not Found=====================//

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler({
      ResourceNotFoundException.class,
      UsernameNotFoundException.class
   })
   @ResponseBody
   protected ExceptionDetails notFoundHandler(Exception exception, HttpServletRequest request) {
      return new ExceptionDetails(LocalDateTime.now(), exception, request);
   }

   //=====================Conflict Handler=====================//


   @ResponseStatus(HttpStatus.CONFLICT)
   @ExceptionHandler({
      ConflictException.class,
      HttpClientErrorException.Conflict.class
   })
   @ResponseBody
   protected ExceptionDetails conflictHandler(Exception exception, HttpServletRequest request) {
      return new ExceptionDetails(LocalDateTime.now(), exception, request);
   }

   //=====================Server Error Handler=====================//


   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler({Exception.class})
   @ResponseBody
   protected ExceptionDetails internalErrorHandler(Exception exception, HttpServletRequest request) {
      return new ExceptionDetails(LocalDateTime.now(), exception, request);
   }


   //=====================Authorization Handler=====================//

   @ResponseStatus(HttpStatus.FORBIDDEN)
   @ExceptionHandler({HttpClientErrorException.Forbidden.class})
   @ResponseBody
   protected ExceptionDetails forbiddenHandler(Exception exception, HttpServletRequest request) {
      return new ExceptionDetails(LocalDateTime.now(), exception, request);
   }


   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   @ExceptionHandler({
      HttpClientErrorException.Unauthorized.class,
      AccessDeniedException.class
   })
   protected void unauthorizedHandler() { }
}
