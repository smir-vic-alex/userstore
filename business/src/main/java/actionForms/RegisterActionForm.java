package actionForms;

import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Smirnov-VA on 25.07.2017.
 */
public class RegisterActionForm extends ActionFormBase {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
