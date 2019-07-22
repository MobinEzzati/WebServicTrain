package com.tnbclub.gridviewtrain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.tnbclub.gridviewtrain.WebServices.Api;
import com.tnbclub.gridviewtrain.WebServices.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit ;
    Api api ;

    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       getPostsByUsingRetrofit();

    }

    private void getPostsByUsingRetrofit(){
         retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        Call<List<Example>>listCall = api.getPosts();
        listCall.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                Toast.makeText(MainActivity.this, "we got respone", Toast.LENGTH_SHORT).show();
                List<Example>examples = new ArrayList<>();
                 examples = response.body() ;

                textView = findViewById(R.id.textRes);
                    String content = "";
                    for (Example example : examples){
                       content = "id number is:"+example.getId()+"\n" ;
                       content += "body is :" + example.getBody() +"\n";
                       content += " title is:" + example.getTitle() +"\n";
                       content +="\n"+"\n";
                        textView.append(content);
                    }
            }
            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"you have problem", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
