package actionForms;

import org.apache.struts.action.ActionForm;

/**
 * Created by SBT-Smirnov-VA on 19.07.2017.
 */
public class LoginActionForm extends ActionForm {

    private String password;
    private String login;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
