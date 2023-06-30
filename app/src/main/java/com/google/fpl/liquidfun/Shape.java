/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class Shape {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Shape(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Shape obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(Shape obj) {
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
        liquidfunJNI.delete_Shape(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public Shape clone(BlockAllocator allocator) {
    long cPtr = liquidfunJNI.Shape_clone(swigCPtr, this, BlockAllocator.getCPtr(allocator), allocator);
    return (cPtr == 0) ? null : new Shape(cPtr, false);
  }

  public Shape.Type getType() {
    return Shape.Type.swigToEnum(liquidfunJNI.Shape_getType(swigCPtr, this));
  }

  public int getChildCount() {
    return liquidfunJNI.Shape_getChildCount(swigCPtr, this);
  }

  public boolean testPoint(Transform xf, Vec2 p) {
    return liquidfunJNI.Shape_testPoint(swigCPtr, this, Transform.getCPtr(xf), xf, Vec2.getCPtr(p), p);
  }

  public void computeDistance(Transform xf, Vec2 p, SWIGTYPE_p_float distance, Vec2 normal, int childIndex) {
    liquidfunJNI.Shape_computeDistance(swigCPtr, this, Transform.getCPtr(xf), xf, Vec2.getCPtr(p), p, SWIGTYPE_p_float.getCPtr(distance), Vec2.getCPtr(normal), normal, childIndex);
  }

  public boolean rayCast(RayCastOutput output, RayCastInput input, Transform transform, int childIndex) {
    return liquidfunJNI.Shape_rayCast(swigCPtr, this, RayCastOutput.getCPtr(output), output, RayCastInput.getCPtr(input), input, Transform.getCPtr(transform), transform, childIndex);
  }

  public void computeAABB(AABB aabb, Transform xf, int childIndex) {
    liquidfunJNI.Shape_computeAABB(swigCPtr, this, AABB.getCPtr(aabb), aabb, Transform.getCPtr(xf), xf, childIndex);
  }

  public void computeMass(MassData massData, float density) {
    liquidfunJNI.Shape_computeMass(swigCPtr, this, MassData.getCPtr(massData), massData, density);
  }

  public void setType(Shape.Type value) {
    liquidfunJNI.Shape_type_set(swigCPtr, this, value.swigValue());
  }

  public void setRadius(float value) {
    liquidfunJNI.Shape_radius_set(swigCPtr, this, value);
  }

  public float getRadius() {
    return liquidfunJNI.Shape_radius_get(swigCPtr, this);
  }

  public final static class Type {
    public final static Shape.Type e_circle = new Shape.Type("e_circle", liquidfunJNI.Shape_e_circle_get());
    public final static Shape.Type e_edge = new Shape.Type("e_edge", liquidfunJNI.Shape_e_edge_get());
    public final static Shape.Type e_polygon = new Shape.Type("e_polygon", liquidfunJNI.Shape_e_polygon_get());
    public final static Shape.Type e_chain = new Shape.Type("e_chain", liquidfunJNI.Shape_e_chain_get());
    public final static Shape.Type e_typeCount = new Shape.Type("e_typeCount", liquidfunJNI.Shape_e_typeCount_get());

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

    private static Type[] swigValues = { e_circle, e_edge, e_polygon, e_chain, e_typeCount };
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
  }

}
