package nl.miwnn.se13.vincent.librarydemo.services.mappers;

import nl.miwnn.se13.vincent.librarydemo.dtos.LibraryUserFormDTO;
import nl.miwnn.se13.vincent.librarydemo.model.LibraryUser;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 **/
public class LibraryUserMapper {
    public static LibraryUser fromDTO(LibraryUserFormDTO dto) {
        LibraryUser user = new LibraryUser();
        user.setUsername(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }
}
