package com.example.hunterqrhunter.page;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hunterqrhunter.R;
import com.example.hunterqrhunter.data.FbRepository;
import com.example.hunterqrhunter.model.QR;
import com.example.hunterqrhunter.model.QRCreature;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
public class QRScreen extends AppCompatActivity {

    private FbRepository fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qr);
        ListView listView = findViewById(R.id.qr_qr_comment_list);
        Button addText = (Button) findViewById(R.id.qr_add_button);
        Button scanned = (Button) findViewById(R.id.qr_scanned_number);
        Button score = (Button) findViewById(R.id.qr_qr_score);
        EditText editText = (EditText) findViewById(R.id.qr_add_comment);

        Intent intent = getIntent();
        String qrCode = intent.getStringExtra("qrCode");


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        fb = new FbRepository(db);
        ArrayList<String> commentList = new ArrayList<>();
        ArrayAdapter<String> commentAdapter = new ArrayAdapter<String>((Context) this, R.layout.activity_qr_comment, commentList);
        listView.setAdapter(commentAdapter);

        DocumentReference docRef = db.collection("QR").document(qrCode);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    QR qr = new QR(qrCode);
                    // set QR object
                    qr.setHashName((String) document.get("hashName"));
                    qr.setQrcode((String) document.get("qrcode"));
                    qr.setScore(((Long) Objects.requireNonNull(document.get("score"))).intValue());
                    qr.setOwnedBy((ArrayList<String>) document.get("ownedBy"));
                    qr.setComments((ArrayList<String>) document.get("comments"));

                    commentList.addAll(qr.getComments());

                    score.setText(Integer.toString(qr.getScore()));
                    //scanned.setText(Integer.toString(qrCreature.getOwnedBy().size()));

                    commentAdapter.notifyDataSetChanged();


                    // here you can use the callback function to do the work with the get QR.
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newComment = editText.getText().toString();
                if (!newComment.isEmpty()) {
                    commentList.add(newComment);
                }
                commentAdapter.notifyDataSetChanged();
                fb.updateQRComments(qrCode, commentList);
            }
        });
    }
}
