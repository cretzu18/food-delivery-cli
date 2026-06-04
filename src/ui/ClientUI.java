package ui;

import model.Adresa;
import model.Client;
import service.CsvExportService;
import service.FoodDeliveryService;

import java.util.Scanner;

public class ClientUI {
    private FoodDeliveryService service;
    private Scanner scanner;

    public ClientUI(FoodDeliveryService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n--- MENIU CLIENT ---\n");
                System.out.println("1. Vizualizeaza toate restaurantele");
                System.out.println("2. Vezi meniul unui restaurant");
                System.out.println("3. Adauga un produs in cos");
                System.out.println("4. Vezi cosul de cumparaturi");
                System.out.println("5. Plaseaza comanda");
                System.out.println("6. Vezi istoricul comenzilor mele");
                System.out.println("7. Vezi lista curierilor");
                System.out.println("8. Lasa un rating unui restaurant");
                System.out.println("0. Logout");
                System.out.print("Optiune: ");
                int optiune = Integer.parseInt(scanner.nextLine());

                switch (optiune) {
                    case 1:
                        CsvExportService.scrieAudit("client_vizualizare_restaurante");
                        service.afiseazaRestaurante();
                        break;

                    case 2:
                        CsvExportService.scrieAudit("client_vizualizare_meniu");
                        service.afiseazaRestaurante();
                        System.out.print("Restaurantul pentru care vrei sa vezi produsele: ");
                        String restaurant = scanner.nextLine();
                        service.afiseazaProduse(restaurant);
                        break;

                    case 3:
                        CsvExportService.scrieAudit("client_adauga_in_cos");
                        service.afiseazaRestaurante();
                        System.out.print("Din ce restaurant? ");
                        String numeRestaurant = scanner.nextLine();
                        service.afiseazaProduse(numeRestaurant);
                        System.out.print("Ce produs doresti? ");
                        String numeProdus = scanner.nextLine();
                        System.out.print("Cantitate: ");
                        int cantitate = Integer.parseInt(scanner.nextLine());
                        service.clientAdaugaInCos(numeRestaurant, numeProdus, cantitate);
                        break;

                    case 4:
                        CsvExportService.scrieAudit("client_vizualizare_cos");
                        service.clientVizualizareCos();
                        break;

                    case 5:
                        CsvExportService.scrieAudit("client_plaseaza_comanda");
                        Adresa adresaImplicita = ((Client) service.getUserLogat()).getAdresaImplicita();
                        System.out.println(adresaImplicita);
                        System.out.print("Folositi adresa implicita? (Da/Nu): ");
                        String raspuns = scanner.nextLine();
                        if (raspuns.equalsIgnoreCase("da")) {
                            Client c = (Client) service.getUserLogat();
                            service.clientPlaseazaComanda(adresaImplicita);
                        } else {
                            System.out.print("Oras: ");
                            String oras = scanner.nextLine();
                            System.out.print("Strada: ");
                            String strada = scanner.nextLine();
                            System.out.print("Numar: ");
                            int numar = Integer.parseInt(scanner.nextLine());

                            Client c = (Client) service.getUserLogat();
                            service.clientPlaseazaComanda(new Adresa(oras, strada, numar));
                        }
                        break;

                    case 6:
                        CsvExportService.scrieAudit("client_vizualizare_istoric");
                        service.clientVizualizareIstoric();
                        break;

                    case 7:
                        CsvExportService.scrieAudit("client_vizualizare_curieri");
                        service.clientVizualizareCurieri();
                        break;

                    case 8:
                        CsvExportService.scrieAudit("client_lasa_rating");
                        service.afiseazaRestaurante();
                        System.out.print("Numele restaurantului pentru rating: ");
                        String numeRes = scanner.nextLine();
                        System.out.print("Nota (1-5): ");
                        double nota = Double.parseDouble(scanner.nextLine());
                        service.clientLasaRating(numeRes, nota);
                        break;

                    case 0:
                        CsvExportService.scrieAudit("client_logout");
                        service.logout();
                        running = false;
                        break;

                    default:
                        System.out.println("Optiune invalida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Optiunea trebuia sa fie un numar intreg.");
            }
        }
    }
}
