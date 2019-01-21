package com.smirix.pojo;

/**
 * Created by Виктор on 20.01.2019.
 */
public class TelegramUser {
    private Long id;
    private Long userId;
    private String token;
    private String name;
    private Integer idTlgm; ///< Unique identifier for this user or bot
    private String firstName; ///< User‘s or bot’s first name
    private Boolean isBot; ///< True, if this user is a bot
    private String lastName; ///< Optional. User‘s or bot’s last name
    private String userName; ///< Optional. User‘s or bot’s username
    private String languageCode; ///< Optional. IETF language tag of the user's language

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdTlgm() {
        return idTlgm;
    }

    public void setIdTlgm(Integer idTlgm) {
        this.idTlgm = idTlgm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getIsBot() {
        return isBot;
    }

    public void setIsBot(Boolean bot) {
        isBot = bot;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
