package com.hunsley.cache.model;

public class CacheableException extends Exception {

  public CacheableException() {
  }

  public CacheableException(String message) {
    super(message);
  }

  public CacheableException(String message, Throwable cause) {
    super(message, cause);
  }

  public CacheableException(Throwable cause) {
    super(cause);
  }

  public CacheableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
