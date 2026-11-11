public class Register8
{
   private int value;
   
   public Register8()
   {
      value = 0;
   }
   
   
   public Register8(int value)
   {
      set(value);
   }
   
   public int get()
   {
      return value;
   }
   
   
   
   public void set(int value)
   {
      this.value = value & 0xFF;
   }
}