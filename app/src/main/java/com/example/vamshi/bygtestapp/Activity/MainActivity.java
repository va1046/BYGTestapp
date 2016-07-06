package com.example.vamshi.bygtestapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.vamshi.bygtestapp.Adapter.HackerNewList;
import com.example.vamshi.bygtestapp.Model.BlogPost;
import com.example.vamshi.bygtestapp.R;
import com.example.vamshi.bygtestapp.Utils.RecyclerItemClickListener;
import com.example.vamshi.bygtestapp.Utils.SimpleDividerItemDecoration;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    ArrayList<BlogPost> blogPosts = new ArrayList<>();
    HackerNewList hackerNewList;

    @Bind(R.id.hackernewslist)
    RecyclerView recyclerView;

    @Bind(R.id.searchKeyword)
    EditText searchKeyword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);


        final Firebase ref = new Firebase("https://hacker-news.firebaseio.com/v0/");

        ref.child("topstories").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                Log.d("MAINACTIVITY", "onDataChange: ");

                for (DataSnapshot child : snapshot.getChildren()) {

                    final Query query = ref.child("item").child(String.valueOf(child.getValue()));

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot1) {
                            BlogPost post = snapshot1.getValue(BlogPost.class);
                            System.out.println(post.getTitle());
                            blogPosts.add(post);
                            if (!blogPosts.isEmpty()) {
                                hackerNewList = null;
                                hackerNewList = new HackerNewList(getApplicationContext(), blogPosts);
                                recyclerView.setAdapter(hackerNewList);
                                if (!snapshot1.hasChildren()) {
                                    ref.removeEventListener(this);
                                    query.removeEventListener(this);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }


                    });

                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        hackerNewList = null;
        hackerNewList = new HackerNewList(getApplicationContext(), blogPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        recyclerView.setAdapter(hackerNewList);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent inThreadActivity = new Intent(MainActivity.this, ShowStoryActivity.class);
                        inThreadActivity.putExtra("URL", blogPosts.get(position).getUrl());
                        inThreadActivity.putExtra("Title", blogPosts.get(position).getTitle());
                        startActivity(inThreadActivity);
                    }
                })
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search Topic...");
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<BlogPost> filteredModelList = filter(blogPosts, query);
        hackerNewList.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<BlogPost> filter(List<BlogPost> models, String query) {
        query = query.toLowerCase();

        final List<BlogPost> filteredModelList = new ArrayList<>();
        for (BlogPost model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
