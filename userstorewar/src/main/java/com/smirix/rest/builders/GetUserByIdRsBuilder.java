package com.smirix.rest.builders;

import com.smirix.entities.user.User;
import com.smirix.rest.elements.messages.Status;
import ru.json2pojo.beans.GetUserByIdRs;

/**
 * Created by Виктор on 31.10.2018.
 */
public class GetUserByIdRsBuilder implements ResponseBuilder<GetUserByIdRs> {

    private User user;
    private String messageFail;

    public GetUserByIdRsBuilder(User user) {
        this.user = user;
    }

    public GetUserByIdRsBuilder(Exception e) {
        this.messageFail= e.getMessage();
    }

    @Override
    public GetUserByIdRs success() {
        GetUserByIdRs rs = new GetUserByIdRs();
        rs.setUser(user);

        return rs;
    }

    @Override
    public GetUserByIdRs fail() {
        GetUserByIdRs rs = new GetUserByIdRs();
        rs.setStatus(new Status(-1L, messageFail));
        return rs;
    }
}
