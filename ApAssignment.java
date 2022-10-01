package Assignment;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class ApAssignment {

    public static void main(String[] args) {
//        Vaccine v1 = new Vaccine();
//        Hospital h1 = new Hospital();
//        Citizen c1 = new Citizen();
        Slots s1 = new Slots();
        ArrayList<Vaccine> vaccine = new ArrayList<Vaccine>();
        ArrayList<Hospital> hospital = new ArrayList<Hospital>();
        ArrayList<Citizen> citizen = new ArrayList<Citizen>();
        ArrayList<Slots> slots = new ArrayList<Slots>();
//        vac_list.add(v2);

//        port.addVaccine(v1);

        int input = 0;
        while(input!=8){
            System.out.println("This is a simulation for covid vaccine portal");
            System.out.println("----------------------------------------");
            System.out.println("CoWin Portal initialized....");
            System.out.println("----------------------------------------");
            System.out.println("1. Add Vaccine");
            System.out.println("2. Register Hospital");
            System.out.println("3. Register Citizen");
            System.out.println("4. Add Slot for Vaccination");
            System.out.println("5. Book slot for Vaccination");
            System.out.println("6. List all slots for Vaccination");
            System.out.println("7. Check Vaccination status");
            System.out.println("8. Exit");
            System.out.println("----------------------------------------");
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();

            if (input == 1){
                Vaccine v1 = new Vaccine();
                Scanner sc1 = new Scanner(System.in);


                System.out.println("");
                System.out.println("----------------------------------------");
                System.out.println("1");

                System.out.print("Vaccine Name: ");
                String vname = sc1.next();
                System.out.print("Number of Doses: ");
                int vdose = sc1.nextInt();
                System.out.print("Gap between Doses: ");
                int gdose = sc1.nextInt();
                v1.setterVacName(vname, vdose, gdose);
                vaccine.add(v1);
                System.out.println("Vaccine Name: " + vname + ", " + "Number of Doses: " + vdose + ", " + "Gap Between Doses: " + gdose );
                System.out.println("\n");

//                for(int i = 0; i< vaccine.size(); i++){
//                    System.out.println(vaccine.get(i).getVac_name());
//                }
            }
            else if(input == 2){
                Hospital h1 = new Hospital();
                Scanner sc2 = new Scanner(System.in);

                System.out.println("");
                System.out.println("----------------------------------------");
                System.out.println("2");

                System.out.print("Hospital Name: ");
                String hname = sc2.next();
                System.out.print("PinCode: ");
                int hpin = sc2.nextInt();
                int hunique = hpin + 1392;
                h1.setterHospital(hname, hpin, hunique);
                hospital.add(h1);

                System.out.println("Hospital Name: " + hname + ", " + "PinCode: " + hpin + ", " + "Unique ID: " +  hunique);
                System.out.println("\n");


            }
            else if(input == 3){
                Citizen c1 = new Citizen();
                Scanner sc3 = new Scanner(System.in);

                System.out.println("");
                System.out.println("----------------------------------------");
                System.out.println("3");

                System.out.print("Citizen Name: ");
                String cname = sc3.next();
                System.out.print("Age: ");
                int cage = sc3.nextInt();
                System.out.print("Unique ID: ");
                int cId = sc3.nextInt();

                c1.setStatus("REGISTERED");
//                System.out.println(c1.getStatus());
                c1.setterCitizen(cname, cage, cId);


                System.out.println("Citizen Name: " + cname + ", Age: " + cage + ", Unique ID: " + cId);
                if(cage>=18){
                    citizen.add(c1);
                }else{
                    System.out.println("Only above 18 are allowed");
                }
            }
            else if (input == 4){
                Scanner sc4 = new Scanner(System.in);
                boolean flag4 = false;

                System.out.println("");
                System.out.println("----------------------------------------");
                System.out.println("4");

                System.out.print("Enter Hospital ID: ");
                int hospital_id = sc4.nextInt();

                for(int i = 0; i< hospital.size();i++){
                    if(hospital.get(i).getUniqueId() != hospital_id){
                        System.out.println("Hospital Not Registered!");
                        flag4 = true;
                        break;
                    }
                }
                if(flag4 == false){
                    System.out.print("Enter number of slots to be added: ");
                    int noOfSlots = sc4.nextInt();
                    for(int j = 0; j< noOfSlots;j++){
                        System.out.print("Enter Day Number: ");
                        int day = sc4.nextInt();
                        System.out.print("Enter Quantity: ");
                        int quantity = sc4.nextInt();
                        System.out.println("Select Vaccine: ");
                        for(int k = 0; k < vaccine.size(); k++){
                            System.out.println(k + "." + " " + vaccine.get(k).getVac_name());
                        }
                        int selected_vaccine = sc4.nextInt();
                        for(int l = 0; l< hospital.size(); l++){
                            if(hospital.get(l).getUniqueId() == hospital_id){
                                String vaccine_name = vaccine.get(selected_vaccine).getVac_name();
                                hospital.get(l).addSlot(day, quantity, vaccine_name, hospital_id);
                                break;
                            }
                        }
                        System.out.println("Slot added by Hospital: " + hospital_id + " for Day: " + day + " , Available Quantity: " + quantity + " of Vaccine " + vaccine.get(selected_vaccine).getVac_name());
                    }

                }

            }
            else if(input == 5){
                Scanner sc5 = new Scanner(System.in);

                System.out.println("Enter patient Unique ID: ");
                int patient_id = sc5.nextInt();

                System.out.println("1. Search by Area");
                System.out.println("2. Search by Vaccine");
                System.out.println("3. Exit");
                System.out.print("Enter Option: ");
                int enter_option = sc5.nextInt();

                if(enter_option == 1){
                    System.out.print("Enter PinCode:");
                    int enter_pincode = sc5.nextInt();
                    for(int i = 0; i < hospital.size();i++){
                        if(hospital.get(i).getPinCode() == enter_pincode){
                            System.out.println(hospital.get(i).getUniqueId() + " " + hospital.get(i).getName());
                        }
                    }
                    System.out.print("Enter Hospital id");
                    int enter_hospita_id = sc5.nextInt();
                    for(int ii = 0; ii< hospital.size(); ii++){
                        if(hospital.get(ii).getUniqueId() == enter_hospita_id){
                            for(int k = 0; k < hospital.get(ii).getReg_slot().size();k++){
                                int display_date = hospital.get(ii).getReg_slot().get(k).getDayNumber();
                                int display_quantity = hospital.get(ii).getReg_slot().get(k).getQuantity();
                                String display_vac_name = hospital.get(ii).getReg_slot().get(k).getVacName();
                                System.out.println(k + "->" + "Day: " + display_date + " Available Quantity: " + display_quantity + " Vaccine: " + display_vac_name);
                            }
                        }
                    }
                    System.out.print("Choose Slot: ");
                    int input_slot = sc5.nextInt();
                    String receivedVaccine;
                    int vaccineDate;
                    int hospital_id ;
                    int updated_quantity;
                    int flag = 0;

                    for(int a = 0; a< hospital.size();a++){
                        if(hospital.get(a).getUniqueId() == enter_hospita_id){
                            flag = a;
                            receivedVaccine = hospital.get(a).getReg_slot().get(input_slot).getVacName();
                            updated_quantity = hospital.get(a).getReg_slot().get(input_slot).getQuantity() ;
                            hospital_id = hospital.get(a).getReg_slot().get(input_slot).getHospitalId() ;
                            vaccineDate = hospital.get(a).getReg_slot().get(input_slot).getDayNumber();
                            updated_quantity = updated_quantity - 1;
                            hospital.get(a).getReg_slot().get(input_slot).setterSlots(vaccineDate, updated_quantity, receivedVaccine, hospital_id);
                            break;
                        }
                    }

                    receivedVaccine = hospital.get(flag).getReg_slot().get(input_slot).getVacName();
                    int presentDay = hospital.get(flag).getReg_slot().get(input_slot).getDayNumber();

                    for(int m = 0; m < citizen.size(); m++){
                        if(citizen.get(m).getId() == patient_id){
                            for(int n = 0; n< vaccine.size();n++){
                                if(vaccine.get(n).getVac_name() == receivedVaccine){
                                    int gapDose = vaccine.get(n).getGapDoses();
                                    citizen.get(m).setDue_date(presentDay + gapDose);
                                    int dosecount = citizen.get(n).getDose_received();
                                    citizen.get(m).setDose_received(dosecount + 1);
                                    if(citizen.get(m).getDose_received() < vaccine.get(n).getDoses()){
                                        citizen.get(m).setStatus("PARTIALLY VACCINATED");
                                    }
                                    else if(citizen.get(m).getDose_received() == vaccine.get(n).getDoses()){
                                        citizen.get(m).setStatus("FULLY VACCINATED");
                                    }


                                }
                            }
                            System.out.println(citizen.get(m).getCname() + "vaccinated with " + receivedVaccine);
                        }
                    }
                }
                else if(enter_option == 2){
                    System.out.print("Enter Vaccine Name: ");
                    String enter_vaccine_name = sc5.next();
                    for(int q = 0; q < hospital.size();q++){
                        for(int w = 0; q < hospital.get(q).getReg_slot().size();w++){
                            if(enter_vaccine_name == hospital.get(q).getReg_slot().get(w).getVacName()){
                                System.out.println(hospital.get(q).getUniqueId() + " " + hospital.get(q).getName());

                            }
                        }
                    }
                    System.out.print("Enter Hospital id");
                    int enter_hospita_id = sc5.nextInt();
                    for(int ii = 0; ii< hospital.size(); ii++){
                        if(hospital.get(ii).getUniqueId() == enter_hospita_id){
                            for(int k = 0; k < hospital.get(ii).getReg_slot().size();k++){
                                int display_date = hospital.get(ii).getReg_slot().get(k).getDayNumber();
                                int display_quantity = hospital.get(ii).getReg_slot().get(k).getQuantity();
                                String display_vac_name = hospital.get(ii).getReg_slot().get(k).getVacName();
                                System.out.println(k + "->" + "Day: " + display_date + " Available Quantity: " + display_quantity + " Vaccine: " + display_vac_name);
                            }
                        }
                    }
                    System.out.print("Choose Slot: ");
                    int input_slot = sc5.nextInt();
                    String receivedVaccine;
                    int vaccineDate;
                    int hospital_id ;
                    int updated_quantity;
                    int flag = 0;

                    for(int a = 0; a< hospital.size();a++){
                        if(hospital.get(a).getUniqueId() == enter_hospita_id){
                            flag = a;
                            receivedVaccine = hospital.get(a).getReg_slot().get(input_slot).getVacName();
                            updated_quantity = hospital.get(a).getReg_slot().get(input_slot).getQuantity() ;
                            hospital_id = hospital.get(a).getReg_slot().get(input_slot).getHospitalId() ;
                            vaccineDate = hospital.get(a).getReg_slot().get(input_slot).getDayNumber();
                            updated_quantity = updated_quantity - 1;
                            hospital.get(a).getReg_slot().get(input_slot).setterSlots(vaccineDate, updated_quantity, receivedVaccine, hospital_id);
                            break;
                        }
                    }

                    receivedVaccine = hospital.get(flag).getReg_slot().get(input_slot).getVacName();
                    int presentDay = hospital.get(flag).getReg_slot().get(input_slot).getDayNumber();

                    for(int m = 0; m < citizen.size(); m++){
                        if(citizen.get(m).getId() == patient_id){
                            for(int n = 0; n< vaccine.size();n++){
                                if(vaccine.get(n).getVac_name() == receivedVaccine){
                                    int gapDose = vaccine.get(n).getGapDoses();
                                    citizen.get(m).setDue_date(presentDay + gapDose);
                                    int dosecount = citizen.get(n).getDose_received();
                                    citizen.get(m).setDose_received(dosecount + 1);
                                    if(citizen.get(m).getDose_received() < vaccine.get(n).getDoses()){
                                        citizen.get(m).setStatus("PARTIALLY VACCINATED");
                                    }
                                    else if(citizen.get(m).getDose_received() == vaccine.get(n).getDoses()){
                                        citizen.get(m).setStatus("FULLY VACCINATED");
                                    }


                                }
                            }
                            System.out.println(citizen.get(m).getCname() + "vaccinated with " + receivedVaccine);
                        }
                    }
                }
                else if(enter_option == 3){
                    System.out.println("");
                }




            }

            else if(input == 6){
                Scanner sc6 = new Scanner(System.in);
                boolean flag6 = false;

                System.out.println("");
                System.out.println("----------------------------------------");
                System.out.println("");

                System.out.println("Enter Hospital ID: ");
                int display_hospital = sc6.nextInt();

                for(int i = 0; i< hospital.size();i++){
                    if(hospital.get(i).getUniqueId() != display_hospital){
                        System.out.println("Hospital Not Registered or no Slot Booked!");
                        flag6 = true;
                        break;
                    }
                }

                if(flag6 = false){
                    for(int j = 0; j < hospital.size(); j++){
                        if(hospital.get(j).getUniqueId() == display_hospital){
                            hospital.get(j).display_slots();
                        }
                    }
                }
            }
            else if(input == 7){
                Scanner sc7 = new Scanner(System.in);
                System.out.print("Enter Patient ID: ");
                int enter_citizen_id = sc7.nextInt();
                for(int i = 0; i< citizen.size();i++){
                    if(citizen.get(i).getId() == enter_citizen_id){
                        if(citizen.get(i).getStatus() == "FULLY VACCINATED"){
                            System.out.println("FULLY VACCINATED");
                            System.out.println("Vaccine Given: " + citizen.get(i).getVaccine_received());
                            System.out.println("Number of Doses Given: " + citizen.get(i).getDose_received());
                        }
                        else if(citizen.get(i).getStatus() == "PARTIALLY VACCINATED"){
                            System.out.println("PARTIALLY VACCINATED");
                            System.out.println("Vaccine Given: " + citizen.get(i).getVaccine_received());
                            System.out.println("Number of Doses Given: " + citizen.get(i).getDose_received());
                            System.out.println("Next Dose due date: " + citizen.get(i).getDue_date());
                        }
                        else if(citizen.get(i).getStatus() == "REGISTERED"){
                            System.out.println("Citizen REGISTERED");
                        }
                    }
                }

            }



        }
    }
}

