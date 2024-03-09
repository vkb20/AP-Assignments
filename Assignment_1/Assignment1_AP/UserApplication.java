/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author varun
 */

public class UserApplication {
    
    private static int admittedPatients;
    ArrayList<Patient> patientList = new ArrayList<>();
    ArrayList<HealthCare> instituteList = new ArrayList<>();
    HashMap<HealthCare, ArrayList<Patient>> patientInHealthCare = new HashMap<>();
 
    UserApplication(){
    }
    
    public void setPatientList(Patient newPatient){
        patientList.add(newPatient);
    }
    
    public void setHealthCareList(HealthCare newHealthCare){
        instituteList.add(newHealthCare);
    }
    
    public int getAdmittedPatients(){
        return admittedPatients;
    }
    
    public HashMap<HealthCare, ArrayList<Patient>> getPatientInHealthCare(){
        return patientInHealthCare;
    }
    
    public void admitPatients(HealthCare _healthCare){
        ArrayList<Patient> patientsToBeAdmitted = new ArrayList<>();
        for(int i=0;i<patientList.size();i++){
            int BedsAvailable = _healthCare.getAvailableBeds();
            if(BedsAvailable>0){
                if(patientList.get(i).getPatientAdmission().compareTo("Not Admitted")==0){
                    if(patientList.get(i).getOxygenLevel()>=_healthCare.getMinOxygenLevel()){
                        patientsToBeAdmitted.add(patientList.get(i));
                        _healthCare.setAvailableBeds(BedsAvailable-1);
                        patientList.get(i).setNameOfHealthCare(_healthCare.getInstituteName());
                        patientList.get(i).setPatientAdmission("Admitted");
                        admittedPatients+=1;
                    }
                }
            }
        }
        for(int i=0;i<patientList.size();i++){
            int BedsAvailable = _healthCare.getAvailableBeds();
            if(BedsAvailable>0){
                if(patientList.get(i).getPatientAdmission().compareTo("Not Admitted")==0){
                    if(patientList.get(i).getTemperature()<=_healthCare.getMaxTemp()){
                        patientsToBeAdmitted.add(patientList.get(i));
                        _healthCare.setAvailableBeds(BedsAvailable-1);
                        patientList.get(i).setNameOfHealthCare(_healthCare.getInstituteName());
                        patientList.get(i).setPatientAdmission("Admitted");
                        admittedPatients+=1;
                    }
                }
            }
        }
        if(_healthCare.getAvailableBeds()==0){
            _healthCare.setAdmissionStatus("CLOSED");
        }
        
        patientInHealthCare.put(_healthCare, patientsToBeAdmitted);
        
    }
    
    public void removeAdmittedPatients(){
        int i=0;
        System.out.println("Account ID removed of admitted patients ");
        while(i<patientList.size()){
            if(patientList.get(i).getPatientAdmission().compareTo("Admitted")==0){
                System.out.println(patientList.get(i).getID());
                patientList.remove(i);
            }
            else{
                i+=1;
            }
        }
        
    }
    
    public void removeClosedInstitutes(){
        int i=0;
        System.out.println("Accounts removed of Institute whose admission is closed ");
        while(i<instituteList.size()){
            if(instituteList.get(i).getAdmissionStatus().compareTo("CLOSED")==0){
                System.out.println(instituteList.get(i).getInstituteName());
                patientInHealthCare.remove(instituteList.get(i));
                instituteList.remove(i);
            }
            else{
                i+=1;
            }
        }   
    }
    
    public int patientInCamps(int n){
        return n-admittedPatients;
    }
    
    public int openedInstitutes(){
        int c=0;
        for(int i=0;i<instituteList.size();i++){
            if(instituteList.get(i).getAdmissionStatus().compareTo("OPEN")==0){
                c+=1;
            }
        }
        return c;
    }
    
    public void healthCareDetails(String instituteName){
        boolean flag = false;
        for(int i=0;i<instituteList.size();i++){
            if(instituteList.get(i).getInstituteName().compareTo(instituteName)==0){
                HealthCare _healthCare = instituteList.get(i);
                System.out.println(_healthCare.getInstituteName());
                System.out.println("Temperature should be <= " + _healthCare.getMaxTemp());
                System.out.println("Oxygen Level should be >= " + _healthCare.getMinOxygenLevel());
                System.out.println("Number of available beds - " + _healthCare.getAvailableBeds());
                System.out.println("Admission Status - " + _healthCare.getAdmissionStatus());  
                flag=true;
            }
        }
        if(flag==false){
            System.out.println("No Health Care institute is present in software with the name " + instituteName);
        }
    }
    
    public void patientDetails(int ID){
        boolean flag = false;
        for(int i=0;i<patientList.size();i++){
            if(patientList.get(i).getID()==ID){
                flag=true;
                Patient _patient = patientList.get(i);
                System.out.println(_patient.getName());
                System.out.println("Temperature is " + _patient.getTemperature());
                System.out.println("Oxygen levels is " + _patient.getOxygenLevel());
                System.out.println("Admission Status - " + _patient.getPatientAdmission());
                if(_patient.getPatientAdmission().compareTo("Admitted")==0){
                    System.out.println("Admitting Institute - " + _patient.getNameOfHealthCare());
                }
                else{
                    System.out.println();
                }
            }
        }
        if(flag==false){
            System.out.println("No patient is present in software with the ID " + ID);
        }
    }
    
    public void displayAllPatients(){
        for(int i=0;i<patientList.size();i++){
            System.out.println(patientList.get(i).getID() + " " + patientList.get(i).getName());
        }
    }
    
