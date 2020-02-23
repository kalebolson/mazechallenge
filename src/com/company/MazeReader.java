package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeReader {
    char[][] map;
    int lineCount = 0; //y axis size
    int lineWidth = 0; //x axis size

    public char[][] read(String fileLocation) throws FileNotFoundException{

        //using BufferedReader to get x and y counts for map
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        try {
            String currentLine = br.readLine();
            lineWidth = currentLine.length();
            while (currentLine != null){
                lineCount++;
                currentLine = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //now that I have the size of the maze, I can instantiate the map
        map = new char[lineCount][lineWidth];

        //filling our map with the characters from the file
        Scanner input = new Scanner (new File(fileLocation));
        int currLineCount = 0;
        String currLineString;
        while (input.hasNextLine()){
            currLineString = input.nextLine();
            for (int i=0; i<lineWidth; i++){
                map[currLineCount][i]=currLineString.charAt(i);
            }

            currLineCount++;
        }

        input.close();

        return map;
    }
}
