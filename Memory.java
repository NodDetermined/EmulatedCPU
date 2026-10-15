public class Memory
{ //use Byte
   private final byte[] ram;
   public static final int MEMORY_SIZE = 65536; // 64 KB (0x0000 to 0xFFFF)
   
   
   public Memory()
   {
      this.ram = new byte[MEMORY_SIZE];
      reset();
   }

   public void reset()
   {
      for(int i = 0; i < MEMORY_SIZE; i++)
      {
         ram[i]=0x00;
      }
   }

   public int read8(int address)//reads an 8-bit byte from given address
   {
      checkAddress(address);
      return ram[address] & 0xFF;
   }


   public int read16(int address)// reads a 16-bit 
   {
      int highByte = read8(address);
      int lowByte = read8(address + 1);
      return (highByte << 8) | lowByte;
   }
   // the << 8 makes highbyte have a 0000 added to the end of it
   // allowing it to be merged with the "lower-end byte". 

   public void write8(int address, int value)// writes an 8-bit value to a specific address
   {
      checkAddress(address);
      int targetAddress = address & 0xFFFF;
      ram[targetAddress] = (byte)(value & 0xFF);
   }

   public void write16(int address, int value)// writes 16 bit value across 2 addresses
   {
   
   }
   
   // loading in a file
   public void load(int startAddress, byte[] data)
   {
      for(int i = 0; i < data.length; i++)
      { 
         write8(startAddress+i,data[i]);   
      }
   }

   
   private void checkAddress(int address)
   {
      if(address < 0 || address >= ram.length)
      {
         throw new IndexOutOfBoundsException("Address out of bounds: " + address);
      }
   }
}