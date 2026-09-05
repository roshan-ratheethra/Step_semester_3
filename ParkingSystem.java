class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;
    
    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }
    
    public void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }
}

public class ParkingSystem {
    
    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].occupiedCount < slots[i].capacity) {
                return slots[i];
            }
        }
        return null;
    }
    
    // Passing the ParkingSlot array into these methods does not copy the slots themselves because Java passes object references by value. 
    // The method simply receives a copy of the memory address pointing to the exact same array and objects.
    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot available = findAvailableSlot(slots);
        if (available != null) {
            available.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }
    
    public static void main(String[] args) {
        ParkingSlot[] availableSlots = new ParkingSlot[2];
        availableSlots[0] = new ParkingSlot("A1", 4, 3);
        availableSlots[1] = new ParkingSlot("A2", 5, 5);
        
        ParkingSlot[] fullSlots = new ParkingSlot[2];
        fullSlots[0] = new ParkingSlot("A1", 4, 4);
        fullSlots[1] = new ParkingSlot("A2", 5, 5);
        
        System.out.println("Slots: A1 (3/4), A2 (5/5)");
        safeAllot(availableSlots, "TN09AB1234");
        
        System.out.println("\nSlots: A1 (4/4), A2 (5/5)");
        safeAllot(fullSlots, "TN09AB1234");
    }
}