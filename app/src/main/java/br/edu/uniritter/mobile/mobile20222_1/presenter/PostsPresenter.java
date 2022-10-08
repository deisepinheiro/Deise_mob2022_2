package br.edu.uniritter.mobile.mobile20222_1.presenter;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Photo;
import br.edu.uniritter.mobile.mobile20222_1.model.Post;
import br.edu.uniritter.mobile.mobile20222_1.repository.PhotoRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PostRepository;

public class PostsPresenter implements PostsPresenterContract.presenter{
    private PostsPresenterContract.view view;

    public PostsPresenter(PostsPresenterContract.view view) {
        this.view = view;
    }

    @Override
    public void getPosts(int idUser) {
        List<Post> posts = PostRepository.getInstance(view.getActivity()).getPostsbyUser(idUser);
        if(posts.size() > 0)
            view.listItens(posts);
        else
            view.message("Nenhum post encontrado");
    }
}
