package com.e.cryptocracy.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.ActivityGettingStartedBinding;
import com.e.cryptocracy.utility.AppUtils;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class GettingStarted extends AppCompatActivity {
    ActivityGettingStartedBinding binding;
    private static final String TAG = "GettingStarted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_getting_started);
    }

    @Override
    protected void onStart() {
        super.onStart();

        binding.btnGetStarted.setOnClickListener(v -> {
            loginUi();
        });
    }

    private void loginUi() {
        //binding.progressBar10.setVisibility(View.VISIBLE);
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTosAndPrivacyPolicyUrls(
                                "https://example.com/terms.html",
                                "https://example.com/privacy.html")
                        //.setLogo(R.drawable.logoo)      // Set logo drawable
                        .setTheme(R.style.AppTheme_NoActionBar)
                        .build(),
                10);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //binding.progressBar10.setVisibility(View.GONE);
        if (requestCode == 10) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Toast.makeText(this, "sign in successfully", Toast.LENGTH_SHORT).show();
                    AppUtils.updateUserInfo(user);
                    startActivity(new Intent(GettingStarted.this, AppHomeScreen.class));
                    finish();
                }

            } else {
                Toast.makeText(this, "Sign in failed ", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onActivityResult: " + response.getError().getLocalizedMessage());
            }
        }
    }
}