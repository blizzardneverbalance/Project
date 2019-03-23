import java.util.ArrayList;
import java.util.List;

public class FollowupDiscussion extends Submission
{
   private boolean resolved;   
   private List<Response> responses = new ArrayList<>();
   
   public boolean isResolved()
   {
      return resolved;
   }
   
   public void resolve()
   {
      resolved = true;
   }
   
   public int getResponseCount()
   {
      return responses.size();
   }
   
   public Response getResponse(int n)
   {
      return responses.get(n);
   }
   
   public int addResponse(Response r)
   {
      responses.add(r);
      return responses.size() - 1;
   }
}
