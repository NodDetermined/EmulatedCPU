public class Memory
{ //use Byte
   private final byte[] ram;
   public static final int MEMORY_SIZE = 65536; // 64 KB (0x0000 to 0xFFFF)
   
   
   public Memory()
   {
      this.ram = new byte[MEMORY_SIZE];
      //reset();
   }

   public int read8(int address)//reads an 8-bit byte from given address
   {
      int targetAddress = address & 0xFFFF; 
   
   return 5;
   }


   public int read16(int address)// reads a 16-bit 
   {
   return 5;
   }

   public void write8(int address, int value)// writes an 8-bit value to a specific address
   {
   }

   public void write16(int address, int value)// writes 16 bit value across 2 addresses
   {
   
   }
   
   

   
   private void checkAddress(int address)
   {
      if(address < 0 || address > ram.length)
      {
         throw new IndexOutOfBoundsException("Address out of bounds: " + address);
      }
   }
}