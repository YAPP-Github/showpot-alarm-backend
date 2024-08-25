package org.showpot;

import org.showpot.config.FCMConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {FCMConfig.class})
public class FCMInfrastructureTestConfig {

}
