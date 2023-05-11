//package test.map;
//
//  INSTALL JUNIT TO RUN THESE TESTS
////  imports
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import src.game.map.Network;
//import static src.game.map.PathNode.PathType.LAND;
//import static src.game.map.PathNode.PathType.SEA;
//
///**
// * A class to test the Network class using junit
// */
//
///*
//public class NetworkTest {
//
//    /**
//     * The number of nodes to be in the Network
//     */
//    private static int numNodes;
//
//    /**
//     * The Network to test
//     */
//    private static Network network;
//
//    /**
//     * Setup method to run before everything else
//     */
//    @BeforeAll
//    public static void setup() {
//        numNodes = 16;
//        network = new Network(numNodes);
//        network.connect(0, 1, LAND);
//        network.connect(0, 3, LAND);
//        network.connect(0, 5, SEA);
//        network.connect(5, 7, LAND);
//        network.connect(7, 9, SEA);
//        network.connect(15, 6, LAND);
//        network.connect(6, 3, LAND);
//        network.connect(8, 7, SEA);
//        network.connect(8, 9, SEA);
//        network.connect(7, 9, SEA);
//        network.connect(0, 7, LAND);
//        network.connect(8, 11, SEA);
//        network.connect(11, 12, LAND);
//        network.connect(9, 10, LAND);
//        network.connect(10, 15, LAND);
//        network.connect(5, 8, SEA);
//        network.connect(14, 13, SEA);
//    }
//
//    @Test
//    @DisplayName("(0, 1) -> (9, 10)")
//    public void testConnection1() {
//        assertEquals(5, network.computeDistanceBetween(0, 1, LAND, 10, 9, LAND));
//    }
//
//    @Test
//    @DisplayName("(0, 5) -> (13, 14)")
//    public void testConnection2() {
//        assertEquals(-1, network.computeDistanceBetween(0, 5, SEA, 14, 13, SEA));
//    }
//
//    @Test
//    @DisplayName("(0, 5) -> (7, 9)")
//    public void testConnection3() {
//        assertEquals(3, network.computeDistanceBetween(0, 5, SEA, 7, 9, SEA));
//    }
//}
