/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class StackEntry {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected StackEntry(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(StackEntry obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(StackEntry obj) {
    long ptr = 0;
    if (obj != null) {
      if (!obj.swigCMemOwn)
        throw new RuntimeException("Cannot release ownership as memory is not owned");
      ptr = obj.swigCPtr;
      obj.swigCMemOwn = false;
      obj.delete();
    }
    return ptr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        liquidfunJNI.delete_StackEntry(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setData(String value) {
    liquidfunJNI.StackEntry_data_set(swigCPtr, this, value);
  }

  public String getData() {
    return liquidfunJNI.StackEntry_data_get(swigCPtr, this);
  }

  public void setSize(int value) {
    liquidfunJNI.StackEntry_size_set(swigCPtr, this, value);
  }

  public int getSize() {
    return liquidfunJNI.StackEntry_size_get(swigCPtr, this);
  }

  public void setUsedMalloc(boolean value) {
    liquidfunJNI.StackEntry_usedMalloc_set(swigCPtr, this, value);
  }

  public boolean getUsedMalloc() {
    return liquidfunJNI.StackEntry_usedMalloc_get(swigCPtr, this);
  }

  public StackEntry() {
    this(liquidfunJNI.new_StackEntry(), true);
  }

}
