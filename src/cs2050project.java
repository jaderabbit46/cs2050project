import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */
public class cs2050project {
    public static void main(String[] args) {
        // Create hanger in main
    }
}

/**
 * 
 */
class Hangar {

    ArrayList<Drone> dronesList; // We will use Arraylists

    /**
     * Iterates through ArrayList in Hangar Class and increments counter if
     * manufacturer name matches input string.
     * 
     * @param manufacturer
     * @return count
     */
    public int getCountByManufacturer(String manufacturer) {
        int count = 0;

        for (int i = 0; i < dronesList.size(); i++) // for loop iterating for ArrayList
        {
            if (dronesList.get(i).getManufacturer() == manufacturer) // Can use Instanceof
            {
                count++;
            }
        }

        return count;
    }

    public void generateReportSortedByManufacturingYear(ArrayList<Drone> dronesList) {

    }

    public void generateReportSortedByPayloadCapacity(ArrayList<Drone> dronesList) {
        /**
         * TODO:
         * 1. Sort list into new Array by Payload Capacity (use Clone or something)
         * 2. Do a simple comparison sort (i.e. Selection sort)
         * 2. Print the output of the list (either during runtime or after the sort)
         * 3.
         */

        // MUST use Implicit casting since .clone() returns an object.
        ArrayList<Drone> sortedPayloadKG = (ArrayList<Drone>) dronesList.clone();

        for (int i = 0; i < sortedPayloadKG.size() - 1; i++)
        {
            //int min = (int) sortedPayloadKG.get(i);
            
            for (int j = i + 1; j < sortedPayloadKG.get(i).getPayloadKg(); j++)
            {
                if (sortedPayloadKG.get(i).getPayloadKg() < sortedPayloadKG.get(min).getPayloadKg())
            }
        }

    }

    public void displayHangarInventory() {
        // Display formatted printout of inventory
    }

    public void showMenu() {
        // TODO: Null check -> while isvalid, Use Switch Case for the rest
        // Refer to L16
        Scanner input = new Scanner(System.in);
        boolean runtime = true;

        String menu[] = {
                "1. Load Drones from CSV",
                "2. Display Hangar Inventory",
                "3. Search Drones (Manufacturer & Type)",
                "4. View Inventory Sorted by Payload (Manual Sort)",
                "5. View Inventory Sorted by Year (Manual Sort)",
                "6. Count Drones by Manufacturer",
                "7. Exit"
        };

        // test ex look at lab16
        // Use
        while (runtime) {
            System.out.println("=== Drone Hangar Menu ===");

            for (int i = 0; i < menu.length; i++) {
                System.out.println(menu[i]);
            }

            System.out.println("Enter your Choice: ");
            int output = input.nextInt();

            if (input.hasNextInt()) {
                int response = input.nextInt();
                input.nextLine();

                switch (response) {
                    case 1:
                        // Load drones from CSV
                    case 2:
                        // Display hangar inventory
                    case 3:
                        // Search Drones (Manufacturer & Type)
                    case 4:
                        // View Inventory by Payload
                    case 5:
                        // View Inventory by Year
                    case 6:
                        // Count drone by manufacturer
                    case 7:
                        // Exit

                    default:
                        System.out.println("Invalid selection. Please pick from 1-" + menu.length);
                }

            } else {
                System.out.println("Invalid input, please insert a valid number.");
                input.nextLine();
            }

        }

    }

    /**
     * Adds a drone to the hangar. Used in CSVreader, has null check to allow other
     * ways of adding a drone
     * 
     * @param drone
     * @return
     */
    public boolean addDrone(Drone drone) {
        // redudancy null check
        if (drone != null) {
            this.dronesList.add(drone);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Read From CSV uses an input CSV file in directory to load and then parse to a
     * readable format.
     * 
     * @param hangar
     * @param fileName
     */
    public static void readFromCSV(Hangar hangar, String fileName) {
        int totalLinesRead = 0;

        if (hangar == null) {
            System.out.println("Error. Hangar not found.");
            return;
        }

        // try catch
        try (Scanner fileScan = new Scanner(new File(fileName))) {
            // Continuous checking to iterate through entire file
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();

                // Increment totalLinesRead to keep track of lines and add drones
                totalLinesRead = totalLinesRead + 1;
                boolean shouldProcess = true;

                if (line == null || line.trim().isEmpty()) {
                    shouldProcess = false;
                }

                // If conditionals above are true then we can proceed and prepare to parse and
                // add a drone
                if (shouldProcess == true) {
                    // Send to helper method to parse the drone line into a new drone
                    Drone newDrone = parseDroneLine(line, totalLinesRead);

                    if (newDrone != null) {
                        boolean added = hangar.addDrone(newDrone);
                        if (added == false) {
                            System.out.println("Error. Unable to add Drone.");
                        }

                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error. Could not open file: " + fileName);
            return;
        }

    }

    /**
     * 
     * @param line
     * @param LineNumber
     * @return
     */
    private static Drone parseDroneLine(String line, int LineNumber) {
        /**
         * TODO:
         * Check if the line is empty (or null)
         * Trim fields
         * Validate text fields (Type of drone, ManufacturerName, ManufacturerYear,
         * weight limit in KG)
         * Use Try-Catch for year and payloadKG checking and if statements for year
         * Pretty iffy- find lecture for the lab
         */
        if (line.trim().isEmpty() || line == null) {
            return null;
        }

        String[] fields = line.split(","); // CSV -> Comma Seperated Values

        // Utilize array of our four inputs that we need: If P or S, Name, Year, Weight
        // Limit and store them into variables
        String droneType = fields[0].trim();
        String manufacturerName = fields[1].trim();
        String manufacturerYear = fields[2].trim();
        String payloadKg = fields[3].trim();

        if (fields.length != 4) {
            System.out.println("Error. Invalid number of columns. Expected 4, recieved " + fields.length);
            return null;
        }

        return Drone;
    }
}

/**
 * Abstract Drone class used for creation of Priority and Standard Drones.
 */
abstract class Drone {
    private String manufacturerName;
    private int manufacturerYear;
    private double payloadKg;

    public Drone(String manufacturerName, int manufacturerYear, double payloadKg) {
        this.manufacturerName = manufacturerName;
        this.manufacturerYear = manufacturerYear;
        this.payloadKg = payloadKg;
    }

    public String getManufacturer() {
        return this.manufacturerName;
    }

    public int getManufacturerYear() {
        return this.manufacturerYear;
    }

    public double getPayloadKg() {
        return this.payloadKg;
    }

    @Override
    public String toString() {
        String toString = "";
        return toString;
    }
}

/**
 * Priority Drone
 */
class PriorityDrone extends Drone {
    public PriorityDrone(String manufacturerName, int manufacturerYear, double payloadKg) {
        super(manufacturerName, manufacturerYear, payloadKg);
    }

}

/**
 * Standard Drone
 */
class StandardDrone extends Drone {
    public StandardDrone(String manufacturerName, int manufacturerYear, double payloadKg) {
        super(manufacturerName, manufacturerYear, payloadKg);
    }

}