/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Roberto Estivill
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package intentbuilder;

import android.content.Context;
import android.os.Parcelable;

import java.util.List;

public class PreConditions {

  private PreConditions() {
  }

  public static void validateContext(Context context) {
    if (context == null) {
      throw new IllegalStateException("Call IntentBuilder.context() first");
    }
  }

  public static void validateNotEmpty(List param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.size(), message);
  }

  public static void validateNotEmpty(CharSequence[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(boolean[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(byte[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(char[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(double[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(float[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(int[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(long[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(short[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotEmpty(Parcelable[] param, String message) {
    validateNotNull(param, message);
    validateNotZero(param.length, message);
  }

  public static void validateNotNull(Object param, String message) {
    if (param == null) {
      throw new IllegalArgumentException(message + " must not be null");
    }
  }

  public static void validateNotZero(int length, String message) {
    if (length == 0) {
      throw new IllegalArgumentException(message + " must not be 0 length");
    }
  }

  public static void validateNotBlank(CharSequence param, String message) {
    validateNotNull(param, message);
    if (param.length() < 1) {
      throw new IllegalArgumentException(message + " must not be empty");
    }
  }
}
