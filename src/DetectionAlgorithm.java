/**
 * Created by Damindu on 11/28/2016.
 */
public class DetectionAlgorithm {
    final int PROCESS = 5;
    final int RESOURCES = 4;

    int[] work;
    int[] available;
    int[][] max;
    boolean[] finish;
    int[][] need;
    int[][] request;
    private int[][] allocated;
    private int processNo;

    public DetectionAlgorithm(Inputs input, int processNo){
        this.work = input.getWork();
        this.available = input.getAvailable();
        this.max = input.getMax();
        this.finish = input.getFinish();
        this.need = input.getNeed();
        this.allocated = input.getAllocated();
        this.request = input.getRequest();
        this.processNo = processNo;
    }

    private boolean updateProcess(int requestNo){
        for(int i=0; i<allocated[this.processNo].length; i++) {
            if (this.request[requestNo][i] > work[i])
                return false;
            allocated[this.processNo][i] = allocated[this.processNo][i] -
                    this.request[this.processNo][i];
        }
        return true;
    }

    public void main(int requestNo){
        if(!updateProcess(requestNo)) {
            System.out.println("Illegal Request. Check Resources for request again");
            return;
        }
        resetAllToUnfinish();
        searchAndFinish();
        if(checkFinish() == false)
            System.out.println("No deadlocks");
        else
            System.out.println("Deadlocks possible");
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
        for(int i=0; i<PROCESS; i++){
            if(checkNeedOfProcess(i) == true){
                releaseResources(i);
                this.finish[i] = true;
            }
        }
    }

    /**
     * Check needed array of i process. If every work element is
     * lower than needed amount the request can be give. Hence it
     * returns true.
     * @param i
     * @return
     */
    private boolean checkNeedOfProcess(int i){
        int count = 1;
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
