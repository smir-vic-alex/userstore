package actionForms;

import org.apache.struts.action.ActionForm;


/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-08
 */
public abstract class ActionFormBase extends ActionForm {

    public abstract boolean validate();
}
