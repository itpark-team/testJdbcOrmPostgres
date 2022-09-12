package com.company;

import com.company.model.entities.User;
import com.company.service.UsersService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int action = 0;

        UsersService usersService = null;

        try {
            usersService = new UsersService();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Приложение будет закрыто");
            System.exit(0);
        }
        
        while (true) {
            System.out.println("Меню");

            System.out.println("1.Вывести всех пользователей");
            System.out.println("0.Выход");

            System.out.print("Введите пункт меню: ");
            action = scanner.nextInt();

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

                case 0: {
                    System.exit(0);
                }
                break;
            }

        }

    }
}
