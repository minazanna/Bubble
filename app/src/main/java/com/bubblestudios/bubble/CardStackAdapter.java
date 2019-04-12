package com.bubblestudios.bubble;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

public class CardStackAdapter extends FirebaseRecyclerAdapter<Snippet, CardViewHolder> {

    private StorageReference albumArtRef;
    private StorageReference snippetRef;
    private SimpleExoPlayer exoPlayer;
    private DataSource.Factory dataSourceFactory;


    public CardStackAdapter(@NonNull FirebaseRecyclerOptions options, StorageReference albumArtRef, StorageReference snippetRef, SimpleExoPlayer exoPlayer, DataSource.Factory dataSourceFactory) {
        super(options);
        this.albumArtRef = albumArtRef;
        this.snippetRef = snippetRef;
        this.exoPlayer = exoPlayer;
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    protected void onBindViewHolder(@NonNull final CardViewHolder holder, final int position, @NonNull final Snippet snippet) {
        holder.artistName.setText(snippet.getArtist());
        holder.songTitle.setText(snippet.getTitle());
        Glide.with(holder.albumArt).load(albumArtRef.child(snippet.getAlbumArt())).into(holder.albumArt);

        snippetRef.child(snippet.getSnippet()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                MediaSource audioSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
                holder.audioSource = audioSource;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), snippet.getTitle(), Toast.LENGTH_SHORT).show();
                exoPlayer.setPlayWhenReady(!exoPlayer.getPlayWhenReady());
            }
        });

    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false));
    }


}
