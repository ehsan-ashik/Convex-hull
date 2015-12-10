/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convxhull;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Ashik
 */
public class ConvxHull {
    /**
     * @param args the command line arguments
     */
    static int n;
    static class Point{
        int num;
        int x,y;        
    }
    static ArrayList<Point> points = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArrayList<String> nums = new ArrayList<>();
        for(String line : Files.readAllLines(Paths.get("9.txt"), Charset.defaultCharset())){
            nums.add(line);
        }
        n = Integer.valueOf(nums.get(0));
        nums.remove(0);
        int p = 1;
        for (String num : nums) {
            String[] parts = num.split("\\s+");
            Point point = new Point();
            point.num = p;
            point.x = Integer.valueOf(parts[0]);
            point.y = Integer.valueOf(parts[1]);
            p++;
            points.add(point);
        }
        
        for(int i = 0 ; i < n; i++){
            System.out.println(points.get(i).num + ". (" + points.get(i).x + ", " + points.get(i).y + ")");
        }
        
        GftWrapping gw = new GftWrapping(points);
        gw.doWrapping();
        
        CHFrame frame = new CHFrame(points, gw.resultHull);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for(int val : gw.resultHull){
            System.out.print(val + " ");
        }
        
        System.out.println();
        for(int val : gw.notInHull){
            System.out.print(val + " ");
        }
        
        try (PrintWriter writer = new PrintWriter("out.txt", "UTF-8")) {
            writer.println(gw.resultHull.size());
            for(int val : gw.resultHull){
                writer.println(points.get(val - 1).num + " " + points.get(val - 1).x + " " + points.get(val - 1).y);
            }
            
            writer.println(gw.notInHull.size());
            for(int val : gw.notInHull){
                writer.println(points.get(val - 1).num + " " + points.get(val - 1).x + " " + points.get(val - 1).y);
            }
            
        }
    }
    
}
