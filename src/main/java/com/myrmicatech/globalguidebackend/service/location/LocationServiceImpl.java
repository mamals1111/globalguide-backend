package com.myrmicatech.globalguidebackend.service.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myrmicatech.globalguidebackend.dto.LocationAreaDto;
import com.myrmicatech.globalguidebackend.dto.LocationCoordinateDto;
import com.myrmicatech.globalguidebackend.dto.LocationDto;
import com.myrmicatech.globalguidebackend.dto.LocationRadiusDto;
import com.myrmicatech.globalguidebackend.entity.*;
import com.myrmicatech.globalguidebackend.entity.ids.AccountLocationId;
import com.myrmicatech.globalguidebackend.enums.LocationLogType;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.repository.LocationLogRepository;
import com.myrmicatech.globalguidebackend.repository.LocationRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.category.CategoryService;
import com.myrmicatech.globalguidebackend.service.file.FileStorageService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import com.myrmicatech.globalguidebackend.service.time.TimeService;
import com.myrmicatech.globalguidebackend.util.FileHandler;
import com.myrmicatech.globalguidebackend.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Location> implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationLogRepository locationLogRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileHandler fileHandler;

    public LocationServiceImpl(LocationRepository locationRepository) {
        super(locationRepository);
    }

    @Override
    public List<Location> findLocation() {
        return null;
    }

    @Override
    public Location save(LocationDto locationDto, List<MultipartFile> bannerFiles, MultipartFile avatarFile) throws Exception {
        Location location = mapStructMapper.locationDtoToLocation(locationDto);
        List<Location> foundLocations = locationRepository.findAllByLatitudeEqualsAndLongitudeEquals(location.getLatitude(), location.getLongitude());
        if (!foundLocations.isEmpty()) {
            throw new Exception("Location is already added!");
        }
        Account account = accountService.checkAccount();

        List<UUID> categoryIds = locationDto.getCategoryIds();
        List<Category> categories = categoryService.findAllByCategoryIds(categoryIds);

       
//        List<File> bannerFilesToSave = new ArrayList<>();
//        for (MultipartFile bannerFile:
//             bannerFiles) {
//            String fileName = fileStorageService.storeFile(bannerFile);
//            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads/downloadFile/")
//                    .path(fileName).toUriString();
//            File file = File.builder()
//                    .fileName(fileName)
//                    .fileDownloadUri(fileDownloadUri)
//                    .fileType(bannerFile.getContentType())
//                    .size(bannerFile.getSize())
//                    .account(account)
//                    .locationBanner(location)
//                    .build();
//            bannerFilesToSave.add(file);
////            File file = mapStructMapper.uploadFileDtoToFile(uploadFileResponse);
//        }

       
//        String avatarFileName = fileStorageService.storeFile(avatarFile);
//        String avatarDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads/downloadFile/")
//                .path(avatarFileName).toUriString();
//        File avatarFileToSave = File.builder()
//                .fileName(avatarFileName)
//                .fileDownloadUri(avatarDownloadUri)
//                .fileType(avatarFile.getContentType())
//                .size(avatarFile.getSize())
//                .account(account)
//                .locationAvatar(location)
//                .build();

        List<File> bannerFilesToSave = handleBannerFiles(location, account, bannerFiles);
        File avatarFileToSave = handleAvatarFile(location, account, avatarFile);

        location.setAvatarFile(avatarFileToSave);
        location.setBannerFiles(bannerFilesToSave);
        location.setCategories(categories);

       
        List<Time> times = new ArrayList<>();
        Time time = Time.builder()
                .weekDay("Monday")
                .openTime(LocalTime.parse("08:00"))
                .closeTime(LocalTime.parse("21:00"))
                .location(location)
                .build();
        times.add(time);

        time = Time.builder()
                .weekDay("Tuesday")
                .openTime(LocalTime.parse("08:00"))
                .closeTime(LocalTime.parse("21:00"))
                .location(location)
                .build();
        times.add(time);

        time = Time.builder()
                .weekDay("Wednesday")
                .openTime(LocalTime.parse("08:00"))
                .closeTime(LocalTime.parse("21:00"))
                .location(location)
                .build();
        times.add(time);

        time = Time.builder()
                .weekDay("Thursday")
                .openTime(LocalTime.parse("08:00"))
                .closeTime(LocalTime.parse("21:00"))
                .location(location)
                .build();
        times.add(time);

        time = Time.builder()
                .weekDay("Friday")
                .openTime(LocalTime.parse("08:00"))
                .closeTime(LocalTime.parse("21:00"))
                .location(location)
                .build();
        times.add(time);

        time = Time.builder()
                .weekDay("Saturday")
                .openTime(LocalTime.parse("08:00"))
                .closeTime(LocalTime.parse("21:00"))
                .location(location)
                .build();
        times.add(time);

        time = Time.builder()
                .weekDay("Sunday")
                .openTime(LocalTime.parse("08:00"))
                .closeTime(LocalTime.parse("21:00"))
                .location(location)
                .build();
        times.add(time);
        location.setTimes(times);

       
        Location savedLocation = locationRepository.save(location);

       
        AccountLocationId accountLocationId = AccountLocationId.builder()
                .locationId(location.getId())
                .userId(account.getId())
                .build();

        LocationLog locationLog = LocationLog.builder()
                .locationLogType(LocationLogType.INSERT)
                .name(location.getName())
                .description(location.getDescription())
                .address(location.getAddress())
                .website(location.getWebsite())
                .phoneNumber(location.getPhoneNumber())
                .accountLocationId(accountLocationId)
                .location(savedLocation)
                .account(account)
                .build();
        locationLogRepository.save(locationLog);

        return savedLocation;
    }

    @Override
    public Location update(UUID locationId, LocationDto locationDto, List<MultipartFile> bannerFiles, MultipartFile avatarFile) throws Exception {
        Account account = accountService.checkAccount();
        BigDecimal latitude = locationDto.getLatitude();
        BigDecimal longitude = locationDto.getLongitude();
        List<Location> locations = locationRepository.findAllByLatitudeEqualsAndLongitudeEquals(latitude, longitude);
        if (locations.isEmpty()) {
            throw new Exception("Cannot find location!");
        }
        Location location = locations.get(0);
        mapStructMapper.update(location, locationDto);

       
        List<File> bannerFilesToSave = handleBannerFiles(location, account, bannerFiles);
        File avatarFileToSave = handleAvatarFile(location, account, avatarFile);
        List<File> filesInDatabase = fileStorageService.findAllByLocationBanner(location);
        List<File> filteredBannerFiles = bannerFilesToSave.stream().filter(file -> {
            boolean isBannerFileExisting = fileHandler.isFileEntityExistingInDatabase(file, account, filesInDatabase);
            return !isBannerFileExisting;
        }).collect(Collectors.toList());

       
        boolean isAvatarFileExisting = fileHandler.isFileEntityExistingInDatabase(avatarFileToSave, account, filesInDatabase);
        if (!isAvatarFileExisting) {
            avatarFileToSave.setLocationAvatar(location);
            location.setAvatarFile(avatarFileToSave);
        }

       
        List<UUID> categoryIds = locationDto.getCategoryIds();
        List<Category> categories = categoryService.findAllByCategoryIds(categoryIds);


       
        List<File> allBannerFilesInDatabase = location.getBannerFiles();
        allBannerFilesInDatabase.addAll(filteredBannerFiles);
        location.setBannerFiles(allBannerFilesInDatabase);

       
        location.setCategories(categories);


       
        AccountLocationId accountLocationId = AccountLocationId.builder()
                .locationId(location.getId())
                .userId(account.getId())
                .build();

        LocationLog locationLog = LocationLog.builder()
                .locationLogType(LocationLogType.UPDATE)
                .name(location.getName())
                .description(location.getDescription())
                .address(location.getAddress())
                .website(location.getWebsite())
                .phoneNumber(location.getPhoneNumber())
                .accountLocationId(accountLocationId)
                .location(location)
                .account(account)
                .build();
        locationLogRepository.save(locationLog);

        return locationRepository.save(location);
    }

    @Override
    public List<Location> findByArea(LocationAreaDto locationAreaDto) throws Exception {
        BigDecimal biggestLatitude;
        BigDecimal biggestLongitude;
        BigDecimal smallestLatitude;
        BigDecimal smallestLongitude;
        BigDecimal latitudeOne = locationAreaDto.getLatitudeOne();
        BigDecimal latitudeTwo = locationAreaDto.getLatitudeTwo();
        BigDecimal longitudeOne = locationAreaDto.getLongitudeOne();
        BigDecimal longitudeTwo = locationAreaDto.getLongitudeTwo();
        if (latitudeOne.compareTo(latitudeTwo) <= 0) {
            biggestLatitude = latitudeTwo;
            smallestLatitude = latitudeOne;
        } else {
            biggestLatitude = latitudeOne;
            smallestLatitude = latitudeTwo;
        }

        if (longitudeOne.compareTo(longitudeTwo) <= 0) {
            biggestLongitude = longitudeTwo;
            smallestLongitude = longitudeOne;
        } else {
            biggestLongitude = latitudeOne;
            smallestLongitude = latitudeTwo;
        }

        List<Location> locations = locationRepository
                .findAllByLatitudeLessThanEqualAndLatitudeGreaterThanEqualAndLongitudeLessThanEqualAndLongitudeGreaterThanEqual
                        (biggestLatitude, smallestLatitude, biggestLongitude, smallestLongitude);

        return locations;
    }

    @Override
    public Location findByCoordinate(LocationCoordinateDto locationCoordinateDto) throws Exception {
        BigDecimal latitude = locationCoordinateDto.getLatitude();
        BigDecimal longitude = locationCoordinateDto.getLongitude();
        List<Location> locations = locationRepository.findAllByLatitudeEqualsAndLongitudeEquals(latitude, longitude);
        if (locations.isEmpty()) {
            throw new Exception("Cannot find location!");
        }

        return locations.get(0);
    }

    @Override
    public List<Location> findByNearestPlace(LocationCoordinateDto locationCoordinateDto) throws Exception {
        BigDecimal latitude = locationCoordinateDto.getLatitude();
        BigDecimal longitude = locationCoordinateDto.getLongitude();
        List<Location> locations = locationRepository.findNearestLocation(latitude, longitude);
        return locations;
    }


    @Override
    public Set<Location> findByKeyword(String keyword) throws Exception {
        Set<Location> locations = locationRepository.searchByKeyword(keyword);
        return locations;
    }

    @Override
    public Location findById(UUID locationId) throws Exception{
        Optional<Location> optionalLocation = locationRepository.findById(locationId);
        if (optionalLocation.isEmpty()) {
            throw new Exception("Cannot find location!");
        }
        Location foundLocation = optionalLocation.get();
        return foundLocation;
    }

    @Override
    public List<Location> findByRadius(LocationRadiusDto locationRadiusDto) throws Exception {
        BigDecimal latitude = locationRadiusDto.getLatitude();
        BigDecimal longitude = locationRadiusDto.getLongitude();
        BigDecimal radius = locationRadiusDto.getRadius();
        BigDecimal radiusInDegree = convertKmToDegree(radius);

        Validate.isBigDecimalPositive(radius);

       
        BigDecimal latitudeBottomLeft = latitude.subtract(radiusInDegree);
        BigDecimal longitudeBottomLeft = longitude.subtract(radiusInDegree);

       
        BigDecimal latitudeTopRight = latitude.add(radiusInDegree);
        BigDecimal longitudeTopRight = longitude.add(radiusInDegree);

        LocationAreaDto locationAreaDto = LocationAreaDto.builder()
                .latitudeOne(latitudeBottomLeft)
                .longitudeOne(longitudeBottomLeft)
                .latitudeTwo(latitudeTopRight)
                .longitudeTwo(longitudeTopRight)
                .build();

        List<Location> locations = findByArea(locationAreaDto);
        return locations;
    }

    @Override
    public LocationDto convertStringToJson(String locationDtoString) throws Exception {
        LocationDto locationDto = new LocationDto();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            locationDto = objectMapper.readValue(locationDtoString, LocationDto.class);
        } catch (Exception err) {
//            System.out.printf("Error ", err.toString());
            throw new Exception(err.getMessage());
        }

        return locationDto;
    }

    public List<File> handleBannerFiles(Location currentLocation, Account account, List<MultipartFile> bannerFiles) {
        List<File> bannerFilesToSave = new ArrayList<>();
        for (MultipartFile file :
                bannerFiles) {
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads/downloadFile/")
                    .path(fileName).toUriString();
            File builtFile = File.builder()
                    .fileName(fileName)
                    .fileDownloadUri(fileDownloadUri)
                    .fileType(file.getContentType())
                    .size(file.getSize())
                    .account(account)
                    .locationBanner(currentLocation)
                    .build();
            bannerFilesToSave.add(builtFile);
        }

        return bannerFilesToSave;
    }

    public File handleAvatarFile(Location currentLocation, Account account, MultipartFile avatarFile) {
        String fileName = fileStorageService.storeFile(avatarFile);
        String avatarDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads/downloadFile/")
                .path(fileName).toUriString();
        File avatarFileToSave = File.builder()
                .fileName(fileName)
                .fileDownloadUri(avatarDownloadUri)
                .fileType(avatarFile.getContentType())
                .size(avatarFile.getSize())
                .account(account)
                .locationAvatar(currentLocation)
                .build();

        return avatarFileToSave;
    }

    public BigDecimal convertKmToDegree(BigDecimal km) {
        return km.multiply(BigDecimal.valueOf(0.008)) ;
    }

}
