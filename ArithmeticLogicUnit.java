public class ArithmeticLogicUnit
{ // use int

   public static final int FLAG_ZERO = 1 << 0;//0001      [1]
   public static final int FLAG_NEGATIVE = 1 << 1;//0010  [2]
   public static final int FLAG_CARRY = 1 << 2;/// 0100   [4]
   public static final int FLAG_OVERFLOW = 1 << 3; // 1000 [8]

   private int flags = 0;
   
   // Opcode definitions
   public static final int OP_ADD = 0x01;  // 00000001
   public static final int OP_SUB = 0x02;  // 00000010
   
   /* 
   *  int is 32 bits, long is 64 bits.
   *
   * 0 + 0 = 0
   * 0 + 1 = 1
   * 1 + 0 = 1
   * 1 + 1 = 11 (carry 1 to next bit)
   */
   
   public int execute(int opcode, int operandA, int operandB)
   {
      switch(opcode)
      {
         case OP_ADD: 
            return add(operandA, operandB); 
         case OP_SUB:
            return sub(operandA, operandB);
         
         default:
            throw new IllegalArgumentException("Unknown Opcode: " + opcode);
      }
   }
   
   
   public int add(int operandA, int operandB)
   {
      //reset any previous flags
      flags = 0;
      
      
      // Ensure operands are treated strictly as 8-bit
      operandA &= 0xFF;
      operandB &= 0xFF;
        
      
      int result = operandA + operandB;  
   
      
         // checking for carry-over of the 8th bit)
         // sets Carry flag if result is over 255 
      if (result > 0xFF) // 1111 1111      
      {
         flags |= FLAG_CARRY;  // |= Or bit. 
      }
      
      /*
      * 01000100 01110101   
      * 00000000 11111111 &=
      * 00000000 11111111
      * cuts down to max, 255 bits.
      */
      // Clamp to 8-bit, makes number positive 0-255
      result = result & 0xFF;
      
     
      // Extract the 7th bit (shows sign of both operands). 
      int signA = operandA & 0x80;
      int signB = operandB & 0x80;
      int signResult = (result & 0x80);
      
      
      // if Pos + Pos = neg,   or Neg - Neg = Pos
      if (signA == signB && signA != signResult) 
      {
         flags |= FLAG_OVERFLOW;
      }
      
      
      // sets Zero flag in the main flag if result is 0. 
      if(result == 0)
      {
         flags |= FLAG_ZERO;
      }
      
         //evaluates as either 1000 0000  or 0000 0000.
      if((result & 0x80) == 0x80) //checks result against  7th bit (128)
      {
         flags |= FLAG_NEGATIVE;
      } 
     
      return result;
   }
   
      
   public int sub(int operandA, int operandB)
   {
      flags = 0;
      
      // only accepting 8-bits.
      operandA &= 0xFF;
      operandB &= 0xFF;
        
        
         // need to borrow from the next bit
      if (operandA < operandB)
      {
         flags |= FLAG_CARRY; 
      }
      
      int result = operandA - operandB;
      
      /*
      * 01000100 01110101   
      * 00000000 11111111 &=
      * 00000000 11111111
      * cuts down to max, 255 bits.
      */
      // Clamp to 8-bit, makes number positive 0-255
      result = result & 0xFF;
      
      
      // Extract the 7th bit (sign bit) of both operands and the 8-bit result
      int signA = operandA & 0x80;
      int signB = operandB & 0x80;
      int signResult = (result & 0x80);
      
      //expected : 
      //  + num  -  (- num) = positive.
      //  - num  -  (+ num) = negative.
      // if Pos - neg = neg,   or neg - pos = pos
      if (signA != signB && signA != signResult) 
      {
         flags |= FLAG_OVERFLOW;
      }
     
     
      if(result == 0)
      {
         flags |= FLAG_ZERO;
      }
      
      if((result & 0x80) == 0x80) //checks result against  7th bit (128)
      {
         flags |= FLAG_NEGATIVE;
      } 
      
      
      return result;
   }



   public int getFlags()
   {
      return this.flags;
   }
  
   public static void main(String[] args) 
   {
      ArithmeticLogicUnit alu = new ArithmeticLogicUnit();
      
        // 10 + 20 = 30     0 flags
      int res1 = alu.execute(ArithmeticLogicUnit.OP_ADD, 10, 20);
      System.out.println("Result: " + res1 + " | Flags: " + alu.getFlags()); // Output: 30 | 0
      
        //250 + 10 = 260 -> 4     Carry Flag should be set : Carry Flag = 4
      int res2 = alu.execute(ArithmeticLogicUnit.OP_ADD, 250, 10);
      System.out.println("Result: " + res2 + " | Flags: " + alu.getFlags()); // Output: 4 | 4
      
        //5 - 5 = 0   Zero Flag should be set : Zero Flag = 1
      int res3 = alu.execute(ArithmeticLogicUnit.OP_SUB, 5, 5);
      System.out.println("Result: " + res3 + " | Flags: " + alu.getFlags()); // Output: 0 | 1
      
        //5 - 10 = -5 -> 251, Carry Flag + Negative flag: 4 + 2
      int res4 = alu.execute(ArithmeticLogicUnit.OP_SUB, 5, 10);
      System.out.println("Result: " + res4 + " | Flags: " + alu.getFlags()); // Output: 251 | 6 (Negative + Carry)
      
      int res5 = alu.execute(ArithmeticLogicUnit.OP_ADD, 250, 100);
      System.out.println("Result: " + res5 + " | Flags: " + alu.getFlags());
      
      
      for(int i = 0; i < 256; i++)
      {
         int res6 = alu.execute(ArithmeticLogicUnit.OP_ADD,i,0);
         System.out.println("Result: " + res6 + "| Flags: " + alu.getFlags());
      }
   }
  
}//end of class


