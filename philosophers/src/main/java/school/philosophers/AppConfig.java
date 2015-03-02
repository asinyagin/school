package school.philosophers;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public static AutoJsonRpcServiceExporter autoJsonRpcServiceExporter() {
        return new AutoJsonRpcServiceExporter();
    }
}
