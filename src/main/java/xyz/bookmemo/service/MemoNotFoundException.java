package xyz.bookmemo.service;

/**
 * It's a custom exception class that extends RuntimeException
 */
public class MemoNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 8232724598839814515L;

  MemoNotFoundException(String message) {
    super(message);
  }
}
