/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class ContactManager {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ContactManager(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ContactManager obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ContactManager obj) {
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
        liquidfunJNI.delete_ContactManager(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public ContactManager() {
    this(liquidfunJNI.new_ContactManager(), true);
  }

  public void addPair(java.lang.Object proxyUserDataA, java.lang.Object proxyUserDataB) {
    liquidfunJNI.ContactManager_addPair(swigCPtr, this, proxyUserDataA, proxyUserDataB);
  }

  public void findNewContacts() {
    liquidfunJNI.ContactManager_findNewContacts(swigCPtr, this);
  }

  public void destroy(Contact c) {
    liquidfunJNI.ContactManager_destroy(swigCPtr, this, Contact.getCPtr(c), c);
  }

  public void collide() {
    liquidfunJNI.ContactManager_collide(swigCPtr, this);
  }

  public void setBroadPhase(BroadPhase value) {
    liquidfunJNI.ContactManager_broadPhase_set(swigCPtr, this, BroadPhase.getCPtr(value), value);
  }

  public BroadPhase getBroadPhase() {
    return new BroadPhase(liquidfunJNI.ContactManager_broadPhase_get(swigCPtr, this), false);
  }

  public void setContactList(Contact value) {
    liquidfunJNI.ContactManager_contactList_set(swigCPtr, this, Contact.getCPtr(value), value);
  }

  public Contact getContactList() {
    long cPtr = liquidfunJNI.ContactManager_contactList_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Contact(cPtr, false);
  }

  public void setContactCount(int value) {
    liquidfunJNI.ContactManager_contactCount_set(swigCPtr, this, value);
  }

  public int getContactCount() {
    return liquidfunJNI.ContactManager_contactCount_get(swigCPtr, this);
  }

  public void setContactFilter(SWIGTYPE_p_b2ContactFilter value) {
    liquidfunJNI.ContactManager_contactFilter_set(swigCPtr, this, SWIGTYPE_p_b2ContactFilter.getCPtr(value));
  }

  public SWIGTYPE_p_b2ContactFilter getContactFilter() {
    long cPtr = liquidfunJNI.ContactManager_contactFilter_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_b2ContactFilter(cPtr, false);
  }

  public void setContactListener(ContactListener value) {
    liquidfunJNI.ContactManager_contactListener_set(swigCPtr, this, ContactListener.getCPtr(value), value);
  }

  public ContactListener getContactListener() {
    long cPtr = liquidfunJNI.ContactManager_contactListener_get(swigCPtr, this);
    return (cPtr == 0) ? null : new ContactListener(cPtr, false);
  }

  public void setAllocator(BlockAllocator value) {
    liquidfunJNI.ContactManager_allocator_set(swigCPtr, this, BlockAllocator.getCPtr(value), value);
  }

  public BlockAllocator getAllocator() {
    long cPtr = liquidfunJNI.ContactManager_allocator_get(swigCPtr, this);
    return (cPtr == 0) ? null : new BlockAllocator(cPtr, false);
  }

}
