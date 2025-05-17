package dev.manyroads.serializable;


import java.io.Serial;
import java.io.Serializable;

class TestSerial implements Serializable{
    @Serial
    private static final long serialVersionUID = 7L;

    String testName;
    transient String nonSerializedTestName;
}



