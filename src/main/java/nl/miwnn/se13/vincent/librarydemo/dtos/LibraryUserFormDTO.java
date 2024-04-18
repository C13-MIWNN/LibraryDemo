package nl.miwnn.se13.vincent.librarydemo.dtos;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 **/
public class LibraryUserFormDTO {
    private String name;

    private String password;
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
