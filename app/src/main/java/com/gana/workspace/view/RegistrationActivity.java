package com.gana.workspace.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.gana.workspace.R;
import com.gana.workspace.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;
    private TextInputEditText inputUsername;
    private TextInputEditText inputPassword;
    private Button button4;
    private FirebaseAuth mAuth;
    private CircularProgressIndicator progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        setupEdgeToEdge();
        processIntentData();
        setUi();
        onclickListener();
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Toast.makeText(RegistrationActivity.this, "Authentication successfull.", Toast.LENGTH_SHORT).show();
//        }
    }
    private void onclickListener() {
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  email = String.valueOf(inputUsername.getText());
                String  password = String.valueOf(inputPassword.getText());
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(RegistrationActivity.this, "Authentication successfull.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                                    setIntent(intent);
                                    finish();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(RegistrationActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }



    private void setUi() {
        inputUsername = binding.inputUsername;
        inputPassword = binding.inputPassword;
        button4 = binding.button4;
        progressBar = binding.progressBar;
        mAuth = FirebaseAuth.getInstance();
    }

    private void setupEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void processIntentData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}