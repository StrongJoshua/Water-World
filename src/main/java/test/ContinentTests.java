package test;

import edu.gatech.scrumbags.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests out getContinent() method in WaterLocation class.
 *
 * @author Guillaume Noziere
 */
public class ContinentTests {

    @Test
    public void testLatitudeException1() {
        WaterLocation loc = new WaterLocation(-90.1, 0.0);
        try {
            loc.getContinent();
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLatitudeException2() {
        WaterLocation loc = new WaterLocation(90.1, 0.0);
        try {
            loc.getContinent();
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLatitudeException3() {
        WaterLocation loc = new WaterLocation(0.0, 0.0);
        try {
            loc.getContinent();
            assertTrue(true);
        } catch (IllegalArgumentException e) {
            assertFalse(true);
        }
    }

    @Test
    public void testLongitudeException1() {
        WaterLocation loc = new WaterLocation(0.0, -180.1);
        try {
            loc.getContinent();
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLongitudeException2() {
        WaterLocation loc = new WaterLocation(0.0, 180.1);
        try {
            loc.getContinent();
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLongitudeException3() {
        WaterLocation loc = new WaterLocation(0.0, 0.0);
        try {
            loc.getContinent();
            assertTrue(true);
        } catch (IllegalArgumentException e) {
            assertFalse(true);
        }
    }

    @Test
    public void testNorthAmericaMessage() {
        // location for Atlanta, GA, USA
        WaterLocation loc = new WaterLocation(33.7490, -84.3880);
        String msg = loc.getContinent();
        assertEquals("North America", msg);
    }

    @Test
    public void testNotNorthAmericaMessage() {
        // location for Tokyo, Japan
        WaterLocation loc = new WaterLocation(35.6895, 139.6917);
        String msg = loc.getContinent();
        assertNotEquals("North America", msg);
    }

    @Test
    public void testSouthAmericaMessage() {
        // location for Rio de Janeiro, Brazil
        WaterLocation loc = new WaterLocation(-22.9068, -43.1729);
        String msg = loc.getContinent();
        assertEquals("South America", msg);
    }

    @Test
    public void testNotSouthAmericaMessage() {
        // location for Tokyo, Japan
        WaterLocation loc = new WaterLocation(35.6895, 139.6917);
        String msg = loc.getContinent();
        assertNotEquals("South America", msg);
    }

    @Test
    public void testAfricaMessage() {
        // location for Dar es Salaam, Tanzania
        WaterLocation loc = new WaterLocation(-6.7924, 39.2083);
        String msg = loc.getContinent();
        assertEquals("Africa", msg);
    }

    @Test
    public void testNotAfricaMessage() {
        // location for Tokyo, Japan
        WaterLocation loc = new WaterLocation(35.6895, 139.6917);
        String msg = loc.getContinent();
        assertNotEquals("Africa", msg);
    }

    @Test
    public void testEuropeMessage() {
        // location for Paris, France
        WaterLocation loc = new WaterLocation(48.8566, 2.3522);
        String msg = loc.getContinent();
        assertEquals("Europe", msg);
    }

    @Test
    public void testNotEuropeMessage() {
        // location for Tokyo, Japan
        WaterLocation loc = new WaterLocation(35.6895, 139.6917);
        String msg = loc.getContinent();
        assertNotEquals("Europe", msg);
    }

    @Test
    public void testAsiaMessage() {
        // location for Tokyo, Japan
        WaterLocation loc = new WaterLocation(35.6895, 139.6917);
        String msg = loc.getContinent();
        assertEquals("Asia", msg);
    }

    @Test
    public void testNotAsiaMessage() {
        // location for New York, NY, USA
        WaterLocation loc = new WaterLocation(40.7128, -74.0059);
        String msg = loc.getContinent();
        assertNotEquals("Asia", msg);
    }

    @Test
    public void testOceaniaMessage() {
        // location for Sydney, Australia
        WaterLocation loc = new WaterLocation(-33.8688, 151.2093);
        String msg = loc.getContinent();
        assertEquals("Oceania", msg);
    }

    @Test
    public void testNotOceaniaMessage() {
        // location for New York, NY, USA
        WaterLocation loc = new WaterLocation(40.7128, -74.0059);
        String msg = loc.getContinent();
        assertNotEquals("Oceania", msg);
    }

    @Test
    public void testAntarcticaMessage() {
        // location for Byrd Station, Antarctica
        WaterLocation loc = new WaterLocation(-80.0, 119.0);
        String msg = loc.getContinent();
        assertEquals("Antarctica", msg);
    }

    @Test
    public void testNotAntarcticaMessage() {
        // location for New York, NY, USA
        WaterLocation loc = new WaterLocation(40.7128, -74.0059);
        String msg = loc.getContinent();
        assertNotEquals("Antarctica", msg);
    }

    @Test
    public void testUndefinedMessage() {
        // location for Byrd Station, Antarctica
        WaterLocation loc = new WaterLocation(-40.0, 75.0);
        String msg = loc.getContinent();
        assertEquals("undefined", msg);
    }

    @Test
    public void testNotUndefinedMessage() {
        // location for New York, NY, USA
        WaterLocation loc = new WaterLocation(40.7128, -74.0059);
        String msg = loc.getContinent();
        assertNotEquals("undefined", msg);
    }
}