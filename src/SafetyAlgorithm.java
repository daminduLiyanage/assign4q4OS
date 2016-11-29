import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Damindu on 11/28/2016.
 */
public class SafetyAlgorithm {
    final int PROCESS = 5;
    final int RESOURCES = 4;

    int[] work;
    int[] available;
    int[][] max;
    boolean[] finish;
    int[][] need;
    private int[][] allocated;

    public SafetyAlgorithm(Inputs input){
        this.work = input.getWork();
        this.available = input.getAvailable();
        this.max = input.getMax();
        this.finish = input.getFinish();
        this.need = input.getNeed();
        this.allocated = input.getAllocated();
    }

    public void main(){
        resetAllToUnfinish();
        searchAndFinish();
        if(checkFinish() == false)
            System.out.println("Deadlocks possible: No finishing sequence is available since it is not safe");
        else
            System.out.println("System Safe");
    }

    /**
     * Release resources to work array from the finished
     * process
     * @param i
     */
    private void releaseResources(int i){
        for(int j=0; j<RESOURCES; j++){
            this.work[j] += this.allocated[i][j];
        }
        System.out.println("Process "+ (i+1) +" finished");
    }

    /**
     * Resets finish array to all false.
     */
    private void resetAllToUnfinish(){
        for(int i=0; i<PROCESS; i++){
            this.finish[i] = false;
        }
    }



    private void searchAndFinish(){
        if(checkFinish())
            return;
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0; i<PROCESS; i++){
            if(checkNeedOfProcess(i) == true){
                releaseResources(i);
                this.finish[i] = true;
            } else {
                list.add(i);
            }
        }
        while (!list.isEmpty()){
            int i;
            if(checkNeedOfProcess(i = list.removeFirst())){
                releaseResources(i);
                this.finish[i] = true;
            }
        }
        checkFinish();
    }

    /**
     * Check needed array of i process. If every work element is
     * lower than needed amount the request can be give. Hence it
     * returns true.
     * @param i
     * @return
     */
    private boolean checkNeedOfProcess(int i){
        for(int j=0; j<RESOURCES; j++){
            if(need[i][j] > work[j])
                return false;
        }
        return true;
    }

    private boolean checkFinish(){
        for(int i=0; i<PROCESS; i++)
            if(this.finish[i] == false)
                return false;
        return true;
    }
}
