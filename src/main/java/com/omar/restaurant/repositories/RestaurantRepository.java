
package com.omar.restaurant.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.omar.restaurant.domain.entities.Restaurant;


@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {

    //Customer queries


    

}

