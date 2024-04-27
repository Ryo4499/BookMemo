package xyz.bookmemo.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;

/** The function creates a transaction manager for the data source */
@EnableJdbcHttpSession
public class JdbcSessionConfig {

  /**
   * > The function creates a transaction manager for the data source
   *
   * @param dataSource The data source to be used for the transaction manager.
   * @return A PlatformTransactionManager object.
   */
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
