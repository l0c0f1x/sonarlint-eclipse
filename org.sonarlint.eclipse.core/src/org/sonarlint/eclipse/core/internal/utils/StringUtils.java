/*
 * SonarLint for Eclipse
 * Copyright (C) 2015-2016 SonarSource SA
 * sonarlint@sonarsource.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarlint.eclipse.core.internal.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

public class StringUtils {

  public static final String EMPTY = "";
  public static final int INDEX_NOT_FOUND = -1;

  public static boolean isBlank(String str) {
    int strLen;
    if (str == null || (strLen = str.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if ((Character.isWhitespace(str.charAt(i)) == false)) {
        return false;
      }
    }
    return true;
  }

  public static boolean isNotBlank(String str) {
    return !StringUtils.isBlank(str);
  }

  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  public static String substringAfterLast(String str, String separator) {
    if (isEmpty(str)) {
      return str;
    }
    if (isEmpty(separator)) {
      return EMPTY;
    }
    int pos = str.lastIndexOf(separator);
    if (pos == INDEX_NOT_FOUND || pos == (str.length() - separator.length())) {
      return EMPTY;
    }
    return str.substring(pos + separator.length());
  }

  public static String joinSkipNull(Collection<String> values, String separator) {
    Iterator<String> iter = values.iterator();
    StringBuilder sb = new StringBuilder();
    while (iter.hasNext()) {
      String next = iter.next();
      if (next != null) {
        sb.append(next);
        while (iter.hasNext()) {
          next = iter.next();
          if (next != null) {
            sb.append(separator).append(next);
          }
        }
      }
    }
    return sb.toString();
  }

  public static String[] split(String str, String separator) {
    return isEmpty(str) ? new String[0] : str.split(Pattern.quote(separator));
  }

  public static String defaultString(String str, String defaultStr) {
    return str == null ? defaultStr : str;
  }

}