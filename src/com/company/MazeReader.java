package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class MazeReader {
    char[][] map;
    char c; //current character while iterating and writing array
    int lineCount = 0; //y axis size
    int lineWidth = 0; //x axis size

    public char[][] read(String fileLocation) throws FileNotFoundException, InvalidCharacterException {

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
                c = currLineString.charAt(i);
                map[currLineCount][i]=c;

                /*This if statement will test to make sure each
                character entered is valid.*/
                if (c!='A' && c!='B' && c!='.' && c!='#'){
                    throw new InvalidCharacterException("One or more of the characters in this file was not recognized" +
                            "by this program. Valid characters are 'A', 'B', '.', and '#'");
                }
            }

            currLineCount++;
        }

        input.close();

        return map;
    }
}
