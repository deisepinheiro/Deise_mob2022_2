package br.edu.uniritter.mobile.mobile20222_1.presenter;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Album;
import br.edu.uniritter.mobile.mobile20222_1.model.Comment;
import br.edu.uniritter.mobile.mobile20222_1.repository.AlbumRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.CommentRepository;

public class CommentPresenter implements CommentPresenterContract.presenter{
    private CommentPresenterContract.view view;

    public CommentPresenter(CommentPresenterContract.view view) {
        this.view = view;
    }

    @Override
    public void getComments(int idPost) {
        List<Comment> comments = CommentRepository.getInstance(view.getActivity()).getCommentsByPost(idPost);
        if(comments.size() > 0)
            view.listItens(comments);
        else
            view.message("Nenhum comentário encontrado");
    }
}
