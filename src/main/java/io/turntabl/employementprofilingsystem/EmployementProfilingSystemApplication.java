package io.turntabl.employementprofilingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.io.IOException;
import java.security.GeneralSecurityException;
import io.jaegertracing.Configuration;
import org.springframework.context.annotation.Bean;

@ComponentScan(basePackages = "io.turntabl.*")
@EnableSwagger2
@EnableAutoConfiguration
@SpringBootApplication
public class EmployementProfilingSystemApplication {

	public static void main(String[] args) throws GeneralSecurityException, IOException {

		SpringApplication.run(EmployementProfilingSystemApplication.class, args);

	}

	@Bean
	public io.opentracing.Tracer initTracer() {
		Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration().fromEnv();
		Configuration.ReporterConfiguration reporterConfig = new Configuration.ReporterConfiguration().fromEnv();
		return new Configuration("EmployeeProfilingService").withSampler(samplerConfig).withReporter(reporterConfig).getTracer();
	}

}
