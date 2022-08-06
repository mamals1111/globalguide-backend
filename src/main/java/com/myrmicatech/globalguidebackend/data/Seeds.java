package com.myrmicatech.globalguidebackend.data;

import com.myrmicatech.globalguidebackend.dto.AccountDto;
import com.myrmicatech.globalguidebackend.dto.ItemDto;
import com.myrmicatech.globalguidebackend.dto.LocationDto;
import com.myrmicatech.globalguidebackend.entity.*;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.category.CategoryService;
import com.myrmicatech.globalguidebackend.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Seeds implements CommandLineRunner {

//    @Autowired
//    private LocationService locationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {
        AccountDto accountDto = AccountDto.builder()
                .username("test123")
                .email("test@gmail.com")
                .password("123456sS")
                .confirmPassword("123456sS")
                .fullName("Test")
                .build();

        Account savedAccount = accountService.save(accountDto);

        accountDto = AccountDto.builder()
                .username("admin")
                .email("admin@gmail.com")
                .password("123456sS")
                .confirmPassword("123456sS")
                .fullName("ADMIN")
                .build();
        accountService.save(accountDto);

        accountDto = AccountDto.builder()
                .username("follow_account_1")
                .email("follow_account_1@gmail.com")
                .password("123456sS")
                .confirmPassword("123456sS")
                .fullName("Follow Account 1")
                .build();
        accountService.save(accountDto);

        accountDto = AccountDto.builder()
                .username("follow_account_2")
                .email("follow_account_2@gmail.com")
                .password("123456sS")
                .confirmPassword("123456sS")
                .fullName("Follow Account 2")
                .build();
        accountService.save(accountDto);

        accountDto = AccountDto.builder()
                .username("follow_account_3")
                .email("follow_account_3@gmail.com")
                .password("123456sS")
                .confirmPassword("123456sS")
                .fullName("Follow Account 3")
                .build();
        accountService.save(accountDto);

        accountDto = AccountDto.builder()
                .username("follow_account_4")
                .email("follow_account_4@gmail.com")
                .password("123456sS")
                .confirmPassword("123456sS")
                .fullName("Follow Account 4")
                .build();
        accountService.save(accountDto);

        accountDto = AccountDto.builder()
                .username("follow_account_5")
                .email("follow_account_5@gmail.com")
                .password("123456sS")
                .confirmPassword("123456sS")
                .fullName("Follow Account 5")
                .build();
        accountService.save(accountDto);

        List<Category> categories = new ArrayList<>();
        Category categoryRestaurant = Category.builder()
                .description("Restaurant")
                .name("Restaurant")
                .build();
        categories.add(categoryRestaurant);

        Category categoryItalianRestaurant = Category.builder()
                .description("Italian Restaurant")
                .name("Italian Restaurant")
                .build();
        categories.add(categoryItalianRestaurant);

        Category categoryHotel = Category.builder()
                .description("Hotel")
                .name("Hotel")
                .build();
        categories.add(categoryHotel);

        Category categoryUniversity = Category.builder()
                .description("University")
                .name("University")
                .build();
        categories.add(categoryUniversity);

        Category categoryHospital = Category.builder()
                .description("Hospital")
                .name("Hospital")
                .build();
        categories.add(categoryHospital);

        Category categoryCoffee = Category.builder()
                .description("Coffee")
                .name("Coffee")
                .build();
        categories.add(categoryCoffee);

        Category categoryGambles = Category.builder()
                .description("Gambles")
                .name("Gambles")
                .build();
        categories.add(categoryGambles);


        Category categoryBeersAndWine = Category.builder()
                .description("Beers & Wines")
                .name("Beers & Wines")
                .build();
        categories.add(categoryBeersAndWine);

        categoryService.saveAll(categories);

//        List<Location> locations = new ArrayList<>();
//        Location location = Location.builder()
//                .name("Trường Đại Học Kinh Tế Quốc Dân")
//                .description("Trường Đại Học Kinh Tế Quốc Dân")
//                .address("207 Giải Phóng, Đồng Tâm, Hai Bà Trưng, Hà Nội, Việt Nam")
//                .phoneNumber("02436280280")
//                .website("http://neu.edu.vn/")
//                .latitude(BigDecimal.valueOf(21.000436808777955))
//                .longitude(BigDecimal.valueOf(105.84271139665073))
//                .account(savedAccount)
//                .category(categoryUniversity)
//                .build();
//        locations.add(location);
//
//        location = Location.builder()
//                .name("Bệnh viện Bạch Mai")
//                .description("Bệnh viện Bạch Mai")
//                .address("78 Giải Phóng, Đồng Tâm, Hai Bà Trưng, Hà Nội, Việt Nam")
//                .phoneNumber("02438693731")
//                .website("http://bachmai.gov.vn/")
//                .latitude(BigDecimal.valueOf(21.000748439551266))
//                .longitude(BigDecimal.valueOf(105.84095134424678))
//                .account(savedAccount)
//                .category(categoryHospital)
//                .build();
//        locations.add(location);
//
//        location = Location.builder()
//                .name("Kí túc xá Đại học Kinh tế Quốc Dân")
//                .description("Kí túc xá Đại học Kinh tế Quốc Dân")
//                .address("P. Trần Đại Nghĩa, Đồng Tâm, Hai Bà Trưng, Hà Nội, Việt Nam")
//                .phoneNumber("02436280280")
//                .latitude(BigDecimal.valueOf(21.000011856780436))
//                .longitude(BigDecimal.valueOf(105.84671703312804))
//                .account(savedAccount)
//                .category(categoryUniversity)
//                .build();
//        locations.add(location);
//
//        location = Location.builder()
//                .name("Đại học Bách khoa Hà Nội")
//                .description("Đại học Bách khoa Hà Nội")
//                .address("1 Đại Cồ Việt, Bách Khoa, Hai Bà Trưng, Hà Nội, Việt Nam")
//                .phoneNumber("02436231732")
//                .latitude(BigDecimal.valueOf(21.007228068761364))
//                .longitude(BigDecimal.valueOf(105.84311941777955))
//                .account(savedAccount)
//                .category(categoryUniversity)
//                .build();
//        locations.add(location);
//        List<Location> locations = new ArrayList<>();
//
//        List<Category> locationCategories = new ArrayList<>();
//        locationCategories.add(categoryGambles);
//        locationCategories.add(categoryBeersAndWine);
//        Location location = Location.builder()
//                .name("Fondamenta Riva Casino")
//                .description("Fondamenta Riva Casino")
//                .address("5586U2 - 101\n" +
//                        "Los Angeles CA 4596")
//                .phoneNumber("02436280280")
//                .website("https://www.fondamentarivacasino.com/")
//                .latitude(BigDecimal.valueOf(21.000536808777955))
//                .longitude(BigDecimal.valueOf(105.84251139665073))
//                .account(savedAccount)
//                .categories(locationCategories)
//                .build();
//        locations.add(location);
//
//        locationCategories = new ArrayList<>();
//        locationCategories.add(categoryCoffee);
//        locationCategories.add(categoryBeersAndWine);
//        location = Location.builder()
//                .name("Bluebird Coffee")
//                .description("Bluebird Coffee")
//                .address("5586U2 - 101\n" +
//                        "Los Angeles CA 4596")
//                .phoneNumber("02436280280")
//                .website("https://www.bluebirdcofffee.com/")
//                .latitude(BigDecimal.valueOf(21.000636808777955))
//                .longitude(BigDecimal.valueOf(105.84281139665073))
//                .account(savedAccount)
//                .categories(locationCategories)
//                .build();
//        locations.add(location);
//
//        locationCategories = new ArrayList<>();
//        locationCategories.add(categoryItalianRestaurant);
//        location = Location.builder()
//                .name("Pizza Royal")
//                .description("Pizza Royal")
//                .address("5586U2 - 101\n" +
//                        "Los Angeles CA 4596")
//                .phoneNumber("02436280280")
//                .website("https://www.pizzaroyal.com/")
//                .latitude(BigDecimal.valueOf(21.000436808777955))
//                .longitude(BigDecimal.valueOf(105.84272139665073))
//                .account(savedAccount)
//                .categories(locationCategories)
//                .build();
//        locations.add(location);
//
//        locationCategories = new ArrayList<>();
//        locationCategories.add(categoryUniversity);
//        location = Location.builder()
//                .name("Trường Đại Học Kinh Tế Quốc Dân")
//                .description("Trường Đại Học Kinh Tế Quốc Dân")
//                .address("207 Giải Phóng, Đồng Tâm, Hai Bà Trưng, Hà Nội, Việt Nam")
//                .phoneNumber("02436280280")
//                .website("http://neu.edu.vn/")
//                .latitude(BigDecimal.valueOf(21.000436808777955))
//                .longitude(BigDecimal.valueOf(105.84271139665073))
//                .account(savedAccount)
//                .categories(locationCategories)
//                .build();
//        locations.add(location);
//
//        locationCategories = new ArrayList<>();
//        locationCategories.add(categoryHospital);
//        location = Location.builder()
//                .name("Bệnh viện Bạch Mai")
//                .description("Bệnh viện Bạch Mai")
//                .address("78 Giải Phóng, Đồng Tâm, Hai Bà Trưng, Hà Nội, Việt Nam")
//                .phoneNumber("02438693731")
//                .website("http://bachmai.gov.vn/")
//                .latitude(BigDecimal.valueOf(21.000748439551266))
//                .longitude(BigDecimal.valueOf(105.84095134424678))
//                .account(savedAccount)
//                .categories(locationCategories)
//                .build();
//        locations.add(location);
//
//        locationCategories = new ArrayList<>();
//        locationCategories.add(categoryUniversity);
//        location = Location.builder()
//                .name("Kí túc xá Đại học Kinh tế Quốc Dân")
//                .description("Kí túc xá Đại học Kinh tế Quốc Dân")
//                .address("P. Trần Đại Nghĩa, Đồng Tâm, Hai Bà Trưng, Hà Nội, Việt Nam")
//                .phoneNumber("02436280280")
//                .latitude(BigDecimal.valueOf(21.000011856780436))
//                .longitude(BigDecimal.valueOf(105.84671703312804))
//                .account(savedAccount)
//                .categories(locationCategories)
//                .build();
//        locations.add(location);
//
//        locationCategories = new ArrayList<>();
//        locationCategories.add(categoryUniversity);
//        location = Location.builder()
//                .name("Đại học Bách khoa Hà Nội")
//                .description("Đại học Bách khoa Hà Nội")
//                .address("1 Đại Cồ Việt, Bách Khoa, Hai Bà Trưng, Hà Nội, Việt Nam")
//                .phoneNumber("02436231732")
//                .latitude(BigDecimal.valueOf(21.007228068761364))
//                .longitude(BigDecimal.valueOf(105.84311941777955))
//                .account(savedAccount)
//                .categories(locationCategories)
//                .build();
//        locations.add(location);
//
//        locationService.saveAll(locations);

    }
}
