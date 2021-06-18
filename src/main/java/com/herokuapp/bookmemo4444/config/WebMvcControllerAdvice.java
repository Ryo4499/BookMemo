package com.herokuapp.bookmemo4444.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import com.herokuapp.bookmemo4444.service.MemoNotFoundException;
import com.herokuapp.bookmemo4444.service.UserNotFoundException;

@ControllerAdvice
public class WebMvcControllerAdvice {
  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }

  @ExceptionHandler(UserNotFoundException.class)
  public String handleUserException(UserNotFoundException e, Model model) {
    model.addAttribute("message", e);
    return "error/CustomPage";
  }

  @ExceptionHandler(MemoNotFoundException.class)
  public String handleMemoException(MemoNotFoundException e, Model model) {
    model.addAttribute("message", e);
    return "error/CustomPage";
  }
}
