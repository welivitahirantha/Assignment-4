import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> postRemarks = new ArrayList<>();

    public Prescription(String firstName, String lastName, String address, float sphere, float cylinder, float axis, String examinationDate, String optometrist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.cylinder = cylinder;
        this.axis = axis;
        try {
            this.examinationDate = new SimpleDateFormat("dd/MM/yy").parse(examinationDate);
        } catch (ParseException e) {
            this.examinationDate = null; // Invalid date
        }
        this.optometrist = optometrist;
    }

    // Method to add a prescription with validation
    public boolean addPrescription() {
        // Condition 1: Validate first and last name
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }

        // Condition 2: Validate address length
        if (address.length() < 20) {
            return false;
        }

        // Condition 3: Validate sphere, cylinder, and axis
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false;
        }

        // Condition 4: Validate examination date is not null (improper format)
        if (examinationDate == null) {
            return false;
        }

        // Condition 5: Validate optometrist name length
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // If all conditions are met, save to a TXT file
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Examination Date: " + new SimpleDateFormat("dd/MM/yy").format(examinationDate) + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("-------------------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Method to add a remark with validation
    public boolean addRemark(String remarkText, String remarkCategory) {
        // Condition 1: Validate category (only "client" or "optometrist" allowed)
        if (!remarkCategory.equalsIgnoreCase("client") && !remarkCategory.equalsIgnoreCase("optometrist")) {
            return false;
        }

        // Condition 2: Validate number of remarks (max 2)
        if (postRemarks.size() >= 2) {
            return false;
        }

        // Condition 3: Validate word count (between 6 and 20 words)
        String[] words = remarkText.split(" ");
        if (words.length < 6 || words.length > 20) {
            return false;
        }

        // Condition 4: Validate the first character of the first word is uppercase
        if (!Character.isUpperCase(remarkText.charAt(0))) {
            return false;
        }

        // If all conditions are met, save to a TXT file
        try (FileWriter writer = new FileWriter("remark.txt", true)) {
            writer.write("Remark: " + remarkText + "\n");
            writer.write("Category: " + remarkCategory + "\n");
            writer.write("-------------------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Add remark to the list of remarks
        postRemarks.add(remarkText);
        return true;
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        // Test cases for addPrescription()
        Prescription presc1 = new Prescription("John", "Smith", "1234 Elm Street, Suburb, 12345, Country", 0.00f, -2.50f, 90, "12/12/23", "Dr. Adams");
        System.out.println("Test Case 1 (Valid Prescription): " + presc1.addPrescription()); // Should return true

        Prescription presc2 = new Prescription("Li", "Wong", "4321 Oak Road, Suburb, 54321, Country", 2.00f, -3.00f, 45, "21/05/23", "Dr. Visual");
        System.out.println("Test Case 2 (Invalid First Name): " + presc2.addPrescription()); // Should return false

        // Test cases for addRemark()
        System.out.println("Test Case 1 (Valid Remark): " + presc1.addRemark("This prescription is accurate and detailed.", "client")); // Should return true
        System.out.println("Test Case 2 (Invalid Category): " + presc1.addRemark("This is well documented.", "other")); // Should return false
    }
}
