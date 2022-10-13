package com.example.seg2105_project;
import static android.content.Context.MODE_PRIVATE;

import java.io.IOException;
import java.util.ArrayList;
import java.io.*;


public class Runner {

    private static final String AF = "admin.txt";
    private static final String ClF = "client.txt";
    private static final String CoF = "cook.txt";
    private static final String MF = "meal.txt";
    private static final String ComF = "complaint.txt";
    private static Runner instance = null;

    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Cook> cooks = new ArrayList<>();
    ArrayList<Meal> meals = new ArrayList<>();
    ArrayList<Complaint> complaints = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();

    protected Runner() throws IOException, ClassNotFoundException {
        loadDBData();
    }

    public static Runner getInstance() throws ClassNotFoundException, IOException {
        if (instance == null) {
            instance = new Runner();
        }
        return instance;
    }

    private void loadDBData() throws IOException, ClassNotFoundException {
        loadClientFromDB();
        loadCookFromDB();
        loadAdminFromDB();
        loadMealFromDB();
        loadComplaintFromDB();
    }

    private boolean loadClientFromDB() throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(ClF)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            Client obj = null;

            boolean isExist = true;

            while (isExist) {
                if (fis.available() != 0) {
                    obj = (Client) ois.readObject();
                    this.clients.add(obj);
                } else {
                    isExist = false;
                }
            }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private boolean loadCookFromDB() throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(CoF)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            Cook obj = null;

            boolean isExist = true;

            while (isExist) {
                if (fis.available() != 0) {
                    obj = (Cook) ois.readObject();
                    this.cooks.add(obj);
                } else {
                    isExist = false;
                }
            }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private boolean loadAdminFromDB() throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(AF)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            Admin obj = null;

            boolean isExist = true;

            while (isExist) {
                if (fis.available() != 0) {
                    obj = (Admin) ois.readObject();
                    this.admins.add(obj);
                } else {
                    isExist = false;
                }
            }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private boolean loadMealFromDB() throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(ClF)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            Meal obj = null;

            boolean isExist = true;

            while (isExist) {
                if (fis.available() != 0) {
                    obj = (Meal) ois.readObject();
                    this.meals.add(obj);
                } else {
                    isExist = false;
                }
            }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private boolean loadComplaintFromDB() throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(ComF)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            Complaint obj = null;

            boolean isExist = true;

            while (isExist) {
                if (fis.available() != 0) {
                    obj = (Complaint) ois.readObject();
                    this.complaints.add(obj);
                } else {
                    isExist = false;
                }
            }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int randomIdGenerator() {
        boolean unique = true;
        int rand = 0;
        do {
            rand = (int) (Math.random()*10000);
            for (Client i : clients) {
                if (i.getId() == rand){
                    unique = false;
                }
            }
            for (Cook i : cooks) {
                if (i.getId() == rand){
                    unique = false;
                }
            }
            for (Admin i : admins) {
                if (i.getId() == rand){
                    unique = false;
                }
            }
            for (Meal i : meals) {
                if (i.getId() == rand){
                    unique = false;
                }
            }
            for (Complaint i : complaints) {
                if (i.getId() == rand){
                    unique = false;
                }
            }
        } while (unique == false);
        return rand;
    }

    public void clientSignup(int id, String firstName, String lastName, String email, String password, String address, String cardNumber, String expiry, String CVV) throws IOException {
        this.clients.add(new Client(id, firstName, lastName, email, password, address, cardNumber, expiry, CVV));
        saveClientToDB();
    }

    private void saveClientToDB() throws IOException {

        FileOutputStream f = new FileOutputStream((ClF));
        ObjectOutputStream o = new ObjectOutputStream(f);
        for (Client i: this.clients){
            o.writeObject(i);
        }
        o.close();
        f.close();
    }

    public void cookSignup(int id, String firstName, String lastName, String email, String password, String address, String description) throws IOException {
        this.cooks.add(new Cook(id, firstName, lastName, email, password, address, description));
        saveCookToDB();
    }

    private void saveCookToDB() throws IOException {
        FileOutputStream f = new FileOutputStream(new File(CoF));
        ObjectOutputStream o = new ObjectOutputStream(f);
        for (Cook i: this.cooks){
            o.writeObject(i);
        }
        o.close();
        f.close();
    }

    public void addAdmin(int id, String firstName, String lastName, String email, String password, String address) throws IOException {
        this.admins.add(new Admin(id, firstName, lastName, email, password, address));
        saveAdminToDB();
    }

    private void saveAdminToDB() throws IOException {
        FileOutputStream f = new FileOutputStream(new File(AF));
        ObjectOutputStream o = new ObjectOutputStream(f);
        for (Admin i: this.admins){
            o.writeObject(i);
        }
        o.close();
        f.close();
    }

    public void addMeal(int id, String name, MEAL_TYPE mealType, CUISINE_TYPE cuisineType, String ingredients, String allergens, String price, String description) throws IOException {
        this.meals.add(new Meal(id, name, mealType, cuisineType, ingredients, allergens, price, description));
        saveMealToDB();
    }

    private void saveMealToDB() throws IOException {
        FileOutputStream f = new FileOutputStream(new File(MF));
        ObjectOutputStream o = new ObjectOutputStream(f);
        for (Meal i: this.meals){
            o.writeObject(i);
        }
        o.close();
        f.close();
    }

    public void addComplaint(int id, String description, int clientID, int cookID) throws IOException {
        this.complaints.add(new Complaint(id, description, clientID, cookID));
        saveComplaintToDB();
    }

    private void saveComplaintToDB() throws IOException {
        FileOutputStream f = new FileOutputStream(new File(ComF));
        ObjectOutputStream o = new ObjectOutputStream(f);
        for (Complaint i: this.complaints){
            o.writeObject(i);
        }
        o.close();
        f.close();
    }


}
