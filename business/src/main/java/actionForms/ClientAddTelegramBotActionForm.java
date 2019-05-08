package actionForms;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ClientAddTelegramBotActionForm extends ActionFormBase {
    private String name;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
