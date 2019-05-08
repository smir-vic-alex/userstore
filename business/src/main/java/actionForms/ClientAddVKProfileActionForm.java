package actionForms;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by Smirnov-VA on 20.07.2017.
 */
public class ClientAddVKProfileActionForm extends ActionFormBase {

    private String userActorAuthUrl;

    public String getUserActorAuthUrl() {
        return userActorAuthUrl;
    }

    public void setUserActorAuthUrl(String userActorAuthUrl) {
        this.userActorAuthUrl = userActorAuthUrl;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
