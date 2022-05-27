package com.example.a19438561_dangnhatkhuong_androiexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lvKhoaHoc;
    List<KhoaHoc> lisKhoaHoc;
    KhoaHocAdapter adt;
    List<KhoaHoc> listdb;

    public static MainActivity  getInstance;
    EditText txtTenKhoaHoc,txtThoiLuong;
    Button btnAddSkills, btnAddCareers, btnSavetoCloud, btnSyn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getInstance = this;

        lvKhoaHoc = findViewById(R.id.lvKhoaHoc);
        lisKhoaHoc = new ArrayList<>();
        txtTenKhoaHoc = findViewById(R.id.txtTenKhoaHocInput);
        txtThoiLuong = findViewById(R.id.txtThoiLuongInput);
        btnAddSkills = findViewById(R.id.btnAdd_skills);
        btnAddCareers = findViewById(R.id.btnAdd_careers);

        getListKhoaHoc();

        adt = new KhoaHocAdapter(this,R.layout.item_lv,lisKhoaHoc);
        lvKhoaHoc.setAdapter(adt);

        btnAddSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhoaHoc khoaHoc = new KhoaHoc(txtTenKhoaHoc.getText().toString(),Double.parseDouble(txtThoiLuong.getText().toString()),"Skills","27/5/2022");
                addCongViec(khoaHoc);
                updateUI();
            }
        });

        btnAddCareers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhoaHoc khoaHoc = new KhoaHoc(txtTenKhoaHoc.getText().toString(),Double.parseDouble(txtThoiLuong.getText().toString()),"Careers","27/5/2022");
                addCongViec(khoaHoc);
                updateUI();
            }
        });
    }

    public void getListKhoaHoc() {


        db.collection("khoahoc")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        lisKhoaHoc.clear();

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                KhoaHoc khoahoc = new KhoaHoc(document.getString("tenkhoahoc"), document.getDouble("thoiluong"),document.getString("loaikhoahoc"),document.getString("date"));
                                khoahoc.setId(document.getId());
                                lisKhoaHoc.add(khoahoc);
                            }
//                            DatabaseAdapter.getInstance(MainActivity.this).congViecDao().deleteAll();
//                            for(int i = 0;i<lisCongViec.size();i++){
//
//                                DatabaseAdapter.getInstance(MainActivity.this).congViecDao().insert(lisCongViec.get(i));
//                            }
//                            listdb = DatabaseAdapter.getInstance(MainActivity.this).congViecDao().getAll();
//                            System.out.println("========"+listdb);
                            adt.notifyDataSetChanged();
//                            adt = new CongViecAdapter(MainActivity.this,R.layout.item_lv_work,listdb);
//                            lvCongViec.setAdapter(adt);
                        }
                    }
                });
    }

    public void addCongViec(KhoaHoc khoahoc1){
        // Create a new user with a first and last name
        Map<String, Object> khoahoc = new HashMap<>();
        khoahoc.put("tenkhoahoc",khoahoc1.getTenKhoaHoc());
        khoahoc.put("thoiluong", khoahoc1.getThoiLuong());
        khoahoc.put("loaikhoahoc", khoahoc1.getLoaiKhoaHoc());
        khoahoc.put("date", khoahoc1.getDate());


// Add a new document with a generated ID
        db.collection("khoahoc")
                .add(khoahoc)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this,"Add success",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Add fail",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void updateUI(){
        getListKhoaHoc();
        txtTenKhoaHoc.setText("");
        txtThoiLuong.setText("");

    }

    public static MainActivity getMainActive(){
        return getInstance;
    }
}