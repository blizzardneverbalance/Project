

public class User
{
   public enum Type { STUDENT, INSTRUCTOR };
   private boolean instructor;
   private String name;
   private String password;
   
   public User(String name, String password, Type type)
   {
      this.name = name;
      this.password = password;
      instructor = type == Type.INSTRUCTOR;
   }
   
   public boolean authenticate(String name, String password)
   {
      return this.name.equals(name) && this.password.equals(password);
   }
   
   public boolean isInstructor()
   {
      return instructor;
   }
   
   public String toString()
   {
      return name;
   }
}
