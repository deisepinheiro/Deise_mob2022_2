package br.edu.uniritter.mobile.mobile20222_1.presenter;

import android.app.Activity;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Comment;
import br.edu.uniritter.mobile.mobile20222_1.model.Photo;
import br.edu.uniritter.mobile.mobile20222_1.model.User;

public class PhotosPresenterContract {
    public interface view {
        public void message(String msg);
        public Activity getActivity();
        public void listItens(List<Photo> photos);
    }

    public interface presenter {
        public void getPhotos(int id);
    }
}
