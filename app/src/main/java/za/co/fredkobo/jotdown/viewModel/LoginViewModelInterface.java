package za.co.fredkobo.jotdown.viewModel;

/**
 * Created by F5094712 on 2018/06/26.
 */

public interface LoginViewModelInterface {
    boolean attemptEmailSignIn(String email, String password);
    boolean attemptGoogleSignIn();
}
