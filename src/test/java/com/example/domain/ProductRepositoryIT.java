package com.example.domain;

import com.example.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProductRepositoryIT {

    @Inject
    private ProductRepository repository;

//    @Test
//    public void o1_save_data() throws Exception {
//        repository.save(new Product("Product 1", 10));
//        repository.save(new Product("Product 2", 20));
//        repository.save(new Product("Product 3", 30));
//    }

    @Test
    public void load_item() throws Exception {
        final Product actual = repository.findByName("Product 1");

        assertThat(actual, is(not(nullValue())));
        assertThat(actual.getAmount(), is(10));
    }
}
