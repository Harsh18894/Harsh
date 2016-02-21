package in.daybox.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.daybox.R;
import in.daybox.asynctask.LoginAsyncTask;
import in.daybox.dto.LoginDTO;
import in.daybox.dto.MessageCustomDialogDTO;
import in.daybox.ui.CustomEditText;
import in.daybox.ui.SnackBar;
import in.daybox.ui.TypefaceSpan;
import in.daybox.util.NetworkCheck;

/**
 * Created by Dell on 2/13/2016.
 */

public class LoginActivity extends ActionBarActivity {

    @InjectView(R.id.etPhoneNumber)
    CustomEditText etPhoneNumber;
    @InjectView(R.id.etPassword)
    CustomEditText etPassword;
    @InjectView(R.id.btnLogin)
    Button btnLogin;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.txtAgreement)
    TextView txtAgreement;
/*
    @InjectView(R.id.txtForgotPassword)
    com.neopixl.pixlui.components.textview.TextView txtForgotPassword;
*/
    @InjectView(R.id.txtShowPassword)
    com.neopixl.pixlui.components.textview.TextView txtShowPassword;
    @InjectView(R.id.llLogin)
    LinearLayout llLogin;
    private int transition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        populate();


    }

    private void populate() {
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        SpannableString s = new SpannableString("Login");
        s.setSpan(new TypefaceSpan(this, "LatoLatin-Regular.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) toolbar.findViewById(R.id.toolbarTitle)).setText(s);

        getSupportActionBar().setTitle("");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnLogin.setBackgroundResource(R.drawable.ripple);
        }


        txtShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtShowPassword.getText().toString().equals("show")) {
                    etPassword.setTransformationMethod(null);
                    txtShowPassword.setText("hide");
                    etPassword.setSelection(etPassword.getText().toString().length());

                } else {
                    etPassword.setTransformationMethod(new PasswordTransformationMethod());
                    txtShowPassword.setText("show");
                    etPassword.setSelection(etPassword.getText().toString().length());
                }
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDTO loginDTO = new LoginDTO();
                loginDTO.setPhone(etPhoneNumber.getText().toString().trim());
                loginDTO.setPassword(etPassword.getText().toString().trim());
                if (NetworkCheck.isNetworkAvailable(LoginActivity.this)){
                    if (loginDTO.getPhone().length() == 10){
                        if (loginDTO.getPassword().length() != 0){
                            LoginAsyncTask loginAsyncTask = new LoginAsyncTask(LoginActivity.this, loginDTO);
                            loginAsyncTask.execute();
                        }else {
                            MessageCustomDialogDTO messageCustomDialogDTO = new MessageCustomDialogDTO();
                            messageCustomDialogDTO.setTitle(getResources().getString(R.string.login_activity_invalid_password_title));
                            messageCustomDialogDTO.setButton(getResources().getString(R.string.ok));
                            messageCustomDialogDTO.setMessage(getResources().getString(R.string.login_activity_invalid_password));
                            messageCustomDialogDTO.setContext(LoginActivity.this);
                            SnackBar.show(LoginActivity.this, messageCustomDialogDTO);
                        }
                    }else {
                        MessageCustomDialogDTO messageCustomDialogDTO = new MessageCustomDialogDTO();
                        messageCustomDialogDTO.setTitle(getResources().getString(R.string.login_activity_invalid_phone_title));
                        messageCustomDialogDTO.setButton(getResources().getString(R.string.ok));
                        messageCustomDialogDTO.setMessage(getResources().getString(R.string.login_activity_invalid_phone));
                        messageCustomDialogDTO.setContext(LoginActivity.this);
                        SnackBar.show(LoginActivity.this, messageCustomDialogDTO);
                    }
                } else {
                    MessageCustomDialogDTO messageCustomDialogDTO = new MessageCustomDialogDTO();
                    messageCustomDialogDTO.setTitle(getResources().getString(R.string.login_activity_no_internet_title));
                    messageCustomDialogDTO.setButton(getResources().getString(R.string.ok));
                    messageCustomDialogDTO.setMessage(getResources().getString(R.string.login_activity_no_internet));
                    messageCustomDialogDTO.setContext(LoginActivity.this);
                    SnackBar.show(LoginActivity.this, messageCustomDialogDTO);
                }

            }
        });


        s = new SpannableString(getResources().getString(R.string.agreement_sentence));

        ClickableSpan termsSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(LoginActivity.this, LoadURLActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("action", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan privacySpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(LoginActivity.this, LoadURLActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("action", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ClickableSpan contentSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(LoginActivity.this, LoadURLActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("action", 3);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        s.setSpan(termsSpan, 34, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_steel)), 34, 51, 0);
        s.setSpan(new RelativeSizeSpan(1.1f), 34, 51, 0);

        s.setSpan(privacySpan, 51, 67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_steel)), 51, 67, 0);
        s.setSpan(new RelativeSizeSpan(1.1f), 51, 67, 0);

        s.setSpan(contentSpan, 71, 87, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_steel)), 71, 87, 0);
        s.setSpan(new RelativeSizeSpan(1.1f), 71, 87, 0);


        txtAgreement.setText(s);
        txtAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        txtAgreement.setHighlightColor(Color.TRANSPARENT);

       /* txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition = 1;
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
*/
        final GestureDetector gestureDetector = new GestureDetector(new MyGestureDetector());
        llLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) return false;
                return false;
            }
        });


    }

    protected void onResume()
    {
        super.onResume();
        transition = 0;
        if(!NetworkCheck.isNetworkAvailable(this))
            SnackBar.noInternet(this);
    }

    protected void onPause()
    {
        super.onPause();
        if(transition != 1)
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }



    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float slope = (e1.getY() - e2.getY()) / (e1.getX() - e2.getX());
                float angle = (float) Math.atan(slope);
                float angleInDegree = (float) Math.toDegrees(angle);
                if (e1.getX() - e2.getX() > 20 && Math.abs(velocityX) > 20) {
                    if ((angleInDegree < 45 && angleInDegree > -45)) {
                        finish();
                    }
                } else if (e2.getX() - e1.getX() > 20 && Math.abs(velocityX) > 20) {
                    if ((angleInDegree < 45 && angleInDegree > -45)) {
                        finish();
                    }
                }
                return true;
            } catch (Exception e) {
            }
            return false;
        }
    }

}


