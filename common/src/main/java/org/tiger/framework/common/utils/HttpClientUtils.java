package org.tiger.framework.common.utils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class HttpClientUtils
{
    
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    
    private static HttpClient client = null;
    
    private static RequestConfig requestConfig = null;
    
    private static final String DEFAULT_CHARSET = "utf-8";
    
    static
    {
        requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(cm).build();
    }
    
    public static String post(String url, String body, String mimeType, String charset, Integer connTimeout,
        Integer readTimeout)
    {
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        String result = "";
        try
        {
            if (!StringUtils.isEmpty(body))
            {
                HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, charset));
                post.setEntity(entity);
            }
            post.setConfig(requestConfig);
            HttpResponse res;
            if (url.startsWith("https"))
            {
                client = createSSLInsecureClient();
                res = client.execute(post);
            }
            else
            {
                client = HttpClientUtils.client;
                res = client.execute(post);
            }
            result = EntityUtils.toString(res.getEntity());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            post.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient)
            {
                try
                {
                    ((CloseableHttpClient)client).close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers,
        Integer connTimeout, Integer readTimeout)
    {
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        try
        {
            if (params != null && !params.isEmpty())
            {
                List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet)
                {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
                post.setEntity(entity);
            }
            if (headers != null && !headers.isEmpty())
            {
                for (Entry<String, String> entry : headers.entrySet())
                {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            post.setConfig(requestConfig);
            HttpResponse res = null;
            if (url.startsWith("https"))
            {
                client = createSSLInsecureClient();
                res = client.execute(post);
            }
            else
            {
                client = HttpClientUtils.client;
                res = client.execute(post);
            }
            return EntityUtils.toString(res.getEntity());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            post.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient)
            {
                try
                {
                    ((CloseableHttpClient)client).close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers)
    {
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        try
        {
            if (params != null && !params.isEmpty())
            {
                List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet)
                {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
                post.setEntity(entity);
            }
            if (headers != null && !headers.isEmpty())
            {
                for (Entry<String, String> entry : headers.entrySet())
                {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            post.setConfig(requestConfig);
            HttpResponse res = null;
            if (url.startsWith("https"))
            {
                client = createSSLInsecureClient();
                res = client.execute(post);
            }
            else
            {
                client = HttpClientUtils.client;
                res = client.execute(post);
            }
            return EntityUtils.toString(res.getEntity());
        }
        catch (Exception e)
        {
            logger.error("webToBase error！" + e.getMessage());
        }
        finally
        {
            post.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient)
            {
                try
                {
                    ((CloseableHttpClient)client).close();
                }
                catch (IOException e)
                {
                    logger.error("webToBase error！" + e.getMessage());
                }
            }
        }
        return null;
    }
    
    /**
     * 发送json数据请求
     * 
     * @param url
     * @param jsonParams
     * @param headers
     * @return
     */
    public static String postForm(String url, String jsonParams, Map<String, String> headers)
    {
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        try
        {
            if (!StringUtils.isEmpty(jsonParams))
            {
                StringEntity s = new StringEntity(jsonParams, Consts.UTF_8);
                post.setEntity(s);
            }
            if (headers != null && !headers.isEmpty())
            {
                for (Entry<String, String> entry : headers.entrySet())
                {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            post.setConfig(requestConfig);
            HttpResponse res = null;
            if (url.startsWith("https"))
            {
                client = createSSLInsecureClient();
                res = client.execute(post);
            }
            else
            {
                client = HttpClientUtils.client;
                res = client.execute(post);
            }
            return EntityUtils.toString(res.getEntity());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            post.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient)
            {
                try
                {
                    ((CloseableHttpClient)client).close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    /**
     * 上传图片
     * 
     * @param url
     * @param params
     */
    @SuppressWarnings("deprecation")
    public static void postImage(String url, Map<String, String> params)
    {
        HttpClient httpclient = new DefaultHttpClient();
        // 请求处理页面
        HttpPost httppost = new HttpPost(url);
        try
        {
            /*
             * httppost.addHeader("charset", HTTP.UTF_8); httppost.setHeader("Content-Type",
             * "multipart/form-data; boundary=" + "--------YUjsdskjnimabi");
             */
            // 对请求的表单域进行填充
            MultipartEntity reqEntity = new MultipartEntity();
            FileBody bin = new FileBody(new File("f://aa.jpg"));
            // reqEntity.addPart("pic", bin);
            
            if (params != null && !params.isEmpty())
            {
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet)
                {
                    reqEntity.addPart(entry.getKey(), new StringBody(entry.getValue()));
                }
            }
            // 设置请求
            httppost.setEntity(reqEntity);
            // 执行
            HttpResponse response = httpclient.execute(httppost);
            // HttpEntity resEntity = response.getEntity();
            // System.out.println(response.getStatusLine());
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode())
            {
                HttpEntity entity = response.getEntity();
                // 显示内容
                if (entity != null)
                {
                    System.out.println(EntityUtils.toString(entity));
                }
                if (entity != null)
                {
                    entity.consumeContent();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                ((CloseableHttpClient)httpclient).close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            httppost.releaseConnection();
        }
    }
    
    /**
     * 发送Get请求
     * 
     * @param url
     * @param charset
     * @return
     */
    public static String get(String url, String charset)
    {
        if (StringUtils.isEmpty(charset))
        {
            charset = DEFAULT_CHARSET;
        }
        return get(url, charset, null, null);
    }
    
    public static String get(String url)
    {
        return get(url, DEFAULT_CHARSET, null, null);
    }
    
    public static String getImmediatelyRetuern(String url)
    {
        return getImmediatelyRetuern(url, DEFAULT_CHARSET, null, null);
    }
    
    public static String getImmediatelyRetuern(String url, String charset, Integer connTimeout, Integer readTimeout)
    {
        HttpClient client = null;
        HttpGet get = new HttpGet(url);
        String result = "";
        try
        {
            get.setConfig(requestConfig);
            client = HttpClientUtils.client;
            client.execute(get);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            get.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient)
            {
                try
                {
                    ((CloseableHttpClient)client).close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    /**
     * 发送Get请求
     * 
     * @param url
     * @param charset
     * @param connTimeout
     * @param readTimeout
     * @return
     */
    public static String get(String url, String charset, Integer connTimeout, Integer readTimeout)
    {
        HttpClient client = null;
        HttpGet get = new HttpGet(url);
        String result = "";
        try
        {
            get.setConfig(requestConfig);
            HttpResponse res = null;
            if (url.startsWith("https"))
            {
                client = createSSLInsecureClient();
                res = client.execute(get);
            }
            else
            {
                client = HttpClientUtils.client;
                res = client.execute(get);
            }
            result = EntityUtils.toString(res.getEntity());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            get.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient)
            {
                try
                {
                    ((CloseableHttpClient)client).close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    @SuppressWarnings("unused")
    private static String getCharsetFromResponse(HttpResponse ressponse)
    {
        if (ressponse.getEntity() != null && ressponse.getEntity().getContentType() != null
            && ressponse.getEntity().getContentType().getValue() != null)
        {
            String contentType = ressponse.getEntity().getContentType().getValue();
            if (contentType.contains("charset="))
            {
                return contentType.substring(contentType.indexOf("charset=") + 8);
            }
        }
        return null;
    }
    
    private static CloseableHttpClient createSSLInsecureClient()
        throws GeneralSecurityException
    {
        try
        {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
            {
                public boolean isTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException
                {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier()
            {
                @Override
                public boolean verify(String arg0, SSLSession arg1)
                {
                    return true;
                }
                
                @Override
                public void verify(String host, SSLSocket ssl)
                    throws IOException
                {
                }
                
                @Override
                public void verify(String host, X509Certificate cert)
                    throws SSLException
                {
                }
                
                @Override
                public void verify(String host, String[] cns, String[] subjectAlts)
                    throws SSLException
                {
                }
            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        }
        catch (GeneralSecurityException e)
        {
            throw e;
        }
    }
    
}
