package com.example.domain;

import com.example.Application;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
* We use {@code @Transactional} with {@code @Commit} because we don't want to do rollback, as is usual in tests.
* This is because we want to guarantee that every test method is executed in a different transaction
* and no object is loaded from a cache.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Commit
public class ProductRepositoryIT {

    private static Product newProduct;
    private static ProductRepository staticRepository;

    @Inject
    private ProductRepository repository;

    @Before
    public void setStaticRepository() {
            staticRepository = repository;
    }

    @AfterClass
    public static void cleanUp() {
        staticRepository.delete(newProduct);
    }

    @Test
    public void o1_save_data() throws Exception {
        newProduct = repository.save(new Product("Product 1", "Category", 10));
    }

    @Test
    public void o2_load_item() throws Exception {
        final Product actual = repository.findByName("Product 1");

        assertThat(actual, is(not(nullValue())));
        assertThat(actual.getAmount(), is(10));
    }
}
