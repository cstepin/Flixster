package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.MovieDetailsActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

//Definition for MovieAdapter Class
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;
  //  MovieAdapterBinding binding;

    /*
    FancyFragmentBinding binding;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    // fancy_fragment.xml -> FancyFragmentBinding
    binding = FancyFragmentBinding.inflate(getLayoutInflater(), container, false);

    // layout of fragment is stored in a special property called root
    View view = binding.getRoot();

    // TODO Use fields...
    // binding.
    return view;
  }
     */

    //Constructor for MovieAdapter (Has a context and a list of movies)
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    // Starting state of the ViewHolder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Movie adapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    //Handles for when data is bound to view holders
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("Movie adapter", "onBindViewHolder");
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    //Returns number of movies in our view holder
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);

            itemView.setOnClickListener(this);
        }
        // Creation a method here that binds each view to the RV
        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
           // ivPoster.set(Glide.with(context).load(movie.getPosterPath()));
         //   Glide.with(context).load(movie.getPosterPath()).into(ivPoster);

            String imageURL;

            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageURL = movie.getBackdropPath();
            }
            else{
                imageURL = movie.getPosterPath();
            }

            int radius = 30; // corner radius, higher value = more rounded

            Glide.with(context)
                    .load(imageURL)
                    .centerCrop() // scale image to fill the entire ImageView
                    .transform(new RoundedCorners(radius))
                    .placeholder(R.drawable.flicks_backdrop_placeholder)
                    .error(R.drawable.flicks_movie_placeholder)
                    .into(ivPoster);

        //    Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
        }

        @Override
        // Ensures that
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Movie movie = movies.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                // show the activity
                context.startActivity(intent);
            }
        }
    }
}
