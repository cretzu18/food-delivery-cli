package ui;

import service.FoodDeliveryService;
import service.CsvExportService;

import java.util.Scanner;

public class CurierUI {
    private FoodDeliveryService service;
    private Scanner scanner;

    public CurierUI(FoodDeliveryService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n--- PANOU CONTROL CURIER ---\n");
                System.out.println("1. Vizualizeaza comenzi disponibile");
                System.out.println("2. Preia o comanda pentru livrare");
                System.out.println("3. Finalizeaza livrarea");
                System.out.println("0. Logout");
                System.out.print("Optiune: ");

                int optiune = Integer.parseInt(scanner.nextLine());
                switch (optiune) {
                    case 1:
                        CsvExportService.scrieAudit("curier_vizualizare_comenzi_disponibile");
                        service.curierAfiseazaComenziDisponibile();
                        break;

                    case 2:
                        CsvExportService.scrieAudit("curier_preia_comanda");
                        service.curierAfiseazaComenziDisponibile();
                        System.out.print("Introdu ID-ul comenzii pe care vrei sa o preiei: ");
                        int idPreluare = Integer.parseInt(scanner.nextLine());
                        service.curierPreiaComanda(idPreluare);
                        break;

                    case 3:
                        CsvExportService.scrieAudit("curier_finalizeaza_comanda");
                        service.curierFinalizeazaComanda();
                        break;

                    case 0:
                        CsvExportService.scrieAudit("curier_logout");
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
