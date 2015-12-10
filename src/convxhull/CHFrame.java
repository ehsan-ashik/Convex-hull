/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convxhull;


import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 *
 * @author Ashik
 */
public class CHFrame extends JFrame{
    public CHPanel drwPanel;
    
    CHFrame(ArrayList<ConvxHull.Point> p, ArrayList<Integer> m){
        drwPanel= new CHPanel(p, m);
        setSize(800, 800);
        add(drwPanel, BorderLayout.CENTER);
    }
}
