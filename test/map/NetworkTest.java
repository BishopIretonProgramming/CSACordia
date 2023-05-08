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

        romeToBrundisium.addConnection(rome);
        romeToBrundisium.addConnection(brundisium);
        romeToPompeii.addConnection(rome);
        romeToPompeii.addConnection(pompeii);
        romeToFlorence.addConnection(rome);
        romeToFlorence.addConnection(florence);
        brundisiumToPompeii.addConnection(brundisium);
        brundisiumToPompeii.addConnection(pompeii);
        brundisiumToFlorence.addConnection(brundisium);
        brundisiumToFlorence.addConnection(florence);
        pompeiiToFlorence.addConnection(pompeii);
        pompeiiToFlorence.addConnection(florence);

        Network network = new Network();

        network.addCity(rome);
        network.addCity(brundisium);
        network.addCity(pompeii);
        network.addCity(florence);
        
        network.addPath(romeToBrundisium);
        network.addPath(romeToPompeii);
        network.addPath(romeToFlorence);
        network.addPath(brundisiumToPompeii);
        network.addPath(brundisiumToFlorence);
        network.addPath(pompeiiToFlorence);

        network.compute();

        network.visualize();
    }
}
