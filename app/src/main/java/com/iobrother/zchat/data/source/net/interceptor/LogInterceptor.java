package com.iobrother.zchat.data.source.net.interceptor;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import retrofit2.Invocation;
import retrofit2.http.Streaming;

public class LogInterceptor implements Interceptor {
    private static final String multipartType = "multipart";
    private final static Charset UTF_8 = Charset.forName("UTF-8");
    private static final String TAG = "TEST";
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Log.i(TAG, "method="+request.method()+ " url="+request.url());

        if(request.headers() != null && request.headers().size() > 0){
//            Timber.d("Headers:" + request.headers());
        }

        RequestBody requestBody = request.body();
        if(requestBody != null){
            MediaType mediaType = requestBody.contentType();
//            Timber.d("RequestContentType:" + mediaType);
            if(mediaType != null && multipartType.equalsIgnoreCase(mediaType.type())){
//                Timber.d("RequestBody:(form data " + requestBody.contentLength() + "-byte body omitted)");
            }else{
                final Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                if(isPlaintext(buffer)){
                    Log.d(TAG, "RequestBody:" + buffer.readString(getCharset(requestBody,UTF_8)));
                }else{
                    Log.d(TAG, "RequestBody:(binary " + requestBody.contentLength() + "-byte body omitted)");
                }
            }
        }

        Invocation invocation = request.tag(Invocation.class);
        if(invocation != null){
            Streaming streaming = invocation.method().getAnnotation(Streaming.class);
            if(streaming != null){
                Log.d(TAG,"Streaming...");
                return chain.proceed(chain.request());
            }
        }

        Response response = chain.proceed(chain.request());
        ResponseBody responseBody = response.body();
        if(responseBody != null){
            MediaType mediaType = responseBody.contentType();
            Log.d(TAG, "ResponseContentType:" + mediaType);
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.getBuffer();
            if ("gzip".equalsIgnoreCase(response.headers().get("Content-Encoding"))) {
                try (GzipSource gzippedResponseBody = new GzipSource(buffer.clone())) {
                    buffer = new Buffer();
                    buffer.writeAll(gzippedResponseBody);
                }
            }

            if(mediaType != null && multipartType.equalsIgnoreCase(mediaType.type())){
                Log.d(TAG, "ResponseBody:(form data " + buffer.size() + "-byte body omitted)");
            }else{
                if(isPlaintext(buffer)){
                    Log.d(TAG, "ResponseBody:" + buffer.clone().readString(getCharset(responseBody,UTF_8)));
                }else{
                    Log.d(TAG, "ResponseBody:(binary " + buffer.size() + "-byte body omitted)");
                }
            }
        }
        return response;
    }

    private Charset getCharset(RequestBody requestBody,@NonNull Charset defaultCharset){
        return requestBody.contentType() != null ? requestBody.contentType().charset(defaultCharset) : defaultCharset;
    }

    private Charset getCharset(ResponseBody responseBody,@NonNull Charset defaultCharset){
        return responseBody.contentType() != null ? responseBody.contentType().charset(defaultCharset) : defaultCharset;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     *
     * @see <a href="https://github.com/square/okhttp">okhttp-logging-interceptor</a>
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            Log.w(TAG, e);
            return false; // Truncated UTF-8 sequence.
        }
    }
}
