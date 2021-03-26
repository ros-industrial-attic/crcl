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
package crcl.ui.misc;

import crcl.base.CRCLProgramType;
import crcl.base.MiddleCommandType;
import crcl.base.MoveToType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.utils.CRCLPosemath;
import static crcl.utils.CRCLUtils.getNonNullFilteredList;
import static crcl.utils.CRCLUtils.getNonNullIterable;
import static crcl.utils.CRCLUtils.getNonNullPoint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/*
 * 
 * NOTE: Comments beginning with {@literal @} or {@literal >>>} are used by Checker Framework Comments
 * beginning with {@literal @} must have no spaces in the comment or Checker will ignore
 * it.
 *
 * See http://types.cs.washington.edu/checker-framework for null pointer
 * checks. This file can be compiled without the Checker Framework, but using
 * the framework allows potential NullPointerExceptions to be found.
 */

 /*>>>
import org.checkerframework.checker.nullness.qual.*;
 */
/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ProgramPlotter {

    public ProgramPlotter(View view) {
        this.view = view;
    }

    private boolean autoScale = true;

    public static enum View {
        OVERHEAD, SIDE;
    }

    final private View view;

    /**
     * Get the value of view
     *
     * @return the value of view
     */
    public View getView() {
        return view;
    }

    /**
     * Get the value of autoScale
     *
     * @return the value of autoScale
     */
    public boolean isAutoScale() {
        return autoScale;
    }

    /**
     * Set the value of autoScale
     *
     * @param autoScale new value of autoScale
     */
    public void setAutoScale(boolean autoScale) {
        this.autoScale = autoScale;
    }

    private Rectangle2D.Double bounds = new Rectangle2D.Double(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    private static boolean isFinite(double x) {
        return !Double.isInfinite(x) && !Double.isNaN(x);
    }

    public boolean hasFiniteBounds() {
        return null != bounds
                && isFinite(bounds.x)
                && isFinite(bounds.y)
                && isFinite(bounds.width)
                && isFinite(bounds.height);
    }

    /**
     * Get the value of bounds
     *
     * @return the value of bounds
     */
    public Rectangle2D.Double getBounds() {
        return bounds;
    }

    /**
     * Set the value of bounds
     *
     * @param bounds new value of bounds
     */
    public void setBounds(Rectangle2D.Double bounds) {
        this.bounds = bounds;
    }

    private final double[] selectionMin = new double[]{0.0, 0.0, 0.0};

    /**
     * Get the value of selectionMin
     *
     * @return the value of selectionMin
     */
    public double[] getSelectionMin() {
        return selectionMin;
    }

    private final double[] selectionMax = new double[]{1.0, 1.0, 1.0};

    /**
     * Get the value of selectionMax
     *
     * @return the value of selectionMax
     */
    public double[] getSelectionMax() {
        return selectionMax;
    }

    public Rectangle2D.Double findBounds(CRCLProgramType program) {
        Rectangle2D.Double rect = new Rectangle2D.Double();
        double xmin = Double.POSITIVE_INFINITY;
        double xmax = Double.NEGATIVE_INFINITY;
        double ymin = Double.POSITIVE_INFINITY;
        double ymax = Double.NEGATIVE_INFINITY;
        int movecount = 0;
        final Iterable<MiddleCommandType> middleCommandsIterable
                = getNonNullIterable(program.getMiddleCommand());
        for (MiddleCommandType cmd : middleCommandsIterable) {
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                PoseType endPose = moveCmd.getEndPosition();
                if (null != endPose) {
                    movecount++;
                    final PoseType endPosition = CRCLPosemath.getNonNullEndPosition((MoveToType) cmd);
                    Point2D.Double pt2D = toPoint2D(endPosition);
                    if (Double.isFinite(pt2D.x) && Double.isFinite(pt2D.y)) {
                        xmin = Math.min(xmin, pt2D.x);
                        xmax = Math.max(xmax, pt2D.x);
                        ymin = Math.min(ymin, pt2D.y);
                        ymax = Math.max(ymax, pt2D.y);
                    }
                }
            }
        }
        if (null != currentPoint) {
            Point2D.Double pt2D = toPoint2D(currentPoint);
            if (Double.isFinite(pt2D.x) && Double.isFinite(pt2D.y)) {
                xmin = Math.min(xmin, pt2D.x);
                xmax = Math.max(xmax, pt2D.x);
                ymin = Math.min(ymin, pt2D.y);
                ymax = Math.max(ymax, pt2D.y);
            }
        }
        if (null != initPoint) {
            Point2D.Double pt2D = toPoint2D(initPoint);
            if (Double.isFinite(pt2D.x) && Double.isFinite(pt2D.y)) {
                xmin = Math.min(xmin, pt2D.x);
                xmax = Math.max(xmax, pt2D.x);
                ymin = Math.min(ymin, pt2D.y);
                ymax = Math.max(ymax, pt2D.y);
            }
        }
        if (movecount < 4 && null != bounds && Double.isFinite(bounds.x) && Double.isFinite(bounds.width) && Double.isFinite(bounds.y) && Double.isFinite(bounds.height)) {
            xmin = Math.min(xmin, bounds.x);
            xmax = Math.max(xmax, bounds.x + bounds.width);
            ymin = Math.min(ymin, bounds.y);
            ymax = Math.max(ymax, bounds.y + bounds.height);
        }
        rect.x = xmin;
        rect.width = xmax - xmin;
        rect.y = ymin;
        rect.height = ymax - ymin;
        if (rect.width < 10 || rect.height < 10) {
            xmin -= 5;
            xmax += 5;
            ymin -= 5;
            ymax += 5;
            rect.x = xmin;
            rect.width = xmax - xmin;
            rect.y = ymin;
            rect.height = ymax - ymin;
        }
        return rect;
    }

    private int xMargin = 40;

    /**
     * Get the value of xMargin
     *
     * @return the value of xMargin
     */
    public int getxMargin() {
        return xMargin;
    }

    /**
     * Set the value of xMargin
     *
     * @param xMargin new value of xMargin
     */
    public void setxMargin(int xMargin) {
        this.xMargin = xMargin;
    }

    private int yMargin = 40;

    /**
     * Get the value of yMargin
     *
     * @return the value of yMargin
     */
    public int getyMargin() {
        return yMargin;
    }

    /**
     * Set the value of yMargin
     *
     * @param yMargin new value of yMargin
     */
    public void setyMargin(int yMargin) {
        this.yMargin = yMargin;
    }

    public Point2D.Double toPoint2D(PointType pt) {
        switch (view) {
            case SIDE:
                return CRCLPosemath.rzPoint2D(pt);

            case OVERHEAD:
            default:
                return CRCLPosemath.xyPoint2D(pt);
        }
    }

    public Point2D.Double toPoint2D(PoseType pose) {
        final PointType pt = getNonNullPoint(pose);
        return toPoint2D(pt);
    }

    private Color background = Color.white;

    /**
     * Get the value of background
     *
     * @return the value of background
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Set the value of background
     *
     * @param background new value of background
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    private Color afterIndexLineColor = Color.black;

    /**
     * Get the value of afterIndexLineColor
     *
     * @return the value of afterIndexLineColor
     */
    public Color getAfterIndexLineColor() {
        return afterIndexLineColor;
    }

    /**
     * Set the value of afterIndexLineColor
     *
     * @param afterIndexLineColor new value of afterIndexLineColor
     */
    public void setAfterIndexLineColor(Color afterIndexLineColor) {
        this.afterIndexLineColor = afterIndexLineColor;
    }

    private Color beforeIndexLineColor = Color.green.darker();

    /**
     * Get the value of beforeIndexLineColor
     *
     * @return the value of beforeIndexLineColor
     */
    public Color getBeforeIndexLineColor() {
        return beforeIndexLineColor;
    }

    /**
     * Set the value of beforeIndexLineColor
     *
     * @param beforeIndexLineColor new value of beforeIndexLineColor
     */
    public void setBeforeIndexLineColor(Color beforeIndexLineColor) {
        this.beforeIndexLineColor = beforeIndexLineColor;
    }

    private Color beforeIndexPointColor = Color.blue.darker();

    /**
     * Get the value of beforeIndexPointColor
     *
     * @return the value of beforeIndexPointColor
     */
    public Color getBeforeIndexPointColor() {
        return beforeIndexPointColor;
    }

    /**
     * Set the value of beforeIndexPointColor
     *
     * @param beforeIndexPointColor new value of beforeIndexPointColor
     */
    public void setBeforeIndexPointColor(Color beforeIndexPointColor) {
        this.beforeIndexPointColor = beforeIndexPointColor;
    }

    private Color afterIndexPointColor = Color.orange;

    /**
     * Get the value of afterIndexPointColor
     *
     * @return the value of afterIndexPointColor
     */
    public Color getAfterIndexPointColor() {
        return afterIndexPointColor;
    }

    /**
     * Set the value of afterIndexPointColor
     *
     * @param afterIndexPointColor new value of afterIndexPointColor
     */
    public void setAfterIndexPointColor(Color afterIndexPointColor) {
        this.afterIndexPointColor = afterIndexPointColor;
    }

    private Dimension dimension = new Dimension(256, 256);

    /**
     * Get the value of dimension
     *
     * @return the value of dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Set the value of dimension
     *
     * @param dimension new value of dimension
     */
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public BufferedImage plotProgram(CRCLProgramType program, int programIndex) {
        BufferedImage bi = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bi.createGraphics();
        paint(program, g2d, programIndex);
        return bi;
    }

    private @MonotonicNonNull
    PointType currentPoint;

    /**
     * Get the value of currentPoint
     *
     * @return the value of currentPoint
     */
    public @Nullable
    PointType getCurrentPoint() {
        return currentPoint;
    }

    /**
     * Set the value of currentPoint
     *
     * @param currentPoint new value of currentPoint
     */
    public void setCurrentPoint(PointType currentPoint) {
        this.currentPoint = currentPoint;
    }

    private Color currentPointColor = Color.red.darker();
    private Color currentPointAlphaColor = new Color(currentPointColor.getRed(), currentPointColor.getGreen(), currentPointColor.getBlue(), 100);

    /**
     * Get the value of currentPointColor
     *
     * @return the value of currentPointColor
     */
    public Color getCurrentPointColor() {
        return currentPointColor;
    }

    /**
     * Set the value of currentPointColor
     *
     * @param currentPointColor new value of currentPointColor
     */
    public void setCurrentPointColor(Color currentPointColor) {
        this.currentPointColor = currentPointColor;
        currentPointAlphaColor = new Color(currentPointColor.getRed(), currentPointColor.getGreen(), currentPointColor.getBlue(), 100);
    }

    private Color marginColor = new Color(100, 100, 100);

    /**
     * Get the value of marginColor
     *
     * @return the value of marginColor
     */
    public Color getMarginColor() {
        return marginColor;
    }

    /**
     * Set the value of marginColor
     *
     * @param marginColor new value of marginColor
     */
    public void setMarginColor(Color marginColor) {
        this.marginColor = marginColor;
    }

    private Color outerColor = new Color(100, 100, 100, 100);

    /**
     * Get the value of outerColor
     *
     * @return the value of outerColor
     */
    public Color getOuterColor() {
        return outerColor;
    }

    /**
     * Set the value of outerColor
     *
     * @param outerColor new value of outerColor
     */
    public void setOuterColor(Color outerColor) {
        this.outerColor = outerColor;
    }

    private @Nullable
    PointType initPoint;

    /**
     * Get the value of initPoint
     *
     * @return the value of initPoint
     */
    public @Nullable
    PointType getInitPoint() {
        return initPoint;
    }

    /**
     * Set the value of initPoint
     *
     * @param initPoint new value of initPoint
     */
    public void setInitPoint(@Nullable PointType initPoint) {
        this.initPoint = initPoint;
    }

    private boolean fillMarginEnabled;

    /**
     * Get the value of fillMarginEnabled
     *
     * @return the value of fillMarginEnabled
     */
    public boolean isFillMarginEnabled() {
        return fillMarginEnabled;
    }

    /**
     * Set the value of fillMarginEnabled
     *
     * @param fillMarginEnabled new value of fillMarginEnabled
     */
    public void setFillMarginEnabled(boolean fillMarginEnabled) {
        this.fillMarginEnabled = fillMarginEnabled;
    }

    public void paint(CRCLProgramType program, Graphics2D g2d, int programIndex) {
        if (autoScale || null == bounds) {
            setBounds(findBounds(program));
        }
        double heightUnder = 0;
        double widthUnder = 0;
        if (bounds.height < bounds.width) {
            heightUnder = bounds.width - bounds.height;
        } else {
            widthUnder = bounds.height - bounds.width;
        }
        double scale = Math.min((dimension.width - xMargin) / bounds.width,
                (dimension.height - yMargin) / bounds.height);
        g2d.setBackground(background);
        g2d.clearRect(0, 0, dimension.width, dimension.height);
        double fullMarginWidth = dimension.width - bounds.width * scale;
        double fullMarginHeight = dimension.height - bounds.height * scale;
        if (fillMarginEnabled) {
            g2d.setColor(marginColor);

            Rectangle2D.Double marginLeft = new Rectangle2D.Double(0, 0, fullMarginWidth / 2, dimension.height);
            g2d.fill(marginLeft);
            Rectangle2D.Double marginUpper = new Rectangle2D.Double(0, 0,
                    dimension.width, fullMarginHeight / 2);
            g2d.fill(marginUpper);
            Rectangle2D.Double marginRight = new Rectangle2D.Double(dimension.width - fullMarginWidth / 2,
                    0, fullMarginWidth / 2, dimension.height);
            g2d.fill(marginRight);
            Rectangle2D.Double marginBottom = new Rectangle2D.Double(0, dimension.height - fullMarginHeight / 2,
                    dimension.width, fullMarginHeight / 2);
            g2d.fill(marginBottom);
        }

        g2d.translate(0, dimension.height);
        g2d.scale(scale, -scale);
        g2d.translate(-bounds.x + fullMarginWidth / (2 * scale),
                -bounds.y + fullMarginHeight / (2 * scale));

        Point2D.Double initPoint2D = null;
        if (null != initPoint) {
            initPoint2D = toPoint2D(initPoint);
        }
        Point2D.Double lastPoint = initPoint2D;
        final double pointSize = Math.max(dimension.width, dimension.height) / 30.0;
        final double halfPointSize = pointSize / 2.0;
        if (null != currentPoint) {
            Point2D.Double currentPoint2D = toPoint2D(currentPoint);
            g2d.setColor(currentPointAlphaColor);
            paintFullCrossHairs(g2d, currentPoint2D, halfPointSize, pointSize, scale, heightUnder, widthUnder);
        }
        final List<MiddleCommandType> middleCommandsList
                = getNonNullFilteredList(program.getMiddleCommand());

        for (int i = 0; i < middleCommandsList.size(); i++) {
            MiddleCommandType cmd = middleCommandsList.get(i);
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                final PoseType endPosition = CRCLPosemath.getNonNullEndPosition(moveCmd);
                Point2D.Double nextPoint = toPoint2D(endPosition);
                if (null != lastPoint) {
                    if (i > programIndex + 1) {
                        g2d.setColor(afterIndexLineColor);
                    } else {
                        g2d.setColor(beforeIndexLineColor);
                    }
                    g2d.draw(new Line2D.Double(nextPoint, lastPoint));
                }
                lastPoint = nextPoint;
            }
        }
        lastPoint = initPoint2D;
        for (int i = 0; i < middleCommandsList.size() && i <= programIndex; i++) {
            MiddleCommandType cmd = middleCommandsList.get(i);
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                final PoseType endPosition = CRCLPosemath.getNonNullEndPosition(moveCmd);
                Point2D.Double nextPoint = toPoint2D(endPosition);
                if (null != lastPoint) {
                    if (i > programIndex + 1) {
                        g2d.setColor(afterIndexLineColor);
                    } else {
                        g2d.setColor(beforeIndexLineColor);
                    }
                    g2d.draw(new Line2D.Double(nextPoint, lastPoint));
                }
                lastPoint = nextPoint;
            }
        }
        for (int i = 0; i < middleCommandsList.size(); i++) {
            MiddleCommandType cmd = middleCommandsList.get(i);
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                final PoseType endPosition = CRCLPosemath.getNonNullEndPosition(moveCmd);
                Point2D.Double nextPoint = toPoint2D(endPosition);
                if (i > programIndex + 1) {
                    g2d.setColor(afterIndexPointColor);
                } else {
                    g2d.setColor(beforeIndexPointColor);
                }
                if (view == View.OVERHEAD) {
                    if (nextPoint.x > getXMax()) {
                        continue;
                    }
                    if (nextPoint.x < getXMin()) {
                        continue;
                    }
                    if (nextPoint.y > getYMax()) {
                        continue;
                    }
                    if (nextPoint.y < getYMin()) {
                        continue;
                    }
                } else {
                    if (nextPoint.y > getZMax()) {
                        continue;
                    }
                    if (nextPoint.y < getZMin()) {
                        continue;
                    }
                }
                g2d.fill(new Arc2D.Double(nextPoint.x - halfPointSize, nextPoint.y - halfPointSize, pointSize, pointSize, 0, 360.0, Arc2D.PIE));
            }
        }
        for (int i = 0; i < middleCommandsList.size() && i <= programIndex + 1; i++) {
            MiddleCommandType cmd = middleCommandsList.get(i);
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                final PoseType endPosition = CRCLPosemath.getNonNullEndPosition(moveCmd);
                Point2D.Double nextPoint = toPoint2D(endPosition);
                if (i > programIndex + 1) {
                    g2d.setColor(afterIndexPointColor);
                } else {
                    g2d.setColor(beforeIndexPointColor);
                }
                g2d.fill(new Arc2D.Double(nextPoint.x - halfPointSize, nextPoint.y - halfPointSize, pointSize, pointSize, 0, 360.0, Arc2D.PIE));
                lastPoint = nextPoint;
            }
        }
        if (null != lastPoint) {
            g2d.setColor(beforeIndexPointColor);
            paintCrossHairs(g2d, lastPoint, halfPointSize, pointSize, scale, heightUnder, widthUnder);
        }
        if (null != currentPoint) {
            Point2D.Double currentPoint2D = toPoint2D(currentPoint);
            g2d.setColor(currentPointColor);
            paintCrossHairs(g2d, currentPoint2D, halfPointSize, pointSize, scale, heightUnder, widthUnder);
        }

        if (view == View.OVERHEAD) {
            g2d.setColor(outerColor);
            Rectangle2D.Double outerleft = new Rectangle2D.Double(bounds.x, bounds.y,
                    selectionMin[0] * bounds.width,
                    bounds.height);
            g2d.fill(outerleft);
            Rectangle2D.Double outerRight = new Rectangle2D.Double(getXMax(), bounds.y,
                    bounds.width - selectionMax[0] * bounds.width,
                    bounds.height);
            g2d.fill(outerRight);
            Rectangle2D.Double outerTop = new Rectangle2D.Double(bounds.x, bounds.y,
                    bounds.width,
                    selectionMin[1] * bounds.height);
            g2d.fill(outerTop);
            Rectangle2D.Double outerBottom = new Rectangle2D.Double(bounds.x, getYMax(),
                    bounds.width,
                    bounds.height - selectionMax[1] * bounds.height);
            g2d.fill(outerBottom);
        } else {
            g2d.setColor(outerColor);

            Rectangle2D.Double outerTop = new Rectangle2D.Double(bounds.x, bounds.y,
                    bounds.width,
                    selectionMin[2] * bounds.height);
            g2d.fill(outerTop);
            Rectangle2D.Double outerBottom = new Rectangle2D.Double(bounds.x, getZMax(),
                    bounds.width,
                    bounds.height - selectionMax[2] * bounds.height);
            g2d.fill(outerBottom);
        }
    }

    public double getXMax() {
        return bounds.x + selectionMax[0] * bounds.width;
    }

    public double getYMax() {
        return bounds.y + bounds.height * selectionMax[1];
    }

    public double getZMax() {
        return bounds.y + bounds.height * selectionMax[2];
    }

    public double getXMin() {
        return bounds.x + selectionMin[0] * bounds.width;
    }

    public double getYMin() {
        return bounds.y + bounds.height * selectionMin[1];
    }

    public double getZMin() {
        return bounds.y + bounds.height * selectionMin[2];
    }

    public void paintFullCrossHairs(Graphics2D g2d, Point2D.Double currentPoint2D, final double halfPointSize, final double pointSize, double scale, double heightUnder, double widthUnder) {
        g2d.fill(new Arc2D.Double(currentPoint2D.x - halfPointSize, currentPoint2D.y - halfPointSize, pointSize, pointSize, 0, 360.0, Arc2D.PIE));
        g2d.draw(new Line2D.Double(currentPoint2D.x, bounds.y - yMargin / (2 * scale) - heightUnder / 2.0,
                currentPoint2D.x, bounds.y + bounds.height + yMargin / (2 * scale) + heightUnder / 2.0));
        g2d.draw(new Line2D.Double(bounds.x - xMargin / (2 * scale) - widthUnder / 2.0, currentPoint2D.y,
                bounds.x + bounds.width + xMargin / (2 * scale) + widthUnder / 2.0, currentPoint2D.y));
    }

    public void paintCrossHairs(Graphics2D g2d, Point2D.Double currentPoint2D, final double halfPointSize, final double pointSize, double scale, double heightUnder, double widthUnder) {
        g2d.fill(new Arc2D.Double(currentPoint2D.x - halfPointSize, currentPoint2D.y - halfPointSize, pointSize, pointSize, 0, 360.0, Arc2D.PIE));
        g2d.draw(new Line2D.Double(currentPoint2D.x, bounds.y - yMargin / (2 * scale) - heightUnder / 2.0,
                currentPoint2D.x, bounds.y));
        g2d.draw(new Line2D.Double(currentPoint2D.x, bounds.y + bounds.height,
                currentPoint2D.x, bounds.y + bounds.height + yMargin / (2 * scale) + heightUnder / 2.0));
        g2d.draw(new Line2D.Double(bounds.x - xMargin / (2 * scale) - widthUnder / 2.0, currentPoint2D.y,
                bounds.x, currentPoint2D.y));
        g2d.draw(new Line2D.Double(bounds.x + bounds.width, currentPoint2D.y,
                bounds.x + bounds.width + xMargin / (2 * scale) + widthUnder / 2.0, currentPoint2D.y));
    }

