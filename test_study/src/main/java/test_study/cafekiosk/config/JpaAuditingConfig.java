package test_study.cafekiosk.config;

import ch.qos.logback.classic.spi.ConfiguratorRank;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
