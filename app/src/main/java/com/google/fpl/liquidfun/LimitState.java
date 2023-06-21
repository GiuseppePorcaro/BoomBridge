/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.1.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.google.fpl.liquidfun;

public final class LimitState {
  public final static LimitState e_inactiveLimit = new LimitState("e_inactiveLimit");
  public final static LimitState e_atLowerLimit = new LimitState("e_atLowerLimit");
  public final static LimitState e_atUpperLimit = new LimitState("e_atUpperLimit");
  public final static LimitState e_equalLimits = new LimitState("e_equalLimits");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static LimitState swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + LimitState.class + " with value " + swigValue);
  }

  private LimitState(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private LimitState(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private LimitState(String swigName, LimitState swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static LimitState[] swigValues = { e_inactiveLimit, e_atLowerLimit, e_atUpperLimit, e_equalLimits };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

