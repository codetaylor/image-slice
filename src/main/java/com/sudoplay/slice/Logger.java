package com.sudoplay.slice;

/**
 * Logger
 */
public class Logger {

  public void info(String message, Object... replacements) {
    System.out.println("INFO  " + resolveMessageReplacements(message, replacements));
  }

  public void warn(String message, Object... replacements) {
    System.out.println("WARN  " + resolveMessageReplacements(message, replacements));
  }

  public void error(String message, Object... replacements) {
    System.err.println("ERROR " + resolveMessageReplacements(message, replacements));
  }

  private String resolveMessageReplacements(String message, Object[] replacements) {
    for (Object o : replacements) {
      message = message.replaceFirst("\\{}", String.valueOf(o));
    }
    return message;
  }

}
