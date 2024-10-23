import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionTest {

    private Prescription presc1, presc2, presc3, presc4, presc5, presc6;

    @BeforeEach
    public void setUp() {
        // Test data setup
        presc1 = new Prescription("John", "Smith", "1234 Elm Street, Suburb, 12345, Country", 0.00f, -2.50f, 90, "12/12/23", "Dr. Adams");
        presc2 = new Prescription("Li", "Wong", "4321 Oak Road, Suburb, 54321, Country", 2.00f, -3.00f, 45, "21/05/23", "Dr. Visual");
        presc3 = new Prescription("Alice", "Johnson", "Short Rd.", 3.25f, -2.00f, 120, "10/08/23", "Dr.Clearview");
        presc4 = new Prescription("Eve", "Walker", "8765 Pine Street, Area, 87654, Country", -25.00f, 0.00f, 60, "05/09/23", "Dr. Sight");
        presc5 = new Prescription("Brian", "Lee", "2345 Birch Lane, Region, 45678, Country", -4.50f, -0.50f, 140, "22/07/23", "Dr. O");
        presc6 = new Prescription("Sophia", "Turner", "5678 Cedar Avenue, District, 98765, Country", -6.25f, -1.25f, 80, "18/06/23", "Dr. Glasses");
    }

    // Test for valid prescription data (should return true)
    @Test
    public void testAddPrescription_Valid() {
        assertTrue(presc1.addPrescription(), "Valid prescription should return true");
    }

    // Test for invalid first name (should return false)
    @Test
    public void testAddPrescription_InvalidFirstName() {
        assertFalse(presc2.addPrescription(), "Invalid first name should return false");
    }

    // Test for invalid address length (should return false)
    @Test
    public void testAddPrescription_InvalidAddress() {
        assertFalse(presc3.addPrescription(), "Invalid address should return false");
    }

    // Test for invalid sphere value (should return false)
    @Test
    public void testAddPrescription_InvalidSphere() {
        assertFalse(presc4.addPrescription(), "Invalid sphere value should return false");
    }

    // Test for invalid optometrist name (too short, should return false)
    @Test
    public void testAddPrescription_InvalidOptometristName() {
        assertFalse(presc5.addPrescription(), "Invalid optometrist name should return false");
    }

    // Test for valid prescription data (should return true)
    @Test
    public void testAddPrescription_ValidWithDifferentData() {
        assertTrue(presc6.addPrescription(), "Valid prescription with different data should return true");
    }

    // Test for valid remark (should return true)
    @Test
    public void testAddRemark_ValidRemark() {
        assertTrue(presc1.addRemark("This prescription is accurate and detailed.", "client"), "Valid remark should return true");
    }

    // Test for invalid remark category (should return false)
    @Test
    public void testAddRemark_InvalidCategory() {
        assertFalse(presc1.addRemark("This is well documented.", "other"), "Invalid category should return false");
    }

    // Test for remark with fewer than 6 words (should return false)
    @Test
    public void testAddRemark_FewerThanSixWords() {
        assertFalse(presc1.addRemark("Needs adjustment.", "optometrist"), "Remark with fewer than 6 words should return false");
    }

    // Test for remark with more than 20 words (should return false)
    @Test
    public void testAddRemark_MoreThanTwentyWords() {
        assertFalse(presc1.addRemark("This prescription is not accurate. The sphere, cylinder, and axis values are incorrect, and the patient is complaining about discomfort.", "client"), "Remark with more than 20 words should return false");
    }

    // Test for remark with first letter not uppercase (should return false)
    @Test
    public void testAddRemark_FirstLetterNotUppercase() {
        assertFalse(presc1.addRemark("this is a client remark that should fail.", "client"), "Remark with first letter not uppercase should return false");
    }

    // Test for valid remark, but already two remarks exist (should return false)
    @Test
    public void testAddRemark_TwoRemarksExist() {
        presc1.addRemark("This is the first remark.", "client");
        presc1.addRemark("This is the second remark.", "client");
        assertFalse(presc1.addRemark("This is a valid client remark.", "client"), "Remark should not be added when two remarks already exist");
    }
}
