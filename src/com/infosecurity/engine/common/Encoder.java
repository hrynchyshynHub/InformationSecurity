package com.infosecurity.engine.common;

public interface Encoder<Data> {
    Data encrypt(Data t);
}
