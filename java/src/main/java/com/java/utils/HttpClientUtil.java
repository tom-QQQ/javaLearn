/**
 * 版权所有(C) 2015 深圳市雁联计算系统有限公司
 * 创建:YangHan 2015-1-8
 */
package com.java.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http工具类
 */
public class HttpClientUtil {
	private final static String CONTENT_CHARSET = "UTF-8";
	private final static int SO_TIMEOUT = 100000;


//	/**
//	 * GET方式下载文件
//	 * @param url 下载地址
//	 * @param data 参数
//	 * @param filePath 保存路径
//	 * @throws Exception
//     */
//	public static void downloadGet(String url,Map<String,String> data,String filePath) throws Exception{
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		OutputStream out = null;
//		InputStream in = null;
//		try{
//			url = url.replaceFirst("^http://|^http://", "");
//			URIBuilder uriBuilder = new URIBuilder().setScheme("http").setHost(url);
//			if(!CollectionUtils.isEmpty(data)){
//				for (String key : data.keySet()) {
//					uriBuilder.setParameter(key, data.get(key));
//				}
//			}
//			HttpGet httpGet = new HttpGet(uriBuilder.build());
//			HttpResponse response = httpClient.execute(httpGet);
//			in = response.getEntity().getContent();
//			File outFile = new File(filePath);
//			if(!outFile.exists())
//				outFile.createNewFile();
//			out = new FileOutputStream(outFile);
//			IOUtils.copy(in,out);
//		}finally{
//			if(null != httpClient){
//				httpClient.close();
//			}
//			IOUtils.closeQuietly(in);
//			IOUtils.closeQuietly(out);
//		}
//	}
	/**
	 * GET方式调用HTTP
	 * @param url
	 * @throws Exception
	 */
	public static String sendGet(String url,Map<String, String> data) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
			url = url.replaceFirst("^http://","");
			URIBuilder uriBuilder = new URIBuilder().setScheme("http").setHost(url);
			if(!CollectionUtils.isEmpty(data)){
				for (String key : data.keySet()) {
					uriBuilder.setParameter(key, data.get(key));
				}
			}
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			HttpResponse response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return EntityUtils.toString(response.getEntity(),CONTENT_CHARSET);
			}else{
				throw new RuntimeException("调用URL地址通讯失败,失败状态：" + response.getStatusLine().getStatusCode());
			}
		}finally{
			if(null != httpClient){
				httpClient.close();
			}
		}
	}
	public static String sendGet(String url) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
			url = url.replaceFirst("^http://", "");
			URIBuilder uriBuilder = new URIBuilder().setScheme("http").setHost(url);
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			HttpResponse response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return EntityUtils.toString(response.getEntity(),CONTENT_CHARSET);
			}else{
				throw new RuntimeException("调用URL地址通讯失败,失败状态：" + response.getStatusLine().getStatusCode());
			}
		}catch (Exception e){
			System.out.println("调用URL地址通讯失败");
			throw new RuntimeException("调用URL地址通讯失败,失败状态：" +e.getMessage());
		}finally{
			if(null != httpClient){
				httpClient.close();
			}
		}
	}

	/**
	 * POST方式调用HTTP
	 * @param url
	 * @param data
	 * @throws Exception
	 */
	public static String sendPost(String url,Map<String, String> data) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
			HttpPost post = new HttpPost(url);
			if(!CollectionUtils.isEmpty(data)){
				List<NameValuePair> params = new ArrayList<>();
				for (String key : data.keySet()) {
					params.add(new BasicNameValuePair(key, data.get(key)));
				}
				HttpEntity fromEntity = new UrlEncodedFormEntity(params,CONTENT_CHARSET);
				post.setEntity(fromEntity);
			}
			HttpResponse response = httpClient.execute(post);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return EntityUtils.toString(response.getEntity(),CONTENT_CHARSET);
			}else{
				throw new RuntimeException("调用URL地址通讯失败,失败状态：" + response.getStatusLine().getStatusCode());
			}
		}finally{
			if(null != httpClient){
				httpClient.close();
			}
		}
	}

    /**
     * POST方式调用HTTP
     * @param url
     * @param data
     * @throws Exception
     */
    public static String sendPost(String url,Map<String, Object> data,String charset) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpPost post = new HttpPost(url);
            if(!CollectionUtils.isEmpty(data)){
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (String key : data.keySet()) {
                    params.add(new BasicNameValuePair(key, (String) data.get(key)));
                }
                HttpEntity fromEntity = new UrlEncodedFormEntity(params, StringUtils.isNotBlank(charset)?charset:CONTENT_CHARSET);
                post.setEntity(fromEntity);
            }
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                return EntityUtils.toString(response.getEntity(),CONTENT_CHARSET);
            }else{
                throw new RuntimeException("调用URL地址通讯失败,失败状态：" + response.getStatusLine().getStatusCode());
            }
        }finally{
            if(null != httpClient){
                httpClient.close();
            }
        }
    }

	/**
	 * GET方式调用HTTPS
	 * @param url 网址
	 * @param data 参数
	 * @throws Exception
	 */
	public static String sendHttpsGet(String url,Map<String,String> data) throws Exception{
		CloseableHttpClient sslClient = createSSLClient();
		try{
			url = url.replaceFirst("^https://", "");
			URIBuilder uriBuilder = new URIBuilder().setScheme("https").setHost(url);
			if(!CollectionUtils.isEmpty(data)){
				for (String key : data.keySet()) {
					uriBuilder.setParameter(key, data.get(key));
				}
			}
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			HttpResponse response = sslClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return EntityUtils.toString(response.getEntity(),CONTENT_CHARSET);
			}else{
				throw new RuntimeException("调用URL地址通讯失败,失败状态：" + response.getStatusLine().getStatusCode());
			}
		}finally {
			sslClient.close();
		}
	}

	/**
	 * POST方式调用HTTPS
	 * @param url
	 * @param data
	 * @return
	 */
	public static String sendHttpsPost(String url,Map<String, String> data) throws Exception {
		CloseableHttpClient sslClient = createSSLClient();
		try {
			HttpPost post = new HttpPost(url);
			if(!CollectionUtils.isEmpty(data)){
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String key : data.keySet()) {
					params.add(new BasicNameValuePair(key, data.get(key)));
				}
				HttpEntity fromEntity = new UrlEncodedFormEntity(params,CONTENT_CHARSET);
				post.setEntity(fromEntity);
			}
			HttpResponse response = sslClient.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), CONTENT_CHARSET);
			} else {
				throw new RuntimeException("调用URL地址通讯失败,失败状态：" + response.getStatusLine().getStatusCode());
			}
		} finally {
			sslClient.close();
		}
	}
	/**
	 * POST方式调用HTTPS
	 * @param url
	 * @param data
	 * @return
	 */
	public static String sendHttpsPost(String url,Map<String, String> data,String enconding) throws Exception {
		CloseableHttpClient sslClient = createSSLClient();
		try {
			HttpPost post = new HttpPost(url);
			if(!CollectionUtils.isEmpty(data)){
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String key : data.keySet()) {
					params.add(new BasicNameValuePair(key, data.get(key)));
				}
				HttpEntity fromEntity = null;
				if(StringUtils.isNotBlank(enconding)){
					fromEntity = new UrlEncodedFormEntity(params,enconding);
				}else{
					fromEntity = new UrlEncodedFormEntity(params,CONTENT_CHARSET);
				}

				post.setEntity(fromEntity);
			}
			HttpResponse response = sslClient.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), CONTENT_CHARSET);
			} else {
				throw new RuntimeException("调用URL地址通讯失败,失败状态：" + response.getStatusLine().getStatusCode());
			}
		} finally {
			sslClient.close();
		}
	}
	public static String sendStringHttpsPost(String url,String param) throws Exception{
		CloseableHttpClient sslClient = createSSLClient();
		try {
			HttpPost post = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(param,CONTENT_CHARSET);
			post.setEntity(stringEntity);
			HttpResponse response = sslClient.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), CONTENT_CHARSET);
			} else {
				throw new RuntimeException("调用URL地址通讯失败,失败状态：" + response.getStatusLine().getStatusCode());
			}
		} finally {
			sslClient.close();
		}
	}
	public static CloseableHttpClient createSSLClient(){
		ConnectionConfig connConfig = ConnectionConfig.custom().build();
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(SO_TIMEOUT).build();
		try {
			RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
			ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
			registryBuilder.register("http", plainSF);
			//指定信任密钥存储对象和连接套接字工厂
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
			registryBuilder.register("https", sslSF);
			Registry<ConnectionSocketFactory> registry = registryBuilder.build();
			//设置连接管理器
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
			connManager.setDefaultConnectionConfig(connConfig);
			connManager.setDefaultSocketConfig(socketConfig);
			//构建客户端
			return HttpClientBuilder.create().setConnectionManager(connManager).build();
		}catch (Exception e){
			System.out.println("创建SSLClient失败");
			throw new RuntimeException("创建SSLClient失败,错误信息:" + e.getMessage());
		}
	}
}
