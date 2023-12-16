package com.solvd.configuration;

import java.io.IOException;
import java.util.logging.FileHandler;

public class CloseableHandler extends FileHandler implements AutoCloseable {
    public CloseableHandler(String pattern) throws IOException, SecurityException {
        super(pattern);
    }
}
