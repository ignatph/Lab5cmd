package interfaces;

import body.*;

import java.io.IOException;


/**
 * Contains method for reading file
 * Read and loads data from file
 */
public interface BaseReader {
    Worker[] read(String path) throws IOException;
}

