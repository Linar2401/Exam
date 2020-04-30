/*
 * Copyright (c) 2017, Alexander Ferenets (Istamendil, ist.kazan@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package ru.linar.sem.utils;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * Source
 * http://stackoverflow.com/questions/1972933/cross-field-validation-with-hibernate-validator-jsr-303
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

  private String errorMessage;
  
  private String firstFieldName;
  private String secondFieldName;

  @Override
  public void initialize(final FieldMatch constraintAnnotation) {
    errorMessage = constraintAnnotation.message();
    firstFieldName = constraintAnnotation.first();
    secondFieldName = constraintAnnotation.second();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    try {
      final BeanWrapperImpl bean = new BeanWrapperImpl(value);
      final Object firstObj = bean.getPropertyValue(firstFieldName);
      final Object secondObj = bean.getPropertyValue(secondFieldName);
      boolean returnValue = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
      //If the validation failed
      if (!returnValue) {
        context.disableDefaultConstraintViolation();
        //In the initialiaze method you get the errorMessage: constraintAnnotation.message();
        context.buildConstraintViolationWithTemplate(errorMessage).addPropertyNode(secondFieldName).addConstraintViolation();
      }
      return returnValue;
    } catch (final Exception ignore) {
      // ignore
    }
    return true;
  }
}
