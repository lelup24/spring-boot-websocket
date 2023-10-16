package de.kipper.websocket.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class ClockConfig {

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Europe/Berlin");
    public static final Clock DEFAULT_CLOCK = Clock.system(DEFAULT_ZONE_ID);

    @Bean
    public Clock clock() {
        return DEFAULT_CLOCK;
    }
}
