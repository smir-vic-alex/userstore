package entities;

/**
 * Created by SBT-Smirnov-VA on 20.07.2017.
 */
public class Password {

    private Long id;
    private String hash;
    private Long loginId;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

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
