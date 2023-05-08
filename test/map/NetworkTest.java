package test.map;

//  imports
import src.map.*;
import static src.map.PathNode.PathType;

public class NetworkTest {
    
    public static void main(String... args) {
        CityNode rome = new CityNode("Rome");
        CityNode brundisium = new CityNode("Brundisium");
        CityNode pompeii = new CityNode("Pompeii");
        CityNode florence = new CityNode("Florence");

        PathNode romeToBrundisium = new PathNode("Rome to Brundisium", PathType.LAND);
        PathNode romeToPompeii = new PathNode("Rome to Pompeii", PathType.LAND);
        PathNode romeToFlorence = new PathNode("Rome to Florence", PathType.LAND);
        PathNode brundisiumToPompeii = new PathNode("Brundisium to Pompeii", PathType.SEA);
        PathNode brundisiumToFlorence = new PathNode("Brundisium to Florence", PathType.LAND);
        PathNode pompeiiToFlorence = new PathNode("Pompeii to Florence", PathType.LAND);

        romeToBrundisium.addConnections(rome, brundisium);
        romeToPompeii.addConnections(rome, pompeii);
        romeToFlorence.addConnections(rome, florence);
        brundisiumToPompeii.addConnections(brundisium, pompeii);
        brundisiumToFlorence.addConnections(brundisium, florence);
        pompeiiToFlorence.addConnections(pompeii, florence);

        Network network = new Network();

        network.addCities(rome, brundisium, pompeii, florence);
        
        network.addPaths(romeToBrundisium, romeToPompeii, romeToFlorence, brundisiumToPompeii, brundisiumToFlorence, pompeiiToFlorence);

        network.compute();

        network.visualize();
    }
}