class Vaccine{
    private String vac_name;
    private int doses, gapOfDoses;

    public void setterVacName(String vac_name, int doses, int gapOfDoses){
        this.vac_name = vac_name;
        this.doses = doses;
        this.gapOfDoses = gapOfDoses;
    }
    public String getVac_name(){
        return vac_name;
    }
    public int getDoses(){
        return doses;
    }
    public int getGapDoses(){
        return gapOfDoses;
    }

}

class Hospital{
    private String name;
    private int pinCode;
    private int uniqueId;

    public void setterHospital(String name, int pinCode, int uniqueId){
        this.name = name;
        this.pinCode = pinCode;
        this.uniqueId = uniqueId;
    }

    public String getName(){
        return name;
    }
    public int getPinCode(){
        return pinCode;
    }
    public int getUniqueId(){
        return uniqueId;
    }

    private ArrayList<Slots> reg_slot = new ArrayList<Slots>();
    public ArrayList<Slots> getReg_slot(){
        return getReg_slot();
    }

//    public void makeSlots(String name, int pinCode, int uniqueId){
//        this.name = name;
//        this.pinCode = pinCode;
//        this.uniqueId = uniqueId;
//    }

    public void addSlot(int day, int quantity, String vaccineName, int hospitalId){
        Slots s1 = new Slots();
        s1.setterSlots(day, quantity, vaccineName, hospitalId);
        reg_slot.add(s1);
    }

