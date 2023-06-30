/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class ContactRegister {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ContactRegister(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ContactRegister obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ContactRegister obj) {
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
        liquidfunJNI.delete_ContactRegister(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setCreateFcn(SWIGTYPE_p_f_p_b2Fixture_int32_p_b2Fixture_int32_p_b2BlockAllocator__p_b2Contact value) {
    liquidfunJNI.ContactRegister_createFcn_set(swigCPtr, this, SWIGTYPE_p_f_p_b2Fixture_int32_p_b2Fixture_int32_p_b2BlockAllocator__p_b2Contact.getCPtr(value));
  }

  public SWIGTYPE_p_f_p_b2Fixture_int32_p_b2Fixture_int32_p_b2BlockAllocator__p_b2Contact getCreateFcn() {
    long cPtr = liquidfunJNI.ContactRegister_createFcn_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_f_p_b2Fixture_int32_p_b2Fixture_int32_p_b2BlockAllocator__p_b2Contact(cPtr, false);
  }

  public void setDestroyFcn(SWIGTYPE_p_f_p_b2Contact_p_b2BlockAllocator__void value) {
    liquidfunJNI.ContactRegister_destroyFcn_set(swigCPtr, this, SWIGTYPE_p_f_p_b2Contact_p_b2BlockAllocator__void.getCPtr(value));
  }

  public SWIGTYPE_p_f_p_b2Contact_p_b2BlockAllocator__void getDestroyFcn() {
    long cPtr = liquidfunJNI.ContactRegister_destroyFcn_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_f_p_b2Contact_p_b2BlockAllocator__void(cPtr, false);
  }

  public void setPrimary(boolean value) {
    liquidfunJNI.ContactRegister_primary_set(swigCPtr, this, value);
  }

  public boolean getPrimary() {
    return liquidfunJNI.ContactRegister_primary_get(swigCPtr, this);
  }

  public ContactRegister() {
    this(liquidfunJNI.new_ContactRegister(), true);
  }

}
