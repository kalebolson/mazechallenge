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
        ArrayList<Path> validPaths = new ArrayList<>(0);
        Path shortestPath;

        try {
            System.out.println("Enter file location");
            location = sc.nextLine();
            maze = new Maze(location);

            //printing to test reader function
            System.out.println(maze);
        } catch (FileNotFoundException e){
            System.out.println("Invalid location");
        }

        //Now to retrieve the paths and compile a list of which ones are valid
        paths = Path.getPaths();
        for(Path p : paths){
            if (p.isValid)
                validPaths.add(p);
        }

        //Scanning valid paths for the shortest one
        shortestPath = validPaths.get(0);
        for (Path p : validPaths){
            if (p.getCount()<shortestPath.getCount())
                shortestPath = p;
        }

        System.out.println("Shortest path count is: "+shortestPath.getCount());
    }
}
