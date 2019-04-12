package com.bubblestudios.bubble;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CardViewHolder extends RecyclerView.ViewHolder {
    public TextView artistName;
    public TextView songTitle;
    public ImageView albumArt;

    public CardViewHolder(final View itemView) {
        super(itemView);
        artistName = (TextView) itemView.findViewById(R.id.artist_name);
        songTitle = (TextView) itemView.findViewById(R.id.song_title);
        albumArt = (ImageView) itemView.findViewById(R.id.album_art_view);
    }
}
