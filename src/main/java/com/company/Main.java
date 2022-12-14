package com.company;

import com.company.model.entities.Country;
import com.company.model.entities.User;
import com.company.service.CountriesService;
import com.company.service.UsersService;
import com.company.util.Util;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int action = 0;

        UsersService usersService = null;
        CountriesService countriesService = null;

        try {
            usersService = new UsersService();
            countriesService = new CountriesService();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Приложение будет закрыто");
            System.exit(0);
        }

        while (true) {
            System.out.println("Меню");

            System.out.println("1.Вывести всех пользователей");
            System.out.println("2.Добавить нового пользователя");
            System.out.println("3.Обновить пользователя по ИД");
            System.out.println("4.Удалить пользователя по ИД");

            System.out.println("0.Выход");

            action = Util.inputInt("Введите пункт меню: ", 0, 4);

            switch (action) {
                case 1: {
                    try {
                        List<User> users = usersService.getAllUsers();
                        users.forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;

                case 2: {
                    try {
                        String name = Util.inputString("Введите имя: ", 30);
                        int age = Util.inputInt("Введите возраст: ", 0, 150);

                        System.out.println("Список доступных стран");

                        List<Country> countries = countriesService.getAllCountries();
                        countries.forEach(System.out::println);

                        List<Integer> countriesIds = countries.stream().map(Country::getId).collect(Collectors.toList());

                        int countryId = Util.inputInt("Введите id выбранной Вами страны: ", countriesIds);

                        usersService.addNewUser(new User(name, age, countryId));

                        System.out.println("Пользователь успешно добавлен");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;

                case 3: {
                    try {
                        List<User> users = usersService.getAllUsers();
                        users.forEach(System.out::println);

                        List<Integer> usersIds = users.stream().map(User::getId).collect(Collectors.toList());
                        int id = Util.inputInt("Введите id пользователя для обновления: ", usersIds);

                        String name = Util.inputString("Введите имя: ", 30);
                        int age = Util.inputInt("Введите возраст: ", 0, 150);

                        System.out.println("Список доступных стран");

                        List<Country> countries = countriesService.getAllCountries();
                        countries.forEach(System.out::println);

                        List<Integer> countriesIds = countries.stream().map(Country::getId).collect(Collectors.toList());

                        int countryId = Util.inputInt("Введите id выбранной Вами страны: ", countriesIds);

                        usersService.updateUser(new User(id, name, age, countryId));

                        System.out.println("Пользователь успешно обновлён");


                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;

                case 4: {
                    try {
                        List<User> users = usersService.getAllUsers();
                        users.forEach(System.out::println);

                        List<Integer> usersIds = users.stream().map(User::getId).collect(Collectors.toList());
                        int id = Util.inputInt("Введите id пользователя для удаления: ", usersIds);

                        usersService.deleteUser(id);

                        System.out.println("Пользователь успешно удален");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;

                case 0: {
                    System.exit(0);
                }
                break;
            }

        }

    }
}
