package br.edu.uniritter.mobile.mobile20222_1.presenter;

import android.app.Activity;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Photo;
import br.edu.uniritter.mobile.mobile20222_1.model.Post;
import br.edu.uniritter.mobile.mobile20222_1.model.User;

public class PostsPresenterContract {
    public interface view {
        public void message(String msg);
        public Activity getActivity();
        public void listItens(List<Post> posts);
    }

    public interface presenter {
        public void getPosts(int id);
    }
}
