package entities;

/**
 * Created by Виктор on 06.09.2017.
 */
public class User
{
    private Long id;
    private Long loginId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }
}
