import java.io.*;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Scanner;



public class LabAssist {
    private final ArrayList<Float> hkg_arr;
    private final ArrayList<Float> gio_arr;
    private final Sample control_gene;
    private final ArrayList<Sample> samples;

    public LabAssist() {
        hkg_arr = new ArrayList<>();
        gio_arr = new ArrayList<>();
        control_gene = new Sample();
        samples = new ArrayList<>();
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        LabAssist op = new LabAssist();
        op.menu();
    }

    public void menu() throws Exception {
        String choice;
        do {
            System.out.println("Please pick a choice:");
            System.out.print("""
                    1.\tEnter Control Sample.
                    2.\tEnter Samples.
                    3.\tAdd Sample.
                    4.\tEdit Sample.
                    0.\tQuit and save result.
                    """);
            choice = sc.next();
            switch (choice) {
                case "1":
                    initializeControl();
                    break;
                case "2":
                    initializeSamples();
                    break;
                case "3":
                    addSample();
                    break;
                case "4":
                    editSample();
                    break;
                case "0":
                    writeToCsv();
                default:
                    break;
            }
        } while (!(choice.equals("0")));
    }

    public void writeToCsv() throws IOException, FileSystemException {
        File csvFile=new File("result.csv");
        PrintWriter out=new PrintWriter(csvFile);
        out.print(" ,AvgGIO,AvgHGK,DeltaCt,DeltaDeltaCt,Result\n");
        out.println("Control,"+ control_gene);
        int i=1;
        for(Sample sample:samples){
          out.print("Sample "+i+",");
          out.println(sample.toString());
          i=i+1;
        }
        out.close();
    }
    public void initializeControl() {
        System.out.println("Please enter number of control samples.");
        int number = sc.nextInt();
        float hkg_ct=0;
        float gio_ct=0;
        for (int i = 0; i < number; i++) {
            System.out.println("Enter HKG Average Ct.");
            hkg_ct = sc.nextFloat();
            this.hkg_arr.add(hkg_ct);
            System.out.println("Enter GIO Average Ct.");
            gio_ct = sc.nextFloat();
            this.gio_arr.add(gio_ct);
        }
        if (number > 1) {
            this.control_gene.setGio_avg_ct(avgCt(gio_arr));
            this.control_gene.setHkg_avg_ct(avgCt(hkg_arr));
        }
        else{
            this.control_gene.setGio_avg_ct(gio_ct);
            this.control_gene.setHkg_avg_ct(hkg_ct);
        }
        this.control_gene.setDetlaCt();
    }

    public void initializeSamples() {
        System.out.println("Please enter number of samples.");
        int number = sc.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("Sample number"+(i+1)+":");
            addSample();
        }
    }

    public void addSample() {
        System.out.println("Enter HKG Average Ct.");
        float hkg_ct = sc.nextFloat();
        this.hkg_arr.add(hkg_ct);
        System.out.println("Enter GIO Average Ct.");
        float gio_ct = sc.nextFloat();
        Sample sample = new Sample(hkg_ct, gio_ct);
        sample.setDetlaCt();
        sample.setDeltaDeltaCt(this.control_gene);
        sample.setResult();
        this.samples.add(sample);
    }
    public void editSample() throws Exception{
        int choice;
        System.out.println("Please Enter number of sample you want to edit.");
        choice=sc.nextInt();
        try{
            System.out.println("Enter HKG Average Ct.");
            float hkg_ct = sc.nextFloat();
            System.out.println("Enter GIO Average Ct.");
            float gio_ct = sc.nextFloat();
            samples.get((choice-1)).setHkg_avg_ct(hkg_ct);
            samples.get((choice-1)).setGio_avg_ct(gio_ct);
            samples.get((choice-1)).setDetlaCt();
            samples.get((choice-1)).setDeltaDeltaCt(control_gene);
            samples.get((choice-1)).setResult();
        }
        catch (Exception e){
            System.out.println("There is no such sample, return to main menu.");
        }
    }

    public float avgCt(ArrayList<Float> arr) {
        float sum = 0;
        for (float ct : arr) {
            sum += ct;
        }
        return sum / arr.size();
    }
}
