/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class Profile {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Profile(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Profile obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(Profile obj) {
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
        liquidfunJNI.delete_Profile(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setStep(float value) {
    liquidfunJNI.Profile_step_set(swigCPtr, this, value);
  }

  public float getStep() {
    return liquidfunJNI.Profile_step_get(swigCPtr, this);
  }

  public void setCollide(float value) {
    liquidfunJNI.Profile_collide_set(swigCPtr, this, value);
  }

  public float getCollide() {
    return liquidfunJNI.Profile_collide_get(swigCPtr, this);
  }

  public void setSolve(float value) {
    liquidfunJNI.Profile_solve_set(swigCPtr, this, value);
  }

  public float getSolve() {
    return liquidfunJNI.Profile_solve_get(swigCPtr, this);
  }

  public void setSolveInit(float value) {
    liquidfunJNI.Profile_solveInit_set(swigCPtr, this, value);
  }

  public float getSolveInit() {
    return liquidfunJNI.Profile_solveInit_get(swigCPtr, this);
  }

  public void setSolveVelocity(float value) {
    liquidfunJNI.Profile_solveVelocity_set(swigCPtr, this, value);
  }

  public float getSolveVelocity() {
    return liquidfunJNI.Profile_solveVelocity_get(swigCPtr, this);
  }

  public void setSolvePosition(float value) {
    liquidfunJNI.Profile_solvePosition_set(swigCPtr, this, value);
  }

  public float getSolvePosition() {
    return liquidfunJNI.Profile_solvePosition_get(swigCPtr, this);
  }

  public void setBroadphase(float value) {
    liquidfunJNI.Profile_broadphase_set(swigCPtr, this, value);
  }

  public float getBroadphase() {
    return liquidfunJNI.Profile_broadphase_get(swigCPtr, this);
  }

  public void setSolveTOI(float value) {
    liquidfunJNI.Profile_solveTOI_set(swigCPtr, this, value);
  }

  public float getSolveTOI() {
    return liquidfunJNI.Profile_solveTOI_get(swigCPtr, this);
  }

  public Profile() {
    this(liquidfunJNI.new_Profile(), true);
  }

}
