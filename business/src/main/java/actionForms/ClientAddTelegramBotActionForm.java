package actionForms;

import org.apache.struts.action.ActionForm;

/**
 * Created by Виктор on 21.01.2019.
 */
public class ClientAddTelegramBotActionForm extends ActionForm {
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
}
