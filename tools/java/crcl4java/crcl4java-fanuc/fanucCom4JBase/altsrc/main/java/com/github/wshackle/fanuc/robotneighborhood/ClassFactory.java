package com.github.wshackle.fanuc.robotneighborhood  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  /**
   * A collection of FRCRNRDMResponse objects.
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponses createFRCRNRDMResponses() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponses.class, "{802FB8A4-AD51-4B5E-9B96-94CDBCB7BC91}" );
  }

  /**
   * Provides information about an RDM response recently received from a robot.
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse createFRCRNRDMResponse() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRNRDMResponse.class, "{CE52A483-C2B1-4176-A700-2662DCE283D0}" );
  }

  /**
   * A collection of FRCRNService objects.
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRNServices createFRCRNServices() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRNServices.class, "{AC1F68B0-7EC4-4C12-8629-A82CE7C9F5AF}" );
  }

  /**
   * Provides information about a TCP/IP service running on the robot.
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRNService createFRCRNService() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRNService.class, "{AD52BA3B-2CC8-4ABC-963A-5CA4F19D0AE2}" );
  }

  /**
   * Provides access to a specific real robot.
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRNRealRobot createFRCRNRealRobot() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRNRealRobot.class, "{CCFF177E-2846-4BF2-9906-20F29C990546}" );
  }

  /**
   * Provides access to a specific virtual robot.
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRNVirtualRobot createFRCRNVirtualRobot() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRNVirtualRobot.class, "{64E6E8C4-D936-4E84-A93B-62C2723C74D1}" );
  }

  /**
   * Provides access to a specific robot in the Robot Neighborhood 
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRNRobot createFRCRNRobot() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRNRobot.class, "{801CE09E-9BEE-4CD8-A3E2-9050648B8A44}" );
  }

  /**
   * A collection of FRCRNRobots and FRCRNRobot objects.
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRNRobots createFRCRNRobots() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRNRobots.class, "{BCECA03B-3CFD-4510-BA75-4304347BB55B}" );
  }

  /**
   * Provides root access to the Robot Neighborhood.
   */
  public static com.github.wshackle.fanuc.robotneighborhood.IRobotNeighborhood createFRCRobotNeighborhood() {
    return COM4J.createInstance( com.github.wshackle.fanuc.robotneighborhood.IRobotNeighborhood.class, "{F96D0B41-CEB3-4F40-9E9E-7EDE89349157}" );
  }
}
