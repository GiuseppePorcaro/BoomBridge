/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class Mat22 {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Mat22(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Mat22 obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(Mat22 obj) {
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
        liquidfunJNI.delete_Mat22(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public Mat22() {
    this(liquidfunJNI.new_Mat22__SWIG_0(), true);
  }

  public Mat22(Vec2 c1, Vec2 c2) {
    this(liquidfunJNI.new_Mat22__SWIG_1(Vec2.getCPtr(c1), c1, Vec2.getCPtr(c2), c2), true);
  }

  public Mat22(float a11, float a12, float a21, float a22) {
    this(liquidfunJNI.new_Mat22__SWIG_2(a11, a12, a21, a22), true);
  }

  public void set(Vec2 c1, Vec2 c2) {
    liquidfunJNI.Mat22_set(swigCPtr, this, Vec2.getCPtr(c1), c1, Vec2.getCPtr(c2), c2);
  }

  public void setIdentity() {
    liquidfunJNI.Mat22_setIdentity(swigCPtr, this);
  }

  public void setZero() {
    liquidfunJNI.Mat22_setZero(swigCPtr, this);
  }

  public Mat22 getInverse() {
    return new Mat22(liquidfunJNI.Mat22_getInverse(swigCPtr, this), true);
  }

  public Vec2 solve(Vec2 b) {
    return new Vec2(liquidfunJNI.Mat22_solve(swigCPtr, this, Vec2.getCPtr(b), b), true);
  }

  public void setEx(Vec2 value) {
    liquidfunJNI.Mat22_ex_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getEx() {
    return new Vec2(liquidfunJNI.Mat22_ex_get(swigCPtr, this), false);
  }

  public void setEy(Vec2 value) {
    liquidfunJNI.Mat22_ey_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getEy() {
    return new Vec2(liquidfunJNI.Mat22_ey_get(swigCPtr, this), false);
  }

}