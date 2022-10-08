package br.edu.uniritter.mobile.mobile20222_1.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Album;
import br.edu.uniritter.mobile.mobile20222_1.model.User;

public class AlbumsPresenterContract {
    public interface view {
        public void message(String msg);
        public Activity getActivity();
        public void listItens(List<Album> albuns);
    }

    public interface presenter {
        public void getAlbums(int id);
    }

}
