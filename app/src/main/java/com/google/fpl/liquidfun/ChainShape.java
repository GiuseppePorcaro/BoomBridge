/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public class ChainShape extends Shape {
  private transient long swigCPtr;

  protected ChainShape(long cPtr, boolean cMemoryOwn) {
    super(liquidfunJNI.ChainShape_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ChainShape obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(ChainShape obj) {
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
        liquidfunJNI.delete_ChainShape(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public ChainShape() {
    this(liquidfunJNI.new_ChainShape(), true);
  }

  public void createLoop(Vec2 vertices, int count) {
    liquidfunJNI.ChainShape_createLoop(swigCPtr, this, Vec2.getCPtr(vertices), vertices, count);
  }

  public void createChain(Vec2 vertices, int count) {
    liquidfunJNI.ChainShape_createChain(swigCPtr, this, Vec2.getCPtr(vertices), vertices, count);
  }

  public void setPrevVertex(Vec2 prevVertex) {
    liquidfunJNI.ChainShape_setPrevVertex(swigCPtr, this, Vec2.getCPtr(prevVertex), prevVertex);
  }

  public void setNextVertex(Vec2 nextVertex) {
    liquidfunJNI.ChainShape_setNextVertex(swigCPtr, this, Vec2.getCPtr(nextVertex), nextVertex);
  }

  public Shape clone(BlockAllocator allocator) {
    long cPtr = liquidfunJNI.ChainShape_clone(swigCPtr, this, BlockAllocator.getCPtr(allocator), allocator);
    return (cPtr == 0) ? null : new Shape(cPtr, false);
  }

  public int getChildCount() {
    return liquidfunJNI.ChainShape_getChildCount(swigCPtr, this);
  }

  public void getChildEdge(EdgeShape edge, int index) {
    liquidfunJNI.ChainShape_getChildEdge(swigCPtr, this, EdgeShape.getCPtr(edge), edge, index);
  }

  public boolean testPoint(Transform transform, Vec2 p) {
    return liquidfunJNI.ChainShape_testPoint(swigCPtr, this, Transform.getCPtr(transform), transform, Vec2.getCPtr(p), p);
  }

  public void computeDistance(Transform xf, Vec2 p, SWIGTYPE_p_float distance, Vec2 normal, int childIndex) {
    liquidfunJNI.ChainShape_computeDistance(swigCPtr, this, Transform.getCPtr(xf), xf, Vec2.getCPtr(p), p, SWIGTYPE_p_float.getCPtr(distance), Vec2.getCPtr(normal), normal, childIndex);
  }

  public boolean rayCast(RayCastOutput output, RayCastInput input, Transform transform, int childIndex) {
    return liquidfunJNI.ChainShape_rayCast(swigCPtr, this, RayCastOutput.getCPtr(output), output, RayCastInput.getCPtr(input), input, Transform.getCPtr(transform), transform, childIndex);
  }

  public void computeAABB(AABB aabb, Transform transform, int childIndex) {
    liquidfunJNI.ChainShape_computeAABB(swigCPtr, this, AABB.getCPtr(aabb), aabb, Transform.getCPtr(transform), transform, childIndex);
  }

  public void computeMass(MassData massData, float density) {
    liquidfunJNI.ChainShape_computeMass(swigCPtr, this, MassData.getCPtr(massData), massData, density);
  }

  public void setVertices(Vec2 value) {
    liquidfunJNI.ChainShape_vertices_set(swigCPtr, this, Vec2.getCPtr(value), value);
  }

  public Vec2 getVertices() {
    long cPtr = liquidfunJNI.ChainShape_vertices_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Vec2(cPtr, false);
  }

  public void setCount(int value) {
    liquidfunJNI.ChainShape_count_set(swigCPtr, this, value);
  }

  public int getCount() {
    return liquidfunJNI.ChainShape_count_get(swigCPtr, this);
  }

  public Vec2 getPrevVertex() {
    return new Vec2(liquidfunJNI.ChainShape_prevVertex_get(swigCPtr, this), false);
  }

  public Vec2 getNextVertex() {
    return new Vec2(liquidfunJNI.ChainShape_nextVertex_get(swigCPtr, this), false);
  }

  public void setHasPrevVertex(boolean value) {
    liquidfunJNI.ChainShape_hasPrevVertex_set(swigCPtr, this, value);
  }

  public boolean getHasPrevVertex() {
    return liquidfunJNI.ChainShape_hasPrevVertex_get(swigCPtr, this);
  }

  public void setHasNextVertex(boolean value) {
    liquidfunJNI.ChainShape_hasNextVertex_set(swigCPtr, this, value);
  }

  public boolean getHasNextVertex() {
    return liquidfunJNI.ChainShape_hasNextVertex_get(swigCPtr, this);
  }

}
