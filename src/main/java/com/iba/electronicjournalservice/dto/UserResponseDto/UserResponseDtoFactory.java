package com.iba.electronicjournalservice.dto.UserResponseDto;

import com.iba.electronicjournalservice.model.user.User;

final public class UserResponseDtoFactory {

    static public UserResponseDto fromUserToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getPhone(),
        user.getRole(),
                user.getClassId());
    }
}
