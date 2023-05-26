package resources.saves;

/*
 * @Author Joseph Murray
 * Created: 5/25/2023
 * FileReaderTools: helpful tools to read my save files with, really only made it until we have an actual file reading/saving system set up
*/

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import src.game.cards.*;

public class FileReaderTools {

                                        //Methods

    private File file;
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    private Scanner scanner;
    private PrintWriter printWriter;

                                        //Constructors

    /**
     * Constructor
     * @param fileName
    */
    public FileReaderTools(String fileName) {
        file = new File(fileName);

        try {
            scanner = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            System.out.println("no file");
        }

        readFile();
    }

                                        //Methods

    /**
     * helper method for constructor that reads from the file and transfers the information into the data ArrayList
    */
    private void readFile() {
        String input;
        int currentSectionID = -1;

        while(scanner.hasNext()) {
            input = scanner.next();

            if(input.contains("SECTION")) {
               currentSectionID++;
               data.add(new ArrayList<String>());
               data.get(currentSectionID).add(scanner.next());
            }
            else {
               data.get(currentSectionID).add(input);
            }
        }

        scanner.close();
    }
    
    /**
     * each section of data in the file is marked on a line by the key-word SECTION followed by a section name
     * @param sectionName name of the section that is searched for
     * @return An ArrayList of the data section
    */
    public ArrayList<String> getDataSection(String sectionName) {      
      for(ArrayList<String> section : data) {
         if(section.get(0).equals(sectionName)) {
            return section;
         }      
      }
      return null;
    }
 
    /**
     * returns an ArrayList of BufferedImages 
     * @param sectionName name of the section that is searched for
     * @return an ArrayList of BufferedImages
    */
    public ArrayList<BufferedImage> getImages(String sectionName) {
      ArrayList<String> section = getDataSection(sectionName);
      ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
      
        String path = "";

      for(String fileName : section) {
        if(fileName.equals("PATH")) {
            path = fileName;
        }
        else {
            try {
                imageList.add(ImageIO.read(new File(path + fileName)));
            }
            catch(IOException e) {
                System.out.println("Image file not found");
            }
        }
      }      
      
      return imageList;
    }
    
    /**
     * returns an ArrayList of Classes 
     * @param sectionName name of the section that is searched for
     * @return an ArrayList of Classes
    */
    public ArrayList<Class<Object>> getClasses(String sectionName) {
        ArrayList<String> section = getDataSection(sectionName);
        ArrayList<Class<Object>> classList = new ArrayList<Class<Object>>();
      
        for(String className : section) {
            try {
                classList.add((Class<Object>) Class.forName(className));
            }
            catch(ClassNotFoundException e) {
                System.out.println("class not found");
            }
        }      
      
      return classList;
    }

                                        //Getters/Setters/toString

    public ArrayList<ArrayList<String>> getData() {
      return data;
    }
    
    @Override
    public String toString() {
      return data.toString();
    }
}