package com.bubblestudios.bubble;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.storage.StorageReference;

public class CardStackAdapter extends FirebaseRecyclerAdapter<Snippet, CardViewHolder> {

    private StorageReference albumArtRef;

    public CardStackAdapter(@NonNull FirebaseRecyclerOptions options, StorageReference mAlbumArtRef) {
        super(options);
        this.albumArtRef = mAlbumArtRef;
    }

    @Override
    protected void onBindViewHolder(@NonNull final CardViewHolder holder, int position, @NonNull final Snippet snippet) {
        holder.artistName.setText(snippet.getArtist());
        holder.songTitle.setText(snippet.getTitle());
        Glide.with(holder.albumArt).load(albumArtRef.child(snippet.getAlbumArt())).into(holder.albumArt);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), snippet.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false));
    }


}
