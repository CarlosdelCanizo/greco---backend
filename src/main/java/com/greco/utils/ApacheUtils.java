package com.greco.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.io.ByteOrderMark;
import java.io.IOException;
import java.io.InputStream;

public final class ApacheUtils {

    private ApacheUtils() {
        // Prevent the class from being constructed
    }

    public static String getFileContents(InputStream is) throws IOException {
        // Some file encodings include BOM bytes at the beginning that Java String class does not exclude by itself
        BOMInputStream bomIn = new BOMInputStream(is, false, ByteOrderMark.UTF_8,
                ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE,
                ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_32BE);
        return IOUtils.toString(bomIn, bomIn.getBOMCharsetName());
    }
}
