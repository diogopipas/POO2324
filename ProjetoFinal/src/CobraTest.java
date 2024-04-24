/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CobraTest {

    @Test
    public void testConstructor() {
        Cobra cobra = new Cobra(3, 5, 3, 1);
        assertEquals(3, cobra.getSegments().size());
        assertEquals(3, cobra.getSegments().get(0).getA().getX());
        assertEquals(5, cobra.getSegments().get(0).getA().getY());
    }

    @Test
    public void testMove() {
        Cobra cobra = new Cobra(3, 5, 3, 1);
        cobra.setDirection(Cobra.UP);
        cobra.move();
        assertEquals(4, cobra.getSegments().get(0).getA().getY());
    }

}
