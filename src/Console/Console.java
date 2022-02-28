package Console;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import algoritms.ReadCSV;
import algoritms.SelectionSort;
import algoritms.InsertionSort;
import algoritms.MergeSort;
import algoritms.QuickSort;
import algoritms.QuickSortWithMedian3;
import algoritms.AboveMedia;
import algoritms.BelowMedia;
import algoritms.CountingSort;
import algoritms.DateFormat;
import algoritms.HeapSort;

public class Console {
        Scanner entrada = new Scanner(System.in);
        boolean loading;

        File directory = new File("");
        String basePAth;
        String fileBase;

        public Console(String fileBase) {
                this.loading = false;
                this.basePAth = directory.getAbsolutePath();

                boolean hasSRC = false;
                for (String i : this.basePAth.split("/")) {
                        if (i.equalsIgnoreCase("src")) {
                                hasSRC = true;
                        }
                }

                if (hasSRC) {
                        this.basePAth = this.basePAth + "/csvs";
                } else {
                        this.basePAth = this.basePAth + "/src/csvs";
                }

                this.fileBase = this.basePAth + "/base/" + fileBase;
        }

        public void menu() throws ParseException, InterruptedException {

                while (true) {

                        System.out.printf("\t\t-------------------------------\n");
                        System.out.printf("\t\t|        AirBNB Data          |\n");
                        System.out.printf("\t\t-------------------------------\n");
                        System.out.printf("Pressione a tecla para executar a função correspondente:\n\n");
                        System.out.printf("Digite (1) - INICIAR\n\n");
                        System.out.printf("Digite (2) - SOBRE\n\n");
                        System.out.printf("Digite (3) - CRÉDITOS\n\n");
                        System.out.printf("Digite (0) - SAIR\n\n");

                        int digito = getEnter();

                        clearConsole();

                        if (digito == 1) {
                                program();
                        } else if (digito == 2) {
                                sobre();
                        } else if (digito == 3) {
                                creditos();
                        } else if (digito == 0) {
                                clearConsole();
                                System.out.printf(" PROGRAMA ENCERRADO !\n");
                                entrada.close();
                                break;
                        } else {
                                System.out.printf("Valor incorreto, tente novamente!\n");

                                try {
                                        Thread.sleep(1500);
                                } catch (InterruptedException ex) {
                                }

                                clearConsole();
                        }
                }

        }

        private int getEnter() {
                String linha = entrada.nextLine(); // ler a linha (termina no enter)

                try {
                        int inteiro = Integer.parseInt(linha); // (tenta converter pra int os dados inseridos)
                        return inteiro;
                } catch (Exception e) {
                        return 20;
                }
        }

