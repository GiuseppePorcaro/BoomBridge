/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class ContactFeature {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected ContactFeature(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ContactFeature obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ContactFeature obj) {
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
        liquidfunJNI.delete_ContactFeature(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setIndexA(short value) {
    liquidfunJNI.ContactFeature_indexA_set(swigCPtr, this, value);
  }

  public short getIndexA() {
    return liquidfunJNI.ContactFeature_indexA_get(swigCPtr, this);
  }

  public void setIndexB(short value) {
    liquidfunJNI.ContactFeature_indexB_set(swigCPtr, this, value);
  }

  public short getIndexB() {
    return liquidfunJNI.ContactFeature_indexB_get(swigCPtr, this);
  }

  public void setTypeA(short value) {
    liquidfunJNI.ContactFeature_typeA_set(swigCPtr, this, value);
  }

  public short getTypeA() {
    return liquidfunJNI.ContactFeature_typeA_get(swigCPtr, this);
  }

  public void setTypeB(short value) {
    liquidfunJNI.ContactFeature_typeB_set(swigCPtr, this, value);
  }

  public short getTypeB() {
    return liquidfunJNI.ContactFeature_typeB_get(swigCPtr, this);
  }

  public ContactFeature() {
    this(liquidfunJNI.new_ContactFeature(), true);
  }

  public final static class Type {
    public final static ContactFeature.Type e_vertex = new ContactFeature.Type("e_vertex", liquidfunJNI.ContactFeature_e_vertex_get());
    public final static ContactFeature.Type e_face = new ContactFeature.Type("e_face", liquidfunJNI.ContactFeature_e_face_get());

    public final int swigValue() {
      return swigValue;
    }

    public String toString() {
      return swigName;
    }

    public static Type swigToEnum(int swigValue) {
      if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
        return swigValues[swigValue];
      for (int i = 0; i < swigValues.length; i++)
        if (swigValues[i].swigValue == swigValue)
          return swigValues[i];
      throw new IllegalArgumentException("No enum " + Type.class + " with value " + swigValue);
    }

    private Type(String swigName) {
      this.swigName = swigName;
      this.swigValue = swigNext++;
    }

    private Type(String swigName, int swigValue) {
      this.swigName = swigName;
      this.swigValue = swigValue;
      swigNext = swigValue+1;
    }

    private Type(String swigName, Type swigEnum) {
      this.swigName = swigName;
      this.swigValue = swigEnum.swigValue;
      swigNext = this.swigValue+1;
    }

    private static Type[] swigValues = { e_vertex, e_face };
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
  }

}
