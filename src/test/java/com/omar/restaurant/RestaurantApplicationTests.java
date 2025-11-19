package com.omar.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.omar.restaurant.repositories.RestaurantRepository;

@SpringBootTest
class RestaurantApplicationTests {

	@MockBean
    private RestaurantRepository restaurantRepository;

	@Test
	void contextLoads() {
	}

}
