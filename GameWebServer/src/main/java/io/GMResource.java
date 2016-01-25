package io;

import base.Resource;

import java.io.File;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class GMResource implements Resource, AutoCloseable {
    private File file;
    public GMResource(String uri) {
        this.file = new File(uri);
    }

    @Override
    public void close() {
        file = null;
    }
}