    public void display_slots(){
        for(int h = 0; h< reg_slot.size();h++){
            String dName = reg_slot.get(h).getVacName();
            int dQuantity = reg_slot.get(h).getQuantity();
            int dDay = reg_slot.get(h).getDayNumber();
            System.out.println("Day: "+ dDay+ " Vaccine: " + dName + " Available Quantity: "+ dQuantity);
        }
    }

}

class Citizen{
    private String cname;
    private int id;
    private String status;
    private int age;
    private int dose_received = 0;
    private String vaccine_received;
    private int due_date;

    public void setDose_received(int dose_received){
        this.dose_received = dose_received;
    }
    public void setVaccine_received(String vaccine_recieved){
        this.vaccine_received = vaccine_recieved;
    }
    public void setDue_date(int due_date){
        this.due_date = due_date;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
    public int getDose_received(){
        return dose_received;
    }
    public String getVaccine_received(){
        return vaccine_received;
    }
    public int getDue_date(){
        return due_date;
    }

    public void setterCitizen(String cname, int age, int id){
        this.cname = cname;
        this.age = age;
        this.id = id;
    }

    public String getCname(){
        return cname;
    }
    public int getId(){
        return id;
    }
    public int getAge(){
        return age;
    }


}
class Slots {
    private int hospitalId;
    private String vacName;
    private int dayNumber;
    private int quantity;

    public void setterSlots(int dayNumber, int quantity, String vacName, int hospitalId){
        this.dayNumber = dayNumber;
        this.quantity = quantity;
        this.vacName = vacName;
        this.hospitalId = hospitalId;
    }

    public String getVacName(){
        return vacName;
    }
    public int getHospitalId(){
        return hospitalId;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getDayNumber(){
        return dayNumber;
    }
//
//    private ArrayList<Citizen> reg_citizen = new ArrayList<>();
//
//    public ArrayList<Citizen> getReg_citizen() {
//        return reg_citizen;
//    }


}
