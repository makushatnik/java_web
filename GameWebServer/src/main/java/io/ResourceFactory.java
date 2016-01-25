package io;

import base.Resource;

import java.io.File;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 *
 * Singleton.
 * ѕри создании запоминаем корневую папку ресурсов.
 * ћожем ее обойти рекурсивно и загрузить в приложение все, что нужно.
 */
public class ResourceFactory {
    private static ResourceFactory factory;// = new ResourceFactory();
    private String root;
    //private static final Resource resource;

    private ResourceFactory(String root) {
        this.root = root;
    }

    public ResourceFactory getInstance(String uri) {
        if (factory == null) {
            factory = new ResourceFactory(uri);
        }
        return factory;
    }

    public Resource get(String uri) {
        if (uri == null || uri.equals(""))
            return null;
        File file = new File(uri);
        if (isExist(uri)) {
            //log
            return null;
        }
        if (isDirectory(uri))
            return null;
        return new GMResource(uri);
    }

    public boolean isDirectory(String path) {
        return new File(root + path).isDirectory();
    }

    public boolean isExist(String path) {
        return new File(path).exists();
    }
}
