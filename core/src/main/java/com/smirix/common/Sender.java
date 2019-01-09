package com.smirix.common;

/**
 * Created by Виктор on 05.01.2019.
 */
public interface Sender<Rq, Rs> {

    Rs send(Rq request) throws Exception;

}