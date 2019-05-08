package actionForms;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by Smirnov-VA on 21.07.2017.
 */
public class CodeVKActionForm extends ActionFormBase {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
