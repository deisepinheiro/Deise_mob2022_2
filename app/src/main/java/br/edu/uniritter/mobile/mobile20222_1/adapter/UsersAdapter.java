package br.edu.uniritter.mobile.mobile20222_1.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.view.AlbumsActivity;
import br.edu.uniritter.mobile.mobile20222_1.view.PostsActivity;
import br.edu.uniritter.mobile.mobile20222_1.view.ToDoActivity;
import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.model.User;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private List<User> dados;

    public UsersAdapter(List<User> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_vh,parent,false);
        return new UserViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = dados.get(position);
        TextView tv1 = holder.itemView.findViewById(R.id.textViewId);
        tv1.setText(user.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewName)).setText(user.getName());
        Button BtnTodo = holder.itemView.findViewById(R.id.btn_todo);
        Button BtnAlbum = holder.itemView.findViewById(R.id.btn_albuns);
        Button BtnPost = holder.itemView.findViewById(R.id.btn_posts);

        BtnTodo.setOnClickListener((view)->{
            Intent intent = new Intent(view.getContext(), ToDoActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });
        BtnAlbum.setOnClickListener((view)->{
            Intent intent = new Intent(view.getContext(), AlbumsActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });
        BtnPost.setOnClickListener((view)->{
            Intent intent = new Intent(view.getContext(), PostsActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
class UserViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}
