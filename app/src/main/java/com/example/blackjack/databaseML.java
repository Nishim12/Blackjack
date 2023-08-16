package com.example.blackjack;

//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.content.Context;
//
//import java.io.File;
//
//import jxl.Sheet;
//import jxl.Workbook;
//
//public class databaseML {
//    public  void main(Context context) {
//        try {
//            Workbook workbook = Workbook.getWorkbook(new File("C:\\Users\\nishi\\OneDrive - UBC\\Blackjack.xlsx"));
//            Sheet sheet = workbook.getSheet(0);
//
//            DatabaseHelper databaseHelper=new DatabaseHelper(context);
//            SQLiteDatabase database = databaseHelper.getWritableDatabase();
//
//            for (int row = 0; row < sheet.getRows(); row++) {
//                int col1Value = Integer.parseInt(sheet.getCell(0, row).getContents());
//                int col3Value = Integer.parseInt(sheet.getCell(2, row).getContents());
//                int col5Value = Integer.parseInt(sheet.getCell(4, row).getContents());
//
//                ContentValues values = new ContentValues();
//                values.put(DatabaseHelper.COLUMN_COLUMN1, col1Value);
//                values.put(DatabaseHelper.COLUMN_COLUMN3, col3Value);
//                values.put(DatabaseHelper.COLUMN_COLUMN5, col5Value);
//
//                long newRowId = database.insert(DatabaseHelper.TABLE_NAME, null, values);
//            }
//
//            database.close();
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
