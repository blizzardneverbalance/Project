public class RatedSubmission extends Submission
{
   private boolean good;

   public boolean isGood()
   {
      return good;
   }
   
   public void setGood()
   {
      this.good = true;
   }
   
   public User getLastAuthor()
   {
      return getAuthor();
   }  
}
