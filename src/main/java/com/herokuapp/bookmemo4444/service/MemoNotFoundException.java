package com.herokuapp.bookmemo4444.service;

public class MemoNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 8232724598839814515L;

  MemoNotFoundException(String message) {
    super(message);
  }
}
