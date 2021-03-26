/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.ui.forcetorquesensorsimulator;

import crcl.base.PointType;
import crcl.base.PoseType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.JPanel;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author will
 */
@SuppressWarnings({"initialization","nullness"})
public class InOutJPanel extends JPanel {

    public InOutJPanel() {
        myMouseAdapter = new InOutMouseAdapter();
        System.out.println("InOutJPanel.this.getSize() = " + InOutJPanel.this.getSize());
        super.addMouseListener(myMouseAdapter);
        super.addMouseMotionListener(myMouseAdapter);
    }

    private List<TrayStack> stacks;

    /**
     * Get the value of stacks
     *
     * @return the value of stacks
     */
    public List<TrayStack> getStacks() {
        return stacks;
    }

    /**
     * Set the value of stacks
     *
     * @param stacks new value of stacks
     */
    public void setStacks(List<TrayStack> stacks) {
        this.stacks = stacks;
        this.repaint();
    }

    private class InOutMouseAdapter extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            mousePoint = new Point2D.Double(e.getX(), e.getY());
            InOutJPanel.this.repaint();
//            super.mouseMoved(e); 

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            xoffset -= (e.getX() - mousePoint.x);
            yoffset -= (e.getY() - mousePoint.y);
            mousePoint = new Point2D.Double(e.getX(), e.getY());
            InOutJPanel.this.repaint();
//            super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private double xoffset;

    /**
     * Get the value of xoffset
     *
     * @return the value of xoffset
     */
    public double getXoffset() {
        return xoffset;
    }

    /**
     * Set the value of xoffset
     *
     * @param xoffset new value of xoffset
     */
    public void setXoffset(double xoffset) {
        this.xoffset = xoffset;
    }

    private double yoffset;

    /**
     * Get the value of yoffset
     *
     * @return the value of yoffset
     */
    public double getYoffset() {
        return yoffset;
    }

    /**
     * Set the value of yoffset
     *
     * @param yoffset new value of yoffset
     */
    public void setYoffset(double yoffset) {
        this.yoffset = yoffset;
    }

    private final InOutMouseAdapter myMouseAdapter;

    private Point2D.Double mousePoint = new Point2D.Double();

    private @Nullable
    PoseType robotPose;

    public @Nullable
    PoseType getRobotPose() {
        return robotPose;
    }

    public void setRobotPose(@Nullable PoseType robotPose) {
        this.robotPose = robotPose;
        this.repaint();
    }

    private @Nullable
    TrayStack holdingFromStack = null;

    public @Nullable
    TrayStack getHoldingFromStack() {
        return holdingFromStack;
    }

    public void setHoldingFromStack(@Nullable TrayStack holdingFromStack) {
        this.holdingFromStack = holdingFromStack;
    }

    public double poseStackZDiff(TrayStack stack) {
        if (stack == null || robotPose == null) {
            return Double.NaN;
        }

        PointType posePoint = robotPose.getPoint();
        if (null == posePoint) {
            return Double.NaN;
        }
        Point2D.Double point = new Point2D.Double(posePoint.getX(), posePoint.getY());
        if (insideStack(stack, point)) {
            final double stackTopZ = stack.z + stack.count * stack.height;
            double zdiff = stackTopZ - posePoint.getZ();
            return zdiff;
        } else {
            return Double.NaN;
        }
    }

    public static boolean insideStack(TrayStack stack, Point2D.Double point) {
        Rectangle2D.Double rect = new Rectangle2D.Double(stack.x, stack.y, stack.width, stack.length);
        double xdiff = point.x - rect.x;
        double ydiff = point.y - rect.y;
        double c = Math.cos(stack.rotationRadians);
        double s = Math.sin(stack.rotationRadians);
        double xdiffrot = xdiff * c + ydiff * s;
        double ydiffrot = ydiff * c - xdiff * s;
//        g2d.setColor(Color.GREEN);
//        g2d.draw(new Line2D.Double(rect.x, rect.y, rect.x + xdiffrot, rect.y + ydiffrot));
        return ((xdiffrot < rect.width) && (ydiffrot < stack.length) && (xdiffrot > 0) && (ydiffrot > 0));

    }

