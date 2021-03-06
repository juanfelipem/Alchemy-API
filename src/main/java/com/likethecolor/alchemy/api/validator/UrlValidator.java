/**
 * File: UrlValidator.java
 * Original Author: Dan Brown <dan@likethecolor.com>
 * Copyright 2012 Dan Brown <dan@likethecolor.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.likethecolor.alchemy.api.validator;

public class UrlValidator {
  private static final int MIN_LENGTH = 10;

  public static void validate(final String url) {
    if(url == null) {
      throw new IllegalArgumentException("URL to document to analyze cannot be null.");
    }
    if(url.trim().length() < MIN_LENGTH) {
      throw new IllegalArgumentException("URL to document to analyze cannot fewer than " + MIN_LENGTH + " [" + url.trim().length() + "].");
    }
  }
}
