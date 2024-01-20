
public class Main {
    public static void main(String[] args) {
        //Przeczytanie pliku i zrobienie tablicy o odpowiednich rozmiarach
        //Sprawdzenie czy dana liczba może być zapisana w notacji elfowej
        //Generacja kolejnych liczb notacji e, rozbicie ich na składniki i wymnożenie ich na wszystkie możliwe sposoby


//        for (int i = 1; i < 10_000; i++) {
//            System.out.println(i + " " + ENumber.findNumber(i));
//        }


//        ENumber number;
//        for (int i = 16; i < 10000; i += 16) {
//            if((number = ENumber.findNumber(i)).getIntVal() != -1) {
//                System.out.println(number);
//            }
//
//        }

        for (int i = 1; i < 10_000; i++) {
            if(i % 5 != 0 && i % 16 != 0) {
                ENumber eNumber;
                if((eNumber = ENumber.findNumber(i)).getIntVal() == -1) {
                    System.out.println(i);
                }
            }
        }



    }
}
