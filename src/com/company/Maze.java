package com.company;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Maze {
    char[][] map;
    Coordinate start;
    Coordinate finish;
    MazeReader mr = new MazeReader();

    public Maze(String fileLocation) throws FileNotFoundException {
        //using file location to create map of the maze
        //the map is a 2d array
        map = mr.read(fileLocation);

        //now scanning over whole map to find start and finish
        for (int y=0; y<map.length; y++){
            for (int x=0; x<map[y].length; x++){
                if (map[y][x] == 'A')
                    start = new Coordinate(x,y);
                if (map[y][x] == 'B')
                    finish = new Coordinate(x,y);
            }
        }

        //testing start and finish detectors
        assert start != null;
        assert finish != null;
        System.out.println("Starting coordinates: "+Arrays.toString(start.getCoord()) +"\nFinishing coordinates: "+ Arrays.toString(finish.getCoord()));

        Path startPath = new Path(start, map);
    }

    @Override
    public String toString() {
        StringBuilder mapString = new StringBuilder();
        for (char[] line : map){
            for (char c : line){
                mapString.append(c);
            }

            mapString.append("\n");
        }
        return mapString.toString();
    }

    public char[][] getMap() {
        return map;
    }
}
