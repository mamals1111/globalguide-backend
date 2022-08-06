package com.myrmicatech.globalguidebackend.mapper;


import com.myrmicatech.globalguidebackend.dto.*;
import com.myrmicatech.globalguidebackend.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
@Component
public interface MapStructMapper {

    Account accountDtoToAccount(AccountDto accountDto);

    Location locationDtoToLocation(LocationDto locationDto);

    File uploadFileDtoToFile(UploadFileDto uploadFileDto);

    Review reviewDtoToReview(ReviewDto reviewDto);

    Comment commentSaveDtoToComment(CommentSaveDto commentSaveDto);

    Reaction reactionDtoToReaction(ReactionDto reactionDto);

    Time timeDtoToTime(TimeDto timeDto);

    Item itemDtoToItem(ItemDto itemDto);

    void update(@MappingTarget Account staff, AccountDto staffEntity);

    void update(@MappingTarget Location location, LocationDto locationDto);

    void update(@MappingTarget Comment comment, CommentUpdateDto commentSaveDto);

    default LocalDateTime emptyToNull(String s) {
        if (s == null || s.isEmpty()) return null;
        return LocalDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME);
    }

    default LocalTime emptyTimeToNull(String s) {
        if (s == null || s.isEmpty()) return null;
        return LocalTime.parse(s);
    }
}
