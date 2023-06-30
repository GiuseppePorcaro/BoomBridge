/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class WheelJointDef extends JointDef {
  private transient long swigCPtr;

  protected WheelJointDef(long cPtr, boolean cMemoryOwn) {
    super(liquidfunJNI.WheelJointDef_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(WheelJointDef obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(WheelJointDef obj) {
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
        liquidfunJNI.delete_WheelJointDef(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public WheelJointDef() {
    this(liquidfunJNI.new_WheelJointDef(), true);
  }

  public void initialize(Body bodyA, Body bodyB, Vec2 anchor, Vec2 axis) {
    liquidfunJNI.WheelJointDef_initialize(swigCPtr, this, Body.getCPtr(bodyA), bodyA, Body.getCPtr(bodyB), bodyB, Vec2.getCPtr(anchor), anchor, Vec2.getCPtr(axis), axis);
  }

  public void setLocalAnchorA(Vec2 value) {
    liquidfunJNI.WheelJointDef_localAnchorA_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getLocalAnchorA() {
    return new Vec2(liquidfunJNI.WheelJointDef_localAnchorA_get(swigCPtr, this), false);
  }

  public void setLocalAnchorB(Vec2 value) {
    liquidfunJNI.WheelJointDef_localAnchorB_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getLocalAnchorB() {
    return new Vec2(liquidfunJNI.WheelJointDef_localAnchorB_get(swigCPtr, this), false);
  }

  public void setLocalAxisA(Vec2 value) {
    liquidfunJNI.WheelJointDef_localAxisA_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getLocalAxisA() {
    return new Vec2(liquidfunJNI.WheelJointDef_localAxisA_get(swigCPtr, this), false);
  }

  public void setEnableMotor(boolean value) {
    liquidfunJNI.WheelJointDef_enableMotor_set(swigCPtr, this, value);
  }

  public boolean getEnableMotor() {
    return liquidfunJNI.WheelJointDef_enableMotor_get(swigCPtr, this);
  }

  public void setMaxMotorTorque(float value) {
    liquidfunJNI.WheelJointDef_maxMotorTorque_set(swigCPtr, this, value);
  }

  public float getMaxMotorTorque() {
    return liquidfunJNI.WheelJointDef_maxMotorTorque_get(swigCPtr, this);
  }

  public void setMotorSpeed(float value) {
    liquidfunJNI.WheelJointDef_motorSpeed_set(swigCPtr, this, value);
  }

  public float getMotorSpeed() {
    return liquidfunJNI.WheelJointDef_motorSpeed_get(swigCPtr, this);
  }

  public void setFrequencyHz(float value) {
    liquidfunJNI.WheelJointDef_frequencyHz_set(swigCPtr, this, value);
  }

  public float getFrequencyHz() {
    return liquidfunJNI.WheelJointDef_frequencyHz_get(swigCPtr, this);
  }

  public void setDampingRatio(float value) {
    liquidfunJNI.WheelJointDef_dampingRatio_set(swigCPtr, this, value);
  }

  public float getDampingRatio() {
    return liquidfunJNI.WheelJointDef_dampingRatio_get(swigCPtr, this);
  }

}
