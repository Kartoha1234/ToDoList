package Controllers;

import Models.Record;
import Services.RecordService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RecordController {
    public void AddRecord(String recordName){
        RecordService rs = new RecordService();
        var records = rs.ReadRecords();
        int newId = records.get(records.size() - 1).getId() + 1;
        records.add(new Record(newId,recordName, "Pending"));
        rs.WriteRecords(records);
    }

    public ArrayList<Record> getRecords(){
        RecordService rs = new RecordService();
        var records = rs.ReadRecords();
        return records;
    }

    public void RemoveRecord(int Id){
        RecordService rs = new RecordService();
        var records = rs.ReadRecords();
        records.removeIf(record -> record.getId() == Id);
        rs.WriteRecords(records);
    }

    public void changeStatusToComplete(int Id){
        RecordService rs = new RecordService();
        var records = rs.ReadRecords();
        int indexOfRecord = -1;
        for (int i = 0; i < records.size(); i++){
            if(records.get(i).getId() == Id){
                indexOfRecord = i;
            }
            Record rc = null;
            if (indexOfRecord != -1){
                String status = records.get(indexOfRecord).getStatus();
                if (status.equals("Pending")){
                    rc = records.get(indexOfRecord);
                    records.set(indexOfRecord,new Record(rc.getId(),rc.getName(),"Complete"));
                }
            }
            rs.WriteRecords(records);
        }
    }



}
