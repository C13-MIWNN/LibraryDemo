package nl.miwnn.se13.vincent.librarydemo.controller;

import nl.miwnn.se13.vincent.librarydemo.dtos.LibraryUserFormDTO;
import nl.miwnn.se13.vincent.librarydemo.model.LibraryUser;
import nl.miwnn.se13.vincent.librarydemo.services.LibraryUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 **/

@Controller
public class LibraryUserController {
    private final LibraryUserService libraryUserService;

    public LibraryUserController(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @GetMapping("/user/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new LibraryUserFormDTO());
        return "userForm";
    }

    @PostMapping("/user/new")
    public String processUserForm(@ModelAttribute("user") LibraryUserFormDTO libraryUserFormDTO,
                                  BindingResult bindingResult) {
        if (libraryUserService.userExists(libraryUserFormDTO.getName())) {
            bindingResult.rejectValue("name", "duplicate", "This username is not available");
        }

        if (!libraryUserFormDTO.getPassword().equals(libraryUserFormDTO.getConfirmPassword())) {
            bindingResult.rejectValue("password", "no.match", "The passwords do not match");
        }

        if(bindingResult.hasErrors()) {
            return "userForm";
        }

        libraryUserService.saveUser(libraryUserFormDTO);
        return "redirect:/user/new";
    }
}
