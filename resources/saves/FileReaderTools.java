package resources.saves;

/*
 * @Author Joseph Murray
 * Created: 5/25/2023
 * FileReaderTools: Reads in a .txt file into an ArrayList, broken down into sections.
 * It also provides generic methods for converting the data into Lists of BufferedImages, Classes, etc.
 * Useful for saving and loading game information in an organized and simplified way
 * 
 * Breakdown Guide: This class requires that files be written in a certain format, which I'll try and explain here:
 * Keys: each String in the file (each "piece" of data)
 * Keywords: These are keys in the file that are in all caps. These are used by this class to organize the data for the methods to use
 * There are two keywords currently:
 * SECTION: this keyword denotes that the following keys are related in some way, and should be grouped into the same ArrayList
 * A section includes all the keys following the SECTION keyword to the next SECTION keyword
 * The key immediatly following the SECTION keyword is the name of the section, used to differentiate sections
 * PATH: this keyword denotes that the next key is a file path, for example, a section of images might be under the PATH src.gui.images
*/

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class FileReaderTools {
    private File file;
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    private Scanner scanner;
    private PrintWriter printWriter; //TODO: adding file printing if we decide to use this class in the future

                                        //Constructors

    /**
     * Constructor, creates a FileReaderTools object to go with the specified .txt file
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
     * helper method for constructor that reads from the file and transfers 
     * the information into a data ArrayList, to be manipulated later on
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
     * returns an ArrayList of BufferedImages from a section of the file
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
     * returns an ArrayList of Classes from a section of the file, currently unused but the method works
     * @param sectionName name of the section that is searched for
     * @return an ArrayList of Class objects
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