package edu.chl.hajo.td.util;

import java.net.URL;

/*
       Find URLs to Resources
       See: https://docs.oracle.com/javase/8/docs/technotes/guides/lang/resources.html

       NOTE: See out/production dir (the red one) for resulting location of files relative each other

        *** Nothing to do here ***
 */
public final class URLFactory {

    // Construct path to fileName **relative** objectAsking
    public static URL getRelativeURLFor(String fileName, Object objectAsking) {
        String relativePath = fileName;
        if (fileName.charAt(0) == '/') {       // Eliminate absolute path
            relativePath = fileName.substring(1);
        }
        return objectAsking.getClass().getResource(relativePath);
    }

    // Construct absolute path to fileName from top level directory
    public static <T> URL getAbsoluteURLFor(String fileName) {
        String absolutePath = fileName;
        if (fileName.charAt(0) != '/') {
            absolutePath = "/" + absolutePath;
        }
        return URLFactory.class.getResource(absolutePath);
    }

    private URLFactory() {
    }
}
