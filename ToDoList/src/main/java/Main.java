import Models.Record;
import Services.RecordService;
import Controllers.RecordController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       RecordController rc = new RecordController();
        String comand = "";
        Scanner scanner = new Scanner(System.in);
        while (!comand.equals("exit")){
            System.out.println("\r\n\r\n" +
                    "Enter \"read\" to read record from list \r\n" +
                    "Enter \"add\" to add record to list \r\n" +
                    "Enter \"remove\" to remove record from list \r\n" +
                    "Enter \"complete\" to change status of record in the list \r\n" +
                    "Enter \"pending\" to see all pending records in the list \r\n" +
                    "Enter \"exit\" to exit \r\n");
            comand = scanner.nextLine();
            switch (comand){
                case "add":{
                    System.out.println("Please write record");
                    String writeRecord = scanner.nextLine();
                    rc.AddRecord(writeRecord);
                    break;}
                case "read":{
                    var records = rc.getRecords();
                    for (Record record : records){
                        System.out.println("Id: " + record.getId() + " | Record: " + record.getName() + " | Status: " + record.getStatus());
                    }
                    break;}
                case "remove":{
                    System.out.println("Please type the Id of record");
                    int Id = Integer.parseInt(scanner.nextLine());
                    rc.RemoveRecord(Id);
                    break;}
                case "complete":{
                    System.out.println("Please type the Id of record");
                    int idToComplete = Integer.parseInt(scanner.nextLine());
                    rc.changeStatusToComplete(idToComplete);
                    break;}
                case "pending":{
                    var records = rc.getRecords();
                    for (Record record : records){
                        if (record.getStatus().equals("Pending")){
                        System.out.println("Id: " + record.getId() + " | Record: " + record.getName());
                        }
                    }
                    break;}

            }
        }
    }
}
