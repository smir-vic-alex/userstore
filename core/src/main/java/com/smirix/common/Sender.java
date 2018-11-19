package com.smirix.common;

public interface Sender<Req, Res> {

    Res send(Req request);
}
