import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post extends RatedSubmission
{
   private Response studentResponse = new Response();
   private Response instructorResponse = new Response(); 
   private Set<User> viewers = new HashSet<>(); 
   private List<FollowupDiscussion> followups = new ArrayList<>();    
   
   public Response getStudentResponse()
   {
      return studentResponse;
   }
   
   public Response getInstructorResponse()
   {
      return instructorResponse;
   }
   
   public int add(FollowupDiscussion followup)
   {
      followups.add(followup);
      return followups.size() - 1;
   }
   
   public FollowupDiscussion getFollowup(int n)
   {
      return followups.get(n);
   }
   
   public int getFollowupCount()
   {
      return followups.size();
   }
   
   public int view(User user)
   {
      viewers.add(user);
      return viewers.size();
   }
}
