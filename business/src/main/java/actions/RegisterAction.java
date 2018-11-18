package actions;

import accesses.EncryptUtils;
import actionForms.RegisterActionForm;
import entities.User;
import hibernate.HibernateExecutor;
import hibernate.services.AuthService;
import entities.Login;
import entities.Password;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import utils.StringUtils;
import utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SBT-Smirnov-VA on 25.07.2017.
 */
public class RegisterAction extends LookupDispatchAction {

    private static final AuthService authService = new AuthService();
    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegisterActionForm registerActionForm = (RegisterActionForm) form;

        if (!StringUtils.isNotEmpty(registerActionForm.getLogin(), registerActionForm.getPassword())){
            return mapping.findForward("fail");
        }

        if (StringUtils.isNotEmpty(registerActionForm.getLogin()) && authService.findByLogin(registerActionForm.getLogin()) == null){
            User user = createNewUser(registerActionForm.getLogin(), registerActionForm.getPassword());
            UserUtils.setUserIsLogin();
            UserUtils.setCurrentUser(user);
        }
        return success(mapping);
    }

    private User createNewUser(final String lgn, final String passwd){

        try {
            return new HibernateExecutor<User>().execute((session) ->
                {
                    Login login = new Login();
                    login.setLogin(lgn);
                    session.saveOrUpdate(login);

                    Password password = new Password();
                    password.setHash(EncryptUtils.code(passwd));
                    password.setLoginId(login.getId());
                    session.saveOrUpdate(password);

                    User newUser = new User();
                    newUser.setLoginId(login.getId());

                    session.saveOrUpdate(newUser);

                    return newUser;
                }
            );
        } catch (Exception e) {
            throw e;
        }
    }
}
