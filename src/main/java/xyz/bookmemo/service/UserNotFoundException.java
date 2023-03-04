package xyz.bookmemo.service;

/**
 * This class is a RuntimeException that is thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -504854235488244700L;

  public UserNotFoundException(String message) {
    super(message);
  }
}
