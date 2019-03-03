package com.sango.microservices.students.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = { "com.sango.microservices.students" })
@Profile({"local","dev"})
public class CassandraConfig extends AbstractCassandraConfiguration{

  private static final Logger log = LoggerFactory.getLogger(CassandraConfig.class);

  @Value("${spring.data.cassandra.keyspace-name}")
  private String keyspace;
  
  @Value("${spring.data.cassandra.port}")
  private String port;
  
  @Value("${spring.data.cassandra.contact-points}")
  private String contactPoints;

  @Bean
  public CassandraClusterFactoryBean cluster() {
    CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
    cluster.setContactPoints(contactPoints);
    cluster.setPort(Integer.parseInt(port));
    return cluster;
  }

  @Bean
  public CassandraMappingContext mappingContext() {
    return new BasicCassandraMappingContext();
  }

  @Bean
  public CassandraConverter converter() {
    return new MappingCassandraConverter(mappingContext());
  }


  @Bean
  public CassandraSessionFactoryBean session() {
    CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
    session.setCluster(cluster().getObject());
    session.setKeyspaceName(keyspace);
    session.setConverter(converter());
    session.setSchemaAction(SchemaAction.NONE);
    return session;
  }

  @Override
  protected String getKeyspaceName() {
	return keyspace;
  }

}
