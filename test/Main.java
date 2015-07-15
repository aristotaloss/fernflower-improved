public final class Main {
   public void coot() {
      byte var = 0;
      if(var == 7) {
         System.out.println("==");
      } else if(var != 7) {
         System.out.println("!=");
      } else if(var <= 7) {
         System.out.println(">=");
      } else if(var < 7) {
         System.out.println(">");
      } else if(var >= 7) {
         System.out.println("<=");
      } else if(var > 7) {
         System.out.println("<");
      } else if(var < 7) {
         System.out.println("<");
      }

      int var1 = 7 / var;
      int[] ints = new int[1];
      if(ints[0] == 7) {
         System.out.println("Okay");
      }

      if(ints[0] <= 7 || ints[0] != 4 || var1 == 6) {
         System.out.println("Okay");
      }

   }
}
