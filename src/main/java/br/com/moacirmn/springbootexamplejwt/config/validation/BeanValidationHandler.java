package br.com.moacirmn.springbootexamplejwt.config.validation;

import br.com.moacirmn.springbootexamplejwt.dto.BeanValidationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BeanValidationHandler {

    @Autowired
    private MessageSource messagesource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<BeanValidationDto> handle(MethodArgumentNotValidException exception) {
        List<BeanValidationDto> listMsgDto = new ArrayList<>(1);
        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
        fieldErros.forEach(e -> {
            String msg = messagesource.getMessage(e, LocaleContextHolder.getLocale());
            BeanValidationDto msgDto = new BeanValidationDto(e.getField(), msg);
            listMsgDto.add(msgDto);
        });
        return listMsgDto;
    }

}
