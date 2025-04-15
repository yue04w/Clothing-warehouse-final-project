package com.example.clotheswarehouse;

import com.example.clotheswarehouse.enums.Brand;
import com.example.clotheswarehouse.model.DistributionCentre;
import com.example.clotheswarehouse.model.DistributionCentreItem;
import com.example.clotheswarehouse.model.Item;
import com.example.clotheswarehouse.model.User;
import com.example.clotheswarehouse.repository.DistributionCentreItemRepository;
import com.example.clotheswarehouse.repository.DistributionCentreRepository;
import com.example.clotheswarehouse.repository.ItemRepository;
import com.example.clotheswarehouse.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Assignment4Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment4Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ItemRepository itemRepository, UserRepository userRepository,
                                   PasswordEncoder passwordEncoder, DistributionCentreRepository dcRepository,
                                   DistributionCentreItemRepository dciRepository) {
        return args -> {
            // Items for warehouse
            Item item1 = new Item();
            item1.setName("Sneakers");
            item1.setBrand(Brand.BALENCIAGA);
            item1.setYearOfCreation(2022);
            item1.setPrice(1200.50);
            item1.setQuantity(10);

            Item item2 = new Item();
            item2.setName("Jacket");
            item2.setBrand(Brand.DIOR);
            item2.setYearOfCreation(2022);
            item2.setPrice(2500.00);
            item2.setQuantity(5);

            Item item3 = new Item();
            item3.setName("Pant");
            item3.setBrand(Brand.STONE_ISLAND);
            item3.setYearOfCreation(2023);
            item3.setPrice(1800.75);
            item3.setQuantity(8);

            Item item4 = new Item();
            item4.setName("Shirt");
            item4.setBrand(Brand.GUCCI);
            item4.setYearOfCreation(2022);
            item4.setPrice(1500.25);
            item4.setQuantity(12);

            Item item5 = new Item();
            item5.setName("Hat");
            item5.setBrand(Brand.PRADA);
            item5.setYearOfCreation(2024);
            item5.setPrice(1100.00);
            item5.setQuantity(3);
            
            Item item6 = new Item();
            item6.setName("Beanie");
            item6.setBrand(Brand.GUCCI);
            item6.setYearOfCreation(2024);
            item6.setPrice(6000.00);
            item6.setQuantity(10);
            itemRepository.saveAll(List.of(item1, item2, item3, item4, item5, item6));

            // Distribution Centres
            DistributionCentre dc1 = new DistributionCentre();
            dc1.setName("New York");
            dc1.setLatitude(40.7128);
            dc1.setLongitude(-74.0060);

            DistributionCentre dc2 = new DistributionCentre();
            dc2.setName("London");
            dc2.setLatitude(51.5074);
            dc2.setLongitude(-0.1278);

            DistributionCentre dc3 = new DistributionCentre();
            dc3.setName("Tokyo");
            dc3.setLatitude(35.6762);
            dc3.setLongitude(139.6503);

            DistributionCentre dc4 = new DistributionCentre();
            dc4.setName("Sydney");
            dc4.setLatitude(-33.8688);
            dc4.setLongitude(151.2093);
            
            
            DistributionCentre dc5 = new DistributionCentre();
            dc5.setName("Toronto");
            dc5.setLatitude(43.6532);
            dc5.setLongitude(79.3832);
            dcRepository.saveAll(List.of(dc1, dc2, dc3, dc4, dc5));

            // Distribution Centre Items
            DistributionCentreItem dci1 = new DistributionCentreItem();
            dci1.setDistributionCentre(dc1);
            dci1.setItem(item1);
            dci1.setQuantity(20);

            DistributionCentreItem dci2 = new DistributionCentreItem();
            dci2.setDistributionCentre(dc1);
            dci2.setItem(item2);
            dci2.setQuantity(15);

            DistributionCentreItem dci3 = new DistributionCentreItem();
            dci3.setDistributionCentre(dc2);
            dci3.setItem(item2);
            dci3.setQuantity(10);

            DistributionCentreItem dci4 = new DistributionCentreItem();
            dci4.setDistributionCentre(dc2);
            dci4.setItem(item3);
            dci4.setQuantity(12);

            DistributionCentreItem dci5 = new DistributionCentreItem();
            dci5.setDistributionCentre(dc3);
            dci5.setItem(item3);
            dci5.setQuantity(8);

            DistributionCentreItem dci6 = new DistributionCentreItem();
            dci6.setDistributionCentre(dc3);
            dci6.setItem(item4);
            dci6.setQuantity(25);

            DistributionCentreItem dci7 = new DistributionCentreItem();
            dci7.setDistributionCentre(dc4);
            dci7.setItem(item4);
            dci7.setQuantity(18);

            DistributionCentreItem dci8 = new DistributionCentreItem();
            dci8.setDistributionCentre(dc4);
            dci8.setItem(item5);
            dci8.setQuantity(5);
            
            DistributionCentreItem dci9 = new DistributionCentreItem();
            dci9.setDistributionCentre(dc5);
            dci9.setItem(item6);
            dci9.setQuantity(20);
            
            dciRepository.saveAll(List.of(dci1, dci2, dci3, dci4, dci5, dci6, dci7, dci8, dci9));

            //Here we have created some sample users as well.
            if (userRepository.findByUsername("admin") == null) {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRole("ADMIN");
                user.setEnabled(true);
                userRepository.save(user);
            }

            if (userRepository.findByUsername("employee") == null) {
                User user = new User();
                user.setUsername("employee");
                user.setPassword(passwordEncoder.encode("employee"));
                user.setRole("EMPLOYEE");
                user.setEnabled(true);
                userRepository.save(user);
            }

            if (userRepository.findByUsername("user") == null) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user"));
                user.setRole("USER");
                user.setEnabled(true);
                userRepository.save(user);
            }
        };
    }
}
