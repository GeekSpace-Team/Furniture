package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geekspace.a3decommerce.Api.APIClient;
import com.geekspace.a3decommerce.Api.ApiInterface;
import com.geekspace.a3decommerce.Model.SignUpResponse;
import com.geekspace.a3decommerce.Post.SignUpPost;
import com.geekspace.a3decommerce.R;

import org.mindrot.jbcrypt.BCrypt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpPhoneNumber extends Fragment {

    private View view;
    private Context context;
    private Button nextBtn;
    private EditText phone;
    private TextView sendCode;
    private ApiInterface apiInterface;
    private ProgressBar progress;
    private String hashedCode=null;
    private EditText first,second,third,fourth,fifth,sixth;
    public SignUpPhoneNumber() {
    }


    public static SignUpPhoneNumber newInstance(String param1, String param2) {
        SignUpPhoneNumber fragment = new SignUpPhoneNumber();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_sign_up_phone_number, container, false);
        context=getContext();
        initComponents();
        setListener();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String one=first.getText().toString();
                String two=second.getText().toString();
                String three=third.getText().toString();
                String four=fourth.getText().toString();
                String five=fifth.getText().toString();
                String six=sixth.getText().toString();

                if(one.length()==0 || two.length()==0 || three.length()==0 || four.length()==0 || five.length()==0 || six.length()==0){
                    Toast.makeText(context, "Kody doly girizin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(hashedCode!=null){
                    if (BCrypt.checkpw(one+two+three+four+five+six, hashedCode))
                        getFragmentManager().beginTransaction().replace(R.id.frame,new PasswordAccount("+993"+phone.getText().toString()),SignUpBody.class.getSimpleName()).commit();
                    else
                        Toast.makeText(context, "Kod nadogry", Toast.LENGTH_SHORT).show();

                }

            }
        });
        return view;
    }

    private void setListener() {
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                sendCode.setVisibility(View.INVISIBLE);
                apiInterface= APIClient.getClient().create(ApiInterface.class);
                SignUpPost post=new SignUpPost("+993"+phone.getText().toString(),"","");
                Call<SignUpResponse> call=apiInterface.signUp(post);
                call.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if(response.isSuccessful()){
                            hashedCode=response.body().getId();
                            Toast.makeText(context, "Sms kod ugradyldy: "+"+993"+phone.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        progress.setVisibility(View.INVISIBLE);
                        sendCode.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        progress.setVisibility(View.INVISIBLE);
                        sendCode.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        first.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                second.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                third.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        third.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fourth.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fourth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fifth.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fifth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sixth.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sixth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sixth.clearFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initComponents() {
        nextBtn=view.findViewById(R.id.nextBtn);
        phone=view.findViewById(R.id.phone);
        sendCode=view.findViewById(R.id.sendCode);
        progress=view.findViewById(R.id.progress);
        first=view.findViewById(R.id.first);
        second=view.findViewById(R.id.second);
        third=view.findViewById(R.id.third);
        fourth=view.findViewById(R.id.fourth);
        fifth=view.findViewById(R.id.fifth);
        sixth=view.findViewById(R.id.sixth);
    }
}