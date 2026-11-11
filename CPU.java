public class CPU
{
      // main hardware components
   private Register8 A;
   private Register8 B;
      // Special CPU registers
   private Register8 instructionRegister; // Current opcode being executed
   private Register16 programCounter; // Address of next instruction
       
   private ArithmeticLogicUnit alu;
   private Memory memory;
      
      //if true, keep running. false, halt()
   private boolean running;
      
       
   public CPU(Memory memory)
   {
      this.memory = memory;
      this.A = new Register8();
      this.B = new Register8();
      this.alu = new ArithmeticLogicUnit();
         
      this.running = false;
      this.instructionRegister = new Register8();
      this.programCounter = new Register16();
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
         get opcode from memory at programCounter
         Store in instruction register
         increment program Counter
      */
   private void fetch()
   {
      int opcode = memory.read8(programCounter.get());
      
      instructionRegister.set(opcode);
         
      programCounter.set(programCounter.get() + 1);
   }
      
      
      // decode opcode inside instruction register and execute it.
      
      // use big switch :case statement
      // opcode : do the instructions
      // call other helper methods.
   private void decodeAndExecute()
   {
      int opcode = instructionRegister.get();
      
      switch(opcode)
      {
         case(ArithmeticLogicUnit.OP_ADD):
            executeAdd();
            break;
         case(ArithmeticLogicUnit.OP_SUB):
            executeSub();
            break;
         
         
         
         case 0xFF:
            halt();
            break;
            
            
         default:
            throw new IllegalArgumentException("Unknown opcode: " + opcode);
      
      }
   
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
      programCounter.set(0);
      instructionRegister.set(0); 
      
      A.set(0);
      B.set(0);
   }
      
      
      
   public Register16 getProgramCounter()
   {
      return programCounter;
   }
   
   public Register8 getInstructionRegister()
   {
      return instructionRegister;
   }
   
   public Register8 getRegisterA()
   {
      return A;
   }
   public Register8 getRegisterB()
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