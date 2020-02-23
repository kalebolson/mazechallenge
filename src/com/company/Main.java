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
        boolean running = true;

        while (running) {
            try {
                System.out.println("Enter file location");
                location = sc.nextLine();
                maze = new Maze(location);

                //Now to retrieve the paths and compile a list of which ones are valid
                paths = Path.getPaths();

                if (paths.size() == 0){
                    throw new NoValidPathException("Unable to find a valid path from 'A' to 'B'");
                }

                //Scanning valid paths for the shortest one
                shortestPath = paths.get(0);
                for (Path p : paths) {
                    if (p.getCount() < shortestPath.getCount())
                        shortestPath = p;
                }

                //Filling in visual representation of shortest path
                map = maze.getMap();
                coordinates = shortestPath.getCoordList();
                for (Coordinate c : coordinates) {
                    if (map[c.getY()][c.getX()] != 'A' && map[c.getY()][c.getX()] != 'B')
                        map[c.getY()][c.getX()] = '@';
                }

                //Displaying result
                System.out.println(maze);
                System.out.println("Shortest path count is: " + shortestPath.getCount());

            } catch (FileNotFoundException e) {
                System.out.println("Unable to locate the specified file. \n" +
                        "please check the file name, and ensure it exists within \n" +
                        "this application's working directory. \n");

            } catch (InvalidCharacterException | NoValidPathException e) {
                System.out.println(e.getMessage());
            } catch (OutOfMemoryError e) {
                System.out.println("The program has run out of memory, and is unable to \n" +
                        "process this maze");
                System.gc();
            } catch (Exception e){
                System.out.println("Something has gone wrong.");
            }
            finally {
                //Asking the user if they would like to run the program again
                String input = "";
                while (!input.equals("y") && !input.equals("n")) {
                    System.out.println("Try again? (y/n)");
                    input = sc.nextLine();
                }
                if (input.equals("n"))
                    running=false;
            }
        }
    }
}
