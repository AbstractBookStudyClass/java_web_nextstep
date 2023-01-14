package org.falsystack;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
  int add(String text) {
    if (isBlank(text)) {
      return 0;
    }

    if (text.length() == 1) {
      return Integer.parseInt(text);
    }

    String[] numbers = split(text);
    return sum(numbers);
  }

  private static String[] split(String text) {
    Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
    if (m.find()) {
      String customDelimiter = m.group(1);
      return m.group(2).split(customDelimiter);
    }
    return text.split("[,:]");
  }

  private static boolean isBlank(String text) {
    return text == null || text.isEmpty();
  }

  private static int sum(String[] numbers) {
    return Arrays.stream(numbers).mapToInt(number -> {
      int num = Integer.parseInt(number);
      if (num < 0) {
        throw new RuntimeException();
      }
      return num;
    }).reduce(Integer::sum).getAsInt();
  }
}
