package br.edu.uniritter.mobile.mobile20222_1.presenter;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepository;
import br.edu.uniritter.mobile.mobile20222_1.view.MainActivity;

public class LoginPresenter implements LoginPresenterContract.presenter{
    private LoginPresenterContract.view view;

    public LoginPresenter(LoginPresenterContract.view view) {
        this.view = view;
    }
    @Override
    public void checkLogin(String login, String password) {
        UserRepository repo  = UserRepository.getInstance(view.getActivity(), null);
        User u = repo.getUserByUserLogin(login);
        if (u == null || ! u.getPassword().equals(password)) {
            view.message("Usuário ou senha Inválido");
        } else {
            validLogin(u);
        }
    }
    @Override
    public void validLogin(User user) {
        saveLoginSharedPreferences(user);
        sendActivity(user);
    }

    private void sendActivity(User user) {
        Intent intent = new Intent(view.getActivity(), MainActivity.class);
        intent.putExtra("userObj", user);
        view.getActivity().startActivity(intent);
    }

    @Override
    public void saveLoginSharedPreferences(User user) {
        SharedPreferences sp = getDefaultSharedPreferences(view.getActivity());
        sp.edit().putString("login", user.getUserLogin()).apply();
        sp.edit().putString("password", user.getPassword()).apply();
        sp.edit().putString("name", user.getName()).apply();
        sp.edit().putInt("id", user.getId()).apply();
    }

    @Override
    public void loadLoginSharedPreferences() {
        SharedPreferences sp = getDefaultSharedPreferences(view.getActivity());
        String name = sp.getString("name",null);
        String login = sp.getString("login",null);
        String password = sp.getString("password",null);
        int id = sp.getInt("id",0);

        if (id > 0)
            sendActivity(new User(id,name,login,password));
    }


}