//    public static void main(String[] args) throws CRCLException, IOException {
//        ProgramPlotter ppOverHead = new ProgramPlotter(View.OVERHEAD);
//        ppOverHead.setAutoScale(true);
//        CRCLProgramType program = CRCLSocket.readProgramFile("../crcl4java-base/src/test/resources/main/MOVE_ALL_RED_PEGS.xml");
////        PointType pt = CRCLPosemath.toPointType(new PmCartesian(300, +100, 0));
//        ppOverHead.getSelectionMin()[0] = 0.2;
//        ppOverHead.getSelectionMax()[0] = 0.8;
//        ppOverHead.getSelectionMin()[1] = 0.2;
//        ppOverHead.getSelectionMax()[1] = 0.8;
////        ppOverHead.setCurrentPoint(pt);
//        BufferedImage biOverhead = ppOverHead.plotProgram(program, 0);
//        ImageIO.write(biOverhead, "jpg", new File("overhead.jpg"));
//        ProgramPlotter ppSide = new ProgramPlotter(View.SIDE);
////        ppSide.setCurrentPoint(pt);
//        ppSide.getSelectionMin()[2] = 0.2;
//        ppSide.getSelectionMax()[2] = 0.8;
//        ppSide.setAutoScale(true);
//        BufferedImage biSide = ppSide.plotProgram(program, 0);
//        ImageIO.write(biSide, "jpg", new File("side.jpg"));
//    }
}
