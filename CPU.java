public class CPU
{
      // main hardware components
      private Register A;
      private Register B;
      private ArithmeticLogicUnit alu;
      private Memory memory;
      
      private boolean running;
      
       // Special CPU registers
       private int programCounter;      // Address of next instruction
       private int instructionRegister; // Current opcode being executed
       
      
      public CPU(Memory memory)
      {
         this.memory = memory;
         this.A = new Register();
         this.B = new Register();
         this.alu = new ArithmeticLogicUnit();
         
         this.running = false;
         this.programCounter = 0;
         this.instructionRegister = 0;
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
      
      //stop everything!!
      public void halt()
      {
         this.running = false;
      }
      
      
      // fetch the next opCode from memory
      /*
         get byte from memory at programCounter
         Store in instruction register
         increment program Counter
      */
      private void fetch()
      {
      
      }
      
      
      // decode opcode inside instruction register and execute it.
      
      // use big switch :case statement
      // opcode : do the instructions
      // call other helper methods.
      private void decodeAndExecute()
      {
      
      }
      
      /**
    * Implement a LOAD instruction.
    *
    * LOAD value into accumulator register.
    */
   private void executeLoad()
   {

   }

   /**
    * Implement a STORE instruction.
    *
    * Write accumulator value to memory.
    */
   private void executeStore()
   {

   }

   /**
    * Implement ADD instruction.
    *
    * Use the ALU's add() method.
    * Store the result back into the A register.
    */
   private void executeAdd()
   {
   int operand1 = this.A.get();
   int operand2 = this.B.get();
   
   int result = this.alu.add(operand1, operand2);
   
   A.set(result);
   }

   /**
    * Implement SUB instruction.
    *
    * Use the ALU's sub() method.
    * Store the result back into the register.
    */
   private void executeSub()
   {
   int operand1 = this.A.get();
   int operand2 = this.B.get();
   
   int result = this.alu.sub(operand1,operand2);
   A.set(result);
   }

   /**
    * Implement unconditional jump.
    *
    * Update programCounter to a new address.
    */
   private void executeJump()
   {

   }
      
      
   // reset CPU state
   // set everything to zero
   public void reset()
   {
      halt();
      programCounter = 0;
      instructionRegister = 0; 
   }
      
      
      
   public int getProgramCounter()
   {
      return programCounter;
   }
   public int getInstructionRegister()
   {
      return instructionRegister;
   }
   public Register getRegisterA()
   {
      return A;
   }
   public Register getRegisterB()
   {
      return B;
   }
   public ArithmeticLogicUnit getALU()
   {
      return alu;
   }
   public Memory getMemory()
   {
      return memory;
   }
   
      
      
}//end of class