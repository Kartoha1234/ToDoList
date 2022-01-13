package Services;

import Models.Record;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RecordService {
    public void WriteRecords(ArrayList<Record> records){
        try (PrintWriter writer = new PrintWriter("Records.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append("Id,");
            sb.append("Name,");
            sb.append("Status\r\n");
            for (Record record : records){
                sb.append(record.getId());
                sb.append(',');
                sb.append(record.getName());
                sb.append(',');
                sb.append(record.getStatus());
                sb.append("\r\n");

            }
            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Record> ReadRecords(){
        ArrayList<Record>records = new ArrayList<Record>();
        try {
            Scanner sc = new Scanner(new File("Records.csv"));
            sc.useDelimiter(",");
            sc.nextLine();
            while (sc.hasNextLine())
            {
               records.add(getDataFromLine(sc.nextLine()));
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return records;
    }

    private Record getDataFromLine(String line){
        int id;
        String name;
        String status;
        Record result = null;
        try {
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            id = lineScanner.nextInt();
            name = lineScanner.next();
            status = lineScanner.next();
            result = new Record(id,name,status);
        }catch (Exception e){

        }
        return result;
    }
}
