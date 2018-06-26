package za.co.fredkobo.jotdown.viewModel;

import za.co.fredkobo.jotdown.LoginViewInterface;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class LoginViewModel implements LoginViewModelInterface{
    private LoginViewInterface loginViewInterface;

    public LoginViewModel(LoginViewInterface loginViewInterface){
        this.loginViewInterface = loginViewInterface;
    }

    @Override
    public boolean attemptEmailSignIn(String email, String password) {
        return false;
    }

    @Override
    public boolean attemptGoogleSignIn() {
        loginViewInterface.onSignInSuccess();
        return true;
    }
}
