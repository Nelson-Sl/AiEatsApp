package comp5216.sydney.edu.au.aieatsapp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class foodAppSeller extends AppCompatActivity {

    private TextView welcome;
    private Button profile_edit,food_manager,logout,manage_order;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_app_seller);
        //set views
        welcome = (TextView) findViewById(R.id.welcome_user);
        profile_edit = (Button) findViewById(R.id.edit_profile);
        food_manager = (Button) findViewById(R.id.manage_food);
        manage_order = (Button) findViewById(R.id.manage_order);
        logout = (Button)findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        //Session Check
        if (mAuth.getCurrentUser() != null){
            welcome.setText("Welcome, " + mAuth.getCurrentUser().getDisplayName());
        }
        //edit profile onclick listener
        profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(foodAppSeller.this,SellerProfile.class));
            }
        });
        //edit food manager onclick listener
        food_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(foodAppSeller.this,SellerFoodList.class));
            }
        });
        //edit manage order onclick listener
        manage_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(foodAppSeller.this,SellerOrderList.class));
            }
        });
        //logout onclick listener
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                if (mAuth.getCurrentUser() == null){
                    Toast.makeText(foodAppSeller.this,"You have successfully logout !",
                            Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(foodAppSeller.this,LoginActivity.class));
                            finish();
                        }
                    },1000);
                }
            }
        });
    }
}
