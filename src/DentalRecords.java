
import java.util.Scanner;
public class DentalRecords {

    static final Scanner keyboard = new Scanner(System.in);
    public static int MAX_NUMBER_OF_FAMILY_MEMBERS = 6;
    public static int MIN_NUMBER_OF_FAMILY_MEMBERS = 0;
    public static int MAX_NUMBER_OF_TEETH = 8;
    public static int MIN_NUMBER_OF_ROWS = 2;


    public static void main(String[] args) {
        final Scanner scnr = new Scanner(System.in);


        //-------------------------------initial family input----------------------------------------------------------
        int numberOfFamilyMembers;

        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");


        System.out.print("Please enter number of people in the family : ");
        numberOfFamilyMembers = keyboard.nextInt();
        if (numberOfFamilyMembers <= MIN_NUMBER_OF_FAMILY_MEMBERS || numberOfFamilyMembers >= MAX_NUMBER_OF_FAMILY_MEMBERS) {
            do {
                System.out.print("Invalid number of people, try again : ");
                numberOfFamilyMembers = keyboard.nextInt();
            } while (numberOfFamilyMembers < MIN_NUMBER_OF_FAMILY_MEMBERS || numberOfFamilyMembers >= MAX_NUMBER_OF_FAMILY_MEMBERS);
        }
        NameAndTeethInput(numberOfFamilyMembers);


        String[] memberName = new String[numberOfFamilyMembers];
        char[][][] familyTeethCount = new char[numberOfFamilyMembers][MIN_NUMBER_OF_ROWS][MAX_NUMBER_OF_TEETH];
        String upperTeeth = String.valueOf(familyTeethCount);
        String lowerTeeth = String.valueOf(familyTeethCount);
        int index = 0;
        int row = 0;
        DentalRecordsMenu(String.valueOf(upperTeeth), String.valueOf(lowerTeeth), memberName, familyTeethCount, index, row, numberOfFamilyMembers  );

    }
//----------------------------Name Input and Teeth Input---------------------------------------------------------

        public static char[][][] NameAndTeethInput (int numberOfFamilyMembers){
            final Scanner scnr = new Scanner(System.in);
            String[] memberName = new String[numberOfFamilyMembers];
            char[][][] familyTeethCount = new char[numberOfFamilyMembers][MIN_NUMBER_OF_ROWS][MAX_NUMBER_OF_TEETH];
            String upperTeeth;
            int row;
            int column;

            String lowerTeeth;
            for (int index = 0; index < numberOfFamilyMembers; index++) {
                System.out.print("Please enter the name for family member " + (index + 1) + " : ");
                memberName[index] = scnr.nextLine();

                System.out.print("Please enter the uppers for " + memberName[index] + ": ");
                upperTeeth = scnr.nextLine();
                upperTeeth = upperTeeth.toUpperCase();


                for (column = 0; column < upperTeeth.length(); column++) {
                    familyTeethCount[index][0][column] = upperTeeth.charAt(column);
                }


                for (row = 0; row < upperTeeth.length(); row++) {
                    while (upperTeeth.charAt(row) != 'I' && upperTeeth.charAt(row) != 'B' && upperTeeth.charAt(row) != 'M') {
                        System.out.print("Invalid teeth types, try again : ");
                        upperTeeth = scnr.nextLine();
                    }

                }

                while (upperTeeth.length() > MAX_NUMBER_OF_TEETH) {
                    System.out.print("Too many teeth, try again : ");
                    upperTeeth = scnr.nextLine();
                }

                System.out.print("Please enter the lowers for " + memberName[index] + ": ");
                lowerTeeth = scnr.nextLine();
                lowerTeeth = lowerTeeth.toUpperCase();

                for (column = 0; column < upperTeeth.length(); column++) {
                    familyTeethCount[index][1][column] = upperTeeth.charAt(column);
                }

                for (column = 0; column < lowerTeeth.length(); column++) {
                    while (lowerTeeth.charAt(column) != 'I' && lowerTeeth.charAt(column) != 'B' && lowerTeeth.charAt(column) != 'M') {
                        System.out.print("Invalid teeth types, try again : ");
                        lowerTeeth = scnr.nextLine();
                    }
                }

            }
            return familyTeethCount;


        }


        //------------------------------------Dental Records Menu----------------------------------------------------

        public static double DentalRecordsMenu (String upperTeeth, String lowerTeeth, String[]memberName, char[][][] familyTeethCount, int index, int row, int numberOfFamilyMembers){
            final Scanner scnr = new Scanner(System.in);
            int toothNumber;


            System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it     : ");
            char dentalRecordsMenuInput = ((scnr.next()).toUpperCase()).charAt(0);

            while (dentalRecordsMenuInput != 'X') {

                System.out.println(upperTeeth);
                switch (dentalRecordsMenuInput) {

                    case 'P':
                        System.out.println("Uppers: ");
                        for (row = 0; row < upperTeeth.length(); row++) {
                            System.out.print((row + 1) + ":" + upperTeeth.charAt(row) + " ");
                        }
                        System.out.println("\nLowers: ");
                        for (row = 0; row < lowerTeeth.length(); row++) {
                            System.out.print((row + 1) + ":" + lowerTeeth.charAt(row) + " ");
                        }
                        System.out.println();
                        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it : ");
                        break;

                    case 'E':

                        System.out.print("Which family member : ");
                        String[] extractionPatient = new String[numberOfFamilyMembers];
                        for (index = 0; index < numberOfFamilyMembers; index++)
                           extractionPatient[index] = scnr.nextLine();
                        if (memberName[index].equals(extractionPatient[index])) {

                            System.out.println("Which tooth layer (U)pper or (L)ower");
                            char extractLevel = ((scnr.next()).toUpperCase()).charAt(0);
                            while (extractLevel != 'U' || extractLevel != 'L') {
                                System.out.println("Invalid layer, try again : ");
                                extractLevel = ((scnr.next()).toUpperCase()).charAt(0);
                            }
                            System.out.println("Which tooth number : ");
                            toothNumber = keyboard.nextInt();
                            if (toothNumber > familyTeethCount[index][row].length || toothNumber < familyTeethCount[index][row].length) {
                                System.out.print("Invalid tooth number, try again : ");
                            } else if (familyTeethCount[index][row][toothNumber - 1] == 'M') {
                                System.out.print("Missing tooth, try again : ");
                            }

                        } else {
                            System.out.print("Invalid family member, try again : ");
                        }
                        break;

                    case 'R':
                        int incisors = 8;
                        int bicuspids = 21;
                        int missingTeeth = 9;



                        double calculateRootLocation = Math.pow(bicuspids, 2) - (4 * (incisors * (-missingTeeth)));
                        if(calculateRootLocation > 0){
                           double firstRootLocation = -bicuspids + Math.sqrt(calculateRootLocation)/(2 * incisors );
                            System.out.println("One root canal at : " + firstRootLocation);
                            double secondRootLocation = -bicuspids - Math.sqrt(calculateRootLocation)/(2 * incisors );
                            System.out.println("Another root canal at : " + secondRootLocation);
                        }




                        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it : ");
                        break;

                    default:
                        System.out.println("Invalid menu option, try again : ");
                        System.out.println("(P)rint, (E)xtract, (R)oot, e(X)it");
                }
                dentalRecordsMenuInput = ((scnr.next()).toUpperCase()).charAt(0);
            }
            System.out.print("Exiting the Floridian Tooth Records :-)");
            return 0.0;
        }
    }








