import java.io.IOException;

/**
 * Created by Damindu on 11/28/2016.
 */
public class Inputs {
    ReadFile file;
    int RESOURCES;
    int[] available;
    int[] work;
    int[][] max;
    int[][] request;
    int[][] need;
    boolean[] finish;
    private int[][] allocated;
    int PROCESS = 5;

    public Inputs(ReadFile file){
        this.file = file;
        this.RESOURCES = file.getNoOfResources();
        this.available = file.getAvailable();
        this.allocated = file.getAllocated();
        this.max = file.getMax();
        try {
            this.request = this.file.getRequest();
        } catch (IOException e){
            e.printStackTrace();
        }
        this.work = new int[PROCESS];
        setWork();
        this.need = new int[PROCESS][RESOURCES];
        setNeed();
        this.finish = new boolean[PROCESS];
    }

    private void setNeed(){
        for(int i=0; i<this.need.length; i++)
            for(int j=0; j<this.need[i].length; j++)
                this.need[i][j] = this.max[i][j] - this.allocated[i][j];
    }

    private void setWork(){
        for(int i=0; i<PROCESS; i++)
            work = available;
    }

    public int getNoOfresources() {
        return RESOURCES;
    }

    public int[] getAvailable(){
        return this.available;
    }

    public int[] getWork(){
        return  this.work;
    }

    public int[][] getMax(){
        return this.max;
    }

    public int[][] getRequest(){
        return this.request;
    }

    public int[][] getAllocated(){
        return this.allocated;
    }

    public int[][] getNeed(){
        return this.need;
    }

    public boolean[] getFinish(){
        return this.finish;
    }
}