    public void patientsInInstitute(String instituteName){
        boolean flag=false;
        for(HealthCare hc : patientInHealthCare.keySet()){
            if(hc.getInstituteName().compareTo(instituteName)==0){
                ArrayList<Patient> _patient = patientInHealthCare.get(hc);
                flag=true;
                for(int i=0;i<_patient.size();i++){
                    System.out.println(_patient.get(i).getName() + ", recovery time is " + _patient.get(i).getRecoveryDays() + " days");
                }
            }
        }
        if(flag==false){
            System.out.println("No Health Care institute is present in software with the name " + instituteName);
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader.init(System.in);
        UserApplication user = new UserApplication();
        
        int numOfPatients = Reader.nextInt();        
        for(int i=0;i<numOfPatients;i++){
            String name = Reader.next();
            float temperature = Reader.nextFloat();
            int oxygenLevel = Reader.nextInt();
            int age = Reader.nextInt();
            Patient newPatient = new Patient(name, temperature, oxygenLevel, age, "Not Admitted");
            user.patientList.add(newPatient);
        }
        while(user.getAdmittedPatients()<numOfPatients){
            System.out.print("enter query : ");
            int query = Reader.nextInt();
            if(query==1){
                String instituteName = Reader.next();
                System.out.println();
                
                System.out.print("Temperature Critera - ");
                float maxTemp = Reader.nextFloat();
                System.out.println();
                
                System.out.print("Oxygen Levels - ");
                int minOxygenLevel = Reader.nextInt();
                System.out.println();
                
                System.out.print("Number of available beds - ");
                int availableBeds = Reader.nextInt();
                System.out.println();
                
                String admissionStatus = "OPEN";
                
                HealthCare _healthCare = new HealthCare(instituteName, maxTemp, minOxygenLevel, availableBeds, admissionStatus);
                
                System.out.println("Temperature should be <= " + maxTemp);
                System.out.println("Oxygen levels should be greater >= " + minOxygenLevel);
                System.out.println("Number of available beds - " + availableBeds);
                System.out.println("admission status - " + admissionStatus);
                
                user.setHealthCareList(_healthCare);
                user.admitPatients(_healthCare);
                
                ArrayList<Patient> patientList = user.getPatientInHealthCare().get(_healthCare);
                for(int i=0;i<patientList.size();i++){
                    System.out.print("Recovery days for admitted patient ID " + patientList.get(i).getID() + " - ");
                    int days = Reader.nextInt();
                    patientList.get(i).setRecoveryDays(days);
                    System.out.println();
                }

            }
            
            if(query==2){
                user.removeAdmittedPatients();
            }
            
            if(query==3){
                user.removeClosedInstitutes();
            }
            if(query==4){
                System.out.println(user.patientInCamps(numOfPatients) + " patients");
            }
            if(query==5){
                System.out.println(user.openedInstitutes() + " institutes are admitting patients currently");
            }
            if(query==6){
                String healthCareName = Reader.next();
                user.healthCareDetails(healthCareName);
            }
            if(query==7){
                int ID = Reader.nextInt();
                user.patientDetails(ID);
            }
            if(query==8){
                user.displayAllPatients();
            }
            if(query==9){
                String instituteName = Reader.next();
                user.patientsInInstitute(instituteName);
            }
            System.out.println();
            
        }
                
    }
    
}


class Patient{
    private String name;
    private int age;
    private int oxygenLevel;
    private float temperature;
    private int ID;
    private String nameOfHealthCare;
    private String patientAdmission;
    private int RecoveryDays;
    private static int patientID=0;
    
    Patient(String name, float temperature, int oxygenLevel, int age, String patientAdmission){
        this.name=name;
        this.age=age;
        this.oxygenLevel=oxygenLevel;
        this.temperature=temperature;
        this.patientAdmission=patientAdmission;
        this.ID=patientID+1;
        patientID+=1;
    }
    
    public void setPatientAdmission(String admitted){
        this.patientAdmission=admitted;
    }
    
    public void setNameOfHealthCare(String instituteName){
        this.nameOfHealthCare=instituteName;
    }
    
    public void setRecoveryDays(int days){
        this.RecoveryDays=days;
    }
    
    public int getRecoveryDays(){
        return this.RecoveryDays;
    }
    
    public String getNameOfHealthCare(){
        return this.nameOfHealthCare;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public int getOxygenLevel(){
        return this.oxygenLevel;
    }
    
    public float getTemperature(){
        return this.temperature;
    }
    
    public String getPatientAdmission(){
        return this.patientAdmission;
    }
    
    public int getID(){
        return this.ID;
    }
    
}

class HealthCare{
    private String instituteName;
    private float maxTemp;
    private int minOxygenLevel;
    private int availableBeds;
    private String admissionStatus;
    
    
    HealthCare(String instituteName, float maxTemp, int minOxygenLevel, int availableBeds, String admissionStatus){
        this.instituteName=instituteName;
        this.maxTemp=maxTemp;
        this.minOxygenLevel=minOxygenLevel;
        this.availableBeds=availableBeds;
        this.admissionStatus=admissionStatus;
    }
    
   
    public void setAdmissionStatus(String status){
        this.admissionStatus=status;
    }
    
    public void setAvailableBeds(int bedsAvailability){
        this.availableBeds=bedsAvailability;
    }
    
    public String getInstituteName(){
        return this.instituteName;
    }
    
    public float getMaxTemp(){
        return this.maxTemp;
    }
    
    public int getMinOxygenLevel(){
        return this.minOxygenLevel;
    }
    
    public int getAvailableBeds(){
        return this.availableBeds;
    }
    
    public String getAdmissionStatus(){
        return this.admissionStatus;
    }

    
}


class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }
}











