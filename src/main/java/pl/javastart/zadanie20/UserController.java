package pl.javastart.zadanie20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/users")
    @ResponseBody
    public String getAddedUsers() {
        StringBuilder result = new StringBuilder();
        List<User> users = userRepository.getUsers();
        for (User user : users) {
            result.append(user.toString()).append("<br/>");
        }
        return result.toString();
    }

    @RequestMapping("/add")
    public String addUserForm(@RequestParam(value = "imie", required = true) String firstName,
                              @RequestParam(value = "nazwisko", required = true) String lastName,
                              @RequestParam(value = "wiek", required = true) String ageStr) {
        // Sprawdzenie, czy imię, nazwisko są niepuste i czy wiek jest poprawny
        if (firstName.isEmpty() || firstName.equalsIgnoreCase("null") ||
                lastName.isEmpty() || lastName.equalsIgnoreCase("null") ||
                ageStr.isEmpty() || ageStr.equalsIgnoreCase("null")) {
            return "redirect:/err.html";  // Przekierowanie na stronę błędu, jeśli dane są nieprawidłowe
        }

        try {
            int age = Integer.parseInt(ageStr);  // Próbujemy zamienić wiek na liczbę całkowitą
            if (age < 0) {  // Sprawdzamy, czy wiek jest nieujemny
                return "redirect:/err.html";
            }
            userRepository.addUser(new User(firstName, lastName, age));
            return "redirect:/success.html";  // Przekierowanie na stronę sukcesu
        } catch (NumberFormatException e) {
            return "redirect:/err.html";  // Przekierowanie na stronę błędu w przypadku niepoprawnego formatu wieku
        }
    }
}
