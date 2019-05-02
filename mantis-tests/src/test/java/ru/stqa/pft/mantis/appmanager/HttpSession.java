package ru.stqa.pft.mantis.appmanager;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build(); //создается новый клиент, объект, который будет отправлять запросы на сервер. Используется шаблон проектирования Билдер, вытягивание методов в цепочку. Если не запросим, то получим ответ 302.
    }

    public boolean login(String username,String password ) throws IOException { //метод выполняющий логин
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php"); //запрос типа пост, т.е. есть тело и внутри тела передаются параметры
        List<BasicNameValuePair> params = new ArrayList<>();  //формирование параметров
        params.add(new BasicNameValuePair("username",username));//формирование параметров
        params.add(new BasicNameValuePair("password",password));//формирование параметров
        params.add(new BasicNameValuePair("secure_session","on"));//формирование параметров
        params.add(new BasicNameValuePair("return","index.php"));//формирование параметров
        post.setEntity(new UrlEncodedFormEntity(params)); //параметры упаковываются и помещаются в заранее созданныйзапрос post.setEntity
        CloseableHttpResponse response = httpclient.execute(post); //происходит отправка в httpclient.execute(post), ответ получается response
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"italic\">%s</span>",username)); //проверяется действительно ли код прошел
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException{  //вспомогательная функция
        try {
            return EntityUtils.toString(response.getEntity());
        }
        finally { response.close(); }
    }

    public boolean isLoggedInAs(String username) throws IOException { // определяет, какой пользователь сейчас залогинин.
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");// узнать какой пользователь сейчас залогинился
        CloseableHttpResponse response = httpclient.execute(get);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"italic\">%s</span>", username));

    }

}