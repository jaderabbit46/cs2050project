import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class cs2050project 
{
    public static void main(String[] args)
    {
        // Create hanger in main
    }
}

/**
 * 
 */
class Hangar
{
    ArrayList<Drone> dronesList; // We will use Arraylists

    /**
     * Iterates through ArrayList in Hangar Class and increments counter if manufacturer name matches input string.
     * @param manufacturer
     * @return count
     */
    public int getCountByManufacturer(String manufacturer)
    {
        int count = 0;

        for (int i = 0; i < dronesList.size();i++) // for loop iterating for ArrayList
        {
            if (dronesList.get(i).getManufacturer() == manufacturer) // Can use Instanceof
            {
                count++;
            }
        }
        
        return count;
    }

    public void generateReportSortedByManufacturingYear(ArrayList<Drone>dronesList)
    {

    }

    public void generateReportSortedByPayloadCapacity(ArrayList<Drone>dronesList)
    {
        /** TODO:
         *  1. Sort list into new Array by Payload Capacity 
         *  2. Print the output of the list (either during runtime or after the sort)
         */

    }

    public void displayHangarInventory()
    {
        //Display formatted printout of inventory
    }

    public void showMenu()
    {
        // TODO: Null check -> while isvalid, Use Switch Case for the rest
        Scanner input = new Scanner(System.in);
        boolean bool = true;

        String menu[] = {
                         "1. Load Drones from CSV",
                         "2. Display Hangar Inventory", 
                         "3. Search Drones (Manufacturer & Type)", 
                         "4. View Inventory Sorted by Payload (Manual Sort)",
                         "5. View Inventory Sorted by Year (Manual Sort)",
                         "6. Count Drones by Manufacturer",
                         "7. Exit"
                        };

        while (bool == true)
        {
            System.out.println("=== Drone Hangar Menu ===");

            for (int i = 0; i < menu.length; i++)
            {
                System.out.println(menu[i]);
            }

            System.out.println("Enter your Choice(1-7): ");
            int output = input.nextInt();

            // use Switch case For choices AFTER checking
            switch (output)
            {
                case 1:

                case 2:

                default:
            }

        }

    }

    public boolean addDrone(Drone drone)
    {
        // redudancy null check
        if (drone != null)
        {
            this.dronesList.add(drone);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Read From CSV uses an input CSV file in directory to load and then parse to a readable format.
     * @param hangar
     * @param fileName
     */
    public static void readFromCSV(Hangar hangar, String fileName)
    {
        int totalLinesRead = 0;

        if (hangar == null)
        {
            System.out.println("Error. Hangar not found.");
            return;
        }

        // try catch 
        try(Scanner fileScan = new Scanner(new File(fileName)))
        {
            // Continuous checking to iterate through entire file
            while (fileScan.hasNextLine())
            {
                String line = fileScan.nextLine();

                // Increment totalLinesRead to keep track of lines and add drones
                totalLinesRead = totalLinesRead + 1;
                boolean shouldProcess = true;
                
                if (line == null || line.trim().isEmpty())
                {
                    shouldProcess = false;
                }
                
                // If conditionals above are true then we can proceed and prepare to parse and add a drone
                if(shouldProcess == true)
                {
                    // Send to helper method to parse the drone line into a new drone
                    Drone newDrone = parseDroneLine(line,totalLinesRead);

                    if (newDrone != null)
                    {
                        boolean added = hangar.addDrone(newDrone);
                        if (added == false)
                        {
                            System.out.println("Error. Unable to add Drone.");
                        }

                    }
                }
            }
        }
        catch (FileNotFoundException ex)
        {
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
    private static Drone parseDroneLine(String line, int LineNumber)
    {
        /**TODO: 
         * Check if the line is empty (or null)
         * Trim fields
         * Validate text fields (Type of drone, ManufacturerName, ManufacturerYear, weight limit in KG)
         * Use Try-Catch for year and payloadKG checking and if statements for year
        */
        if (line.trim().isEmpty() || line == null)
        {
            return null;
        }

        String[] fields = line.split(","); // CSV -> Comma Seperated Values
        
        // Utilize array of our four inputs that we need: If P or S, Name, Year, Weight Limit and store them into variables
        String droneType = fields[0].trim(); 
        String manufacturerName = fields[1].trim();
        String manufacturerYear = fields[2].trim();
        String payloadKg = fields[3].trim();

        if (fields.length != 4)
        {
            System.out.println("Error. Invalid number of columns. Expected 4, recieved " + fields.length);
            return null;
        }

        return Drone;
    }
}

/** 
 * Abstract Drone class used for creation of Priority and Standard Drones.
 */
abstract class Drone
{
    private String manufacturerName;
    private int manufacturerYear;
    private double payloadKg;

    public Drone (String manufacturerName, int manufacturerYear,double payloadKg)
    {
        this.manufacturerName = manufacturerName;
        this.manufacturerYear = manufacturerYear;
        this.payloadKg = payloadKg;
    }

    public String getManufacturer()
    {
        return this.manufacturerName;
    }

    public int getManufacturerYear()
    {
        return this.manufacturerYear;
    }

    public double getPayloadKg()
    {
        return this.payloadKg;
    }

    @Override
    public String toString(){
        String toString = "";
        return toString;
    }
}

/**
 * Priority Drone
 */
class PriorityDrone extends Drone
{
    public PriorityDrone(String manufacturerName, int manufacturerYear,double payloadKg)
    {
        super(manufacturerName, manufacturerYear, payloadKg);
    }

}

/**
 * Standard Drone
 */
class StandardDrone extends Drone
{
    public StandardDrone(String manufacturerName, int manufacturerYear, double payloadKg)
    {
        super(manufacturerName, manufacturerYear, payloadKg);
    }

}