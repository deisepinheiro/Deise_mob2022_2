package br.edu.uniritter.mobile.mobile20222_1.presenter;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Comment;
import br.edu.uniritter.mobile.mobile20222_1.model.Photo;
import br.edu.uniritter.mobile.mobile20222_1.repository.CommentRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PhotoRepository;

public class PhotosPresenter implements PhotosPresenterContract.presenter{
    private PhotosPresenterContract.view view;

    public PhotosPresenter(PhotosPresenterContract.view view) {
        this.view = view;
    }

    @Override
    public void getPhotos(int idAlbum) {
        List<Photo> photos = PhotoRepository.getInstance(view.getActivity()).getPhotobyAlbum(idAlbum);
        if(photos.size() > 0)
            view.listItens(photos);
        else
            view.message("Nenhum foto encontrada");
    }
}
