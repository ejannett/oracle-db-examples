/*
 * Copyright (c) 2024, Oracle and/or its affiliates.
 *
 *   This software is dual-licensed to you under the Universal Permissive License
 *   (UPL) 1.0 as shown at https://oss.oracle.com/licenses/upl or Apache License
 *   2.0 as shown at http://www.apache.org/licenses/LICENSE-2.0. You may choose
 *   either license.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *
 */

package com.oracle.jdbc.samples.statementinterceptordemo.webcontent;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(SecurityException.class)
  public String handleException(SecurityException e, Model model) {
    final var stacktrace = Arrays.stream(e.getStackTrace())
                                 .map(StackTraceElement::toString)
                                 .collect(
                                   Collectors.joining(System.lineSeparator()));

    model.addAttribute("error", e);
    model.addAttribute("stacktrace", stacktrace);

    return "error";

  }

}