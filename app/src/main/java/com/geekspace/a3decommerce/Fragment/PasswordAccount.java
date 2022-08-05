package com.geekspace.a3decommerce.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geekspace.a3decommerce.Api.APIClient;
import com.geekspace.a3decommerce.Api.ApiInterface;
import com.geekspace.a3decommerce.Common.Utils;
import com.geekspace.a3decommerce.Model.CreateAccountPost;
import com.geekspace.a3decommerce.Model.CreateAccountResponse;
import com.geekspace.a3decommerce.Model.SignUpData;
import com.geekspace.a3decommerce.Model.SignUpUser;
import com.geekspace.a3decommerce.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PasswordAccount extends Fragment {

    private EditText password,passwordConfirm;
    private Button createAccount;
    private View view;
    private ApiInterface apiInterface;
    private String phoneNumber;
    private Context context;
    public PasswordAccount(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_password_account, container, false);
        context=getContext();
        initComponents();
        setListener();
        return view;
    }

    private void setListener() {
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiInterface= APIClient.getClient().create(ApiInterface.class);
                CreateAccountPost post=new CreateAccountPost(phoneNumber,password.getText().toString(),passwordConfirm.getText().toString());
                Call<CreateAccountResponse> call=apiInterface.createAccount(post);
                call.enqueue(new Callback<CreateAccountResponse>() {
                    @Override
                    public void onResponse(Call<CreateAccountResponse> call, Response<CreateAccountResponse> response) {
                        if(response.isSuccessful()){
                            CreateAccountResponse res=response.body();
                            SignUpData data=res.getData();
                            SignUpUser user=data.getUser();
                            Utils.setSharedPreferences(context,"tkn",res.getToken());
                            Utils.setSharedPreferences(context,"user_id",user.getUser_id());
                            Utils.removeShow(new Settings(),Settings.class.getSimpleName(),getFragmentManager(),R.id.content);
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateAccountResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void initComponents() {
        password=view.findViewById(R.id.password);
        passwordConfirm=view.findViewById(R.id.passwordConfirm);
        createAccount=view.findViewById(R.id.createAccount);
    }
}