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

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import static intentbuilder.PreConditions.validateContext;
import static intentbuilder.PreConditions.validateNotBlank;
import static intentbuilder.PreConditions.validateNotEmpty;
import static intentbuilder.PreConditions.validateNotNull;

public class IntentBuilder {

  private Intent mIntent;
  private Context mContext;

  // //////////////////////
  // Constructors
  // //////////////////////

  public IntentBuilder() {
    mIntent = new Intent();
  }

  public IntentBuilder(Intent intent) {
    mIntent = intent;
  }

  public IntentBuilder(String action) {
    mIntent = new Intent(action);
  }

  public IntentBuilder(String action, Uri uri) {
    mIntent = new Intent(action, uri);
  }

  public IntentBuilder(Context packageContext, Class<?> cls) {
    mIntent = new Intent(packageContext, cls);
  }

  public IntentBuilder(String action, Uri uri, Context packageContext, Class<?> cls) {
    mIntent = new Intent(action, uri, packageContext, cls);
  }

  // //////////////////////
  // Builder methods
  // //////////////////////

  public IntentBuilder context(Context context) {
    mContext = context;
    return this;
  }

  public IntentBuilder action(String action) {
    validateNotBlank(action, "Action");
    mIntent.setAction(action);
    return this;
  }

  public IntentBuilder service(Class<? extends Service> service) {
    return setClass(service);
  }

  public IntentBuilder activity(Class<? extends Activity> activity) {
    return setClass(activity);
  }

  public IntentBuilder receiver(Class<? extends BroadcastReceiver> receiver) {
    return setClass(receiver);
  }

  public IntentBuilder component(ComponentName component) {
    validateNotNull(component, "ComponentName");
    mIntent.setComponent(component);
    return this;
  }

  public IntentBuilder className(Context packageCtx, String className) {
    validateNotNull(packageCtx, "Context");
    validateNotBlank(className, "ClassName");
    mIntent.setClassName(packageCtx, className);
    return this;
  }

  public IntentBuilder className(String packageName, String className) {
    validateNotBlank(packageName, "PackageName");
    validateNotBlank(className, "ClassName");
    mIntent.setClassName(packageName, className);
    return this;
  }

  public IntentBuilder setPackage(String pack) {
    validateNotBlank(pack, "Package");
    mIntent.setPackage(pack);
    return this;
  }

  public IntentBuilder flag(int flag) {
    return flags(flag);
  }

  public IntentBuilder flags(int... flags) {
    validateNotEmpty(flags, "Flags");
    for (int flag : flags) {
      mIntent.addFlags(flag);
    }
    return this;
  }

  public IntentBuilder extras(Bundle extras) {
    validateNotNull(extras, "Extras bundle");
    mIntent.putExtras(extras);
    return this;
  }

  public IntentBuilder extras(Intent intent) {
    validateNotNull(intent, "Intent");
    mIntent.putExtras(intent);
    return this;
  }

  public IntentBuilder data(Uri data) {
    validateNotNull(data, "Data Uri");
    mIntent.setData(data);
    return this;
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public IntentBuilder dataNormalize(Uri data) {
    validateNotNull(data, "Data");
    mIntent.setDataAndNormalize(data);
    return this;
  }

  public IntentBuilder type(String type) {
    validateNotBlank(type, "Type");
    mIntent.setType(type);
    return this;
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public IntentBuilder typeNormalize(String type) {
    validateNotBlank(type, "Type");
    mIntent.setTypeAndNormalize(type);
    return this;
  }

  // //////////////////////
  // Primitive extras
  // //////////////////////

  public IntentBuilder extra(String name, boolean value) {
    validateNotBlank(name, "Name");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, byte value) {
    validateNotBlank(name, "Name");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, char value) {
    validateNotBlank(name, "Name");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, double value) {
    validateNotBlank(name, "Name");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, float value) {
    validateNotBlank(name, "Name");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, int value) {
    validateNotBlank(name, "Name");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, long value) {
    validateNotBlank(name, "Name");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, short value) {
    validateNotBlank(name, "Name");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, String value) {
    validateNotBlank(name, "Name");
    validateNotNull(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  // //////////////////////
  // Primitive Arrays extras
  // //////////////////////

  public IntentBuilder extra(String name, byte[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, boolean[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, char[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, double[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, float[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, int[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, long[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, short[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  // //////////////////////
  // Object extras
  // //////////////////////

  public IntentBuilder extra(String name, Bundle value) {
    validateNotBlank(name, "Name");
    validateNotNull(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, CharSequence value) {
    validateNotBlank(name, "Name");
    validateNotBlank(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, Parcelable value) {
    validateNotBlank(name, "Name");
    validateNotNull(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, Serializable value) {
    validateNotBlank(name, "Name");
    validateNotNull(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  // //////////////////////
  // Object collections extras
  // //////////////////////

  public IntentBuilder extra(String name, CharSequence[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, Parcelable[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extra(String name, String[] value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extraCharSequenceList(String name, ArrayList<CharSequence> value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extraIntegerList(String name, ArrayList<Integer> value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extraParcelableList(String name, ArrayList<? extends Parcelable> value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  public IntentBuilder extraStringList(String name, ArrayList<String> value) {
    validateNotBlank(name, "Name");
    validateNotEmpty(value, "Value");
    mIntent.putExtra(name, value);
    return this;
  }

  // //////////////////////
  // Return the intent
  // //////////////////////

  public Intent build() {
    return mIntent;
  }

  // //////////////////////
  // Private methods
  // //////////////////////

  private IntentBuilder setClass(Class<?> cls) {
    validateContext(mContext);
    validateNotNull(cls, "Class<?>");
    mIntent.setClass(mContext, cls);
    return this;
  }
}
