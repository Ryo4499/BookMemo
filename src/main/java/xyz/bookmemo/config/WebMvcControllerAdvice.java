package xyz.bookmemo.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import xyz.bookmemo.service.MemoNotFoundException;
import xyz.bookmemo.service.UserNotFoundException;

/**
 * This class is used to trim all incoming strings, and if the string is empty, it will set it to null
 */
@ControllerAdvice
public class WebMvcControllerAdvice {

  /**
   * > This function will trim all incoming strings, and if the string is empty, it will set it to null
   *
   * @param dataBinder The WebDataBinder object that is used to bind the request parameters to the form
   * object.
   */
  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    dataBinder.registerCustomEditor(
      String.class,
      new StringTrimmerEditor(true)
    );
  }

  /**
   * The function takes in a UserNotFoundException object and a Model object, and returns a String
   *
   * @param e The exception object
   * @param model The model object that will be used to render the view.
   * @return A String
   */
  @ExceptionHandler(UserNotFoundException.class)
  public String handleUserException(UserNotFoundException e, Model model) {
    model.addAttribute("message", e);
    return "error/CustomPage";
  }

  /**
   * > When a MemoNotFoundException is thrown, the handleMemoException function will be called, and the
   * exception will be added to the model
   *
   * @param e The exception object
   * @param model The model object that will be passed to the view.
   * @return A String
   */
  @ExceptionHandler(MemoNotFoundException.class)
  public String handleMemoException(MemoNotFoundException e, Model model) {
    model.addAttribute("message", e);
    return "error/CustomPage";
  }
}
