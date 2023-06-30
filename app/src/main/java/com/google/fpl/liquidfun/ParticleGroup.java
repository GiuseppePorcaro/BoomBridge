/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class ParticleGroup {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ParticleGroup(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ParticleGroup obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ParticleGroup obj) {
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

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        throw new UnsupportedOperationException("C++ destructor does not have public access");
      }
      swigCPtr = 0;
    }
  }

  public ParticleGroup getNext() {
    long cPtr = liquidfunJNI.ParticleGroup_getNext__SWIG_0(swigCPtr, this);
    return (cPtr == 0) ? null : new ParticleGroup(cPtr, false);
  }

  public ParticleSystem getParticleSystem() {
    long cPtr = liquidfunJNI.ParticleGroup_getParticleSystem__SWIG_0(swigCPtr, this);
    return (cPtr == 0) ? null : new ParticleSystem(cPtr, false);
  }

  public int getParticleCount() {
    return liquidfunJNI.ParticleGroup_getParticleCount(swigCPtr, this);
  }

  public int getBufferIndex() {
    return liquidfunJNI.ParticleGroup_getBufferIndex(swigCPtr, this);
  }

  public boolean containsParticle(int index) {
    return liquidfunJNI.ParticleGroup_containsParticle(swigCPtr, this, index);
  }

  public long getAllParticleFlags() {
    return liquidfunJNI.ParticleGroup_getAllParticleFlags(swigCPtr, this);
  }

  public long getGroupFlags() {
    return liquidfunJNI.ParticleGroup_getGroupFlags(swigCPtr, this);
  }

  public void setGroupFlags(long flags) {
    liquidfunJNI.ParticleGroup_setGroupFlags(swigCPtr, this, flags);
  }

  public float getMass() {
    return liquidfunJNI.ParticleGroup_getMass(swigCPtr, this);
  }

  public float getInertia() {
    return liquidfunJNI.ParticleGroup_getInertia(swigCPtr, this);
  }

  public Vec2 getCenter() {
    return new Vec2(liquidfunJNI.ParticleGroup_getCenter(swigCPtr, this), true);
  }

  public Vec2 getLinearVelocity() {
    return new Vec2(liquidfunJNI.ParticleGroup_getLinearVelocity(swigCPtr, this), true);
  }

  public float getAngularVelocity() {
    return liquidfunJNI.ParticleGroup_getAngularVelocity(swigCPtr, this);
  }

  public Transform getTransform() {
    return new Transform(liquidfunJNI.ParticleGroup_getTransform(swigCPtr, this), false);
  }

  public Vec2 getPosition() {
    return new Vec2(liquidfunJNI.ParticleGroup_getPosition(swigCPtr, this), false);
  }

  public float getAngle() {
    return liquidfunJNI.ParticleGroup_getAngle(swigCPtr, this);
  }

  public Vec2 getLinearVelocityFromWorldPoint(Vec2 worldPoint) {
    return new Vec2(liquidfunJNI.ParticleGroup_getLinearVelocityFromWorldPoint(swigCPtr, this, Vec2.getCPtr(worldPoint), worldPoint), true);
  }

  public java.lang.Object getUserData() {
    return liquidfunJNI.ParticleGroup_getUserData(swigCPtr, this);
  }

  public void setUserData(java.lang.Object data) {
    liquidfunJNI.ParticleGroup_setUserData(swigCPtr, this, data);
  }

  public void applyForce(Vec2 force) {
    liquidfunJNI.ParticleGroup_applyForce(swigCPtr, this, Vec2.getCPtr(force), force);
  }

  public void applyLinearImpulse(Vec2 impulse) {
    liquidfunJNI.ParticleGroup_applyLinearImpulse(swigCPtr, this, Vec2.getCPtr(impulse), impulse);
  }

  public void destroyParticles(boolean callDestructionListener) {
    liquidfunJNI.ParticleGroup_destroyParticles__SWIG_0(swigCPtr, this, callDestructionListener);
  }

  public void destroyParticles() {
    liquidfunJNI.ParticleGroup_destroyParticles__SWIG_1(swigCPtr, this);
  }

}
