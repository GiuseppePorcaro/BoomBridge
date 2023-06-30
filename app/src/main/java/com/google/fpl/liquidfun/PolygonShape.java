/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class PolygonShape extends Shape {
  private transient long swigCPtr;

  protected PolygonShape(long cPtr, boolean cMemoryOwn) {
    super(liquidfunJNI.PolygonShape_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(PolygonShape obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(PolygonShape obj) {
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
        liquidfunJNI.delete_PolygonShape(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public PolygonShape() {
    this(liquidfunJNI.new_PolygonShape(), true);
  }

  public Shape clone(BlockAllocator allocator) {
    long cPtr = liquidfunJNI.PolygonShape_clone(swigCPtr, this, BlockAllocator.getCPtr(allocator), allocator);
    return (cPtr == 0) ? null : new Shape(cPtr, false);
  }

  public int getChildCount() {
    return liquidfunJNI.PolygonShape_getChildCount(swigCPtr, this);
  }

  public void set(Vec2 points, int count) {
    liquidfunJNI.PolygonShape_set(swigCPtr, this, Vec2.getCPtr(points), points, count);
  }

  public void setAsBox(float hx, float hy) {
    liquidfunJNI.PolygonShape_setAsBox__SWIG_0(swigCPtr, this, hx, hy);
  }

  public void setAsBox(float hx, float hy, Vec2 center, float angle) {
    liquidfunJNI.PolygonShape_setAsBox__SWIG_1(swigCPtr, this, hx, hy, Vec2.getCPtr(center), center, angle);
  }

  public boolean testPoint(Transform transform, Vec2 p) {
    return liquidfunJNI.PolygonShape_testPoint(swigCPtr, this, Transform.getCPtr(transform), transform, Vec2.getCPtr(p), p);
  }

  public void computeDistance(Transform xf, Vec2 p, SWIGTYPE_p_float distance, Vec2 normal, int childIndex) {
    liquidfunJNI.PolygonShape_computeDistance(swigCPtr, this, Transform.getCPtr(xf), xf, Vec2.getCPtr(p), p, SWIGTYPE_p_float.getCPtr(distance), Vec2.getCPtr(normal), normal, childIndex);
  }

  public boolean rayCast(RayCastOutput output, RayCastInput input, Transform transform, int childIndex) {
    return liquidfunJNI.PolygonShape_rayCast(swigCPtr, this, RayCastOutput.getCPtr(output), output, RayCastInput.getCPtr(input), input, Transform.getCPtr(transform), transform, childIndex);
  }

  public void computeAABB(AABB aabb, Transform transform, int childIndex) {
    liquidfunJNI.PolygonShape_computeAABB(swigCPtr, this, AABB.getCPtr(aabb), aabb, Transform.getCPtr(transform), transform, childIndex);
  }

  public void computeMass(MassData massData, float density) {
    liquidfunJNI.PolygonShape_computeMass(swigCPtr, this, MassData.getCPtr(massData), massData, density);
  }

  public int getVertexCount() {
    return liquidfunJNI.PolygonShape_getVertexCount(swigCPtr, this);
  }

  public Vec2 getVertex(int index) {
    return new Vec2(liquidfunJNI.PolygonShape_getVertex(swigCPtr, this, index), false);
  }

  public boolean validate() {
    return liquidfunJNI.PolygonShape_validate(swigCPtr, this);
  }

  public void setCentroid(float x, float y) {
    liquidfunJNI.PolygonShape_setCentroid(swigCPtr, this, x, y);
  }

  public void setAsBox(float hx, float hy, float centerX, float centerY, float angle) {
    liquidfunJNI.PolygonShape_setAsBox__SWIG_2(swigCPtr, this, hx, hy, centerX, centerY, angle);
  }

  public void setCentroid(Vec2 value) {
    liquidfunJNI.PolygonShape_centroid_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getCentroid() {
    return new Vec2(liquidfunJNI.PolygonShape_centroid_get(swigCPtr, this), false);
  }

  public void setVertices(Vec2 value) {
    liquidfunJNI.PolygonShape_vertices_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getVertices() {
    long cPtr = liquidfunJNI.PolygonShape_vertices_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Vec2(cPtr, false);
  }

  public void setNormals(Vec2 value) {
    liquidfunJNI.PolygonShape_normals_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getNormals() {
    long cPtr = liquidfunJNI.PolygonShape_normals_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Vec2(cPtr, false);
  }

  public void setCount(int value) {
    liquidfunJNI.PolygonShape_count_set(swigCPtr, this, value);
  }

  public int getCount() {
    return liquidfunJNI.PolygonShape_count_get(swigCPtr, this);
  }

}
