package seamfinding;

import seamfinding.energy.EnergyFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {

    @Override
    public List<Integer> findHorizontal(Picture picture, EnergyFunction f) {
        int width = picture.width();
        int height = picture.height();
        double[][] pic = new double[width][height];
        int[][] backtrack = new int[width][height];
        for(int i = 0; i < height; i++){
            pic[0][i] = f.apply(picture, 0, i);
        }
        for(int i = 1; i < width; i++){
            for(int j = 0; j < height; j++){
                double energy = pic[i-1][j];
                int min = j;
                if(j > 0 && pic[i-1][j-1] < energy) {
                    energy = pic[i-1][j-1];
                    min = j-1;
                }
                if(j < height-1 && pic[i-1][j+1] < energy){
                    energy = pic[i-1][j+1];
                    min = j+1;
                }

                pic[i][j] = f.apply(picture, i, j) + energy;
                backtrack[i][j] = min;
            }

        }
        double min = pic[width-1][0];
        int mini = 0;
        for(int i = 1; i < height; i++){
            if(pic[width-1][i] < min){
                min = pic[width-1][i];
                mini = i;
            }
        }

        List<Integer> seam = new ArrayList<>(width);
        int currt = mini;
        for(int i = width-1; i >= 0; i--){
            seam.add(0, currt);
            currt = backtrack[i][currt];
        }
        return seam;
       // throw new UnsupportedOperationException("Not implemented yet");
    }
}
