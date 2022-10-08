package br.edu.uniritter.mobile.mobile20222_1.presenter;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Album;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.repository.AlbumRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepository;
import br.edu.uniritter.mobile.mobile20222_1.view.MainActivity;

public class AlbumPresenter implements AlbumsPresenterContract.presenter{
    private AlbumsPresenterContract.view view;

    public AlbumPresenter(AlbumsPresenterContract.view view) {
        this.view = view;
    }

    @Override
    public void getAlbums(int id) {
        List<Album> albums = AlbumRepository.getInstance(view.getActivity()).getAlbumsbyUser(id);
        if(albums.size() > 0)
            view.listItens(albums);
        else
            view.message("Nenhum album encontrado");
    }
}
