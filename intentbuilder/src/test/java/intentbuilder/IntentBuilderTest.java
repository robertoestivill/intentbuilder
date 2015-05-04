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
import android.os.Parcel;
import android.os.Parcelable;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IntentBuilderTest {

  // //////////////////////
  // Constructors
  // //////////////////////
  @Test
  public void sameIntent() {
    Intent mock = mock(Intent.class);
    Intent intent = new IntentBuilder(mock).build();
    assertEquals(mock, intent);
  }

  @Test
  public void emptyIntent() {
    Intent intent = new IntentBuilder().build();
    assertNotNull(intent);
  }

  // //////////////////////
  // Builder methods
  // //////////////////////

  @Test(expected = IllegalStateException.class)
  public void noContext() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).activity(Activity.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void actionNull() {
    new IntentBuilder().action(null);
  }

  @Test
  public void action() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).action("MY_ACTION");
    verify(mock, times(1)).setAction("MY_ACTION");
  }

  @Test(expected = IllegalArgumentException.class)
  public void actionBlank() {
    new IntentBuilder().action("");
  }

  @Test(expected = IllegalStateException.class)
  public void serviceNoContext() {
    new IntentBuilder().service(Service.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void serviceNull() {
    Context context = mock(Context.class);
    new IntentBuilder().context(context).service(null);
  }

  @Test
  public void service() {
    Intent mock = mock(Intent.class);
    Context context = mock(Context.class);
    new IntentBuilder(mock).context(context).service(Service.class);
    verify(mock, times(1)).setClass(context, Service.class);
  }

  @Test(expected = IllegalStateException.class)
  public void activityNoContext() {
    new IntentBuilder().activity(Activity.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void activityNull() {
    Context context = mock(Context.class);
    new IntentBuilder().context(context).activity(null);
  }

  @Test
  public void activity() {
    Intent mock = mock(Intent.class);
    Context context = mock(Context.class);
    new IntentBuilder(mock).context(context).activity(Activity.class);
    verify(mock, times(1)).setClass(context, Activity.class);
  }


  @Test(expected = IllegalStateException.class)
  public void receiverNoContext() {
    new IntentBuilder().receiver(BroadcastReceiver.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void receiverNull() {
    Context context = mock(Context.class);
    new IntentBuilder().context(context).receiver(null);
  }

  @Test
  public void receiver() {
    Intent mock = mock(Intent.class);
    Context context = mock(Context.class);
    new IntentBuilder(mock).context(context).receiver(BroadcastReceiver.class);
    verify(mock, times(1)).setClass(context, BroadcastReceiver.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void componentNull() {
    new IntentBuilder().component(null);
  }

  @Test
  public void component() {
    Intent mock = mock(Intent.class);
    ComponentName component = mock(ComponentName.class);
    new IntentBuilder(mock).component(component);
    verify(mock, times(1)).setComponent(component);
  }

  @Test(expected = IllegalArgumentException.class)
  public void classNameWithContextNullContext() {
    Context context = null;
    new IntentBuilder().className(context, "my.package.MyClass");
  }

  @Test(expected = IllegalArgumentException.class)
  public void classNameWithContextNullClass() {
    Context context = mock(Context.class);
    new IntentBuilder().className(context, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void classNameWithContextBlankClass() {
    Context context = mock(Context.class);
    new IntentBuilder().className(context, "");
  }

  @Test
  public void classNameWithContext() {
    Intent mock = mock(Intent.class);
    Context context = mock(Context.class);
    new IntentBuilder(mock).className(context, "my.package.MyClass");
    verify(mock, times(1)).setClassName(context, "my.package.MyClass");
  }

  @Test(expected = IllegalArgumentException.class)
  public void classNameWithPackageNullPackage() {
    String pckg = null;
    new IntentBuilder().className(pckg, "MyClass");
  }

  @Test(expected = IllegalArgumentException.class)
  public void classNameWithPackageBlankPackage() {
    String pckg = "";
    new IntentBuilder().className(pckg, "MyClass");
  }

  @Test(expected = IllegalArgumentException.class)
  public void classNameWithPackageNullClass() {
    Intent mock = mock(Intent.class);
    String cls = null;
    new IntentBuilder(mock).className("my.package", cls);
  }

  @Test(expected = IllegalArgumentException.class)
  public void classNameWithPackageBlankClass() {
    Intent mock = mock(Intent.class);
    String cls = "";
    new IntentBuilder(mock).className("my.package", cls);
  }

  @Test
  public void classNameWithPackage() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).className("my.package", "MyClass");
    verify(mock, times(1)).setClassName("my.package", "MyClass");
  }

  @Test(expected = IllegalArgumentException.class)
  public void setPackageNull() {
    String pack = null;
    new IntentBuilder().setPackage(pack);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setPackageBlank() {
    String pack = "";
    new IntentBuilder().setPackage(pack);
  }

  @Test
  public void setPackage() {
    Intent mock = mock(Intent.class);
    String pack = "my.package";
    new IntentBuilder(mock).setPackage(pack);
    verify(mock, times(1)).setPackage("my.package");
  }

  @Test
  public void flagOne() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).flag(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    verify(mock, times(1)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void flagsNull() {
    new IntentBuilder().flags(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void flagsEmpty() {
    new IntentBuilder().flags(new int[]{});
  }

  @Test
  public void flagsOne() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).flags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    verify(mock, times(1)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
  }

  @Test
  public void flagsTwo() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).flags(
            Intent.FLAG_ACTIVITY_CLEAR_TASK,
            Intent.FLAG_ACTIVITY_CLEAR_TOP);
    verify(mock, times(1)).addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TASK);
    verify(mock, times(1)).addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP);
  }

  @Test(expected = IllegalArgumentException.class)
  public void dataNull() {
    Uri data = null;
    new IntentBuilder().data(data);
  }

  @Test
  public void data() {
    Intent mock = mock(Intent.class);
    Uri data = mock(Uri.class);
    new IntentBuilder(mock).data(data);
    verify(mock, times(1)).setData(data);
  }

  @Test(expected = IllegalArgumentException.class)
  public void dataNormalizeNull() {
    Uri data = null;
    new IntentBuilder().dataNormalize(data);
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Test
  public void dataNormalize() {
    Intent mock = mock(Intent.class);
    Uri data = mock(Uri.class);
    new IntentBuilder(mock).dataNormalize(data);
    verify(mock, times(1)).setDataAndNormalize(data);
  }

  @Test(expected = IllegalArgumentException.class)
  public void typeNull() {
    String type = null;
    new IntentBuilder().type(type);
  }

  @Test
  public void type() {
    Intent mock = mock(Intent.class);
    String type = "my.type";
    new IntentBuilder(mock).type(type);
    verify(mock, times(1)).setType(type);
  }

  @Test(expected = IllegalArgumentException.class)
  public void typeNormalizeNull() {
    String type = null;
    new IntentBuilder().typeNormalize(type);
  }

  @Test
  public void typeNormalize() {
    Intent mock = mock(Intent.class);
    String type = "my.type";
    new IntentBuilder(mock).typeNormalize(type);
    verify(mock, times(1)).setTypeAndNormalize(type);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extrasBundleNull() {
    Bundle bundle = null;
    new IntentBuilder().extras(bundle);
  }

  @Test
  public void extrasBundle() {
    Intent mock = mock(Intent.class);
    Bundle bundle = new Bundle();
    new IntentBuilder(mock).extras(bundle);
    verify(mock, times(1)).putExtras(bundle);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extrasIntentNull() {
    Intent intent = null;
    new IntentBuilder().extras(intent);
  }

  @Test
  public void extrasIntent() {
    Intent mock = mock(Intent.class);
    Intent intent = new Intent();
    new IntentBuilder(mock).extras(intent);
    verify(mock, times(1)).putExtras(intent);
  }

  // //////////////////////
  // Primitive extras
  // //////////////////////

  @Test(expected = IllegalArgumentException.class)
  public void extraBooleanNullKey() {
    new IntentBuilder().extra(null, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraBooleanBlankKey() {
    new IntentBuilder().extra("", false);
  }

  @Test
  public void extraBoolean() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", false);
    verify(mock, times(1)).putExtra("my_key", false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraByteNullKey() {
    new IntentBuilder().extra(null, (byte) 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraByteBlankKey() {
    new IntentBuilder().extra("", (byte) 1);
  }

  @Test
  public void extraByte() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", (byte) 1);
    verify(mock, times(1)).putExtra("my_key", (byte) 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharNullKey() {
    new IntentBuilder().extra(null, 'c');
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharBlankKey() {
    new IntentBuilder().extra("", 'c');
  }

  @Test
  public void extraChar() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", 'c');
    verify(mock, times(1)).putExtra("my_key", 'c');
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraDoubleNullKey() {
    new IntentBuilder().extra(null, 12.34d);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraDoubleBlankKey() {
    new IntentBuilder().extra("", 12.34d);
  }

  @Test
  public void extraDouble() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", 12.34d);
    verify(mock, times(1)).putExtra("my_key", 12.34d);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraFloatNullKey() {
    new IntentBuilder().extra(null, 12.34f);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraFloatBlankKey() {
    new IntentBuilder().extra("", 12.34f);
  }

  @Test
  public void extraFloat() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", 12.34f);
    verify(mock, times(1)).putExtra("my_key", 12.34f);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntNullKey() {
    new IntentBuilder().extra(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntBlankKey() {
    new IntentBuilder().extra("", 1);
  }

  @Test
  public void extraInt() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", 1);
    verify(mock, times(1)).putExtra("my_key", 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraLongNullKey() {
    new IntentBuilder().extra(null, 1234l);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraLongBlankKey() {
    new IntentBuilder().extra("", 1234l);
  }

  @Test
  public void extraLong() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", 1234l);
    verify(mock, times(1)).putExtra("my_key", 1234l);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraShortNullKey() {
    new IntentBuilder().extra(null, (short) 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraShortBlankKey() {
    new IntentBuilder().extra("", (short) 12);
  }

  @Test
  public void extraShort() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", (short) 12);
    verify(mock, times(1)).putExtra("my_key", (short) 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringNullKey() {
    new IntentBuilder().extra(null, "string");
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringBlankKey() {
    new IntentBuilder().extra("", "string");
  }

  @Test
  public void extraString() {
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", "string");
    verify(mock, times(1)).putExtra("my_key", "string");
  }

  // //////////////////////
  // Primitive Arrays extras
  // //////////////////////

  @Test(expected = IllegalArgumentException.class)
  public void extraBooleanArrayNullKey() {
    new IntentBuilder().extra(null, new boolean[]{true});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraBooleanArrayBlankKey() {
    new IntentBuilder().extra("", new boolean[]{true});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraBooleanArrayNullValue() {
    boolean[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraBooleanArrayEmptyValue() {
    boolean[] array = new boolean[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraBooleanArray() {
    boolean[] array = new boolean[]{true};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraByteArrayNullKey() {
    new IntentBuilder().extra(null, new byte[]{(byte) 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraByteArrayBlankKey() {
    new IntentBuilder().extra("", new byte[]{(byte) 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraByteArrayNullValue() {
    byte[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraByteArrayEmptyValue() {
    byte[] array = new byte[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraByteArray() {
    byte[] array = new byte[]{(byte) 1};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharArrayNullKey() {
    new IntentBuilder().extra(null, new char[]{'a'});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharArrayBlankKey() {
    new IntentBuilder().extra("", new char[]{'a'});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharArrayNullValue() {
    char[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharArrayEmptyValue() {
    char[] array = new char[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraCharArray() {
    char[] array = new char[]{'a'};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraDoubleArrayNullKey() {
    new IntentBuilder().extra(null, new double[]{1.2d});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraDoubleArrayBlankKey() {
    new IntentBuilder().extra("", new double[]{1.2d});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraDoubleArrayNullValue() {
    double[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraDoubleArrayEmptyValue() {
    double[] array = new double[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraDoubleArray() {
    double[] array = new double[]{1.2d};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraFloatArrayNullKey() {
    new IntentBuilder().extra(null, new float[]{1.2f});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraFloatArrayBlankKey() {
    new IntentBuilder().extra("", new float[]{1.2f});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraFloatArrayNullValue() {
    float[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraFloatArrayEmptyValue() {
    float[] array = new float[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraFloatArray() {
    float[] array = new float[]{1.2f};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntArrayNullKey() {
    new IntentBuilder().extra(null, new int[]{1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntArrayBlankKey() {
    new IntentBuilder().extra("", new int[]{1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntArrayNullValue() {
    int[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntArrayEmptyValue() {
    int[] array = new int[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraIntArray() {
    int[] array = new int[]{1};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraLongArrayNullKey() {
    new IntentBuilder().extra(null, new long[]{1l});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraLongArrayBlankKey() {
    new IntentBuilder().extra("", new long[]{1l});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraLongArrayNullValue() {
    long[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraLongArrayEmptyValue() {
    long[] array = new long[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraLongArray() {
    long[] array = new long[]{1l};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraShortArrayNullKey() {
    new IntentBuilder().extra(null, new short[]{(short) 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraShortArrayBlankKey() {
    new IntentBuilder().extra("", new short[]{(short) 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraShortArrayNullValue() {
    short[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraShortArrayEmptyValue() {
    short[] array = new short[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraShortArray() {
    short[] array = new short[]{(short) 1};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  // //////////////////////
  // Object extras
  // //////////////////////

  @Test(expected = IllegalArgumentException.class)
  public void extraBundleNullKey() {
    new IntentBuilder().extra(null, new Bundle());
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraBundleBlankKey() {
    new IntentBuilder().extra("", new Bundle());
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraBundleNullValue() {
    Bundle bundle = null;
    new IntentBuilder().extra("my_key", bundle);
  }

  @Test
  public void extraBundle() {
    Bundle bundle = new Bundle();
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", bundle);
    verify(mock, times(1)).putExtra("my_key", bundle);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceNullKey() {
    CharSequence sequence = "my_value";
    new IntentBuilder().extra(null, sequence);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceBlankKey() {
    CharSequence sequence = "my_value";
    new IntentBuilder().extra("", sequence);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceNullValue() {
    CharSequence sequence = null;
    new IntentBuilder().extra("my_key", sequence);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceBlankValue() {
    CharSequence sequence = "";
    new IntentBuilder().extra("my_key", sequence);
  }

  @Test
  public void extraCharSequence() {
    CharSequence sequence = "my_value";
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", sequence);
    verify(mock, times(1)).putExtra("my_key", sequence);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableNullKey() {
    Parcelable parcelable = new MyObject();
    new IntentBuilder().extra(null, parcelable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableBlankKey() {
    Parcelable parcelable = new MyObject();
    new IntentBuilder().extra("", parcelable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableNullValue() {
    Parcelable parcelable = null;
    new IntentBuilder().extra("my_key", parcelable);
  }

  @Test
  public void extraParcelable() {
    Parcelable parcelable = new MyObject();
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", parcelable);
    verify(mock, times(1)).putExtra("my_key", parcelable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraSerializableNullKey() {
    Serializable serializable = new MyObject();
    new IntentBuilder().extra(null, serializable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraSerializableBlankKey() {
    Serializable serializable = new MyObject();
    new IntentBuilder().extra("", serializable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraSerializableNullValue() {
    Serializable serializable = null;
    new IntentBuilder().extra("my_key", serializable);
  }

  @Test
  public void extraSerializable() {
    Serializable serializable = new MyObject();
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", serializable);
    verify(mock, times(1)).putExtra("my_key", serializable);
  }

  // //////////////////////
  // Object collections extras
  // //////////////////////

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceArrayNullKey() {
    CharSequence[] array = new CharSequence[]{"value"};
    new IntentBuilder().extra(null, array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceArrayBlankKey() {
    CharSequence[] array = new CharSequence[]{"value"};
    new IntentBuilder().extra("", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceArrayNullValue() {
    CharSequence[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceArrayEmptyValue() {
    CharSequence[] array = new CharSequence[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraCharSequenceArray() {
    CharSequence[] array = new CharSequence[]{"value"};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableArrayNullKey() {
    Parcelable[] array = new Parcelable[]{new MyObject()};
    new IntentBuilder().extra(null, array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableArrayBlankKey() {
    Parcelable[] array = new Parcelable[]{new MyObject()};
    new IntentBuilder().extra("", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableArrayNullValue() {
    Parcelable[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableArrayEmptyValue() {
    Intent mock = mock(Intent.class);
    Parcelable[] array = new Parcelable[]{};
    new IntentBuilder(mock).extra("my_key", array);
  }

  @Test
  public void extraParcelableArray() {
    Parcelable[] array = new Parcelable[]{new MyObject()};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringArrayNullKey() {
    String[] array = new String[]{"value"};
    new IntentBuilder().extra(null, array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringArrayBlankKey() {
    String[] array = new String[]{"value"};
    new IntentBuilder().extra("", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringArrayNullValue() {
    String[] array = null;
    new IntentBuilder().extra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringArrayEmptyValue() {
    String[] array = new String[]{};
    new IntentBuilder().extra("my_key", array);
  }

  @Test
  public void extraStringArray() {
    String[] array = new String[]{"value"};
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extra("my_key", array);
    verify(mock, times(1)).putExtra("my_key", array);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceListNullKey() {
    ArrayList<CharSequence> list = new ArrayList<>();
    list.add("value");
    new IntentBuilder().extraCharSequenceList(null, list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceListBlankKey() {
    ArrayList<CharSequence> list = new ArrayList<>();
    list.add("value");
    new IntentBuilder().extraCharSequenceList("", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceListNullValue() {
    ArrayList<CharSequence> list = null;
    new IntentBuilder().extraCharSequenceList("my_key", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraCharSequenceListEmptyValue() {
    ArrayList<CharSequence> list = new ArrayList<>();
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extraCharSequenceList("my_key", list);
  }

  @Test
  public void extraCharSequenceList() {
    ArrayList<CharSequence> list = new ArrayList<>();
    list.add("value");
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extraCharSequenceList("my_key", list);
    verify(mock, times(1)).putExtra("my_key", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntegerListNullKey() {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(new Integer(1));
    new IntentBuilder().extraIntegerList(null, list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntegerListBlankKey() {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(new Integer(1));
    new IntentBuilder().extraIntegerList("", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntegerListNullValue() {
    ArrayList<Integer> list = null;
    new IntentBuilder().extraIntegerList("my_key", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraIntegerListEmptyValue() {
    ArrayList<Integer> list = new ArrayList<>();
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extraIntegerList("my_key", list);
  }

  @Test
  public void extraIntegerList() {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(new Integer(1));
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extraIntegerList("my_key", list);
    verify(mock, times(1)).putExtra("my_key", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableListNullKey() {
    ArrayList<Parcelable> list = new ArrayList<>();
    list.add(new MyObject());
    new IntentBuilder().extraParcelableList(null, list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableListBlankKey() {
    ArrayList<Parcelable> list = new ArrayList<>();
    list.add(new MyObject());
    new IntentBuilder().extraParcelableList("", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableListNullValue() {
    ArrayList<Parcelable> list = null;
    new IntentBuilder().extraParcelableList("my_key", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraParcelableListEmptyValue() {
    ArrayList<Parcelable> list = new ArrayList<>();
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extraParcelableList("my_key", list);
  }

  @Test
  public void extraParcelableList() {
    ArrayList<Parcelable> list = new ArrayList<>();
    list.add(new MyObject());
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extraParcelableList("my_key", list);
    verify(mock, times(1)).putExtra("my_key", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringListNullKey() {
    ArrayList<String> list = new ArrayList<>();
    list.add("value");
    new IntentBuilder().extraStringList(null, list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringListBlankKey() {
    ArrayList<String> list = new ArrayList<>();
    list.add("value");
    new IntentBuilder().extraStringList("", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringListNullValue() {
    ArrayList<String> list = null;
    new IntentBuilder().extraStringList("my_key", list);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraStringListEmptyValue() {
    ArrayList<String> list = new ArrayList<>();
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extraStringList("my_key", list);
  }

  @Test
  public void extraStringList() {
    ArrayList<String> list = new ArrayList<>();
    list.add("value");
    Intent mock = mock(Intent.class);
    new IntentBuilder(mock).extraStringList("my_key", list);
    verify(mock, times(1)).putExtra("my_key", list);
  }

  // //////////////////////
  // Util
  // //////////////////////

  static class MyObject implements Parcelable, Serializable {

    @Override
    public int describeContents() {
      return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
  }
}