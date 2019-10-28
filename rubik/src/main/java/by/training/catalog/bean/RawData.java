package by.training.catalog.bean;

import java.io.InputStream;

public class RawData {
    private static String rootPath;
    private InputStream stream;
    private String contentType;

    public static String getRootPath() {
        return rootPath;
    }

    public static void setRootPath(final String rootPathNew) {
        rootPath = rootPathNew;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(final InputStream streamNew) {
        stream = streamNew;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentTypeNew) {
        contentType = contentTypeNew;
    }

    @Override
    public String toString() {
        return "RawData{"
                + "stream=" + stream
                + ", contentType='" + contentType + "'}";
    }
}
