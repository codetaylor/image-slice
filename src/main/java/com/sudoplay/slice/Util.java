package com.sudoplay.slice;

/**
 * Utility class.
 */
public class Util {

  public static String plurality(int count, String... pluralForms) {

    if (count > pluralForms.length - 1) {
      count = pluralForms.length - 1;

    }

    return pluralForms[count];

  }

  private Util() {
    //
  }

}