        public void program() throws ParseException, InterruptedException {

                System.out.printf("\t\tSelecione o Método de Ordenação\n");
                System.out.printf("\t\t-------------------------------\n\n");
                System.out.printf("Pressione a tecla para executar a função correspondente:\n\n");
                System.out.printf("Digite (1) - Selection Sort\n\n");
                System.out.printf("Digite (2) - Insertion Sort\n\n");
                System.out.printf("Digite (3) - Merge Sort\n\n");
                System.out.printf("Digite (4) - Quick Sort\n\n");
                System.out.printf("Digite (5) - Quick Sort com Mediana de 3\n\n");
                System.out.printf("Digite (6) - Counting\n\n");
                System.out.printf("Digite (7) - HeapSort\n\n");
                System.out.printf("Digite (8) - Executar Todos\n\n");
                System.out.printf("Digite (9) - Transformar Datas\n\n");
                System.out.printf("Digite (10) - Gerar valores Acima da Media\n\n");
                System.out.printf("Digite (0) - SAIR\n\n");

                int digito = getEnter();
                clearConsole();

                if (digito == 1) {

                        ReadCSV numberOfReviews = new ReadCSV(this.fileBase,
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_numberOfreviews_selectionSort_medioCaso.csv",
                                        this.basePAth
                                                        + "/SelectionSort/MedioCaso/metrics_listings_numberOfreviews_selectionSort_medioCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        numberOfReviews.readCsv(new SelectionSort(true));

                        ReadCSV price = new ReadCSV(this.fileBase,
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_price_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MedioCaso/metrics_listings_price_selectionSort_medioCaso.csv",
                                        "price", ";");

                        price.readCsv(new SelectionSort(true));

                        ReadCSV name = new ReadCSV(this.fileBase,
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_names_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MedioCaso/metrics_listings_names_selectionSort_medioCaso.csv",
                                        "name", ";");

                        name.readCsv(new SelectionSort(false));

                        this.loading = false;

                        // MELHOR CASO

                        ReadCSV bestNumberOfReviews = new ReadCSV(
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_numberOfreviews_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/listings_bestNumberOfreviews_selectionSort_melhorCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/metrics_bestNumberOfreviews_selectionSort_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        bestNumberOfReviews.readCsv(new SelectionSort(true));

                        ReadCSV bestPrice = new ReadCSV(
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_price_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/listings_bestPrice_selectionSort_melhorCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/metrics__bestPrice_selectionSort_melhorCaso.csv",
                                        "price", ";");

                        bestPrice.readCsv(new SelectionSort(true));

                        ReadCSV bestName = new ReadCSV(
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_names_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/listings_bestName_selectionSort_MelhorCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/metrics_bestName_selectionSort_MelhorCaso.csv",
                                        "name", ";");

                        bestName.readCsv(new SelectionSort(false));

                        this.loading = false;
                        clearConsole();

                } else if (digito == 2) {

                        ReadCSV numberOfReviews = new ReadCSV(this.fileBase,
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_numberOfreviews_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MedioCaso/metrics_numberOfreviews_insertionSort_medioCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        numberOfReviews.readCsv(new InsertionSort(true));

                        ReadCSV price = new ReadCSV(this.fileBase,
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_price_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MedioCaso/metrics_price_insertionSort_medioCaso.csv",
                                        "price", ";");

                        price.readCsv(new InsertionSort(true));

                        ReadCSV name = new ReadCSV(this.fileBase,
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_name_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MedioCaso/metrics_name_insertionSort_medioCaso.csv",
                                        "name", ";");

                        name.readCsv(new InsertionSort(false));

                        this.loading = false;

                        // MELHOR CASO

                        ReadCSV bestNumberOfReviews = new ReadCSV(
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_numberOfreviews_insertionSort_medioCaso.csv",
                                        this.basePAth
                                                        + "/InsertionSort/MelhorCaso/listings_bestNumberOfreviews_insertionSort_melhorCaso.csv",
                                        this.basePAth
                                                        + "/InsertionSort/MelhorCaso/metrics_bestNumberOfreviews_insertionSort_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        bestNumberOfReviews.readCsv(new InsertionSort(true));

                        ReadCSV bestPrice = new ReadCSV(
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_price_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MelhorCaso/listings_bestPrice_insertionSort_melhorCaso.csv",
                                        this.basePAth + "/InsertionSort/MelhorCaso/metrics_bestPrice_insertionSort_melhorCaso.csv",
                                        "price", ";");

                        bestPrice.readCsv(new InsertionSort(true));

                        ReadCSV bestName = new ReadCSV(
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_name_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MelhorCaso/listings_bestName_insertionSort_MelhorCaso.csv",
                                        this.basePAth + "/InsertionSort/MelhorCaso/metrics_bestName_insertionSort_MelhorCaso.csv",
                                        "name", ";");

                        bestName.readCsv(new InsertionSort(false));

                        this.loading = false;

                        clearConsole();

                } else if (digito == 3) {

                        ReadCSV numberOfReviews = new ReadCSV(this.fileBase,
                                        this.basePAth + "/MergeSort/MedioCaso/listing_numberOfReviews_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MedioCaso/listing_numberOfReviews_MergeSort_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        numberOfReviews.readCsv(new MergeSort(true));

                        ReadCSV price = new ReadCSV(this.fileBase,
                                        this.basePAth + "/MergeSort/MedioCaso/listing_price_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MedioCaso/metrics_price_MergeSort_MedioCaso.csv",
                                        "price", ";");

                        price.readCsv(new MergeSort(true));

                        ReadCSV name = new ReadCSV(this.fileBase,
                                        this.basePAth + "/MergeSort/MedioCaso/listing_name_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MedioCaso/metrics_name_MergeSort_MedioCaso.csv",
                                        "name", ";");

                        name.readCsv(new MergeSort(false));
                        this.loading = false;

                        // MELHOR CASO

                        ReadCSV bestNumberOfReviews = new ReadCSV(
                                        this.basePAth + "/MergeSort/MedioCaso/listing_numberOfReviews_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/listing_bestNumberOfReviews_MergeSort_MelhorCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/metrics_bestNumberOfReviews_MergeSort_MelhorCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        bestNumberOfReviews.readCsv(new MergeSort(true));

                        ReadCSV bestPrice = new ReadCSV(
                                        this.basePAth + "/MergeSort/MedioCaso/listing_price_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/listing_bestPrice_MergeSort_MelhorCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/metrics_bestPrice_MergeSort_MelhorCaso.csv",
                                        "price", ";");

                        bestPrice.readCsv(new MergeSort(true));

                        ReadCSV bestName = new ReadCSV(
                                        this.basePAth + "/MergeSort/MedioCaso/listing_name_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/listing_name_MergeSort_MelhorCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/metrics_name_MergeSort_MelhorCaso.csv",
                                        "name", ";");

                        bestName.readCsv(new MergeSort(false));
                        this.loading = false;

                        clearConsole();
                } else if (digito == 4) {

                        ReadCSV numberOfReviews = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSort/MedioCaso/listings_numberOfReviews_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MedioCaso/metrics_numberOfReviews_QuickSort_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;

                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        numberOfReviews.readCsv(new QuickSort(true));

                        ReadCSV price = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSort/MedioCaso/listings_price_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MedioCaso/metrics_price_QuickSort_MedioCaso.csv",
                                        "price", ";");

                        price.readCsv(new QuickSort(true));

                        ReadCSV name = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSort/MedioCaso/listings_name_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MedioCaso/metrics_name_QuickSort_MedioCaso.csv",
                                        "name", ";");

                        name.readCsv(new QuickSort(false));
                        this.loading = false;

                        // MELHOR CASO
                        ReadCSV bestNumberOfReviews = new ReadCSV(
                                        this.basePAth + "/QuickSort/MedioCaso/listings_numberOfReviews_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/listings_bestNumberOfReviews_QuickSort_MelhorCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/metrics_bestNumberOfReviews_QuickSort_MelhorCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;

                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        bestNumberOfReviews.readCsv(new QuickSort(true));

                        ReadCSV bestPrice = new ReadCSV(
                                        this.basePAth + "/QuickSort/MedioCaso/listings_price_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/listings_bestPrice_QuickSort_MelhorCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/metrics_bestPrice_QuickSort_MelhorCaso.csv",
                                        "price", ";");

                        bestPrice.readCsv(new QuickSort(true));

                        ReadCSV bestName = new ReadCSV(
                                        this.basePAth + "/QuickSort/MedioCaso/listings_name_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/listings_bestName_QuickSort_MelhorCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/metrics_bestName_QuickSort_MelhorCaso.csv",
                                        "name", ";");

                        bestName.readCsv(new QuickSort(false));
                        this.loading = false;

                        clearConsole();

                } else if (digito == 5) {

                        ReadCSV numberOfReviews = new ReadCSV(this.fileBase,
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MedioCaso/listings_numberOfReviews_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MedioCaso/metrics_numberOfReviews_QuickSortWithMedian3_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        numberOfReviews.readCsv(new QuickSortWithMedian3(true));

                        ReadCSV price = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/listing_price_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/metrics_price_QuickSortWithMedian3_MedioCaso.csv",
                                        "price", ";");

                        price.readCsv(new QuickSortWithMedian3(true));

                        ReadCSV name = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/listing_name_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/metrics_name_QuickSortWithMedian3_MedioCaso.csv",
                                        "name", ";");

                        name.readCsv(new QuickSortWithMedian3(false));
                        this.loading = false;

                        // MELHOR CASO

                        ReadCSV bestNumberOfReviews = new ReadCSV(
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MedioCaso/listings_numberOfReviews_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/listings_bestNumberOfreviews_QuickSortWithMedian3_melhorCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/metrics_bestNumberOfreviews_QuickSortWithMedian3_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        bestNumberOfReviews.readCsv(new QuickSortWithMedian3(true));

                        ReadCSV bestPrice = new ReadCSV(
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/listing_price_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/listings_bestPrice_QuickSortWithMedian3_melhorCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/metrics_bestPrice_QuickSortWithMedian3_melhorCaso.csv",
                                        "price", ";");

                        bestPrice.readCsv(new QuickSortWithMedian3(true));

                        ReadCSV bestName = new ReadCSV(
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/listing_name_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/listings_bestName_QuickSortWithMedian3_melhorCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/metrics_bestName_QuickSortWithMedian3_melhorCaso.csv",
                                        "name", ";");

                        bestName.readCsv(new QuickSortWithMedian3(false));
                        this.loading = false;

                        clearConsole();

                } else if (digito == 6) {

                        ReadCSV numberOfReviews = new ReadCSV(this.fileBase,
                                        this.basePAth + "/CountingSort/MedioCaso/listings_numberOrReviews_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MedioCaso/metrics_numberOrReviews_CountingSort_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        numberOfReviews.readCsv(new CountingSort(true));

                        ReadCSV price = new ReadCSV(this.fileBase,
                                        this.basePAth + "/CountingSort/MedioCaso/listings_price_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MedioCaso/metrics_price_CountingSort_MedioCaso.csv",
                                        "price", ";");

                        price.readCsv(new CountingSort(true));

                        ReadCSV name = new ReadCSV(this.fileBase,
                                        this.basePAth + "/CountingSort/MedioCaso/listing_name_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MedioCaso/metrics_name_CountingSort_MedioCaso.csv",
                                        "name", ";");

                        name.readCsv(new CountingSort(false));
                        this.loading = false;

                        // MELHOR CASO
                        ReadCSV bestNumberOfReviews = new ReadCSV(
                                        this.basePAth + "/CountingSort/MedioCaso/listings_numberOrReviews_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/listings_bestNumberOfreviews_CountingSort_melhorCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/metrics_bestNumberOfreviews_CountingSort_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        bestNumberOfReviews.readCsv(new CountingSort(true));

                        ReadCSV bestPrice = new ReadCSV(
                                        this.basePAth + "/CountingSort/MedioCaso/listings_price_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/listings_bestPrice_CountingSort_melhorCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/metrics_bestPrice_CountingSort_melhorCaso.csv",
                                        "price", ";");

                        bestPrice.readCsv(new CountingSort(true));

                        ReadCSV bestName = new ReadCSV(
                                        this.basePAth + "/CountingSort/MedioCaso/listings_price_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/listings_bestName_CountingSort_melhorCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/metrics_bestName_CountingSort_melhorCaso.csv",
                                        "name", ";");

                        bestName.readCsv(new CountingSort(false));
                        this.loading = false;

                        clearConsole();

                } else if (digito == 7) {

                        ReadCSV numberOfReviews = new ReadCSV(this.fileBase,
                                        this.basePAth + "/HeapSort/MedioCaso/listing_numberOfReviews_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MedioCaso/metrics_numberOfReviews_HeapSort_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        numberOfReviews.readCsv(new HeapSort(true));

                        ReadCSV price = new ReadCSV(this.fileBase,
                                        this.basePAth + "/HeapSort/MedioCaso/listing_price_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MedioCaso/metrics_price_HeapSort_MedioCaso.csv",
                                        "price", ";");

                        price.readCsv(new HeapSort(true));

                        ReadCSV name = new ReadCSV(this.fileBase,
                                        this.basePAth + "/HeapSort/MedioCaso/listing_name_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MedioCaso/metrics_name_HeapSort_MedioCaso.csv",
                                        "name", ";");

                        name.readCsv(new HeapSort(false));
                        this.loading = false;

                        // MELHOR CASO HEAPSORT

                        ReadCSV BestNumberOfReviews = new ReadCSV(
                                        this.basePAth + "/HeapSort/MedioCaso/listing_numberOfReviews_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/listings_bestNumberOfreviews_HeapSort_melhorCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/metrics_bestNumberOfreviews_HeapSort_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        BestNumberOfReviews.readCsv(new HeapSort(true));

                        ReadCSV bestPrice = new ReadCSV(
                                        this.basePAth + "/HeapSort/MedioCaso/listing_price_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/listings_bestPrice_HeapSort_melhorCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/metrics_listings_bestPrice_HeapSort_melhorCaso.csv",
                                        "price", ";");

                        bestPrice.readCsv(new HeapSort(true));

                        ReadCSV bestName = new ReadCSV(
                                        this.basePAth + "/HeapSort/MedioCaso/listing_name_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/listings_bestName_HeapSort_melhorCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/metrics_listings_bestName_HeapSort_melhorCaso.csv",
                                        "name", ";");

                        bestName.readCsv(new HeapSort(false));
                        this.loading = false;

                        clearConsole();

                        clearConsole();
                } else if (digito == 8) {

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        // Medio Caso Selection Sort
                        ReadCSV numberOfReviews = new ReadCSV(this.fileBase,
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_numberOfreviews_selectionSort_medioCaso.csv",
                                        this.basePAth
                                                        + "/SelectionSort/MedioCaso/metrics_listings_numberOfreviews_selectionSort_medioCaso.csv",
                                        "number_of_reviews", ";");

                        numberOfReviews.readCsv(new SelectionSort(true));

                        ReadCSV price = new ReadCSV(this.fileBase,
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_price_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MedioCaso/metrics_listings_price_selectionSort_medioCaso.csv",
                                        "price", ";");

                        price.readCsv(new SelectionSort(true));

                        ReadCSV name = new ReadCSV(this.fileBase,
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_names_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MedioCaso/metrics_listings_names_selectionSort_medioCaso.csv",
                                        "name", ";");

                        name.readCsv(new SelectionSort(false));

                        // MELHOR CASO

                        ReadCSV bestNumberOfReviews = new ReadCSV(
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_numberOfreviews_selectionSort_medioCaso.csv",
                                        this.basePAth
                                                        + "/SelectionSort/MelhorCaso/listings_bestNumberOfreviews_selectionSort_melhorCaso.csv",
                                        this.basePAth + "/SelectionSort/MedioCaso/metrics_bestNumberOfreviews_selectionSort_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        bestNumberOfReviews.readCsv(new SelectionSort(true));

                        ReadCSV bestPrice = new ReadCSV(
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_price_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/listings_bestPrice_selectionSort_melhorCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/metrics__bestPrice_selectionSort_melhorCaso.csv",
                                        "price", ";");

                        bestPrice.readCsv(new SelectionSort(true));

                        ReadCSV bestName = new ReadCSV(
                                        this.basePAth + "/SelectionSort/MedioCaso/listings_names_selectionSort_medioCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/listings_bestName_selectionSort_MelhorCaso.csv",
                                        this.basePAth + "/SelectionSort/MelhorCaso/metrics_bestName_selectionSort_MelhorCaso.csv",
                                        "name", ";");

                        bestName.readCsv(new SelectionSort(false));

                        // InsertionSort
                        ReadCSV numberOfReviewsInsertion = new ReadCSV(this.fileBase,
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_numberOfreviews_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MedioCaso/metrics_numberOfreviews_insertionSort_medioCaso.csv",
                                        "number_of_reviews", ";");

                        numberOfReviewsInsertion.readCsv(new InsertionSort(true));

                        ReadCSV priceInsertion = new ReadCSV(this.fileBase,
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_price_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MedioCaso/metrics_price_insertionSort_medioCaso.csv",
                                        "price", ";");

                        priceInsertion.readCsv(new InsertionSort(true));

                        ReadCSV nameInsertion = new ReadCSV(this.fileBase,
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_name_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MedioCaso/metrics_name_insertionSort_medioCaso.csv",
                                        "name", ";");

                        nameInsertion.readCsv(new InsertionSort(false));

                        // MELHOR CASO

                        ReadCSV bestNumberOfReviewsInsertion = new ReadCSV(
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_numberOfreviews_insertionSort_medioCaso.csv",
                                        this.basePAth
                                                        + "/InsertionSort/MelhorCaso/listings_bestNumberOfreviews_insertionSort_melhorCaso.csv",
                                        this.basePAth
                                                        + "/InsertionSort/MelhorCaso/metrics_bestNumberOfreviews_insertionSort_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        bestNumberOfReviewsInsertion.readCsv(new InsertionSort(true));

                        ReadCSV bestPriceInsertion = new ReadCSV(
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_price_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MelhorCaso/listings_bestPrice_insertionSort_melhorCaso.csv",
                                        this.basePAth + "/InsertionSort/MelhorCaso/metrics_bestPrice_insertionSort_melhorCaso.csv",
                                        "price", ";");

                        bestPriceInsertion.readCsv(new InsertionSort(true));

                        ReadCSV bestNameInsertion = new ReadCSV(
                                        this.basePAth + "/InsertionSort/MedioCaso/listings_name_insertionSort_medioCaso.csv",
                                        this.basePAth + "/InsertionSort/MelhorCaso/listings_bestName_insertionSort_MelhorCaso.csv",
                                        this.basePAth + "/InsertionSort/MelhorCaso/metrics_bestName_insertionSort_MelhorCaso.csv",
                                        "name", ";");

                        bestNameInsertion.readCsv(new InsertionSort(false));

                        // MergeSort
                        ReadCSV numberOfReviewsMergeSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/MergeSort/MedioCaso/listing_numberOfReviews_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MedioCaso/listing_numberOfReviews_MergeSort_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        numberOfReviewsMergeSort.readCsv(new MergeSort(true));

                        ReadCSV priceMergeSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/MergeSort/MedioCaso/listing_price_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MedioCaso/metrics_price_MergeSort_MedioCaso.csv",
                                        "price", ";");

                        priceMergeSort.readCsv(new MergeSort(true));

                        ReadCSV nameMergeSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/MergeSort/MedioCaso/listing_name_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MedioCaso/metrics_name_MergeSort_MedioCaso.csv",
                                        "name", ";");

                        nameMergeSort.readCsv(new MergeSort(false));

                        // MELHOR CASO

                        ReadCSV bestNumberOfReviewsMergeSort = new ReadCSV(
                                        this.basePAth + "/MergeSort/MedioCaso/listing_numberOfReviews_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/listing_bestNumberOfReviews_MergeSort_MelhorCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/metrics_bestNumberOfReviews_MergeSort_MelhorCaso.csv",
                                        "number_of_reviews", ";");

                        bestNumberOfReviewsMergeSort.readCsv(new MergeSort(true));

                        ReadCSV bestPriceMergeSort = new ReadCSV(
                                        this.basePAth + "/MergeSort/MedioCaso/listing_price_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/listing_bestPrice_MergeSort_MelhorCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/metrics_bestPrice_MergeSort_MelhorCaso.csv",
                                        "price", ";");

                        bestPriceMergeSort.readCsv(new MergeSort(true));

                        ReadCSV bestNameMergeSort = new ReadCSV(
                                        this.basePAth + "/MergeSort/MedioCaso/listing_name_MergeSort_MedioCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/listing_name_MergeSort_MelhorCaso.csv",
                                        this.basePAth + "/MergeSort/MelhorCaso/metrics_name_MergeSort_MelhorCaso.csv",
                                        "name", ";");

                        bestNameMergeSort.readCsv(new MergeSort(false));

                        // QuickSort
                        ReadCSV numberOfReviewsQuickSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSort/MedioCaso/listings_numberOfReviews_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MedioCaso/metrics_numberOfReviews_QuickSort_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        numberOfReviewsQuickSort.readCsv(new QuickSort(true));

                        ReadCSV priceQuickSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSort/MedioCaso/listings_price_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MedioCaso/metrics_price_QuickSort_MedioCaso.csv",
                                        "price", ";");

                        priceQuickSort.readCsv(new QuickSort(true));

                        ReadCSV nameQuickSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSort/MedioCaso/listings_name_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MedioCaso/metrics_name_QuickSort_MedioCaso.csv",
                                        "name", ";");

                        nameQuickSort.readCsv(new QuickSort(false));

                        // MELHOR CASO
                        ReadCSV bestNumberOfReviewsQuickSort = new ReadCSV(
                                        this.basePAth + "/QuickSort/MedioCaso/listings_numberOfReviews_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/listings_bestNumberOfReviews_QuickSort_MelhorCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/metrics_bestNumberOfReviews_QuickSort_MelhorCaso.csv",
                                        "number_of_reviews", ";");

                        bestNumberOfReviewsQuickSort.readCsv(new QuickSort(true));

                        ReadCSV bestPriceQuickSort = new ReadCSV(
                                        this.basePAth + "/QuickSort/MedioCaso/listings_price_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/listings_bestPrice_QuickSort_MelhorCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/metrics_bestPrice_QuickSort_MelhorCaso.csv",
                                        "price", ";");

                        bestPriceQuickSort.readCsv(new QuickSort(true));

                        ReadCSV bestNameQuickSort = new ReadCSV(
                                        this.basePAth + "/QuickSort/MedioCaso/listings_name_QuickSort_MedioCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/listings_bestName_QuickSort_MelhorCaso.csv",
                                        this.basePAth + "/QuickSort/MelhorCaso/metrics_bestName_QuickSort_MelhorCaso.csv",
                                        "name", ";");

                        bestNameQuickSort.readCsv(new QuickSort(false));

                        // QuickSortWithMedian3
                        ReadCSV numberOfReviewsQuickSortWithMedian3 = new ReadCSV(this.fileBase,
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MedioCaso/listings_numberOfReviews_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MedioCaso/metrics_numberOfReviews_QuickSortWithMedian3_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        numberOfReviewsQuickSortWithMedian3.readCsv(new QuickSortWithMedian3(true));

                        ReadCSV priceQuickSortWithMedian3 = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/listing_price_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/metrics_price_QuickSortWithMedian3_MedioCaso.csv",
                                        "price", ";");

                        priceQuickSortWithMedian3.readCsv(new QuickSortWithMedian3(true));

                        ReadCSV nameQuickSortWithMedian3 = new ReadCSV(this.fileBase,
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/listing_name_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/metrics_name_QuickSortWithMedian3_MedioCaso.csv",
                                        "name", ";");

                        nameQuickSortWithMedian3.readCsv(new QuickSortWithMedian3(false));

                        // MELHOR CASO

                        ReadCSV bestNumberOfReviewsQuickSortWithMedian3 = new ReadCSV(
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MedioCaso/listings_numberOfReviews_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/listings_bestNumberOfreviews_QuickSortWithMedian3_melhorCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/metrics_bestNumberOfreviews_QuickSortWithMedian3_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        bestNumberOfReviewsQuickSortWithMedian3.readCsv(new QuickSortWithMedian3(true));

                        ReadCSV bestPriceQuickSortWithMedian3 = new ReadCSV(
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/listing_price_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/listings_bestPrice_QuickSortWithMedian3_melhorCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/metrics_bestPrice_QuickSortWithMedian3_melhorCaso.csv",
                                        "price", ";");

                        bestPriceQuickSortWithMedian3.readCsv(new QuickSortWithMedian3(true));

                        ReadCSV bestNameQuickSortWithMedian3 = new ReadCSV(
                                        this.basePAth + "/QuickSortWithMedian3/MedioCaso/listing_name_QuickSortWithMedian3_MedioCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/listings_bestName_QuickSortWithMedian3_melhorCaso.csv",
                                        this.basePAth
                                                        + "/QuickSortWithMedian3/MelhorCaso/metrics_bestName_QuickSortWithMedian3_melhorCaso.csv",
                                        "name", ";");

                        bestNameQuickSortWithMedian3.readCsv(new QuickSortWithMedian3(false));

                        // CountingSort
                        ReadCSV numberOfReviewsCountingSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/CountingSort/MedioCaso/listings_numberOrReviews_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MedioCaso/metrics_numberOrReviews_CountingSort_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        numberOfReviewsCountingSort.readCsv(new CountingSort(true));

                        ReadCSV priceCountingSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/CountingSort/MedioCaso/listings_price_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MedioCaso/metrics_price_CountingSort_MedioCaso.csv",
                                        "price", ";");

                        priceCountingSort.readCsv(new CountingSort(true));

                        ReadCSV nameCountingSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/CountingSort/MedioCaso/listing_name_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MedioCaso/metrics_name_CountingSort_MedioCaso.csv",
                                        "name", ";");

                        nameCountingSort.readCsv(new CountingSort(false));

                        // MELHOR CASO
                        ReadCSV bestNumberOfReviewsCountingSort = new ReadCSV(
                                        this.basePAth + "/CountingSort/MedioCaso/listings_numberOrReviews_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/listings_bestNumberOfreviews_CountingSort_melhorCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/metrics_bestNumberOfreviews_CountingSort_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        bestNumberOfReviewsCountingSort.readCsv(new CountingSort(true));

                        ReadCSV bestPriceCountingSort = new ReadCSV(
                                        this.basePAth + "/CountingSort/MedioCaso/listing_price_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/listings_bestPrice_CountingSort_melhorCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/metrics_bestPrice_CountingSort_melhorCaso.csv",
                                        "price", ";");

                        bestPriceCountingSort.readCsv(new CountingSort(true));

                        ReadCSV bestNameCountingSort = new ReadCSV(
                                        this.basePAth + "/CountingSort/MedioCaso/listing_name_CountingSort_MedioCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/listings_bestName_CountingSort_melhorCaso.csv",
                                        this.basePAth + "/CountingSort/MelhorCaso/metrics_bestName_CountingSort_melhorCaso.csv",
                                        "name", ";");

                        bestNameCountingSort.readCsv(new CountingSort(false));

                        // HeapSort
                        ReadCSV numberOfReviewsHeapSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/HeapSort/MedioCaso/listing_numberOfReviews_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MedioCaso/metrics_numberOfReviews_HeapSort_MedioCaso.csv",
                                        "number_of_reviews", ";");

                        numberOfReviewsHeapSort.readCsv(new HeapSort(true));

                        ReadCSV priceHeapSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/HeapSort/MedioCaso/listing_price_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MedioCaso/metrics_price_HeapSort_MedioCaso.csv",
                                        "price", ";");

                        priceHeapSort.readCsv(new HeapSort(true));

                        ReadCSV nameHeapSort = new ReadCSV(this.fileBase,
                                        this.basePAth + "/HeapSort/MedioCaso/listing_name_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MedioCaso/metrics_name_HeapSort_MedioCaso.csv",
                                        "name", ";");

                        nameHeapSort.readCsv(new HeapSort(false));

                        // MELHOR CASO HEAPSORT

                        ReadCSV BestNumberOfReviewsHeapSort = new ReadCSV(
                                        this.basePAth + "/HeapSort/MedioCaso/listing_numberOfReviews_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/listings_bestNumberOfreviews_HeapSort_melhorCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/metrics_bestNumberOfreviews_HeapSort_melhorCaso.csv",
                                        "number_of_reviews", ";");

                        BestNumberOfReviewsHeapSort.readCsv(new HeapSort(true));

                        ReadCSV bestPriceHeapSort = new ReadCSV(
                                        this.basePAth + "/HeapSort/MedioCaso/listing_price_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/listings_bestPrice_HeapSort_melhorCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/metrics_listings_bestPrice_HeapSort_melhorCaso.csv",
                                        "price", ";");

                        bestPriceHeapSort.readCsv(new HeapSort(true));

                        ReadCSV bestNameHeapSort = new ReadCSV(
                                        this.basePAth + "/HeapSort/MedioCaso/listing_name_HeapSort_MedioCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/listings_bestName_HeapSort_melhorCaso.csv",
                                        this.basePAth + "/HeapSort/MelhorCaso/metrics_listings_bestName_HeapSort_melhorCaso.csv",
                                        "name", ";");

                        bestNameHeapSort.readCsv(new HeapSort(false));

                        this.loading = false;
                        clearConsole();

                } else if (digito == 9) {
                        ReadCSV dateTransform = new ReadCSV(this.basePAth + "/base/listings.csv",
                                        this.basePAth + "/base/listings_review_date.csv",
                                        this.basePAth + "/Date/listings_review_date.csv",
                                        "Last_review", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        dateTransform.readCsvAndTransform(new DateFormat());
                        this.loading = false;
                        clearConsole();

                } else if (digito == 10) {
                        ReadCSV aboveMedia = new ReadCSV(this.fileBase,
                                        this.basePAth + "/Media/listings_gt_avg_prices.csv",
                                        this.basePAth + "/Media/listings_gt_avg_prices.csv",
                                        "price", ";");
                        ReadCSV belowMedia = new ReadCSV(this.fileBase,
                                        this.basePAth + "/Media/listings_lt_avg_prices.csv",
                                        this.basePAth + "/Media/listings_lt_avg_prices.csv",
                                        "price", ";");

                        this.loading = true;
                        new Thread() {
                                @Override
                                public void run() {
                                        loading();
                                }
                        }.start();

                        aboveMedia.readCsvAndTransform(new AboveMedia());
                        belowMedia.readCsvAndTransform(new BelowMedia());
                        this.loading = false;
                        clearConsole();

                } else if (digito == 0) {
                        clearConsole();
                } else {
                        System.out.printf("Valor incorreto, tente novamente!\n");

                        try {
                                Thread.sleep(1500);
                        } catch (InterruptedException ex) {
                        }

                        clearConsole();
                }
        }

        public void sobre() {
                System.out.printf(
                                "O programa AirBNB Data é uma ferramenta feita com o intuito de indicar as estatísticas do AirBNB de Berlin. Que por meio de um arquivo .csv analisa e ordena os dados utilizando os seguintes métodos:\n\t• Selection Sort;\n\t• Insertion Sort;\n\t• Merge Sort;\n\t• Quick Sort;\n\t• QuickSort com Mediana de 3;\n\t• counting;\n\t• HeapSort \n\n\n");
                promptEnterKey();
                clearConsole();
        }

        public void creditos() {
                System.out.printf("Desenvolvedores :\n\n");
                System.out.printf("Gabriel Alves da Costa e Silva - gabriel.costa@aluno.uepb.edu.br\n");
                System.out.printf("Github -> gabrizl\n\n");
                System.out.printf("Jose Axaiel - jose.queiroz@aluno.uepb.edu.br\n");
                System.out.printf("Github -> Axaiel \n\n");
                System.out.printf("Natalia Maite- natalia.guimaraes@aluno.uepb.edu.br\n");
                System.out.printf("Github -> Nataliamgs\n\n");
                promptEnterKey();
                clearConsole();
        }

        public void promptEnterKey() {
                System.out.println("Press \"ENTER\" to continue...");
                try {
                        System.in.read(new byte[2]);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public final static void clearConsole() {
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }

        private void loading() {
                String[] arr = { "/", "|", "\\", "-" };
                int i = 0;
                while (this.loading) {
                        System.out.printf("Processando %s \r", arr[i++]);

                        if (i == arr.length) {
                                i = 0;
                        }

                        try {
                                Thread.sleep(100);
                        } catch (InterruptedException ex) {
                        }
                }
        }

}
