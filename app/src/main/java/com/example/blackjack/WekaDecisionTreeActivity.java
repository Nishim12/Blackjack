package com.example.blackjack;

//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import weka.classifiers.trees.J48;
//import weka.core.Attribute;
//import weka.core.DenseInstance;
//import weka.core.Instances;
//
//public class WekaDecisionTreeActivity{
//
//    private DatabaseHelper databaseHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Initialize the DatabaseHelper
//        databaseHelper = new DatabaseHelper(this);
//
//        // Load data from SQLite and perform Weka Decision Tree
//        loadAndPerformDecisionTree();
//    }
//
//    private void loadAndPerformDecisionTree() {
//        try {
//            // Load data from SQLite
//            SQLiteDatabase database = databaseHelper.getReadableDatabase();
//            Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
//
//            // Create Weka attributes
//            Attribute attrColumn1 = new Attribute(DatabaseHelper.COLUMN_COLUMN1);
//            Attribute attrColumn3 = new Attribute(DatabaseHelper.COLUMN_COLUMN3);
//            Attribute attrColumn5 = new Attribute(DatabaseHelper.COLUMN_COLUMN5);
//            Attribute classAttribute = new Attribute("target_variable", 2); // 2 indicates nominal values
//
//            // Create Instances object
//            Instances instances = new Instances("Dataset",
//                    new Attribute[]{attrColumn1, attrColumn3, attrColumn5, classAttribute}, 0);
//
//            // Set class index
//            instances.setClassIndex(3); // Set class index to 3 (5th column)
//
//            // Add data from SQLite to Instances
//            while (cursor.moveToNext()) {
//                double col1Value = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_COLUMN1));
//                double col3Value = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_COLUMN3));
//                double col5Value = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_COLUMN5));
//                double classValue = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASS)); // Assuming it's stored as double
//
//                double[] instanceValues = {col1Value, col3Value, col5Value, classValue};
//                instances.add(new DenseInstance(1.0, instanceValues));
//            }
//
//            // Close cursor and database
//            cursor.close();
//            database.close();
//
//            // Build and evaluate the Decision Tree model
//            J48 decisionTree = new J48();
//            decisionTree.buildClassifier(instances);
//
//            // Make predictions
//            // You can use the trained model to predict new instances
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
