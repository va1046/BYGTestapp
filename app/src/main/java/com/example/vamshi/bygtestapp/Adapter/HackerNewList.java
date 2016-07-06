package com.example.vamshi.bygtestapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vamshi.bygtestapp.Model.BlogPost;
import com.example.vamshi.bygtestapp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vamshi on 06-07-2016.
 */
public class HackerNewList extends RecyclerView.Adapter<HackerNewList.CustomViewHolder> {


    Context mContext;
    List<BlogPost> blogPosts;

    public HackerNewList(Context mContext, List<BlogPost> blogPosts) {
        this.mContext = mContext;
        this.blogPosts = blogPosts;
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newlistitem,parent,false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        BlogPost blogPost = blogPosts.get(position);
        holder.mNavTitle.setText(blogPost.getTitle());
        holder.mNavDesc.setText(blogPost.getBy());
    }

    @Override
    public int getItemCount() {
        if (blogPosts!=null)
            return blogPosts.size();
        else
            return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.Post_title)
        TextView mNavTitle;

        @Bind(R.id.Post_Description)
        TextView mNavDesc;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void animateTo(List<BlogPost> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<BlogPost> newModels) {
        for (int i = blogPosts.size() - 1; i >= 0; i--) {
            final BlogPost model = blogPosts.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<BlogPost> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final BlogPost model = newModels.get(i);
            if (!blogPosts.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<BlogPost> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final BlogPost model = newModels.get(toPosition);
            final int fromPosition = blogPosts.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public BlogPost removeItem(int position) {
        final BlogPost model = blogPosts.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, BlogPost model) {
        blogPosts.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final BlogPost model = blogPosts.remove(fromPosition);
        blogPosts.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

}
