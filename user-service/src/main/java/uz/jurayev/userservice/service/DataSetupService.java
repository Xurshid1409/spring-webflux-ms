package uz.jurayev.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import java.nio.charset.StandardCharsets;

//@Service
public class DataSetupService implements CommandLineRunner {

    @Value("classpath:init.sql")
    private Resource initsql;

    private final R2dbcEntityTemplate template;

    @Autowired
    public DataSetupService(R2dbcEntityTemplate template) {
        this.template = template;
    }

    @Override
    public void run(String... args) throws Exception {
        String s = StreamUtils.copyToString(initsql.getInputStream(), StandardCharsets.UTF_8);
        template.getDatabaseClient()
                .sql(s)
                .then()
                .subscribe();
    }
}
