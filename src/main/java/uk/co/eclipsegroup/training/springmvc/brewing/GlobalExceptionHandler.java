package uk.co.eclipsegroup.training.springmvc.brewing;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String errorPage(Model model, Exception e) {
        model.addAttribute("type", e.getClass().getSimpleName());
        model.addAttribute("message", e.getMessage());
        return "global-error";
    }
}
