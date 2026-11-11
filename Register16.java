public class Register16
{
   private int value;

   public Register16()
   {
      value = 0;
   }

   public Register16(int value)
   {
      set(value);
   }

   public int get()
   {
      return value;
   }

   public void set(int value)
   {
      this.value = value & 0xFFFF;
   }
}