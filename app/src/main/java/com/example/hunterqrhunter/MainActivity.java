package com.example.hunterqrhunter;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hunterqrhunter.data.FbRepository;
import com.example.hunterqrhunter.model.QRCreature;
import com.example.hunterqrhunter.model.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Object hashVal = "yongbin@gmail.com";
    HashQR hashQR = new HashQR();

    private Button mButton;
    FirebaseFirestore db;
    FbRepository fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        fb = new FbRepository(db);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("a","b","c"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("c","b","a"));

        QRCreature qr = new QRCreature("5", "Lingfeng", 300, 2001, list,list2);
        list.add("abc");
        qr.setComments(list);

//        QRCreature qr2 = new QRCreature("7", "Lingfeng", 300, 1000, list,list2);
        getQR(300);
        fb.writeQR(qr);
    }
    public void getQR(int qrCode){
        DocumentReference docRef = db.collection("QR Creatures").document(Integer.toString(qrCode));
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    QRCreature qr = new QRCreature(qrCode);
                    // set QR object
                    qr.setHashName((String) document.get("QR Name"));
                    qr.setHashImage((String) document.get("QR Image"));
                    qr.setScore(((Long) Objects.requireNonNull(document.get("QR Score"))).intValue());
                    qr.setOwnedBy((ArrayList<String>) document.get("QR Ownedby"));
                    qr.setComments((ArrayList<String>) document.get("QR comments"));
                    System.out.println(qr.toMap());
                    // here you can use the callback function to do the work with the get QR.
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });

    }
}