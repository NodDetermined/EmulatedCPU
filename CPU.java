public class CPU
{
      private RegisterFile registers;
      private ArithmeticLogicUnit alu;
      private Memory memory;
      
      private boolean running;
      
      public CPU(Memory memory)
      {
         this.memory = memory;
         this.registers = new RegisterFile();
         this.alu = new ArithmeticLogicUnit();
         this.running = false;
      }
      
      public void run()
      {
         this.running = true;
         while(running)
         {
            fetch();
            decodeAndExecute();
         }
      }
      
      private void fetch()
      {
      
      }
      
      private void decodeAndExecute()
      {
      
      }
      
}