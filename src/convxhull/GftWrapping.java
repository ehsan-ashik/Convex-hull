/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convxhull;

import java.util.ArrayList;

/**
 *
 * @author Ashik
 */
public class GftWrapping {
    int n;
    ArrayList<ConvxHull.Point> points;
    ArrayList<Integer> resultHull;
    ArrayList<Integer> notInHull;
    
    GftWrapping(ArrayList<ConvxHull.Point> p) {
        points = new ArrayList<>(p);
        n = p.size();
        resultHull = new ArrayList<>();
        notInHull = new ArrayList<>();
    }
    
    public int pointLeftMost(){
        int index = 0;
        int minVal = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            if(points.get(i).x < minVal){
                minVal = points.get(i).x;
                index = i;
            }
        }
        return index;
    }
    
    public boolean isLeftFromPoint(int p, int q, int r){
        int val = ((points.get(q).y - points.get(p).y) * (points.get(r).x - points.get(q).x)) -
                ((points.get(q).x - points.get(p).x) * (points.get(r).y - points.get(q).y));
        
        return (val < 0);
    }
    
    public boolean isOverTheLine(int p, int q, int r){
        int val = ((points.get(q).y - points.get(p).y) * (points.get(r).x - points.get(q).x)) -
                ((points.get(q).x - points.get(p).x) * (points.get(r).y - points.get(q).y));
        
        return (val == 0);
    }
    
    public double uclidDistance(int x1,int y1,int x2,int y2){
        return Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
    }
    
    public void doWrapping(){
        int endP = 0;
        int pOnHll = pointLeftMost();
        do{
            resultHull.add(pOnHll + 1);
            endP = points.get(0).num - 1;
            //System.out.println(endP);
            for(int j = 1; j < n; j++){
                if( (endP == pOnHll) || isLeftFromPoint(pOnHll, endP, j) || isOverTheLine(pOnHll, endP, j) ){
                    if(isOverTheLine(pOnHll, endP, j)){
                        if( uclidDistance(points.get(pOnHll).x, points.get(pOnHll).y,
                                points.get(endP).x, points.get(endP).y) < uclidDistance(points.get(pOnHll).x, points.get(pOnHll).y,
                                points.get(j).x, points.get(j).y)){
                            endP = j;
                        }
                    }
                }else {
                    endP = j;

                }
            }
            pOnHll = endP;
        }while(endP != resultHull.get(0) - 1);
        for(int i = 0; i < n; i++){
            if(!resultHull.contains(i + 1)){
                notInHull.add(i + 1);
            }
        }
    }
}
