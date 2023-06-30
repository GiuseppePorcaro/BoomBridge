/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class DistanceJointDef {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected DistanceJointDef(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(DistanceJointDef obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(DistanceJointDef obj) {
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
        liquidfunJNI.delete_DistanceJointDef(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public DistanceJointDef() {
    this(liquidfunJNI.new_DistanceJointDef(), true);
  }

  public void initialize(Body bodyA, Body bodyB, Vec2 anchorA, Vec2 anchorB) {
    liquidfunJNI.DistanceJointDef_initialize(swigCPtr, this, Body.getCPtr(bodyA), bodyA, Body.getCPtr(bodyB), bodyB, Vec2.getCPtr(anchorA), anchorA, Vec2.getCPtr(anchorB), anchorB);
  }

  public void setLocalAnchorA(Vec2 value) {
    liquidfunJNI.DistanceJointDef_localAnchorA_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getLocalAnchorA() {
    return new Vec2(liquidfunJNI.DistanceJointDef_localAnchorA_get(swigCPtr, this), false);
  }

  public void setLocalAnchorB(Vec2 value) {
    liquidfunJNI.DistanceJointDef_localAnchorB_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getLocalAnchorB() {
    return new Vec2(liquidfunJNI.DistanceJointDef_localAnchorB_get(swigCPtr, this), false);
  }

  public void setLength(float value) {
    liquidfunJNI.DistanceJointDef_length_set(swigCPtr, this, value);
  }

  public float getLength() {
    return liquidfunJNI.DistanceJointDef_length_get(swigCPtr, this);
  }

  public void setFrequencyHz(float value) {
    liquidfunJNI.DistanceJointDef_frequencyHz_set(swigCPtr, this, value);
  }

  public float getFrequencyHz() {
    return liquidfunJNI.DistanceJointDef_frequencyHz_get(swigCPtr, this);
  }

  public void setDampingRatio(float value) {
    liquidfunJNI.DistanceJointDef_dampingRatio_set(swigCPtr, this, value);
  }

  public float getDampingRatio() {
    return liquidfunJNI.DistanceJointDef_dampingRatio_get(swigCPtr, this);
  }

}
