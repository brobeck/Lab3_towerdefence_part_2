package edu.chl.hajo.td.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/*
        JUnit testing of model
 */
public class ModelTest {

    @Test
    public void testPath() throws Exception {
         List<String> strPts = Arrays.asList(
                "0,3", "3,3", "3,9", "8,9", "8,4", "12,4",
                "12,12", "3,12", "3,17", "17,17",
                "17,6", "20,6");
        Path p = new Path(0, strPts, TDBuilder.getTileSize());

        assertTrue(true);

        // etc.
    }

    // Add more tests as needed

}