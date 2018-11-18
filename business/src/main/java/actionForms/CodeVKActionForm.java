package actionForms;

import org.apache.struts.action.ActionForm;

/**
 * Created by SBT-Smirnov-VA on 21.07.2017.
 */
public class CodeVKActionForm extends ActionForm {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
