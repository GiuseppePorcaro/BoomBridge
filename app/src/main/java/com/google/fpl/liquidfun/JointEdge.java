/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class JointEdge {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected JointEdge(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(JointEdge obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(JointEdge obj) {
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
        liquidfunJNI.delete_JointEdge(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setOther(Body value) {
    liquidfunJNI.JointEdge_other_set(swigCPtr, this, Body.getCPtr(value), value);
  }

  public Body getOther() {
    long cPtr = liquidfunJNI.JointEdge_other_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Body(cPtr, false);
  }

  public void setJoint(Joint value) {
    liquidfunJNI.JointEdge_joint_set(swigCPtr, this, Joint.getCPtr(value), value);
  }

  public Joint getJoint() {
    long cPtr = liquidfunJNI.JointEdge_joint_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Joint(cPtr, false);
  }

  public void setPrev(JointEdge value) {
    liquidfunJNI.JointEdge_prev_set(swigCPtr, this, JointEdge.getCPtr(value), value);
  }

  public JointEdge getPrev() {
    long cPtr = liquidfunJNI.JointEdge_prev_get(swigCPtr, this);
    return (cPtr == 0) ? null : new JointEdge(cPtr, false);
  }

  public void setNext(JointEdge value) {
    liquidfunJNI.JointEdge_next_set(swigCPtr, this, JointEdge.getCPtr(value), value);
  }

  public JointEdge getNext() {
    long cPtr = liquidfunJNI.JointEdge_next_get(swigCPtr, this);
    return (cPtr == 0) ? null : new JointEdge(cPtr, false);
  }

  public JointEdge() {
    this(liquidfunJNI.new_JointEdge(), true);
  }

}
