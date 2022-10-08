package br.edu.uniritter.mobile.mobile20222_1.presenter;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Todo;
import br.edu.uniritter.mobile.mobile20222_1.repository.TodoRepository;

public class ToDoPresenter implements ToDoPresenterContract.presenter{
    private ToDoPresenterContract.view view;

    public ToDoPresenter(ToDoPresenterContract.view view) {
        this.view = view;
    }

    @Override
    public void getTodos(int idTodo) {
        List<Todo> todos = TodoRepository.getInstance(view.getActivity()).getUserTodos(idTodo);
        if(todos.size() > 0)
            view.listItens(todos);
        else
            view.message("Nenhuma tarefa encontrada");
    }
}
