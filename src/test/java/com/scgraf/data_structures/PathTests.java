package com.scgraf.data_structures;

import com.scgraf.data_structures.graph.Path;
import org.junit.Assert;

public class PathTests {
    @org.junit.Test
    public void testGetOppositeSide() {
        Path.Side top = Path.Side.TOP;
        Path.Side right = Path.Side.RIGHT;
        Path.Side bottom = Path.Side.BOTTOM;
        Path.Side left = Path.Side.LEFT;

        Assert.assertEquals(top.getOppositeSide(), bottom);
        Assert.assertEquals(right.getOppositeSide(), left);
        Assert.assertEquals(bottom.getOppositeSide(), top);
        Assert.assertEquals(left.getOppositeSide(), right);
    }

    @org.junit.Test
    public void testGetSideTurnedBy() {
        Path.Side top = Path.Side.TOP;
        Path.Side right = Path.Side.RIGHT;
        Path.Side bottom = Path.Side.BOTTOM;
        Path.Side left = Path.Side.LEFT;

        Assert.assertEquals(top.getSideTurnedBy(-3), right);
        Assert.assertEquals(top.getSideTurnedBy(-2), bottom);
        Assert.assertEquals(top.getSideTurnedBy(-1), left);
        Assert.assertEquals(top.getSideTurnedBy(0), top);
        Assert.assertEquals(top.getSideTurnedBy(1), right);
        Assert.assertEquals(top.getSideTurnedBy(2), bottom);
        Assert.assertEquals(top.getSideTurnedBy(3), left);
        Assert.assertEquals(top.getSideTurnedBy(4), top);

        Assert.assertEquals(right.getSideTurnedBy(-3), bottom);
        Assert.assertEquals(right.getSideTurnedBy(-2), left);
        Assert.assertEquals(right.getSideTurnedBy(-1), top);
        Assert.assertEquals(right.getSideTurnedBy(0), right);
        Assert.assertEquals(right.getSideTurnedBy(1), bottom);
        Assert.assertEquals(right.getSideTurnedBy(2), left);
        Assert.assertEquals(right.getSideTurnedBy(3), top);
        Assert.assertEquals(right.getSideTurnedBy(4), right);

        Assert.assertEquals(bottom.getSideTurnedBy(0), bottom);
        Assert.assertEquals(bottom.getSideTurnedBy(1), left);
        Assert.assertEquals(bottom.getSideTurnedBy(2), top);
        Assert.assertEquals(bottom.getSideTurnedBy(3), right);
        Assert.assertEquals(bottom.getSideTurnedBy(4), bottom);

        Assert.assertEquals(left.getSideTurnedBy(0), left);
        Assert.assertEquals(left.getSideTurnedBy(1), top);
        Assert.assertEquals(left.getSideTurnedBy(2), right);
        Assert.assertEquals(left.getSideTurnedBy(3), bottom);
        Assert.assertEquals(left.getSideTurnedBy(4), left);
    }
}
