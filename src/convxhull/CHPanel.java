/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convxhull;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Ashik
 */
public class CHPanel extends JPanel{
    int n;
    int size = 400;
    int no = 20;
    ArrayList<ConvxHull.Point> points;
    ArrayList<Integer> hullNodes;

    CHPanel(ArrayList<ConvxHull.Point> p, ArrayList<Integer> m) {
        points = new ArrayList<>(p);
        n = p.size();
        hullNodes = new ArrayList<>(m);
        hullNodes.add(hullNodes.get(0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        repaint();
        g.drawLine(0, size, size * 2, size);
        g.drawLine(size, 0, size, size * 2);
        
        g.setColor(Color.RED);
        for(int i = 0; i <= size * 2; i = i + no){
            g.drawLine(i, size - 3, i, size + 3);
            g.drawLine(size - 3, i, size + 3, i);
        }
        
        for(int i = 0; i < n; i++){
            if(hullNodes.contains(points.get(i).num)){
                g.setColor(Color.BLUE);
            }else{
                g.setColor(Color.red);
            }
            
            g.drawRect(size + points.get(i).x*no - 2, size - points.get(i).y*no - 2, 4, 4);
            g.drawRect(size + points.get(i).x*no - 2, size - points.get(i).y*no - 2, 3, 3);
            g.drawRect(size + points.get(i).x*no - 1, size - points.get(i).y*no - 1, 2, 2);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf( (i+1) + "(" + points.get(i).x + "," + points.get(i).y + ")" ), size + points.get(i).x*no + 5,
                    size - points.get(i).y*no + 5);
        }
        
        for(int i = 0; i< hullNodes.size() - 1; i++){
            g.setColor(Color.darkGray);
            g.drawLine(size + points.get(hullNodes.get(i) - 1).x*no ,size - points.get(hullNodes.get(i) - 1).y*no , 
                    size + points.get(hullNodes.get(i+1) - 1).x*no, size - points.get(hullNodes.get(i+1) - 1).y*no);
        }
    }
    
    
}
