import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {
    public static void main (String[] args) throws IOException{

        // menu Class shows menu.
        menu Menu = new menu();
        menu.showMenu();

        System.out.println("{Menu Options}");

        ArrayList<vaccine> vaccinesList = new ArrayList<>();
        ArrayList<hospital> hospitalList = new ArrayList<>();
        ArrayList<citizen> citizensList = new ArrayList<>();
        ArrayList<slot> slotsList = new ArrayList<>();

        reader.init(System.in);
        int sno = reader.nextInt();

        int HospitalId = 100000;

        while(sno!=8) {

            if (sno == 1) {
                System.out.print("Vaccine Name: ");
                String Vacname = reader.next();
                System.out.print("Number of Doses: ");
                int nofDoses = reader.nextInt();

                // Vaccine Initialized.
                vaccine tempVacc = new vaccine(Vacname, nofDoses);

                int GapBetweenDoses = 0;
                // Gap between Doses is printed and asked from the user only if no. of doses > 1.
                if (nofDoses > 1) {
                    System.out.print("Gap between Doses: ");
                    GapBetweenDoses = reader.nextInt();

                    System.out.println("Vaccine Name: " + Vacname + ",Number of Doses: " + nofDoses + ",Gap between Doses: " + GapBetweenDoses);
                }
                // If no. of doses is 1 then gap between doses is not asked and printed 0.
                else {
                    System.out.println("Vaccine Name: " + Vacname + ",Number of Doses: " + nofDoses + ",Gap between Doses: " + GapBetweenDoses);
                }

                // Gap between Doses is added to temp Vaccine.
                tempVacc.GapBtwDoses(GapBetweenDoses);

                // Vaccine added to Vaccines List.
                vaccinesList.add(tempVacc);
            }
            else if (sno == 2) {
                System.out.print("Hospital Name: ");
                String HospitalName = reader.next();

                System.out.print("PinCode: ");
                int pinCode = reader.nextInt();

                hospital tempHospital = new hospital();
                tempHospital.RegisterHospital(HospitalName, pinCode, HospitalId++);

                System.out.println("Hospital Name: " + HospitalName + ", PinCode: " + pinCode + ", Unique ID: " + tempHospital.hospital_id);

                //tempHospital is added to hospital's list.
                hospitalList.add(tempHospital);
            }
            else if (sno == 3) {
                System.out.print("Citizen Name: ");
                String CitizenName = reader.next();

                System.out.print("Age: ");
                int Age = reader.nextInt();

                System.out.print("Unique ID: ");
                long UniqueID = reader.nextLong();

                System.out.println("Citizen Name: " + CitizenName + ", Age" + Age + ", Unique ID: " + UniqueID);

                if (Age <= 18) {
                    System.out.println("Only above 18 are allowed");
                }

                // Adding information of citizen to citizenList only if they are above 18.
                if (Age > 18) {
                    citizen tempCitizen = new citizen();
                    tempCitizen.RegisterCitizen(CitizenName, Age, UniqueID);
                    citizensList.add(tempCitizen);
                }
            } else if (sno == 4) {
                // 4. Add Slot for Vaccination

                System.out.print("Enter Hospital ID: ");
                int HospitalID = reader.nextInt();

                System.out.print("Enter number of Slots to be added: ");
                int noOfSlots = reader.nextInt();

                for (int i = 0; i < noOfSlots; i++) {

                    System.out.print("Enter Day Number: ");
                    int DayNumber = reader.nextInt();

                    System.out.print("Enter Quantity: ");
                    int VaccineQuantity = reader.nextInt();

                    // Outputs vaccine options.
                    System.out.println("Select Vaccine: ");
                    for (int j = 0; j < vaccinesList.size(); j++) {
                        System.out.println(j + ". " + vaccinesList.get(j).vacName);
                    }
                    int vaccOption = reader.nextInt();
                    String VaccineName = vaccinesList.get(vaccOption).vacName;
                    System.out.println("Slot added by Hospital " + HospitalID + " for Day: " + DayNumber + ", Available Quantity: " + VaccineQuantity + " of Vaccine " + VaccineName);

                    slot tempSlot = new slot();
                    tempSlot.addVaccinationSlot(HospitalID, DayNumber, VaccineQuantity, VaccineName);

                    // Slot Added to Slots List
                    slotsList.add(tempSlot);
                }
            }
            else if (sno == 5) {
                // 5. Book Slot for Vaccination
                System.out.print("Enter patient Unique ID: ");
                long uniqueId = reader.nextLong();

                System.out.println("1. Search by area");
                System.out.println("2. Search by Vaccine");
                System.out.println("3. Exit");

                System.out.print("Enter option: ");
                int option = reader.nextInt();

                if (option == 1) {
                    System.out.print("Enter pincode: ");
                    int pincode = reader.nextInt();

                    // This loop gives the user options of hospital in that pincode.

                    for (int i = 0; i < hospitalList.size(); i++) {
                        if (hospitalList.get(i).HosPincode == pincode) {
                            System.out.println(hospitalList.get(i).hospital_id + " " + hospitalList.get(i).HosName);
                        }
                    }

                    System.out.print("Enter hospital id: ");
                    int hospitaID = reader.nextInt();

                    // This loops prints the vaccination options in that hospital.
                    int outputOption = 0; // intially outputoption is used for different use but later while optimising use has been changed now the variable name is not much related please ignore..


                    for (int i = 0; i < slotsList.size(); i++) {
                        if (slotsList.get(i).HospitalID == hospitaID) {
                            if (slotsList.get(i).VaccineQuantity > 0) {
                                System.out.println(i + "-> Day: " + slotsList.get(i).day + " Available Qty: " + slotsList.get(i).VaccineQuantity + " Vaccine: " + slotsList.get(i).VaccineName);
                                outputOption++;
                            }
                        }
                    }

                    if (outputOption > 0) {
                        System.out.print("Choose slot: ");
                        int vaccinationOption = reader.nextInt();

                        String vaccName = slotsList.get(vaccinationOption).VaccineName;

                        //find citizen name.
                        String citizenName = "...";
                        for (int i = 0; i < citizensList.size(); i++) {
                            if (citizensList.get(i).Unique_ID == uniqueId) {
                                citizenName = citizensList.get(i).CitizenName;
                                citizensList.get(i).vaccination(vaccName);
                            }
                        }

                        if (citizenName != "...") {
                            System.out.println(citizenName + " vaccinatred with " + vaccName);
                        }
                        // After vaccinating the citizen reduce the quantity.
                        if (slotsList.get(vaccinationOption).VaccineQuantity > 0) {
                            slotsList.get(vaccinationOption).VaccineQuantity--;
                        }
                    }
                    else {
                        System.out.println("No slots available.");
                    }

                }
                else if (option == 2) {
                    //Search by Vaccine
                    System.out.print("Enter Vaccine Name: ");
                    String VaccineName = reader.next();

                    for (int i = 0; i < slotsList.size(); i++) {
                        if (VaccineName == slotsList.get(i).VaccineName){
                            int hid = slotsList.get(i).HospitalID;
                            for (int j = 0; j < hospitalList.size(); j++) {
                                if (hospitalList.get(j).hospital_id==hid){
                                    System.out.println(hid+" "+hospitalList.get(j).HosName);
                                }
                            }
                        }
                    }

                    System.out.print("Enter Hospital ID: ");
                    int hospitaID = reader.nextInt();

                    int noOfOptions = 0;
                    for (int i = 0; i < slotsList.size(); i++) {
                        if (slotsList.get(i).HospitalID == hospitaID) {
                            if (slotsList.get(i).VaccineQuantity > 0) {
                                System.out.println(i + "-> Day: " + slotsList.get(i).day + " Available Qty: " + slotsList.get(i).VaccineQuantity + " Vaccine: " + slotsList.get(i).VaccineName);
                                noOfOptions++;
                            }
                        }
                    }
                    /*
                    for (int i = 0; i < slotsList.size(); i++) {
                        if (slotsList.get(i).VaccineName == VaccineName) {
                            if (slotsList.get(i).VaccineQuantity > 0) {
                                System.out.println(i + "-> Day: " + slotsList.get(i).day + " Available Qty: " + slotsList.get(i).VaccineQuantity + " Vaccine: " + slotsList.get(i).VaccineName);
                                noOfOptions++;
                            }
                        }
                    }
                    */

                    if (noOfOptions > 0) {
                        System.out.println("choose slot: ");
                        int vaccinationOption = reader.nextInt();

                        //find citizen name.
                        String citizenName = "...";
                        for (int i = 0; i < citizensList.size(); i++) {
                            if (citizensList.get(i).Unique_ID == uniqueId) {
                                citizenName = citizensList.get(i).CitizenName;
                                citizensList.get(i).vaccination(VaccineName);
                            }
                        }

                        if (citizenName != "...") {
                            System.out.println(citizenName + " vaccinatred with " + VaccineName);
                        }
                        // After vaccinating the citizen reduce the quantity.
                        if (slotsList.get(vaccinationOption).VaccineQuantity > 0) {
                            slotsList.get(vaccinationOption).VaccineQuantity--;
                        }
                    }
                    else {
                        System.out.println("No slots available.");
                    }
                }
                else if (option == 3) {
                    return;
                }
            }
            else if (sno == 6) {
                //6. List all slots for a hospital
                System.out.print("Enter Hospital Id: ");
                int hospitalId = reader.nextInt();

                // to find the slot in that hospital
                for (int i = 0; i < slotsList.size(); i++) {
                    if (slotsList.get(i).HospitalID == hospitalId) {
                        if (slotsList.get(i).VaccineQuantity > 0) {
                            System.out.println("Day: " + slotsList.get(i).day + " Vaccine: " + slotsList.get(i).VaccineName + " Available Qty: " + slotsList.get(i).VaccineQuantity);
                        }
                    }
                }
            }
            else if (sno == 7) {
                // 7. Check Vaccination Status

                System.out.print("Enter Patient ID: ");
                long UniqueId = reader.nextLong();
                //initializing vaccination status.

                String Status = "Citizen REGISTERED";

                // Find citizen id.
                for (int i = 0; i < citizensList.size(); i++) {
                    if (citizensList.get(i).Unique_ID == UniqueId) {
                        String VaccineName = citizensList.get(i).typeOfVaccine;
                        if (VaccineName==null){
                            System.out.println("Citizen REGISTERED");
                            break;
                        }
                        //find vaccine name
                        for (int j = 0; j < vaccinesList.size(); j++) {

                            if (vaccinesList.get(j).vacName == VaccineName) {
                                int reqnOfDoses = vaccinesList.get(j).nOfDoses;

                                if (citizensList.get(i).noofDosesofVaccine < reqnOfDoses) {
                                    Status = "PARTIALLY VACCINATED";
                                    System.out.println(Status);
                                    System.out.println("Vaccine Given: " + citizensList.get(i).typeOfVaccine);
                                    System.out.println("Number of Doses given: " + citizensList.get(i).noofDosesofVaccine);
                                    int sum = vaccinesList.get(j).GapBtwDoses+1;
                                    System.out.println("Next Dose due date: " + sum);
                                }
                                else if (citizensList.get(i).noofDosesofVaccine == reqnOfDoses) {
                                    Status = "FULLY VACCINATED";
                                    System.out.println(Status);
                                    System.out.println("Vaccine Given: " + citizensList.get(i).typeOfVaccine);
                                    System.out.println("Number of Doses given: " + citizensList.get(i).noofDosesofVaccine);
                                }
                                else if (citizensList.get(i).noofDosesofVaccine == 0) {
                                    System.out.println("Citizen REGISTERED");
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("---------------------------------");
            System.out.println("{Menu Options}");
            sno = reader.nextInt();
        }
        System.out.println("{End of Test case}");
    }
}

class menu{
    public static void showMenu(){
        System.out.println("CoWin Portal initialized....");
        System.out.println("---------------------------------");
        System.out.println("1. Add Vaccine");
        System.out.println("2. Register Hospital");
        System.out.println("3. Register Citizen");
        System.out.println("4. Add Slot for Vaccination");
        System.out.println("5. Book Slot for Vaccination");
        System.out.println("6. List all slots for a hospital");
        System.out.println("7. Check Vaccination Status");
        System.out.println("8. Exit");
        System.out.println("---------------------------------");
    }
}

class slot{
    public int day;
    public int HospitalID;
    public String VaccineName;
    public int VaccineQuantity;

    public void addVaccinationSlot(int HospitalID,int Day,int Quantity,String VaccineName){
        this.day = Day;
        this.HospitalID = HospitalID;
        this.VaccineQuantity = Quantity;
        this.VaccineName = VaccineName;
    }

}

class vaccine{
    public String vacName;
    public int nOfDoses;
    public int GapBtwDoses;

    public vaccine(String vacName,int nOfDoses){
        this.vacName = vacName;
        this.nOfDoses = nOfDoses;
    }

    public void GapBtwDoses(int GapBtwDoses){
        this.GapBtwDoses = GapBtwDoses;
    }
}

class hospital{
    public int hospital_id;
    public String HosName;
    public int HosPincode;
    /*
    Can also use constructor of the class public hospital()
    and take input of the class but Used method RegisterHospital to ensure a clean code
    */
    public void RegisterHospital(String HosName,int HosPincode,int hospital_id){
        this.HosName = HosName;
        this.HosPincode = HosPincode;
        this.hospital_id = hospital_id;
    }
}

class citizen{
    public long Unique_ID;
    public int Age;
    public String CitizenName;
    public int noofDosesofVaccine;
    public String typeOfVaccine;

    /*
    Can also use constructor of the class public citizen()
    and take input of the class but Used method RegisterCitizen to ensure a clean code
    */
    public void RegisterCitizen(String CitizenName,int Age,long UniqueID){
        this.Age = Age;
        this.CitizenName = CitizenName;
        this.Unique_ID = UniqueID;
    }

    public void vaccination(String VaccineName){
        noofDosesofVaccine++;
        this.typeOfVaccine = VaccineName;
    }

}

class reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    // call this method to initialize reader for InputStream
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    // get next word
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
        return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static long nextLong() throws IOException{
        return Long.parseLong(next());
    }
}
