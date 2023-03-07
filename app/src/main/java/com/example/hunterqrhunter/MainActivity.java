package com.example.hunterqrhunter;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.hunterqrhunter.model.User;
import com.example.hunterqrhunter.data.FbRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Object hashVal = "rickywang1123@gmail.com";
    HashQR hashQR = new HashQR();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FbRepository fb = new FbRepository(db);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int hash = HashQR.hashObject(hashVal);

//      All stuffs related to firebase creation,
//      firebase data create/update/delete/pull need to be inside FbRepository
//      In MainActivity just initialize the firebase.

        // Create a new user with a first and last name
        User user = new User("5","Ricky","Wang",1997,hash);

        // Add a new document with a generated ID
        fb.createUser(user);
    }
}