package io.github.demirmustafa.testcontainers.domain.repository;

import io.github.demirmustafa.testcontainers.TestcontainersSampleApplication;
import io.github.demirmustafa.testcontainers.domain.entity.User;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.MySQLContainer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = UserRepositoryIntegrationTest.Initializer.class)
@SpringBootTest(classes = TestcontainersSampleApplication.class)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;


    @ClassRule
    public static MySQLContainer mysql = (MySQLContainer) new MySQLContainer()
            .withDatabaseName("integration_test_with_test_containers")
            .withUsername("testUser")
            .withPassword("12345678")
            .withExposedPorts(3306);

    public static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + mysql.getJdbcUrl(),
                    "spring.datasource.username=" + mysql.getUsername(),
                    "spring.datasource.password=" + mysql.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    /**
     * {@link UserRepository#findAll()}
     */
    @Test
    public void findAll_method_should_return_all_users() {
        //given
        List<User> users = Arrays.asList(
                new User(null, "Mustafa", "Demir"),
                new User(null, "Ferhat", "Aykan")
        );
        userRepository.saveAll(users);

        //when
        List<User> all = userRepository.findAll();

        //then
        assertNotNull(all);
        assertEquals(2, all.size());
        assertEquals(Long.valueOf(1), users.get(0).getId());
        assertEquals("Mustafa", users.get(0).getName());
        assertEquals("Demir", users.get(0).getSurname());
        assertEquals(Long.valueOf(2), users.get(1).getId());
        assertEquals("Ferhat", users.get(1).getName());
        assertEquals("Aykan", users.get(1).getSurname());
    }
}
