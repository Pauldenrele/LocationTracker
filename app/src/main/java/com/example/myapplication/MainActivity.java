package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Model.User;
import com.example.myapplication.Utils.Common;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Arrays;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

Button btnLogin;
private final static int LOGIN_PERMISSION = 1000;
/*  Intent intent;
    DatabaseReference user_info;
    private final static int MY_REQUEST_CODE= 1000;
    List<AuthUI.IdpConfig> providers;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_main);


   btnLogin = (Button)findViewById(R.id.btnLogin);

   btnLogin.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

             startActivityForResult(
                     AuthUI.getInstance().createSignInIntentBuilder()
                     .setAllowNewEmailAccounts(true).build() , LOGIN_PERMISSION
             );
       }
   });



    /*    Paper.init(this);

     //FirebaseInit
        user_info = FirebaseDatabase.getInstance().getReference(Common.USER_INFO);

     //ProviderInit
        providers = Arrays.asList(
             new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        //Include Permissions

        locationPermission();
    }

    private void locationPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        showSignOptions();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity.this, "You should accept the request to use the app", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }

    private void showSignOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build() ,MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode==RESULT_OK){
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                //Checcking if user exist

                user_info.orderByKey()
                        .equalTo(firebaseUser.getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue() !=null)  //if the user does nt exists

                                {
                                    if(!dataSnapshot.child(firebaseUser.getUid()).exists())   //If key UID doesnt exists
                                         {
                                     Common.loggedUser = new User(firebaseUser.getUid() , firebaseUser.getEmail());
                                     //Add to database
                                             user_info.child(Common.loggedUser.getUid())
                                                     .setValue(Common.loggedUser);

                                    }
                                }
                                else {

                                    Common.loggedUser = dataSnapshot.child(firebaseUser.getUid()).getValue(User.class);


                                    //Save UID to Storsge to update the background location

                                    Paper.book().write(Common.USER_UID_SAVE_KEY , Common.loggedUser.getUid());

                                    updateToken(firebaseUser);
                                   // UIsetup();
                                    intent = new Intent(MainActivity.this , BottomAcitvity.class);
                                    startActivity(intent);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
        }
    }

    private void UIsetup() {
        //Navigate Home

       // finish();
    }


    private void updateToken(final FirebaseUser firebaseUser) {
        final DatabaseReference tokens = FirebaseDatabase.getInstance().getReference(Common.tokens);

        //Get Token
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                tokens.child(firebaseUser.getUid()).setValue(instanceIdResult.getToken());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode==LOGIN_PERMISSION){
           startNewActivity(resultCode , data);
       }

    }

    private void startNewActivity(int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            Intent intent = new Intent(MainActivity.this , ListOnline.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }
}

