package com.kat.orderlogisticsystem1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalErrorExceptionHandler
{

	@ExceptionHandler
	@ResponseBody
	GlobalErrorExceptionMessage handleException(Exception e)
	{
		log.error(e.getMessage());
		return new GlobalErrorExceptionMessage("ERROR_DESCRIPTION");
	}
}

