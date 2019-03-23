public class Response extends RatedSubmission
{
   public Response(User lastAuthor, String text) {
      update(lastAuthor, text);
   }
   public Response() {}
}
