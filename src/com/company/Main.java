package com.company;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String location;
        Maze maze;
        ArrayList<Path> paths;
        Path shortestPath;
        char [][] map;
        Coordinate[] coordinates;

        try {
            System.out.println("Enter file location");
            location = sc.nextLine();
            maze = new Maze(location);
            sc.close();

            //Now to retrieve the paths and compile a list of which ones are valid
            paths = Path.getPaths();

            //Scanning valid paths for the shortest one
            shortestPath = paths.get(0);
            for (Path p : paths){
                if (p.getCount()<shortestPath.getCount())
                    shortestPath = p;
            }

            //Filling in visual representation of shortest path
            map = maze.getMap();
            coordinates = shortestPath.getCoordList();
            for (Coordinate c : coordinates){
                if (map[c.getY()][c.getX()] != 'A' && map[c.getY()][c.getX()] != 'B')
                    map[c.getY()][c.getX()] = '@';
            }

            //Displaying result
            System.out.println(maze);
            System.out.println("Shortest path count is: "+shortestPath.getCount());

        } catch (FileNotFoundException e){
            System.out.println("Invalid location");
        }
    }
}
