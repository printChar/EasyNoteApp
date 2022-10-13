package com.example.easynoteapp.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easynoteapp.NoteClickListener;
import com.example.easynoteapp.R;
import com.example.easynoteapp.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteListAdapter extends RecyclerView.Adapter<NoteViewHolder>{

    private Context context;
    private List<Note> list;
    private NoteClickListener noteClickListener;

    public NoteListAdapter(Context context, List<Note> list, NoteClickListener noteClickListener) {
        this.context = context;
        this.list = list;
        this.noteClickListener = noteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_list, parent, false));
    }

    //depending on which one u press
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(list.get(position).getDesc());

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        int colourCode = getRandomColour();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(colourCode, null));
        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteClickListener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                noteClickListener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });
    }

    private int getRandomColour(){
        List<Integer> colourCode = new ArrayList<>();

        colourCode.add(R.color.nationBlue);
        colourCode.add(R.color.mauvelous);
        colourCode.add(R.color.lilac);
        colourCode.add(R.color.yellow);

        Random random = new Random();
        int random_colour = random.nextInt(colourCode.size());
        return colourCode.get(random_colour);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class NoteViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView textView_title, textView_notes, textView_date;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        notes_container = itemView.findViewById(R.id.notes_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_notes = itemView.findViewById(R.id.textView_notes);
        textView_date = itemView.findViewById(R.id.textView_date);
    }
}
