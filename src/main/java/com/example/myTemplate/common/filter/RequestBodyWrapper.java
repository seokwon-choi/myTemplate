package com.example.myTemplate.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RequestBodyWrapper extends HttpServletRequestWrapper {
    private final String requestBody;

    public RequestBodyWrapper(HttpServletRequest request) throws IOException {
        super(request);

        requestBody = requestDataByte(request);

    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    private String requestDataByte(HttpServletRequest request) throws IOException {
        byte[] rawData;
        InputStream inputStream = request.getInputStream();
        rawData = IOUtils.toByteArray(inputStream);
        return new String(rawData);
    }

    public String getRequestBody() {
        return requestBody;
    }

}
