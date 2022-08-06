package com.myrmicatech.globalguidebackend.mapper;

import com.myrmicatech.globalguidebackend.dto.AccountDto;
import com.myrmicatech.globalguidebackend.dto.CommentSaveDto;
import com.myrmicatech.globalguidebackend.dto.CommentUpdateDto;
import com.myrmicatech.globalguidebackend.dto.ItemDto;
import com.myrmicatech.globalguidebackend.dto.LocationDto;
import com.myrmicatech.globalguidebackend.dto.ReactionDto;
import com.myrmicatech.globalguidebackend.dto.ReviewDto;
import com.myrmicatech.globalguidebackend.dto.TimeDto;
import com.myrmicatech.globalguidebackend.dto.UploadFileDto;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Account.AccountBuilder;
import com.myrmicatech.globalguidebackend.entity.Comment;
import com.myrmicatech.globalguidebackend.entity.Comment.CommentBuilder;
import com.myrmicatech.globalguidebackend.entity.File;
import com.myrmicatech.globalguidebackend.entity.File.FileBuilder;
import com.myrmicatech.globalguidebackend.entity.Item;
import com.myrmicatech.globalguidebackend.entity.Item.ItemBuilder;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.entity.Location.LocationBuilder;
import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.entity.Reaction.ReactionBuilder;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.entity.Review.ReviewBuilder;
import com.myrmicatech.globalguidebackend.entity.Time;
import com.myrmicatech.globalguidebackend.entity.Time.TimeBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-24T18:05:29+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public Account accountDtoToAccount(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        AccountBuilder account = Account.builder();

        account.fullName( accountDto.getFullName() );
        account.email( accountDto.getEmail() );
        account.username( accountDto.getUsername() );

        return account.build();
    }

    @Override
    public Location locationDtoToLocation(LocationDto locationDto) {
        if ( locationDto == null ) {
            return null;
        }

        LocationBuilder location = Location.builder();

        location.name( locationDto.getName() );
        location.description( locationDto.getDescription() );
        location.address( locationDto.getAddress() );
        location.website( locationDto.getWebsite() );
        location.phoneNumber( locationDto.getPhoneNumber() );
        location.latitude( locationDto.getLatitude() );
        location.longitude( locationDto.getLongitude() );

        return location.build();
    }

    @Override
    public File uploadFileDtoToFile(UploadFileDto uploadFileDto) {
        if ( uploadFileDto == null ) {
            return null;
        }

        FileBuilder file = File.builder();

        file.fileName( uploadFileDto.getFileName() );
        file.fileDownloadUri( uploadFileDto.getFileDownloadUri() );
        file.fileType( uploadFileDto.getFileType() );
        file.size( uploadFileDto.getSize() );
        file.locationBannerId( uploadFileDto.getLocationBannerId() );
        file.reviewId( uploadFileDto.getReviewId() );
        file.account( uploadFileDto.getAccount() );

        return file.build();
    }

    @Override
    public Review reviewDtoToReview(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        ReviewBuilder review = Review.builder();

        review.description( reviewDto.getDescription() );
        review.rating( reviewDto.getRating() );
        review.locationId( reviewDto.getLocationId() );

        return review.build();
    }

    @Override
    public Comment commentSaveDtoToComment(CommentSaveDto commentSaveDto) {
        if ( commentSaveDto == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        comment.description( commentSaveDto.getDescription() );
        comment.reviewId( commentSaveDto.getReviewId() );

        return comment.build();
    }

    @Override
    public Reaction reactionDtoToReaction(ReactionDto reactionDto) {
        if ( reactionDto == null ) {
            return null;
        }

        ReactionBuilder reaction = Reaction.builder();

        reaction.reactionType( reactionDto.getReactionType() );

        return reaction.build();
    }

    @Override
    public Time timeDtoToTime(TimeDto timeDto) {
        if ( timeDto == null ) {
            return null;
        }

        TimeBuilder time = Time.builder();

        time.weekDay( timeDto.getWeekDay() );
        time.openTime( timeDto.getOpenTime() );
        time.closeTime( timeDto.getCloseTime() );

        return time.build();
    }

    @Override
    public Item itemDtoToItem(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        ItemBuilder item = Item.builder();

        item.name( itemDto.getName() );
        item.defaultLevel( itemDto.getDefaultLevel() );
        item.defaultEnergy( itemDto.getDefaultEnergy() );

        return item.build();
    }

    @Override
    public void update(Account staff, AccountDto staffEntity) {
        if ( staffEntity == null ) {
            return;
        }

        staff.setFullName( staffEntity.getFullName() );
        staff.setEmail( staffEntity.getEmail() );
        staff.setUsername( staffEntity.getUsername() );
    }

    @Override
    public void update(Location location, LocationDto locationDto) {
        if ( locationDto == null ) {
            return;
        }

        location.setName( locationDto.getName() );
        location.setDescription( locationDto.getDescription() );
        location.setAddress( locationDto.getAddress() );
        location.setWebsite( locationDto.getWebsite() );
        location.setPhoneNumber( locationDto.getPhoneNumber() );
        location.setLatitude( locationDto.getLatitude() );
        location.setLongitude( locationDto.getLongitude() );
    }

    @Override
    public void update(Comment comment, CommentUpdateDto commentSaveDto) {
        if ( commentSaveDto == null ) {
            return;
        }

        comment.setDescription( commentSaveDto.getDescription() );
    }
}
