public class Memory
{ //use Byte
   private final byte[] ram;
   public static final int MEMORY_SIZE = 65536; // 64 KB (0x0000 to 0xFFFF)
   
   
   public Memory()
   {
      this.ram = new byte[MEMORY_SIZE];
      //reset();
   }

}