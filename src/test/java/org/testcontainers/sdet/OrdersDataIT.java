package org.testcontainers.sdet;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testcontainers.sdet.OrderBuilder.anOrder;

@Testcontainers
@Tag("integration")
public class OrdersDataIT {

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.4")
                    .withDatabaseName("retail_test")
                    .withUsername("lahari")
                    .withPassword("lahari");

    static OrderRepository repository;
    static OrderFactory factory;

    @BeforeAll
    static void migrateSchema() {
        Flyway.configure()
                .dataSource(
                        mysql.getJdbcUrl(),
                        mysql.getUsername(),
                        mysql.getPassword()
                )
                .locations("classpath:db/migration")
                .load()
                .migrate();

        repository = new OrderRepository(
                mysql.getJdbcUrl(),
                mysql.getUsername(),
                mysql.getPassword()
        );

        factory = new OrderFactory(repository);
    }

    @BeforeEach
    void resetMutableTables() {
        repository.resetMutableTables();
    }

    @Test
    void flywaySeedsReferenceDataButNoPerTestOrders() {
        assertEquals(
                4,
                repository.referenceStatusCount(),
                "Reference statuses are seeded by migration"
        );

        assertEquals(
                0,
                repository.count(),
                "Per-test order rows should not come from migrations"
        );
    }

    @Test
    void factoryPersistsBuilderDataAgainstIsolatedMySql() {
        long id = factory.persisted(
                anOrder()
                        .withQuantity(3)
        );

        assertTrue(id > 0);
        assertEquals(1, repository.count());
    }

    @Test
    void countsOnlyThisTestsOrders() {
        factory.persisted(anOrder());

        factory.persisted(
                anOrder()
                        .withSku("SKU-RET-202")
                        .withQuantity(2)
        );

        assertEquals(2, repository.count());
    }

    @Test
    void resetMakesTestsOrderIndependent() {
        assertEquals(
                0,
                repository.count(),
                "Previous tests must not leak rows into this test"
        );

        factory.persisted(
                anOrder()
                        .refunded()
        );

        assertEquals(1, repository.count());
        assertEquals(1, repository.countByStatus("REFUNDED"));
    }
}
