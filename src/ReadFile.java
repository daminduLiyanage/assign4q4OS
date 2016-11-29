import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by Damindu on 11/28/2016.
 */
public class ReadFile {

    int PROCESS = 0;

    int[] available;
    int[][] max;
    int[][] request;
    private int[][] allocated;
    int RESOURCES;
    final int PROCESSES = 5;
    Path path;

    public ReadFile(){
        this.path = Paths.get(".\\src\\input4.txt");
        try {
            this.main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void main() throws IOException {
        getAvailable();
        setNoOfresources(available);
        this.getAllocated();
        this.getMax();
        this.getRequest();
    }

    public int[] getAvailable() {
        if (this.available != null)
            return this.available;
        String string = null;
        try {
            string = Files.readAllLines(this.path).get(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringTokenizer tokens = new StringTokenizer(string);
        ArrayList<Integer> list = new ArrayList<>();
        String element;
        while (tokens.hasMoreElements()) {
            if ((element = tokens.nextElement().toString()) != " ") {
                list.add(Integer.parseInt(element));
            }
        }
        int[] available = list.stream().mapToInt(i -> i).toArray();
        this.available = available;
        return available;
    }

    public int[][] getAllocated(){
        if(this.allocated != null)
            return this.allocated;
        String string = null;
        this.allocated = new int[PROCESSES][RESOURCES];
        this.max = new int[PROCESSES][RESOURCES];
        int p = 0;
        for(int line = 4; line<=8; line++, p++) {
            try {
                string = Files.readAllLines(this.path).get(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringTokenizer tokens = new StringTokenizer(string);
            for (int i = 0; i < this.allocated[p].length; i++) {
                this.allocated[p][i] = Integer.parseInt(tokens.nextElement().toString());
            }
            for (int i = 0; i < this.max[p].length; i++) {
                this.max[p][i] = Integer.parseInt(tokens.nextElement().toString());
            }
        }
        return this.allocated;
    }

    public int setNoOfresources(int[] available) {
        return (RESOURCES = available.length);
    }

    public void diplayAvailableResources(){
        System.out.println(Arrays.toString(this.available));
    }

    public int[][] getMax(){
        return this.max;
    }

    public int[][] getRequest() throws IOException {
        if(this.request != null)
            return this.request;
        this.request = new int[noOfLines()-11][5];
        setRequestArray(this.request);
        int p =0;
        for (int line = 11; line<(this.request.length + 11)
                ; line++, p++) {
            String string = Files.readAllLines(this.path).get(line);
            StringTokenizer tokens = new StringTokenizer(string);
            for(int i = 0; i<this.request[p].length; i++)
                this.request[p][i] = Integer.parseInt(tokens.nextElement().toString());
        }
        return this.request;
    }

    private void setRequestArray(int[][] request) {
        for(int i = 0; i<request.length; i++){
            request[i][PROCESS] = i;
        }
    }

    private int noOfLines() throws IOException {
        LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(".\\src\\input4.txt")));
        lnr.skip(Long.MAX_VALUE);
        int lines = lnr.getLineNumber() + 1;
        lnr.close();
        return lines;
    }

    public int getNoOfResources(){
        return this.RESOURCES;
    }
}