    Point2D.Double world2Display(Point2D.Double worldPoint, double z) {
        return world2Display(worldPoint.x, worldPoint.y, z);
    }

    Point2D.Double world2Display(double x, double y, double z) {
        final double hvac = Math.cos(Math.toRadians(heightViewAngle));
        final double hvas = Math.sin(Math.toRadians(heightViewAngle));
        return new Point2D.Double(x - xoffset, this.getHeight() - y * hvac - z * hvas - yoffset);
    }

    Point2D.Double world2Display(PointType worldPoint) {
        return world2Display(worldPoint.getX(), worldPoint.getY(), worldPoint.getZ());
    }

    Point2D.Double displayToWorld(Point2D.Double displayPoint, double z) {
        final double hvac = Math.cos(Math.toRadians(heightViewAngle));
        final double hvas = Math.sin(Math.toRadians(heightViewAngle));
        return new Point2D.Double(displayPoint.x + xoffset, (this.getHeight() - z * hvas - displayPoint.y - yoffset) / hvac);
    }

    private TrayStack holding;

    /**
     * Get the value of holding
     *
     * @return the value of holding
     */
    public TrayStack getHolding() {
        return holding;
    }

    /**
     * Set the value of holding
     *
     * @param holding new value of holding
     */
    public void setHolding(TrayStack holding) {
        this.holding = holding;
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, 3000, 3000);
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.black);
        final double hvac = Math.cos(Math.toRadians(heightViewAngle));
        final double hvas = Math.sin(Math.toRadians(heightViewAngle));
        if (hvac > Double.MIN_NORMAL && hvas > Double.MIN_NORMAL) {
            final Point2D.Double worldMousePoint = displayToWorld(mousePoint, 0.0);

            g.drawString(worldMousePoint.toString(), 20, 20);

            final Point2D.Double robotPoint;
            final PointType robotPosePoint;
            double minZ = Double.POSITIVE_INFINITY;
            if (null != robotPose) {
                robotPosePoint = robotPose.getPoint();
                if (null != robotPosePoint) {
                    g.drawString("robot:" + robotPosePoint.getX() + ", " + robotPosePoint.getY() + ", " + robotPosePoint.getZ(), 20, 40);
                    robotPoint = world2Display(robotPosePoint);
                } else {
                    robotPoint = null;
                }
            } else {
                robotPoint = null;
                robotPosePoint = null;
            }

            if (null != stacks) {

                for (TrayStack stack : stacks) {
                    final double stackZDiff = poseStackZDiff(stack);
//                    Rectangle2D.Double rect = new Rectangle2D.Double(stack.x, stack.y, stack.width, stack.length);
                    final boolean inSide = insideStack(stack, worldMousePoint) || (null != robotPoint && stackZDiff > 0);
//                final AffineTransform transform = g2d.getTransform();
                    final int stackLevels = stack.count + 1;

                    drawStackObject(stack, stackLevels, inSide, g2d);
                    if (minZ > stack.z) {
                        minZ = stack.z;
                    }
                    final Point2D.Double stackDisplayPoint = world2Display(stack.x, stack.y, stack.z);
                    g2d.drawString(stack.name, (float) stackDisplayPoint.x, (float) stackDisplayPoint.y);
                }
            }

            if (null != robotPosePoint && Double.isFinite(minZ)) {
                final Point2D.Double robotShadowPoint
                        = world2Display(robotPosePoint.getX(), robotPosePoint.getY(), minZ);
                g.setColor(Color.gray);
                drawCross(g2d, robotShadowPoint);
            }
            if (null != robotPoint) {
                if (null != holding) {
                    holding.x = robotPoint.x;
                    holding.y = robotPoint.y;
                    holding.z = robotPose.getPoint().getZ() - holding.height;
                    drawStackObject(holding, 2, false, g2d);
                }
                g.setColor(Color.black);
                drawCross(g2d, robotPoint);
            }
        }
    }

    private void drawStackObject(TrayStack stack, final int stackLevels, final boolean inSide, Graphics2D g2d) {
        final double src = Math.cos(stack.rotationRadians);
        final double srs = Math.sin(stack.rotationRadians);
        final double x1 = stack.x;
        final double y1 = stack.y;
        final double x2 = x1 + stack.width * src;
        final double y2 = y1 + stack.width * srs;

        final double x3 = x2 - stack.length * srs;
        final double y3 = y2 + stack.length * src;
        final double x4 = x1 - stack.length * srs;
        final double y4 = y1 + stack.length * src;
        for (int i = 0; i < stackLevels; i++) {
            final double stacklevel = stack.height * i + stack.z;
            Point2D.Double p1 = world2Display(x1, y1, stacklevel);
            Point2D.Double p2 = world2Display(x2, y2, stacklevel);
            Point2D.Double p3 = world2Display(x3, y3, stacklevel);
            Point2D.Double p4 = world2Display(x4, y4, stacklevel);
            Path2D.Double path = fourPointsClosedPath(p1, p2, p3, p4);

            if (i > 0) {
                final double stacklevelnext = stack.height * (i - 1);
                final Point2D.Double p1Next = world2Display(x1, y1, stacklevelnext);
                final Point2D.Double p2Next = world2Display(x2, y2, stacklevelnext);
                final Point2D.Double p3Next = world2Display(x3, y3, stacklevelnext);
                final Point2D.Double p4Next = world2Display(x4, y4, stacklevelnext);
                if (inSide) {
                    g2d.setColor(Color.red);
                    g2d.fill(fourPointsClosedPath(p1, p1Next, p2Next, p2));
                    g2d.fill(fourPointsClosedPath(p2, p2Next, p3Next, p3));
                    g2d.fill(fourPointsClosedPath(p3, p3Next, p4Next, p4));
                    g2d.fill(fourPointsClosedPath(p4, p4Next, p1Next, p1));
                }
                g2d.setColor(Color.black);
                g2d.draw(new Line2D.Double(p1, p1Next));
                g2d.draw(new Line2D.Double(p2, p2Next));
                g2d.draw(new Line2D.Double(p3, p3Next));
                g2d.draw(new Line2D.Double(p4, p4Next));
//                        g2d.draw(new Line2D.Double(rect.x + rect.width, rect.y + stack.length, rect.x + rect.width, rect.y + stack.length - yinc));
//                        g2d.draw(new Line2D.Double(rect.x, rect.y + stack.length, rect.x, rect.y + stack.length - yinc));
            }
//                    g2d.rotate(stack.rotationRadians, rect.x, rect.y);
//                    g2d.scale(1.0, hvac);

            if (inSide) {
                g2d.setColor(Color.red);
                g2d.fill(path);
            }
            g2d.setColor(Color.black);
            g2d.draw(path);
            final Point2D.Double stackLevelDisplayPoint = world2Display(x1, y1, stacklevel);
            g2d.drawString(Integer.toString(i), (float) stackLevelDisplayPoint.x, (float) stackLevelDisplayPoint.y);
//                    rect.y -= yinc;
//                    g2d.setTransform(transform);
        }
    }

    private void drawCross(Graphics2D g2d, final Point2D.Double robotPoint) {
        g2d.draw(new Line2D.Double(robotPoint.x - 10, robotPoint.y, robotPoint.x + 10, robotPoint.y));
        g2d.draw(new Line2D.Double(robotPoint.x, robotPoint.y - 10, robotPoint.x, robotPoint.y + 10));
    }

    private Path2D.Double fourPointsClosedPath(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4) {
        Path2D.Double path = new Path2D.Double();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.lineTo(p4.x, p4.y);
        path.closePath();
        return path;
    }
    private double heightViewAngle = 30.0;

    public double getHeightViewAngle() {
        return heightViewAngle;
    }

    public void setHeightViewAngle(double heightViewAngle) {
        this.heightViewAngle = heightViewAngle;
        this.repaint();
    }

}
