package school.students;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class AppConfig {

    @Bean
    public static AutoJsonRpcClientProxyCreator autoJsonRpcClientProxyCreator()
            throws MalformedURLException {
        AutoJsonRpcClientProxyCreator creator =
                new AutoJsonRpcClientProxyCreator();
        creator.setBaseUrl(new URL("http://localhost:8080/api/"));
        creator.setScanPackage("school.students.rpc");
        return creator;
    }
}
