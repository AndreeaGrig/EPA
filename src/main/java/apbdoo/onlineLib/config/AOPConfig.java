package apbdoo.onlineLib.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author alexnutu
 * @since 3/25/2020
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "apbdoo.onlineLib")
public class AOPConfig {

}
