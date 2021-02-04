package Network;

import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.protocol.HttpContext;

import java.net.URI;

public class HttpTest {
    public static void main (String args[]) throws Exception {
        new HttpTest().go();
    }
    public void go() throws Exception {
        BasicCookieStore basicCookieStore = new BasicCookieStore();
        BasicClientCookie authCookie = new BasicClientCookie("MyCookie", "Something");
        authCookie.setVersion(0);
        authCookie.setDomain(".github.com");
        authCookie.setAttribute(ClientCookie.DOMAIN_ATTR, "true");
        authCookie.setPath("/");
        basicCookieStore.addCookie(authCookie);
        CloseableHttpAsyncClient asyncClient = HttpAsyncClients.custom().setDefaultCookieStore(basicCookieStore).build();
        asyncClient.start();
        RequestBuilder requestBuilder = RequestBuilder
                .get(new URI("http://www.github.com"));
        HttpContext context = HttpClientContext.create();
        asyncClient.execute(requestBuilder.build(), context,null)
                .get();
        System.out.println("\n\n\nBah-ha\n\n\n\n\n\n\n\n\n");
        BasicClientCookie authCookie2 = new BasicClientCookie("MyCookie", "Something Else");
        authCookie2.setVersion(0);
        authCookie2.setDomain(".github.com");
        authCookie2.setAttribute(ClientCookie.DOMAIN_ATTR, "true");
        authCookie2.setPath("/");
        HttpClientContext.adapt(context).getCookieStore().addCookie(authCookie2);
        asyncClient.execute(requestBuilder.build(), context,null)
                .get();
        asyncClient.close();
    }
}

//compile group: 'org.apache.httpcomponents', name: 'httpasyncclient', version: '4.1.3'
