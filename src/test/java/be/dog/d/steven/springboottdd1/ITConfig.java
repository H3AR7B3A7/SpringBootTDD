package be.dog.d.steven.springboottdd1;

import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.CompositeDatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class ITConfig {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("integrationTestData.sql")));

        populator.populate(dataSource.getConnection());
    }
}
