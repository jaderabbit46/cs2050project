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
    ArrayList<Drone> dronesList;

    public int getCountByManufacturer(String manufacturer)
    {
        int count;
        count = 0;

        return count;
    }

    public void generateReportSortedByManufacturingYear(ArrayList<Drone>dronesList)
    {

    }

    public void generateReportSortedByPayloadCapacity(ArrayList<Drone>dronesList)
    {

    }

    public void displayHangarInventory()
    {

    }

    public void showMenu()
    {

    }

    public boolean addDrone(Drone drone)
    {
        if (drone == null)
        {
            return false;
        }
        else
        {
            this.dronesList.add(drone);
            return true;
        }
    }

    public static void readFromCSV(Hangar hangar, String fileName)
    {
        int totalLinesRead = 0;

        if (hangar == null)
        {
            System.out.println("Error. Hangar not found.");
            return;
        }

        try(Scanner fileScan = new Scanner(new File(fileName)))
        {
            while (fileScan.hasNextLine())
            {
                String line = fileScan.nextLine();
                totalLinesRead = totalLinesRead + 1;
                boolean shouldProcess = true;
                if (line == null || line.trim().isEmpty())
                {
                    shouldProcess = false;
                }

                if(shouldProcess == true)
                {
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

    private static Drone parseDroneLine(String line, int LineNumber)
    {
        /**TODO: 
         * Check if the line is empty (or null)
         * Trim fields
         * Validate text fields (Type of drone, ManufacturerName, ManufacturerYear, weight limit in KG)
         * Use Try-Catch for year and payloadKG checking and if statements for year
        */

        String[] fields = line.split(",");
        String droneType = fields[0].trim();
        String manufacturerName = fields[1].trim();
        String manufacturerYear = fields[2].trim();
        String payloadKg = fields[3].trim();

        if (line.trim().isEmpty() || line == null)
        {
            return null;
        }
        if (fields.length != 4)
        {
            System.out.println("Error. Invalid number of columns. Expected 4, recieved " + fields.length);
            return null;
        }


        return Drone;
    }
}

/**
 * 
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
    
    @Override
    public String toString(){
        String toString = "";
        return toString;
    }
}

/**
 * 
 */
class PriorityDrone extends Drone
{
    public PriorityDrone(String manufacturerName, int manufacturerYear,double payloadKg)
    {
        super(manufacturerName, manufacturerYear, payloadKg);
    }

}

/**
 * 
 */
class StandardDrone extends Drone
{
    public StandardDrone(String manufacturerName, int manufacturerYear, double payloadKg)
    {
        super(manufacturerName, manufacturerYear, payloadKg);
    }

}