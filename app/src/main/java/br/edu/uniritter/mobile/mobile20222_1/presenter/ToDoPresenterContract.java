package br.edu.uniritter.mobile.mobile20222_1.presenter;

import android.app.Activity;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Post;
import br.edu.uniritter.mobile.mobile20222_1.model.Todo;
import br.edu.uniritter.mobile.mobile20222_1.model.User;

public class ToDoPresenterContract {
    public interface view {
        public void message(String msg);
        public Activity getActivity();
        public void listItens(List<Todo> todos);
    }

    public interface presenter {
        public void getTodos(int id);
    }
}
