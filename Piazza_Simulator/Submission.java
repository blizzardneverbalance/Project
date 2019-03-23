public class Submission
{
   private User lastAuthor;
   private String text;
   
   public Submission() {}
   
   public Submission(User lastAuthor, String text)
   {
      this.lastAuthor = lastAuthor;
      this.text = text;
   }

   public void update(User author, String text)
   {
      lastAuthor = author;
      this.text = text;
   }
   
   public User getAuthor()
   {
      return lastAuthor;
   }

   public String getText()
   {
      return text;
   }
}
