/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package lab2;

import java.util.Arrays;
import lab2.Location.LocationEnum;

/**
 *
 * @author KebabRonin
 */
public class Lab2 {

    public static void main(String[] args) {
        Location l1 = new Location(10,20,LocationEnum.airport);
        Location l2 = new Location(60,20,LocationEnum.school);
        Location l3 = new Location(10,20,LocationEnum.shop);
        
        System.out.println(l1);
        System.out.println(l2);
        
        Road r1 = new Road(l1, l3, 20);
        Road r2 = new Road(l2, l3, 10);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(l3);
        l3.addRoad(r2);
        System.out.println(Arrays.toString(l3.getRoads()));
    }
}
