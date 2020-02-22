package com.company;

import java.util.ArrayList;

public class Path {
    private static char[][] map;
    private static ArrayList<Path> paths = new ArrayList<Path>(0);
    private char[] directions;
    Coordinate[] coordList = new Coordinate[1];
    Coordinate head;
    boolean isValid;
    int count = 0;

    //initial constructor, this path is just the
    //start point with no movement yet
    public Path(Coordinate start, char[][] map){
        Path.map = map;
        paths.add(this);
        directions = new char[]{'S'};
        coordList[0] = start;
        head=start;
        isValid = false;

        move();
        paths.remove(this);
    }

    //overriding constructor. This one is intended to be called by the
    //move() method only. It will be called for every valid move in order
    //to create a new path
    public Path(char[] directions, Coordinate[] coordList, Coordinate head, int count){
        this.count = count+1;
        this.directions = directions;
        this.coordList = coordList;
        this.head = head;

        if (map[head.getY()][head.getX()] == 'B') isValid = true;
        else isValid = false;

        move();
    }

    // The name 'move' might be a bit misleading. This function
    //actually just creates new paths for every valid move
    public void move(){

        //Down
        if (check(head.getY() + 1,head.getX())) {
            //Create new char array for directions for new path
            char[] newDirections = new char[directions.length+1];
            System.arraycopy(directions, 0, newDirections, 0, directions.length);
            newDirections[newDirections.length-1] = 'D';

            Coordinate newHead = new Coordinate(head.getX(), head.getY()+1);

            //Create new coordList for new path
            Coordinate[] newCL = new Coordinate[coordList.length+1];
            for (int i = 0; i<coordList.length; i++){
                newCL[i] = coordList[i];
            }
            newCL[newCL.length-1] = newHead;

            //Creating a new path for the intended direction
            Path p = new Path(newDirections,newCL,newHead,count);
            paths.add(p);

            //Removing invalid paths from the path list to free up memory
            if (!p.isValid) {
                paths.remove(p);
                p=null;
            }
        }

        //Up
        if (check(head.getY() - 1,head.getX())) {
            //Create new char array for directions for new path
            char[] newDirections = new char[directions.length+1];
            System.arraycopy(directions, 0, newDirections, 0, directions.length);
            newDirections[newDirections.length-1] = 'U';

            Coordinate newHead = new Coordinate(head.getX(), head.getY()-1);

            //Create new coordList for new path
            Coordinate[] newCL = new Coordinate[coordList.length+1];
            for (int i = 0; i<coordList.length; i++){
                newCL[i] = coordList[i];
            }
            newCL[newCL.length-1] = newHead;

            Path p = new Path(newDirections,newCL,newHead,count);
            paths.add(p);
            if (!p.isValid) {
                paths.remove(p);
                p=null;
            }
        }

        //Left
        if (check(head.getY(),head.getX() - 1)) {
            //Create new char array for directions for new path
            char[] newDirections = new char[directions.length+1];
            System.arraycopy(directions, 0, newDirections, 0, directions.length);
            newDirections[newDirections.length-1] = 'L';

            Coordinate newHead = new Coordinate(head.getX()-1, head.getY());

            //Create new coordList for new path
            Coordinate[] newCL = new Coordinate[coordList.length+1];
            for (int i = 0; i<coordList.length; i++){
                newCL[i] = coordList[i];
            }
            newCL[newCL.length-1] = newHead;

            Path p = new Path(newDirections,newCL,newHead,count);
            paths.add(p);
            if (!p.isValid) {
                paths.remove(p);
                p=null;
            }
        }

        //Right
        if (check(head.getY(),head.getX() + 1)) {
            //Create new char array for directions for new path
            char[] newDirections = new char[directions.length+1];
            System.arraycopy(directions, 0, newDirections, 0, directions.length);
            newDirections[newDirections.length-1] = 'R';

            Coordinate newHead = new Coordinate(head.getX()+1, head.getY());

            //Create new coordList for new path
            Coordinate[] newCL = new Coordinate[coordList.length+1];
            for (int i = 0; i<coordList.length; i++){
                newCL[i] = coordList[i];
            }
            newCL[newCL.length-1] = newHead;

            Path p = new Path(newDirections,newCL,newHead,count);
            paths.add(p);
            if (!p.isValid) {
                paths.remove(p);
                p=null;
            }
        }
    }

    //Here we are checking to make sure a given move is valid
    public boolean check(int y, int x){
        char target;
        try {
            target = map[y][x]; //this might trigger out of bounds exception

            //if target position is a wall, move is invalid
            if (target == '#')
                return false;

            //if target position has already been visited,
            //there is no reason to visit it again
            for (Coordinate c : coordList){
                if (c.getX()==x && c.getY()==y)
                    return false;
            }
        } catch (IndexOutOfBoundsException e){
            //if target position is out of bounds, move is invalid
            return false;
        }
        //if the target position passes the previous tests, move is valid
        return true;
    }

    public static ArrayList<Path> getPaths(){
        return paths;
    }

    public int getCount(){return count;}
    public Coordinate[] getCoordList(){return coordList;}
}
