///* Anthony Amedome
//  City Card
//  panel that shows the information about a city when clicked on
//  */
//package src.gui;
//
//  import java.awt.Graphics;
//  import javax.swing.JPanel;
//  import javax.swing.JFrame;
//  import javax.swing.JLabel;
//  import java.util.ArrayList;
//  import java.awt.Color;
//  import java.awt.Font;
//
//  public class CityCard extends JPanel{
//
//     private City city;
//     private String cityName;
//     private double[] coords;
//     private City[] connections;
//
//     //these have to do later
//     public boolean hasHouse;
//     public String[] playersBuilt;
//     public String[] playersOnCity;
//     public int cost;
//     public Good good;
//
//
//
//     public CityCard(City c){
//        city = c;
//        cityName = c.getName();
//        coords = new double[]{c.getX(), c.getY()};
//        connections = c.getConnections(true);
//        // on land to do later might have to ask it to be added in city
//        super.add(configureLabel());
//     }
//
//     public JLabel configureLabel() {
//        JLabel label = new JLabel(this.toString());
//        label.setBackground(new Color(222, 208, 120));
//        label.setFont(new Font("Serif", Font.BOLD, 50));
//        return label;
//     }
//
//     //converts all of the connections into an arraylist of their names so it can be printed easier
//     public ArrayList<String> connectionsToString(){
//        if (connections != null) {
//           ArrayList<String> stringConnections = new ArrayList<String>();
//           for (int i = 0; i < connections.length - 1 ;i++) {
//              String name = connections[i].getName();
//              stringConnections.add(name);
//           }
//          return stringConnections;
//        } else {
//           return null;
//        }
//     }
//
//     public String toString(){
//        return("City Name: " + cityName + "\n City Location: " + coords[0] + ", " + coords[1] + "\n Connected Cities: " + connectionsToString());
//     }
//
//     public JFrame drawCityCard() {
//        JFrame frame = new JFrame();
//        City NewYork = new City("NewYork",50,50);
//        frame.add(new CityCard(NewYork));
//        frame.setVisible(true);
//        frame.setSize(400,400);
//        return frame;
//     }
//
//
//  }
//