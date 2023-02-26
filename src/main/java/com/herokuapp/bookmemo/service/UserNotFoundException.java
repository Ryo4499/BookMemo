package com.herokuapp.bookmemo.service;

public class UserNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -504854235488244700L;

  public UserNotFoundException(String message) {
    super(message);
  }
}