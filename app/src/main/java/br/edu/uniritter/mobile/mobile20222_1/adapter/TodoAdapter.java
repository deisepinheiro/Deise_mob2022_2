package br.edu.uniritter.mobile.mobile20222_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.model.Todo;

public class TodoAdapter extends RecyclerView.Adapter {

    private List<Todo> todos;

    public TodoAdapter(List<Todo> todos) {
       this.todos = todos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todo_vh,parent,false);
        return new TodoViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Todo todo = todos.get(position);
        ((TextView) holder.itemView.findViewById(R.id.txv_id)).setText(todo.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.txv_description)).setText(todo.getTitle());

        ((CheckBox) holder.itemView.findViewById(R.id.chk_done)).setChecked(todo.isCompleted());
        holder.itemView.setBackgroundResource(todo.isCompleted() ?  R.color.done : R.color.undone );

        ((CheckBox) holder.itemView.findViewById(R.id.chk_done)).setOnCheckedChangeListener((compoundButton, b) -> {
            holder.itemView.setBackgroundResource(b ? R.color.done : R.color.undone );
        });

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}

class TodoViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public TodoViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}
